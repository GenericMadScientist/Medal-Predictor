package com.gms.tfmedals.gui;

import javafx.beans.binding.ObjectBinding;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;

public final class OptionsController {
    private ConfigOptions options;

    @FXML
    private RadioButton ps2Toggle;

    @FXML
    private RadioButton pspToggle;

    @FXML
    private CheckBox filterCheckBox;

    void setOptions(ConfigOptions options) {
        unsetOldBindings();
        this.options = options;
        setNewControlValues();
        setNewOptionBindings();
    }

    private void unsetOldBindings() {
        options.consoleProperty().unbind();
        options.filterLowMedalsProperty().unbind();
    }

    private void setNewControlValues() {
        ps2Toggle.selectedProperty().set(options.getConsole().equals(Console.PS2));
        pspToggle.selectedProperty().set(options.getConsole().equals(Console.PSP));
        filterCheckBox.selectedProperty().set(options.getFilterLowMedals());
    }

    private void setNewOptionBindings() {
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
    }

    @FXML
    private void initialize() {
    }
}
