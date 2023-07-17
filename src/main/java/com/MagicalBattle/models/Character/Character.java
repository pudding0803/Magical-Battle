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
        return characterClass;
    }

    public boolean isPlayer1() {
        return player1;
    }

    public boolean isDead() {
        return currentAbility.getHealth() == 0;
    }

    public void setGameOverImage(boolean winner, int counter) {
        Image image;
        if (winner) image = AssetLoader.getCharacterImageSet(characterClass).getPreparingOrSelect(counter, true);
        else image = AssetLoader.getCharacterImageSet(characterClass).getDead();

        imageView.setImage(image);
        imageView.setFitHeight(image.getHeight());
        if (!isFacingLeft()) imageView.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

        if (getX() < 0) {
            setX(0);
        } else if (getX() > Settings.WIDTH - getWidth()) {
            setX(Settings.WIDTH - getWidth());
        }
    }

    public double getWidth() {
        return imageView.getImage().getWidth() * Settings.PLAYER_SIZE_RATE;
    }

    public double getHeight() {
        return imageView.getImage().getHeight() * Settings.PLAYER_SIZE_RATE;
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

    public double getHealthRate() {
        return currentAbility.getHealth() / AbilityLoader.getAbilityValue(characterClass).getHealth();
    }

    public double getMagicRate() {
        return currentAbility.getMagic() / AbilityLoader.getAbilityValue(characterClass).getMagic();
    }

    public double getHealth() {
        return currentAbility.getHealth();
    }

    public double getMagic() {
        return currentAbility.getMagic();
    }

    public double getAttack() {
        return currentAbility.getAttack();
    }

    public double getDefense() {
        return currentAbility.getDefense();
    }

    public double getSpeed() {
        return currentAbility.getSpeed();
    }

    public double getAgility() {
        return currentAbility.getAgility();
    }

    public double getMaxHealth() {
        return AbilityLoader.getAbilityValue(characterClass).getHealth();
    }

    public double getMaxMagic() {
        return AbilityLoader.getAbilityValue(characterClass).getMagic();
    }

    public double getMaxAttack() {
        return AbilityLoader.getAbilityValue(characterClass).getAttack();
    }

    public double getMaxDefense() {
        return AbilityLoader.getAbilityValue(characterClass).getDefense();
    }

    public double getMaxSpeed() {
        return AbilityLoader.getAbilityValue(characterClass).getSpeed();
    }

    public double getMaxAgility() {
        return AbilityLoader.getAbilityValue(characterClass).getAgility();
    }

    public void setHealth(double value) {
        currentAbility.setHealth(Math.max(0, value));
    }

    public void setSpeed(double value) {
        currentAbility.setSpeed(Math.max(0, value));
    }

    public void setAgility(double value) {
        currentAbility.setAgility(Math.max(0, value));
    }

    public boolean isUp() {
        return vDirection == VDirection.UP;
    }

    public boolean isDown() {
        return vDirection == VDirection.DOWN;
    }

    public boolean isLeft() {
        return hDirection == HDirection.LEFT;
    }

    public boolean isRight() {
        return hDirection == HDirection.RIGHT;
    }

    public boolean isFacingLeft() {
        return facing == HDirection.LEFT;
    }

    public boolean isOnGround() {
        return getY() + velocity > Settings.GROUND_HEIGHT - getHeight();
    }

    public void setVDirection(VDirection vDirection) {
        this.vDirection = vDirection;
    }

    public void setHDirection(HDirection hDirection) {
        this.hDirection = hDirection;
        if (hDirection != HDirection.NULL) facing = hDirection;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public void updateVelocity() {
        if (isUp() && jumpCount++ < maxJumpCount) {
            AssetLoader.playOtherAudio("jump");
            velocity = -(Settings.INITIAL_VELOCITY + Settings.BONUS_VELOCITY * getSpeed());
        } else if (isDown()) {
            velocity = Settings.INITIAL_VELOCITY + Settings.BONUS_VELOCITY * getSpeed();
        }
        vDirection = VDirection.NULL;
    }

    public void doHorizonMotion() {
        imageView.setImage(AssetLoader.getCharacterImageSet(characterClass).getWalking(walkingIndex));
        imageView.setNodeOrientation(isFacingLeft() ? NodeOrientation.RIGHT_TO_LEFT : NodeOrientation.LEFT_TO_RIGHT);
        walkingIndex = (++walkingIndex) % 2;
        moveDistance = Settings.STEP * currentAbility.getSpeed() * hDirection.getValue();
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(Settings.UPDATE_TIME), (event) -> {
            setX(getX() + moveDistance);
            if (getX() < 0) {
                setX(0);
            } else if (getX() > Settings.WIDTH - getWidth()) {
                setX(Settings.WIDTH - getWidth());
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public void doVerticalMotion() {
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(Settings.UPDATE_TIME), (event) -> {
            if (getY() + velocity <= Settings.GROUND_HEIGHT - getHeight()) {
                setY(getY() + velocity * (getSpeed() + getMaxSpeed() * 3) / 4);
                velocity += Settings.GRAVITY;
            } else if (isOnGround()) {
                setY(Settings.GROUND_HEIGHT - getHeight());
                velocity = 0;
            } else if (getY() + velocity < 0) {
                setY(0);
                velocity = 0;
            }
            if (getY() == Settings.GROUND_HEIGHT - getHeight()) {
                if (jumpCount > 0) {
                    AssetLoader.playOtherAudio("land");
                }
                jumpCount = 0;
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public void setStand() {
        imageView.setImage(AssetLoader.getCharacterImageSet(characterClass).getIdle(0));
        imageView.setNodeOrientation(isFacingLeft() ? NodeOrientation.RIGHT_TO_LEFT : NodeOrientation.LEFT_TO_RIGHT);
    }

    public void resetJumpCount() {
        jumpCount = maxJumpCount;
    }

    public void setEffect(Effect effect) {
        imageView.setEffect(effect);
    }

    public AttackTimers getAttackTimers() {
        return attackTimers;
    }

    public Timer getStatusTimer(StatusName statusName) {
        return statusTimers.getTimer(statusName);
    }

    public void stopStatusTimers() {
        statusTimers.stopAll();
    }

    public boolean isCollidedFromOther(SkillObject skillObject) {
        double x = skillObject.getX();
        double y = skillObject.getY();
        double width = skillObject.getWidth();
        double height = skillObject.getHeight();
        if (skillObject.isFromOther(player1) && (inSelf(x, y) || inSelf(x + width, y) || inSelf(x, y + height) || inSelf(x + width, y + height))) {
            boolean miss = new Random().nextInt(20) < (int) getAgility();
            if (miss) {
                AssetLoader.playOtherAudio("miss");
                GameController.newEffectObject(new MissLabel(this));
            } else {
                skillObject.playHitMedia();
                setHealth(getHealth() + Math.min(getDefense() - skillObject.getDamage(), 0));
                statusTimers.getTimer(StatusName.HURT).restart();
                skillObject.getStatusList().forEach(statusName -> statusTimers.restart(statusName, skillObject));
            }
            return true;
        }
        return false;
    }

    private boolean inSelf(double x, double y) {
        return (x >= getX() && x <= getX() + getWidth() && y >= getY() && y <= getY() + getHeight());
    }

    public void updateEffect() {
        imageView.setEffect(null);
        setSpeed(getMaxSpeed());
        setAgility(getMaxAgility());
        attackTimers.timing();
        statusTimers.doAllByTime();
    }

    public abstract void attack();

    public abstract void skill1();

    public abstract void skill2();

    public abstract void skill3();

    public abstract void skill4();

    public void debug() {

    }
}
