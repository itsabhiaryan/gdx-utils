package com.ng.gdxutils.b2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.*;

/**
 * (c) Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 7/27/2016.
 *
 * com.badlogicgames.gdx:gdx-box2d:1.9.3 should be included in your dependencies.
 */

public class GenericPhysicsHelper {

    private static final String TAG = GenericPhysicsHelper.class.getSimpleName();

    private static final float TIMESTEP=1/60f;     //60 frame per second
    private static final int VELOCITYITERATION=6;   //higher the value higher the quality of simulation  // 8 and 3 is copy from box2d
    private static final int POSITIONITERATION=2;
    private static World world;
    public static OrthographicCamera camera;
    public static Box2DDebugRenderer debugRenderer;
    public static final int PIXEL_PER_METER=32;

    public static World createWorld(OrthographicCamera camera){

        GenericPhysicsHelper.camera=camera;
        world=new World(new Vector2(0,10),true);
        debugRenderer=new Box2DDebugRenderer();

       return world;
    }

    public static void update(){
        world.step(TIMESTEP, VELOCITYITERATION, POSITIONITERATION);
        camera.update();
        debugRenderer.render(world, camera.combined);
    }

    public static BodyDef createBodyDef(BodyDef.BodyType type, float x, float y){

        BodyDef bodyDef=new BodyDef();
        bodyDef.type=type;
        bodyDef.position.set(x,y);
        return bodyDef;
    }

    public static FixtureDef createFixtureDef(Shape shape, float density, float restitution, float friction){
        FixtureDef fixtureDef=new FixtureDef();
        fixtureDef.shape=shape;
        fixtureDef.density=density;
        fixtureDef.restitution=restitution;
        fixtureDef.friction=friction;

        return fixtureDef;
    }

    public static MouseJointDef createMouseJointDef(Body body1 , Body body2, Vector3 target){

        MouseJointDef mouseJointDef=new MouseJointDef();
        mouseJointDef.bodyA=body1;
        mouseJointDef.bodyB=body2;

        mouseJointDef.collideConnected = true;
        mouseJointDef.target.set(target.x, target.y);
        mouseJointDef.maxForce = 1000.0f * body2.getMass();
        mouseJointDef.frequencyHz=5;

        return mouseJointDef;
    }

    public static RopeJointDef createRopeJointDef(Body body1 , Body body2, Vector2 firstAnchor, Vector2 secondAnchor, float length){

        RopeJointDef ropeJointDef = new RopeJointDef();
        ropeJointDef.bodyA = body1;
        ropeJointDef.bodyB = body2;
        ropeJointDef.localAnchorA.set(firstAnchor);
        ropeJointDef.localAnchorB.set(secondAnchor);
        ropeJointDef.maxLength = length;

        return ropeJointDef;
    }

    public static RevoluteJointDef createRevoluteJointDef(Body body1 , Body body2, Vector2 firstAnchor, Vector2 secondAnchor){

        RevoluteJointDef revoluteJointDef=new RevoluteJointDef();
        revoluteJointDef.bodyA=body1;
        revoluteJointDef.bodyB=body2;
        revoluteJointDef.localAnchorA.set(firstAnchor);
        revoluteJointDef.localAnchorB.set(secondAnchor);

        return revoluteJointDef;
    }

    public static DistanceJointDef createDistanceJointDef(){

        DistanceJointDef def=new DistanceJointDef();
        def.collideConnected=true;
        return def;
    }

    public static WeldJointDef createWeldJointDef(){

        WeldJointDef jd = new WeldJointDef();
        jd.collideConnected=true;

        return jd;
    }



    public static World getWorld() {
        return world;
    }

    public static final int CIRCLE=0;
    public static final int SQUARE=1;


    public static Shape createShape(float size,int type){

        Shape shape=null;
        if(type==CIRCLE){
            shape=new CircleShape();
            shape.setRadius(size);
            Gdx.app.log(TAG,"circle");
        }else if(type==SQUARE){
            shape = new PolygonShape();
            ((PolygonShape)shape).setAsBox(size,size);
            Gdx.app.log(TAG,"square");
        }

        if (shape == null) throw new IllegalStateException("Shape type is not circle not square");

        return shape;
    }
}
