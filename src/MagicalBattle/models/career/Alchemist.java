package MagicalBattle.models.career;

import javafx.scene.image.ImageView;
import MagicalBattle.models.enums.Career;

public class Alchemist extends Player {
    public Alchemist(ImageView imageView,boolean isPlayer1) {
        initialize(imageView, Career.ALCHEMIST, isPlayer1);
    }

    @Override
    public void attack() {

    }
}
