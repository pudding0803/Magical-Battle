package MagicalBattle.models;

public class AbilityValue {
    private double health;
    private double magic;
    private double attack;
    private double defense;
    private double speed;
    private double agility;

    public AbilityValue(double health, double magic, double attack, double defense, double speed, double agility) {
        this.health = health;
        this.magic = magic;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.agility = agility;
    }

    public AbilityValue(AbilityValue abilityValue) {
        this.health = abilityValue.health;
        this.magic = abilityValue.magic;
        this.attack = abilityValue.attack;
        this.defense = abilityValue.defense;
        this.speed = abilityValue.speed;
        this.agility = abilityValue.agility;
    }

    public double getHealth() {
        return this.health;
    }

    public double getMagic() {
        return this.magic;
    }

    public double getAttack() {
        return this.attack;
    }

    public double getDefense() {
        return this.defense;
    }

    public double getSpeed() {
        return this.speed;
    }

    public double getAgility() {
        return this.agility;
    }

    public void setHealth(double value) {
         this.health = value;
    }

    public void setMagic(double value) {
         this.magic = value;
    }

    public void setAttack(double value) {
         this.attack = value;
    }

    public void setDefense(double value) {
         this.defense = value;
    }

    public void setSpeed(double value) {
         this.speed = value;
    }

    public void setAgility(double value) {
         this.agility = value;
    }

}
