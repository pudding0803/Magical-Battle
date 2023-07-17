package com.MagicalBattle.models.Character;

import com.MagicalBattle.constants.Time;
import com.MagicalBattle.controllers.GameController;
import com.MagicalBattle.models.SkillTimers;
import com.MagicalBattle.models.Enums.SkillType;
import com.MagicalBattle.models.Enums.CharacterClass;
import com.MagicalBattle.models.SkillObject.*;
import javafx.scene.image.ImageView;

public class Mage extends Character {
    public static final int DEFAULT_ATTACK_TIME = Time.ms(800);
    private static final int[] skillDurations = {DEFAULT_ATTACK_TIME, DEFAULT_ATTACK_TIME, DEFAULT_ATTACK_TIME, DEFAULT_ATTACK_TIME, DEFAULT_ATTACK_TIME};
    private static final int[] chargedSkillDurations = {0, 0, 0, 0, 0};

    public Mage(ImageView imageView, boolean isPlayer1) {
        super(imageView, CharacterClass.MAGE, isPlayer1, new SkillTimers(skillDurations, chargedSkillDurations));
    }

    @Override
    public void attack() {

    }

    @Override
    public void skill1() {
        skillTimers.restartSkill(SkillType.SKILL1);
        SkillObject skillObject = new Ice(this);
        skillObject.playFireMedia();
        GameController.newSkillObject(skillObject);
    }

    @Override
    public void skill2() {
        skillTimers.restartSkill(SkillType.SKILL2);
        SkillObject skillObject = new WeakWind(this);
        skillObject.playFireMedia();
        GameController.newSkillObject(skillObject);
    }

    @Override
    public void skill3() {
        skillTimers.restartSkill(SkillType.SKILL3);
        SkillObject skillObject = new Fire(this);
        skillObject.playFireMedia();
        GameController.newSkillObject(skillObject);
    }

    @Override
    public void skill4() {
        skillTimers.restartSkill(SkillType.SKILL4);
        SkillObject skillObject = new Earth(this);
        skillObject.playFireMedia();
        GameController.newSkillObject(skillObject);
    }
}
