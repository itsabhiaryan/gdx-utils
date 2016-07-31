package com.nayragames.vis;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kotcrab.vis.runtime.component.*;
import com.nayragames.gdxutils.model.Position;
import com.nayragames.gdxutils.model.Scale;
import com.nayragames.gdxutils.model.Size;
import com.nayragames.vis.component.AnimationComponent;
import com.nayragames.vis.component.BasicComponent;
import com.nayragames.vis.component.ExpireComponent;

//import com.badlogic.gdx.ai.steer.behaviors.Arrive;
//import com.badlogic.gdx.ai.steer.limiters.LinearLimiter;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 30-11-2015.
 *
 * Factory class of Entity used in game.
 *
 */

public class EntityFactory {

	private static final String TAG = "[" + EntityFactory.class.getSimpleName() + "]";


	public static Entity createShape(World world, int layerId, Color color, Size size, Position point , float angle){

		Entity entity=world.createEntity();
		//ShapeComponent shapeComponent=new ShapeComponent();
		//shapeComponent.setSize(size.x,size.y);
		//entity.edit().add(shapeComponent);
		//return shapeComponent ;
		EntityManager.addShape(entity,size);

		if(point.isCentric) {
			EntityManager.addTransform(entity, point.x - size.x / 2f, point.y - size.y / 2f, Scale.makeUnScale(), angle);
		}
		else {

			Transform transform=new Transform();
			transform.setPosition(point.x,point.y);
			transform.setScale(1,1);
			transform.setRotation(angle);
			entity.edit().add(transform);
			//EntityManager.addTransform(entity, point, Scale.makeUnScale(), angle);
		}

		entity.edit().add(new Origin(size.x/2f,size.x/2f));
		entity.edit().add(new Tint(color));
		entity.edit().add(new Layer(layerId));
		entity.edit().add(new Renderable(0));
		//EntityManager.addOrigin(entity,size.x/2f,size.y/2f);
		//EntityManager.addTint(entity,color);
		//EntityManager.addVisID(entity,""));
		// EntityManager.addLayer(entity,layerId);
		//EntityManager.addRenderer(entity,0);

		return entity;
	}


	public static Entity createBGEntity(World world, TextureRegion texture, Size size, Position position, boolean isCentric, float angle){

		Entity entity=world.createEntity();
		entity.edit().add(new Layer(Enums.Layer.BG_LAYER.value));
		entity.edit().add(new Renderable(0));
		EntityManager.addVisSprite(entity,texture,size);
		EntityManager.addOrigin(entity,size.x/2,size.y/2);
		if(isCentric)
			EntityManager.addTransform(entity,position.sub(size.scl(.5f)), Scale.makeUnScale(),angle);
		else
			EntityManager.addTransform(entity,position, Scale.makeUnScale(),angle);

		return entity;
	}





	public static Vector2 getPoint(Rectangle rectangle, float scale){

		float valueX= MathUtils.random(rectangle.getX()-scale,rectangle.getX()+rectangle.getWidth()+scale);
		float valueY= MathUtils.random(rectangle.getY()-scale,rectangle.getY()+rectangle.getHeight()+scale);

		while (rectangle.contains(valueX,valueY)){

			valueX= MathUtils.random(rectangle.getX()-scale,rectangle.getX()+rectangle.getWidth()+scale);
			valueY= MathUtils.random(rectangle.getY()-scale,rectangle.getY()+rectangle.getHeight()+scale);
		}
		return new Vector2(valueX,valueY);
	}


	public static void createRocket(World world,float width,float height,float x,float y) {

/*
		Array<Entity> entities = world.getSystem(CollisionSystem.class).getEnemyShip();
			int ship=0;
			{

			Entity entity = world.createEntity();
			entity.edit().add(new com.kotcrab.vis.runtime.component.Layer(Enums.Layer.PLAYER_LAYER.value));
			entity.edit().add(new Renderable(0));
			entity.edit().add(new AnimationComponent(Assets.animationAsset.rocket, .1f, Animation.PlayMode.LOOP));

			entity.edit().add(EntityManager.createBasic(Size.makeSize(width * .065f, height * .1f), Position.makePosition(x - .5f, y ), true, 0));
			world.getSystem(PlayerManager.class).setPlayer(entity, Player.PLAYER.value);
			world.getSystem(GroupManager.class).add(entity, Group.PLAYER_BULLET.value);
			entity.edit().add(new CollisionComponent());
			entity.edit().add(EntityManager.createFSMC(entity));
			entity.edit().add(EntityManager.createSteerC(entity));
			SteerableComponent steer = entity.getComponent(SteerableComponent.class);

			if (entities.get(ship).getComponent(SteerableComponent.class) != null)
				steer.setSteeringBehavior(SB.createBulletBehaviour(steer, entities.get(ship).getComponent(SteerableComponent.class)));

				GenericEntityBuilder.addSound(entity,Assets.soundAsset.roc);
			}

			{
				Entity entity = world.createEntity();
				entity.edit().add(new com.kotcrab.vis.runtime.component.Layer(Enums.Layer.PLAYER_LAYER.value));
				entity.edit().add(new Renderable(0));
				entity.edit().add(new AnimationComponent(Assets.animationAsset.rocket, .1f, Animation.PlayMode.LOOP));

				entity.edit().add(EntityManager.createBasic(Size.makeSize(width * .065f, height * .1f), Position.makePosition(x + .5f, y), true, 0));
				world.getSystem(PlayerManager.class).setPlayer(entity, Player.PLAYER.value);
				world.getSystem(GroupManager.class).add(entity, Group.PLAYER_BULLET.value);
				entity.edit().add(new CollisionComponent());
				entity.edit().add(EntityManager.createFSMC(entity));
				entity.edit().add(EntityManager.createSteerC(entity));
				SteerableComponent steer = entity.getComponent(SteerableComponent.class);

				if (entities.get(ship).getComponent(SteerableComponent.class) != null)
					steer.setSteeringBehavior(SB.createBulletBehaviour(steer, entities.get(ship).getComponent(SteerableComponent.class)));

				GenericEntityBuilder.addSound(entity,Assets.soundAsset.roc);
			}

*/

	}









