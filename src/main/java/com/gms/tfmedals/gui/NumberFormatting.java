package com.gms.tfmedals.gui;

import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

final class NumberFormatting {
    private NumberFormatting() {
    }

    static TextFormatter<Integer> getFormatter() {
        return new TextFormatter<>(new IntegerStringConverter(), 0, change ->
        {
            String input = change.getControlNewText();
            if (input.matches("-?([1-9][0-9]*)?")) {
                return change;
            } else {
                return null;
            }
        });
    }
}