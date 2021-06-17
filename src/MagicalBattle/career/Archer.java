package MagicalBattle.career;

import MagicalBattle.constants.CareerSettings;
import MagicalBattle.constants.Settings;
import MagicalBattle.controllers.GameController;
import MagicalBattle.enums.Status;
import MagicalBattle.skillObject.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import MagicalBattle.enums.Career;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Archer extends Player {
    public Archer(ImageView imageView, boolean isPlayer1) {
        this.initialize(imageView, Career.ARCHER, isPlayer1);
    }

    @Override
    public void attack() {
        this.timer.setAttackTimer(Settings.ARCHER_ATTACK_TIME);
        int arrowsNumber = new Random().nextInt(3) + 3;
        for (int i = 0; i < arrowsNumber; i++) {
            int attackIndex = new Random().nextInt(6);
            boolean isLeft = (CareerSettings.imageSetMap.get(this.career).indexInLeft(this.self.getImage()) != -1);
            SkillObject skillObject;
            if (attackIndex == 0) {
                skillObject = new FlameArrow(this, isLeft, this.isPlayer1);
            } else if (attackIndex == 1) {
                skillObject = new FlashArrow(this, isLeft, this.isPlayer1);
            } else {
                skillObject = new Arrow(this, isLeft, this.isPlayer1);
            }
            skillObject.playFireMedia();
            GameController.newSkillObject(skillObject);
        }
    }
}
