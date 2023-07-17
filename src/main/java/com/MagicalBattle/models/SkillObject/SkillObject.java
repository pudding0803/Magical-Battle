package com.MagicalBattle.models.SkillObject;

import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.Enums.StatusName;
import javafx.geometry.NodeOrientation;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class SkillObject {
    protected ImageView imageView = new ImageView();
    protected String fireAudio;
    protected String hitAudio;

    protected ArrayList<StatusName> statusList = new ArrayList<>();
    protected double damage;
    protected Character character;
    protected boolean attackBoth;
    protected boolean gravity;
    protected double velocityX;
    protected double velocityY;

    public SkillObject(Character character, String image, String fireAudio, String hitAudio) {
        this.character = character;
        this.imageView.setImage(AssetLoader.getSkillImage(image));
        if (this.character.isFacingLeft()) this.imageView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        this.setX(this.character.getX() + (this.character.isFacingLeft() ? -this.getWidth() : this.character.getWidth()));
        this.setY(this.character.getY() + (this.character.getHeight() - this.getHeight()) / 2);
        this.fireAudio = fireAudio;
        this.hitAudio = hitAudio;
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

    public ArrayList<StatusName> getStatusList() {
        return this.statusList;
    }

    public void playFireMedia() {
        AssetLoader.playFireAudio(this.fireAudio);
    }

    public void playHitMedia() {
        AssetLoader.playHitAudio(this.hitAudio);
    }
}
