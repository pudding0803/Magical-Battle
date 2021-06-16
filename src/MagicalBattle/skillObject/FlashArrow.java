package MagicalBattle.skillObject;

import MagicalBattle.career.Player;
import MagicalBattle.enums.Status;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import java.util.ArrayList;
import java.util.Objects;

public class FlashArrow extends Arrow {

    public FlashArrow(Player player, boolean isLeft, boolean fromPlayer1) {
        super(player, isLeft, fromPlayer1);
        this.fireMedia = new AudioClip(Objects.requireNonNull(getClass().getResource("../assets/media/fire/flash_arrow.mp3")).toExternalForm());
        this.left = new Image(Objects.requireNonNull(getClass().getResource("../assets/attack/left/flash_arrow.png")).toExternalForm());
        this.right = new Image(Objects.requireNonNull(getClass().getResource("../assets/attack/right/flash_arrow.png")).toExternalForm());
        this.imageView.setImage(isLeft ? left : right);
        this.statusList = new ArrayList<>() {
            {
                add(Status.STUNNED);
            }
        };
    }

}
