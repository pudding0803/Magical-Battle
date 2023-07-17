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
            pressedTimers.put(attack, pressedTimer);
            attackTimers.put(attack, new Timer(attackDurations[index]));
            chargedAttackTimers.put(attack, new Timer(chargedAttackDurations[index]));
        }
    }

    public void timing() {
        for (Attack attack : Attack.values()) {
            pressedTimers.get(attack).timing();
            attackTimers.get(attack).timing();
            chargedAttackTimers.get(attack).timing();
        }
    }
    
    public void restartPressed(Attack attack) {
        pressedTimers.get(attack).setActive(true);
        pressedTimers.get(attack).restart();
    }

    public void stopPressed(Attack attack) {
        pressedTimers.get(attack).setActive(false);
    }

    public void restartAttack(Attack attack) {
        attackTimers.get(attack).restart();
    }

    public void restartChargedAttack(Attack attack) {
        chargedAttackTimers.get(attack).restart();
    }

    public boolean isPressing(Attack attack) {
        return pressedTimers.get(attack).isActive();
    }

    public boolean isCharged(Attack attack) {
        return pressedTimers.get(attack).isActive() && pressedTimers.get(attack).isEnd();
    }

    public boolean isAttackValid(Attack attack) {
        return attackTimers.get(attack).isEnd();
    }

    public boolean isChargedAttackValid(Attack attack) {
        return chargedAttackTimers.get(attack).isEnd();
    }
}
