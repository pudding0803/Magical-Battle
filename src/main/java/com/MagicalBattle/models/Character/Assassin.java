package com.MagicalBattle.models.Character;

import com.MagicalBattle.controllers.GameController;
import com.MagicalBattle.models.Enums.CharacterClass;
import com.MagicalBattle.models.SkillObject.Blade;
import com.MagicalBattle.models.SkillObject.ElectricStar;
import com.MagicalBattle.models.SkillObject.SkillObject;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Assassin extends Character {
    public final int DEFAULT_ATTACK_TIME = 32;

    public Assassin(ImageView imageView, boolean isPlayer1) {
        super(imageView, CharacterClass.ASSASSIN, isPlayer1);
        this.maxJumpCount = 2;
    }

    @Override
    public void attack() {
        this.timer.setAttackTimer(DEFAULT_ATTACK_TIME);
        int attackIndex = new Random().nextInt(3);
        SkillObject skillObject;
        if (attackIndex == 0) {
            skillObject = new ElectricStar(this);
        } else {
            skillObject = new Blade(this);
        }
        skillObject.playFireMedia();
        GameController.newSkillObject(skillObject);
    }

    @Override
    public void skill1() {

    }

    @Override
    public void skill2() {

    }
}

