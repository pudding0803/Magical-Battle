package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.Character.Character;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class Arrow extends SkillObject {
    private static final String NAME = "arrow";
    private static final ArrayList<Image> IMAGES = AssetLoader.getSkillImages(NAME);
    private static final double BASE_VELOCITY_X = 12;
    private static final double BASE_VELOCITY_Y = 0;

    public Arrow(Character character) {
        super(character, IMAGES.get(0), NAME, NAME);
        arrowInitialize();
    }

    public Arrow(Character character, Image image, String fireMedia) {
        super(character, image, fireMedia, NAME);
        arrowInitialize();
    }

    public void arrowInitialize() {
        int fixedPositionY = new Random().nextInt(61) - 35;
        int fixedVelocityX = new Random().nextInt(7) - 2;
        setY(getY() + fixedPositionY);
        statusList = new ArrayList<>();
        damage = character.getAttack() * 0.11;
        attackBoth = false;
        gravity = false;
        velocityX = (BASE_VELOCITY_X + fixedVelocityX) * (character.isFacingLeft() ? -1 : 1);
        velocityY = BASE_VELOCITY_Y;
    }

    @Override
    public void doPerTime() {
        damage += 0.29;
    }
}
