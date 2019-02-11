package com.gms.tfmedals.gui;

import com.gms.tfmedals.logic.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.util.StringConverter;

public final class AppController {
    private final ObservableList<MedalResult> medals = initialMedalResults();

    @FXML
    private TableView<MedalResult> medalTable;

    @FXML
    private TableColumn<MedalResult, String> duelistColumn;

    @FXML
    private TableColumn<MedalResult, Integer> medalColumn;

    @FXML
    private TreeTableView<MedalResult> predictionTable;

    @FXML
    private TreeTableColumn<MedalResult, String> predictionDuelistColumn;

    @FXML
    private TreeTableColumn<MedalResult, Integer> predictionMedalColumn;

    public AppController() {
    }

    private static ObservableList<MedalResult> initialMedalResults() {
        return FXCollections.observableArrayList(
            Duelist.allDuelists().stream().map(duelist -> new MedalResult(duelist, null))
                .toArray(MedalResult[]::new)
        );
    }

    @FXML
    private void initialize() {
        medalColumn.setCellFactory(TextFieldTableCell.forTableColumn(new MedalStringConverter()));
        medalColumn.setOnEditCommit(
            (TableColumn.CellEditEvent<MedalResult, Integer> t) ->
                (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
                ).setMedals(t.getNewValue())
            );

        duelistColumn.setReorderable(false);
        medalColumn.setReorderable(false);

        duelistColumn.setCellValueFactory(new PropertyValueFactory<>("duelistName"));
        medalColumn.setCellValueFactory(new PropertyValueFactory<>("medals"));
        medalTable.setItems(medals);

        final TreeItem<MedalResult> root = new TreeItem<>(
            new MedalResult(new Duelist("Classroom", 0, Location.CLASSROOM), null)
        );
        predictionTable.setRoot(root);

        predictionDuelistColumn.setCellValueFactory((CellDataFeatures<MedalResult, String> param) ->
            new ReadOnlyStringWrapper(param.getValue().getValue().getDuelistName())
        );
        predictionMedalColumn.setCellValueFactory((CellDataFeatures<MedalResult, Integer> param) ->
            new ReadOnlyObjectWrapper<>(param.getValue().getValue().getMedals())
        );
    }

    @FXML
    private void handlePredictButtonAction() {
        MedalFilter filter = new MedalFilter(medals);
        SeedRange range = new PS2SeedRange();
        FilterResult results = filter.results(range);
        System.out.println(results.getCount());
        System.out.println(results.getFirstSeed());
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
