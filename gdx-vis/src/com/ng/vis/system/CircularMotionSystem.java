package com.ng.vis.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.kotcrab.vis.runtime.component.Origin;
import com.kotcrab.vis.runtime.component.PhysicsBody;
import com.kotcrab.vis.runtime.component.Transform;
import com.kotcrab.vis.runtime.component.VisParticle;
import com.ng.gdxutils._GameManager;
import com.ng.vis.component.CircularMotion;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 04-01-2016.
 *
 *
 * If entity having CircularMotion component then entity it moves around a point.
 * Center of that Circle is decide at creation of CircularMotion component.
 *
 */
public class CircularMotionSystem extends EntityProcessingSystem {

    ComponentMapper<CircularMotion> circularCm;
    ComponentMapper<Transform> transformCm;
    ComponentMapper<Origin> originCm;
    ComponentMapper<PhysicsBody> physicsCm;
    ComponentMapper<VisParticle> particleCm;

    public CircularMotionSystem() {
        super(Aspect.all(CircularMotion.class));
    }

    @Override
    protected void process(Entity e) {

        CircularMotion circularMotion=circularCm.get(e);
        Transform transform=transformCm.get(e);
        Origin origin=originCm.get(e);
        PhysicsBody physics=physicsCm.get(e);

        if(circularMotion.angle<360)
            if(circularMotion.motionType== CircularMotion.MotionType.CLOCKWISE)
                circularMotion.angle=circularMotion.angle+circularMotion.speed;
            else
                circularMotion.angle=circularMotion.angle-circularMotion.speed;
        else
            circularMotion.angle=0;

        float x=(circularMotion.centerX+(float)(circularMotion.radius*Math.sin((circularMotion.angle)*Math.PI/180))-origin.getOriginX());
        float y=(circularMotion.centerY-(float)(circularMotion.radius*Math.cos((circularMotion.angle)*Math.PI/180))-origin.getOriginY());

        if(physicsCm.has(e))
            physics.body.setTransform(x+origin.getOriginX(),y+origin.getOriginY(),physics.body.getAngle());
        else
            transform.setPosition(x,y);

        if(particleCm.has(e)) {
            VisParticle visParticle=particleCm.get(e);
            visParticle.getEffect().setPosition(x,y);
        }
    }

    @Override
    protected boolean checkProcessing() {
        return !_GameManager.isPaused();
    }
}
