package com.MagicalBattle.models.Character;

import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.controllers.GameController;
import com.MagicalBattle.loaders.AbilityLoader;
import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.AbilitySet;
import com.MagicalBattle.models.AttackTimers;
import com.MagicalBattle.models.EffectObject.MissLabel;
import com.MagicalBattle.models.Enums.CharacterClass;
import com.MagicalBattle.models.Enums.HDirection;
import com.MagicalBattle.models.Enums.StatusName;
import com.MagicalBattle.models.Enums.VDirection;
import com.MagicalBattle.models.SkillObject.SkillObject;
import com.MagicalBattle.models.StatusTimers;
import com.MagicalBattle.models.Timer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.NodeOrientation;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Random;

public abstract class Character {
    protected ImageView imageView;
    protected CharacterClass characterClass;
    protected boolean player1;

    protected AbilitySet currentAbility;
    protected double moveDistance;
    protected double velocity = 0.0;
    protected VDirection vDirection = VDirection.NULL;
    protected HDirection hDirection = HDirection.NULL;
    protected HDirection facing;
    protected int walkingIndex = 0;
    protected int jumpCount = 0;

    protected final AttackTimers attackTimers;
    protected final StatusTimers statusTimers = new StatusTimers(this);

    protected int maxJumpCount = 1;

    public Character(ImageView imageView, CharacterClass characterClass, boolean player1, AttackTimers attackTimers) {
        this.imageView = imageView;
        this.imageView.setImage(AssetLoader.getCharacterImageSet(characterClass).getIdle(0));
        this.facing = (player1 ? HDirection.RIGHT : HDirection.LEFT);
        this.characterClass = characterClass;
        this.currentAbility = new AbilitySet(AbilityLoader.getAbilityValue(characterClass));
        this.player1 = player1;
        this.attackTimers = attackTimers;
    }

    public CharacterClass getCharacterClass() {
        return this.characterClass;
    }

    public boolean isPlayer1() {
        return this.player1;
    }

    public boolean isDead() {
        return this.currentAbility.getHealth() == 0;
    }

    public void setGameOverImage(boolean winner, int counter) {
        Image image;
        if (winner) image = AssetLoader.getCharacterImageSet(this.characterClass).getPreparingOrSelect(counter, true);
        else image = AssetLoader.getCharacterImageSet(this.characterClass).getDead();

        this.imageView.setImage(image);
        this.imageView.setFitHeight(image.getHeight());
        if (!this.isFacingLeft()) this.imageView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        if (this.getX() < 0) {
            this.setX(0);
        } else if (this.getX() > Settings.WIDTH - this.getWidth()) {
            this.setX(Settings.WIDTH - this.getWidth());
        }
    }

    public double getWidth() {
        return this.imageView.getImage().getWidth() * Settings.PLAYER_SIZE_RATE;
    }

