package com.nayragames.vis.ai;

import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.artemis.managers.PlayerManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kotcrab.vis.runtime.component.*;
import com.nayragames.gdxutils.model.Position;
import com.nayragames.gdxutils.model.Scale;
import com.nayragames.gdxutils.model.Size;
import com.nayragames.vis.EntityManager;
import com.nayragames.vis.Enums;
import com.nayragames.vis.GenericEntityBuilder;
import com.nayragames.vis.ai.AI;
import com.nayragames.vis.component.*;

import static com.nayragames.vis.EntityFactory.getPoint;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 31-07-2016.
 */
public class Test {

    public static Component createSteerC(Entity entity){

        Component steeringComponent=null;
        if(entity.getWorld().getMapper(PhysicsBody.class).has(entity))
            steeringComponent=createB2dSteerC(entity,false);
        else
            steeringComponent=createSteerC(entity,false);

        return steeringComponent;
    }

    public static SteerableComponent createSteerC(Entity entity, boolean isIndependentFace){
        //SpriteComponent playerBasic=leader.getComponent(SpriteComponent.class);

        //AnimationComponent playerBasic=leader.getComponent(AnimationComponent.class);
        //if(playerBasic==null)
        //playerBasic=leader.getComponent(AnimationComponent.class);

        VisSprite playerSprite=entity.getComponent(VisSprite.class);
        Transform transform=entity.getComponent(Transform.class);

        float bounding=(playerSprite.getWidth()+playerSprite.getHeight())/4;
        SteerableComponent steerableComponent =new SteerableComponent(bounding,isIndependentFace);
        steerableComponent.getPosition().set(transform.getX(),transform.getY());
        steerableComponent.setOrientation(transform.getRotation()* MathUtils.degreesToRadians);

        return steerableComponent;
    }

    public static B2DSteerableComponent createB2dSteerC(Entity entity){

        B2DSteerableComponent steer=createB2dSteerC(entity,false);
        return steer;
    }

    public static B2DSteerableComponent createB2dSteerC(Entity entity, boolean isIndependentFace){

        VisSprite spriteComponent=entity.getComponent(VisSprite.class);
        PhysicsBody physicsComponent=entity.getComponent(PhysicsBody.class);
        float bounding=(spriteComponent.getWidth()+spriteComponent.getHeight())/4;
        B2DSteerableComponent b2dSteer=new B2DSteerableComponent(physicsComponent.body,isIndependentFace,bounding);

        b2dSteer.getPosition().set(physicsComponent.body.getPosition());
        b2dSteer.setOrientation(physicsComponent.body.getAngle());

        return b2dSteer;
    }

    public static Entity createCollectableObject(World world, TextureRegion[] textureRegions, float x1, float y1, boolean hasMagnet, Entity origin){

        Entity entity=world.createEntity();
        entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
        entity.edit().add(new Renderable(1));

        entity.edit().add(new AnimationComponent(textureRegions,.1f, Animation.PlayMode.LOOP));
        entity.edit().add(new BasicComponent(Size.makeSize(.4f,.4f), Position.makePosition(x1,y1),180));

        entity.edit().add(new MovementComponent(.05f));
        CollisionComponent collisionComponent=new CollisionComponent();
        collisionComponent.type= Enums.CollectionType.COIN.value;
        entity.edit().add(collisionComponent);
        world.getSystem(GroupManager.class).add(entity, Enums.Group.COLLECTABLE.value);
        entity.edit().add(createSteerC(entity));

        SteerableComponent steer=entity.getComponent(SteerableComponent.class);
        SteerableComponent target=origin.getComponent(SteerableComponent.class);

        if(hasMagnet) {

            steer.setSteeringBehavior(AI.getArrive(steer,target,400));
        }

        return entity;
    }

