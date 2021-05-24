package MagicalBattle.career;

import javafx.scene.image.ImageView;
import MagicalBattle.Enums.Career;

public class Archer extends Player {
    public Archer(ImageView imageView,boolean isPlayer1) {
        initialize(imageView, Career.ARCHER, isPlayer1);
    }

    @Override
    public void attack() {

    }
}
