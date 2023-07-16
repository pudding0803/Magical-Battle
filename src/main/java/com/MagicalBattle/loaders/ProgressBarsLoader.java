package com.MagicalBattle.loaders;

import com.MagicalBattle.models.Enums.CharacterClass;
import javafx.scene.control.ProgressBar;

import java.util.ArrayList;
import java.util.HashMap;

import static com.MagicalBattle.loaders.AbilityLoader.getAbilityRatio;


public class ProgressBarsLoader {
    private static final HashMap<CharacterClass, ArrayList<ProgressBar>> progressBarsMap = new HashMap<>();

    public static ProgressBar getProgressBars(CharacterClass characterClass, int ability) {
        return progressBarsMap.get(characterClass).get(ability);
    }

    public static void loadProgressBars() {
        for (int i = 0; i < 6; i++) {
            CharacterClass characterClass = CharacterClass.getCharacter(i);
            ArrayList<ProgressBar> progressBars = new ArrayList<>();
            progressBars.add(getProgressBar(getAbilityRatio(characterClass).getHealth(), "health"));
            progressBars.add(getProgressBar(getAbilityRatio(characterClass).getMagic(), "magic"));
            progressBars.add(getProgressBar(getAbilityRatio(characterClass).getAttack(), "attack"));
            progressBars.add(getProgressBar(getAbilityRatio(characterClass).getDefense(), "defense"));
            progressBars.add(getProgressBar(getAbilityRatio(characterClass).getSpeed(), "speed"));
            progressBars.add(getProgressBar(getAbilityRatio(characterClass).getAgility(), "agility"));
            progressBarsMap.put(characterClass, progressBars);
        }
    }

    private static ProgressBar getProgressBar(double progress, String styleClass) {
        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(progress);
        progressBar.getStyleClass().add(styleClass);
        return progressBar;
    }
}
