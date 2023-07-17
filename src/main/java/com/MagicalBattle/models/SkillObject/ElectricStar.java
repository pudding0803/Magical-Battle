package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.enums.StatusName;

import java.util.ArrayList;
import java.util.List;

public class ElectricStar extends SkillObject {
    public ElectricStar(Character character) {
        super(character, "electric_star", "electric_star", "electric_star");
        statusList = new ArrayList<>(List.of(StatusName.DIZZY));
        damage = character.getAttack() * 0.75;
        attackBoth = false;
        gravity = false;
        velocityX = Settings.ASSASSIN_ATTACK_VELOCITY * (character.isFacingLeft() ? -1 : 1);
        velocityY = 0;
    }

    @Override
    public void doPerTime() {

    }
}
