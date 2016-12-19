package com.ng.vis.physics;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.kotcrab.vis.runtime.component.*;
import com.kotcrab.vis.runtime.system.physics.PhysicsSystem;
import com.ng.gdxutils.b2d.BodyEditorLoader;
import com.ng.gdxutils.model.Position;
import com.ng.gdxutils.model.Scale;
import com.ng.gdxutils.model.Size;
import com.ng.vis.EntityManager;
import com.ng.vis.Enums;
import com.ng.vis.component.PhysicsHelperComponent;

import static com.ng.vis.physics.PhysicsManager.addPhysicsBody;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 31-07-2016.
 *
 */
public class PhysicsFactory {

    private static final String TAG=PhysicsFactory.class.getSimpleName();

    public static Entity createPhysicsBoundary(World world, Size size, Position position, boolean isCentric, float angle){

        Entity entity=world.createEntity();
        entity.edit().add(new Renderable(0));
        entity.edit().add(new Layer(0));

        com.badlogic.gdx.physics.box2d.World physicsWorld=world.getSystem(PhysicsSystem.class).getPhysicsWorld();

        BodyDef bodyDef=new BodyDef();
        bodyDef.position.set(position.x, position.y);
        bodyDef.type= BodyDef.BodyType.StaticBody;

		/*PolygonShape polygonShape=new PolygonShape();
		polygonShape.setAsBox(sc.getWidth(), sc.getHeight());*/

        ChainShape chainShape=new ChainShape();
        chainShape.createChain(new Vector2[]{new Vector2(-size.x/2, -size.y/2),new Vector2(size.x/2, -size.y/2),new Vector2(size.x/2, size.y/2)});

        FixtureDef fixture=new FixtureDef();
        fixture.shape=chainShape;
        fixture.restitution=0;

        Body body=physicsWorld.createBody(bodyDef);
        body.setUserData(entity);

        body.createFixture(fixture);

        PhysicsBody physicsBody=new PhysicsBody(body);
        entity.edit().add(physicsBody);

        EntityManager.addOrigin(entity,size.x/2,size.y/2);

        return entity;
    }

    public static Entity createPhysicsSprite(World world, int layerId, TextureRegion textureRegion, Size size, Position point, float angle, BodyDef.BodyType type, float density){

        //PhysicsProperties,VisPolygon,VisSprite,PhysicsBody,PhysicsSprite,Transform,Origin,Tint,VisID,Layer,Renderer

        Entity entity=world.createEntity();
        EntityManager.addVisSprite(entity,textureRegion,size);
        EntityManager.addTransform(entity,point, Scale.makeUnScale(),angle);
        EntityManager.addOrigin(entity,0,0);
        EntityManager.addTint(entity);
        //EntityManager.addVisID(entity,"");
        EntityManager.addLayer(entity,layerId);
        EntityManager.addRenderer(entity,0);
        //entity.edit().add(new PhysicsOrigin());

        //  EntityManager.addPhysicsBody(entity,type,density,point.x,point.y,size.x,size.y,true,angle);
        // entity.edit().add(new PhysicsSprite());

        return entity;
    }

    public static Entity createPhysicsSprite(World world, int layerId, TextureRegion textureRegion, Size size, Position point, float angle, BodyDef.BodyType type, float density, String name, BodyEditorLoader loader, float scaleFactor){

        //PhysicsProperties,VisPolygon,VisSprite,PhysicsBody,PhysicsSprite,Transform,Origin,Tint,VisID,Layer,Renderer

        Entity entity=world.createEntity();
        EntityManager.addVisSprite(entity,textureRegion,size);
        EntityManager.addTransform(entity,point, Scale.makeScale(scaleFactor,scaleFactor),angle);
        EntityManager.addOrigin(entity,size.x/2,size.y/2);
        EntityManager.addTint(entity);
        //EntityManager.addVisID(entity,"");
        EntityManager.addLayer(entity,layerId);
        EntityManager.addRenderer(entity,0);

        // EntityManager.addPhysicsBody(entity,type,density,point.x,point.y,size.x,size.y,true,angle,name,loader,scaleFactor);
        // Variables variables=EntityManager.addVariable(entity);
        // variables.putInt("phy",0);
        //entity.edit().add(new PhysicsSprite());

        return entity;
    }

    public static Entity createPhysicsAnimation(World world, int layerId, TextureRegion[] textureRegion, Size size, Position point, float angle, BodyDef.BodyType type, float density){

        Entity entity=world.createEntity();
        EntityManager.addAnimation(entity,textureRegion,size);
        EntityManager.addTransform(entity,point, Scale.makeUnScale(),angle);
        EntityManager.addOrigin(entity,0,0);
        EntityManager.addTint(entity);
        //EntityManager.addVisID(entity,"");
        EntityManager.addLayer(entity,layerId);
        EntityManager.addRenderer(entity,0);
        //new PhysicsOrigin());
        //EntityManager.addPhysicsBody(entity,type,density,point.x,point.y,size.x,size.y,true,angle);
        //Variables variables=EntityManager.addVariable(entity);
        //variables.putInt("phy",0);

        return entity;
    }

