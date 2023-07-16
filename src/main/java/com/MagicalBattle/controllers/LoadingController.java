package com.MagicalBattle.controllers;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.loaders.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingController implements Initializable {
    @FXML
    Label file, loading;
    @FXML
    ImageView image;
    @FXML
    ProgressBar progress;
    @FXML
    Button start;

    private static int fileCount = 0;
    private static long maxFileCount;

    static {
        try {
            maxFileCount = ConfigLoader.getAssetsNumber();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AbilityLoader.loadAbilities();
        ImagesLoader.loadCharacterImages();
        ImagesLoader.loadBackgroundImages();
        ImagesLoader.loadSkillImages();
        ImagesLoader.loadEffectImages();
        ProgressBarsLoader.loadProgressBars();
//        File assetsDir = new File("src\\main\\java\\com\\MagicalBattle");
//        AtomicInteger dotCounter = new AtomicInteger(0);
//        Timeline timeline = new Timeline();
//        KeyFrame keyFrame = new KeyFrame(Duration.millis(Settings.WAIT_TIME), (event) -> {
//            dotCounter.set(dotCounter.get() == 2 ? 0 : dotCounter.incrementAndGet());
//            loading.setText("Loading" + ".".repeat(Math.max(0, dotCounter.get())));
//        });
//        timeline.getKeyFrames().add(keyFrame);
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
//
//        findFile(assetsDir);
//        timeline.stop();
        file.setVisible(false);
        loading.setText("Loading Finished");
        start.setDisable(false);

        try {
            ConfigLoader.setAssetsNumber(fileCount);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void findFile(File currentFile) {
        if (!currentFile.isDirectory()) {
            fileCount++;
            file.setText("Loading\t\"" + currentFile.getPath() + "\"");
            progress.setProgress(Math.min(1, (double) fileCount / maxFileCount));
            String relativePath = currentFile.getPath();
            if (currentFile.getName().contains(".png")) {
                image.setImage(ResourceLoader.getImage(relativePath));
            } else if (currentFile.getName().contains(".mp3")) {
                if (currentFile.getParentFile().getName().equals("bgm")) {
                    Media media = ResourceLoader.getMedia(relativePath);
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.setVolume(0);
                    mediaPlayer.play();
                    mediaPlayer.stop();
                } else {
                    AudioClip audioClip = ResourceLoader.getAudioClip(relativePath);
                    audioClip.setVolume(0);
                    audioClip.play();
                }
            }
            return;
        }
        File[] fileList = currentFile.listFiles();
        assert fileList != null;
        for (File file : fileList) {
            findFile(file);
        }
    }

    @FXML
    public void switchToMenu() throws IOException {
        AudioClip audioClip = ResourceLoader.getAudioClip("media/other/submit.mp3");
        audioClip.setVolume(Settings.EFFECT_VOLUME);
        audioClip.play();
        ViewController.toMenuScene();
    }
}
