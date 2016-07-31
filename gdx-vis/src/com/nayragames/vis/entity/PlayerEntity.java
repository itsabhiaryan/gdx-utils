package com.nayragames.vis.entity;/*
package com.nayragames.vis.entity;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.graphics.Color;
import com.kotcrab.vis.runtime.component.PhysicsBody;
import com.kotcrab.vis.runtime.component.Variables;
import com.kotcrab.vis.runtime.system.physics.PhysicsSystem;
import com.nayragames.Assets;
import com.nayragames.ecs.EntityManager;
import com.nayragames.ecs.PhysicsHelper;
import com.nayragames.model.Position;
import com.nayragames.model.Scale;
import com.nayragames.model.Size;

*/
/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 1/6/2016.
 *
 *//*

public class PlayerEntity {

    static float sizex=.7f;
    static float sizey=.4f;

    public static Entity create(World world,float positionx,float positiony){

        int randomType=0;
        Entity entity=world.createEntity();

        */
/*if(randomType==0) {
            EntityManager.addShape(entity, Size.makeSize(sizex, sizey));  //rectangle
        }else if(randomType==1) {
            EntityManager.addShape(entity, sizex / 3);   //circle
        }else if(randomType==2){
            sizex=sizey;
            EntityManager.addShape(entity, Size.makeSize(sizex, sizey));  //rectangle
        }*//*


        EntityManager.addVisSprite(entity, Assets.imageAsset.helicopter,Size.makeSize(sizex,sizey));

        EntityManager.addTransform(entity, Position.makePosition(positionx, positiony), Scale.makeUnScale(),0);
        EntityManager.addOrigin(entity,sizex/2f,sizey/2);
        EntityManager.addTint(entity, Color.WHITE);
        //EntityManager.addVisID(entity,"");
        EntityManager.addLayer(entity,1);
        EntityManager.addRenderer(entity,0);


        com.badlogic.gdx.physics.box2d.World physicsWorld = world.getSystem(PhysicsSystem.class).getPhysicsWorld();
        Body body = physicsWorld.createBody(PhysicsHelper.createBodyDef(BodyDef.BodyType.DynamicBody,positionx,positiony));

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

        PhysicsBody physicsBody = new PhysicsBody(body);
        entity.edit().add(physicsBody);
        body.setUserData(entity);

        Variables variables=EntityManager.addVariable(entity);
        variables.putInt("phy",0);

        return entity;
    }
}
*/
