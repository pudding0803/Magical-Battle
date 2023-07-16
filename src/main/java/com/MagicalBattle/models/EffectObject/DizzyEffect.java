package com.MagicalBattle.models.EffectObject;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.models.Character.Character;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class DizzyEffect extends EffectObject {
    private final Image image = new Image(Objects.requireNonNull(getClass().getResource(Settings.RESOURCE_PATH + "images/attack/effect/dizzy.png")).toExternalForm());
    private final ImageView imageView = new ImageView(image);

    public DizzyEffect(Character character) {
        super(character);
        this.imageView.setLayoutX(this.character.getX() + (this.character.getWidth() - this.getWidth()) / 2);
        this.imageView.setLayoutY(this.character.getY() - this.getHeight());
    }

    public double getWidth() {
        return this.imageView.getImage().getWidth();
    }

    public double getHeight() {
        return this.imageView.getImage().getHeight();
    }

    @Override
    public Node getEffect() {
        return this.imageView;
    }

    @Override
    public void doByTime() {
        this.imageView.setLayoutX(this.character.getX() + (this.character.getWidth() - this.getWidth()) / 2);
        this.imageView.setLayoutY(this.character.getY() - this.getHeight());
    }

    @Override
    public boolean isFinished() {
        return this.character.getTimer().isDizzyTimerEnd();
    }
}
