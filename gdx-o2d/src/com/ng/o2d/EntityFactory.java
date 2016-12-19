package com.ng.o2d;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.uwsoft.editor.renderer.components.TransformComponent;

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

/*	public static Entity createPlayerEntity(World world, TextureRegion texture, Size size, Position position, boolean isCentric, Player playerTag, float angle){

		Entity entity=world.createEntity();
		entity.edit().add(new Layer(Enums.Layer.UI_LAYER.value));
		entity.edit().add(new Renderable(0));
		EntityManager.addTextureRegion(entity,texture,size);
		EntityManager.addOrigin(entity,size.x/2,size.y/2);
		EntityManager.addTransform(entity,position, Scale.makeUnScale(),angle);
		entity.edit().add(EntityManager.createSteerC(entity));
		world.getSystem(PlayerManager.class).setPlayer(entity, playerTag.value);

		return entity;
	}

	public static Entity createBGEntity(World world, TextureRegion texture, Size size, Position position, boolean isCentric, float angle){

		Entity entity=world.createEntity();
		entity.edit().add(new Layer(Enums.Layer.BG_LAYER.value));
		entity.edit().add(new Renderable(0));
		EntityManager.addTextureRegion(entity,texture,size);
		EntityManager.addOrigin(entity,size.x/2,size.y/2);
		if(isCentric)
			EntityManager.addTransform(entity,position.sub(size.scl(.5f)), Scale.makeUnScale(),angle);
		else
			EntityManager.addTransform(entity,position, Scale.makeUnScale(),angle);

		return entity;
	}

	public static Entity createLeftRightEntity(World world, TextureRegion textureRegion, float width, float height){

		Entity entity=GenericEntityBuilder.createSprite(world,1,textureRegion,Size.makeSize(width*.1f,height*.1f),Position.makePosition(width/2f,height*.9f),180);
		world.getSystem(PlayerManager.class).setPlayer(entity, Player.NPC.value);
		entity.edit().add(new LeftRightComponent());
		entity.edit().add(EntityManager.createFSMC(entity));
		entity.edit().add(new BulletSpawnComponent());

		return entity;
	}

	public static Entity createPlayerBullet(World world,float width,float height,float x,float y,float angle){

			Entity entity = world.createEntity();
			entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
			entity.edit().add(new Renderable(0));
			//entity.edit().add(createAC(Assets.animationAsset.bomb16,.1f, Animation.PlayMode.LOOP));
//			entity.edit().add(EntityManager.addTextureRegion(Assets.imageAsset.playerBullet, Size.makeSize(width * .025f, height * .03f)));
			EntityManager.addTransform(entity, Position.makePosition(x, y + .1f), Scale.makeUnScale(),angle);
			//entity.edit().add(EntityManager.createBasic(Size.makeSize(width * .025f, height * .03f), Position.makePosition(x, y + .1f), true, angle));
			world.getSystem(PlayerManager.class).setPlayer(entity, Player.PLAYER.value);
			world.getSystem(GroupManager.class).add(entity, Group.PLAYER_BULLET.value);
			entity.edit().add(new CollisionComponent());
			entity.edit().add(EntityManager.createFSMC(entity));
			entity.edit().add(new MovementComponent(.085f));

			//GenericEntityBuilder.addSound(entity,Assets.soundAsset.bullet);

		return entity;
	}

	public static Entity createFormationEntity(World world, TextureRegion textureRegion, float width, float height){

		Entity entity =world.createEntity();
		entity.edit().add(new Layer(Enums.Layer.NP_LAYER.value));
		entity.edit().add(new Renderable(0));
		EntityManager.addTextureRegion(entity,textureRegion,Size.makeSize(width*.075f,height*.075f));
		EntityManager.addTransform(entity,Position.makePosition(width/2f,height*.9f),Scale.makeUnScale(),180);
		world.getSystem(PlayerManager.class).setPlayer(entity,  Player.NPC.value);

		entity.edit().add(EntityManager.createSteerC(entity));
		entity.edit().add(new LeaderComponent(entity.getComponent(SteerableComponent.class)));
		entity.edit().add(new LeftRightComponent(1,.01f));
		//entity.edit().add(new MovementComponent(1));

		entity.edit().add(EntityManager.createFSMC(entity));
		//entity.getComponent(FSMComponent.class).getStateMachine().changeState(EntityState.MOVE_DOWN);

		return entity;
	}

	public static Entity createFormationMemberEntity(World world, TextureRegion textureRegion, float width, float height, Entity teamLead){

		Entity entity =world.createEntity();
		entity.edit().add(new Layer(Enums.Layer.NP_LAYER.value));
		entity.edit().add(new Renderable(0));
		EntityManager.addTextureRegion(entity,textureRegion,Size.makeSize(width*.075f,height*.075f));
		EntityManager.addTransform(entity,Position.makePosition(1,1),Scale.makeUnScale(),180);
		world.getSystem(PlayerManager.class).setPlayer(entity, Player.NPC.value);

		entity.edit().add(EntityManager.createSteerC(entity,true));
		entity.edit().add(new BulletSpawnComponent());

		EntityManager.createFormationMemberComponent(entity,teamLead);
		entity.edit().add(EntityManager.createFSMC(entity));

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
		entity.edit().add(EntityManager.createSteerC(entity));
		entity.edit().add(new ExpireComponent(.5f, ExpireComponent.ExpireType.OTHER));

		SteerableComponent steer=entity.getComponent(SteerableComponent.class);
		SteerableComponent target=playerEntity.getComponent(SteerableComponent.class);

		Arrive<Vector2> arriveSB = new Arrive<Vector2>(steer, target) //
				.setLimiter(new LinearLimiter(3500, 1000)) //
				.setTimeToTarget(0.1f) //
				.setArrivalTolerance(0.001f) //
				.setDecelerationRadius(40);
		steer.setSteeringBehavior(arriveSB);

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




	}

	public static Entity createHelperPlayer(World world, TextureRegion[] textureRegions, Entity playerEntity, Sound sound){

		Entity entity=world.createEntity();
		entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
		entity.edit().add(new Renderable(0));
		entity.edit().add(EntityManager.createAC(textureRegions,.1f, Animation.PlayMode.LOOP_PINGPONG));
		entity.edit().add(EntityManager.createBasic(Size.makeSize(.5f, .5f), Position.makePosition(MathUtils.random(0,4.8f),0),true,0));

		world.getSystem(PlayerManager.class).setPlayer(entity,Player.PLAYER.value);
		world.getSystem(GroupManager.class).add(entity,Group.PLAYER.value);
		entity.edit().add(new BulletSpawnComponent(Player.PLAYER, BulletSpawnComponent.BulletSpawnType.SINGLE));
		entity.getComponent(BulletSpawnComponent.class).time=10;

		entity.edit().add(EntityManager.createSteerC(entity,true));
		EntityManager.addSound(entity, sound);

		EntityManager.createFormationMemberComponent(entity,playerEntity);
		SteerableComponent steerableComponent =entity.getComponent(SteerableComponent.class);
		steerableComponent.setMaxLinearAcceleration(100);
		steerableComponent.setSteeringBehavior(SB.createSB1(steerableComponent, entity.getComponent(FormationMemberComponent.class).getTargetLocation()));

		//steerableComponent.setSteeringBehavior(SB.createWander(steerableComponent));



		Arrive<Vector2> arriveSB = new Arrive<Vector2>(steerableComponent,playerEntity.getComponent(SteerableComponent.class)) //
				.setLimiter(new LinearLimiter(3500, 1000)) //
				.setTimeToTarget(0.1f) //
				.setArrivalTolerance(0.001f) //
				.setDecelerationRadius(800);*//**//*



SteerableComponent steeringComponent1=playerEntity.getComponent(SteerableComponent.class);
		steerableComponent.setSteeringBehavior(SB.createSB1(steerableComponent,steeringComponent1));


		return entity;
	}

	public static Entity createEnemyShip(World world, TextureRegion[] textureRegions, float width, float height, float x, float y, int angle){

		Entity entity =world.createEntity();
		entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
		entity.edit().add(new Renderable(0));
		//entity.edit().add(addTextureRegion(Assets.imageAsset.alien3s,Size.makeSize(width*.2f,height*.1f),Position.makePosition(MathUtils.random(x, y),height*.95f),false,angle));
		entity.edit().add(EntityManager.createAC(textureRegions,.1f, Animation.PlayMode.LOOP));
		entity.edit().add(new BasicComponent(Size.makeSize(width*.1f,height*.06f), Position.makePosition(MathUtils.random(x, y),height*.95f),angle));

		world.getSystem(PlayerManager.class).setPlayer(entity, Player.NPC.value);
		world.getSystem(GroupManager.class).add(entity, Group.ENEMY_SHIP.value);

		entity.edit().add(new MovementComponent(.025f));
		entity.edit().add(new BulletSpawnComponent());
		entity.edit().add(EntityManager.createFSMC(entity));
		entity.edit().add(EntityManager.createSteerC(entity));
		entity.edit().add(new CollisionComponent());

		GameSceneManager gameSceneManager=world.getSystem(GameSceneManager.class);

		int enemyPower=10;
		int healthValue=enemyPower <10?enemyPower :10;

		HealthComponent health=new HealthComponent(healthValue,healthValue);
		entity.edit().add(health);

		float percent=(health.health/health.maximumHealth)*100;
		VisText textComponent=new VisText(Assets.bitmapFontAsset.bitmapFont,String.valueOf((int)percent));
		//textComponent.setScale(.5f, .5f);

		entity.edit().add(textComponent);

		//Body body=addPhysicsBody(leader, BodyType.DynamicBody,0);
		//body.setLinearVelocity(-(float)Math.sin(angle)*2, 2*(float)Math.cos(angle));

		return entity;
	}

	public static Entity createEnemyOnPath(World world, TextureRegion[] textureRegions, float width, float height, float x, float y){

		Entity entity =world.createEntity();
		entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
		entity.edit().add(new Renderable(0));
		//leader.edit().add(addTextureRegion(Resource.ALIEN3s,Size.makeSize(width*.2f,height*.1f),Position.makePosition(x,y),false,0));
		entity.edit().add(new AnimationComponent(textureRegions,.1f, Animation.PlayMode.LOOP));
		entity.edit().add(new BasicComponent(Size.makeSize(width*.2f,height*.1f), Position.makePosition(x,y),0));
		world.getSystem(PlayerManager.class).setPlayer(entity, Player.NPC.value);
		world.getSystem(GroupManager.class).add(entity,Group.ENEMY_SHIP.value);
		entity.edit().add(EntityManager.createSteerC(entity));

		entity.edit().add(EntityManager.createFSMC(entity));
		//addPhysicsBody(playerEntity, BodyType.DynamicBody, 0);
		entity.edit().add(new BulletSpawnComponent(false, BulletSpawnComponent.BulletSpawnType.SINGLE));
		entity.edit().add(new CollisionComponent());

		HealthComponent health=new HealthComponent();
		entity.edit().add(health);

		float percent=(health.health/health.maximumHealth)*100;
		VisText textComponent=new VisText(Assets.bitmapFontAsset.bitmapFont,String.valueOf((int)percent));
		//textComponent.setScale(.5f, .5f);
		entity.edit().add(textComponent);

		return entity;
	}

	public static Entity createPhysicsBoundary(World world,float width,float height){

		Entity entity=world.createEntity();
		entity.edit().add(new Layer(Enums.Layer.NP_LAYER.value));
		entity.edit().add(new Renderable(0));

//		, Position.makePosition(width*.5f,height*.5f), true,0
		//entity.getComponent(VisSprite.class).sprite.setAlpha(0);

		//world.getManager(GroupManager.class).add(leader, Resource.PLAYER);
		world.getSystem(PlayerManager.class).setPlayer(entity, Group.PLAYER_BULLET.value);

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

	public static Entity createWanderEntities(World world, TextureRegion textureRegion, float width, float height){

		Entity entity=world.createEntity();
		entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
		entity.edit().add(new Renderable(0));
		EntityManager.addTextureRegion(entity, textureRegion, Size.makeSize(width*.1f,width*.1f));
		//,Position.makePosition(width*.5f,height*.5f),true,0
		entity.edit().add(EntityManager.createBasic(Size.makeSize(width*.1f,width*.1f), Position.makePosition(width*.5f,height*.5f),true,0));
		entity.getWorld().getSystem(GroupManager.class).add(entity, Group.PLAYER.value);

		return entity;
	}

	public static Entity spawnBulletByEntity(World world, TextureRegion textureRegion, TextureRegion[] textureRegions , float size, Position point, float angle, Player player, BulletSpawnComponent.BulletSpawnType type){

		Entity entity =world.createEntity();
		entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
		entity.edit().add(new Renderable(0));
		//leader.edit().add(addTextureRegion(Resource.PLAYER_BULLET,Size.makeSize(size,size),point,true,angle));
		entity.edit().add(new MovementComponent(.05f));

		if(player.value ==Player.NPC.value) {
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

			world.getSystem(PlayerManager.class).setPlayer(entity, Player.NPC.value);
			world.getSystem(GroupManager.class).add(entity, Group.ENEMY_BULLET.value);
		}
		else {
				entity.edit().add(EntityManager.createBasic(Size.makeSize(size*.5f,size*.5f),point,true,angle));
				entity.edit().add(new VisSprite(new Sprite(textureRegion)));
				//entity.edit().add(createAC(Assets.animationAsset.bomb16, .5f, Animation.PlayMode.LOOP));
				world.getSystem(PlayerManager.class).setPlayer(entity, Player.PLAYER.value);
				world.getSystem(GroupManager.class).add(entity, Group.PLAYER_BULLET.value);
			}

		//leader.getComponent(SpriteComponent.class).setRotation(angle);

		entity.edit().add(EntityManager.createFSMC(entity));
		entity.edit().add(new CollisionComponent());
		entity.edit().add(EntityManager.createSteerC(entity));

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
		world.getSystem(GroupManager.class).add(entity, Group.COLLECTABLE.value);
		entity.edit().add(EntityManager.createSteerC(entity));

		SteerableComponent steer=entity.getComponent(SteerableComponent.class);
		SteerableComponent target=origin.getComponent(SteerableComponent.class);

		if(hasMagnet) {
			Arrive<Vector2> arriveSB = new Arrive<Vector2>(steer, target) //
					.setLimiter(new LinearLimiter(3500, 1000)) //
					.setTimeToTarget(0.1f) //
					.setArrivalTolerance(0.001f) //
					.setDecelerationRadius(400);
			steer.setSteeringBehavior(arriveSB);
		}

		return entity;
	}

	public static Entity createParticleEntity(World world,float x,float y,float size){

		Entity entity=world.createEntity();
		entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
		entity.edit().add(new Renderable(0));

		//ParticleEffect particleEffect = new ParticleEffect();
		//particleEffect.load(Gdx.files.internal("particle/explosion.p"), Gdx.files.internal("particle"));

		VisParticle pc = new VisParticle(Assets.particleAsset.explosion);
		entity.edit().add(pc);
		pc.getEffect().scaleEffect(size);
		pc.getEffect().setPosition(x, y);
		entity.edit().add(new ExpireComponent(1, ExpireComponent.ExpireType.PARTICLE));
		EntityManager.addSound(entity, Assets.soundAsset.bigExplosion);
		return entity;
	}

	public static boolean isPooled;
	static ParticleEffectPool bucketEffect;

	public static Entity createParticleonDragon(World world,float x,float y,float size){



if(!isPooled){
			//Assets.particleAsset.explosion.scaleEffect(.2f);
			bucketEffect =new ParticleEffectPool(Assets.particleAsset.explosion,10,20);
			isPooled=true;
		}


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
*/
	public static void createPlayerParts(final Body body, TransformComponent transform, float revertPosition){

		final Vector2 pos=body.getPosition();
    	final Vector2 velocity=body.getLinearVelocity();
		final float angle=body.getAngle();

		Gdx.app.log(TAG,"Transform"+transform.x+"X"+transform.y);
		Gdx.app.log(TAG,"Position"+pos.x+"AND "+pos.y);
		//GameSceneManager.death++;
		//GameManager.saveData();

		/*  for(int i=0;i<10;i++) {
			  Entity entity ;
			   if(i<7)
				   entity = GenericEntityBuilder.createPhysicsShape(world, 1, Color.BLACK, Size.makeSize(MathUtils.random(.05f, .125f), MathUtils.random(.05f, .125f)), Position.makePosition(MathUtils.random(transform.getX(), transform.getX() + .5f), MathUtils.random(transform.getY(), transform.getY() + .25f)), angle, BodyDef.BodyType.DynamicBody, 1);
				else
				   entity = GenericEntityBuilder.createPhysicsShape(world, 1, Color.WHITE, Size.makeSize(MathUtils.random(.05f, .125f), MathUtils.random(.05f, .125f)), Position.makePosition(MathUtils.random(transform.getX(), transform.getX() + .5f), MathUtils.random(transform.getY(), transform.getY() + .25f)), angle, BodyDef.BodyType.DynamicBody, 1);

			  entity.getComponent(PhysicsBody.class).body.setLinearVelocity(velocity);
		  }

				float dst=pos.x+2.5f-4.5f;

				Timeline.createSequence()
						.pushPause(.1f)
						.push(Tween.to(world.getSystem(CameraManager.class).getCamera(), CameraAccessor.POS_XY,2).target(revertPosition,2.4f))
						.setCallback(new TweenCallback() {
							@Override
							public void onEvent(int type, BaseTween<?> source) {
								for(int i=0;i<world.getSystem(PlayerSystem.class).status.length;i++)
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
						.start(GameManager.game.tweenManager);
		//	}
		//});*/
	}

	public static Entity createFPSEntity(){

		Entity entity=new Entity();
		/*EntityManager.ad(entity, Assets.bitmapFontAsset.bitmapFont,"");
		EntityManager.addLayer(entity,1);*/

		return entity;
	}

}

