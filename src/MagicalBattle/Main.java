package MagicalBattle;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import MagicalBattle.controllers.ViewController;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("icon/pudding.png")));
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
