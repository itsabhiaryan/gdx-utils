package com.nayragames.o2d.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nayragames.o2d.component.RotationComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 1/30/2016.
 *
 */
public class RotationSystem extends IteratingSystem {

    ComponentMapper<RotationComponent> rotationCm= ComponentMapper.getFor(RotationComponent.class);
    ComponentMapper<TransformComponent> tranformCm= ComponentMapper.getFor(TransformComponent.class);

    public RotationSystem() {
        super(Family.all(RotationComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        RotationComponent rotationComponent=rotationCm.get(entity);
        TransformComponent transformComponent=tranformCm.get(entity);

        if(transformComponent.rotation<360)
            if(rotationComponent.clockwise)
                transformComponent.rotation=transformComponent.rotation+rotationComponent.speed;
            else
                transformComponent.rotation=transformComponent.rotation-rotationComponent.speed;
        else
            transformComponent.rotation=0;

    }
}
