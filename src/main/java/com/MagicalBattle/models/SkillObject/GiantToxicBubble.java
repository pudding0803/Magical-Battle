package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.enums.StatusName;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class GiantToxicBubble extends SkillObject {
    private static final String NAME = "giant_toxic_bubble";
    private static final ArrayList<Image> IMAGES = AssetLoader.getSkillImages(NAME);
    private static final double BASE_VELOCITY_X = 2.5;
    private static final double BASE_VELOCITY_Y = 0;

    public GiantToxicBubble(Character character) {
        super(character, IMAGES.get(0), NAME, NAME);
        statusList = new ArrayList<>(List.of(StatusName.POISONED));
        damage = character.getAttack() * 2;
        attackBoth = false;
        gravity = false;
        velocityX = BASE_VELOCITY_X * (character.isFacingLeft() ? -1 : 1);
        velocityY = BASE_VELOCITY_Y;
    }

    @Override
    public void doPerTime() {

    }
}
