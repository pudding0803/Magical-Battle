package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.enums.StatusName;
import javafx.geometry.NodeOrientation;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class SkillObject {
    protected final ImageView imageView = new ImageView();
    protected final String fireAudio;
    protected final String hitAudio;

    protected ArrayList<StatusName> statusList = new ArrayList<>();
    protected double damage;
    protected final Character character;
    protected boolean attackBoth;
    protected boolean gravity;
    protected double velocityX;
    protected double velocityY;

    public SkillObject(Character character, String image, String fireAudio, String hitAudio) {
        this.character = character;
        imageView.setImage(AssetLoader.getSkillImage(image));
        if (character.isFacingLeft()) imageView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        setX(character.getX() + (character.isFacingLeft() ? -getWidth() : character.getWidth()));
        setY(character.getY() + (character.getHeight() - getHeight()) / 2);
        this.fireAudio = fireAudio;
        this.hitAudio = hitAudio;
    }

    public void doByTime() {
        setX(getX() + getVelocityX());
        setX(getX() + getVelocityY());
        doPerTime();
    }

    protected abstract void doPerTime();

    public ImageView getImageView() {
        return imageView;
    }

    public double getDamage() {
        return damage;
    }

    public double getWidth() {
        return imageView.getImage().getWidth();
    }

    public double getHeight() {
        return imageView.getImage().getHeight();
    }

    public double getX() {
        return imageView.getLayoutX();
    }

    public double getY() {
        return imageView.getLayoutY();
    }

    public void setX(double value) {
        imageView.setLayoutX(value);
    }

    public void setY(double value) {
        imageView.setLayoutY(value);
    }

    public boolean isFromOther(boolean isPlayer1) {
        return character.isPlayer1() != isPlayer1;
    }

    public boolean isAttackBoth() {
        return attackBoth;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public ArrayList<StatusName> getStatusList() {
        return statusList;
    }

    public void playFireMedia() {
        AssetLoader.playFireAudio(fireAudio);
    }

    public void playHitMedia() {
        AssetLoader.playHitAudio(hitAudio);
    }
}
