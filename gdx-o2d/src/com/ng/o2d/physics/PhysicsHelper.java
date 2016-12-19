package com.ng.o2d.physics;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.*;
import com.badlogic.gdx.utils.Array;
import com.ng.gdxutils.b2d.GenericPhysicsHelper;
import com.ng.gdxutils.model.Position;
import com.ng.gdxutils.model.Size;
import com.ng.o2d.EntityManager;
import com.ng.o2d.Enums;
import com.ng.o2d.GenericEntityBuilder;
import com.ng.o2d.component.CircularMotion;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.physics.PhysicsBodyComponent;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 1/1/2016.
 *
 */

public class PhysicsHelper {

    private static final String TAG = PhysicsHelper.class.getSimpleName();

    public static MouseJoint createMouseJoint(SceneLoader world, Body ground, Body hitBody, Vector3 unProjectVec){
        MouseJointDef mouseJointDef= com.ng.gdxutils.b2d.GenericPhysicsHelper.createMouseJointDef(ground,hitBody,unProjectVec);
        return (MouseJoint)getPhysicsWorld(world).createJoint(mouseJointDef);
    }

    public static void destroyJoint(SceneLoader world, Joint joint){
        getPhysicsWorld(world).destroyJoint(joint);
    }

    public static void QueryAABB(SceneLoader world, QueryCallback callback, float x1, float y1, float x2, float y2){

        getPhysicsWorld(world).QueryAABB(callback,x1, y1,x2, y2);
    }

    public static Array<Entity> addObject(SceneLoader sceneLoader, int type){

        //PhysicsHelper.addFirstObject(world,new Vector2(5.5f,4f),2);
        //PhysicsHelper.addSecondObject(world,new Vector2(37f,4),new Vector2(39,4),1.5f);
       // PhysicsHelper.addSecondObject1(world,new Vector2(25,4),new Vector2(27f,4),1f);
        //PhysicsHelper.createHangingChain(world,new Vector2(10f,4),1);
        //PhysicsHelper.createBridge(world,new Vector2(3.5f,4),new Vector2(4.5f,4),1f);

        switch (type){

            case 0:
            //    return PhysicsHelper.createBridge(world,new Vector2(61f,4),new Vector2(62f,4f),1.25f);
                return PhysicsHelper.createHangingChain(sceneLoader,new Vector2(61.5f,4.1f),1.2f);

            case 1:
                return PhysicsHelper.createHangingChain(sceneLoader,new Vector2(10f,4.1f),1.1f);


            case 2:
                return PhysicsHelper.addSecondObject1(sceneLoader,new Vector2(25,4.4f),new Vector2(27f,4.3f),1.3f);


            case 3:
               // return PhysicsHelper.addSecondObject(world,new Vector2(37,4),new Vector2(39,4),1.5f);
            return  PhysicsHelper.addFirstObject(sceneLoader,new Vector2(38,4.3f),1.3f);

            default:
                return  null;

        }
    }

    public static Array<Entity> addFirstObject(SceneLoader sceneLoader, Vector2 firstPoint, float rope_length){

        Array<Entity> entities=new Array<Entity>();

        float pivot_width=.05f;
        float pivot_height=.05f;

        World world1=getPhysicsWorld(sceneLoader);
        Entity pivot = GenericEntityBuilder.createPhysicsShape(sceneLoader, 1, Color.BLACK, Size.makeSize(pivot_width, pivot_height), Position.makePosition(firstPoint.x, firstPoint.y), 0, BodyDef.BodyType.StaticBody, 1);
        entities.add(pivot);
        Body pivotBody = pivot.getComponent(PhysicsBodyComponent.class).body;

        float hangBlock_width=.5f;
        float hangBlock_height=.25f;

        Entity entity2 = GenericEntityBuilder.createPhysicsShape(sceneLoader, 1, Color.BLACK, Size.makeSize(hangBlock_width, hangBlock_height), Position.makePosition(firstPoint.x, firstPoint.y), 0, BodyDef.BodyType.DynamicBody, 1);
        Body body2 = entity2.getComponent(PhysicsBodyComponent.class).body;

        body2.applyForceToCenter(new Vector2(50,0),true);
        entities.add(entity2);

        world1.createJoint(GenericPhysicsHelper.createRopeJointDef(pivotBody,body2,new Vector2(0,0),new Vector2(0,hangBlock_height/2f),rope_length));

        Vector2 point1=firstPoint;
        Vector2 point2=new Vector2(firstPoint.x,firstPoint.y-rope_length);

        float rope_width=0.05f;
        float rope_height=0.021f;

        RevoluteJointDef jd = new RevoluteJointDef();
        jd.localAnchorA.set(0,-pivot_height/2f);
        jd.localAnchorB.set(-rope_width,0);

        RopeJointDef jd1=new RopeJointDef();
        jd1.localAnchorA.set(0,-pivot_height/2f);
        jd1.localAnchorB.set(-rope_width,0);
        jd1.maxLength=0;

        Body prevBody = pivotBody;

        double angle1=Math.toDegrees(Math.atan2(point1.x-point2.x,point1.y-point2.y));

        if(angle1<0)
            angle1+=360;

        float e_count=(rope_length-pivot_height/2f-hangBlock_height/2f)/(2*rope_width);

        for (int i = 0; i < e_count; i++) {

            Entity e= GenericEntityBuilder.createPhysicsShape(sceneLoader,0 , Color.BLACK, Size.makeSize(2*rope_width,2*rope_height), Position.makePosition(firstPoint.x,firstPoint.y),0, BodyDef.BodyType.DynamicBody,1);
            Body body=e.getComponent(PhysicsBodyComponent.class).body;
            entities.add(e);

            jd.bodyA=prevBody;
            jd.bodyB=body;
            world1.createJoint(jd);

            jd1.bodyA=prevBody;
            jd1.bodyB=body;
            world1.createJoint(jd1);

            jd.localAnchorA.set(rope_width,0);
            jd.localAnchorB.set(-rope_width,0);
            jd1.localAnchorA.set(rope_width,0);
            jd1.localAnchorB.set(-rope_width,0);
            prevBody = body;
        }

        world1.createJoint(GenericPhysicsHelper.createRevoluteJointDef(prevBody,body2,new Vector2(rope_width,0),new Vector2(0, hangBlock_height/2f)));
        world1.createJoint(GenericPhysicsHelper.createRopeJointDef(prevBody,body2,new Vector2(rope_width,0),new Vector2(0, hangBlock_height/2f),0));
        return entities;
    }

