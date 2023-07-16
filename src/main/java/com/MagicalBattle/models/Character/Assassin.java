package com.MagicalBattle.models.Character;

import com.MagicalBattle.constants.Time;
import com.MagicalBattle.controllers.GameController;
import com.MagicalBattle.models.AttackTimers;
import com.MagicalBattle.models.Enums.Attack;
import com.MagicalBattle.models.Enums.CharacterClass;
import com.MagicalBattle.models.SkillObject.Blade;
import com.MagicalBattle.models.SkillObject.SkillObject;
import javafx.scene.image.ImageView;

public class Assassin extends Character {
    private static final int DEFAULT_ATTACK_TIME = Time.ms(600);
    private static final int[] attackDurations = {DEFAULT_ATTACK_TIME, 0, 0, 0, 0};
    private static final int[] chargedAttackDurations = {0, 0, 0, 0, 0};

    public Assassin(ImageView imageView, boolean isPlayer1) {
        super(imageView, CharacterClass.ASSASSIN, isPlayer1, new AttackTimers(attackDurations, chargedAttackDurations));
        this.maxJumpCount = 2;
    }

    @Override
    public void attack() {
        this.attackTimers.restartAttack(Attack.ATTACK);
        SkillObject skillObject = new Blade(this);
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

