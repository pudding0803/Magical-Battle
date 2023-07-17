package com.MagicalBattle.models.DisplayObject.EffectObject;

import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.DisplayObject.DisplayObject;
import javafx.scene.image.ImageView;

public abstract class EffectObject extends DisplayObject {
    protected final ImageView imageView;

    public EffectObject(Character character, String image) {
        super(character);
        imageView = new ImageView(AssetLoader.getEffectImage(image));
        adjustPosition();
    }
}
