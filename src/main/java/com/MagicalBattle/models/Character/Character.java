package com.MagicalBattle.models.Character;

import com.MagicalBattle.constants.Colors;
import com.MagicalBattle.constants.Settings;
import com.MagicalBattle.controllers.GameController;
import com.MagicalBattle.loaders.AbilityLoader;
import com.MagicalBattle.loaders.AssetLoader;
import com.MagicalBattle.models.AbilitySet;
import com.MagicalBattle.models.EffectObject.DizzyEffect;
import com.MagicalBattle.models.EffectObject.MissLabel;
import com.MagicalBattle.models.EffectObject.StunnedEffect;
import com.MagicalBattle.models.Enums.CharacterClass;
import com.MagicalBattle.models.Enums.HDirection;
import com.MagicalBattle.models.Enums.VDirection;
import com.MagicalBattle.models.SkillObject.SkillObject;
import com.MagicalBattle.models.Timer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.NodeOrientation;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Random;

public abstract class Character {
    protected ImageView self;
    protected CharacterClass characterClass;
    protected boolean player1;
    protected final Timer timer = new Timer();

    protected AbilitySet currentValue;
    protected double moveDistance;
    protected double velocity = 0.0;
    protected VDirection vDirection = VDirection.NULL;
    protected HDirection hDirection = HDirection.NULL;
    protected HDirection facing;
    protected int walkingIndex = 0;
    protected int jumpCount = 0;
    protected boolean attacking = false;
    protected boolean stunned = false;
    protected boolean dizzy = false;
    protected HDirection knockBackFromRight = HDirection.NULL;

    protected int maxJumpCount = 1;

    public Character(ImageView imageView, CharacterClass characterClass, boolean player1) {
        this.self = imageView;
        this.self.setImage(AssetLoader.getCharacterImageSet(characterClass).getIdle(0));
        this.facing = (player1 ? HDirection.RIGHT : HDirection.LEFT);
        this.characterClass = characterClass;
        this.currentValue = new AbilitySet(AbilityLoader.getAbilityValue(this.characterClass));
        this.player1 = player1;
    }

    public CharacterClass getCharacter() {
        return this.characterClass;
    }

    public boolean isPlayer1() {
        return this.player1;
    }

    public Timer getTimer() {
        return this.timer;
    }

    public boolean isDead() {
        return this.currentValue.getHealth() == 0;
    }

    public void setGameOverImage(boolean winner, int counter) {
        Image image;
        if (winner) image = AssetLoader.getCharacterImageSet(this.characterClass).getPreparingOrSelect(counter, true);
        else image = AssetLoader.getCharacterImageSet(this.characterClass).getDead();

        this.self.setImage(image);
        this.self.setFitHeight(image.getHeight());
        if (!this.isFacingLeft()) this.self.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        if (this.getX() < 0) {
            this.setX(0);
        } else if (this.getX() > Settings.WIDTH - this.getWidth()) {
            this.setX(Settings.WIDTH - this.getWidth());
        }
    }

    public double getWidth() {
        return this.self.getImage().getWidth() * Settings.PLAYER_SIZE_RATE;
    }

    public double getHeight() {
        return this.self.getImage().getHeight() * Settings.PLAYER_SIZE_RATE;
    }

    public double getX() {
        return this.self.getLayoutX();
    }

    public double getY() {
        return this.self.getLayoutY();
    }

    public void setX(double value) {
        this.self.setLayoutX(value);
    }

    public void setY(double value) {
        this.self.setLayoutY(value);
    }

    public double getHealthRate() {
        return this.currentValue.getHealth() / AbilityLoader.getAbilityValue(this.characterClass).getHealth();
    }

    public double getMagicRate() {
        return this.currentValue.getMagic() / AbilityLoader.getAbilityValue(this.characterClass).getMagic();
    }

    public double getHealth() {
        return this.currentValue.getHealth();
    }

    public double getMagic() {
        return this.currentValue.getMagic();
    }

    public double getAttack() {
        return this.currentValue.getAttack();
    }

    public double getDefense() {
        return this.currentValue.getDefense();
    }

    public double getSpeed() {
        return this.currentValue.getSpeed();
    }

