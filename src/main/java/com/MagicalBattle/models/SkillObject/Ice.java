package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.Enums.Status;

import java.util.ArrayList;
import java.util.List;

public class Ice extends SkillObject {
    public Ice(Character character) {
        super(character, "images/attack/skill/ice.png", "media/fire/ice.mp3", "media/hit/ice.mp3");
        this.statusList = new ArrayList<>(List.of(Status.FROZEN));
        this.damage = this.character.getAttack();
        this.attackBoth = false;
        this.gravity = false;
        this.velocityX = Settings.MAGE_ATTACK_VELOCITY * (this.character.isFacingLeft() ? -1 : 1);
        this.velocityY = 0;
    }

    @Override
    public void doByTime() {

    }
}
