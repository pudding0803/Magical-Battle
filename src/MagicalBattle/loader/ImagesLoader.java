package MagicalBattle.loader;

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
                                    "../assets/career/" + career.getName() + "/left/" + i + ".png")
                            ).toExternalForm()));
                    }
                },
                new ArrayList<>() {
                    {
                        for (int i = 0; i < 3; i++)
                            add(new Image(Objects.requireNonNull(getClass().getResource(
                                    "../assets/career/" + career.getName() + "/right/" + i + ".png")
                            ).toExternalForm()));
                    }
                },
                new ArrayList<>() {
                    {
                        for (int i = 0; i < 3; i++)
                            add(new Image(Objects.requireNonNull(getClass().getResource(
                                    "../assets/career/" + career.getName() + "/prepare/" + i + ".png")
                            ).toExternalForm()));
                    }
                },
                new ArrayList<>() {
                    {
                        for (int i = 0; i < 3; i++)
                            add(new Image(Objects.requireNonNull(getClass().getResource(
                                    "../assets/career/" + career.getName() + "/selected/" + i + ".png")
                            ).toExternalForm()));
                    }
                },
                new Image(Objects.requireNonNull(ImagesLoader.class.getResource("../assets/career/" + career.getName() + "/dead.png")).toExternalForm())
        );
    }

}