    public static Entity spawnBulletByEntity(World world, TextureRegion textureRegion, TextureRegion[] textureRegions , float size, Position point, float angle, Enums.Player player, BulletSpawnComponent.BulletSpawnType type){

        Entity entity =world.createEntity();
        entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
        entity.edit().add(new Renderable(0));
        //leader.edit().add(addVisSprite(Resource.PLAYER_BULLET,Size.makeSize(size,size),point,true,angle));
        entity.edit().add(new MovementComponent(.05f));

        if(player.value == Enums.Player.NPC.value) {
            entity.edit().add(EntityManager.createBasic(Size.makeSize(size,size),point,true,angle));
            String anim="";
            if(type== BulletSpawnComponent.BulletSpawnType.SINGLE)
                entity.edit().add(EntityManager.createAC(textureRegions, .1f, Animation.PlayMode.LOOP));
            if(type== BulletSpawnComponent.BulletSpawnType.DOUBLE)
                entity.edit().add(EntityManager.createAC(textureRegions, .1f, Animation.PlayMode.LOOP));
            if(type== BulletSpawnComponent.BulletSpawnType.TRIPLE)
                entity.edit().add(EntityManager.createAC(textureRegions, .1f, Animation.PlayMode.LOOP));
            if(type== BulletSpawnComponent.BulletSpawnType.CIRCULAR)
                entity.edit().add(new VisSprite(new Sprite(textureRegion)));
//				leader.edit().add(createAC(Assets.animationAsset.bomb0, .1f, Animation.PlayMode.LOOP));

            world.getSystem(PlayerManager.class).setPlayer(entity, Enums.Player.NPC.value);
            world.getSystem(GroupManager.class).add(entity, Enums.Group.ENEMY_BULLET.value);
        }
        else {
            entity.edit().add(EntityManager.createBasic(Size.makeSize(size*.5f,size*.5f),point,true,angle));
            entity.edit().add(new VisSprite(new Sprite(textureRegion)));
            //entity.edit().add(createAC(Assets.animationAsset.bomb16, .5f, Animation.PlayMode.LOOP));
            world.getSystem(PlayerManager.class).setPlayer(entity, Enums.Player.PLAYER.value);
            world.getSystem(GroupManager.class).add(entity, Enums.Group.PLAYER_BULLET.value);
        }

        //leader.getComponent(SpriteComponent.class).setRotation(angle);

        entity.edit().add(createFSMC(entity));
        entity.edit().add(new CollisionComponent());
        entity.edit().add(createSteerC(entity));

        return entity;
    }

    public static Entity createEnemyOnPath(World world, TextureRegion[] textureRegions, float width, float height, float x, float y,BitmapFont bitmapFont){

        Entity entity =world.createEntity();
        entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
        entity.edit().add(new Renderable(0));
        //leader.edit().add(addVisSprite(Resource.ALIEN3s,Size.makeSize(width*.2f,height*.1f),Position.makePosition(x,y),false,0));
        entity.edit().add(new AnimationComponent(textureRegions,.1f, Animation.PlayMode.LOOP));
        entity.edit().add(new BasicComponent(Size.makeSize(width*.2f,height*.1f), Position.makePosition(x,y),0));
        world.getSystem(PlayerManager.class).setPlayer(entity, Enums.Player.NPC.value);
        world.getSystem(GroupManager.class).add(entity, Enums.Group.ENEMY_SHIP.value);
        entity.edit().add(createSteerC(entity));

        entity.edit().add(createFSMC(entity));
        //addPhysicsBody(playerEntity, BodyType.DynamicBody, 0);
        entity.edit().add(new BulletSpawnComponent(false, BulletSpawnComponent.BulletSpawnType.SINGLE));
        entity.edit().add(new CollisionComponent());

        HealthComponent health=new HealthComponent();
        entity.edit().add(health);

        float percent=(health.health/health.maximumHealth)*100;
        VisText textComponent=new VisText(bitmapFont,String.valueOf((int)percent));
        //textComponent.setScale(.5f, .5f);
        entity.edit().add(textComponent);

        return entity;
    }

    public static Entity createEnemyShip(World world, TextureRegion[] textureRegions, float width, float height, float x, float y, int angle,BitmapFont bitmapFont){

        Entity entity =world.createEntity();
        entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
        entity.edit().add(new Renderable(0));
        //entity.edit().add(addVisSprite(Assets.imageAsset.alien3s,Size.makeSize(width*.2f,height*.1f),Position.makePosition(MathUtils.random(x, y),height*.95f),false,angle));
        entity.edit().add(EntityManager.createAC(textureRegions,.1f, Animation.PlayMode.LOOP));
        entity.edit().add(new BasicComponent(Size.makeSize(width*.1f,height*.06f), Position.makePosition(MathUtils.random(x, y),height*.95f),angle));

        world.getSystem(PlayerManager.class).setPlayer(entity, Enums.Player.NPC.value);
        world.getSystem(GroupManager.class).add(entity, Enums.Group.ENEMY_SHIP.value);

        entity.edit().add(new MovementComponent(.025f));
        entity.edit().add(new BulletSpawnComponent());
        entity.edit().add(createFSMC(entity));
        entity.edit().add(createSteerC(entity));
        entity.edit().add(new CollisionComponent());



        int enemyPower=10;
        int healthValue=enemyPower <10?enemyPower :10;

        HealthComponent health=new HealthComponent(healthValue,healthValue);
        entity.edit().add(health);

        float percent=(health.health/health.maximumHealth)*100;
        VisText textComponent=new VisText(bitmapFont,String.valueOf((int)percent));
        //textComponent.setScale(.5f, .5f);

        entity.edit().add(textComponent);

        //Body body=addPhysicsBody(leader, BodyType.DynamicBody,0);
        //body.setLinearVelocity(-(float)Math.sin(angle)*2, 2*(float)Math.cos(angle));

        return entity;
    }

