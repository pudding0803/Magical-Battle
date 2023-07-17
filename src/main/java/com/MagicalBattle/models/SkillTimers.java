package com.MagicalBattle.models;

import com.MagicalBattle.constants.Time;
import com.MagicalBattle.models.enums.SkillType;

import java.util.HashMap;

public class SkillTimers {
    private static final int CHARGE_TIME = Time.ms(2000);

    private final HashMap<SkillType, Timer> pressedTimers = new HashMap<>();
    private final HashMap<SkillType, Timer> skillTimers = new HashMap<>();
    private final HashMap<SkillType, Timer> chargedSkillTimers = new HashMap<>();

    public SkillTimers(int[] skillDurations, int[] chargedSkillDurations) {
        for (SkillType skill : SkillType.values()) {
            int index = skill.ordinal();
            Timer pressedTimer = new Timer(CHARGE_TIME);
            pressedTimer.setActive(false);
            pressedTimers.put(skill, pressedTimer);
            skillTimers.put(skill, new Timer(skillDurations[index]));
            chargedSkillTimers.put(skill, new Timer(chargedSkillDurations[index]));
        }
    }

    public void timing() {
        for (SkillType skill : SkillType.values()) {
            pressedTimers.get(skill).timing();
            skillTimers.get(skill).timing();
            chargedSkillTimers.get(skill).timing();
        }
    }
    
    public void restartPressed(SkillType skill) {
        pressedTimers.get(skill).setActive(true);
        pressedTimers.get(skill).restart();
    }

    public void stopPressed(SkillType skill) {
        pressedTimers.get(skill).setActive(false);
    }

    public void restartSkill(SkillType skill) {
        skillTimers.get(skill).restart();
    }

    public void restartChargedSkill(SkillType skill) {
        chargedSkillTimers.get(skill).restart();
    }

    public boolean isPressing(SkillType skill) {
        return pressedTimers.get(skill).isActive();
    }

    public boolean isCharged(SkillType skill) {
        return pressedTimers.get(skill).isActive() && this.pressedTimers.get(skill).isEnd();
    }

    public boolean isSkillValid(SkillType skill) {
        return this.skillTimers.get(skill).isEnd();
    }

    public boolean isChargedSkillValid(SkillType skill) {
        return this.chargedSkillTimers.get(skill).isEnd();
    }
}
