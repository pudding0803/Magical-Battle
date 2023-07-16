package com.MagicalBattle.models.Status;

import com.MagicalBattle.constants.Colors;
import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.constants.Time;
import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.Enums.StatusName;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.media.AudioClip;

public class Burned extends Status {
    private static final int DURATION = Time.ms(3000);
    private static final double DAMAGE = 8;

    public Burned(Character character) {
        super(character, DURATION);
    }

    @Override
    protected void initialize() {
        this.character.getStatusTimer(StatusName.CHILLED).stop();
    }

    @Override
    protected void doPerTime() {
       if(this.timer.getTime() % Time.ms(1000) == 0) {
            AudioClip audioClip = AssetLoader.getEffectAudio("burned");
            audioClip.setVolume(Settings.EFFECT_VOLUME);
            audioClip.play();
            this.character.setHealth(this.character.getHealth() - DAMAGE);
            this.character.getStatusTimer(StatusName.HURT).restart();
       }
       if (this.character.getStatusTimer(StatusName.HURT).isEnd()) {
            Lighting lighting = new Lighting();
            lighting.setLight(new Light.Distant(0, 45, Colors.burnedColor));
            this.character.setEffect(lighting);
       }
    }
}
