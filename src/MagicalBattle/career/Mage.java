package MagicalBattle.career;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import MagicalBattle.Enums.Career;
import MagicalBattle.Enums.Status;
import MagicalBattle.constants.Settings;
import MagicalBattle.models.SkillObject;
import MagicalBattle.constants.CareerSettings;
import MagicalBattle.controllers.GameController;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Mage extends Player {
    private int attackCounter = 0;
    private final ArrayList<Image> attackLeft = new ArrayList<>() {
        {
            for (int i = 0; i < 5; i++)
                add(new Image(Objects.requireNonNull(getClass().getResource("../assets/Mage/attackLeft/" + i + ".png")).toString()));
        }
    };
    private final ArrayList<Image> attackRight = new ArrayList<>() {
        {
            for (int i = 0; i < 5; i++)
                add(new Image(Objects.requireNonNull(getClass().getResource("../assets/Mage/attackRight/" + i + ".png")).toString()));
        }
    };

    public Mage(ImageView imageView, boolean isPlayer1) {
        initialize(imageView, Career.MAGE, isPlayer1);
    }

    @Override
    public void attack() {
        this.timer.setAttackTimer(Settings.MAGE_ATTACK_TIME);
        int index = CareerSettings.imageSetMap.get(this.career).indexInLeft(this.self.getImage());
        ImageView attack;
        if (index != -1) attack = new ImageView(this.attackLeft.get(this.attackCounter));
        else attack = new ImageView(this.attackRight.get(this.attackCounter));
        attack.setLayoutX(this.getX() + (index != -1 ? -attack.getImage().getWidth() : this.getWidth()));
        attack.setLayoutY(this.getY() + (this.getHeight() - attack.getImage().getHeight()) / 2);
        ArrayList<Status> statusList = new ArrayList<>();
        double damage = 0;
        switch (this.attackCounter) {
            case 0 -> {
                statusList.add(Status.FROZEN);
                this.attackCounter += (new Random().nextInt(2) == 0 ? 1 : 2);
            }
            case 1 -> {
                statusList.add(Status.STUNNED);
                damage = this.getAttack() * 0.6;
                this.attackCounter += 2;
            }
            case 2 -> {
                statusList.add(Status.KNOCKED_BACK);
                damage = this.getAttack() * 1.4;
                this.attackCounter++;
            }
            case 3 -> {
                statusList.add(Status.BURNED);
                damage = this.getAttack();
                this.attackCounter++;
            }
            case 4 -> {
                statusList.add(Status.KNOCKED_UP);
                damage = this.getAttack();
                this.attackCounter = 0;
            }
        }
        SkillObject skillObject = new SkillObject(attack, statusList, damage, this.isPlayer1, (index != -1 ? -10 : 10), 0);
        GameController.newSkillObject(skillObject);
    }
}
