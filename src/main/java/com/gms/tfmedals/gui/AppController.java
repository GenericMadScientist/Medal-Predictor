package com.gms.tfmedals.gui;

import com.gms.tfmedals.logic.*;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public AppController() throws IOException {
        Path path = Paths.get("options.json");

        if (Files.exists(path)) {
            options = new ConfigOptions();
            try {
                JSONObject json = new JSONObject(Files.readString(path));
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

    private static ObservableList<MedalResult> initialMedalResults() {
        return FXCollections.observableArrayList(
            Duelist.allDuelists().stream().map(duelist -> new MedalResult(duelist, null))
                .toArray(MedalResult[]::new)
        );
    }

    @FXML
    private void initialize() {
        configureMedalsTable();
        configurePredictionsTable();
    }

    private void configureMedalsTable() {
        medalColumn.setCellFactory(TextFieldTableCell.forTableColumn(new MedalStringConverter()));
        medalColumn.setOnEditCommit(
            (CellEditEvent<MedalResult, Integer> t) ->
                (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
                ).setMedals(t.getNewValue())
        );

        duelistColumn.setReorderable(false);
        medalColumn.setReorderable(false);

        duelistColumn.setCellValueFactory(new PropertyValueFactory<>("duelistName"));
        medalColumn.setCellValueFactory(new PropertyValueFactory<>("medals"));
        medalTable.setItems(medals);
    }

    private void configurePredictionsTable() {
        predictionTable.setRoot(root);

        predictionDuelistColumn.setReorderable(false);
        predictionGroupColumn.setReorderable(false);
        predictionMedalColumn.setReorderable(false);

        predictionDuelistColumn.setCellValueFactory((CellDataFeatures<MedalResultPair, String> param) ->
            new ReadOnlyStringWrapper(param.getValue().getValue().getDuelistNames())
        );
        predictionGroupColumn.setCellValueFactory((CellDataFeatures<MedalResultPair, String> param) ->
            new ReadOnlyStringWrapper(param.getValue().getValue().getHousesString())
        );
        predictionMedalColumn.setCellValueFactory((CellDataFeatures<MedalResultPair, String> param) ->
            new ReadOnlyStringWrapper(param.getValue().getValue().getMedalsString())
        );
    }

    @FXML
    private void handlePredictButtonAction() {
        root.getChildren().clear();
        fiveCountLabel.setText("Number of 5s: -");

        MedalFilter filter = new MedalFilter(medals);
        SeedRange range = seedRangeFromOptions();
        FilterResult results = filter.results(range);

        matchingSeedsLabel.setText("Number of matching seeds: " + results.getCount());

        if (results.getCount() == 1 && results.getFirstSeed().isPresent()) {
            long seed = results.getFirstSeed().getAsLong();
            Collection<MedalResultPair> predictions = predictionsFromSeed(seed);
            long numberOfFives = predictions.stream().filter(x -> x.getMedalYield() == 5).count();
            fiveCountLabel.setText("Number of 5s: " + numberOfFives);
            fillInPredictions(predictions);
        }
    }

    private SeedRange seedRangeFromOptions() {
        if (options.getConsole().equals(Console.PS2)) {
            return new PS2SeedRange();
        } else {
            return new PSPSeedRange();
        }
    }

    private Collection<MedalResultPair> predictionsFromSeed(long seed) {
        Collection<MedalResultPair> results = MedalResultPair.resultsFromSeed(seed);
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
                locationNode.getChildren().addAll(resultsInLocation);
                root.getChildren().add(locationNode);
            }
        }
    }

    @FXML
    private void handleOptionsButtonAction() throws Exception {
        Stage stage = new Stage();
        stage.initOwner(medalTable.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        OptionsFXML optionsMenu = new OptionsFXML(options);
        optionsMenu.start(stage);
    }

    private class MedalStringConverter extends StringConverter<Integer> {
        @Override
        public String toString(Integer integer) {
            if (integer == null) {
                return null;
            } else {
                return integer.toString();
            }
        }

        @Override
        public Integer fromString(String s) {
            try {
                return Integer.valueOf(s);
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }
}
