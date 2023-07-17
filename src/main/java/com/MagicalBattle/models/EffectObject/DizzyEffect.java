package com.MagicalBattle.models.EffectObject;

import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.Enums.StatusName;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DizzyEffect extends EffectObject {
    private final Image image = AssetLoader.getEffectImage("dizzy");
    private final ImageView imageView = new ImageView(image);

    public DizzyEffect(Character character) {
        super(character);
        imageView.setLayoutX(character.getX() + (character.getWidth() - getWidth()) / 2);
        imageView.setLayoutY(character.getY() - getHeight());
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
        imageView.setLayoutY(character.getY() - getHeight());
    }

    @Override
    public boolean isFinished() {
        return character.getStatusTimer(StatusName.DIZZY).isEnd();
    }
}
