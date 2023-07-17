package com.MagicalBattle.models.Status;

import com.MagicalBattle.constants.Time;
import com.MagicalBattle.controllers.GameController;
import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.DisplayObject.EffectObject.DizzyEffect;

public class Dizzy extends Status {
    private static final int DURATION = Time.ms(1200);

    public Dizzy(Character character) {
        super(character, DURATION);
    }

    @Override
    protected void initialize() {
        AssetLoader.playEffectAudio("dizzy");
        GameController.newDisplayObject(new DizzyEffect(character));
    }

    @Override
    protected void doPerTime() {
        character.setSpeed(0);
    }
}
