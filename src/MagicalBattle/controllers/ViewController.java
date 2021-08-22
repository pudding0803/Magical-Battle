package MagicalBattle.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import MagicalBattle.constants.Settings;

public class ViewController {
    public static Stage currentStage;

    public static void toLoadingScene() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ViewController.class.getResource("../views/loading.fxml")));
        Scene scene = new Scene(root, Settings.WIDTH, Settings.HEIGHT);
        scene.getRoot().requestFocus();
        currentStage.setTitle("Loading");
        currentStage.setScene(scene);
    }

    public static void toMenuScene() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ViewController.class.getResource("../views/menu.fxml")));
        Scene scene = new Scene(root, Settings.WIDTH, Settings.HEIGHT);
        scene.getRoot().requestFocus();
        currentStage.setTitle("Menu");
        currentStage.setScene(scene);
    }

    public static void toChoiceScene() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ViewController.class.getResource("../views/choice.fxml")));
        Scene scene = new Scene(root, Settings.WIDTH, Settings.HEIGHT);
        scene.getRoot().requestFocus();
        currentStage.setTitle("Choice");
        currentStage.setScene(scene);
    }

    public static void toBackgroundScene() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ViewController.class.getResource("../views/background.fxml")));
        Scene scene = new Scene(root, Settings.WIDTH, Settings.HEIGHT);
        scene.getRoot().requestFocus();
        currentStage.setTitle("Background");
        currentStage.setScene(scene);
    }

    public static void toGameScene() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ViewController.class.getResource("../views/game.fxml")));
        Scene scene = new Scene(root, Settings.WIDTH, Settings.HEIGHT);
        scene.getRoot().requestFocus();
        currentStage.setTitle("Game");
        currentStage.setScene(scene);
    }

    public static void closeStage() {
        currentStage.close();
    }
}