    public static Array<Entity> addSecondObject(SceneLoader sceneLoader, Vector2 firstPoint, Vector2 secondPoint, float rope_length) {

        Array<Entity> entities=new Array<Entity>();

        float pivot_width=.05f;
        float pivot_height=.05f;

        World world1 = getPhysicsWorld(sceneLoader);

        Entity pivotEntity1 = GenericEntityBuilder.createPhysicsShape(sceneLoader, 1, Color.BLACK, Size.makeSize(pivot_width, pivot_height), Position.makePosition(firstPoint.x, firstPoint.y), 0, BodyDef.BodyType.StaticBody, 1);
        Body pivotBody1 = pivotEntity1.getComponent(PhysicsBodyComponent.class).body;

        entities.add(pivotEntity1);

        Entity pivotEntity2 = GenericEntityBuilder.createPhysicsShape(sceneLoader, 1, Color.BLACK, Size.makeSize(pivot_width, pivot_height), Position.makePosition(secondPoint.x, secondPoint.y), 0, BodyDef.BodyType.StaticBody, 1);
        Body pivotBody2 = pivotEntity2.getComponent(PhysicsBodyComponent.class).body;

        entities.add(pivotEntity2);
        float dst = firstPoint.dst(secondPoint);

        float hangBlock_width=.5f;
        float hangBlock_height=.25f;

        Entity entity2 = GenericEntityBuilder.createPhysicsShape(sceneLoader, 1, Color.BLACK, Size.makeSize(hangBlock_width, hangBlock_height), Position.makePosition(firstPoint.x + dst / 2f, firstPoint.y-rope_length/2), 0, BodyDef.BodyType.DynamicBody, 1);
        Body body2 = entity2.getComponent(PhysicsBodyComponent.class).body;

        entities.add(entity2);

        world1.createJoint(GenericPhysicsHelper.createRopeJointDef(pivotBody1, body2, new Vector2(0, 0), new Vector2(0, hangBlock_height/2f), rope_length));
        world1.createJoint(GenericPhysicsHelper.createRopeJointDef(pivotBody2, body2, new Vector2(0, 0), new Vector2(0, hangBlock_height/2f), rope_length));

       {
            Vector2 point1 = firstPoint;
            Vector2 point2 = new Vector2(firstPoint.x, firstPoint.y - rope_length);

            float rope_width = 0.05f;
            float rope_height = 0.021f;

            RevoluteJointDef jd = new RevoluteJointDef();
            jd.localAnchorA.set(0, -pivot_height/2f);
            jd.localAnchorB.set(-rope_width, 0);

            RopeJointDef jd1 = new RopeJointDef();
            jd1.localAnchorA.set(0, -pivot_height/2f);
            jd1.localAnchorB.set(-rope_width, 0);
            jd1.maxLength = 0;

            Body prevBody = pivotBody1;
            double angle1 = Math.toDegrees(Math.atan2(point1.x - point2.x, point1.y - point2.y));

            if (angle1 < 0)
                angle1 += 360;

            float e_count = (rope_length - pivot_height/2-hangBlock_height/2) / (2 * rope_width);
            for (int i = 0; i < e_count; i++) {

                Entity e= GenericEntityBuilder.createPhysicsShape(sceneLoader, 0, Color.BLACK, Size.makeSize(2 * rope_width, 2 * rope_height), Position.makePosition(firstPoint.x+i*rope_width, firstPoint.y), 0, BodyDef.BodyType.DynamicBody, 1);
                entities.add(e);

                Body body=e.getComponent(PhysicsBodyComponent.class).body;

                jd.bodyA = prevBody;
                jd.bodyB = body;
                world1.createJoint(jd);

                jd1.bodyA = prevBody;
                jd1.bodyB = body;
                jd1.maxLength = 0;
                world1.createJoint(jd1);

                jd.localAnchorA.set(rope_width, 0);
                jd.localAnchorB.set(-rope_width, 0);
                jd1.localAnchorA.set(rope_width, 0);
                jd1.localAnchorB.set(-rope_width, 0);
                prevBody = body;
            }
            world1.createJoint(GenericPhysicsHelper.createRevoluteJointDef(prevBody, body2, new Vector2(rope_width, 0), new Vector2(0, hangBlock_height/2)));
            world1.createJoint(GenericPhysicsHelper.createRopeJointDef(prevBody, body2, new Vector2(rope_width, 0), new Vector2(0, hangBlock_height/2), 0));
       }

       {
            Vector2 point1 = secondPoint;
            Vector2 point2 = new Vector2(firstPoint.x, firstPoint.y - rope_length);

            float rope_width = 0.05f;
            float rope_height = 0.021f;

            RevoluteJointDef jd = new RevoluteJointDef();
            jd.localAnchorA.set(0, -pivot_height/2);
            jd.localAnchorB.set(-rope_width, 0);

            RopeJointDef jd1 = new RopeJointDef();
            jd1.localAnchorA.set(0, -pivot_height/2);
            jd1.localAnchorB.set(-rope_width, 0);
            jd1.maxLength = 0;

            Body prevBody = pivotBody2;

            double angle1 = Math.toDegrees(Math.atan2(point1.x - point2.x, point1.y - point2.y));

            if (angle1 < 0)
                angle1 += 360;

            float e_count = (rope_length - pivot_height/2-hangBlock_height/2f) / (2 * rope_width);

            for (int i = 0; i < e_count; i++) {

                Entity e= GenericEntityBuilder.createPhysicsShape(sceneLoader, 0, Color.BLACK, Size.makeSize(2 * rope_width, 2 * rope_height), Position.makePosition(secondPoint.x-i*rope_width, secondPoint.y), 0, BodyDef.BodyType.DynamicBody, 1);
                entities.add(e);

                Body body=e.getComponent(PhysicsBodyComponent.class).body;

                jd.bodyA = prevBody;
                jd.bodyB = body;
                world1.createJoint(jd);

                jd1.bodyA = prevBody;
                jd1.bodyB = body;
                jd1.maxLength = 0;
                world1.createJoint(jd1);

                jd.localAnchorA.set(rope_width, 0);
                jd.localAnchorB.set(-rope_width, 0);
                jd1.localAnchorA.set(rope_width, 0);
                jd1.localAnchorB.set(-rope_width, 0);
                prevBody = body;

            }
            world1.createJoint(GenericPhysicsHelper.createRevoluteJointDef(prevBody, body2, new Vector2(rope_width, 0), new Vector2(0, hangBlock_height/2f)));
            world1.createJoint(GenericPhysicsHelper.createRopeJointDef(prevBody, body2, new Vector2(rope_width, 0), new Vector2(0, hangBlock_height/2f), 0));
       }
       return entities;
    }

