package com.MagicalBattle.models.Character;

import com.MagicalBattle.controllers.GameController;
import com.MagicalBattle.models.Enums.CharacterClass;
import com.MagicalBattle.models.SkillObject.Arrow;
import com.MagicalBattle.models.SkillObject.FlameArrow;
import com.MagicalBattle.models.SkillObject.FlashArrow;
import com.MagicalBattle.models.SkillObject.SkillObject;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Archer extends Character {
    public final int DEFAULT_ATTACK_TIME = 40;

    public Archer(ImageView imageView, boolean isPlayer1) {
        super(imageView, CharacterClass.ARCHER, isPlayer1);
    }

    @Override
    public void attack() {
        this.timer.setAttackTimer(DEFAULT_ATTACK_TIME);
        int arrowsNumber = new Random().nextInt(4) + 3;
        for (int i = 0; i < arrowsNumber; i++) {
            int attackIndex = new Random().nextInt(8);
            SkillObject skillObject;
            if (attackIndex == 0) {
                skillObject = new FlameArrow(this);
            } else if (attackIndex == 1) {
                skillObject = new FlashArrow(this);
            } else {
                skillObject = new Arrow(this);
            }
            skillObject.playFireMedia();
            GameController.newSkillObject(skillObject);
        }
    }

    @Override
    public void skill1() {

    }

    @Override
    public void skill2() {

    }
}