    public double getAgility() {
        return this.currentValue.getAgility();
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
        this.currentValue.setHealth(Math.max(0, value));
    }

    public void setSpeed(double value) {
        this.currentValue.setSpeed(Math.max(0, value));
    }

    public void setAgility(double value) {
        this.currentValue.setAgility(Math.max(0, value));
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

    public void setVDirection(VDirection vDirection) {
        this.vDirection = vDirection;
    }

    public void setHDirection(HDirection hDirection) {
        this.hDirection = hDirection;
        if (hDirection != HDirection.NULL) this.facing = hDirection;
    }

    public void setVelocity() {
        if (this.isUp() && this.jumpCount++ < this.maxJumpCount) {
            AudioClip audioClip = AssetLoader.getOtherAudio("jump");
            audioClip.setVolume(Settings.EFFECT_VOLUME);
            audioClip.play();
            this.velocity = -(Settings.INITIAL_VELOCITY + Settings.BONUS_VELOCITY * this.getSpeed()) * (this.stunned || this.dizzy ? 0 : 1);
        } else if (this.isDown()) {
            this.velocity = (Settings.INITIAL_VELOCITY + Settings.BONUS_VELOCITY * this.getSpeed()) * (this.stunned || this.dizzy ? 0 : 1);
        }
        this.vDirection = VDirection.NULL;
    }

    public void doHorizonMotion() {
        if (this.dizzy) return;
        this.self.setImage(AssetLoader.getCharacterImageSet(this.characterClass).getWalking(walkingIndex));
        this.self.setNodeOrientation(this.isFacingLeft() ? NodeOrientation.RIGHT_TO_LEFT : NodeOrientation.LEFT_TO_RIGHT);
        walkingIndex = (++walkingIndex) % 2;
        this.moveDistance = Settings.STEP * this.currentValue.getSpeed() * this.hDirection.getValue() * (this.stunned || this.dizzy ? 0 : 1);
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
                this.setY(this.getY() + this.velocity * (this.getSpeed() + this.getMaxSpeed() * 3) / 4 );
                this.velocity += Settings.GRAVITY;
            } else if (this.getY() + this.velocity > Settings.GROUND_HEIGHT - this.getHeight()) {
                this.setY(Settings.GROUND_HEIGHT - this.getHeight());
                this.velocity = 0;
            } else if (this.getY() + this.velocity < 0) {
                this.setY(0);
                this.velocity = 0;
            }
            if (this.getY() == Settings.GROUND_HEIGHT - this.getHeight()) {
                if (this.jumpCount > 0) {
                    AudioClip audioClip = AssetLoader.getOtherAudio("land");
                    audioClip.setVolume(Settings.EFFECT_VOLUME);
                    audioClip.play();
                }
                this.jumpCount = 0;
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public void setStand() {
        this.self.setImage(AssetLoader.getCharacterImageSet(this.characterClass).getIdle(0));
        this.self.setNodeOrientation(this.isFacingLeft() ? NodeOrientation.RIGHT_TO_LEFT : NodeOrientation.LEFT_TO_RIGHT);
    }

    public boolean isAttacking() {
        return !this.dizzy && this.attacking;
    }

    public boolean isCollidedFromOther(SkillObject skillObject) {
        double x = skillObject.getX();
        double y = skillObject.getY();
        double width = skillObject.getWidth();
        double height = skillObject.getHeight();
        if (skillObject.isFromOther(this.player1) && (inSelf(x, y) || inSelf(x + width, y) || inSelf(x, y + height) || inSelf(x + width, y + height))) {
            boolean miss = (new Random().nextInt(10) < (int) this.getAgility());
            if (miss) {
                AudioClip audioClip = AssetLoader.getOtherAudio("miss");
                audioClip.setVolume(Settings.EFFECT_VOLUME);
                audioClip.play();
                GameController.newEffectObject(new MissLabel(this));
            } else {
                skillObject.playHitMedia();
                this.setHealth(this.getHealth() + Math.min(this.getDefense() - skillObject.getDamage(), 0));
                this.timer.setHurtTimer(Settings.HURT_TIME);
                if (skillObject.containFrozen()) {
                    this.timer.setFrozenTimer(Settings.FROZEN_TIME);
                    this.timer.setBurnedTimer(0);
                }
                if (skillObject.containBurned()) {
                    this.timer.setBurnedTimer(Settings.BURNED_TIME);
                    this.timer.setFrozenTimer(0);
                }
                if (skillObject.containStunned()) {
                    AudioClip audioClip = AssetLoader.getEffectAudio("stunned");
                    audioClip.setVolume(Settings.EFFECT_VOLUME);
                    audioClip.play();
                    this.timer.setStunnedTimer(Settings.STUNNED_TIME);
                    GameController.newEffectObject(new StunnedEffect(this));
                }
                if (skillObject.containDizzy()) {
                    AudioClip audioClip = AssetLoader.getEffectAudio("dizzy");
                    audioClip.setVolume(Settings.EFFECT_VOLUME);
                    audioClip.play();
                    this.timer.setDizzyTimer(Settings.DIZZY_TIME);
                    GameController.newEffectObject(new DizzyEffect(this));
                }
                if (skillObject.containKnockBack()) {
                    this.timer.setStunnedTimer(Settings.STUNNED_TIME);
                    this.timer.setKnockBackTimer(Settings.KNOCK_BACK_TIME);
                    this.knockBackFromRight = (skillObject.getVelocityX() > 0 ? HDirection.LEFT : HDirection.RIGHT);
                }
                if (skillObject.containKnockUp()) {
                    this.timer.setStunnedTimer(Settings.STUNNED_TIME);
                    this.velocity = -Settings.KNOCK_UP_VELOCITY;
                    this.jumpCount = 0;
                }
            }
            return true;
        }
        return false;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    private boolean inSelf(double x, double y) {
        return (x >= this.getX() && x <= this.getX() + this.getWidth() && y >= this.getY() && y <= this.getY() + this.getHeight());
    }

    public void updateEffect() {
        this.self.setEffect(null);
        this.setSpeed(this.getMaxSpeed());
        this.setAgility(this.getMaxAgility());
        this.stunned = !this.timer.isStunnedTimerEnd();
        this.dizzy = !this.timer.isDizzyTimerEnd();
        if (!this.timer.isHurtTimerEnd()) {
            Lighting lighting = new Lighting();
            lighting.setLight(new Light.Distant(0, 45, Color.RED));
            this.self.setEffect(lighting);
        }
        if (!this.timer.isFrozenTimerEnd()) {
            if (this.timer.isHurtTimerEnd()) {
                Lighting lighting = new Lighting();
                lighting.setLight(new Light.Distant(0, 45, Colors.frozenColor));
                this.self.setEffect(lighting);
            }
            this.setSpeed(this.getMaxSpeed() * Settings.FROZEN_SPEED_EFFECT);
            this.setAgility(Math.max(this.getMaxAgility() - Settings.FROZEN_AGILITY_EFFECT, 0));
        }
        if (!this.timer.isBurnedTimerEnd()) {
            if(this.timer.getBurnedTimer() % 50 == 0) {
                AudioClip audioClip = AssetLoader.getEffectAudio("burned");
                audioClip.setVolume(Settings.EFFECT_VOLUME);
                audioClip.play();
                this.setHealth(this.getHealth() - Settings.BURNED_DAMAGE);
                this.timer.setHurtTimer(Settings.HURT_TIME);
            }
            if (this.timer.isHurtTimerEnd()) {
                Lighting lighting = new Lighting();
                lighting.setLight(new Light.Distant(0, 45, Colors.burnedColor));
                this.self.setEffect(lighting);
            }
        }
        if (!this.timer.isKnockBackTimerEnd()) {
            double knockBackMovement = (this.knockBackFromRight == HDirection.RIGHT ? -1 : 1) * Settings.KNOCK_BACK_PER_DISTANCE;
            if (this.getX() + knockBackMovement <= 0 || this.getX() + knockBackMovement >= Settings.WIDTH - this.getWidth()) return;
            this.setX(this.getX() + knockBackMovement);
        }
    }

    public abstract void attack();

    public abstract void skill1();

    public abstract void skill2();

    public void debug() {
    }
}
