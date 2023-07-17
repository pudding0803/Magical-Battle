package com.MagicalBattle.controllers;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.loaders.ConfigLoader;
import com.MagicalBattle.loaders.ProgressBarsLoader;
import com.MagicalBattle.models.enums.CharacterClass;
import com.MagicalBattle.models.enums.Direction;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import static com.MagicalBattle.loaders.AssetLoader.getCharacterImageSet;

public class ChoiceController implements Initializable {
    @FXML
    private Pane background;
    @FXML
    private VBox warriorVBox, guardianVBox, mageVBox, archerVBox, assassinVBox, alchemistVBox;
    @FXML
    private GridPane warriorGrid, guardianGrid, mageGrid, archerGrid, assassinGrid, alchemistGrid;
    @FXML
    private ImageView warriorImageView, guardianImageView, mageImageView, archerImageView, assassinImageView, alchemistImageView;
    @FXML
    private Button startButton;

    private static final Timeline timeline = new Timeline();

    private Direction key1, key2;
    private CharacterClass choice1, choice2;
    private static CharacterClass selected1, selected2;
    private static boolean pressed1 = false, pressed2 = false;

    private static final Media media = AssetLoader.getBackgroundMusic("prepare");
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

        ArrayList<VBox> vBoxes = new ArrayList<>(List.of(warriorVBox, guardianVBox, mageVBox, archerVBox, assassinVBox, alchemistVBox));
        ArrayList<GridPane> gridPanes = new ArrayList<>(List.of(warriorGrid, guardianGrid, mageGrid, archerGrid, assassinGrid, alchemistGrid));
        ArrayList<ImageView> imageViews = new ArrayList<>(List.of(warriorImageView, guardianImageView, mageImageView, archerImageView, assassinImageView, alchemistImageView));

        key1 = key2 = Direction.NULL;
        choice1 = CharacterClass.ARCHER; // WARRIOR;
        choice2 = CharacterClass.MAGE; // ALCHEMIST;
        selected1 = selected2 = CharacterClass.NULL;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                gridPanes.get(i).add(ProgressBarsLoader.getProgressBars(CharacterClass.getCharacter(i), j), 1, j);
            }
        }

        timeline.stop();
        timeline.getKeyFrames().clear();
        AtomicInteger count = new AtomicInteger(0);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(Settings.WAIT_TIME), (event) -> {
            for (int i = 0; i < 6; i++) {
                CharacterClass characterClass = CharacterClass.getCharacter(i);
                imageViews.get(i).setImage(getCharacterImageSet(characterClass).getPreparingOrSelect(count.get(), isSelected(characterClass)));
            }
            count.set(count.get() == Settings.CHOOSE_IMAGES_COUNT - 1 ? 0 : count.incrementAndGet());

            if (pressed1) {
                pressed1 = false;
                playAudioClip(selected1 == CharacterClass.NULL);
                selected1 = (selected1 == CharacterClass.NULL ? choice1 : CharacterClass.NULL);
            }
            if (pressed2) {
                pressed2 = false;
                playAudioClip(selected2 == CharacterClass.NULL);
                selected2 = (selected2 == CharacterClass.NULL ? choice2 : CharacterClass.NULL);
            }
            if (selected1 == CharacterClass.NULL) choice1 = updateChoice(key1, choice1.getValue());
            if (selected2 == CharacterClass.NULL) choice2 = updateChoice(key2, choice2.getValue());
            key1 = key2 = Direction.NULL;
            vBoxes.forEach(vBox -> vBox.setId(null));
            if (choice1 != choice2) {
                vBoxes.get(choice1.getValue()).setId(selected1 != CharacterClass.NULL ? "selected1" : "prepare1");
                vBoxes.get(choice2.getValue()).setId(selected2 != CharacterClass.NULL ? "selected2" : "prepare2");
            } else {
                vBoxes.get(choice1.getValue()).setId(selected1 != CharacterClass.NULL ? "selectedBoth" : "prepareBoth");
            }
            startButton.setDisable(selected1 == CharacterClass.NULL || selected2 == CharacterClass.NULL);
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private CharacterClass updateChoice(Direction key, int choice) {
        switch (key) {
            case UP -> {
                AssetLoader.playOtherAudio("choose");
                return CharacterClass.getCharacter(choice + (choice < 3 ? 3 : -3));
            }
            case DOWN -> {
                AssetLoader.playOtherAudio("choose");
                return CharacterClass.getCharacter(choice + (choice >= 3 ? -3 : 3));
            }
            case LEFT -> {
                AssetLoader.playOtherAudio("choose");
                return CharacterClass.getCharacter(choice + (choice == 0 || choice == 3 ? 2 : -1));
            }
            case RIGHT -> {
                AssetLoader.playOtherAudio("choose");
                return CharacterClass.getCharacter(choice + (choice == 2 || choice == 5 ? -2 : 1));
            }
            default -> {
                return CharacterClass.getCharacter(choice);
            }
        }
    }

    private boolean isSelected(CharacterClass career) {
        return selected1 == career || selected2 == career;
    }

    public static CharacterClass getSelected1() {
        return selected1;
    }

    public static CharacterClass getSelected2() {
        return selected2;
    }

    private void playAudioClip(boolean isSelected) {
        AssetLoader.playOtherAudio(isSelected ? "select" : "deselect");
    }

    @FXML
    public void pressHandle(KeyEvent event) throws IOException {
        switch (event.getCode()) {
            case W -> key1 = Direction.UP;
            case S -> key1 = Direction.DOWN;
            case A -> key1 = Direction.LEFT;
            case D -> key1 = Direction.RIGHT;
            case SPACE -> pressed1 = true;
            case UP -> key2 = Direction.UP;
            case DOWN -> key2 = Direction.DOWN;
            case LEFT -> key2 = Direction.LEFT;
            case RIGHT -> key2 = Direction.RIGHT;
            case ENTER -> pressed2 = true;
            case ESCAPE -> switchToMenu();
        }
    }

    @FXML
    public void switchToGame() throws IOException {
        mediaPlayer.stop();
        AssetLoader.playOtherAudio("submit");
        ViewController.toGameScene();
    }

    @FXML
    public void switchToMenu() throws IOException {
        mediaPlayer.stop();
        AssetLoader.playOtherAudio("cancel");
        ViewController.toMenuScene();
    }
}
