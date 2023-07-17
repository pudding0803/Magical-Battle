package com.MagicalBattle.models.DisplayObject.LabelObject;

import com.MagicalBattle.models.Character.Character;
import javafx.scene.paint.Color;

public class MissLabel extends LabelObject {
    public MissLabel(Character character) {
        super(character, "MISS", Color.web("#00ff70"));
    }
}
