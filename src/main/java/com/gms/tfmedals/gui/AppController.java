package com.gms.tfmedals.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AppController {
    private final ObservableList<MedalResultModel> medals =
        FXCollections.observableArrayList(
            new MedalResultModel("Alexis", 4),
            new MedalResultModel("Chumley", 5)
        );

    @FXML
    private TableView<MedalResultModel> medalTable;

    @FXML
    private TableColumn<MedalResultModel, String> duelistColumn;

    @FXML
    private TableColumn<MedalResultModel, Integer> medalColumn;

    public AppController() {
    }

    @FXML
    private void initialize() {
        duelistColumn.setCellValueFactory(new PropertyValueFactory<>("duelistName"));
        medalColumn.setCellValueFactory(new PropertyValueFactory<>("medals"));
        medalTable.setItems(medals);
    }
}
