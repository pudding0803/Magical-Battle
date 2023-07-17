package com.MagicalBattle.models.DisplayObject.EffectObject;

import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.enums.StatusName;
import javafx.scene.Node;

public class DizzyEffect extends EffectObject {
    public DizzyEffect(Character character) {
        super(character, "dizzy");
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
