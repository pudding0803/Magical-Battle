package com.MagicalBattle.models.EffectObject;

import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.Character.Character;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DizzyEffect extends EffectObject {
    private final Image image = AssetLoader.getEffectImage("dizzy");
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
