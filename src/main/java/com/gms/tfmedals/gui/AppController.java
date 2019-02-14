package com.gms.tfmedals.gui;

import com.gms.tfmedals.logic.*;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public final class AppController {
    private static final int MEDALS_NEEDED = 80;

    private ConfigOptions options = null;
    private final ObservableList<MedalResult> medals = initialMedalResults();

    @FXML
    private TableView<MedalResult> medalTable;

    @FXML
    private TableColumn<MedalResult, String> duelistColumn;

    @FXML
    private TableColumn<MedalResult, Integer> medalColumn;

    @FXML
    private Button predictMedalsButton;

    @FXML
    private TreeTableView<MedalResultPair> predictionTable;

    private final TreeItem<MedalResultPair> root = new TreeItem<>(null);

    @FXML
    private TreeTableColumn<MedalResultPair, String> predictionDuelistColumn;

    @FXML
    private TreeTableColumn<MedalResultPair, String> predictionGroupColumn;

    @FXML
    private TreeTableColumn<MedalResultPair, String> predictionMedalColumn;

    @FXML
    private Label matchingSeedsLabel;

    @FXML
    private Label fiveCountLabel;

    @FXML
    private Label timeOffLabel;

    private boolean isAlreadyRunning = false;
    private String lastKey = null;

    public AppController() throws IOException {
        Path path = Paths.get("options.json");

        if (Files.exists(path)) {
            options = new ConfigOptions();
            try {
                JSONObject json = new JSONObject(allFileText());
                options.readFromJson(json);
            } catch (JSONException ignore) {
                options = null;
            }
        }

        if (options == null) {
            options = new ConfigOptions();
            Files.write(path, options.toJson().toString().getBytes());
        }
    }

    private String allFileText() throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get("options.json"));
        return new String(encoded);
    }

    private static ObservableList<MedalResult> initialMedalResults() {
        return FXCollections.observableArrayList(
            Duelist.allDuelists().stream().map(duelist -> new MedalResult(duelist, null))
                .toArray(MedalResult[]::new)
        );
    }

    ConfigOptions getOptions() {
        return options;
    }

    @FXML
    private void initialize() {
        configureMedalsTable();
        configurePredictionsTable();
    }

    private void configureMedalsTable() {
        medalColumn.setCellFactory(x -> new MedalEditingCell());
        medalColumn.setOnEditCommit(event -> {
            int row = event.getTablePosition().getRow();
            event.getTableView().getItems().get(row).setMedals(event.getNewValue());
            if ((row + 1) < medals.size()) {
                medalTable.getSelectionModel().selectBelowCell();
                medalTable.scrollTo(row);
            }
        });

        duelistColumn.setReorderable(false);
        medalColumn.setReorderable(false);

        duelistColumn.setCellValueFactory(new PropertyValueFactory<>("duelistName"));
        medalColumn.setCellValueFactory(new PropertyValueFactory<>("medals"));
        medalTable.setItems(medals);

        medalTable.setOnKeyPressed(event -> {
            if (event.getCode().isDigitKey() && (medalTable.getEditingCell() == null)) {
                if (medalTable.getSelectionModel().getSelectedIndex() != -1) {
                    lastKey = event.getText();
                    medalTable.edit(medalTable.getSelectionModel().getSelectedIndex(), medalColumn);
                    medalTable.getFocusModel().getFocusedCell();
                }
            }
        });
    }

    private void configurePredictionsTable() {
        predictionTable.setRoot(root);

        predictionDuelistColumn.setReorderable(false);
        predictionGroupColumn.setReorderable(false);
        predictionMedalColumn.setReorderable(false);

        predictionDuelistColumn.setCellValueFactory(param ->
            new ReadOnlyStringWrapper(param.getValue().getValue().getDuelistNames())
        );
        predictionGroupColumn.setCellValueFactory(param ->
            new ReadOnlyStringWrapper(param.getValue().getValue().getHousesString())
        );
        predictionMedalColumn.setCellValueFactory(param ->
            new ReadOnlyStringWrapper(param.getValue().getValue().getMedalsString())
        );
    }

    @FXML
    private void handlePredictButtonAction() {
        if (!isAlreadyRunning) {
            predictMedalsButton.setText("Please wait...");
            root.getChildren().clear();
            fiveCountLabel.setText("Number of 5s: -");
            timeOffLabel.setText("Time off: -");
            isAlreadyRunning = true;

            new Thread(() -> {
                MedalFilter filter = new MedalFilter(medals);
                SeedRange range = seedRangeFromOptions();
                FilterResult results = filter.results(range);

                Platform.runLater(() -> {
                    matchingSeedsLabel.setText("Number of matching seeds: " + results.getCount());
                    if (results.getCount() == 1 && results.getFirstSeed().isPresent()) {
                        long seed = results.getFirstSeed().getAsLong();
                        System.out.println(seed);
                        Collection<MedalResultPair> predictions = predictionsFromSeed(seed);
                        long numberOfFives = predictions.stream()
                            .filter(x -> x.getMedalYield() == 5).count();
                        fiveCountLabel.setText("Number of 5s: " + numberOfFives);
                        fillInPredictions(predictions);
                        setTimeOffText(seed);
                    }
                    predictMedalsButton.setText("Predict Medals");
                    isAlreadyRunning = false;
                });
            }).start();
        }
    }

    private SeedRange seedRangeFromOptions() {
        if (options.getConsole().equals(Console.PS2)) {
            return new PS2SeedRange();
        } else if (options.getLastTime() == null) {
            return new PSPSeedRange();
        } else {
            return new PSPSeedRange(options.getLastTime() - 1000000 * options.getPspTimerDelay(),
                1000000 * options.getPspTimerUncertainty());
        }
    }

    private Collection<MedalResultPair> predictionsFromSeed(long seed) {
        Collection<MedalResultPair> results = MedalResultPair.resultsFromSeed(seed).stream()
            .filter(x -> !x.hasMember(options.getPartner()))
            .collect(Collectors.toList());
        if (!options.getFilterLowMedals()) {
            return results;
        }

        Collection<MedalResultPair> highResults = Collections.emptyList();
        int medalThreshold = 4;

        while (highResults.stream().mapToInt(MedalResultPair::getMedalYield).sum() < MEDALS_NEEDED) {
            final int thresholdCopy = medalThreshold;
            highResults = results.stream().filter(x -> x.getMedalYield() > thresholdCopy)
                .collect(Collectors.toList());
            medalThreshold--;
        }

        return highResults;
    }

    private void fillInPredictions(Collection<MedalResultPair> predictions) {
        for (Location location : Location.values()) {
            Collection<TreeItem<MedalResultPair>> resultsInLocation = predictions.stream()
                .filter(x -> x.getLocation().equals(location))
                .map(TreeItem::new)
                .collect(Collectors.toList());

            if (!resultsInLocation.isEmpty()) {
                TreeItem<MedalResultPair> locationNode = new TreeItem<>(
                    MedalResultPair.dummyPair(location.toString())
                );
                locationNode.setExpanded(true);
                locationNode.getChildren().addAll(resultsInLocation);
                root.getChildren().add(locationNode);
            }
        }
    }

    private void setTimeOffText(long seed) {
        if (options.getConsole().equals(Console.PSP) && (options.getLastTime() != null)) {
            long time = options.getLastTime() - 1000000 * options.getPspTimerDelay();
            double timeDiffInSecs = ((int) (time - seed)) / 1000000.0;
            timeOffLabel.setText("Time off: "
                + new DecimalFormat("#.00#").format(timeDiffInSecs) + 's');
        }
    }

    @FXML
    private void handleRecordTimeAction() {
        options.setLastTime(System.currentTimeMillis() * 1000);
    }

    @FXML
    private void handleClearTimeAction() {
        options.setLastTime(null);
    }

    @FXML
    private void handleOptionsButtonAction() throws Exception {
        Stage stage = new Stage();
        stage.initOwner(medalTable.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        OptionsFXML optionsMenu = new OptionsFXML(options);
        optionsMenu.start(stage);
    }

    /* Life-saving cell I took from StackOverflow. See
       https://stackoverflow.com/questions/21987552/how-to-make-a-javafx-tableview-cell-editable-without-first-pressing-enter
     */
    private class MedalEditingCell extends TableCell<MedalResult, Integer> {
        private TextField textField;

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                Platform.runLater(() -> textField.requestFocus());
                if (lastKey != null) {
                    textField.setText(lastKey);
                    lastKey = null;
                    Platform.runLater(() -> {
                        textField.deselect();
                        textField.end();
                    });
                }
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText(getItem().toString());
            setGraphic(null);
        }

        @Override
        public void updateItem(Integer item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(null);
            }
        }

        private void createTextField() {
            textField = new TextField(getString());

            textField.focusedProperty().addListener((arg0, arg1, arg2) -> {
                if (!arg2) {
                    commit();
                }
            });

            textField.setOnKeyReleased(t -> {
                if (t.getCode() == KeyCode.ENTER) {
                    commit();
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            });

            textField.addEventFilter(KeyEvent.KEY_RELEASED, t -> {
                if (t.getCode() == KeyCode.DELETE) {
                    t.consume();
                }
            });
        }

        private void commit() {
            commitEdit(textField.getText().isEmpty() ? null : Integer.valueOf(textField.getText()));
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
}
