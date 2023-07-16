package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.models.Character.Character;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class Arrow extends SkillObject {
    protected Image image;

    public Arrow(Character character) {
        super(character, "images/attack/skill/arrow.png", "media/fire/arrow.mp3", "media/hit/arrow.mp3");
        this.arrowInitialize();
    }

    public Arrow(Character character, String image, String fireMedia) {
        super(character, image, fireMedia, "media/hit/arrow.mp3");
        this.arrowInitialize();
    }

    public void arrowInitialize() {
        int fixedPositionY = new Random().nextInt(61) - 30;
        int fixedVelocityX = new Random().nextInt(7) - 2;
        this.setY(this.getY() + fixedPositionY);
        this.statusList = new ArrayList<>();
        this.damage = this.character.getAttack() * 0.12;
        this.attackBoth = false;
        this.gravity = false;
        this.velocityX = (Settings.ARCHER_ATTACK_VELOCITY + fixedVelocityX) * (this.character.isFacingLeft() ? -1 : 1);
        this.velocityY = 0;
    }

    @Override
    public void doByTime() {
        this.damage += 0.48;
    }
}
