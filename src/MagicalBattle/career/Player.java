package MagicalBattle.career;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import MagicalBattle.Enums.Career;
import MagicalBattle.Enums.HDirection;
import MagicalBattle.Enums.VDirection;
import MagicalBattle.constants.CareerSettings;
import MagicalBattle.constants.Colors;
import MagicalBattle.constants.Settings;
import MagicalBattle.controllers.GameController;
import MagicalBattle.models.*;

import java.util.HashMap;
import java.util.Random;

public abstract class Player {
    private final HashMap<Career, AbilityValue> abilityMap = CareerSettings.abilityMap;
    private final HashMap<Career, ImageSet> imageSetMap = CareerSettings.imageSetMap;

    protected ImageView self;
    protected Career career;
    protected boolean isPlayer1;
    protected final Timer timer = new Timer();

    protected AbilityValue currentValue;
    protected double moveDistance;
    protected double velocity = 0.0;
    protected VDirection vDirection = VDirection.NULL;
    protected HDirection hDirection = HDirection.NULL;
    protected int jumpCount;
    protected boolean attacking = false;
    protected boolean stunned = false;
    protected HDirection knockBackFromRight = HDirection.NULL;

    public void initialize(ImageView imageView, Career career, boolean isPlayer1) {
        this.self = imageView;
        this.self.setImage(isPlayer1 ? imageSetMap.get(career).getRight(1) : imageSetMap.get(career).getLeft(1));
        this.career = career;
        this.currentValue = new AbilityValue(abilityMap.get(this.career));
        this.isPlayer1 = isPlayer1;
        this.jumpCount = (career == Career.ASSASSIN ? 2 : 1);
    }

    public Career getCareer() {
        return this.career;
    }

    public Timer getTimer() {
        return this.timer;
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
        this.currentValue.setHealth(value);
    }

    public void setSpeed(double value) {
        this.currentValue.setSpeed(value);
    }

    public void setAgility(double value) {
        this.currentValue.setAgility(value);
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
        if (this.isUp() && this.jumpCount-- > 0 || this.isDown())
            this.velocity = (Settings.INITIAL_VELOCITY + Settings.BONUS_VELOCITY * this.getSpeed()) * this.vDirection.getValue() * (stunned ? 0 : 1);
        this.vDirection = VDirection.NULL;
    }

    public void doHorizonMotion() {
        if (this.isLeft()) {
            int index = imageSetMap.get(this.career).indexInLeft(this.self.getImage());
            if(index == -1) this.self.setImage(imageSetMap.get(this.career).getLeft(1));
            else this.self.setImage(imageSetMap.get(this.career).getLeft(index == 2 ? 0 : ++index));
        } else if (this.isRight()) {
            int index = imageSetMap.get(this.career).indexInRight(this.self.getImage());
            if (index == -1) this.self.setImage(imageSetMap.get(this.career).getRight(1));
            else this.self.setImage(imageSetMap.get(this.career).getRight(index == 2 ? 0 : ++index));
        }
        this.moveDistance = Settings.STEP * this.currentValue.getSpeed() * this.hDirection.getValue() * (stunned ? 0 : 1);
        if (this.getX() - this.moveDistance < 0) {
            this.setX(0);
            return;
        } else if (this.getX() + this.moveDistance > Settings.WIDTH - this.self.getImage().getWidth()) {
            this.setX(Settings.WIDTH - this.getWidth());
            return;
        }
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(Settings.UPDATE_TIME), (event) ->
                this.self.setLayoutX(this.getX() + this.moveDistance));
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public void doVerticalMotion() {
        if (this.getY() + this.velocity > Settings.GROUND_HEIGHT) {
            this.self.setLayoutY(Settings.GROUND_HEIGHT);
            this.velocity = 0.0;
        } else if (this.getY() + this.velocity < 0) {
            this.self.setLayoutY(0);
            this.velocity = 0.0;
        }
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(Settings.UPDATE_TIME), (event) -> {
            this.setY(this.getY() + this.velocity * abilityMap.get(this.career).getSpeed());
            this.velocity += Settings.GRAVITY;
            if (this.getY() == Settings.GROUND_HEIGHT) {
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
        return this.attacking;
    }

    public boolean isCollidedFromOther(SkillObject skillObject) {
        double x = skillObject.getX();
        double y = skillObject.getY();
        double width = skillObject.getWidth();
        double height = skillObject.getHeight();
        if (skillObject.isFromOther(this.isPlayer1) && (inSelf(x, y) || inSelf(x + width, y) || inSelf(x, y + height) || inSelf(x + width, y + height))) {
            int random = new Random().nextInt(10);
            if (random < (int) this.getAgility()) {
                GameController.newMissLabel(new MissLabel(this));
            } else {
                this.setHealth(this.getHealth() + this.getDefense() - skillObject.getDamage());
                if (skillObject.containFrozen()) {
                    this.timer.setFrozenTimer(this.timer.isBurnedTimerEnd() ? Settings.FROZEN_TIME : 0);
                    this.timer.setBurnedTimer(0);
                }
                if (skillObject.containBurned()) {
                    this.timer.setBurnedTimer(this.timer.isFrozenTimerEnd() ? Settings.BURNED_TIME : 0);
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
        if (!this.timer.isFrozenTimerEnd()) {
            Lighting lighting = new Lighting();
            lighting.setLight(new Light.Distant(0, 45, Colors.frozenColor));
            this.self.setEffect(lighting);
            this.setSpeed(this.getMaxSpeed() * Settings.FROZEN_SPEED_EFFECT);
            this.setAgility(this.getMaxAgility() - Settings.FROZEN_AGILITY_EFFECT);
        }
        if (!this.timer.isBurnedTimerEnd()) {
            Lighting lighting = new Lighting();
            if(this.timer.getBurnedTimer() % 50 == 0) this.setHealth(this.getHealth() - Settings.BURNED_DAMAGE);
            if (this.timer.getBurnedTimer() % 50 >= 0 && this.timer.getBurnedTimer() % 50 <= 3) {
                lighting.setLight(new Light.Distant(0, 45, Color.RED));
            } else {
                lighting.setLight(new Light.Distant(0, 45, Colors.burnedColor));
            }
            this.self.setEffect(lighting);
        }
        if (!this.timer.isKnockBackTimerEnd()) {
            double knockBackMovement = (this.knockBackFromRight == HDirection.RIGHT ? -1 : 1) * Settings.KNOCK_BACK_PER_DISTANCE;
            if (this.getX() + knockBackMovement <= 0 || this.getX() + knockBackMovement >= Settings.WIDTH - this.getWidth()) return;
            this.setX(this.getX() + knockBackMovement);
        }
    }

    public abstract void attack();

    public void debug() {
        System.out.println(this.getHealth());
        System.out.println(this.getMaxHealth());
        System.out.println(this.getHealthRate());
    }

}
