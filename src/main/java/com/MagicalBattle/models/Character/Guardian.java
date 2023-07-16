package com.MagicalBattle.models.Character;

import com.MagicalBattle.constants.Time;
import com.MagicalBattle.models.AttackTimers;
import com.MagicalBattle.models.Enums.CharacterClass;
import javafx.scene.image.ImageView;

public class Guardian extends Character {
    private static final int DEFAULT_ATTACK_TIME = Time.ms(-1);
    private static final int[] attackDurations = {DEFAULT_ATTACK_TIME, 0, 0, 0, 0};
    private static final int[] chargedAttackDurations = {0, 0, 0, 0, 0};

    public Guardian(ImageView imageView, boolean isPlayer1) {
        super(imageView, CharacterClass.GUARDIAN, isPlayer1, new AttackTimers(attackDurations, chargedAttackDurations));
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
