package com.MagicalBattle.constants;

import static com.MagicalBattle.constants.Settings.UPDATE_TIME;

public final class Time {
    public static int ms(int time) {
        return (int) (time / UPDATE_TIME);
    }
}
