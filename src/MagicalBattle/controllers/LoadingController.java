package MagicalBattle.controllers;

import MagicalBattle.constants.Settings;
import MagicalBattle.config.ConfigProcessor;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

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
            maxFileCount = ConfigProcessor.getAssetsNumber();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File assetsDir = new File("src/MagicalBattle/assets");
        AtomicInteger dotCounter = new AtomicInteger(0);
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(Settings.WAIT_TIME), (event) -> {
            dotCounter.set(dotCounter.get() == 2 ? 0 : dotCounter.incrementAndGet());
            loading.setText("Loading" + ".".repeat(Math.max(0, dotCounter.get())));
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        findFile(assetsDir);
        timeline.stop();
        file.setVisible(false);
        loading.setText("Loading Finished");
        start.setDisable(false);

        try {
            ConfigProcessor.setAssetsNumber(fileCount);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void findFile(File currentFile) {
        if (!currentFile.isDirectory()) {
            fileCount ++;
            file.setText("Loading\t\"" + currentFile.getPath() + "\"");
            progress.setProgress(Math.min(1, (double) fileCount / maxFileCount));
            String relativePath = ".." + currentFile.getPath().substring(currentFile.getPath().indexOf("\\assets"));
            if (currentFile.getName().contains(".png")) {
                image.setImage(new Image(Objects.requireNonNull(getClass().getResource(relativePath)).toExternalForm()));
            } else if (currentFile.getName().contains(".mp3")) {
                if (currentFile.getParentFile().getName().equals("bgm")) {
                    Media media = new Media(Objects.requireNonNull(getClass().getResource(relativePath)).toExternalForm());
                    MediaPlayer mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.setVolume(0);
                    mediaPlayer.play();
                    mediaPlayer.stop();
                } else {
                    AudioClip audioClip = new AudioClip(Objects.requireNonNull(getClass().getResource(relativePath)).toExternalForm());
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
        AudioClip audioClip = new AudioClip(Objects.requireNonNull(getClass().getResource("../assets/media/other/submit.mp3")).toExternalForm());
        audioClip.setVolume(Settings.EFFECT_VOLUME);
        audioClip.play();
        ViewController.toMenuScene();
    }
}
