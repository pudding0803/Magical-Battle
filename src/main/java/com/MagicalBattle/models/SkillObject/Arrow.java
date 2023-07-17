package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.models.Character.Character;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class Arrow extends SkillObject {
    protected Image image;

    public Arrow(Character character) {
        super(character, "arrow", "arrow", "arrow");
        arrowInitialize();
    }

    public Arrow(Character character, String image, String fireMedia) {
        super(character, image, fireMedia, "arrow");
        arrowInitialize();
    }

    public void arrowInitialize() {
        int fixedPositionY = new Random().nextInt(71) - 30;
        int fixedVelocityX = new Random().nextInt(7) - 2;
        setY(getY() + fixedPositionY);
        statusList = new ArrayList<>();
        damage = character.getAttack() * 0.11;
        attackBoth = false;
        gravity = false;
        velocityX = (Settings.ARCHER_ATTACK_VELOCITY + fixedVelocityX) * (character.isFacingLeft() ? -1 : 1);
        velocityY = 0;
    }

    @Override
    public void doByTime() {
        damage += 0.39;
    }
}
