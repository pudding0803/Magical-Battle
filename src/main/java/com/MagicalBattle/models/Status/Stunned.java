package com.MagicalBattle.models.Status;

import com.MagicalBattle.constants.Time;
import com.MagicalBattle.controllers.GameController;
import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.DisplayObject.EffectObject.StunnedEffect;

public class Stunned extends Status {
    private static final int DURATION = Time.ms(800);

    public Stunned(Character character) {
        super(character, DURATION);
    }

    @Override
    protected void initialize() {
        AssetLoader.playEffectAudio("stunned");
        GameController.newDisplayObject(new StunnedEffect(character));
    }

    @Override
    protected void doPerTime() {
        character.setSpeed(0);
        character.disableJump();
    }
}
