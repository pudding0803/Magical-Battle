package MagicalBattle.career;

import MagicalBattle.constants.CareerSettings;
import MagicalBattle.constants.Settings;
import MagicalBattle.controllers.GameController;
import MagicalBattle.enums.Status;
import MagicalBattle.models.SkillObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import MagicalBattle.enums.Career;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Archer extends Player {
    public Archer(ImageView imageView,boolean isPlayer1) {
        initialize(imageView, Career.ARCHER, isPlayer1);
    }

    private final ArrayList<Image> attackLeft = new ArrayList<>() {
        {
            for (int i = 0; i < 2; i++)
                add(new Image(Objects.requireNonNull(getClass().getResource("../assets/Archer/attackLeft/" + i + ".png")).toString()));
        }
    };
    private final ArrayList<Image> attackRight = new ArrayList<>() {
        {
            for (int i = 0; i < 2; i++)
                add(new Image(Objects.requireNonNull(getClass().getResource("../assets/Archer/attackRight/" + i + ".png")).toString()));
        }
    };

    @Override
    public void attack() {
        this.timer.setAttackTimer(Settings.ARCHER_ATTACK_TIME);
        int arrowsNumber = new Random().nextInt(8) + 6;
        for (int i = 0; i < arrowsNumber; i++) {
            int imageIndex = (new Random().nextInt(6) == 0 ? 1 : 0);
            int fixedPositionX = new Random().nextInt(41) - 20;
            int fixedPositionY = new Random().nextInt(61) - 30;
            int fixedVelocityX = new Random().nextInt(10);
            int index = CareerSettings.imageSetMap.get(this.career).indexInLeft(this.self.getImage());
            ImageView attack = new ImageView();
            attack.setImage(index == -1 ? this.attackLeft.get(imageIndex) : this.attackRight.get(imageIndex));
            attack.setLayoutX(this.getX() + (index != -1 ? -attack.getImage().getWidth() : this.getWidth()) + fixedPositionX);
            attack.setLayoutY(this.getY() + (this.getHeight() - attack.getImage().getHeight()) / 2 + fixedPositionY);
            ArrayList<Status> statusList = new ArrayList<>();
            if (imageIndex == 1) statusList.add(Status.BURNED);
            double damage = this.getAttack() * 0.24;
            SkillObject skillObject = new SkillObject(attack, statusList, damage, this.isPlayer1, (Settings.ATTACK_VELOCITY + fixedVelocityX ) * (index != -1 ? -1 : 1), 0);
            GameController.newSkillObject(skillObject);
        }
    }
}
