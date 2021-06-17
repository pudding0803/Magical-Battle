package MagicalBattle.skillObject;

import MagicalBattle.career.Player;
import MagicalBattle.constants.Settings;
import MagicalBattle.enums.Status;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

import java.util.ArrayList;
import java.util.Objects;

public class Shuriken extends SkillObject {

    public Shuriken(Player player, boolean isLeft, boolean fromPlayer1) {
        Image left = new Image(Objects.requireNonNull(getClass().getResource("../assets/attack/left/shuriken.png")).toExternalForm());
        Image right = new Image(Objects.requireNonNull(getClass().getResource("../assets/attack/right/shuriken.png")).toExternalForm());
        this.fireMedia = new AudioClip(Objects.requireNonNull(getClass().getResource("../assets/media/fire/shuriken.mp3")).toExternalForm());
        this.hitMedia = new AudioClip(Objects.requireNonNull(getClass().getResource("../assets/media/hit/shuriken.mp3")).toExternalForm());
        this.imageView = new ImageView();
        this.imageView.setImage(isLeft ? left : right);
        this.imageView.setLayoutX(player.getX() + (isLeft ? -this.getWidth() : player.getWidth()));
        this.imageView.setLayoutY(player.getY() + (player.getHeight() - this.getHeight()) / 2);
        this.statusList = new ArrayList<>();
        this.damage = player.getAttack();
        this.fromPlayer1 = fromPlayer1;
        this.attackBoth = false;
        this.gravity = false;
        this.velocityX = Settings.ASSASSIN_ATTACK_VELOCITY * (isLeft ? -1 : 1);
        this.velocityY = 0;
    }

    @Override
    public void doByTime() {

    }
}
