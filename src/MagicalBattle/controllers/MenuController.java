package MagicalBattle.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import MagicalBattle.models.enums.Career;
import MagicalBattle.constants.CareerSettings;
import MagicalBattle.constants.Settings;
import MagicalBattle.models.ImageSet;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MenuController implements Initializable {
    @FXML
    private ImageView image1, image2, image3, image4, image5, image6;

    private final HashMap<Career, ImageSet> imageSetMap = CareerSettings.imageSetMap;

    @FXML
    public void exit() {
        ViewController.closeStage();
    }

    private static final Timeline timeline = new Timeline();

    private static final Media media = new Media(Objects.requireNonNull(MenuController.class.getResource("../assets/media/bgm/menu.mp3")).toExternalForm());
    private static final MediaPlayer mediaPlayer = new MediaPlayer(media);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mediaPlayer.setVolume(Settings.BGM_VOLUME);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
        ArrayList<ImageView> imageViews = new ArrayList<>() {
            {
                add(image1);
                add(image2);
                add(image3);
                add(image4);
                add(image5);
                add(image6);
            }
        };
        ArrayList<Boolean> isSelected = new ArrayList<>() {
            {
                for (int i = 0; i < 3; i++) add(true);
                for (int i = 0; i < 3; i++) add(false);
            }
        };
        ArrayList<ArrayList<Image>> prepareImages = new ArrayList<>() {
            {
                for (int i = 0; i <= 2; i++) {
                    int finalI = i;
                    add(new ArrayList<>() {
                        {
                            for (int j = 1; j <= 6; j++)
                                add(imageSetMap.get(Career.getCareer(j)).getPrepareOrSelect(finalI, false));
                        }
                    });
                }
            }
        };
        ArrayList<ArrayList<Image>> selectedImages = new ArrayList<>() {
            {
                for (int i = 0; i <= 2; i++) {
                    int finalI = i;
                    add(new ArrayList<>() {
                        {
                            for (int j = 1; j <= 6; j++)
                                add(imageSetMap.get(Career.getCareer(j)).getPrepareOrSelect(finalI, true));
                        }
                    });
                }
            }
        };
        AtomicInteger counter = new AtomicInteger(0);
        timeline.stop();
        timeline.getKeyFrames().clear();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(Settings.WAIT_TIME), (event) -> {
            if (counter.get() % 3 == 0) {
                Collections.shuffle(imageViews);
                Collections.shuffle(isSelected);
            }
            for (int i = 0; i < imageViews.size(); i++) {
                if (isSelected.get(i)) imageViews.get(i).setImage(prepareImages.get(counter.get()).get(i));
                else imageViews.get(i).setImage(selectedImages.get(counter.get()).get(i));
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
        AudioClip audioClip = new AudioClip(Objects.requireNonNull(getClass().getResource("../assets/media/other/submit.mp3")).toExternalForm());
        audioClip.setVolume(Settings.EFFECT_VOLUME);
        audioClip.play();
        ViewController.toChoiceScene();
    }
}
