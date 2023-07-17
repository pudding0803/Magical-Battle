package com.MagicalBattle.models.Status;

import com.MagicalBattle.constants.Time;
import com.MagicalBattle.models.Character.Character;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;

public class Hurt extends Status {
    private static final int DURATION = Time.ms(120);

    public Hurt(Character character) {
        super(character, DURATION);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void doPerTime() {
        Lighting lighting = new Lighting();
        lighting.setLight(new Light.Distant(0, 45, Color.RED));
        character.setEffect(lighting);
    }
}
