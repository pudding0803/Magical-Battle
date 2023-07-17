package com.MagicalBattle.models.Status;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.constants.Time;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.Enums.HDirection;
import com.MagicalBattle.models.Enums.StatusName;

public class KnockedBack extends Status {
    private static final int HARD_DURATION = Time.ms(600);
    private static final int SOFT_DURATION = Time.ms(1200);
    private static final double HARD_DISTANCE_PER_TIME = 10;
    private static final double SOFT_DISTANCE_PER_TIME = 8;

    private final boolean hard;
    private final double distancePerTime;
    private HDirection from;

    public KnockedBack(Character character, boolean hard) {
        super(character, hard ? HARD_DURATION : SOFT_DURATION);
        this.hard = hard;
        this.distancePerTime = hard ? HARD_DISTANCE_PER_TIME : SOFT_DISTANCE_PER_TIME;
    }

    @Override
    protected void initialize() {
        this.character.getStatusTimer(StatusName.STUNNED).stop();
        this.from = (this.skillObject.getVelocityX() > 0 ? HDirection.RIGHT : HDirection.LEFT);
    }

    @Override
    protected void doPerTime() {
        double distance = (this.from == HDirection.RIGHT ? 1 : -1) * distancePerTime;
        if (this.character.getX() + distance > 0 && this.character.getX() + distance < Settings.WIDTH - this.character.getWidth()) {
            this.character.setX(this.character.getX() + distance);
        }
        if (this.hard) {
            this.character.setSpeed(0);
            this.character.resetJumpCount();
        }
    }
}
