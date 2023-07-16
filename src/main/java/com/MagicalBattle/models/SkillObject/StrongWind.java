package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.Enums.Status;

import java.util.ArrayList;
import java.util.List;

public class StrongWind extends SkillObject {
    public StrongWind(Character character) {
        super(character, "strong_wind", "strong_wind", "strong_wind");
        this.statusList = new ArrayList<>(List.of(Status.KNOCKED_BACK));
        this.damage = this.character.getAttack() * 1.4;
        this.attackBoth = false;
        this.gravity = false;
        this.velocityX = Settings.MAGE_ATTACK_VELOCITY * (this.character.isFacingLeft() ? -1 : 1);
        this.velocityY = 0;
    }

    @Override
    public void doByTime() {

    }
}
