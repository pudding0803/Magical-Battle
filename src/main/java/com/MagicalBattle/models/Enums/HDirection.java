package com.MagicalBattle.models.Enums;

public enum HDirection {
    NULL(0), LEFT(-1), RIGHT(1);

    private final int value;

    HDirection(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
