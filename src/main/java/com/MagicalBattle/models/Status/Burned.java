package com.MagicalBattle.models.Status;

import com.MagicalBattle.constants.Colors;
import com.MagicalBattle.constants.Time;
import com.MagicalBattle.controllers.GameController;
import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.DisplayObject.LabelObject.DamageLabel;
import com.MagicalBattle.models.enums.StatusName;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;

public class Burned extends Status {
    private static final int DURATION = Time.ms(3000);
    private static final double DAMAGE = 8;

    public Burned(Character character) {
        super(character, DURATION);
    }

    @Override
    protected void initialize() {
        character.getStatusTimer(StatusName.CHILLED).stop();
    }

    @Override
    protected void doPerTime() {
        if (timer.getTime() % Time.ms(1000) == 0) {
            AssetLoader.playEffectAudio("burned");
            character.setHealth(character.getHealth() - DAMAGE);
            character.getStatusTimer(StatusName.HURT).restart();
            GameController.newDisplayObject(new DamageLabel(character, DAMAGE, Colors.BURNED));
        }
        if (character.getStatusTimer(StatusName.HURT).isEnd()) {
            Lighting lighting = new Lighting();
            lighting.setLight(new Light.Distant(0, 45, Colors.BURNED));
            character.setEffect(lighting);
        }
    }
}
