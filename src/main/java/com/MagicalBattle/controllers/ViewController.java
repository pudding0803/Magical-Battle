package com.MagicalBattle.controllers;

import com.MagicalBattle.constants.Settings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ViewController {
    public static Stage currentStage;

    private static URL getFXML(String path) {
        return Objects.requireNonNull(ViewController.class.getResource(Settings.RESOURCE_PATH + path));
    }

    public static void toLoadingScene() throws IOException {
        Parent root = FXMLLoader.load(getFXML("views/loading.fxml"));
        Scene scene = new Scene(root, Settings.WIDTH, Settings.HEIGHT);
        scene.getRoot().requestFocus();
        currentStage.setTitle("Loading");
        currentStage.setScene(scene);
    }

    public static void toMenuScene() throws IOException {
        Parent root = FXMLLoader.load(getFXML("views/menu.fxml"));
        Scene scene = new Scene(root, Settings.WIDTH, Settings.HEIGHT);
        scene.getRoot().requestFocus();
        currentStage.setTitle("Menu");
        currentStage.setScene(scene);
    }

    public static void toChoiceScene() throws IOException {
        Parent root = FXMLLoader.load(getFXML("views/choice.fxml"));
        Scene scene = new Scene(root, Settings.WIDTH, Settings.HEIGHT);
        scene.getRoot().requestFocus();
        currentStage.setTitle("Choice");
        currentStage.setScene(scene);
    }

    public static void toBackgroundScene() throws IOException {
        Parent root = FXMLLoader.load(getFXML("views/background.fxml"));
        Scene scene = new Scene(root, Settings.WIDTH, Settings.HEIGHT);
        scene.getRoot().requestFocus();
        currentStage.setTitle("Background");
        currentStage.setScene(scene);
    }

    public static void toGameScene() throws IOException {
        Parent root = FXMLLoader.load(getFXML("views/game.fxml"));
        Scene scene = new Scene(root, Settings.WIDTH, Settings.HEIGHT);
        scene.getRoot().requestFocus();
        currentStage.setTitle("Game");
        currentStage.setScene(scene);
    }

    public static void closeStage() {
        currentStage.close();
    }
}
