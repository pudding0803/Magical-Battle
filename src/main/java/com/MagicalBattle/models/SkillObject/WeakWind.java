package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.enums.StatusName;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class WeakWind extends SkillObject {
    private static final String NAME = "weak_wind";
    private static final ArrayList<Image> IMAGES = AssetLoader.getSkillImages(NAME);
    private static final double BASE_VELOCITY_X = 14;
    private static final double BASE_VELOCITY_Y = 0;

    public WeakWind(Character character) {
        super(character, IMAGES.get(0), NAME, NAME);
        statusList = new ArrayList<>(List.of(StatusName.STUNNED));
        damage = character.getAttack() * 0.6;
        attackBoth = false;
        gravity = false;
        velocityX = BASE_VELOCITY_X * (character.isFacingLeft() ? -1 : 1);
        velocityY = BASE_VELOCITY_Y;
    }

    @Override
    public void doPerTime() {

    }
}
