package com.MagicalBattle.models.Character;

import com.MagicalBattle.constants.Time;
import com.MagicalBattle.controllers.GameController;
import com.MagicalBattle.models.SkillObject.Blade;
import com.MagicalBattle.models.SkillObject.SkillObject;
import com.MagicalBattle.models.SkillTimers;
import com.MagicalBattle.models.enums.CharacterClass;
import com.MagicalBattle.models.enums.SkillType;
import javafx.scene.image.ImageView;

public class Assassin extends Character {
    private static final int DEFAULT_ATTACK_TIME = Time.ms(600);
    private static final int[] skillDurations = {DEFAULT_ATTACK_TIME, 0, 0, 0, 0};
    private static final int[] chargedSkillDurations = {0, 0, 0, 0, 0};

    public Assassin(ImageView imageView, boolean isPlayer1) {
        super(imageView, CharacterClass.ASSASSIN, isPlayer1, new SkillTimers(skillDurations, chargedSkillDurations));
        maxJumpCount = 2;
    }

    @Override
    public void attack() {
        skillTimers.restartSkill(SkillType.ATTACK);
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

