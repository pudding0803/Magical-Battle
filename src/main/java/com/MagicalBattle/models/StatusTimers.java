package com.MagicalBattle.models;

import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.SkillObject.SkillObject;
import com.MagicalBattle.models.Status.Status;
import com.MagicalBattle.models.enums.StatusName;

import java.util.HashMap;

public class StatusTimers {
    private final HashMap<StatusName, Status> timers = new HashMap<>();

    public StatusTimers(Character character) {
        for (StatusName statusName : StatusName.values()) {
            timers.put(statusName, statusName.createStatus(character));
        }
    }

    public void restart(StatusName statusName, SkillObject skillObject) {
        timers.get(statusName).doInitialize(skillObject);
    }

    public void stopAll() {
        timers.values().forEach(status -> status.getTimer().stop());
    }

    public void doAllByTime() {
        timers.values().forEach(Status::doByTime);
    }

    public Timer getTimer(StatusName statusName) {
        return timers.get(statusName).getTimer();
    }
}
