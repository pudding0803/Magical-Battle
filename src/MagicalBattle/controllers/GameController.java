package MagicalBattle.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;


import java.util.ArrayList;
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;

import MagicalBattle.Enums.HDirection;
import MagicalBattle.career.Player;
import MagicalBattle.models.MissLabel;
import MagicalBattle.models.SkillObject;
import MagicalBattle.constants.Settings;
import MagicalBattle.Enums.VDirection;

public class GameController implements Initializable {
    @FXML
    private AnchorPane face1, face2;
    @FXML
    private ImageView imageView1, imageView2;
    @FXML
    private Pane skillPane, missPane;
    @FXML
    private ProgressBar health1, health2, magic1, magic2;

    private static final ArrayList<SkillObject> allSkillObjects = new ArrayList<>();
    private static final ArrayList<MissLabel> allMissLabel = new ArrayList<>();

    private static Player player1, player2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        health1.setProgress(0);
        health2.setProgress(0);
        magic1.setProgress(0);
        magic2.setProgress(0);
        player1 = ChoiceController.getSelected1().createPlayer(imageView1, true);
        player2 = ChoiceController.getSelected2().createPlayer(imageView2, false);
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(Settings.UPDATE_TIME), (event) -> {
            updateProgressBars();
            player1.updateEffect();
            player2.updateEffect();
            player1.getTimer().countDown();
            player2.getTimer().countDown();

            skillPane.getChildren().clear();
            for (int i = 0; i < allSkillObjects.size(); i++) {
                SkillObject skillObject = allSkillObjects.get(i);
                skillPane.getChildren().add(skillObject.getImageView());
                skillObject.setX(skillObject.getX() + skillObject.getVelocityX());
                skillObject.setX(skillObject.getX() + skillObject.getVelocityY());
                if (skillObject.getX() <= 0 || skillObject.getX() >= Settings.WIDTH || player1.isCollidedFromOther(skillObject) || player2.isCollidedFromOther(skillObject))
                    allSkillObjects.remove(i--);
            }

            missPane.getChildren().clear();
            for (int i = 0; i < allMissLabel.size(); i++) {
                MissLabel missLabel = allMissLabel.get(i);
                missPane.getChildren().add(missLabel.getLabel());
                missLabel.setY(missLabel.getY() - Settings.MISS_PER_DISTANCE);
                if (missLabel.isFinished()) allMissLabel.remove(i--);
            }

            if (player1.isUp() || player1.isDown()) player1.setVelocity();
            player1.doVerticalMotion();
            if (player1.isLeft() || player1.isRight()) player1.doHorizonMotion();
            else player1.setStand();
            if (player1.isAttacking() && player1.getTimer().isAttackTimerEnd()) player1.attack();

            if (player2.isUp() || player2.isDown()) player2.setVelocity();
            player2.doVerticalMotion();
            if (player2.isLeft() || player2.isRight()) player2.doHorizonMotion();
            else player2.setStand();
            if (player2.isAttacking() && player2.getTimer().isAttackTimerEnd()) player2.attack();

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
            (isPlayer1 ? face1 : face2).getStyleClass().add((isPlayer1 ? player1 : player2).getCareer().getName() + "0");
        } else if (rate <= 0.5) {
            (isPlayer1 ? health1 : health2).getStyleClass().add("warning");
            (isPlayer1 ? face1 : face2).getStyleClass().add((isPlayer1 ? player1 : player2).getCareer().getName() + "1");
        } else {
            (isPlayer1 ? health1 : health2).getStyleClass().add("healthy");
            (isPlayer1 ? face1 : face2).getStyleClass().add((isPlayer1 ? player1 : player2).getCareer().getName() + "2");
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

    public static Player otherPlayer(boolean isPlayer1) {
        return (isPlayer1 ? player2 : player1);
    }

    public static void newSkillObject(SkillObject skillObject) {
        allSkillObjects.add(skillObject);
    }

    public static void newMissLabel(MissLabel missLabel) {
        allMissLabel.add(missLabel);
    }

    @FXML
    public void pressHandle(KeyEvent event) throws IOException {
        switch (event.getCode()) {
            case ESCAPE -> switchToChoice();
            case W -> player1.setVDirection(VDirection.UP);
            case S -> player1.setVDirection(VDirection.DOWN);
            case A -> player1.setHDirection(HDirection.LEFT);
            case D -> player1.setHDirection(HDirection.RIGHT);
            case SPACE -> player1.setAttacking(true);

            case UP -> player2.setVDirection(VDirection.UP);
            case DOWN -> player2.setVDirection(VDirection.DOWN);
            case LEFT -> player2.setHDirection(HDirection.LEFT);
            case RIGHT -> player2.setHDirection(HDirection.RIGHT);
            case ENTER -> player2.setAttacking(true);
        }
    }

    @FXML
    public void releaseHandle(KeyEvent event) {
        switch (event.getCode()) {
            case A -> player1.setHDirection(player1.isRight() ? HDirection.RIGHT : HDirection.NULL);
            case D -> player1.setHDirection(player1.isLeft() ? HDirection.LEFT : HDirection.NULL);
            case SPACE -> player1.setAttacking(false);
            case Z -> player1.debug();
            case LEFT -> player2.setHDirection(player2.isRight() ? HDirection.RIGHT : HDirection.NULL);
            case RIGHT -> player2.setHDirection(player2.isLeft() ? HDirection.LEFT : HDirection.NULL);
            case ENTER -> player2.setAttacking(false);
            case SLASH -> player2.debug();
        }
    }

    @FXML
    public void switchToChoice() throws IOException {
        ViewController.toChoiceScene();
    }
}
