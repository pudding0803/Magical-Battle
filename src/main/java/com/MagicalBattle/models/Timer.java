package com.MagicalBattle.models;

public class Timer {
    public static final int INFINITE_TIME = 60000;
    private static final int END_TIME = -1;
    
    private int startTime;
    private int time = END_TIME;
    private boolean active = true;

    public Timer(int startTime) {
        this.startTime = startTime;
    }

    public int getTime() {
        return this.time;
    }

    public void restart() {
        this.time = startTime;
    }

    public void stop() {
        this.time = END_TIME;
    }

    public void timing() {
        if (active && this.time > END_TIME) this.time--;
    }

    public boolean isEnd() {
        return this.time == END_TIME;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public boolean isActive() {
        return this.active;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }
}
