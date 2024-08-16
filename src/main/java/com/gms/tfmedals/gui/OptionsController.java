package com.gms.tfmedals.gui;

import com.gms.tfmedals.logic.ConfigOptions;
import com.gms.tfmedals.logic.Console;
import com.gms.tfmedals.logic.Duelist;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.ObjectBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

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

    @FXML
    private TextField timerDelayField;

    @FXML
    private TextField timerUncertaintyField;

    void setOptions(final ConfigOptions options) {
        unsetOldBindings();
        this.options = options;
        setNewControlValues();
        setNewOptionBindings();
    }

    private void unsetOldBindings() {
        if (options != null) {
            options.consoleProperty().unbind();
            options.filterLowMedalsProperty().unbind();
            options.partnerProperty().unbind();
            options.pspTimerDelayProperty().unbind();
            options.pspTimerUncertaintyProperty().unbind();
        }
    }

    private void setNewControlValues() {
        if (options == null) {
            return;
        }

        Console console = options.getConsole();
        ps2Toggle.selectedProperty().set(console.equals(Console.PS2));
        pspToggle.selectedProperty().set(console.equals(Console.PSP));
        filterCheckBox.selectedProperty().set(options.getFilterLowMedals());
        partnerComboBox.getSelectionModel().select(options.getPartner());
        timerDelayField.setText(Integer.toString(options.getPspTimerDelay()));
        timerUncertaintyField.setText(Integer.toString(options.getPspTimerUncertainty()));
    }

    private void setNewOptionBindings() {
        if (options == null) {
            return;
        }

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

        IntegerBinding delayProperty = new IntegerBinding() {
            {
                super.bind(timerDelayField.textProperty());
            }

            @Override
            protected int computeValue() {
                try {
                    return Integer.valueOf(timerDelayField.getText());
                } catch (NumberFormatException ignored) {
                    return 0;
                }
            }
        };
        options.pspTimerDelayProperty().bind(delayProperty);

        IntegerBinding uncertaintyProperty = new IntegerBinding() {
            {
                super.bind(timerUncertaintyField.textProperty());
            }

            @Override
            protected int computeValue() {
                try {
                    return Integer.valueOf(timerUncertaintyField.getText());
                } catch (NumberFormatException ignored) {
                    return 0;
                }
            }
        };
        options.pspTimerUncertaintyProperty().bind(uncertaintyProperty);
    }

    @FXML
    @SuppressWarnings("PMD")
    private void initialize() {
        ObservableList<String> partners = FXCollections.observableArrayList("-");
        partners.addAll(Duelist.allPartners());
        partnerComboBox.setItems(partners);

        timerDelayField.setTextFormatter(NumberFormatting.getFormatter());
        timerUncertaintyField.setTextFormatter(NumberFormatting.getFormatter());
    }
}
