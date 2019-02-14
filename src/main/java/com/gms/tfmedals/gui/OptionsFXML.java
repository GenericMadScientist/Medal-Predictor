package com.gms.tfmedals.gui;

import com.gms.tfmedals.logic.ConfigOptions;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public final class OptionsFXML extends Application {
    private ConfigOptions options;

    OptionsFXML(ConfigOptions options) {
        this.options = options;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/options.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        ((OptionsController) loader.getController()).setOptions(options);

        stage.getIcons().add(new Image(
            getClass().getResourceAsStream("/images/winged_kuriboh.jpg"))
        );
        stage.setScene(scene);
        stage.setTitle("Options");
        stage.showAndWait();
    }
}
