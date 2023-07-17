package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.Enums.StatusName;

import java.util.ArrayList;
import java.util.List;

public class Fire extends SkillObject {
    public Fire(Character character) {
        super(character, "fire", "fire", "fire");
        statusList = new ArrayList<>(List.of(StatusName.BURNED));
        damage = character.getAttack();
        attackBoth = false;
        gravity = false;
        velocityX = Settings.MAGE_ATTACK_VELOCITY * (character.isFacingLeft() ? -1 : 1);
        velocityY = 0;
    }

    @Override
    public void doByTime() {

    }
}
