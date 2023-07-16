package com.MagicalBattle.models;

public class AbilitySet {
    private double health;
    private double magic;
    private double attack;
    private double defense;
    private double speed;
    private double agility;

    public AbilitySet(double[] values) {
        this.health = values[0];
        this.magic = values[1];
        this.attack = values[2];
        this.defense = values[3];
        this.speed = values[4];
        this.agility = values[5];
    }

    public AbilitySet(AbilitySet abilitySet) {
        this.health = abilitySet.health;
        this.magic = abilitySet.magic;
        this.attack = abilitySet.attack;
        this.defense = abilitySet.defense;
        this.speed = abilitySet.speed;
        this.agility = abilitySet.agility;
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
