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
        return time;
    }

    public void restart() {
        time = startTime;
    }

    public void stop() {
        time = END_TIME;
    }

    public void timing() {
        if (active && time > END_TIME) time--;
    }

    public boolean isEnd() {
        return time == END_TIME;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }
}
