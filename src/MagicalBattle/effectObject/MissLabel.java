package MagicalBattle.effectObject;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import MagicalBattle.career.Player;
import MagicalBattle.constants.Settings;

public class MissLabel extends EffectObject {
    private final Label miss = new Label("MISS");
    private final double initialY;

    public MissLabel(Player player) {
        super(player);
        this.miss.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, Settings.MISS_SIZE));
        this.miss.setTextFill(Color.web("#00ff70"));
        this.miss.setAlignment(Pos.CENTER);
        this.miss.setPrefWidth(Settings.MISS_WIDTH);
        this.miss.setPrefHeight(Settings.MISS_HEIGHT);
        this.miss.setLayoutX(player.getX() + (player.getWidth() - this.miss.getPrefWidth()) / 2);
        this.miss.setLayoutY(player.getY() - this.miss.getPrefHeight());
        this.initialY = this.miss.getLayoutY();
    }

    public double getY() {
        return this.miss.getLayoutY();
    }

    public void setY(double value) {
        this.miss.setLayoutY(value);
    }

    @Override
    public Node getEffect() {
        return this.miss;
    }

    @Override
    public void doByTime() {
        this.setY(this.getY() - Settings.MISS_PER_DISTANCE);
    }

    @Override
    public boolean isFinished() {
        return this.miss.getLayoutY() - this.initialY <= Settings.MISS_TOTAL_DISTANCE;
    }
}
