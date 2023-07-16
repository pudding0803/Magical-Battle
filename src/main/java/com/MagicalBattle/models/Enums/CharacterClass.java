package com.MagicalBattle.models.Enums;

import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.Character.*;
import javafx.scene.image.ImageView;

import java.util.HashMap;

public enum CharacterClass {
    NULL(-1, "NULL"),
    WARRIOR(0, "Warrior"),
    GUARDIAN(1, "Guardian"),
    MAGE(2, "Mage"),
    ARCHER(3, "Archer"),
    ASSASSIN(4, "Assassin"),
    ALCHEMIST(5, "Alchemist");

    private final int value;
    private final String name;
    private static final HashMap<Integer, CharacterClass> characterClasses = new HashMap<>();

    static {
        for (CharacterClass characterClass : CharacterClass.values()) {
            characterClasses.put(characterClass.getValue(), characterClass);
        }
    }

    CharacterClass(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static CharacterClass getCharacter(int value) {
        return characterClasses.get(value);
    }

    public int getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    public Character createPlayer(ImageView imageView, boolean isPlayer1) {
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
