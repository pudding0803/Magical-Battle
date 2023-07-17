package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.models.Character.Character;

import java.util.ArrayList;

public class Blade extends SkillObject {
    public Blade(Character character) {
        super(character, "blade", "blade", "blade");
        statusList = new ArrayList<>();
        damage = character.getAttack();
        attackBoth = false;
        gravity = false;
        velocityX = Settings.ASSASSIN_ATTACK_VELOCITY * (character.isFacingLeft() ? -1 : 1);
        velocityY = 0;
    }

    @Override
    public void doPerTime() {

    }
}
