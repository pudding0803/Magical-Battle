package com.MagicalBattle.controllers;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.loaders.ConfigLoader;
import com.MagicalBattle.models.enums.CharacterClass;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class MenuController implements Initializable {
    @FXML
    private Pane background;
    @FXML
    private ImageView image1, image2, image3, image4, image5, image6;

    @FXML
    public void exit() {
        ViewController.closeStage();
    }

    private static final Timeline timeline = new Timeline();

    private static final Media media = AssetLoader.getBackgroundMusic("menu");
    private static final MediaPlayer mediaPlayer = new MediaPlayer(media);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mediaPlayer.setVolume(Settings.BGM_VOLUME);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        try {
            background.setBackground(ConfigLoader.getBackgroundImage());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        ArrayList<ImageView> imageViews = new ArrayList<>(List.of(image1, image2, image3, image4, image5, image6));
        ArrayList<Boolean> isSelected = new ArrayList<>(List.of(true, true, true, false, false, false));

        AtomicInteger counter = new AtomicInteger(0);
        timeline.stop();
        timeline.getKeyFrames().clear();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(Settings.WAIT_TIME), (event) -> {
            if (counter.get() % 3 == 0) {
                Collections.shuffle(imageViews);
                Collections.shuffle(isSelected);
            }
            for (int i = 0; i < imageViews.size(); i++) {
                CharacterClass characterClass = CharacterClass.getCharacter(i);
                if (isSelected.get(i)) imageViews.get(i).setImage(AssetLoader.getCharacterImageSet(characterClass).getPreparingOrSelect(counter.get(), false));
                else imageViews.get(i).setImage(AssetLoader.getCharacterImageSet(characterClass).getPreparingOrSelect(counter.get(), true));
            }
            counter.set(counter.get() == 2 ? 0 : counter.incrementAndGet());
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    public void switchToChoice() throws IOException {
        mediaPlayer.stop();
        AssetLoader.playOtherAudio("submit");
        ViewController.toChoiceScene();
    }

    @FXML
    public void switchToBackground() throws IOException {
        mediaPlayer.stop();
        AssetLoader.playOtherAudio("submit");
        ViewController.toBackgroundScene();
    }
}
