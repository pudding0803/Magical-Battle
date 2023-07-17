package com.MagicalBattle.loaders;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.models.CharacterImageSet;
import com.MagicalBattle.models.enums.CharacterClass;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class AssetLoader {
    private static final HashMap<CharacterClass, CharacterImageSet> characters = new HashMap<>();
    private static final HashMap<String, Image> backgroundImages = new HashMap<>();
    private static final HashMap<String, Image> skillImages = new HashMap<>();
    private static final HashMap<String, Image> effectImages = new HashMap<>();
    private static final HashMap<String, Media> backgroundMusics = new HashMap<>();
    private static final HashMap<String, AudioClip> fireAudios = new HashMap<>();
    private static final HashMap<String, AudioClip> hitAudios = new HashMap<>();
    private static final HashMap<String, AudioClip> effectAudios = new HashMap<>();
    private static final HashMap<String, AudioClip> otherAudios = new HashMap<>();

    public static void loadAll() {
        loadCharacterImages();
        loadBackgroundImages();
        loadSkillImages();
        loadEffectImages();
        loadBackgroundMusics();
        loadFireAudios();
        loadHitAudios();
        loadEffectAudios();
        loadOtherAudios();
    }
    
    public static CharacterImageSet getCharacterImageSet(CharacterClass character) {
        return characters.get(character);
    }

    public static HashMap<String, Image> getBackgroundImages() {
        return backgroundImages;
    }

    public static Image getSkillImage(String name) {
        return skillImages.get(name);
    }

    public static Image getEffectImage(String name) {
        return effectImages.get(name);
    }

    public static Media getBackgroundMusic(String name) {
        return backgroundMusics.get(name);
    }

    public static void playFireAudio(String name) {
        playAudio(fireAudios.get(name));
    }

    public static void playHitAudio(String name) {
        playAudio(hitAudios.get(name));
    }

    public static void playEffectAudio(String name) {
        playAudio(effectAudios.get(name));
    }

    public static void playOtherAudio(String name) {
        playAudio(otherAudios.get(name));
    }

    private static void loadCharacterImages() {
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

    private static void loadBackgroundImages() {
        final String BACKGROUND_IMAGE_PATH = "images/background/";
        loadImageDirectory(BACKGROUND_IMAGE_PATH, backgroundImages);
    }

    private static void loadSkillImages() {
        final String SKILL_IMAGE_PATH = "images/attack/skill/";
        loadImageDirectory(SKILL_IMAGE_PATH, skillImages);
    }

    private static void loadEffectImages() {
        final String EFFECT_IMAGE_PATH = "images/attack/effect/";
        loadImageDirectory(EFFECT_IMAGE_PATH, effectImages);
    }

    private static void loadBackgroundMusics() {
        final String BACKGROUND_MUSIC_PATH = "media/background_music/";
        File directory = new File(Objects.requireNonNull(AssetLoader.class.getResource(Settings.RESOURCE_PATH + BACKGROUND_MUSIC_PATH)).getPath());
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isFile()) {
                String fileName = file.getName();
                AssetLoader.backgroundMusics.put(fileName.substring(0, fileName.indexOf(".")), ResourceLoader.getMedia(BACKGROUND_MUSIC_PATH + fileName));
            }
        }
    }

    private static void loadFireAudios() {
        final String FIRE_AUDIO_PATH = "media/fire/";
        loadAudioDirectory(FIRE_AUDIO_PATH, fireAudios);
    }

    private static void loadHitAudios() {
        final String HIT_AUDIO_PATH = "media/hit/";
        loadAudioDirectory(HIT_AUDIO_PATH, hitAudios);
    }

    private static void loadEffectAudios() {
        final String EFFECT_AUDIO_PATH = "media/effect/";
        loadAudioDirectory(EFFECT_AUDIO_PATH, effectAudios);
    }

    private static void loadOtherAudios() {
        final String OTHER_AUDIO_PATH = "media/other/";
        loadAudioDirectory(OTHER_AUDIO_PATH, otherAudios);
    }
    
    private static void loadImageDirectory(String DIRECTORY_PATH, HashMap<String, Image> imageMap) {
        File directory = new File(Objects.requireNonNull(AssetLoader.class.getResource(Settings.RESOURCE_PATH + DIRECTORY_PATH)).getPath());
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isFile()) {
                String fileName = file.getName();
                imageMap.put(fileName.substring(0, fileName.indexOf(".")), ResourceLoader.getImage(DIRECTORY_PATH + fileName));
            }
        }
    }

    private static void loadAudioDirectory(String DIRECTORY_PATH, HashMap<String, AudioClip> imageMap) {
        File directory = new File(Objects.requireNonNull(AssetLoader.class.getResource(Settings.RESOURCE_PATH + DIRECTORY_PATH)).getPath());
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isFile()) {
                String fileName = file.getName();
                imageMap.put(fileName.substring(0, fileName.indexOf(".")), ResourceLoader.getAudioClip(DIRECTORY_PATH + fileName));
            }
        }
    }

    private static void playAudio(AudioClip audioClip) {
        audioClip.setVolume(Settings.EFFECT_VOLUME);
        audioClip.play();
    }
}
