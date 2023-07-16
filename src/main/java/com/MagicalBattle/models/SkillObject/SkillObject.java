package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.Enums.Status;
import javafx.geometry.NodeOrientation;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

import java.util.ArrayList;

public abstract class SkillObject {
    protected AudioClip fireMedia;
    protected AudioClip hitMedia;

    protected ImageView imageView = new ImageView();
    protected ArrayList<Status> statusList = new ArrayList<>();
    protected double damage;
    protected Character character;
    protected boolean attackBoth;
    protected boolean gravity;
    protected double velocityX;
    protected double velocityY;

    public SkillObject(Character character, String image, String fireMedia, String hitMedia) {
        this.character = character;

        this.imageView.setImage(AssetLoader.getSkillImage(image));
        if (this.character.isFacingLeft()) this.imageView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        this.setX(this.character.getX() + (this.character.isFacingLeft() ? -this.getWidth() : this.character.getWidth()));
        this.setY(this.character.getY() + (this.character.getHeight() - this.getHeight()) / 2);

        this.fireMedia = AssetLoader.getFireAudio(fireMedia);
        this.hitMedia = AssetLoader.getHitAudio(hitMedia);
    }

    public abstract void doByTime();

    public ImageView getImageView() {
        return this.imageView;
    }

    public double getDamage() {
        return this.damage;
    }

    public double getWidth() {
        return this.imageView.getImage().getWidth();
    }

    public double getHeight() {
        return this.imageView.getImage().getHeight();
    }

    public double getX() {
        return this.imageView.getLayoutX();
    }

    public double getY() {
        return this.imageView.getLayoutY();
    }

    public void setX(double value) {
        this.imageView.setLayoutX(value);
    }

    public void setY(double value) {
        this.imageView.setLayoutY(value);
    }

    public boolean isFromOther(boolean isPlayer1) {
        return this.character.isPlayer1() != isPlayer1;
    }

    public boolean isAttackBoth() {
        return this.attackBoth;
    }

    public double getVelocityX() {
        return this.velocityX;
    }

    public double getVelocityY() {
        return this.velocityY;
    }

    public boolean containFrozen() {
        return this.statusList.contains(Status.FROZEN);
    }

    public boolean containBurned() {
        return this.statusList.contains(Status.BURNED);
    }

    public boolean containStunned() {
        return this.statusList.contains(Status.STUNNED);
    }

    public boolean containDizzy() {
        return this.statusList.contains(Status.DIZZY);
    }

    public boolean containKnockBack() {
        return this.statusList.contains(Status.KNOCKED_BACK);
    }

    public boolean containKnockUp() {
        return this.statusList.contains(Status.KNOCKED_UP);
    }

    public void playFireMedia() {
        this.fireMedia.setVolume(Settings.EFFECT_VOLUME);
        this.fireMedia.play();
    }

    public void playHitMedia() {
        this.hitMedia.setVolume(Settings.EFFECT_VOLUME);
        this.hitMedia.play();
    }
}
