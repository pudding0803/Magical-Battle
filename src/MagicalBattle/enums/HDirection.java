package MagicalBattle.enums;

public enum HDirection {
    NULL, LEFT, RIGHT;

    public int getValue() {
        return switch (this) {
            case NULL -> 0;
            case LEFT -> -1;
            case RIGHT -> 1;
        };
    }

}
