package com.gms.tfmedals.gui;

import com.gms.tfmedals.logic.Duelist;
import javafx.beans.binding.ObjectBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;

public final class OptionsController {
    private ConfigOptions options = null;

    @FXML
    private RadioButton ps2Toggle;

    @FXML
    private RadioButton pspToggle;

    @FXML
    private ComboBox<String> partnerComboBox;

    @FXML
    private CheckBox filterCheckBox;

    void setOptions(ConfigOptions options) {
        unsetOldBindings();
        this.options = options;
        setNewControlValues();
        setNewOptionBindings();
    }

    private void unsetOldBindings() {
        if (options != null) {
            options.consoleProperty().unbind();
            options.filterLowMedalsProperty().unbind();
        }
    }

    private void setNewControlValues() {
        if (options != null) {
            ps2Toggle.selectedProperty().set(options.getConsole().equals(Console.PS2));
            pspToggle.selectedProperty().set(options.getConsole().equals(Console.PSP));
            filterCheckBox.selectedProperty().set(options.getFilterLowMedals());
            partnerComboBox.getSelectionModel().select(options.getPartner());
        }
    }

    private void setNewOptionBindings() {
        if (options != null) {
            ObjectBinding<Console> cb = new ObjectBinding<>() {
                {
                    super.bind(ps2Toggle.selectedProperty());
                }

                @Override
                protected Console computeValue() {
                    return ps2Toggle.selectedProperty().get() ? Console.PS2 : Console.PSP;
                }
            };
            options.consoleProperty().bind(cb);
            options.filterLowMedalsProperty().bind(filterCheckBox.selectedProperty());
            options.partnerProperty().bind(partnerComboBox.getSelectionModel().selectedItemProperty());
        }
    }

    @FXML
    private void initialize() {
        ObservableList<String> partners = FXCollections.observableArrayList("-");
        partners.addAll(Duelist.allPartners());
        partnerComboBox.setItems(partners);
    }
}