    public static Array<Entity> addSecondObject1(SceneLoader sceneLoader, Vector2 firstPoint, Vector2 secondPoint, float rope_length){

        Array<Entity> entities=new Array<Entity>();

        float pivot_width=.05f;
        float pivot_height=.05f;

        World world1=getPhysicsWorld(sceneLoader);

        Entity pivotEntity1 = GenericEntityBuilder.createPhysicsShape(sceneLoader, 1, Color.BLACK, Size.makeSize(pivot_width, pivot_height), Position.makePosition(firstPoint.x, firstPoint.y), 0, BodyDef.BodyType.StaticBody, 1);
        Body pivotBody1 = pivotEntity1.getComponent(PhysicsBodyComponent.class).body;

        entities.add(pivotEntity1);

        Entity pivotEntity2 = GenericEntityBuilder.createPhysicsShape(sceneLoader, 1, Color.BLACK, Size.makeSize(pivot_width, pivot_height), Position.makePosition(secondPoint.x, secondPoint.y), 0, BodyDef.BodyType.StaticBody, 1);
        Body pivotBody2 = pivotEntity2.getComponent(PhysicsBodyComponent.class).body;

        entities.add(pivotEntity2);

        float dst=firstPoint.dst(secondPoint);

        float hangBlock_height=.25f;
        float hingOffset=.25f;

        Entity entity2 = GenericEntityBuilder.createPhysicsShape(sceneLoader, 1, Color.BLACK, Size.makeSize(dst+hingOffset, hangBlock_height), Position.makePosition(firstPoint.x, firstPoint.y-rope_length), 0, BodyDef.BodyType.DynamicBody, 1);
        Body body2 = entity2.getComponent(PhysicsBodyComponent.class).body;

        entities.add(entity2);

        world1.createJoint(GenericPhysicsHelper.createRopeJointDef(pivotBody1,body2,new Vector2(0,0),new Vector2(-dst/2,hangBlock_height/2f),rope_length));
        world1.createJoint(GenericPhysicsHelper.createRopeJointDef(pivotBody2,body2,new Vector2(0,0),new Vector2(dst/2,hangBlock_height/2f),rope_length));

        {
            Vector2 point1 = firstPoint;
            Vector2 point2 = new Vector2(firstPoint.x, firstPoint.y - rope_length);

            float rope_width = 0.05f;
            float rope_height = 0.021f;

            RevoluteJointDef jd = new RevoluteJointDef();
            jd.localAnchorA.set(0, -pivot_height/2);
            jd.localAnchorB.set(-rope_width, 0);

            RopeJointDef jd1 = new RopeJointDef();
            jd1.localAnchorA.set(0, -pivot_height/2);
            jd1.localAnchorB.set(-rope_width, 0);
            jd1.maxLength = 0;
            Body prevBody = pivotBody1;

            double angle1 = Math.toDegrees(Math.atan2(point1.x - point2.x, point1.y - point2.y));

            if (angle1 < 0)
                angle1 += 360;

            float e_count = (rope_length-pivot_height/2-hangBlock_height/2) / (2 * rope_width);

            for (int i = 0; i < e_count; i++) {

                Entity e = GenericEntityBuilder.createPhysicsShape(sceneLoader, 0, Color.BLACK, Size.makeSize(2 * rope_width, 2 * rope_height), Position.makePosition(firstPoint.x, firstPoint.y), 0, BodyDef.BodyType.DynamicBody, 1);
                entities.add(e);
                Body body=e.getComponent(PhysicsBodyComponent.class).body;

                jd.bodyA = prevBody;
                jd.bodyB = body;
                world1.createJoint(jd);

                jd1.bodyA = prevBody;
                jd1.bodyB = body;
                jd1.maxLength = 0;
                world1.createJoint(jd1);

                jd.localAnchorA.set(rope_width, 0);
                jd.localAnchorB.set(-rope_width, 0);
                jd1.localAnchorA.set(rope_width, 0);
                jd1.localAnchorB.set(-rope_width, 0);
                prevBody = body;
            }
            world1.createJoint(GenericPhysicsHelper.createRevoluteJointDef(prevBody, body2, new Vector2(rope_width , 0), new Vector2(-dst/2, hangBlock_height/2f)));
            world1.createJoint(GenericPhysicsHelper.createRopeJointDef(prevBody, body2, new Vector2(rope_width , 0), new Vector2(-dst/2, hangBlock_height/2f), 0));
        }

        {
            Vector2 point1 = secondPoint;
            Vector2 point2 = new Vector2(firstPoint.x, firstPoint.y - rope_length);

            float rope_width = 0.05f;
            float rope_height = 0.021f;

            RevoluteJointDef jd = new RevoluteJointDef();
            jd.localAnchorA.set(0, -pivot_height/2);
            jd.localAnchorB.set(-rope_width, 0);

            RopeJointDef jd1 = new RopeJointDef();
            jd1.localAnchorA.set(0, -pivot_height/2);
            jd1.localAnchorB.set(-rope_width, 0);
            jd1.maxLength = 0;

            Body prevBody = pivotBody2;
            double angle1 = Math.toDegrees(Math.atan2(point1.x - point2.x, point1.y - point2.y));

            if (angle1 < 0)
                angle1 += 360;

            float e_count = (rope_length - pivot_height/2-hangBlock_height/2) / (2 * rope_width);
            for (int i = 0; i < e_count; i++) {
                Entity e= GenericEntityBuilder.createPhysicsShape(sceneLoader, 0, Color.BLACK, Size.makeSize(2 * rope_width, 2 * rope_height), Position.makePosition(secondPoint.x, secondPoint.y), 0, BodyDef.BodyType.DynamicBody, 1);
                entities.add(e);
                Body body=e.getComponent(PhysicsBodyComponent.class).body;

                jd.bodyA = prevBody;
                jd.bodyB = body;
                world1.createJoint(jd);

                jd1.bodyA = prevBody;
                jd1.bodyB = body;
                jd1.maxLength = 0;
                world1.createJoint(jd1);

                jd.localAnchorA.set(rope_width, 0);
                jd.localAnchorB.set(-rope_width, 0);
                jd1.localAnchorA.set(rope_width, 0);
                jd1.localAnchorB.set(-rope_width, 0);
                prevBody = body;
            }

            world1.createJoint(GenericPhysicsHelper.createRevoluteJointDef(prevBody, body2, new Vector2(rope_width, 0), new Vector2(dst/2, hangBlock_height/2)));
            world1.createJoint(GenericPhysicsHelper.createRopeJointDef(prevBody, body2, new Vector2(rope_width , 0), new Vector2(dst/2, hangBlock_height/2), 0));
        }
        return entities;
    }

