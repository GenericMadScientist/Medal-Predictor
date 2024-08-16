package com.gms.tfmedals.gui;

import com.gms.tfmedals.logic.ConfigOptions;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class AppFXML extends Application {
    private AppController controller;

    @Override
    public void start(final Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/predictor.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        controller = loader.getController();

        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/winged_kuriboh.jpg")));
        stage.setScene(scene);
        stage.setTitle("Medal Predictor");
        stage.show();
    }

    @Override
    public void stop() throws IOException {
        ConfigOptions options = controller.getOptions();
        if (options != null) {
            Path path = Paths.get("options.json");
            Files.write(path, options.toJson().toString().getBytes());
        }
    }
}
