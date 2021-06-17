package MagicalBattle.career;

import MagicalBattle.constants.CareerSettings;
import MagicalBattle.constants.Settings;
import MagicalBattle.controllers.GameController;
import MagicalBattle.skillObject.*;
import javafx.scene.image.ImageView;
import MagicalBattle.enums.Career;

import java.util.Random;

public class Assassin extends Player {
    public Assassin(ImageView imageView,boolean isPlayer1) {
        initialize(imageView, Career.ASSASSIN, isPlayer1);
    }

    @Override
    public void attack() {
        this.timer.setAttackTimer(Settings.ASSASSIN_ATTACK_TIME);
        int attackIndex = new Random().nextInt(4);
        boolean isLeft = (CareerSettings.imageSetMap.get(this.career).indexInLeft(this.self.getImage()) != -1);
        SkillObject skillObject;
        if (attackIndex == 0) {
            skillObject = new ElectricStar(this, isLeft, this.isPlayer1);
        } else {
            skillObject = new Shuriken(this, isLeft, this.isPlayer1);
        }
        skillObject.playFireMedia();
        GameController.newSkillObject(skillObject);
    }
}

