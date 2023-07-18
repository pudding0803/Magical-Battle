package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.enums.StatusName;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class FlameArrow extends Arrow {
    private static final String NAME = "flame_arrow";
    private static final ArrayList<Image> IMAGES = AssetLoader.getSkillImages(NAME);

    public FlameArrow(Character character) {
        super(character, IMAGES.get(0), NAME);
        statusList = new ArrayList<>(List.of(StatusName.BURNED));
    }
}
