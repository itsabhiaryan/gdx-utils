package com.nayragames.o2d.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nayragames.gdxutils._GameManager;
import com.nayragames.o2d.component.ExpireComponent;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/9/2015.
 *
 * System used to expire or delete entity form ECS world if entity having ExpireComponent.
 *
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
        return !_GameManager.isPaused();
    }
}
