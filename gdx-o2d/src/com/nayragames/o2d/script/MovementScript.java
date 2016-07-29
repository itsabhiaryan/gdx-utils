package com.nayragames.o2d.script;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.MathUtils;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.scripts.IScript;

/**
 * Created by ARYAN on 1/30/2016.
 */
public class MovementScript implements IScript {

    TransformComponent transformComponent;
    float speed=1;
    float angle;

    public MovementScript(float speed,float angle){
        this.speed=speed;
        this.angle=angle;
    }

    @Override
    public void init(Entity entity) {
        transformComponent=entity.getComponent(TransformComponent.class);
    }

    @Override
    public void act(float delta) {
     //   float angleInRad= MathUtils.degRad*angle;
        transformComponent.x=(transformComponent.x + speed * (float) (MathUtils.cosDeg(angle)));
        transformComponent.y=(transformComponent.y + speed * (float) (MathUtils.sinDeg(angle)));

    }

    @Override
    public void dispose() {

    }
}
