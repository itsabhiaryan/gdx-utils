package com.nayragames.o2d.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.*;
import com.nayragames.gdxutils.b2d.GenericPhysicsHelper;
import com.nayragames.gdxutils.model.Position;
import com.nayragames.gdxutils.model.Scale;
import com.nayragames.o2d.EntityManager;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.physics.PhysicsBodyComponent;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 1/6/2016.
 *
 */
public class PlayerEntity {

    static float sizex=.7f;
    static float sizey=.4f;

    public static Entity create(SceneLoader sceneLoader, float positionx, float positiony){

        int randomType=0;
        Entity entity=new Entity();

        /*if(randomType==0) {
            EntityManager.addShape(entity, Size.makeSize(sizex, sizey));  //rectangle
        }else if(randomType==1) {
            EntityManager.addShape(entity, sizex / 3);   //circle
        }else if(randomType==2){
            sizex=sizey;
            EntityManager.addShape(entity, Size.makeSize(sizex, sizey));  //rectangle
        }*/

        //EntityManager.addTextureRegion(entity, Assets.imageAsset.helicopter, Size.makeSize(sizex,sizey));

        EntityManager.addTransform(entity, Position.makePosition(positionx, positiony), Scale.makeUnScale(),0);
        //EntityManager.addOrigin(entity,sizex/2f,sizey/2);
        EntityManager.addTint(entity, Color.WHITE);
        //EntityManager.addVisID(entity,"");
       // EntityManager.addLayer(entity,1);
        EntityManager.addRenderer(entity,0);


        World physicsWorld = sceneLoader.world;
        Body body = physicsWorld.createBody(GenericPhysicsHelper.createBodyDef(BodyDef.BodyType.DynamicBody,positionx,positiony));

        Shape shape;
        if(randomType==0) {
            shape = new PolygonShape();
            ((PolygonShape)shape).setAsBox(sizex *.5f, sizey *.4f);
            body.createFixture(shape, 1);
            shape.dispose();

           // FixtureDef fixtureDef=new FixtureDef();
           // fixtureDef.density=1.25f;

            //PhysicsHelper.heliLoader.attachFixture(body,PhysicsHelper.BodyLoaderData.HELI.value,fixtureDef,.7f);

        } else if(randomType==1){
            shape=new CircleShape();
            shape.setRadius(sizex/3);
            body.createFixture(shape,1);
            shape.dispose();

        }else if(randomType==2){
            shape = new PolygonShape();
            ((PolygonShape)shape).setAsBox(sizey / 2f, sizey / 2f);
            body.createFixture(shape, 1.5f);
            shape.dispose();
        }

        PhysicsBodyComponent physicsBody = new PhysicsBodyComponent();
        physicsBody.body=body;
        entity.add(physicsBody);
        body.setUserData(entity);

        //Variables variables= EntityManager.addVariable(entity);
        //variables.putInt("phy",0);

        return entity;
    }
}
