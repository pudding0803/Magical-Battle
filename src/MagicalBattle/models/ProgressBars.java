package MagicalBattle.models;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.control.ProgressBar;
import MagicalBattle.Enums.Career;
import MagicalBattle.constants.CareerSettings;


public class ProgressBars {
    private static final HashMap<Career, AbilityValue> rateMap = CareerSettings.rateMap;

    private static final HashMap<Career, ArrayList<ProgressBar>> PROGRESS_BAR_MAP = new HashMap<>() {
        {
            put(Career.WARRIOR, getProgressBars(Career.WARRIOR));
            put(Career.GUARDIAN, getProgressBars(Career.GUARDIAN));
            put(Career.MAGE, getProgressBars(Career.MAGE));
            put(Career.ARCHER, getProgressBars(Career.ARCHER));
            put(Career.ASSASSIN, getProgressBars(Career.ASSASSIN));
            put(Career.ALCHEMIST, getProgressBars(Career.ALCHEMIST));
        }
    };

    private static ProgressBar getProgressBar(double progress, String styleClass) {
        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(progress);
        progressBar.getStyleClass().add(styleClass);
        return progressBar;
    }

    private static ArrayList<ProgressBar> getProgressBars(Career career) {
        AbilityValue abilityRate;
        switch (career) {
            case WARRIOR -> abilityRate = rateMap.get(Career.WARRIOR);
            case GUARDIAN -> abilityRate = rateMap.get(Career.GUARDIAN);
            case MAGE -> abilityRate = rateMap.get(Career.MAGE);
            case ARCHER -> abilityRate = rateMap.get(Career.ARCHER);
            case ASSASSIN -> abilityRate = rateMap.get(Career.ASSASSIN);
            case ALCHEMIST -> abilityRate = rateMap.get(Career.ALCHEMIST);
            default -> throw new IllegalStateException("Unexpected value: " + career);
        }
        return new ArrayList<>() {
            {
                add(getProgressBar(abilityRate.getHealth(), "health"));
                add(getProgressBar(abilityRate.getMagic(), "magic"));
                add(getProgressBar(abilityRate.getAttack(), "attack"));
                add(getProgressBar(abilityRate.getDefense(), "defense"));
                add(getProgressBar(abilityRate.getSpeed(), "speed"));
                add(getProgressBar(abilityRate.getAgility(), "agility"));
            }
        };
    }

    public static ProgressBar getProgressBars(Career career, int ability) {
        return PROGRESS_BAR_MAP.get(career).get(ability);
    }
}
