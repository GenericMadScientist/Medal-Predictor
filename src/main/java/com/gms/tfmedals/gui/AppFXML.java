package com.gms.tfmedals.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public final class AppFXML extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/predictor.fxml"));
        Scene scene = new Scene(root);

        stage.getIcons().add(new Image(
            getClass().getResourceAsStream("/images/winged_kuriboh.jpg"))
        );
        stage.setScene(scene);
        stage.setTitle("Medal Predictor");
        stage.show();
    }
}