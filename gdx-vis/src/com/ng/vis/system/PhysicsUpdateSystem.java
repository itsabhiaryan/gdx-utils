package com.ng.vis.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.kotcrab.vis.runtime.component.Origin;
import com.kotcrab.vis.runtime.component.PhysicsBody;
import com.kotcrab.vis.runtime.component.Transform;
import com.kotcrab.vis.runtime.component.VisSprite;
import com.ng.gdxutils._GameManager;
import com.ng.vis.component.AnimationComponent;
import com.ng.vis.component.ShapeComponent;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 28-12-2015.
 *
 * System that is responsible for update transform property by body of Entity.
 * you should disable PHYSICS_SPRITE_UPDATE_SYSTEM system which is enable by default.
 * Entity must have PhysicsBody and Origin and any one from AnimationComponent,ShapeComponent,VisSprite.
 *
 */
public class PhysicsUpdateSystem extends EntityProcessingSystem {

    private ComponentMapper<PhysicsBody> physicsCm;
    private ComponentMapper<Transform> transformCm;
    private ComponentMapper<Origin> originCm;

    public PhysicsUpdateSystem(){

        super(Aspect.all(PhysicsBody.class,Origin.class).one(AnimationComponent.class, ShapeComponent.class,VisSprite.class));
    }

    @Override
    protected void process(Entity e) {
        PhysicsBody physics = physicsCm.get(e);
        if (physics.body == null) return;

        Transform transform = transformCm.get(e);
        Origin origin=originCm.get(e);

        transform.setPosition(physics.body.getPosition().x-origin.getOriginX(), physics.body.getPosition().y-origin.getOriginY());
        transform.setRotation(physics.body.getAngle() * MathUtils.radiansToDegrees);

    }

    @Override
    protected boolean checkProcessing() {
        return !_GameManager.isPaused();
    }
}
