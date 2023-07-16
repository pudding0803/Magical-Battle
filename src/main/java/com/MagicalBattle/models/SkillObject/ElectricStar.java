package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.Enums.Status;

import java.util.ArrayList;
import java.util.List;

public class ElectricStar extends SkillObject {
    public ElectricStar(Character character) {
        super(character, "electric_star", "electric_star", "electric_star");
        this.statusList = new ArrayList<>(List.of(Status.DIZZY));
        this.damage = this.character.getAttack() * 0.75;
        this.attackBoth = false;
        this.gravity = false;
        this.velocityX = Settings.ASSASSIN_ATTACK_VELOCITY * (this.character.isFacingLeft() ? -1 : 1);
        this.velocityY = 0;
    }

    @Override
    public void doByTime() {

    }
}
