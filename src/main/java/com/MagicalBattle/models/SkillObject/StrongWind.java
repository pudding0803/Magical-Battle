package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.Enums.StatusName;

import java.util.ArrayList;
import java.util.List;

public class StrongWind extends SkillObject {
    public StrongWind(Character character) {
        super(character, "strong_wind", "strong_wind", "strong_wind");
        statusList = new ArrayList<>(List.of(StatusName.KNOCKED_BACK_SOFT));
        damage = character.getAttack() * 1.4;
        attackBoth = false;
        gravity = false;
        velocityX = Settings.MAGE_ATTACK_VELOCITY * (character.isFacingLeft() ? -1 : 1);
        velocityY = 0;
    }

    @Override
    public void doByTime() {

    }
}