    public static Entity createHelperPlayer(World world, TextureRegion[] textureRegions, Entity playerEntity, Sound sound){

        Entity entity=world.createEntity();
        entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
        entity.edit().add(new Renderable(0));
        entity.edit().add(EntityManager.createAC(textureRegions,.1f, Animation.PlayMode.LOOP_PINGPONG));
        entity.edit().add(EntityManager.createBasic(Size.makeSize(.5f, .5f), Position.makePosition(MathUtils.random(0,4.8f),0),true,0));

        world.getSystem(PlayerManager.class).setPlayer(entity, Enums.Player.PLAYER.value);
        world.getSystem(GroupManager.class).add(entity, Enums.Group.PLAYER.value);
        entity.edit().add(new BulletSpawnComponent(true, BulletSpawnComponent.BulletSpawnType.SINGLE));
        entity.getComponent(BulletSpawnComponent.class).time=10;

        entity.edit().add(createSteerC(entity,true));
        EntityManager.addSound(entity, sound);

        //EntityManager.createFormationMemberComponent(entity,playerEntity);
        SteerableComponent steerableComponent =entity.getComponent(SteerableComponent.class);
        steerableComponent.setMaxLinearAcceleration(100);
        steerableComponent.setSteeringBehavior(com.nayragames.vis.ai.steer.SB.createSB1(steerableComponent, entity.getComponent(FormationMemberComponent.class).getTargetLocation()));

        //steerableComponent.setSteeringBehavior(SB.createWander(steerableComponent));

		/*
		Arrive<Vector2> arriveSB = new Arrive<Vector2>(steerableComponent,playerEntity.getComponent(SteerableComponent.class)) //
				.setLimiter(new LinearLimiter(3500, 1000)) //
				.setTimeToTarget(0.1f) //
				.setArrivalTolerance(0.001f) //
				.setDecelerationRadius(800);*/

		/*SteerableComponent steeringComponent1=playerEntity.getComponent(SteerableComponent.class);
		steerableComponent.setSteeringBehavior(SB.createSB1(steerableComponent,steeringComponent1));*/

        return entity;
    }

    public static void createRumbleEntity(World world,Entity playerEntity){

        Rectangle rectangle=playerEntity.getComponent(BasicComponent.class).getBoundingRectangle();
        //playerEntity.getComponent(RenderableComponent.class).zIndex=1;

        Entity entity=world.createEntity();
        entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
        entity.edit().add(new Renderable(0));
        Vector2 vector2=getPoint(rectangle,1.5f);
        entity.edit().add(EntityManager.createBasic(Size.makeSize(.25f,.7f), Position.makePosition(vector2.x,vector2.y),true, MathUtils.random(0,360)));
        //entity.edit().add(EntityManager.createAC(Assets.animationAsset.rocket,.1f, Animation.PlayMode.LOOP));
        //entity.edit().add(new MovementComponent());
        entity.edit().add(createSteerC(entity));
        entity.edit().add(new ExpireComponent(.5f, ExpireComponent.ExpireType.OTHER));

        SteerableComponent steer=entity.getComponent(SteerableComponent.class);
        SteerableComponent target=playerEntity.getComponent(SteerableComponent.class);

        steer.setSteeringBehavior(AI.getArrive(steer,target,40));

    }

