package MagicalBattle.models.career;

import javafx.scene.image.ImageView;
import MagicalBattle.models.enums.Career;

public class Guardian extends Player {
    public Guardian(ImageView imageView,boolean isPlayer1) {
        initialize(imageView, Career.GUARDIAN, isPlayer1);
    }

    @Override
    public void attack() {

    }
}
