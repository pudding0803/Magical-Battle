package MagicalBattle.career;

import javafx.scene.image.ImageView;
import MagicalBattle.enums.Career;

public class Warrior extends Player {
    public Warrior(ImageView imageView,boolean isPlayer1) {
        initialize(imageView, Career.WARRIOR, isPlayer1);
    }

    @Override
    public void attack() {

    }
}