    public static Array<Entity> createBridge(SceneLoader sceneLoader, Vector2 firstPoint, Vector2 secondPoint, float length){

        Array<Entity> array=new Array<Entity>();
        World world1=getPhysicsWorld(sceneLoader);

        float pivot_width=.05f;
        float pivot_height=.05f;

        Entity entity1 = GenericEntityBuilder.createPhysicsShape(sceneLoader, 1, Color.BLACK, Size.makeSize(pivot_width, pivot_height), Position.makePosition(firstPoint.x, firstPoint.y), 0, BodyDef.BodyType.StaticBody, 1);
        Body pivotBody1 = entity1.getComponent(PhysicsBodyComponent.class).body;

        array.add(entity1);

        Entity entity2 = GenericEntityBuilder.createPhysicsShape(sceneLoader, 1, Color.BLACK, Size.makeSize(pivot_width,pivot_height), Position.makePosition(secondPoint.x, secondPoint.y), 0, BodyDef.BodyType.StaticBody, 1);
        Body pivotBody2 = entity2.getComponent(PhysicsBodyComponent.class).body;

        array.add(entity2);

        Vector2 point1=firstPoint;
        Vector2 point2=secondPoint;

        float rope_width=0.05f;
        float rope_height=0.02f;

        RevoluteJointDef jd = new RevoluteJointDef();
        jd.localAnchorA.set(0,-pivot_height/2f);
        jd.localAnchorB.set(-rope_width,0);

        RopeJointDef jd1=new RopeJointDef();
        jd1.localAnchorA.set(0,-pivot_height/2);
        jd1.localAnchorB.set(-rope_width,0);
        jd1.maxLength=0;

        Body prevBody = pivotBody1;

        float dst=point1.dst(point2);
        double angle1=Math.toDegrees(Math.atan2(point1.x-point2.x,point1.y-point2.y));

        if(angle1<0)
            angle1+=360;

        float e_count=(dst>length)?dst/(2*rope_width):length/(2*rope_width);

        for (int i = 0; i < e_count; i++) {
            Entity e= GenericEntityBuilder.createPhysicsShape(sceneLoader,0 , Color.BLACK, Size.makeSize(2*rope_width,2*rope_height), Position.makePosition(point1.x + i*rope_width,point1.y) ,0, BodyDef.BodyType.DynamicBody,1);
            array.add(e);
            Body body=e.getComponent(PhysicsBodyComponent.class).body;

            jd.bodyA=prevBody;
            jd.bodyB=body;
            world1.createJoint(jd);

            jd1.bodyA=prevBody;
            jd1.bodyB=body;
            jd1.maxLength=0;

            world1.createJoint(jd1);
            jd.localAnchorA.set(rope_width, 0);
            jd.localAnchorB.set(-rope_width, 0);
            jd1.localAnchorA.set(rope_width, 0);
            jd1.localAnchorB.set(-rope_width, 0);

            prevBody = body;
        }

        world1.createJoint(GenericPhysicsHelper.createRevoluteJointDef(prevBody, pivotBody2, new Vector2(rope_width, 0), new Vector2(0,- pivot_height/2f)));
        world1.createJoint(GenericPhysicsHelper.createRopeJointDef(prevBody, pivotBody2, new Vector2(rope_width, 0), new Vector2(0, -pivot_height/2f), 0));

        return array;
    }

