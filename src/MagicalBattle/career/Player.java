package MagicalBattle.career;

import MagicalBattle.skillObject.SkillObject;
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
import MagicalBattle.enums.Career;
import MagicalBattle.enums.HDirection;
import MagicalBattle.enums.VDirection;
import MagicalBattle.constants.CareerSettings;
import MagicalBattle.constants.Colors;
import MagicalBattle.constants.Settings;
import MagicalBattle.controllers.GameController;
import MagicalBattle.models.*;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public abstract class Player {
    private final HashMap<Career, AbilityValue> abilityMap = CareerSettings.abilityMap;
    private final HashMap<Career, ImageSet> imageSetMap = CareerSettings.imageSetMap;

    protected ImageView self;
    protected Career career;
    protected boolean isPlayer1;
    protected final Timer timer = new Timer();
    protected boolean flippingNeeded;

    protected AbilityValue currentValue;
    protected double moveDistance;
    protected double velocity = 0.0;
    protected VDirection vDirection = VDirection.NULL;
    protected HDirection hDirection = HDirection.NULL;
    protected int jumpCount;
    protected boolean attacking = false;
    protected boolean stunned = false;
    protected boolean dizzy = false;
    protected HDirection knockBackFromRight = HDirection.NULL;

    public void initialize(ImageView imageView, Career career, boolean isPlayer1) {
        this.self = imageView;
        this.self.setImage(isPlayer1 ? imageSetMap.get(career).getRight(1) : imageSetMap.get(career).getLeft(1));
        this.career = career;
        this.currentValue = new AbilityValue(abilityMap.get(this.career));
        this.isPlayer1 = isPlayer1;
        this.jumpCount = (career == Career.ASSASSIN ? 2 : 1);
        this.flippingNeeded = isPlayer1;
    }

    public Career getCareer() {
        return this.career;
    }

    public Timer getTimer() {
        return this.timer;
    }

    public boolean isDead() {
        return this.currentValue.getHealth() == 0;
    }

    public void setWinnerImage(int counter) {
        Image image = imageSetMap.get(this.career).getPrepareOrSelect(counter, true);
        this.self.setImage(image);
        this.self.setFitHeight(image.getHeight());
        if (this.flippingNeeded) this.self.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
    }

    public void setLoserImage() {
        Image image = imageSetMap.get(this.career).getDead();
        this.self.setImage(image);
        this.self.setFitHeight(image.getHeight());
        if (this.flippingNeeded) this.self.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
    }

    public double getWidth() {
        return this.self.getImage().getWidth();
    }

    public double getHeight() {
        return this.self.getImage().getHeight();
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
        return this.currentValue.getHealth() / abilityMap.get(this.career).getHealth();
    }

    public double getMagicRate() {
        return this.currentValue.getMagic() / abilityMap.get(this.career).getMagic();
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
        return CareerSettings.abilityMap.get(this.career).getHealth();
    }

    public double getMaxMagic() {
        return CareerSettings.abilityMap.get(this.career).getMagic();
    }

    public double getMaxAttack() {
        return CareerSettings.abilityMap.get(this.career).getAttack();
    }

    public double getMaxDefense() {
        return CareerSettings.abilityMap.get(this.career).getDefense();
    }

    public double getMaxSpeed() {
        return CareerSettings.abilityMap.get(this.career).getSpeed();
    }

    public double getMaxAgility() {
        return CareerSettings.abilityMap.get(this.career).getAgility();
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

    public void setVDirection(VDirection vDirection) {
        this.vDirection = vDirection;
    }

    public void setHDirection(HDirection hDirection) {
        this.hDirection = hDirection;
    }

    public void setVelocity() {
        if (this.isUp() && this.jumpCount-- > 0) {
            AudioClip audioClip = new AudioClip(Objects.requireNonNull(getClass().getResource("../assets/media/other/jump.mp3")).toExternalForm());
            audioClip.play();
            this.velocity = -(Settings.INITIAL_VELOCITY + Settings.BONUS_VELOCITY * this.getSpeed()) * (this.stunned || this.dizzy ? 0 : 1);
        } else if (this.isDown()) {
            this.velocity = (Settings.INITIAL_VELOCITY + Settings.BONUS_VELOCITY * this.getSpeed()) * (this.stunned || this.dizzy ? 0 : 1);
        }
        this.vDirection = VDirection.NULL;
    }

    public void doHorizonMotion() {
        this.flippingNeeded = (imageSetMap.get(this.career).indexInLeft(this.self.getImage()) == -1);
        if (this.dizzy) return;
        if (this.isLeft()) {
            int index = imageSetMap.get(this.career).indexInLeft(this.self.getImage());
            if(index == -1) this.self.setImage(imageSetMap.get(this.career).getLeft(1));
            else this.self.setImage(imageSetMap.get(this.career).getLeft(index == 2 ? 0 : ++index));
        } else if (this.isRight()) {
            int index = imageSetMap.get(this.career).indexInRight(this.self.getImage());
            if (index == -1) this.self.setImage(imageSetMap.get(this.career).getRight(1));
            else this.self.setImage(imageSetMap.get(this.career).getRight(index == 2 ? 0 : ++index));
        }
        this.moveDistance = Settings.STEP * this.currentValue.getSpeed() * this.hDirection.getValue() * (this.stunned || this.dizzy ? 0 : 1);
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(Settings.UPDATE_TIME), (event) -> {
            this.setX(this.getX() + this.moveDistance);
            if (this.getX() < 0) {
                this.setX(0);
            } else if (this.getX() > Settings.WIDTH - this.getWidth() + Settings.FIXED_WIDTH) {
                this.setX(Settings.WIDTH - this.getWidth() + Settings.FIXED_WIDTH);
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public void doVerticalMotion() {
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(Settings.UPDATE_TIME), (event) -> {
            if (this.getY() + this.velocity <= Settings.GROUND_HEIGHT - this.getHeight()) {
                this.setY(this.getY() + this.velocity * abilityMap.get(this.career).getSpeed());
                this.velocity += Settings.GRAVITY;
            } else if (this.getY() + this.velocity > Settings.GROUND_HEIGHT - this.getHeight()) {
                this.setY(Settings.GROUND_HEIGHT - this.getHeight());
                this.velocity = 0;
            } else if (this.getY() + this.velocity < 0) {
                this.setY(0);
                this.velocity = 0;
            }
            if (this.getY() == Settings.GROUND_HEIGHT - this.getHeight()) {
                if (this.jumpCount <= (this.career == Career.ASSASSIN ? 1 : 0)) {
                    AudioClip audioClip = new AudioClip(Objects.requireNonNull(getClass().getResource("../assets/media/other/land.mp3")).toExternalForm());
                    audioClip.play();
                }
                this.jumpCount = (this.career == Career.ASSASSIN ? 2 : 1);
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public void setStand() {
        int index = imageSetMap.get(this.career).indexInLeft(this.self.getImage());
        if (index != -1) this.self.setImage(imageSetMap.get(this.career).getLeft(1));
        else this.self.setImage(imageSetMap.get(this.career).getRight(1));
    }

    public boolean isAttacking() {
        return !this.dizzy && this.attacking;
    }

    public boolean isCollidedFromOther(SkillObject skillObject) {
        double x = skillObject.getX();
        double y = skillObject.getY();
        double width = skillObject.getWidth();
        double height = skillObject.getHeight();
        if (skillObject.isFromOther(this.isPlayer1) && (inSelf(x, y) || inSelf(x + width, y) || inSelf(x, y + height) || inSelf(x + width, y + height))) {
            int random = new Random().nextInt(10);
            if (random < (int) this.getAgility()) {
                AudioClip audioClip = new AudioClip(Objects.requireNonNull(getClass().getResource("../assets/media/other/miss.mp3")).toExternalForm());
                audioClip.play();
                GameController.newMissLabel(new MissLabel(this));
            } else {
                skillObject.playHitMedia();
                this.setHealth(this.getHealth() + this.getDefense() - skillObject.getDamage());
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
                    this.timer.setStunnedTimer(Settings.STUNNED_TIME);
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
                if (skillObject.containDizzy()) {
                    this.timer.setDizzyTimer(Settings.DIZZY_TIME);
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

    public void debug() {

    }

}
