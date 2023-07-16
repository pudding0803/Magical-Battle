package com.MagicalBattle.loaders;

import com.MagicalBattle.models.CharacterImageSet;
import com.MagicalBattle.models.Enums.CharacterClass;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ImagesLoader {
    private static final HashMap<CharacterClass, CharacterImageSet> characters = new HashMap<>();
    
    public static CharacterImageSet getCharacterImageSet(CharacterClass character) {
        return characters.get(character);
    }

    public static void loadImages() {
        for (int i = 0; i < 6; i++) {
            CharacterClass character = CharacterClass.getCharacter(i);
            ArrayList<Image> idle = new ArrayList<>(List.of(
                    ResourceLoader.getImage("images/character/" + character.getName() + "/idle/0.png")
            ));
            ArrayList<Image> walking = new ArrayList<>(List.of(
                    ResourceLoader.getImage("images/character/" + character.getName() + "/walking/0.png"),
                    ResourceLoader.getImage("images/character/" + character.getName() + "/walking/1.png")
            ));
            ArrayList<Image> preparing = new ArrayList<>(List.of(
                    ResourceLoader.getImage("images/character/" + character.getName() + "/preparing/0.png"),
                    ResourceLoader.getImage("images/character/" + character.getName() + "/preparing/1.png"),
                    ResourceLoader.getImage("images/character/" + character.getName() + "/preparing/2.png")
            ));
            ArrayList<Image> selected = new ArrayList<>(List.of(
                    ResourceLoader.getImage("images/character/" + character.getName() + "/selected/0.png"),
                    ResourceLoader.getImage("images/character/" + character.getName() + "/selected/1.png"),
                    ResourceLoader.getImage("images/character/" + character.getName() + "/selected/2.png")
            ));
            Image dead = ResourceLoader.getImage("images/character/" + character.getName() + "/dead.png");
            characters.put(character, new CharacterImageSet(idle, walking, preparing, selected, dead));
        }
    }
}