    public static Array<Entity> createHangingChain(SceneLoader sceneLoader, Vector2 firstPoint, float length){

        Array<Entity> entities=new Array<Entity>();
        float pivot_width=.05f;
        float pivot_height=.05f;

        World world1=getPhysicsWorld(sceneLoader);
        Entity entity1 = GenericEntityBuilder.createPhysicsShape(sceneLoader, 1, Color.BLACK, Size.makeSize(pivot_width, pivot_height), Position.makePosition(firstPoint.x, firstPoint.y), 0, BodyDef.BodyType.StaticBody, 1);
        Body body1 = entity1.getComponent(PhysicsBodyComponent.class).body;

        entities.add(entity1);

        Vector2 point1=firstPoint;
        Vector2 point2=new Vector2(4,4);

        float rope_width=0.05f;
        float rope_height=0.021f;

        RevoluteJointDef jd = new RevoluteJointDef();
        jd.localAnchorA.set(0,-pivot_height/2f);
        jd.localAnchorB.set(-rope_width,0);

        RopeJointDef jd1=new RopeJointDef();
        jd1.localAnchorA.set(0,-pivot_height/2f);
        jd1.localAnchorB.set(-rope_width,0);
        jd1.maxLength=0;

        Body prevBody = body1;

        float dst=point1.dst(point2);
        double angle1=Math.toDegrees(Math.atan2(point1.x-point2.x,point1.y-point2.y));

        if(angle1<0)
            angle1+=360;

        float e_count=(length-pivot_height/2f)/(2*rope_width);
        for (int i = 0; i < e_count; i++) {
            Entity e= GenericEntityBuilder.createPhysicsShape(sceneLoader,0 , Color.BLACK, Size.makeSize(2*rope_width,2*rope_height), Position.makePosition(point1.x + i*rope_width,point1.y) ,0, BodyDef.BodyType.DynamicBody,1);

      //      Entity e=GenericEntityBuilder.createPhysicsSprite(world,0, Assets.imageAsset.alien3s,Size.makeSize(2*rope_width,2*rope_height), Position.makePosition(point1.x + i*rope_width,point1.y) ,0, BodyDef.BodyType.DynamicBody,1);

            entities.add(e);
            Body body= e.getComponent(PhysicsBodyComponent.class).body;

            jd.bodyA=prevBody;
            jd.bodyB=body;
            world1.createJoint(jd);

            jd1.bodyA=prevBody;
            jd1.bodyB=body;
            jd1.maxLength=0;
            world1.createJoint(jd1);

            jd.localAnchorA.set(rope_width-.1f, 0);
            jd.localAnchorB.set(-rope_width+.1f, 0);
            jd1.localAnchorA.set(rope_width-.1f, 0);
            jd1.localAnchorB.set(-rope_width+.1f, 0);

            prevBody = body;
        }
        return  entities;
    }

