package MagicalBattle.career;

import javafx.scene.image.ImageView;
import MagicalBattle.enums.Career;

public class Assassin extends Player {
    public Assassin(ImageView imageView,boolean isPlayer1) {
        initialize(imageView, Career.ASSASSIN, isPlayer1);
    }

    @Override
    public void attack() {

    }
}

