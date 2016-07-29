package com.nayragames.o2d.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nayragames.o2d.Constants;
import com.nayragames.o2d.component.ExpireComponent;


/**
 * System used to expire or delete entity form ECS world if entity having ExpireComponent.
 *
 * Created by ARYAN on 12/9/2015.
 */

public class ExpiringSystem extends IteratingSystem {

    ComponentMapper<ExpireComponent> expireMapper= ComponentMapper.getFor(ExpireComponent.class);
    Engine engine;

    public ExpiringSystem(Engine engine) {
        super(Family.all(ExpireComponent.class).get());
        this.engine=engine;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        ExpireComponent expireComponent=expireMapper.get(entity);
        expireComponent.delay-=deltaTime;

        if(expireComponent.delay<=0)
            engine.removeEntity(entity);
    }

    @Override
    public boolean checkProcessing() {
        return !Constants.isPaused();
    }
}
