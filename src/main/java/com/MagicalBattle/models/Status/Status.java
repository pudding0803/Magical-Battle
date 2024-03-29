package com.MagicalBattle.models.Status;

import com.MagicalBattle.models.Character.Character;
import com.MagicalBattle.models.SkillObject.SkillObject;
import com.MagicalBattle.models.Timer;

public abstract class Status {
    protected final Character character;
    protected final Timer timer;
    protected SkillObject skillObject;

    public Status(Character character, int endTime) {
        this.character = character;
        this.timer = new Timer(endTime);
    }

    public Timer getTimer() {
        return timer;
    }

    public void doInitialize(SkillObject skillObject) {
        this.skillObject = skillObject;
        timer.restart();
        initialize();
    }

    public void doByTime() {
        if (!timer.isEnd()) {
            doPerTime();
            timer.timing();
        }
    }

    protected abstract void initialize();

    protected abstract void doPerTime();
}
