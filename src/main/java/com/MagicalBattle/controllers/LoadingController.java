package com.MagicalBattle.controllers;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.loaders.*;
import com.MagicalBattle.models.CharacterImageSet;
import com.MagicalBattle.models.enums.CharacterClass;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class LoadingController implements Initializable {
    @FXML
    Label file;
    @FXML
    Label loading;
    @FXML
    ImageView image;
    @FXML
    ProgressBar progress;
    @FXML
    Button start;

    private static long assetCount = 0;
    private static long maxAssetCount;

    static {
        try {
            maxAssetCount = ConfigLoader.getAssetsCount();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AbilityLoader.loadAbilities();
        ProgressBarsLoader.loadProgressBars();

        AtomicInteger dotCounter = new AtomicInteger(0);
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(Settings.LOADING_TIME), (event) -> {
            dotCounter.set(dotCounter.get() == 3 ? 0 : dotCounter.incrementAndGet());
            loading.setText("Loading" + ".".repeat(Math.max(0, dotCounter.get())));
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Thread thread = new Thread(() -> {
            loadCharacterImages();
            loadBackgroundImages();
            loadSkillImages();
            loadEffectImages();
            loadBackgroundMusics();
            loadFireAudios();
            loadHitAudios();
            loadEffectAudios();
            loadOtherAudios();
            Platform.runLater(() -> {
                timeline.stop();
                file.setVisible(false);
                loading.setText("Loading Finished");
                start.setDisable(false);
                try {
                    ConfigLoader.setAssetsCount(assetCount);
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            });
        });
        thread.start();
    }

    public void updateFileName(String fileName) {
        assetCount++;
        Platform.runLater(() -> {
            progress.setProgress(Math.min(1, (double) assetCount / maxAssetCount));
            file.setText(fileName);
        });
    }

    private void loadCharacterImages() {
        for (int i = 0; i < 6; i++) {
            CharacterClass character = CharacterClass.getCharacter(i);
            ArrayList<Image> idle = new ArrayList<>();
            ArrayList<Image> walking = new ArrayList<>();
            ArrayList<Image> preparing = new ArrayList<>();
            ArrayList<Image> selected = new ArrayList<>();
            for (int j = 0; j < 1; j++) {
                idle.add(ResourceLoader.getImage("images/character/" + character.getName() + "/idle/" + j + ".png"));
            }
            for (int j = 0; j < 2; j++) {
                walking.add(ResourceLoader.getImage("images/character/" + character.getName() + "/walking/" + j + ".png"));
            }
            for (int j = 0; j < 3; j++) {
                preparing.add(ResourceLoader.getImage("images/character/" + character.getName() + "/preparing/" + j + ".png"));
            }
            for (int j = 0; j < 3; j++) {
                selected.add(ResourceLoader.getImage("images/character/" + character.getName() + "/selected/" + j + ".png"));
            }
            Image dead = ResourceLoader.getImage("images/character/" + character.getName() + "/dead.png");
            AssetLoader.characters.put(character, new CharacterImageSet(idle, walking, preparing, selected, dead));
        }
    }

    private void loadBackgroundImages() {
        final String BACKGROUND_IMAGE_PATH = "images/background/";
        loadDirectory(BACKGROUND_IMAGE_PATH, AssetLoader.backgroundImages, ResourceLoader::getImage);
    }

    private void loadSkillImages() {
        final String SKILL_IMAGE_PATH = "images/attack/skill/";
        loadDirectory(SKILL_IMAGE_PATH, AssetLoader.skillImages, ResourceLoader::getImage);
    }

    private void loadEffectImages() {
        final String EFFECT_IMAGE_PATH = "images/attack/effect/";
        loadDirectory(EFFECT_IMAGE_PATH, AssetLoader.effectImages, ResourceLoader::getImage);
    }

    private void loadBackgroundMusics() {
        final String BACKGROUND_MUSIC_PATH = "media/background_music/";
        loadDirectory(BACKGROUND_MUSIC_PATH, AssetLoader.backgroundMusics, ResourceLoader::getMedia);
    }

    private void loadFireAudios() {
        final String FIRE_AUDIO_PATH = "media/fire/";
        loadDirectory(FIRE_AUDIO_PATH, AssetLoader.fireAudios, ResourceLoader::getAudioClip);
    }

    private void loadHitAudios() {
        final String HIT_AUDIO_PATH = "media/hit/";
        loadDirectory(HIT_AUDIO_PATH, AssetLoader.hitAudios, ResourceLoader::getAudioClip);
    }

    private void loadEffectAudios() {
        final String EFFECT_AUDIO_PATH = "media/effect/";
        loadDirectory(EFFECT_AUDIO_PATH, AssetLoader.effectAudios, ResourceLoader::getAudioClip);
    }

    private void loadOtherAudios() {
        final String OTHER_AUDIO_PATH = "media/other/";
        loadDirectory(OTHER_AUDIO_PATH, AssetLoader.otherAudios, ResourceLoader::getAudioClip);
    }

    private <T> void loadDirectory(String DIRECTORY_PATH, HashMap<String, T> resourceMap, Function<String, T> resourceLoader) {
        File directory = new File(Objects.requireNonNull(LoadingController.class.getResource(Settings.RESOURCE_PATH + DIRECTORY_PATH)).getPath());
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isFile()) {
                String fileName = file.getName();
                updateFileName(Settings.RESOURCE_PATH + DIRECTORY_PATH + file.getName());
                resourceMap.put(fileName.substring(0, fileName.indexOf(".")), resourceLoader.apply(DIRECTORY_PATH + fileName));
            }
        }
    }

    @FXML
    public void switchToMenu() throws IOException {
        AssetLoader.playOtherAudio("submit");
        ViewController.toMenuScene();
    }
}
