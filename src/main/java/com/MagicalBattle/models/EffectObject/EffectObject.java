package com.MagicalBattle.models.EffectObject;

import com.MagicalBattle.models.Character.Character;
import javafx.scene.Node;

public abstract class EffectObject {
    protected Character character;

    public EffectObject(Character character) {
        this.character = character;
    }

    public abstract Node getEffect();
    public abstract void doByTime();
    public abstract boolean isFinished();
}
