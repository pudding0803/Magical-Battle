package com.MagicalBattle.models.Status;

import com.MagicalBattle.constants.Time;
import com.MagicalBattle.models.Character.Character;

public class KnockedUp extends Status {
    private static final int DURATION = Time.ms(60000);
    private static final double KNOCK_UP_VELOCITY = 14;

    public KnockedUp(Character character) {
        super(character, DURATION);
    }

    @Override
    protected void initialize() {
        character.setSpeed(0);
        character.disableJump();
        character.setVelocity(-KNOCK_UP_VELOCITY);
    }

    @Override
    protected void doPerTime() {
        character.setSpeed(0);
        character.disableJump();
        if (character.isOnGround()) {
            timer.stop();
        }
    }
}
