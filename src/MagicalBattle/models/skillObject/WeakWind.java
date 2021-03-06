package MagicalBattle.models.skillObject;

import MagicalBattle.models.career.Player;
import MagicalBattle.constants.Settings;
import MagicalBattle.models.enums.Status;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

import java.util.ArrayList;
import java.util.Objects;

public class WeakWind extends SkillObject {

    public WeakWind(Player player, boolean isLeft, boolean fromPlayer1) {
        Image left = new Image(Objects.requireNonNull(getClass().getResource(assetsFilePath + "attack/left/weak_wind.png")).toExternalForm());
        Image right = new Image(Objects.requireNonNull(getClass().getResource(assetsFilePath + "attack/right/weak_wind.png")).toExternalForm());
        this.fireMedia = new AudioClip(Objects.requireNonNull(getClass().getResource(assetsFilePath + "media/fire/weak_wind.mp3")).toExternalForm());
        this.hitMedia = new AudioClip(Objects.requireNonNull(getClass().getResource(assetsFilePath + "media/hit/weak_wind.mp3")).toExternalForm());
        this.imageView = new ImageView();
        this.imageView.setImage(isLeft ? left : right);
        this.imageView.setLayoutX(player.getX() + (isLeft ? -this.getWidth() : player.getWidth()));
        this.imageView.setLayoutY(player.getY() + (player.getHeight() - this.getHeight()) / 2);
        this.statusList = new ArrayList<>() {
            {
                add(Status.STUNNED);
            }
        };
        this.damage = player.getAttack() * 0.6;
        this.fromPlayer1 = fromPlayer1;
        this.attackBoth = false;
        this.gravity = false;
        this.velocityX = Settings.MAGE_ATTACK_VELOCITY * (isLeft ? -1 : 1);
        this.velocityY = 0;
    }

    @Override
    public void doByTime() {

    }
}
