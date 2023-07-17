package com.MagicalBattle.controllers;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.loaders.ConfigLoader;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.EffectObject.EffectObject;
import com.MagicalBattle.models.Enums.Attack;
import com.MagicalBattle.models.Enums.HDirection;
import com.MagicalBattle.models.Enums.VDirection;
import com.MagicalBattle.models.SkillObject.SkillObject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class GameController implements Initializable {
    @FXML
    private AnchorPane face1, face2;
    @FXML
    private ImageView imageView1, imageView2;
    @FXML
    private Pane background, skillPane, effectPane;
    @FXML
    private ProgressBar health1, health2, magic1, magic2;

    private static final Timeline timeline = new Timeline();
    private static final ArrayList<SkillObject> allSkillObjects = new ArrayList<>();
    private static final ArrayList<EffectObject> allEffectObjects = new ArrayList<>();

    private static Character player1, player2;

    private static final Media media = AssetLoader.getBackgroundMusic("8bits");
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

        health1.setProgress(0);
        health2.setProgress(0);
        magic1.setProgress(0);
        magic2.setProgress(0);
        player1 = ChoiceController.getSelected1().createPlayer(imageView1, true);
        player2 = ChoiceController.getSelected2().createPlayer(imageView2, false);
        timeline.stop();
        timeline.getKeyFrames().clear();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(Settings.UPDATE_TIME), (event) -> {
            updateProgressBars();
            if (player1.isDead() || player2.isDead()) gameOver();

            player1.updateEffect();
            player2.updateEffect();

            updateSkillObjects();
            updateEffectObjects();

            playerAction(player1);
            playerAction(player2);
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void setStyles(double rate, boolean isPlayer1) {
        (isPlayer1 ? health1 : health2).getStyleClass().clear();
        (isPlayer1 ? health1 : health2).getStyleClass().add("progress-bar");
        (isPlayer1 ? face1 : face2).getStyleClass().clear();
        (isPlayer1 ? face1 : face2).getStyleClass().add("face");
        if (rate <= 0.2) {
            (isPlayer1 ? health1 : health2).getStyleClass().add("dangerous");
            (isPlayer1 ? face1 : face2).getStyleClass().add((isPlayer1 ? player1 : player2).getCharacterClass().getName() + "0");
        } else if (rate <= 0.5) {
            (isPlayer1 ? health1 : health2).getStyleClass().add("warning");
            (isPlayer1 ? face1 : face2).getStyleClass().add((isPlayer1 ? player1 : player2).getCharacterClass().getName() + "1");
        } else {
            (isPlayer1 ? health1 : health2).getStyleClass().add("healthy");
            (isPlayer1 ? face1 : face2).getStyleClass().add((isPlayer1 ? player1 : player2).getCharacterClass().getName() + "2");
        }
    }

    public void updateProgressBars() {
        double rate1 = Math.max(player1.getHealthRate(), 0.0);
        double rate2 = Math.max(player2.getHealthRate(), 0.0);
        setStyles(rate1, true);
        setStyles(rate2, false);
        health1.setProgress(rate1);
        health2.setProgress(rate2);
        magic1.setProgress(player1.getMagicRate());
        magic2.setProgress(player2.getMagicRate());
    }

    public static void newSkillObject(SkillObject skillObject) {
        allSkillObjects.add(skillObject);
    }

    private void updateSkillObjects() {
        skillPane.getChildren().clear();
        for (int i = 0; i < allSkillObjects.size(); i++) {
            SkillObject skillObject = allSkillObjects.get(i);
            skillPane.getChildren().add(skillObject.getImageView());
            skillObject.setX(skillObject.getX() + skillObject.getVelocityX());
            skillObject.setX(skillObject.getX() + skillObject.getVelocityY());
            skillObject.doByTime();
            if (skillObject.getX() <= 0 || skillObject.getX() >= Settings.WIDTH - skillObject.getWidth() ||
                    skillObject.getY() <= 0 || skillObject.getY() >= Settings.GROUND_HEIGHT ||
                    player1.isCollidedFromOther(skillObject) || player2.isCollidedFromOther(skillObject))
                allSkillObjects.remove(i--);
        }
    }

    public static void newEffectObject(EffectObject effectObject) {
        allEffectObjects.add(effectObject);
    }

    private void updateEffectObjects() {
        effectPane.getChildren().clear();
        for (int i = 0; i < allEffectObjects.size(); i++) {
            EffectObject effectObject = allEffectObjects.get(i);
            effectPane.getChildren().add(effectObject.getEffect());
            effectObject.doByTime();
            if (effectObject.isFinished()) allEffectObjects.remove(i--);
        }
    }

    private void playerAction(Character character) {
        if (character.isUp() || character.isDown()) character.updateVelocity();
        character.doVerticalMotion();
        if (character.isLeft() || character.isRight()) character.doHorizonMotion();
        else character.setStand();
        if (character.getAttackTimers().isPressing(Attack.ATTACK) && character.getAttackTimers().isAttackValid(Attack.ATTACK)) character.attack();
    }

    private void gameOver() {
        mediaPlayer.stop();
        timeline.stop();
        timeline.getKeyFrames().clear();
        player1.stopStatusTimers();
        player2.stopStatusTimers();
        player1.updateEffect();
        player2.updateEffect();
        AtomicInteger deadCounter = new AtomicInteger(0);
        KeyFrame gameOver = new KeyFrame(Duration.millis(Settings.UPDATE_TIME), (event) -> {
            updateSkillObjects();
            updateEffectObjects();
            player1.doVerticalMotion();
            player2.doVerticalMotion();

            if (player1.isDead()) player1.setGameOverImage(false, -1);
            else player1.setGameOverImage(true, (int) ((deadCounter.get() + 1) * Settings.UPDATE_TIME / Settings.WAIT_TIME));

            if (player2.isDead()) player2.setGameOverImage(false, -1);
            else player2.setGameOverImage(true, (int) ((deadCounter.get() + 1) * Settings.UPDATE_TIME / Settings.WAIT_TIME));

            deadCounter.set((deadCounter.get() + 2) * Settings.UPDATE_TIME / Settings.WAIT_TIME == 3 ? 0 : deadCounter.incrementAndGet());
        });
        timeline.setCycleCount((int) (14 * Settings.WAIT_TIME / Settings.UPDATE_TIME));
        timeline.getKeyFrames().add(gameOver);
        timeline.play();
        timeline.setOnFinished((event) -> {
            try {
                allSkillObjects.clear();
                allEffectObjects.clear();
                switchToChoice();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    public void pressHandle(KeyEvent event) throws IOException {
        switch (event.getCode()) {
            case W -> player1.setVDirection(VDirection.UP);
            case S -> player1.setVDirection(VDirection.DOWN);
            case A -> player1.setHDirection(HDirection.LEFT);
            case D -> player1.setHDirection(HDirection.RIGHT);
            case SPACE -> player1.getAttackTimers().restartPressed(Attack.ATTACK);
            case Z -> player1.skill1();
            case X -> player1.skill2();
            case C -> player1.skill3();
            case V -> player1.skill4();
            case UP -> player2.setVDirection(VDirection.UP);
            case DOWN -> player2.setVDirection(VDirection.DOWN);
            case LEFT -> player2.setHDirection(HDirection.LEFT);
            case RIGHT -> player2.setHDirection(HDirection.RIGHT);
            case ENTER -> player2.getAttackTimers().restartPressed(Attack.ATTACK);
            case M -> player2.skill1();
            case COMMA -> player2.skill2();
            case PERIOD -> player2.skill3();
            case SLASH -> player2.skill4();
            case ESCAPE -> switchToChoice();
        }
    }

    @FXML
    public void releaseHandle(KeyEvent event) {
        switch (event.getCode()) {
            case A -> player1.setHDirection(player1.isRight() ? HDirection.RIGHT : HDirection.NULL);
            case D -> player1.setHDirection(player1.isLeft() ? HDirection.LEFT : HDirection.NULL);
            case SPACE -> player1.getAttackTimers().stopPressed(Attack.ATTACK);
            case Z -> player1.skill1();
            case X -> player1.skill2();
            case C -> player1.skill3();
            case V -> player1.skill4();
            case Q -> player1.debug();
            case LEFT -> player2.setHDirection(player2.isRight() ? HDirection.RIGHT : HDirection.NULL);
            case RIGHT -> player2.setHDirection(player2.isLeft() ? HDirection.LEFT : HDirection.NULL);
            case ENTER -> player2.getAttackTimers().stopPressed(Attack.ATTACK);
            case M -> player2.skill1();
            case COMMA -> player2.skill2();
            case PERIOD -> player2.skill3();
            case SLASH -> player2.skill4();
            case P -> player2.debug();
        }
    }

    @FXML
    public void switchToChoice() throws IOException {
        mediaPlayer.stop();
        AssetLoader.playOtherAudio("cancel");
        ViewController.toChoiceScene();
    }
}
