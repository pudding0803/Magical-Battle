package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.Enums.StatusName;

import java.util.ArrayList;
import java.util.List;

public class FlashArrow extends Arrow {
    public FlashArrow(Character character) {
        super(character, "flash_arrow", "flash_arrow");
        this.statusList = new ArrayList<>(List.of(StatusName.STUNNED));
    }
}
