package MagicalBattle.constants;

public final class Settings {
    public static final double WIDTH = 900;
    public static final double HEIGHT = 600;
    public static final double BGM_VOLUME = 0.5;
    public static final double EFFECT_VOLUME = 0.7;

    public static final int MOVE_IMAGES_COUNT = 4;
    public static final int CHOOSE_IMAGES_COUNT = 3;

    public static final double PLAYER_SIZE_RATE = (double) 54 / 48;
    public static final double STEP = 5;

    public static final double WAIT_TIME = 260;
    public static final double UPDATE_TIME = 20;

    public static final double GROUND_HEIGHT = 560;
    public static final double INITIAL_VELOCITY = 8;
    public static final double BONUS_VELOCITY = 3;
    public static final double GRAVITY = 0.44;

    public static final double MAGE_ATTACK_VELOCITY = 14;
    public static final double ARCHER_ATTACK_VELOCITY = 16;
    public static final double ASSASSIN_ATTACK_VELOCITY = 22;

    public static final double MISS_TOTAL_DISTANCE = -60;
    public static final double MISS_PER_DISTANCE = 2;
    public static final double MISS_SIZE = 20;
    public static final double MISS_WIDTH = 57;
    public static final double MISS_HEIGHT = 28;

    public static final double FROZEN_SPEED_EFFECT = 0.4;
    public static final double FROZEN_AGILITY_EFFECT = 2;
    public static final double BURNED_DAMAGE = 8;
    public static final double KNOCK_BACK_PER_DISTANCE = 10;
    public static final double KNOCK_UP_VELOCITY = 12;

    public static final int END_TIME = -1;
    // 20ms * ?
    // 50 -> 1s
    // 100 -> 2s
    public static final int MAGE_ATTACK_TIME = 40;
    public static final int ARCHER_ATTACK_TIME = 40;
    public static final int ASSASSIN_ATTACK_TIME = 32;
    public static final int HURT_TIME = 6;
    public static final int FROZEN_TIME = 180;
    public static final int BURNED_TIME = 150;
    public static final int STUNNED_TIME = 60;
    public static final int KNOCK_BACK_TIME = STUNNED_TIME / 2;
    public static final int DIZZY_TIME = 50;
}
