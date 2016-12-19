package com.ng.vis.physics;

import com.artemis.Entity;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.kotcrab.vis.runtime.component.PhysicsBody;
import com.kotcrab.vis.runtime.system.physics.PhysicsSystem;
import com.ng.gdxutils.b2d.GenericPhysicsHelper;
import com.ng.gdxutils.model.Calc;
import com.ng.gdxutils.b2d.BodyEditorLoader;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 7/27/2016.
 *
 */
public class PhysicsManager {

    public static void transformBody(Body body, Vector2 position, float Rotationangle){

        Vector2 posi=position;
        Vector2 center=posi.cpy().add(body.getLocalCenter());
        float radius=posi.dst(center);
        float angle=Rotationangle+225;

        if(angle>360)
            angle-=360;

        float xValue=center.x+radius*(float)(Calc.getCosValue(Math.toRadians(angle)));
        float yValue=center.y+radius*(float)(Calc.getSinValue(Math.toRadians(angle)));

        body.setTransform(xValue,yValue,Rotationangle* MathUtils.degRad);
    }

    public static PhysicsBody addPhysicsBody(Entity entity, BodyDef.BodyType type, float density, float x, float y, float width, float height, boolean isCentric, float angle) {

        PolygonShape shape = new PolygonShape();
        if (isCentric)
            shape.setAsBox(width / 2f, height / 2f, new Vector2(width / 2f, height / 2f), angle);
        else
            shape.setAsBox(width / 2f, height / 2f);

        World physicsWorld = entity.getWorld().getSystem(PhysicsSystem.class).getPhysicsWorld();
        Body body = physicsWorld.createBody(GenericPhysicsHelper.createBodyDef(type,x,y));

        body.createFixture(shape,density);
        PhysicsBody physicsBody = new PhysicsBody(body);
        entity.edit().add(physicsBody);
        body.setUserData(entity);
        shape.dispose();

        return physicsBody;
    }


    public static PhysicsBody addPhysicsBody(Entity entity, Shape shape, float density, BodyDef bodyDef){

        Body body = PhysicsHelper.getPhysicsWorld(entity.getWorld()).createBody(bodyDef);
        body.createFixture(shape, density);
        PhysicsBody physicsBody=new PhysicsBody(body);
        entity.edit().add(physicsBody);
        body.setUserData(entity);
        return physicsBody;
    }

    public static PhysicsBody addPhysicsBody(Entity entity, BodyDef.BodyType type, float density, float x, float y, float width, float height, boolean isCentric, float angle, String name, BodyEditorLoader loader, float scaleFactor){

        FixtureDef fixtureDef=new FixtureDef();
        fixtureDef.density=density;

        Body body=entity.getWorld().getSystem(PhysicsSystem.class).getPhysicsWorld().createBody(GenericPhysicsHelper.createBodyDef(type,x,y));
        loader.attachFixture(body,name,fixtureDef,scaleFactor);

        PhysicsBody physicsBody=new PhysicsBody(body);
        entity.edit().add(physicsBody);
        body.setUserData(entity);

        return physicsBody;
    }


}
