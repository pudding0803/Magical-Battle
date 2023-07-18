package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.enums.StatusName;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class FlashArrow extends Arrow {
    private static final String NAME = "flash_arrow";
    private static final ArrayList<Image> IMAGES = AssetLoader.getSkillImages(NAME);

    public FlashArrow(Character character) {
        super(character, IMAGES.get(0), NAME);
        statusList = new ArrayList<>(List.of(StatusName.STUNNED));
    }
}
