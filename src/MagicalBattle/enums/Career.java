package MagicalBattle.enums;

import javafx.scene.image.ImageView;
import MagicalBattle.career.Player;
import MagicalBattle.career.*;

public enum Career {
    NULL, WARRIOR, GUARDIAN, MAGE, ARCHER, ASSASSIN, ALCHEMIST;

    public static Career getCareer(int value) {
        return switch (value) {
            case 0 -> NULL;
            case 1 -> WARRIOR;
            case 2 -> GUARDIAN;
            case 3 -> MAGE;
            case 4 -> ARCHER;
            case 5 -> ASSASSIN;
            case 6 -> ALCHEMIST;
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }

    public String getName() {
        return switch (this) {
            case NULL -> "NULL";
            case WARRIOR -> "Warrior";
            case GUARDIAN -> "Guardian";
            case MAGE -> "Mage";
            case ARCHER -> "Archer";
            case ASSASSIN -> "Assassin";
            case ALCHEMIST -> "Alchemist";
        };
    }

    public Player createPlayer(ImageView imageView, boolean isPlayer1) {
        return switch (this) {
            case NULL -> null;
            case WARRIOR -> new Warrior(imageView, isPlayer1);
            case GUARDIAN -> new Guardian(imageView, isPlayer1);
            case MAGE -> new Mage(imageView, isPlayer1);
            case ARCHER -> new Archer(imageView, isPlayer1);
            case ASSASSIN -> new Assassin(imageView, isPlayer1);
            case ALCHEMIST -> new Alchemist(imageView, isPlayer1);
        };
    }
}
