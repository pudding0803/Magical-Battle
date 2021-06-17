package MagicalBattle.skillObject;

import MagicalBattle.career.Player;
import MagicalBattle.constants.Settings;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Arrow extends SkillObject {
    protected Image left;
    protected Image right;

    public Arrow(Player player, boolean isLeft, boolean fromPlayer1) {
        this.left = new Image(Objects.requireNonNull(getClass().getResource("../assets/attack/left/arrow.png")).toExternalForm());
        this.right = new Image(Objects.requireNonNull(getClass().getResource("../assets/attack/right/arrow.png")).toExternalForm());
        this.fireMedia = new AudioClip(Objects.requireNonNull(getClass().getResource("../assets/media/fire/arrow.mp3")).toExternalForm());
        this.hitMedia = new AudioClip(Objects.requireNonNull(getClass().getResource("../assets/media/hit/arrow.mp3")).toExternalForm());
        int fixedPositionY = new Random().nextInt(51) - 25;
        int fixedVelocityX = new Random().nextInt(7) - 2;
        this.imageView = new ImageView();
        this.imageView.setImage(isLeft ? left : right);
        this.imageView.setLayoutX(player.getX() + (isLeft ? -this.getWidth() : player.getWidth()));
        this.imageView.setLayoutY(player.getY() + (player.getHeight() - this.getHeight()) / 2 + fixedPositionY);
        this.statusList = new ArrayList<>();
        this.damage = player.getAttack() * 0.22;
        this.fromPlayer1 = fromPlayer1;
        this.attackBoth = false;
        this.gravity = false;
        this.velocityX = (Settings.ARCHER_ATTACK_VELOCITY + fixedVelocityX) * (isLeft ? -1 : 1);
        this.velocityY = 0;
    }

    @Override
    public void doByTime() {
        this.damage += 0.21;
    }
}
