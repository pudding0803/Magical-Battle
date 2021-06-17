package MagicalBattle.skillObject;

import javafx.scene.image.ImageView;
import MagicalBattle.enums.Status;
import javafx.scene.media.AudioClip;

import java.util.ArrayList;
import java.util.Objects;

public abstract class SkillObject {
    protected AudioClip fireMedia;
    protected AudioClip hitMedia;

    protected ImageView imageView = new ImageView();
    protected ArrayList<Status> statusList = new ArrayList<>();
    protected double damage;
    protected boolean fromPlayer1;
    protected boolean attackBoth;
    protected boolean gravity;
    protected double velocityX;
    protected double velocityY;

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
        return this.fromPlayer1 != isPlayer1;
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
        this.fireMedia.play();
    }

    public void playHitMedia() {
        this.hitMedia.play();
    }

}
