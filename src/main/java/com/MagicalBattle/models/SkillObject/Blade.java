package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.Character.Character;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Blade extends SkillObject {
    private static final String NAME = "blade";
    private static final ArrayList<Image> IMAGES = AssetLoader.getSkillImages(NAME);
    private static final double BASE_VELOCITY_X = 22;
    private static final double BASE_VELOCITY_Y = 0;

    public Blade(Character character) {
        super(character, IMAGES.get(0), NAME, NAME);
        statusList = new ArrayList<>();
        damage = character.getAttack();
        attackBoth = false;
        gravity = false;
        velocityX = BASE_VELOCITY_X * (character.isFacingLeft() ? -1 : 1);
        velocityY = BASE_VELOCITY_Y;
    }

    @Override
    public void doPerTime() {

    }
}
