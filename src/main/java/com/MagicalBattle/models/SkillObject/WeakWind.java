package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.Enums.StatusName;

import java.util.ArrayList;
import java.util.List;

public class WeakWind extends SkillObject {
    public WeakWind(Character character) {
        super(character, "weak_wind", "weak_wind", "weak_wind");
        statusList = new ArrayList<>(List.of(StatusName.STUNNED));
        damage = character.getAttack() * 0.6;
        attackBoth = false;
        gravity = false;
        velocityX = Settings.MAGE_ATTACK_VELOCITY * (character.isFacingLeft() ? -1 : 1);
        velocityY = 0;
    }

    @Override
    public void doByTime() {

    }
}
