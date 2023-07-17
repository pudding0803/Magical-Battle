package com.MagicalBattle.models.Enums;

import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.Status.*;

public enum StatusName {
    HURT, CHILLED, BURNED, STUNNED, DIZZY, KNOCKED_BACK_HARD, KNOCKED_BACK_SOFT, KNOCKED_UP;

    public Status createStatus(Character character) {
        return switch (this) {
            case HURT -> new Hurt(character);
            case CHILLED -> new Chilled(character);
            case BURNED -> new Burned(character);
            case STUNNED -> new Stunned(character);
            case DIZZY -> new Dizzy(character);
            case KNOCKED_BACK_HARD -> new KnockedBack(character, true);
            case KNOCKED_BACK_SOFT -> new KnockedBack(character, false);
            case KNOCKED_UP -> new KnockedUp(character);
        };
    }
}
