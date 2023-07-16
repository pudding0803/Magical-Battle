package com.MagicalBattle.models;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class CharacterImageSet {
    private final ArrayList<Image> idle;
    private final ArrayList<Image> walking;
    private final ArrayList<Image> preparing;
    private final ArrayList<Image> selected;
    private final Image dead;

    public CharacterImageSet(ArrayList<Image> idle, ArrayList<Image> walking, ArrayList<Image> preparing, ArrayList<Image> selected, Image dead) {
        this.idle = idle;
        this.walking = walking;
        this.preparing = preparing;
        this.selected = selected;
        this.dead = dead;
    }

    public Image getIdle(int index) {
        return this.idle.get(index);
    }

    public Image getWalking(int index) {
        return this.walking.get(index);
    }

    public Image getPreparingOrSelect(int index, boolean selected) {
        if (selected) return this.selected.get(index);
        else return this.preparing.get(index);
    }

    public Image getDead() {
        return this.dead;
    }
}
