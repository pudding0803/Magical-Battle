package com.MagicalBattle.models.DisplayObject.EffectObject;

import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.enums.StatusName;
import javafx.scene.Node;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class DizzyEffect extends EffectObject {
    private static final String NAME = "dizzy";
    private static final ArrayList<Image> IMAGES = AssetLoader.getEffectImages(NAME);

    public DizzyEffect(Character character) {
        super(character, IMAGES.get(0));
    }

    public double getWidth() {
        return imageView.getImage().getWidth();
    }

    public double getHeight() {
        return imageView.getImage().getHeight();
    }

    @Override
    public void adjustPosition() {
        imageView.setLayoutX(character.getX() + (character.getWidth() - getWidth()) / 2);
        imageView.setLayoutY(character.getY() - getHeight());
    }

    @Override
    public Node getDisplay() {
        return imageView;
    }

    @Override
    public void doByTime() {
        adjustPosition();
    }

    @Override
    public boolean isFinished() {
        return character.getStatusTimer(StatusName.DIZZY).isEnd();
    }
}
