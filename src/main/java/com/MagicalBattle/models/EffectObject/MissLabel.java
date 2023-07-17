package com.MagicalBattle.models.EffectObject;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.models.Character.Character;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MissLabel extends EffectObject {
    private final Label miss = new Label("MISS");
    private final double initialY;

    public MissLabel(Character character) {
        super(character);
        miss.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, Settings.MISS_SIZE));
        miss.setTextFill(Color.web("#00ff70"));
        miss.setAlignment(Pos.CENTER);
        miss.setPrefWidth(Settings.MISS_WIDTH);
        miss.setPrefHeight(Settings.MISS_HEIGHT);
        miss.setLayoutX(character.getX() + (character.getWidth() - miss.getPrefWidth()) / 2);
        miss.setLayoutY(character.getY() - miss.getPrefHeight());
        initialY = miss.getLayoutY();
    }

    public double getY() {
        return miss.getLayoutY();
    }

    public void setY(double value) {
        miss.setLayoutY(value);
    }

    @Override
    public Node getEffect() {
        return miss;
    }

    @Override
    public void doByTime() {
        setY(getY() - Settings.MISS_PER_DISTANCE);
    }

    @Override
    public boolean isFinished() {
        return miss.getLayoutY() - initialY <= Settings.MISS_TOTAL_DISTANCE;
    }
}
