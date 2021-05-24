package MagicalBattle.config;

import javafx.scene.image.Image;
import MagicalBattle.enums.Career;
import MagicalBattle.models.ImageSet;

import java.util.ArrayList;
import java.util.Objects;

public class ImagesLoader {
    public static ImageSet loadImages(Career career) {
        return new ImageSet(
                new ArrayList<>() {
                    {
                        for (int i = 0; i < 3; i++)
                            add(new Image(Objects.requireNonNull(getClass().getResource(
                                    "../assets/" + career.getName() + "/left/" + i + ".png")
                            ).toString()));
                    }
                },
                new ArrayList<>() {
                    {
                        for (int i = 0; i < 3; i++)
                            add(new Image(Objects.requireNonNull(getClass().getResource(
                                    "../assets/" + career.getName() + "/right/" + i + ".png")
                            ).toString()));
                    }
                },
                new ArrayList<>() {
                    {
                        for (int i = 0; i < 3; i++)
                            add(new Image(Objects.requireNonNull(getClass().getResource(
                                    "../assets/" + career.getName() + "/prepare/" + i + ".png")
                            ).toString()));
                    }
                },
                new ArrayList<>() {
                    {
                        for (int i = 0; i < 3; i++)
                            add(new Image(Objects.requireNonNull(getClass().getResource(
                                    "../assets/" + career.getName() + "/selected/" + i + ".png")
                            ).toString()));
                    }
                }
        );
    }

}
