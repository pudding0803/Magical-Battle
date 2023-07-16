package com.MagicalBattle.models.Character;

import com.MagicalBattle.models.Enums.CharacterClass;
import javafx.scene.image.ImageView;

public class Warrior extends Character {
    public Warrior(ImageView imageView, boolean isPlayer1) {
        super(imageView, CharacterClass.WARRIOR, isPlayer1);
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
