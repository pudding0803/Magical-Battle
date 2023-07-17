package com.MagicalBattle.models.DisplayObject;

import com.MagicalBattle.models.Character.Character;
import javafx.scene.Node;

public abstract class DisplayObject {
    protected final Character character;
    
    public DisplayObject(Character character) {
        this.character = character;
    }

    public abstract void adjustPosition();

    public abstract Node getDisplay();

    public abstract void doByTime();

    public abstract boolean isFinished();
}
