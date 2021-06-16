package MagicalBattle.career;

import MagicalBattle.skillObject.*;
import javafx.scene.image.ImageView;
import MagicalBattle.enums.Career;
import MagicalBattle.constants.Settings;
import MagicalBattle.constants.CareerSettings;
import MagicalBattle.controllers.GameController;

import java.util.Random;

public class Mage extends Player {
    private int attackCounter = 0;

    public Mage(ImageView imageView, boolean isPlayer1) {
        this.initialize(imageView, Career.MAGE, isPlayer1);
    }

    @Override
    public void attack() {
        this.timer.setAttackTimer(Settings.MAGE_ATTACK_TIME);
        boolean isLeft = (CareerSettings.imageSetMap.get(this.career).indexInLeft(this.self.getImage()) != -1);
        SkillObject skillObject;
        switch (this.attackCounter) {
            case 0 -> {
                skillObject = new Ice(this, isLeft, this.isPlayer1);
                this.attackCounter += (new Random().nextInt(2) == 0 ? 1 : 2);
            }
            case 1 -> {
              skillObject = new WeakWind(this, isLeft, this.isPlayer1);
              this.attackCounter += 2;
            }
            case 2 -> {
                skillObject = new StrongWind(this, isLeft, this.isPlayer1);
                this.attackCounter++;
            }
            case 3 -> {
                skillObject = new Fire(this, isLeft, this.isPlayer1);
                this.attackCounter++;
            }
            case 4 -> {
                skillObject = new Earth(this, isLeft, this.isPlayer1);
                this.attackCounter = 0;
            }
            default -> throw new IllegalStateException("Unexpected value: " + this.attackCounter);
        }
        skillObject.playFireMedia();
        GameController.newSkillObject(skillObject);
    }
}
