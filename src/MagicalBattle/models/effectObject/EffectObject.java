package MagicalBattle.models.effectObject;

import MagicalBattle.models.career.Player;
import javafx.scene.Node;

public abstract class EffectObject {
    protected static final String assetsFilePath = "../../assets/";

    protected Player player;

    public EffectObject(Player player) {
        this.player = player;
    }

    public abstract Node getEffect();
    public abstract void doByTime();
    public abstract boolean isFinished();
}
