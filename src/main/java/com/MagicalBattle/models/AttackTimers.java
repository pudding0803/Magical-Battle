package com.MagicalBattle.models;

import com.MagicalBattle.constants.Time;
import com.MagicalBattle.models.Enums.Attack;

import java.util.HashMap;

public class AttackTimers {
    private static final int CHARGE_TIME = Time.ms(2000);

    private final HashMap<Attack, Timer> pressedTimers = new HashMap<>();
    private final HashMap<Attack, Timer> attackTimers = new HashMap<>();
    private final HashMap<Attack, Timer> chargedAttackTimers = new HashMap<>();

    public AttackTimers(int[] attackDurations, int[] chargedAttackDurations) {
        for (Attack attack : Attack.values()) {
            int index = attack.ordinal();
            Timer pressedTimer = new Timer(CHARGE_TIME);
            pressedTimer.setActive(false);
            this.pressedTimers.put(attack, pressedTimer);
            this.attackTimers.put(attack, new Timer(attackDurations[index]));
            this.chargedAttackTimers.put(attack, new Timer(chargedAttackDurations[index]));
        }
    }

    public void timing() {
        for (Attack attack : Attack.values()) {
            this.pressedTimers.get(attack).timing();
            this.attackTimers.get(attack).timing();
            this.chargedAttackTimers.get(attack).timing();
        }
    }
    
    public void restartPressed(Attack attack) {
        this.pressedTimers.get(attack).setActive(true);
        this.pressedTimers.get(attack).restart();
    }

    public void stopPressed(Attack attack) {
        this.pressedTimers.get(attack).setActive(false);
    }

    public void restartAttack(Attack attack) {
        this.attackTimers.get(attack).restart();
    }

    public void restartChargedAttack(Attack attack) {
        this.chargedAttackTimers.get(attack).restart();
    }

    public boolean isPressing(Attack attack) {
        return this.pressedTimers.get(attack).isActive();
    }

    public boolean isCharged(Attack attack) {
        return this.pressedTimers.get(attack).isActive() && this.pressedTimers.get(attack).isEnd();
    }

    public boolean isAttackValid(Attack attack) {
        return this.attackTimers.get(attack).isEnd();
    }

    public boolean isChargedAttackValid(Attack attack) {
        return this.chargedAttackTimers.get(attack).isEnd();
    }
}
