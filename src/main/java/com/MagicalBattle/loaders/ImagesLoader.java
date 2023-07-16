package com.MagicalBattle.loaders;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.models.CharacterImageSet;
import com.MagicalBattle.models.Enums.CharacterClass;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ImagesLoader {
    private static final HashMap<CharacterClass, CharacterImageSet> characters = new HashMap<>();
    private static final HashMap<String, Image> backgrounds = new HashMap<>();
    private static final HashMap<String, Image> skills = new HashMap<>();
    private static final HashMap<String, Image> effects = new HashMap<>();
    
    public static CharacterImageSet getCharacterImageSet(CharacterClass character) {
        return characters.get(character);
    }

    public static HashMap<String, Image> getBackgroundImages() {
        return backgrounds;
    }

    public static void loadCharacterImages() {
        for (int i = 0; i < 6; i++) {
            CharacterClass character = CharacterClass.getCharacter(i);
            ArrayList<Image> idle = new ArrayList<>();
            for (int j = 0; j < 1; j++) {
                idle.add(ResourceLoader.getImage("images/character/" + character.getName() + "/idle/" + j + ".png"));
            }
            ArrayList<Image> walking = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                walking.add(ResourceLoader.getImage("images/character/" + character.getName() + "/walking/" + j + ".png"));
            }
            ArrayList<Image> preparing = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                preparing.add(ResourceLoader.getImage("images/character/" + character.getName() + "/preparing/" + j + ".png"));
            }
            ArrayList<Image> selected = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                selected.add(ResourceLoader.getImage("images/character/" + character.getName() + "/selected/" + j + ".png"));
            }
            Image dead = ResourceLoader.getImage("images/character/" + character.getName() + "/dead.png");
            characters.put(character, new CharacterImageSet(idle, walking, preparing, selected, dead));
        }
    }

    public static void loadBackgroundImages() {
        final String BACKGROUND_PATH = "images/background/";
        loadDirectory(BACKGROUND_PATH, backgrounds);
    }

    public static void loadSkillImages() {
        final String SKILL_PATH = "images/attack/skill/";
        loadDirectory(SKILL_PATH, skills);
    }

    public static void loadEffectImages() {
        final String EFFECT_PATH = "images/attack/effect/";
        loadDirectory(EFFECT_PATH, effects);
    }

    private static void loadDirectory(String DIRECTORY_PATH, HashMap<String, Image> imageMap) {
        File directory = new File(Objects.requireNonNull(ImagesLoader.class.getResource(Settings.RESOURCE_PATH + DIRECTORY_PATH)).getPath());
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isFile()) {
                String fileName = file.getName();
                imageMap.put(fileName.substring(0, fileName.indexOf(".")), ResourceLoader.getImage(DIRECTORY_PATH + fileName));
            }
        }
    }
}