	public static Entity createWanderEntities(World world, TextureRegion textureRegion, float width, float height){

		Entity entity=world.createEntity();
		entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
		entity.edit().add(new Renderable(0));
		EntityManager.addVisSprite(entity, textureRegion, Size.makeSize(width*.1f,width*.1f));
		//,Position.makePosition(width*.5f,height*.5f),true,0
		entity.edit().add(EntityManager.createBasic(Size.makeSize(width*.1f,width*.1f), Position.makePosition(width*.5f,height*.5f),true,0));
		entity.getWorld().getSystem(GroupManager.class).add(entity, Enums.Group.PLAYER.value);

		return entity;
	}



	public static void createMagnetAnimation(World world, TextureRegion[] textureRegions, BasicComponent basicComponent, Sound sound){

		Entity entity=world.createEntity();
		entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
		entity.edit().add(new Renderable(2));
		entity.edit().add(new AnimationComponent(textureRegions,.1f, Animation.PlayMode.LOOP));
		entity.edit().add(basicComponent);
		entity.edit().add(new ExpireComponent(10, ExpireComponent.ExpireType.MAGNET));
		EntityManager.addSound(entity, sound);
	}

	public static void createShieldAnimation(World world, TextureRegion[] textureRegions, BasicComponent basicComponent, Sound sound){

		Entity entity=world.createEntity();
		entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
		entity.edit().add(new Renderable(2));
		entity.edit().add(new AnimationComponent(textureRegions,.1f, Animation.PlayMode.LOOP));
		entity.edit().add(basicComponent);
		entity.edit().add(new ExpireComponent(10, ExpireComponent.ExpireType.SHIELD));

		EntityManager.addSound(entity, sound);

	}

	public static Entity createBoomAnimation(World world, TextureRegion[] textureRegion, float x, float y, float size, int type, Sound sound){

		Entity entity=world.createEntity();
		entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
		entity.edit().add(new Renderable(0));

		if(type==0)
		entity.edit().add(new AnimationComponent(textureRegion,.1f, Animation.PlayMode.NORMAL));
		if(type==1)
		entity.edit().add(new AnimationComponent(textureRegion,.1f, Animation.PlayMode.NORMAL));

		entity.edit().add(new BasicComponent(Size.makeSize(size,size), Position.makePosition(x,y),0));
		entity.edit().add(new ExpireComponent(.5f, ExpireComponent.ExpireType.ANIMATION));
		EntityManager.addSound(entity, sound);

		return entity;
	}



	public static Entity createParticleEntity(World world,float x,float y,float size,ParticleEffect particleEffect){

		Entity entity=world.createEntity();
		entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
		entity.edit().add(new Renderable(0));

		//ParticleEffect particleEffect = new ParticleEffect();
		//particleEffect.load(Gdx.files.internal("particle/explosion.p"), Gdx.files.internal("particle"));

		VisParticle pc = new VisParticle(particleEffect);
		entity.edit().add(pc);
		pc.getEffect().scaleEffect(size);
		pc.getEffect().setPosition(x, y);
		entity.edit().add(new ExpireComponent(1, ExpireComponent.ExpireType.PARTICLE));
		//EntityManager.addSound(entity, Assets.soundAsset.bigExplosion);
		return entity;
	}

	public static boolean isPooled;
	static ParticleEffectPool bucketEffect;

	public static Entity createParticleonDragon(World world,float x,float y,float size){


		/*if(!isPooled){
			//Assets.particleAsset.explosion.scaleEffect(.2f);
			bucketEffect =new ParticleEffectPool(Assets.particleAsset.explosion,10,20);
			isPooled=true;
		}*/

		Entity entity=world.createEntity();
		entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
		entity.edit().add(new Renderable(3));

		ParticleEffect particleEffect = new ParticleEffect();
		particleEffect.load(Gdx.files.internal("particle/explosion.p"), Gdx.files.internal("particle"));

		//ParticleEffectPool.PooledEffect particleEffect=null;

		VisParticle pc = new VisParticle(particleEffect);
		entity.edit().add(pc);
		pc.getEffect().scaleEffect(size);
		pc.getEffect().setPosition(x, y);
		entity.edit().add(new ExpireComponent(1, ExpireComponent.ExpireType.PARTICLE));

		return entity;
	}



	public static Entity createFPSEntity(World world,BitmapFont bitmapFont){

		Entity entity=world.createEntity();
		EntityManager.addVisText(entity, bitmapFont,"");
		EntityManager.addLayer(entity,1);

		return entity;
	}

}
