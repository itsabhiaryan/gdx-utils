package com.nayragames.o2d.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.nayragames.o2d.component.TickPerSecond;

/**
 * Created by ARYAN on 1/28/2016.
 */
public class SecondIntervalSystem extends IntervalIteratingSystem {

    public static final float TICK_PER_SECOND=1;

    public SecondIntervalSystem() {

        super(Family.all(TickPerSecond.class).get(), TICK_PER_SECOND);
    }

    @Override
    protected void processEntity(Entity entity) {

    }
}
