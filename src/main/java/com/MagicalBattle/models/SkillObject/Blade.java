package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.models.Character.Character;

import java.util.ArrayList;

public class Blade extends SkillObject {
    public Blade(Character character) {
        super(character, "shuriken", "shuriken", "shuriken");
        this.statusList = new ArrayList<>();
        this.damage = this.character.getAttack();
        this.attackBoth = false;
        this.gravity = false;
        this.velocityX = Settings.ASSASSIN_ATTACK_VELOCITY * (this.character.isFacingLeft() ? -1 : 1);
        this.velocityY = 0;
    }

    @Override
    public void doByTime() {

    }
}
