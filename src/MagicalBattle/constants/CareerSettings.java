package MagicalBattle.constants;

import MagicalBattle.models.enums.Career;
import MagicalBattle.config.ImagesLoader;
import MagicalBattle.config.AbilityReader;
import MagicalBattle.models.AbilityValue;
import MagicalBattle.models.ImageSet;

import java.util.HashMap;

public final class CareerSettings {
    public static final HashMap<Career, AbilityValue> abilityMap = new HashMap<>() {
        {
            put(Career.WARRIOR, AbilityReader.getAbilityValue(Career.WARRIOR));
            put(Career.GUARDIAN, AbilityReader.getAbilityValue(Career.GUARDIAN));
            put(Career.MAGE, AbilityReader.getAbilityValue(Career.MAGE));
            put(Career.ARCHER, AbilityReader.getAbilityValue(Career.ARCHER));
            put(Career.ASSASSIN, AbilityReader.getAbilityValue(Career.ASSASSIN));
            put(Career.ALCHEMIST, AbilityReader.getAbilityValue(Career.ALCHEMIST));
        }
    };

    public static final HashMap<Career, AbilityValue> rateMap = new HashMap<>() {
        {
            put(Career.WARRIOR, AbilityReader.getAbilityRate(Career.WARRIOR));
            put(Career.GUARDIAN, AbilityReader.getAbilityRate(Career.GUARDIAN));
            put(Career.MAGE, AbilityReader.getAbilityRate(Career.MAGE));
            put(Career.ARCHER, AbilityReader.getAbilityRate(Career.ARCHER));
            put(Career.ASSASSIN, AbilityReader.getAbilityRate(Career.ASSASSIN));
            put(Career.ALCHEMIST, AbilityReader.getAbilityRate(Career.ALCHEMIST));
        }
    };

    public static final HashMap<Career, ImageSet> imageSetMap = new HashMap<>() {
        {
            put(Career.WARRIOR, ImagesLoader.loadImages(Career.WARRIOR));
            put(Career.GUARDIAN, ImagesLoader.loadImages(Career.GUARDIAN));
            put(Career.MAGE, ImagesLoader.loadImages(Career.MAGE));
            put(Career.ARCHER, ImagesLoader.loadImages(Career.ARCHER));
            put(Career.ASSASSIN, ImagesLoader.loadImages(Career.ASSASSIN));
            put(Career.ALCHEMIST, ImagesLoader.loadImages(Career.ALCHEMIST));
        }
    };

}
