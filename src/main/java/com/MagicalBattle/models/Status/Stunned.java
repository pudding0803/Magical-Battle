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
        GameController.newEffectObject(new StunnedEffect(this.character));
    }

    @Override
    protected void doPerTime() {
        this.character.setSpeed(0);
        this.character.resetJumpCount();
    }
}
