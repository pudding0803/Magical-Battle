package com.MagicalBattle.models.Status;

import com.MagicalBattle.constants.Time;
import com.MagicalBattle.controllers.GameController;
import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.EffectObject.StunnedEffect;

public class Stunned extends Status {
    private static final int DURATION = Time.ms(1600);

    public Stunned(Character character) {
        super(character, DURATION);
    }

    @Override
    protected void initialize() {
        AssetLoader.playEffectAudio("stunned");
        GameController.newEffectObject(new StunnedEffect(character));
    }

    @Override
    protected void doPerTime() {
        character.setSpeed(0);
        character.resetJumpCount();
    }
}
