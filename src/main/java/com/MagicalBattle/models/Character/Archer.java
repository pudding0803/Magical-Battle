package com.MagicalBattle.models.Character;

import com.MagicalBattle.constants.Time;
import com.MagicalBattle.controllers.GameController;
import com.MagicalBattle.models.SkillTimers;
import com.MagicalBattle.models.enums.SkillType;
import com.MagicalBattle.models.enums.CharacterClass;
import com.MagicalBattle.models.SkillObject.Arrow;
import com.MagicalBattle.models.SkillObject.SkillObject;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Archer extends Character {
    public static final int DEFAULT_ATTACK_TIME = Time.ms(800);
    private static final int[] skillDurations = {DEFAULT_ATTACK_TIME, 0, 0, 0, 0};
    private static final int[] chargedSkillDurations = {0, 0, 0, 0, 0};

    public Archer(ImageView imageView, boolean isPlayer1) {
        super(imageView, CharacterClass.ARCHER, isPlayer1, new SkillTimers(skillDurations, chargedSkillDurations));
    }

    @Override
    public void attack() {
        skillTimers.restartSkill(SkillType.ATTACK);
        int arrowsNumber = new Random().nextInt(3) + 5;
        for (int i = 0; i < arrowsNumber; i++) {
            SkillObject skillObject = new Arrow(this);
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

    @Override
    public void skill3() {

    }

    @Override
    public void skill4() {

    }
}
