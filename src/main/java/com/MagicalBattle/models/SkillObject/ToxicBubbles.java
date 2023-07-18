package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.constants.Time;
import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.Timer;
import com.MagicalBattle.models.enums.StatusName;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToxicBubbles extends SkillObject {
    private static final String NAME = "toxic_bubbles";
    private static final ArrayList<Image> IMAGES = AssetLoader.getSkillImages(NAME);
    private static final double BASE_VELOCITY_X = 2.5;
    private static final double BASE_VELOCITY_Y = 0;

    private static final int EXIST_TIME = Time.ms(2000);
    private final Timer timer = new Timer(EXIST_TIME);

    public ToxicBubbles(Character character) {
        super(character, IMAGES.get(new Random().nextInt(3)), NAME, NAME);
        statusList = new ArrayList<>(List.of(StatusName.POISONED));
        damage = character.getAttack() * 0.5;
        attackBoth = false;
        gravity = false;
        velocityX = BASE_VELOCITY_X * (character.isFacingLeft() ? -1 : 1);
        velocityY = BASE_VELOCITY_Y;
        int fixedPositionY = new Random().nextInt(41) - 20;
        setY(getY() + fixedPositionY);
        timer.restart();
    }

    @Override
    public void doPerTime() {
        if (timer.isEnd()) {
            finished = true;
            playHitMedia();
        }
        timer.timing();
    }
}
