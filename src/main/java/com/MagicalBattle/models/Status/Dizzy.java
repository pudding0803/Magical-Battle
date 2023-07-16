package com.MagicalBattle.models.Status;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.constants.Time;
import com.MagicalBattle.controllers.GameController;
import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.EffectObject.DizzyEffect;
import javafx.scene.media.AudioClip;

public class Dizzy extends Status {
    private static final int DURATION = Time.ms(1200);

    public Dizzy(Character character) {
        super(character, DURATION);
    }

    @Override
    protected void initialize() {
        AudioClip audioClip = AssetLoader.getEffectAudio("dizzy");
        audioClip.setVolume(Settings.EFFECT_VOLUME);
        audioClip.play();
        GameController.newEffectObject(new DizzyEffect(this.character));
    }

    @Override
    protected void doPerTime() {
        this.character.setSpeed(0);
    }
}
