package MagicalBattle.models;

import javafx.scene.image.ImageView;
import MagicalBattle.Enums.Status;

import java.util.ArrayList;

public class SkillObject {
    private final ImageView imageView;
    private final ArrayList<Status> statusList;
    private final double damage;
    private final boolean fromPlayer1;
    private double velocityX;
    private double velocityY;

    public SkillObject(ImageView imageView, ArrayList<Status> statusList, double damage, boolean fromPlayer1, double velocityX, double velocityY) {
        this.imageView = imageView;
        this.statusList = statusList;
        this.damage = damage;
        this.fromPlayer1 = fromPlayer1;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

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

    public boolean containKnockBack() {
        return this.statusList.contains(Status.KNOCKED_BACK);
    }

    public boolean containKnockUp() {
        return this.statusList.contains(Status.KNOCKED_UP);
    }

}
