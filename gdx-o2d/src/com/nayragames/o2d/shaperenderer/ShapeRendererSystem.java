package com.nayragames.o2d.shaperenderer;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nayragames.o2d.component.ShapeComponent;

/**
 * Created by ARYAN on 27-01-2016.
 */
public class ShapeRendererSystem extends IteratingSystem {

    public ShapeRendererSystem(){
        super(Family.all(ShapeComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
