package com.MagicalBattle;

import com.MagicalBattle.controllers.ViewController;
import com.MagicalBattle.loaders.ResourceLoader;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Image icon = ResourceLoader.getImage("images/favicon.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setResizable(false);
        ViewController.currentStage = primaryStage;
        ViewController.toLoadingScene();
        ViewController.currentStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


