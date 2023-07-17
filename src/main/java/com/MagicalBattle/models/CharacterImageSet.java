package com.MagicalBattle.models;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class CharacterImageSet {
    private final ArrayList<Image> idleImages;
    private final ArrayList<Image> walkingImages;
    private final ArrayList<Image> preparingImages;
    private final ArrayList<Image> selectedImages;
    private final Image deadImage;

    public CharacterImageSet(ArrayList<Image> idleImages, ArrayList<Image> walkingImages, ArrayList<Image> preparingImages, ArrayList<Image> selectedImages, Image deadImage) {
        this.idleImages = idleImages;
        this.walkingImages = walkingImages;
        this.preparingImages = preparingImages;
        this.selectedImages = selectedImages;
        this.deadImage = deadImage;
    }

    public Image getIdle(int index) {
        return idleImages.get(index);
    }

    public Image getWalking(int index) {
        return walkingImages.get(index);
    }

    public Image getPreparingOrSelect(int index, boolean selected) {
        if (selected) return selectedImages.get(index);
        else return preparingImages.get(index);
    }

    public Image getDead() {
        return deadImage;
    }
}
