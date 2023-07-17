package com.MagicalBattle.models.Character;

import com.MagicalBattle.constants.Time;
import com.MagicalBattle.models.SkillTimers;
import com.MagicalBattle.models.enums.CharacterClass;
import javafx.scene.image.ImageView;

public class Guardian extends Character {
    private static final int DEFAULT_ATTACK_TIME = Time.ms(-1);
    private static final int[] skillDurations = {DEFAULT_ATTACK_TIME, 0, 0, 0, 0};
    private static final int[] chargedSkillDurations = {0, 0, 0, 0, 0};

    public Guardian(ImageView imageView, boolean isPlayer1) {
        super(imageView, CharacterClass.GUARDIAN, isPlayer1, new SkillTimers(skillDurations, chargedSkillDurations));
    }

    @Override
    public void attack() {

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
