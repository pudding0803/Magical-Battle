package MagicalBattle.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

import MagicalBattle.enums.Career;

import java.util.ResourceBundle;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.IOException;
import java.net.URL;

import MagicalBattle.constants.CareerSettings;
import MagicalBattle.constants.Settings;
import MagicalBattle.enums.Direction;
import MagicalBattle.models.ImageSet;
import MagicalBattle.models.ProgressBars;

public class ChoiceController implements Initializable {
    @FXML
    private VBox warriorVBox, guardianVBox, mageVBox, archerVBox, assassinVBox, alchemistVBox;
    @FXML
    private GridPane warriorGrid, guardianGrid, mageGrid, archerGrid, assassinGrid, alchemistGrid;
    @FXML
    private ImageView warriorImageView, guardianImageView, mageImageView, archerImageView, assassinImageView, alchemistImageView;
    @FXML
    private Button startButton;

    private static final Timeline timeline = new Timeline();

    private final HashMap<Career, ImageSet> imageSetMap = CareerSettings.imageSetMap;
    private Direction key1, key2;
    private Career choice1, choice2;
    private static Career selected1, selected2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        key1 = key2 = Direction.NULL;
        choice1 = Career.MAGE; // Career.WARRIOR;
        choice2 = Career.MAGE; // Career.ALCHEMIST;
        selected1 = selected2 = Career.NULL;
        for (int i = 0; i < 6; i++) {
            warriorGrid.add(ProgressBars.getProgressBars(Career.WARRIOR, i), 1, i);
            guardianGrid.add(ProgressBars.getProgressBars(Career.GUARDIAN, i), 1, i);
            mageGrid.add(ProgressBars.getProgressBars(Career.MAGE, i), 1, i);
            archerGrid.add(ProgressBars.getProgressBars(Career.ARCHER, i), 1, i);
            assassinGrid.add(ProgressBars.getProgressBars(Career.ASSASSIN, i), 1, i);
            alchemistGrid.add(ProgressBars.getProgressBars(Career.ALCHEMIST, i), 1, i);
        }
        final HashMap<Career, VBox> vBoxes = new HashMap<>() {
            {
                put(Career.WARRIOR, warriorVBox);
                put(Career.GUARDIAN, guardianVBox);
                put(Career.MAGE, mageVBox);
                put(Career.ARCHER, archerVBox);
                put(Career.ASSASSIN, assassinVBox);
                put(Career.ALCHEMIST, alchemistVBox);
            }
        };
        AtomicInteger count = new AtomicInteger(0);
        Timeline timeline = new Timeline();
        KeyFrame cardKeyFrame = new KeyFrame(Duration.millis(Settings.WAIT_TIME), (event) -> {
            warriorImageView.setImage(imageSetMap.get(Career.WARRIOR).getPrepareOrSelect(count.get(), isSelected(Career.WARRIOR)));
            guardianImageView.setImage(imageSetMap.get(Career.GUARDIAN).getPrepareOrSelect(count.get(), isSelected(Career.GUARDIAN)));
            mageImageView.setImage(imageSetMap.get(Career.MAGE).getPrepareOrSelect(count.get(), isSelected(Career.MAGE)));
            archerImageView.setImage(imageSetMap.get(Career.ARCHER).getPrepareOrSelect(count.get(), isSelected(Career.ARCHER)));
            assassinImageView.setImage(imageSetMap.get(Career.ASSASSIN).getPrepareOrSelect(count.get(), isSelected(Career.ASSASSIN)));
            alchemistImageView.setImage(imageSetMap.get(Career.ALCHEMIST).getPrepareOrSelect(count.get(), isSelected(Career.ALCHEMIST)));
            count.set(count.get() == 2 ? 0 : count.incrementAndGet());
        });
        timeline.stop();
        timeline.getKeyFrames().clear();
        KeyFrame chooseKeyFrame = new KeyFrame(Duration.millis(Settings.UPDATE_TIME), (event) -> {
            if (selected1 == Career.NULL) choice1 = updateChoice(key1, choice1.ordinal());
            if (selected2 == Career.NULL) choice2 = updateChoice(key2, choice2.ordinal());
            key1 = key2 = Direction.NULL;
            for (VBox v : vBoxes.values()) v.setId(null);
            if (choice1 != choice2) {
                vBoxes.get(choice1).setId(selected1 != Career.NULL ? "selected1" : "prepare1");
                vBoxes.get(choice2).setId(selected2 != Career.NULL ? "selected2" : "prepare2");
            } else {
                vBoxes.get(choice1).setId(selected1 != Career.NULL ? "selectedBoth" : "prepareBoth");
            }
            startButton.setDisable(selected1 == Career.NULL || selected2 == Career.NULL);
        });
        timeline.getKeyFrames().add(cardKeyFrame);
        timeline.getKeyFrames().add(chooseKeyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private Career updateChoice(Direction key, int choice) {
        switch (key) {
            case UP -> {
                return Career.values()[choice + (choice < 4 ? 3 : -3)];
            }
            case DOWN -> {
                return Career.values()[choice + (choice >= 4 ? -3 : 3)];
            }
            case LEFT -> {
                return Career.values()[choice + (choice == 1 || choice == 4 ? 2 : -1)];
            }
            case RIGHT -> {
                return Career.values()[choice + (choice == 3 || choice == 6 ? -2 : 1)];
            }
            default -> {
                return Career.values()[choice];
            }
        }
    }

    private boolean isSelected(Career career) {
        return selected1 == career || selected2 == career;
    }

    public static Career getSelected1() {
        return selected1;
    }

    public static Career getSelected2() {
        return selected2;
    }

    @FXML
    public void pressHandle(KeyEvent event) throws IOException {
        switch (event.getCode()) {
            case W -> key1 = Direction.UP;
            case S -> key1 = Direction.DOWN;
            case A -> key1 = Direction.LEFT;
            case D -> key1 = Direction.RIGHT;
            case SPACE -> selected1 = (selected1 == Career.NULL ? choice1 : Career.NULL);
            case UP -> key2 = Direction.UP;
            case DOWN -> key2 = Direction.DOWN;
            case LEFT -> key2 = Direction.LEFT;
            case RIGHT -> key2 = Direction.RIGHT;
            case ENTER -> selected2 = (selected2 == Career.NULL ? choice2 : Career.NULL);
            case ESCAPE -> ViewController.toMenuScene();
        }
    }

    @FXML
    public void switchToGame() throws IOException {
        ViewController.toGameScene();
    }

    @FXML
    public void switchToMenu() throws IOException {
        ViewController.toMenuScene();
    }
}