    public double getHeight() {
        return this.imageView.getImage().getHeight() * Settings.PLAYER_SIZE_RATE;
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

    public double getHealthRate() {
        return this.currentAbility.getHealth() / AbilityLoader.getAbilityValue(this.characterClass).getHealth();
    }

    public double getMagicRate() {
        return this.currentAbility.getMagic() / AbilityLoader.getAbilityValue(this.characterClass).getMagic();
    }

    public double getHealth() {
        return this.currentAbility.getHealth();
    }

    public double getMagic() {
        return this.currentAbility.getMagic();
    }

    public double getAttack() {
        return this.currentAbility.getAttack();
    }

    public double getDefense() {
        return this.currentAbility.getDefense();
    }

    public double getSpeed() {
        return this.currentAbility.getSpeed();
    }

    public double getAgility() {
        return this.currentAbility.getAgility();
    }

    public double getMaxHealth() {
        return AbilityLoader.getAbilityValue(this.characterClass).getHealth();
    }

    public double getMaxMagic() {
        return AbilityLoader.getAbilityValue(this.characterClass).getMagic();
    }

    public double getMaxAttack() {
        return AbilityLoader.getAbilityValue(this.characterClass).getAttack();
    }

    public double getMaxDefense() {
        return AbilityLoader.getAbilityValue(this.characterClass).getDefense();
    }

    public double getMaxSpeed() {
        return AbilityLoader.getAbilityValue(this.characterClass).getSpeed();
    }

    public double getMaxAgility() {
        return AbilityLoader.getAbilityValue(this.characterClass).getAgility();
    }

    public void setHealth(double value) {
        this.currentAbility.setHealth(Math.max(0, value));
    }

    public void setSpeed(double value) {
        this.currentAbility.setSpeed(Math.max(0, value));
    }

    public void setAgility(double value) {
        this.currentAbility.setAgility(Math.max(0, value));
    }

    public boolean isUp() {
        return this.vDirection == VDirection.UP;
    }

    public boolean isDown() {
        return this.vDirection == VDirection.DOWN;
    }

    public boolean isLeft() {
        return this.hDirection == HDirection.LEFT;
    }

    public boolean isRight() {
        return this.hDirection == HDirection.RIGHT;
    }

    public boolean isFacingLeft() {
        return this.facing == HDirection.LEFT;
    }

    public boolean isOnGround() {
        return this.getY() + this.velocity > Settings.GROUND_HEIGHT - this.getHeight();
    }

    public void setVDirection(VDirection vDirection) {
        this.vDirection = vDirection;
    }

    public void setHDirection(HDirection hDirection) {
        this.hDirection = hDirection;
        if (hDirection != HDirection.NULL) this.facing = hDirection;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public void updateVelocity() {
        if (this.isUp() && this.jumpCount++ < this.maxJumpCount) {
            AssetLoader.playOtherAudio("jump");
            this.velocity = -(Settings.INITIAL_VELOCITY + Settings.BONUS_VELOCITY * this.getSpeed());
        } else if (this.isDown()) {
            this.velocity = Settings.INITIAL_VELOCITY + Settings.BONUS_VELOCITY * this.getSpeed();
        }
        this.vDirection = VDirection.NULL;
    }

    public void doHorizonMotion() {
        this.imageView.setImage(AssetLoader.getCharacterImageSet(this.characterClass).getWalking(walkingIndex));
        this.imageView.setNodeOrientation(this.isFacingLeft() ? NodeOrientation.RIGHT_TO_LEFT : NodeOrientation.LEFT_TO_RIGHT);
        this.walkingIndex = (++this.walkingIndex) % 2;
        this.moveDistance = Settings.STEP * this.currentAbility.getSpeed() * this.hDirection.getValue();
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(Settings.UPDATE_TIME), (event) -> {
            this.setX(this.getX() + this.moveDistance);
            if (this.getX() < 0) {
                this.setX(0);
            } else if (this.getX() > Settings.WIDTH - this.getWidth()) {
                this.setX(Settings.WIDTH - this.getWidth());
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public void doVerticalMotion() {
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(Settings.UPDATE_TIME), (event) -> {
            if (this.getY() + this.velocity <= Settings.GROUND_HEIGHT - this.getHeight()) {
                this.setY(this.getY() + this.velocity * (this.getSpeed() + this.getMaxSpeed() * 3) / 4);
                this.velocity += Settings.GRAVITY;
            } else if (this.isOnGround()) {
                this.setY(Settings.GROUND_HEIGHT - this.getHeight());
                this.velocity = 0;
            } else if (this.getY() + this.velocity < 0) {
                this.setY(0);
                this.velocity = 0;
            }
            if (this.getY() == Settings.GROUND_HEIGHT - this.getHeight()) {
                if (this.jumpCount > 0) {
                    AssetLoader.playOtherAudio("land");
                }
                this.jumpCount = 0;
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public void setStand() {
        this.imageView.setImage(AssetLoader.getCharacterImageSet(this.characterClass).getIdle(0));
        this.imageView.setNodeOrientation(this.isFacingLeft() ? NodeOrientation.RIGHT_TO_LEFT : NodeOrientation.LEFT_TO_RIGHT);
    }

    public void resetJumpCount() {
        this.jumpCount = this.maxJumpCount;
    }

    public void setEffect(Effect effect) {
        this.imageView.setEffect(effect);
    }

    public AttackTimers getAttackTimers() {
        return this.attackTimers;
    }

    public Timer getStatusTimer(StatusName statusName) {
        return this.statusTimers.getTimer(statusName);
    }

    public void stopStatusTimers() {
        this.statusTimers.stopAll();
    }

    public boolean isCollidedFromOther(SkillObject skillObject) {
        double x = skillObject.getX();
        double y = skillObject.getY();
        double width = skillObject.getWidth();
        double height = skillObject.getHeight();
        if (skillObject.isFromOther(this.player1) && (inSelf(x, y) || inSelf(x + width, y) || inSelf(x, y + height) || inSelf(x + width, y + height))) {
            boolean miss = (new Random().nextInt(10) < (int) this.getAgility());
            if (miss) {
                AssetLoader.playOtherAudio("miss");
                GameController.newEffectObject(new MissLabel(this));
            } else {
                skillObject.playHitMedia();
                this.setHealth(this.getHealth() + Math.min(this.getDefense() - skillObject.getDamage(), 0));
                this.statusTimers.getTimer(StatusName.HURT).restart();
                skillObject.getStatusList().forEach(statusName -> this.statusTimers.restart(statusName, skillObject));
            }
            return true;
        }
        return false;
    }

    private boolean inSelf(double x, double y) {
        return (x >= this.getX() && x <= this.getX() + this.getWidth() && y >= this.getY() && y <= this.getY() + this.getHeight());
    }

    public void updateEffect() {
        this.imageView.setEffect(null);
        this.setSpeed(this.getMaxSpeed());
        this.setAgility(this.getMaxAgility());
        this.attackTimers.timing();
        this.statusTimers.doAllByTime();
    }

    public abstract void attack();

    public abstract void skill1();

    public abstract void skill2();

    public abstract void skill3();

    public abstract void skill4();

    public void debug() {

    }
}
