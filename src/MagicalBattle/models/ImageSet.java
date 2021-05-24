package MagicalBattle.models;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class ImageSet {
    private final ArrayList<Image> left;
    private final ArrayList<Image> right;
    private final ArrayList<Image> prepare;
    private final ArrayList<Image> selected;

    public ImageSet(ArrayList<Image> left, ArrayList<Image> right, ArrayList<Image> prepare, ArrayList<Image> selected) {
        this.left = left;
        this.right = right;
        this.prepare = prepare;
        this.selected = selected;
    }

    public Image getLeft(int index) {
        return this.left.get(index);
    }

    public Image getRight(int index) {
        return this.right.get(index);
    }

    public Image getPrepareOrSelect(int index, boolean selected) {
        if (selected) return this.selected.get(index);
        else return this.prepare.get(index);
    }

    public int indexInLeft(Image image) {
        return left.indexOf(image);
    }

    public int indexInRight(Image image) {
        return right.indexOf(image);
    }
}
