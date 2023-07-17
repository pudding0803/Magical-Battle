package com.MagicalBattle.models.Character;

import com.MagicalBattle.constants.Time;
import com.MagicalBattle.controllers.GameController;
import com.MagicalBattle.models.AttackTimers;
import com.MagicalBattle.models.Enums.Attack;
import com.MagicalBattle.models.Enums.CharacterClass;
import com.MagicalBattle.models.SkillObject.*;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Mage extends Character {
    public static final int DEFAULT_ATTACK_TIME = Time.ms(800);
    private static final int[] attackDurations = {DEFAULT_ATTACK_TIME, 0, 0, 0, 0};
    private static final int[] chargedAttackDurations = {0, 0, 0, 0, 0};

    private int attackCounter = 0;

    public Mage(ImageView imageView, boolean isPlayer1) {
        super(imageView, CharacterClass.MAGE, isPlayer1, new AttackTimers(attackDurations, chargedAttackDurations));
    }

    @Override
    public void attack() {
        attackTimers.restartAttack(Attack.ATTACK);
        SkillObject skillObject;
        switch (attackCounter) {
            case 0 -> {
                skillObject = new Ice(this);
                attackCounter += (new Random().nextInt(2) == 0 ? 1 : 2);
            }
            case 1 -> {
              skillObject = new WeakWind(this);
              attackCounter += 2;
            }
            case 2 -> {
                skillObject = new StrongWind(this);
                attackCounter++;
            }
            case 3 -> {
                skillObject = new Fire(this);
                attackCounter++;
            }
            case 4 -> {
                skillObject = new Earth(this);
                attackCounter = 0;
            }
            default -> throw new IllegalStateException("Unexpected value: " + attackCounter);
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

    @Override
    public void skill3() {

    }

    @Override
    public void skill4() {

    }
}
