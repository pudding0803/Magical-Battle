package com.MagicalBattle.models.Character;

import com.MagicalBattle.controllers.GameController;
import com.MagicalBattle.models.Enums.CharacterClass;
import com.MagicalBattle.models.SkillObject.*;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Mage extends Character {
    public final int DEFAULT_ATTACK_TIME = 40;

    private int attackCounter = 0;

    public Mage(ImageView imageView, boolean isPlayer1) {
        super(imageView, CharacterClass.MAGE, isPlayer1);
    }

    @Override
    public void attack() {
        this.timer.setAttackTimer(DEFAULT_ATTACK_TIME);
        SkillObject skillObject;
        switch (this.attackCounter) {
            case 0 -> {
                skillObject = new Ice(this);
                this.attackCounter += (new Random().nextInt(2) == 0 ? 1 : 2);
            }
            case 1 -> {
              skillObject = new WeakWind(this);
              this.attackCounter += 2;
            }
            case 2 -> {
                skillObject = new StrongWind(this);
                this.attackCounter++;
            }
            case 3 -> {
                skillObject = new Fire(this);
                this.attackCounter++;
            }
            case 4 -> {
                skillObject = new Earth(this);
                this.attackCounter = 0;
            }
            default -> throw new IllegalStateException("Unexpected value: " + this.attackCounter);
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
