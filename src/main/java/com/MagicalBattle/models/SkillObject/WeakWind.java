package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.Enums.Status;

import java.util.ArrayList;
import java.util.List;

public class WeakWind extends SkillObject {
    public WeakWind(Character character) {
        super(character, "images/attack/skill/weak_wind.png", "media/fire/weak_wind.mp3", "media/hit/weak_wind.mp3");
        this.statusList = new ArrayList<>(List.of(Status.STUNNED));
        this.damage = this.character.getAttack() * 0.6;
        this.attackBoth = false;
        this.gravity = false;
        this.velocityX = Settings.MAGE_ATTACK_VELOCITY * (this.character.isFacingLeft() ? -1 : 1);
        this.velocityY = 0;
    }

    @Override
    public void doByTime() {

    }
}
