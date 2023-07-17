package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.enums.StatusName;

import java.util.ArrayList;
import java.util.List;

public class Ice extends SkillObject {
    public Ice(Character character) {
        super(character, "ice", "ice", "ice");
        statusList = new ArrayList<>(List.of(StatusName.CHILLED));
        damage = character.getAttack();
        attackBoth = false;
        gravity = false;
        velocityX = Settings.MAGE_ATTACK_VELOCITY * (character.isFacingLeft() ? -1 : 1);
        velocityY = 0;
    }

    @Override
    public void doPerTime() {

    }
}
