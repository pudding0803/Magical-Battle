package com.MagicalBattle.models;

public class AbilitySet {
    private double health;
    private double magic;
    private double attack;
    private double defense;
    private double speed;
    private double agility;

    public AbilitySet(double[] values) {
        health = values[0];
        magic = values[1];
        attack = values[2];
        defense = values[3];
        speed = values[4];
        agility = values[5];
    }

    public AbilitySet(AbilitySet abilitySet) {
        health = abilitySet.health;
        magic = abilitySet.magic;
        attack = abilitySet.attack;
        defense = abilitySet.defense;
        speed = abilitySet.speed;
        agility = abilitySet.agility;
    }

    public double getHealth() {
        return health;
    }

    public double getMagic() {
        return magic;
    }

    public double getAttack() {
        return attack;
    }

    public double getDefense() {
        return defense;
    }

    public double getSpeed() {
        return speed;
    }

    public double getAgility() {
        return agility;
    }

    public void setHealth(double value) {
        health = value;
    }

    public void setMagic(double value) {
        magic = value;
    }

    public void setAttack(double value) {
        attack = value;
    }

    public void setDefense(double value) {
        defense = value;
    }

    public void setSpeed(double value) {
        speed = value;
    }

    public void setAgility(double value) {
        agility = value;
    }
}
