package MagicalBattle.effectObject;

import MagicalBattle.career.Player;
import javafx.scene.Node;

public abstract class EffectObject {
    protected Player player;

    public EffectObject(Player player) {
        this.player = player;
    }

    public abstract Node getEffect();
    public abstract void doByTime();
    public abstract boolean isFinished();
}
