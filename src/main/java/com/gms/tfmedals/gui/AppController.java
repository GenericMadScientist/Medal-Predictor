package com.gms.tfmedals.gui;

import com.gms.tfmedals.logic.Duelist;
import com.gms.tfmedals.logic.MedalResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

public class AppController {
    private final ObservableList<MedalResult> medals = initMedalResults();


    @FXML
    private TableView<MedalResult> medalTable;

    @FXML
    private TableColumn<MedalResult, String> duelistColumn;

    @FXML
    private TableColumn<MedalResult, Integer> medalColumn;

    public AppController() {
    }

    private static ObservableList<MedalResult> initMedalResults() {
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
