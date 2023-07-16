package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.Enums.Status;

import java.util.ArrayList;
import java.util.List;

public class FlashArrow extends Arrow {
    public FlashArrow(Character character) {
        super(character, "images/attack/skill/flash_arrow.png", "media/fire/flash_arrow.mp3");
        this.statusList = new ArrayList<>(List.of(Status.STUNNED));
    }
}
