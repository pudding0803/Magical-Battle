package com.MagicalBattle.models.Status;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.constants.Time;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.Enums.HDirection;
import com.MagicalBattle.models.Enums.StatusName;

public class KnockedBack extends Status {
    private static final int DURATION = Time.ms(600);
    private static final double DISTANCE_PER_TIME = 10;

    private HDirection from;

    public KnockedBack(Character character) {
        super(character, DURATION);
    }

    @Override
    protected void initialize() {
        this.character.getStatusTimer(StatusName.STUNNED).stop();
        this.from = (this.skillObject.getVelocityX() > 0 ? HDirection.RIGHT : HDirection.LEFT);
    }

    @Override
    protected void doPerTime() {
        double distance = (this.from == HDirection.RIGHT ? 1 : -1) * DISTANCE_PER_TIME;
        if (this.character.getX() + distance > 0 && this.character.getX() + distance < Settings.WIDTH - this.character.getWidth()) {
            this.character.setX(this.character.getX() + distance);
        }
        this.character.setSpeed(0);
        this.character.resetJumpCount();
    }
}