    public static class BodyProperties {

        BodyDef.BodyType type;
        float density;
        float friction;

        BodyProperties (BodyDef.BodyType type, float density, float friction){
            this.type=type;
            this.density=density;
            this.friction=friction;

        }
    }

    public static BodyProperties getBodyProperties(BodyDef.BodyType bodyType, float density, float friction){

        return new BodyProperties(bodyType,density,friction);
    }

    public static Entity createPhysicsShape(World world, int layerId, Color color, Size size, Position point, float angle, BodyDef.BodyType type, float density){

        Entity entity=world.createEntity();
        EntityManager.addShape(entity,size);
        EntityManager.addTransform(entity,point, Scale.makeUnScale(),angle);
        EntityManager.addOrigin(entity,size.x/2,size.y/2);
        EntityManager.addTint(entity,color);
        //EntityManager.addVisID(entity,"");
        EntityManager.addLayer(entity,layerId);
        EntityManager.addRenderer(entity,0);
        //new PhysicsOrigin());
         PhysicsManager.addPhysicsBody(entity,type,density,point.x,point.y,size.x,size.y,false,angle);

        Variables variables=EntityManager.addVariable(entity);
        variables.putInt("phy",0);

        return entity;
    }

    public static Entity createPhysicsBoundary(World world,float width,float height){

        Entity entity=world.createEntity();
        entity.edit().add(new Layer(Enums.Layer.NP_LAYER.value));
        entity.edit().add(new Renderable(0));

//		, Position.makePosition(width*.5f,height*.5f), true,0
        //entity.getComponent(VisSprite.class).sprite.setAlpha(0);

        //world.getManager(GroupManager.class).add(leader, Resource.PLAYER);
//        world.getSystem(PlayerManager.class).setPlayer(entity, Enums.Group.PLAYER_BULLET.value);

        com.badlogic.gdx.physics.box2d.World physicsWorld=world.getSystem(PhysicsSystem.class).getPhysicsWorld();

        BodyDef bodyDef=new BodyDef();
        bodyDef.position.set(width*.5f, 0);
        bodyDef.type= BodyDef.BodyType.StaticBody;

        ChainShape chainShape=new ChainShape();
        chainShape.createChain(new Vector2[]{new Vector2(-width/2,height),new Vector2(-width/2, 0),new Vector2(width/2, 0),new Vector2(width/2, height)});

        FixtureDef fixture=new FixtureDef();
        fixture.shape=chainShape;
        fixture.restitution=0;

        Body body=physicsWorld.createBody(bodyDef);
        body.setUserData(entity);
        body.createFixture(fixture);

        entity.edit().add(new PhysicsBody(body));
        entity.edit().add(new PhysicsHelperComponent(width/2f,height/2f));

        return entity;
    }

    public static void createPlayerParts(final Body body, final World world, Transform transform, float revertPosition){

        final Vector2 pos=body.getPosition();
        final Vector2 velocity=body.getLinearVelocity();
        final float angle=body.getAngle();

        Gdx.app.log(TAG,"Transform"+transform.getX()+"X"+transform.getY());
        Gdx.app.log(TAG,"Position"+pos.x+"AND "+pos.y);


        for(int i=0;i<10;i++) {
            Entity entity ;
            if(i<7)
                entity = createPhysicsShape(world, 1, Color.BLACK, Size.makeSize(MathUtils.random(.05f, .125f), MathUtils.random(.05f, .125f)), Position.makePosition(MathUtils.random(transform.getX(), transform.getX() + .5f), MathUtils.random(transform.getY(), transform.getY() + .25f)), angle, BodyDef.BodyType.DynamicBody, 1);
            else
                entity = createPhysicsShape(world, 1, Color.WHITE, Size.makeSize(MathUtils.random(.05f, .125f), MathUtils.random(.05f, .125f)), Position.makePosition(MathUtils.random(transform.getX(), transform.getX() + .5f), MathUtils.random(transform.getY(), transform.getY() + .25f)), angle, BodyDef.BodyType.DynamicBody, 1);

            entity.getComponent(PhysicsBody.class).body.setLinearVelocity(velocity);
        }

        float dst=pos.x+2.5f-4.5f;

				/*Timeline.createSequence()
						.pushPause(.1f)
						.push(Tween.to(world.getSystem(CameraManager.class).getCamera(), CameraAccessor.POS_XY,2).target(revertPosition,2.4f))
						.setCallback(new TweenCallback() {
							@Override
							public void onEvent(int type, BaseTween<?> source) {
								for(int i = 0; i<world.getSystem(PlayerSystem.class).status.length; i++)
									if(world.getSystem(PlayerSystem.class).status[i]) {
										world.getSystem(PlayerSystem.class).deleteArray(i);
										world.getSystem(PlayerSystem.class).status[i] = false;
									}

								for(Entity e:world.getSystem(GameSceneManager.class).spawner){
									e.getComponent(Variables.class).putBoolean("Boolean",false);
								}

								world.getSystem(GameSceneManager.class).createPlayer();
							}
						})
						.start(GameManager.game.tweenManager);*/
        //	}
        //});
    }
}
