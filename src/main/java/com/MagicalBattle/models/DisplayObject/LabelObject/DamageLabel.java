package com.MagicalBattle.models.DisplayObject.LabelObject;

import com.MagicalBattle.models.Character.Character;
import javafx.scene.paint.Color;

public class DamageLabel extends LabelObject {
    public DamageLabel(Character character, double damage) {
        super(character, String.valueOf(Math.round(damage)), Color.RED);
    }
}
