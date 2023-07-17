package com.MagicalBattle.models.EffectObject;

import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.Enums.StatusName;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StunnedEffect extends EffectObject {
    private final Image image = AssetLoader.getEffectImage("stunned");
    private final ImageView imageView = new ImageView(image);

    public StunnedEffect(Character character) {
        super(character);
        imageView.setLayoutX(character.getX() + (character.getWidth() - getWidth()) / 2);
        imageView.setLayoutY(character.getY() + character.getHeight() - getHeight());
    }

    public double getWidth() {
        return imageView.getImage().getWidth();
    }

    public double getHeight() {
        return imageView.getImage().getHeight();
    }

    @Override
    public Node getEffect() {
        return imageView;
    }

    @Override
    public void doByTime() {
        imageView.setLayoutX(character.getX() + (character.getWidth() - getWidth()) / 2);
        imageView.setLayoutY(character.getY() + character.getHeight() - getHeight());
    }

    @Override
    public boolean isFinished() {
        return character.getStatusTimer(StatusName.STUNNED).isEnd();
    }
}