    public static Entity createFormationEntity(World world, TextureRegion textureRegion, float width, float height){

        Entity entity =world.createEntity();
        entity.edit().add(new Layer(Enums.Layer.NP_LAYER.value));
        entity.edit().add(new Renderable(0));
        EntityManager.addVisSprite(entity,textureRegion,Size.makeSize(width*.075f,height*.075f));
        EntityManager.addTransform(entity,Position.makePosition(width/2f,height*.9f),Scale.makeUnScale(),180);
        world.getSystem(PlayerManager.class).setPlayer(entity,  Enums.Player.NPC.value);

        entity.edit().add(createSteerC(entity));
        entity.edit().add(new LeaderComponent(entity.getComponent(SteerableComponent.class)));
        entity.edit().add(new LeftRightComponent(1,.01f));
        //entity.edit().add(new MovementComponent(1));

        entity.edit().add(createFSMC(entity));
        //entity.getComponent(FSMComponent.class).getStateMachine().changeState(EntityState.MOVE_DOWN);

        return entity;
    }

    public static Entity createLeftRightEntity(World world, TextureRegion textureRegion, float width, float height){

        Entity entity= GenericEntityBuilder.createSprite(world,1,textureRegion,Size.makeSize(width*.1f,height*.1f),Position.makePosition(width/2f,height*.9f),180);
        world.getSystem(PlayerManager.class).setPlayer(entity, Enums.Player.NPC.value);
        entity.edit().add(new LeftRightComponent());
        entity.edit().add(createFSMC(entity));
        entity.edit().add(new BulletSpawnComponent());

        return entity;
    }

    public static Entity createPlayerBullet(World world,float width,float height,float x,float y,float angle){

        Entity entity = world.createEntity();
        entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
        entity.edit().add(new Renderable(0));
        //entity.edit().add(createAC(Assets.animationAsset.bomb16,.1f, Animation.PlayMode.LOOP));
//			entity.edit().add(EntityManager.addVisSprite(Assets.imageAsset.playerBullet, Size.makeSize(width * .025f, height * .03f)));
        EntityManager.addTransform(entity, Position.makePosition(x, y + .1f), Scale.makeUnScale(),angle);
        //entity.edit().add(EntityManager.createBasic(Size.makeSize(width * .025f, height * .03f), Position.makePosition(x, y + .1f), true, angle));
        world.getSystem(PlayerManager.class).setPlayer(entity, Enums.Player.PLAYER.value);
        world.getSystem(GroupManager.class).add(entity, Enums.Group.PLAYER_BULLET.value);
        entity.edit().add(new CollisionComponent());
        entity.edit().add(createFSMC(entity));
        entity.edit().add(new MovementComponent(.085f));

        //GenericEntityBuilder.addSound(entity,Assets.soundAsset.bullet);

        return entity;
    }



    public static Entity createFormationMemberEntity(World world, TextureRegion textureRegion, float width, float height, Entity teamLead){

        Entity entity =world.createEntity();
        entity.edit().add(new Layer(Enums.Layer.NP_LAYER.value));
        entity.edit().add(new Renderable(0));
        EntityManager.addVisSprite(entity,textureRegion,Size.makeSize(width*.075f,height*.075f));
        EntityManager.addTransform(entity,Position.makePosition(1,1),Scale.makeUnScale(),180);
        world.getSystem(PlayerManager.class).setPlayer(entity, Enums.Player.NPC.value);

        entity.edit().add(createSteerC(entity,true));
        entity.edit().add(new BulletSpawnComponent());

        //EntityManager.createFormationMemberComponent(entity,teamLead);
        entity.edit().add(createFSMC(entity));

        return entity;
    }

    public static Entity createPlayerEntity(World world, TextureRegion texture, Size size, Position position, boolean isCentric, Enums.Player playerTag, float angle){

        Entity entity=world.createEntity();
        entity.edit().add(new Layer(Enums.Layer.UI_LAYER.value));
        entity.edit().add(new Renderable(0));
        EntityManager.addVisSprite(entity,texture,size);
        EntityManager.addOrigin(entity,size.x/2,size.y/2);
        EntityManager.addTransform(entity,position, Scale.makeUnScale(),angle);
        entity.edit().add(createSteerC(entity));
        world.getSystem(PlayerManager.class).setPlayer(entity, playerTag.value);

        return entity;
    }

    public static FSMComponent createFSMC(Entity entity){

		FSMComponent component=new FSMComponent(entity);
		entity.edit().add(component);
		//component.getStateMachine().setInitialState(EntityState.NONE);
		return component;
	}
}
