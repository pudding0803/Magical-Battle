package MagicalBattle.config;

import MagicalBattle.constants.Settings;
import javafx.scene.image.Image;
import MagicalBattle.models.enums.Career;
import MagicalBattle.models.ImageSet;

import java.util.ArrayList;
import java.util.Objects;

public class ImagesLoader {
    public static ImageSet loadImages(Career career) {
        return new ImageSet(
                new ArrayList<>() {
                    {
                        for (int i = 0; i < Settings.MOVE_IMAGES_COUNT - 1; i++) {
                            add(new Image(Objects.requireNonNull(getClass().getResource(
                                    "../assets/career/" + career.getName() + "/left/" + i + ".png")
                            ).toExternalForm()));
                        }
                        add(new Image(Objects.requireNonNull(getClass().getResource("../assets/career/" + career.getName() + "/left/" + 1 + ".png")).toExternalForm()));
                    }
                },
                new ArrayList<>() {
                    {
                        for (int i = 0; i < Settings.MOVE_IMAGES_COUNT - 1; i++) {
                            add(new Image(Objects.requireNonNull(getClass().getResource(
                                    "../assets/career/" + career.getName() + "/right/" + i + ".png")
                            ).toExternalForm()));
                        }
                        add(new Image(Objects.requireNonNull(getClass().getResource("../assets/career/" + career.getName() + "/right/" + 1 + ".png")).toExternalForm()));
                    }
                },
                new ArrayList<>() {
                    {
                        for (int i = 0; i < Settings.CHOOSE_IMAGES_COUNT; i++) {
                            add(new Image(Objects.requireNonNull(getClass().getResource(
                                    "../assets/career/" + career.getName() + "/prepare/" + i + ".png")
                            ).toExternalForm()));
                        }
                    }
                },
                new ArrayList<>() {
                    {
                        for (int i = 0; i < Settings.CHOOSE_IMAGES_COUNT; i++) {
                            add(new Image(Objects.requireNonNull(getClass().getResource(
                                    "../assets/career/" + career.getName() + "/selected/" + i + ".png")
                            ).toExternalForm()));
                        }
                    }
                },
                new Image(Objects.requireNonNull(ImagesLoader.class.getResource("../assets/career/" + career.getName() + "/dead.png")).toExternalForm())
        );
    }

}
