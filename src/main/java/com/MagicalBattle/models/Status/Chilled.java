package com.MagicalBattle.models.Status;

import com.MagicalBattle.constants.Colors;
import com.MagicalBattle.constants.Time;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.Enums.StatusName;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;

public class Chilled extends Status {
    private static final int DURATION = Time.ms(3600);
    private static final double SPEED_EFFECT = 0.4;
    private static final double AGILITY_EFFECT = 2;

    public Chilled(Character character) {
        super(character, DURATION);
    }

    @Override
    protected void initialize() {
        character.getStatusTimer(StatusName.BURNED).stop();
    }

    @Override
    protected void doPerTime() {
        if (character.getStatusTimer(StatusName.HURT).isEnd()) {
            Lighting lighting = new Lighting();
            lighting.setLight(new Light.Distant(0, 45, Colors.chilledColor));
            character.setEffect(lighting);
        }
        character.setSpeed(character.getMaxSpeed() * SPEED_EFFECT);
        character.setAgility(character.getMaxAgility() - AGILITY_EFFECT);
    }
}
