package com.MagicalBattle.loaders;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.models.CharacterImageSet;
import com.MagicalBattle.models.enums.CharacterClass;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.util.ArrayList;
import java.util.HashMap;

public class AssetLoader {
    public static final HashMap<CharacterClass, CharacterImageSet> characters = new HashMap<>();
    public static final HashMap<String, Image> backgroundImages = new HashMap<>();
    public static final HashMap<String, ArrayList<Image>> skillImages = new HashMap<>();
    public static final HashMap<String, ArrayList<Image>> effectImages = new HashMap<>();
    public static final HashMap<String, Media> backgroundMusics = new HashMap<>();
    public static final HashMap<String, AudioClip> fireAudios = new HashMap<>();
    public static final HashMap<String, AudioClip> hitAudios = new HashMap<>();
    public static final HashMap<String, AudioClip> effectAudios = new HashMap<>();
    public static final HashMap<String, AudioClip> otherAudios = new HashMap<>();

    public static CharacterImageSet getCharacterImageSet(CharacterClass character) {
        return characters.get(character);
    }

    public static HashMap<String, Image> getBackgroundImages() {
        return backgroundImages;
    }

    public static ArrayList<Image> getSkillImages(String name) {
        return skillImages.get(name);
    }

    public static ArrayList<Image> getEffectImages(String name) {
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

    private static void playAudio(AudioClip audioClip) {
        audioClip.setVolume(Settings.EFFECT_VOLUME);
        audioClip.play();
    }
}