 /*   public static void createRope(){




			Vector2 point1 = playerBody.getPosition().add(.25f, .125f);
			Vector2 point2 = body.getPosition().add(.25f, .125f);

			com.badlogic.gdx.physics.box2d.World world = getWorld().getSystem(PhysicsSystem.class).getPhysicsWorld();

			float rope_width = 0.025f;
			float piece_width = 0.05f;

			body1 = GenericEntityBuilder.createPhysicsShape(getWorld(), 0, Color.BLACK, Size.makeSize(2 * rope_width, 2 * rope_width), Position.makePosition(point1.x - rope_width, point1.y - rope_width), 0, BodyDef.BodyType.StaticBody, 0).getComponent(PhysicsBody.class).body;
			body2 = GenericEntityBuilder.createPhysicsShape(getWorld(), 0, Color.BLACK, Size.makeSize(2 * rope_width, 2 * rope_width), Position.makePosition(point2.x - rope_width, point2.y - rope_width), 0, BodyDef.BodyType.StaticBody, 0).getComponent(PhysicsBody.class).body;

			//RevoluteJointDef jd = new RevoluteJointDef();
			//RopeJointDef jd1 = new RopeJointDef();
			//.collideConnected=true;
			//WeldJointDef jd = new WeldJointDef();
			//jd.collideConnected=true;

			Body prevBody = body1;

			float dst = point1.dst(point2);
			double angle1 = Math.toDegrees(Math.atan2(point1.x - point2.x, point1.y - point2.y));

			if (angle1 < 0)
				angle1 += 360;

			float e_count = dst / (2 * piece_width);
			for (int i = 0; i < e_count; i++) {

				Body body1 = GenericEntityBuilder.createPhysicsShape(getWorld(), 0, Color.BLACK, Size.makeSize(2 * piece_width, 2 * rope_width), Position.makePosition(piece_width + point1.x - MathUtils.sinDeg((float) angle1) * (2 * piece_width) * i - piece_width, point1.y - 4 * rope_width * MathUtils.cosDeg((float) angle1) * i - rope_width), 0, BodyDef.BodyType.DynamicBody, 1).getComponent(PhysicsBody.class).body;

				Vector2 anchor = new Vector2(point1.x - MathUtils.sinDeg((float) angle1) * (2 * piece_width) * i, point1.y - 4 * rope_width * MathUtils.cosDeg((float) angle1) * i);
				jd.initialize(prevBody, body1, anchor);

				jd1.bodyA=prevBody;
				jd1.bodyB=body;
				jd1.localAnchorA.set(piece_width,rope_width);
				jd1.localAnchorB.set(0,rope_width);
				jd1.maxLength=piece_width;

//				jd.localAnchorA=anchor.x;
//				jd.localAnchorB=anchor.y;
//				world.createJoint(jd1);
				Joint revolute=world.createJoint(jd);


				jd1.bodyA=prevBody;
				jd1.bodyB=body;
				jd1.localAnchorA.set(revolute.getAnchorA());
				jd1.localAnchorB.set(revolute.getAnchorB());
				jd1.maxLength=piece_width;

				//world.createJoint(jd1);
				prevBody = body1;
			}

			Vector2 anchor = new Vector2(point1.x - MathUtils.sinDeg((float) angle1) * (2 * piece_width) * e_count, point2.y + rope_width * MathUtils.cosDeg((float) angle1));
			jd.initialize(prevBody, body2, anchor);

			jd1.bodyA=prevBody;
			jd1.bodyB=body2;
			jd1.localAnchorA.set(piece_width,rope_width);
			jd1.localAnchorB.set(0,rope_width);
			jd1.maxLength=piece_width;
			Joint revolute=world.createJoint(jd);
			//shape.dispose();
		}



    }
*/

    public void createRopeJoint(World physicsWorld){

        Body body[]=new Body[5];
        RevoluteJoint revoluteJoint[]=new RevoluteJoint[4];
        RopeJoint ropeJoint[]=new RopeJoint[4];

        {
            BodyDef bodyDef =new BodyDef();
            bodyDef.type= BodyDef.BodyType.DynamicBody;
            bodyDef.position.set(3,3);

            float height=.25f;
            float width=.125f;
            PolygonShape polygonShape=new PolygonShape();
            polygonShape.setAsBox(width,height);

            FixtureDef fixtureDef=new FixtureDef();
            fixtureDef.shape=polygonShape;
            fixtureDef.density=2;


            for(int i=0;i<5;i++){

                body[i]=physicsWorld.createBody(bodyDef);
                body[i].createFixture(fixtureDef);
            }

            RevoluteJointDef revoluteJointDef=new RevoluteJointDef();
            revoluteJointDef.localAnchorA.set(0,-height);
            revoluteJointDef.localAnchorB.set(0,height);

            for(int i=0;i<4;i++){
                revoluteJointDef.bodyA=body[i];
                revoluteJointDef.bodyB=body[i+1];
                revoluteJoint[i]=(RevoluteJoint) physicsWorld.createJoint(revoluteJointDef);
            }
            //revoluteJoint.localAnchorAs

            RopeJointDef ropeJointDef=new RopeJointDef();
            ropeJointDef.localAnchorA.set(0,-height);
            ropeJointDef.localAnchorB.set(0,height);
            ropeJointDef.maxLength=height;

            for (int i=0;i<4;i++){
                ropeJointDef.bodyA=body[i];
                ropeJointDef.bodyB=body[i+1];
                ropeJoint[i]=(RopeJoint) physicsWorld.createJoint(ropeJointDef);

            }
        }
    }

