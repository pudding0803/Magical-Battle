package com.MagicalBattle.models.DisplayObject.LabelObject;

import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.DisplayObject.DisplayObject;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public abstract class LabelObject extends DisplayObject {
    private static final double TOTAL_DISTANCE = -60;
    private static final double DISTANCE_PER_TIME = 2;
    private static final double SIZE = 20;
    private static final Text TEXT = new Text();

    static {
        TEXT.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, SIZE));
        TEXT.setStroke(Color.WHITE);
        TEXT.setStrokeWidth(1.5);
    }

    private final Label label = new Label();
    private final double initialY;

    public LabelObject(Character character, String text, Color color) {
        super(character);
        TEXT.setText(text);
        TEXT.setFill(color);
        label.setGraphic(TEXT);
        label.setAlignment(Pos.CENTER);
        label.setLayoutX(character.getX() + (character.getWidth() - label.getPrefWidth()) / 2);
        label.setLayoutY(character.getY() - label.getPrefHeight());
        initialY = label.getLayoutY();
    }

    @Override
    public void adjustPosition() {
        label.setLayoutY(label.getLayoutY() - DISTANCE_PER_TIME);
    }

    @Override
    public Node getDisplay() {
        return label;
    }

    @Override
    public void doByTime() {
        adjustPosition();
    }

    @Override
    public boolean isFinished() {
        return label.getLayoutY() - initialY <= TOTAL_DISTANCE;
    }
}
