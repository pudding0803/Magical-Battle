package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.enums.StatusName;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class ElectricStar extends SkillObject {
    private static final String NAME = "electric_star";
    private static final ArrayList<Image> IMAGES = AssetLoader.getSkillImages(NAME);
    private static final double BASE_VELOCITY_X = 22;
    private static final double BASE_VELOCITY_Y = 0;

    public ElectricStar(Character character) {
        super(character, IMAGES.get(0), NAME, NAME);
        statusList = new ArrayList<>(List.of(StatusName.DIZZY));
        damage = character.getAttack() * 0.75;
        attackBoth = false;
        gravity = false;
        velocityX = BASE_VELOCITY_X * (character.isFacingLeft() ? -1 : 1);
        velocityY = BASE_VELOCITY_Y;
    }

    @Override
    public void doPerTime() {

    }
}
