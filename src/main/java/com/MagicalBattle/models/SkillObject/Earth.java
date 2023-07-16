package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.Enums.Status;

import java.util.ArrayList;
import java.util.List;

public class Earth extends SkillObject {
    public Earth(Character character) {
        super(character,"images/attack/skill/earth.png","media/fire/earth.mp3", "media/hit/earth.mp3");
        this.statusList = new ArrayList<>(List.of(Status.KNOCKED_UP));
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
