package MagicalBattle.effectObject;

import MagicalBattle.career.Player;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class DizzyEffect extends EffectObject {
    private final Image image = new Image(Objects.requireNonNull(getClass().getResource("../assets/attack/effect/dizzy.png")).toExternalForm());
    private final ImageView imageView = new ImageView(image);

    public DizzyEffect(Player player) {
        super(player);
        this.imageView.setLayoutX(player.getX() + (player.getWidth() - this.getWidth()) / 2);
        this.imageView.setLayoutY(player.getY() - this.getHeight());
    }

    public double getWidth() {
        return this.imageView.getImage().getWidth();
    }

    public double getHeight() {
        return this.imageView.getImage().getHeight();
    }

    @Override
    public Node getEffect() {
        return this.imageView;
    }

    @Override
    public void doByTime() {
        this.imageView.setLayoutX(player.getX() + (player.getWidth() - this.getWidth()) / 2);
        this.imageView.setLayoutY(player.getY() - this.getHeight());
    }

    @Override
    public boolean isFinished() {
        return this.player.getTimer().isDizzyTimerEnd();
    }
}
