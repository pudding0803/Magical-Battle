package com.MagicalBattle.loaders;

import com.MagicalBattle.constants.Settings;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.util.Objects;

public class ResourceLoader {
    public static Image getImage(String path) {
        return new Image(Objects.requireNonNull(ResourceLoader.class.getResource(Settings.RESOURCE_PATH + path)).toExternalForm());
    }

    public static Media getMedia(String path) {
        return new Media(Objects.requireNonNull(ResourceLoader.class.getResource(Settings.RESOURCE_PATH + path)).toExternalForm());
    }

    public static AudioClip getAudioClip(String path) {
        return new AudioClip(Objects.requireNonNull(ResourceLoader.class.getResource(Settings.RESOURCE_PATH + path)).toExternalForm());
    }
}
