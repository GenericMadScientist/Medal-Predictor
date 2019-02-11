package com.gms.tfmedals.gui;

import com.gms.tfmedals.logic.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
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

import java.util.Collection;
import java.util.stream.Collectors;

public final class AppController {
    private ConfigOptions options = new ConfigOptions();
    private final ObservableList<MedalResult> medals = initialMedalResults();

    @FXML
    private TableView<MedalResult> medalTable;

    @FXML
    private TableColumn<MedalResult, String> duelistColumn;

    @FXML
    private TableColumn<MedalResult, Integer> medalColumn;

    @FXML
    private TreeTableView<MedalResult> predictionTable;

    private final TreeItem<MedalResult> root = new TreeItem<>(new MedalResult(null, null));

    @FXML
    private TreeTableColumn<MedalResult, String> predictionDuelistColumn;

    @FXML
    private TreeTableColumn<MedalResult, Integer> predictionMedalColumn;

    @FXML
    private Label matchingSeedsLabel;

    @FXML
    private Label fiveCountLabel;

    public AppController() {
        options.toJson();
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
        predictionMedalColumn.setReorderable(false);

        predictionDuelistColumn.setCellValueFactory((CellDataFeatures<MedalResult, String> param) ->
            new ReadOnlyStringWrapper(param.getValue().getValue().getDuelistName())
        );
        predictionMedalColumn.setCellValueFactory((CellDataFeatures<MedalResult, Integer> param) ->
            new ReadOnlyObjectWrapper<>(param.getValue().getValue().getMedals())
        );
    }

    @FXML
    private void handlePredictButtonAction() {
        root.getChildren().clear();
        fiveCountLabel.setText("Number of 5s: -");

        MedalFilter filter = new MedalFilter(medals);
        SeedRange range = new PS2SeedRange();
        FilterResult results = filter.results(range);

        matchingSeedsLabel.setText("Number of matching seeds: " + results.getCount());

        if (results.getCount() == 1 && results.getFirstSeed().isPresent()) {
            long seed = results.getFirstSeed().getAsLong();
            Collection<MedalResult> predictions = MedalResult.resultsFromSeed(seed);
            long numberOfFives = predictions.stream().filter(x -> x.getMedals() == 5).count();
            fiveCountLabel.setText("Number of 5s: " + numberOfFives);
            fillInPredictions(predictions);
        }
    }

    private void fillInPredictions(Collection<MedalResult> predictions) {
        for (Location location : Location.values()) {
            Collection<TreeItem<MedalResult>> resultsInLocation = predictions.stream()
                .filter(x -> x.getLocation().equals(location))
                .map(TreeItem::new)
                .collect(Collectors.toList());

            if (!resultsInLocation.isEmpty()) {
                TreeItem<MedalResult> locationNode = new TreeItem<>(
                    new MedalResult(new Duelist(location.toString(), 0, location), null)
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
        OptionsFXML optionsMenu = new OptionsFXML();
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
