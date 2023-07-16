package com.MagicalBattle.controllers;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.loaders.ConfigLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class BackgroundController implements Initializable {
    @FXML
    private Pane background;
    @FXML
    private GridPane backgroundList;

    private static final Media media = AssetLoader.getBackgroundMusic("prepare");
    private static final MediaPlayer mediaPlayer = new MediaPlayer(media);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mediaPlayer.setVolume(Settings.BGM_VOLUME);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        try {
            background.setBackground(ConfigLoader.getBackgroundImage());
            for (Node node : backgroundList.getChildren()) {
                if (Objects.equals(node.getId(), ConfigLoader.getBackgroundName())) {
                    node.getStyleClass().add("chosen");
                    break;
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        AtomicInteger index = new AtomicInteger(0);
        AssetLoader.getBackgroundImages().forEach((name, image) -> {
            ImageView imageView = new ImageView(image);
            imageView.setId(name);
            imageView.setFitHeight(160);
            imageView.setFitWidth(240);
            imageView.setOnMouseClicked(event -> {
                try {
                    choose(event);
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            });
            GridPane.setConstraints(imageView, index.get() % 3, index.get() / 3);
            index.getAndIncrement();
            backgroundList.getChildren().add(imageView);
        });
    }

    @FXML
    public void choose(MouseEvent event) throws IOException, ParseException {
        ImageView chosen = (ImageView) event.getSource();
        backgroundList.getChildren().forEach(node -> node.getStyleClass().clear());
        chosen.getStyleClass().add("chosen");
        ConfigLoader.setBackgroundImage(chosen.getId());
        background.setBackground(ConfigLoader.getBackgroundImage());
    }

    @FXML
    public void switchToMenu() throws IOException {
        mediaPlayer.stop();
        AudioClip audioClip = AssetLoader.getOtherAudio("cancel");
        audioClip.setVolume(Settings.EFFECT_VOLUME);
        audioClip.play();
        ViewController.toMenuScene();
    }
}
