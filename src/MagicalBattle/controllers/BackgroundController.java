package MagicalBattle.controllers;

import MagicalBattle.config.ConfigProcessor;
import MagicalBattle.constants.Settings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
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

public class BackgroundController implements Initializable {
    @FXML
    private Pane background;
    @FXML
    private GridPane backgroundList;

    private static final Media media = new Media(Objects.requireNonNull(BackgroundController.class.getResource("../assets/media/bgm/prepare.mp3")).toExternalForm());
    private static final MediaPlayer mediaPlayer = new MediaPlayer(media);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mediaPlayer.setVolume(Settings.BGM_VOLUME);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        try {
            background.setBackground(ConfigProcessor.getBackgroundImage());
            for (Node node : backgroundList.getChildren()) {
                if (Objects.equals(node.getId(), ConfigProcessor.getBackgroundName())) {
                    node.getStyleClass().add("chosen");
                    break;
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void choose(MouseEvent event) throws IOException, ParseException {
        ImageView chosen = (ImageView) event.getSource();
        for (Node node : backgroundList.getChildren()) {
            node.getStyleClass().clear();
        }
        chosen.getStyleClass().add("chosen");
        ConfigProcessor.setBackgroundImage(chosen.getId());
        background.setBackground(ConfigProcessor.getBackgroundImage());
    }

    @FXML
    public void switchToMenu() throws IOException {
        mediaPlayer.stop();
        AudioClip audioClip = new AudioClip(Objects.requireNonNull(getClass().getResource("../assets/media/other/cancel.mp3")).toExternalForm());
        audioClip.setVolume(Settings.EFFECT_VOLUME);
        audioClip.play();
        ViewController.toMenuScene();
    }
}
