package com.MagicalBattle.models.Character;

import com.MagicalBattle.models.Enums.CharacterClass;
import javafx.scene.image.ImageView;

public class Guardian extends Character {
    public Guardian(ImageView imageView, boolean isPlayer1) {
        super(imageView, CharacterClass.GUARDIAN, isPlayer1);
    }

    @Override
    public void attack() {

    }

    @Override
    public void skill1() {

    }

    @Override
    public void skill2() {

    }
}