    public void addRope(World world){
        /*{

            //Entity entity=visIDManager.get("firstPoint");
            //Entity second=visIDManager.get("secondPoint");

            //Point point=entity.getComponent(Point.class);
            //Point secondPoint=entity.getComponent(Point.class);


	    Vector2 point1=new Vector2(entity.getComponent(Transform.class).getX(),entity.getComponent(Transform.class).getY());
		Vector2 point2=new Vector2(second.getComponent(Transform.class).getX(),second.getComponent(Transform.class).getY());


            Vector2 point1=new Vector2(3f,4f);
            Vector2 point2=new Vector2(4,4);

            com.badlogic.gdx.physics.box2d.World physicsWorld=world.getSystem(PhysicsSystem.class).getPhysicsWorld();

            float rope_width=0.05f;
            float rope_height=0.021f;

//			.5f, .25f

            //.05
            Body body1=GenericEntityBuilder.createPhysicsShape(world,0, Color.BLACK, Size.makeSize(.05f,.05f), Position.makePosition(point1.x-rope_width,point1.y-rope_width),0, BodyDef.BodyType.StaticBody,0).getComponent(PhysicsBody.class).body;
            Body body2=GenericEntityBuilder.createPhysicsShape(world,0, Color.BLACK, Size.makeSize(.05f,.05f), Position.makePosition(point2.x-rope_width,point2.y-rope_width),0, BodyDef.BodyType.StaticBody,0).getComponent(PhysicsBody.class).body;

            RevoluteJointDef jd = new RevoluteJointDef();
            jd.localAnchorA.set(rope_width,0);
            jd.localAnchorB.set(-rope_width,0);


            RopeJointDef jd1=new RopeJointDef();
            //DistanceJointDef jd1=new DistanceJointDef();
            jd1.localAnchorA.set(rope_width,0);
            jd1.localAnchorB.set(-rope_width,0);
            //jd1.collideConnected=true;
            jd1.maxLength=0;
            //jd1.length=0;
            //DistanceJointDef def=new DistanceJointDef();

            //.collideConnected=true;
            //WeldJointDef jd = new WeldJointDef();
            //jd.collideConnected=true;

            Body prevBody = body1;

            float dst=point1.dst(point2);
            double angle1=Math.toDegrees(Math.atan2(point1.x-point2.x,point1.y-point2.y));

            if(angle1<0)
                angle1+=360;

            float e_count=dst/(2*rope_width);
            //ropeJointDef.maxLength=.5f;
            for (int i = 0; i < e_count; i++) {

                Body body=GenericEntityBuilder.createPhysicsShape(world,0 , Color.BLACK, Size.makeSize(2*rope_width,2*rope_height), Position.makePosition(rope_height+ point1.x - MathUtils.sinDeg((float) angle1)*(2*rope_height) * i-rope_height,point1.y-4*rope_width* MathUtils.cosDeg((float) angle1)*i-rope_width),0, BodyDef.BodyType.DynamicBody,1).getComponent(PhysicsBody.class).body;
                Vector2 anchor = new Vector2(point1.x - MathUtils.sinDeg((float) angle1)*(2*rope_height) * i, point1.y-4*rope_width* MathUtils.cosDeg((float) angle1)*i);
                //jd.initialize(prevBody, body, anchor);

///				jd.bodyA=prevBody;
//				jd.bodyB=body;

                jd.bodyA=prevBody;
                jd.bodyB=body;

                RevoluteJoint revoluteJoint=(RevoluteJoint) physicsWorld.createJoint(jd);
                //world.createJoint(jd1);

                jd1.bodyA=prevBody;
                jd1.bodyB=body;
                jd1.maxLength=0;

//				jd.localAnchorA=anchor.x;
//				jd.localAnchorB=anchor.y;
//				world.createJoint(jd1);

                physicsWorld.createJoint(jd1);
                prevBody = body;
            }

            Vector2 anchor = new Vector2(point1.x - MathUtils.sinDeg((float) angle1)*(2*rope_height) * e_count, point2.y+rope_width* MathUtils.cosDeg((float) angle1));
            //jd.initialize(prevBody, body2, anchor);

            jd.bodyA=prevBody;
            jd.bodyB=body2;




		jd1.bodyA=prevBody;
			jd1.bodyB=body2;
			jd1.localAnchorA.set(piece_width,rope_width);
			jd1.localAnchorB.set(0,rope_width);
			jd1.maxLength=piece_width;*//**//*




            jd1.localAnchorA.set(rope_width/2f,0);
            jd1.localAnchorB.set(-.25f, 0);
            jd1.bodyA=prevBody;
            //jd1.bodyB=playerBody;

            physicsWorld.createJoint(jd1);
//			world.createJoint(jd1);
            //shape.dispose();
        }





{
			Entity entity=visIDManager.get("firstpoint1");
			Entity second=visIDManager.get("secondpoint1");

			//Point point=entity.getComponent(Point.class);
			//Point secondPoint=entity.getComponent(Point.class);

			Vector2 point1=new Vector2(entity.getComponent(Transform.class).getX(),entity.getComponent(Transform.class).getY());
			Vector2 point2=new Vector2(second.getComponent(Transform.class).getX(),second.getComponent(Transform.class).getY());

			//Vector2 point1=new Vector2(3,4);
			//Vector2 point2=new Vector2(5,4);

			com.badlogic.gdx.physics.box2d.World world=getWorld().getSystem(PhysicsSystem.class).getPhysicsWorld();

			float rope_width=0.025f;
			float piece_width=0.05f;

			body1=GenericEntityBuilder.createPhysicsShape(getWorld(),0, Color.BLACK,Size.makeSize(2*rope_width,2*rope_width), Position.makePosition(point1.x-rope_width,point1.y-rope_width),0, BodyDef.BodyType.StaticBody,0).getComponent(PhysicsBody.class).body;
			body2=GenericEntityBuilder.createPhysicsShape(getWorld(),0, Color.BLACK,Size.makeSize(2*rope_width,2*rope_width), Position.makePosition(point2.x-rope_width,point2.y-rope_width),0, BodyDef.BodyType.StaticBody,0).getComponent(PhysicsBody.class).body;

			RevoluteJointDef jd = new RevoluteJointDef();
			//.collideConnected=true;
			//WeldJointDef jd = new WeldJointDef();
			//jd.collideConnected=true;

			Body prevBody = body1;

			float dst=point1.dst(point2);
			float angle=point1.angle(point2);

			double angle1=Math.toDegrees(Math.atan2(point1.x-point2.x,point1.y-point2.y));

			if(angle1<0)
				angle1+=360;

			float e_count=dst/(2*piece_width);
			for (int i = 0; i < e_count; i++) {

				Body body=GenericEntityBuilder.createPhysicsShape(getWorld(),0 ,Color.BLACK,Size.makeSize(2*piece_width,2*rope_width), Position.makePosition(piece_width+ point1.x - MathUtils.sinDeg((float) angle1)*(2*piece_width) * i-piece_width,point1.y-4*rope_width*MathUtils.cosDeg((float) angle1)*i-rope_width),0, BodyDef.BodyType.DynamicBody,1).getComponent(PhysicsBody.class).body;

				Vector2 anchor = new Vector2(point1.x - MathUtils.sinDeg((float) angle1)*(2*piece_width) * i, point1.y-4*rope_width*MathUtils.cosDeg((float) angle1)*i);
				jd.initialize(prevBody, body, anchor);

				world.createJoint(jd);
				prevBody = body;
			}

			Vector2 anchor = new Vector2(point1.x - MathUtils.sinDeg((float) angle1)*(2*piece_width) * e_count, point2.y+rope_width*MathUtils.cosDeg((float) angle1));
			jd.initialize(prevBody, body2, anchor);
			joint=world.createJoint(jd);
			//shape.dispose();
		}


*/
    }

