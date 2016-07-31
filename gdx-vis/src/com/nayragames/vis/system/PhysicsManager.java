package com.nayragames.vis.system;

import com.artemis.*;
import com.artemis.managers.PlayerManager;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.JointEdge;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.runtime.component.PhysicsBody;
import com.kotcrab.vis.runtime.component.Variables;
import com.kotcrab.vis.runtime.system.CameraManager;
import com.kotcrab.vis.runtime.system.physics.PhysicsSystem;
import com.nayragames.gdxutils._GameManager;
import com.nayragames.vis.component.Bounds;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 28-12-2015.
 *
 * PhysicsHelper of PhysicsSystem like if entity remove from world than it delete body form physics World.
 *
 */
public class PhysicsManager extends EntitySystem {

    private static final String TAG = "[" + PlayerManager.class.getSimpleName() + "]";
    ComponentMapper<PhysicsBody> physicsCm;
    ComponentMapper<Variables> variablesCm;
    ComponentMapper<Bounds> boundsCm;
    private World world;
    private PhysicsSystem physicsSystem;
    IntBag entities;
    CameraManager cameraManager;
    AspectSubscriptionManager aspect;
    //EntitySubscription sub;

    public PhysicsManager() {
        super(Aspect.all(PhysicsBody.class));
    }

    @Override
    protected void initialize() {
        world=physicsSystem.getPhysicsWorld();
    }

    @Override
    public void inserted(Entity e) {
        entities=getSubscription().getEntities();
    }

    @Override
    protected void begin() {
        super.begin();
        //isBodyRemove=true;
    }

    @Override
    protected void processSystem() {



    }

    @Override
    public void removed(Entity e) {

            if (physicsCm.has(e) == false) return;
            PhysicsBody physics = physicsCm.get(e);
            if (physics.body == null) return;

            Array<JointEdge> jointEdge = physics.body.getJointList();
            for (JointEdge jointEdge1 : jointEdge)
                world.destroyJoint(jointEdge1.joint);
            world.destroyBody(physics.body);
            physics.body = null;

            entities = getSubscription().getEntities();
    }

    public void removeBody(int id){

            Entity e = getWorld().getEntity(id);

            if (physicsCm.has(e) == false) return;
            PhysicsBody physics = physicsCm.get(e);
            if (physics.body == null) return;

            Array<JointEdge> jointEdge = physics.body.getJointList();
            for (JointEdge jointEdge1 : jointEdge)
                world.destroyJoint(jointEdge1.joint);

            world.destroyBody(physics.body);
            e.edit().remove(PhysicsBody.class);
            physics.body = null;
    }


    public boolean checkAwake(int id){

        Entity e=getWorld().getEntity(id);

        if (physicsCm.has(e) == false) return true;
        PhysicsBody physics = physicsCm.get(e);
        if (physics.body == null) return true;

        if(physics.body.getType()== BodyDef.BodyType.StaticBody||physics.body.getType()== BodyDef.BodyType.KinematicBody)
            return true;

        return physics.body.isAwake();
    }

    @Override
    protected boolean checkProcessing() {

        if(_GameManager.isPaused())
            getWorld().getSystem(PhysicsSystem.class).setEnabled(false);
        else
            getWorld().getSystem(PhysicsSystem.class).setEnabled(true);

        return !_GameManager.isPaused();
    }
}
