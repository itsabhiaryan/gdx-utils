package com.ng.o2d.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.ng.gdxutils._GameManager;
import com.ng.o2d.component.CircularMotion;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.particle.ParticleComponent;
import com.uwsoft.editor.renderer.components.physics.PhysicsBodyComponent;
import com.uwsoft.editor.renderer.physics.PhysicsBodyLoader;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 04-01-2016.
 *
 * If entity having CircularMotion component then entity it moves around a point.
 * Center of that Circle is decide at creation of CircularMotion component.
 *
 */
public class CircularMotionSystem extends IteratingSystem {

    ComponentMapper<CircularMotion> circularCm;
    ComponentMapper<PhysicsBodyComponent> physicsCm;
    ComponentMapper<ParticleComponent> particleCm;
    ComponentMapper<TransformComponent> transformCm;

    public CircularMotionSystem() {
        super(Family.all(CircularMotion.class).get());

         circularCm= ComponentMapper.getFor(CircularMotion.class);
         physicsCm= ComponentMapper.getFor(PhysicsBodyComponent.class);
         particleCm= ComponentMapper.getFor(ParticleComponent.class);
         transformCm= ComponentMapper.getFor(TransformComponent.class);
    }

    @Override
    protected void processEntity(Entity e, float deltaTime) {

        CircularMotion circularMotion=circularCm.get(e);
        TransformComponent transform= transformCm.get(e);
        PhysicsBodyComponent physics=physicsCm.get(e);

        if(circularMotion.angle<360)
            if(circularMotion.motionType== CircularMotion.MotionType.CLOCKWISE)
                circularMotion.angle=circularMotion.angle+circularMotion.speed;
            else
                circularMotion.angle=circularMotion.angle-circularMotion.speed;
        else
            circularMotion.angle=0;

        float x=(circularMotion.centerX+(float)(circularMotion.radius*Math.sin((circularMotion.angle)*Math.PI/180))-circularMotion.originX);
        float y=(circularMotion.centerY-(float)(circularMotion.radius*Math.cos((circularMotion.angle)*Math.PI/180))-circularMotion.originY);

        if(physicsCm.has(e)) {
            physics.body.setTransform(PhysicsBodyLoader.getScale()*(x ), PhysicsBodyLoader.getScale()*(y ), physics.body.getAngle());
        }
        else {
                transform.x=x;
                transform.y=y;
            }
        if(particleCm.has(e)) {
            ParticleComponent particle=particleCm.get(e);
            particle.particleEffect.setPosition(x,y);
        }
    }

    @Override
    public boolean checkProcessing() {
        return !_GameManager.isPaused();
    }
}