  //  public static final int CIRCLE=0;
  //  public static final int SQUARE=1;


    public static Shape createShape(float size, Enums.Shape type){

        Shape shape=null;
        if(type== Enums.Shape.CIRCLE){
           shape=new CircleShape();
           shape.setRadius(size);
            Gdx.app.log(TAG,"circle");
        }else if(type== Enums.Shape.RECTANGLE){
            shape = new PolygonShape();
            ((PolygonShape)shape).setAsBox(size,size);
            Gdx.app.log(TAG,"square");
        }

        if (shape == null) throw new IllegalStateException("Shape type is not circle not square");

        return shape;
    }

    public static World getPhysicsWorld(SceneLoader sceneLoader){

        World world=sceneLoader.world;
        if (world == null) throw new IllegalStateException("Scene having no world");
        return world;
    }

    public static void createCircularMotion(SceneLoader world, int totalObject, Enums.Shape type, float size, float x, float y, float radius, boolean isPhysics , CircularMotion.MotionType motionType){

        float speed=2;
        int layer=1;
        float density=1;
        Shape shape=createShape(size,type);

        for (int i=0;i<totalObject;i++){
            Entity entity1=null;
            if(type== Enums.Shape.CIRCLE)
                entity1= GenericEntityBuilder.createShape(world,layer, Color.BLACK,size, Position.makePosition(x,y,true),0);
            else if(type== Enums.Shape.RECTANGLE)
                entity1= GenericEntityBuilder.createShape(world,layer, Color.BLACK, Size.makeSize(2*size,2*size), Position.makePosition(x,y,true),0);

            EntityManager.addCircularMotion(entity1,x,y,radius,speed, motionType,type).angle=i*(360/totalObject);
            if(isPhysics) EntityManager.addPhysicsBody(getPhysicsWorld(world),entity1,shape,density, GenericPhysicsHelper.createBodyDef(BodyDef.BodyType.StaticBody, x, y));
        }
        shape.dispose();
    }
}

