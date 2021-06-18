package MagicalBattle.models.enums;

public enum VDirection {
    NULL, UP, DOWN;

    public int getValue() {
        return switch (this) {
            case NULL -> 0;
            case UP -> -1;
            case DOWN -> 1;
        };
    }
}
