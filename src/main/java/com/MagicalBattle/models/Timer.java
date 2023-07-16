package com.MagicalBattle.models;

import com.MagicalBattle.constants.Settings;

public class Timer {
    private int attackTimer;
    private int hurtTimer;
    private int frozenTimer;
    private int burnedTimer;
    private int stunnedTimer;
    private int dizzyTimer;
    private int knockBackTimer;

    public Timer() {
        this.zero();
    }

    public void countDown() {
        if (this.attackTimer > Settings.END_TIME) this.attackTimer--;
        if (this.hurtTimer > Settings.END_TIME) this.hurtTimer--;
        if (this.frozenTimer > Settings.END_TIME) this.frozenTimer--;
        if (this.burnedTimer > Settings.END_TIME) this.burnedTimer--;
        if (this.stunnedTimer > Settings.END_TIME) this.stunnedTimer--;
        if (this.dizzyTimer > Settings.END_TIME) this.dizzyTimer--;
        if (this.knockBackTimer > Settings.END_TIME) this.knockBackTimer--;
    }

    public void zero() {
        this.attackTimer = Settings.END_TIME;
        this.hurtTimer = Settings.END_TIME;
        this.frozenTimer = Settings.END_TIME;
        this.burnedTimer = Settings.END_TIME;
        this.stunnedTimer = Settings.END_TIME;
        this.dizzyTimer = Settings.END_TIME;
        this.knockBackTimer = Settings.END_TIME;
    }

    public void setAttackTimer(int time) {
        this.attackTimer = time;
    }

    public void setHurtTimer(int time) {
        this.hurtTimer = time;
    }

    public void setFrozenTimer(int time) {
        this.frozenTimer = time;
    }

    public void setBurnedTimer(int time) {
        this.burnedTimer = time;
    }

    public void setStunnedTimer(int time) {
        this.stunnedTimer = time;
    }

    public void setDizzyTimer(int time) {
        this.dizzyTimer = time;
    }

    public void setKnockBackTimer(int time) {
        this.knockBackTimer = time;
    }

    public int getBurnedTimer() {
        return this.burnedTimer;
    }

    public boolean isAttackTimerEnd() {
        return this.attackTimer == Settings.END_TIME;
    }

    public boolean isHurtTimerEnd() {
        return this.hurtTimer == Settings.END_TIME;
    }

    public boolean isFrozenTimerEnd() {
        return this.frozenTimer == Settings.END_TIME;
    }

    public boolean isBurnedTimerEnd() {
        return this.burnedTimer == Settings.END_TIME;
    }

    public boolean isStunnedTimerEnd() {
        return this.stunnedTimer == Settings.END_TIME;
    }

    public boolean isDizzyTimerEnd() {
        return this.dizzyTimer == Settings.END_TIME;
    }

    public boolean isKnockBackTimerEnd() {
        return this.knockBackTimer == Settings.END_TIME;
    }
}
