package com.ng.vis.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.systems.DelayedEntityProcessingSystem;
import com.ng.vis.component.ExpireComponent;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/9/2015.
 *
 * System used to expire or delete entity form ECS world if entity having ExpireComponent.
 *
 */

public class ExpiringSystem extends DelayedEntityProcessingSystem {

    ComponentMapper<ExpireComponent> expireMapper;

    public ExpiringSystem() {
        super(Aspect.all(ExpireComponent.class));
    }

    @Override
    protected float getRemainingDelay(Entity e) {
        ExpireComponent expires = expireMapper.get(e);
        return expires.delay;
    }

    @Override
    protected void processDelta(Entity e, float accumulatedDelta) {
        ExpireComponent expires = expireMapper.get(e);
        expires.delay -= accumulatedDelta;
    }

    @Override
    protected void processExpired(Entity e) {

        e.deleteFromWorld();
    }

}
