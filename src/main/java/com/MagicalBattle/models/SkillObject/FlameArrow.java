package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.Enums.StatusName;

import java.util.ArrayList;
import java.util.List;

public class FlameArrow extends Arrow {
    public FlameArrow(Character character) {
        super(character, "flame_arrow", "flame_arrow");
        statusList = new ArrayList<>(List.of(StatusName.BURNED));
    }
}
