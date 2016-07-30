package com.nayragames.o2d;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.nayragames.gdxutils.model.Calc;
import com.nayragames.gdxutils.model.Position;
import com.nayragames.gdxutils.model.Scale;
import com.nayragames.gdxutils.model.Size;
import com.nayragames.gdxutils.b2d.PhysicsHelper;
import com.nayragames.o2d.component.*;
import com.uwsoft.editor.renderer.components.*;
import com.uwsoft.editor.renderer.components.label.LabelComponent;
import com.uwsoft.editor.renderer.components.particle.ParticleComponent;
import com.uwsoft.editor.renderer.components.physics.PhysicsBodyComponent;
import com.uwsoft.editor.renderer.factory.EntityFactory;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 30-11-2015.
 *
 * Collection of different method for creation of different component of of Entity.
 */

public class EntityManager {

	private static final String TAG = "[" + EntityManager.class.getSimpleName() + "]";
/*
	public static Body addPhysicsBody(Entity entity,BodyType bodyType,float gravity){

		BasicComponent sc=entity.getComponent(BasicComponent.class);
		com.badlogic.gdx.physics.box2d.World physicsWorld=entity.getWorld().getSystem(PhysicsSystem.class).getPhysicsWorld();

		//sc.setOrigin(sc.getWidth()/2, sc.getHeight()/2);
		sc.setOrigin(0, 0);
		BodyDef bodyDef=new BodyDef();
		bodyDef.position.set(sc.getX(), sc.getY());
		bodyDef.type=bodyType;

		PolygonShape polygonShape=new PolygonShape();
		polygonShape.setAsBox(sc.getWidth()/2, sc.getHeight()/2,new Vector2(sc.getWidth()/2,sc.getHeight()/2),0);

		FixtureDef fixture=new FixtureDef();
		fixture.shape=polygonShape;
		fixture.restitution=.5f;
		fixture.density=1;
		fixture.friction=0;

		Body body=physicsWorld.createBody(bodyDef);
		body.setUserData(entity);
		body.setGravityScale(gravity);
		body.setLinearDamping(0);
		body.setAngularDamping(0);
		body.setBullet(false);
		body.setFixedRotation(false);
		body.setSleepingAllowed(true);
		body.setActive(true);

		body.createFixture(fixture);

		Vector2 position=body.getPosition();
		Vector2 origin=body.getPosition().add(body.getLocalCenter());

		//body.setLinearVelocity(2,0);

		//System.out.println(body.getWorldCenter());
		//System.out.println(body.get);
		//body.setTransform(origin.sub((sc.getWidth()/2)*(float)Math.cos(sc.getRotation()*MathUtils.degRad),(sc.getHeight()/2)*(float)Math.sin(sc.getRotation()*MathUtils.degRad)), sc.getRotation()*MathUtils.degRad);
		// body.setTransform(body.getPosition().add(sc.getWidth()*(float)MathUtils.cos((float)Math.toRadians(90))),0), Math.toRadians(sc.getRotation());

		//System.out.println("BODY POSITION before"+body.getPosition());
		// body.setTransform(body.getPosition(),sc.getRotation()*MathUtils.degRad);

		//  System.out.println("BODY POSITION after"+body.getPosition());

		// System.out.println((float)Math.cos(sc.getRotation()*MathUtils.degRad));
		// System.out.println(MathUtils.cosDeg(sc.getRotation()));

		// System.out.println("Angle is"+sc.getRotation());
		//body.setTransform(body.getPosition(), sc.getRotation()*MathUtils.degRad);
		//  System.out.println("Rotation angle is"+Math.cos(sc.getRotation()*MathUtils.degRad));

      *//*  Vector2 posi=body.getPosition();
        Vector2 center=posi.cpy().add(body.getLocalCenter());
        float radius=posi.dst(center);
        float angle=sc.getRotation()+225;

        if(angle>360)
        	angle-=360;

        float xValue=center.x+radius*(float)(Calc.getCosValue(Math.toRadians(angle)));
        float yValue=center.y+radius*(float)(Calc.getSinValue(Math.toRadians(angle)));

        body.setTransform(xValue,yValue,sc.getRotation()*MathUtils.degRad);
        *//*
		transformBody(body,body.getPosition(), sc.getRotation());

		System.out.println("Angle is"+body.getAngle()* MathUtils.radiansToDegrees);

		// System.out.println("Radius is"+radius);
		// System.out.println("Sin"+Calc.getSinValue(Math.toRadians(90)));
		// System.out.println("Cos"+Calc.getCosValue(Math.toRadians(90)));
		// System.out.println("Cos Value"+Math.cos(x));
		//body.setFixedRotation(false);

		entity.edit().add(new PhysicsBody(body));
		entity.edit().add(new PhysicsHelperComponent(sc.getWidth(),sc.getHeight()));
		//sc.getWidth()/2,sc.getHeight()/2));
		//Vector2 vector=GameSceneManager.loader.getOrigin("Gear",1);

		return body;
	}*/

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

	public static ZIndexComponent addZIndex(Entity entity, int zIndex, int layerIndex){

		ZIndexComponent zIndexComponent=new ZIndexComponent();
		zIndexComponent.layerIndex=layerIndex;
		zIndexComponent.setZIndex(zIndex);
		entity.add(zIndexComponent);
		return zIndexComponent;
	}

	public static ScriptComponent addScript(Entity entity){
		ScriptComponent scriptComponent=new ScriptComponent();
		entity.add(scriptComponent);
		return scriptComponent;
	}

	public static ShapeComponent addShape(Entity entity, Size size){

		ShapeComponent shapeComponent=new ShapeComponent();
		shapeComponent.setSize(size.x,size.y);
		entity.add(shapeComponent);
		return shapeComponent ;
	}

	public static ShapeComponent addShape(Entity entity, float radius){

		ShapeComponent shapeComponent=new ShapeComponent();
		shapeComponent.setShape(ShapeComponent.Shape.CIRCLE);
		shapeComponent.setRadius(radius);
		entity.add(shapeComponent);
		return shapeComponent ;
	}

	public static AnimationComponent addAnimation(Entity entity, TextureRegion[] textureRegion, Size size){
		AnimationComponent animationComponent=new AnimationComponent(textureRegion,.1f, Animation.PlayMode.LOOP);
		animationComponent.setSize(size.x,size.y);
		entity.add(animationComponent);
		return animationComponent;
	}

	public static PhysicsBodyComponent addPhysicsBody(World world, Entity entity, BodyDef.BodyType type, float density, float x, float y, float width, float height, boolean isCentric, float angle) {

		PolygonShape shape = new PolygonShape();
		if (isCentric)
			shape.setAsBox(width / 2f, height / 2f, new Vector2(width / 2f, height / 2f), angle);
		else
			shape.setAsBox(width / 2f, height / 2f);

		//World physicsWorld = entity.getWorld().getSystem(PhysicsSystem.class).getPhysicsWorld();
		Body body = world.createBody(PhysicsHelper.createBodyDef(type,x,y));

		body.createFixture(shape,density);
		PhysicsBodyComponent physicsBody = new PhysicsBodyComponent();
		physicsBody.body=body;
		entity.add(physicsBody);
		body.setUserData(entity);
		shape.dispose();

		return physicsBody;
	}

	public static PhysicsBodyComponent addPhysicsBody(World world, Entity entity, Shape shape, float density, BodyDef bodyDef){

		Body body = world.createBody(bodyDef);
		body.createFixture(shape, density);
		PhysicsBodyComponent physicsBody=new PhysicsBodyComponent();
		physicsBody.body=body;
		entity.add(physicsBody);
		body.setUserData(entity);
		return physicsBody;
	}

/*	public static PhysicsBodyComponent addPhysicsBody(Entity entity, BodyDef.BodyType type, float density, float x, float y, float width, float height, boolean isCentric, float angle, String name, BodyEditorLoader loader, float scaleFactor){

		FixtureDef fixtureDef=new FixtureDef();
		fixtureDef.density=density;

		Body body=entity.getWorld().getSystem(PhysicsSystem.class).getPhysicsWorld().createBody(Helper.createBodyDef(type,x,y));
		loader.attachFixture(body,name,fixtureDef,scaleFactor);

		PhysicsBodyComponent physicsBody=new PhysicsBodyComponent();
		physicsBody.body=body;
		entity.add(physicsBody);
		body.setUserData(entity);

		return physicsBody;
	}*/

	/*public static LabelComponent addVisText(Entity entity, BitmapFont font, String text){
		LabelComponent visText=new LabelComponent();
		entity.add(visText);
		return visText;
	}*/

	public static ParentNodeComponent addParentNode(Entity entity, Entity parent){

		ParentNodeComponent parentNodeComponent=new ParentNodeComponent();
		parentNodeComponent.parentEntity=parent;
		entity.add(parentNodeComponent);

		return parentNodeComponent;
	}

	public static NodeComponent addTONode(Entity entity, Entity parent){

		NodeComponent nodeComponent= ComponentRetriever.get(parent,NodeComponent.class);
		nodeComponent.addChild(entity);
		return nodeComponent;
	}

	public static TextureRegionComponent addTextureRegion(Entity entity, TextureRegion texture){

		TextureRegionComponent component = new TextureRegionComponent();
		component.regionName = "text";
		component.region = texture;
		entity.add(component);

		return component;
	}

	public static DimensionsComponent addDimension(Entity entity, float width, float height){

		DimensionsComponent component = new DimensionsComponent();
		component.width=width;
		component.height=height;
		entity.add(component);
		return component;

	}

	public static MainItemComponent addMainItem(Entity entity){

		MainItemComponent mainItemComponent =new MainItemComponent();
		mainItemComponent.entityType= EntityFactory.IMAGE_TYPE;
		entity.add(mainItemComponent);
		return mainItemComponent;
	}


	public static TransformComponent addTransform(Entity entity, Vector2 point, Scale scale, float angle){

		TransformComponent transform=new TransformComponent();
		transform.x=point.x;
		transform.y=point.y;
		transform.scaleX=scale.x;
		transform.scaleY=scale.y;
		transform.rotation=angle;
		entity.add(transform);
		return transform;
	}

	public static TransformComponent addTransform(Entity entity, float x, float y, Scale scale, float angle){

		TransformComponent transform=new TransformComponent();
		transform.x=x;
		transform.y=y;
		transform.scaleX=scale.x;
		transform.scaleY=scale.y;
		transform.rotation=angle;
		entity.add(transform);
		return transform;
	}


	public static TintComponent addTint(Entity entity, Color color){
		TintComponent tint=new TintComponent();
		tint.color=color;
		entity.add(tint);
		return tint;
	}

	public static TintComponent addTint(Entity entity){
		TintComponent tint=new TintComponent();
		entity.add(tint);
		return tint;
	}

	public static MainItemComponent addVisID(Entity entity, String name){

		MainItemComponent visID=new MainItemComponent();
		visID.itemIdentifier=name;
		entity.add(visID);
		return visID;
	}

	/*public static Layer addLayer(Entity entity,int layerId){
		Layer layer=new Layer(layerId);
		entity.edit().add(layer);
		return layer;
	}*/

	public static ZIndexComponent addRenderer(Entity entity, int zIndex){
		ZIndexComponent renderable=new ZIndexComponent();
		renderable.setZIndex(zIndex);
		entity.add(renderable);
		return renderable;
	}

	public static void addSound(Entity entity, Sound sound){
		//if(Pref.isUnMute)
		//	entity.add(new VisSound(sound));
	}

	public static CircularMotion addCircularMotion(Entity entity, float x, float y, float radius, float speed, CircularMotion.MotionType motionType, Enums.Shape shape){

		//shape is rectangle if image is in circular motition doesn't matter that is circle of rectangele

		CircularMotion circularMotion = new CircularMotion(x, y, radius,speed, motionType);
		entity.add(circularMotion);
		if(shape== Enums.Shape.RECTANGLE) {
			DimensionsComponent dimensionsComponent = ComponentRetriever.get(entity, DimensionsComponent.class);
			circularMotion.originX = dimensionsComponent.width / 2f;
			circularMotion.originY = dimensionsComponent.height / 2f;
		}
		return circularMotion;
	}



	public static ParticleComponent addParticle(Entity entity, float scale, String path, String imagePath){

		ParticleEffect particleEffect = new ParticleEffect();
		particleEffect.load(Gdx.files.internal(path), Gdx.files.internal(imagePath));
		particleEffect.scaleEffect(scale);
		ParticleComponent particleComponent=new ParticleComponent();
		particleComponent.particleEffect=particleEffect;

		entity.add(particleComponent);
		return particleComponent;
	}


	public static AnimationComponent createAC(TextureRegion textureRegion[], float duration, Animation.PlayMode mode){

		AnimationComponent animationComponent=new AnimationComponent(textureRegion,duration, mode);
		return animationComponent;
	}


	public static BasicComponent createBasic(Size size, Position point, boolean isCenter, float angle){
		BasicComponent basicComponent=new BasicComponent();
		basicComponent.setSize(size);
		basicComponent.setOrigin(size.x/2,size.y/2);
		if(isCenter) {
			basicComponent.setX(point.x - size.x / 2);
			basicComponent.setY(point.y-size.y/2);
			basicComponent.setCenterX(point.x);
			basicComponent.setCenterY(point.y);
		}else {
			basicComponent.setPosition(point.x,point.y);
			basicComponent.setCenterX(point.x+size.x/2);
			basicComponent.setCenterY(point.y+size.y/2);
		}

		basicComponent.setRotation(angle);

		return basicComponent;
	}


	public static FSMComponent createFSMC(Entity entity){

		FSMComponent component=new FSMComponent(entity);
		entity.add(component);
		//component.getStateMachine().setInitialState(EntityState.NONE);
		return component;
	}

	public static Component createSteerC(Entity entity){

		Component steeringComponent=null;
		if(ComponentMapper.getFor(PhysicsBodyComponent.class).has(entity))
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

		DimensionsComponent playerSprite=entity.getComponent(DimensionsComponent.class);
		TransformComponent transform=entity.getComponent(TransformComponent.class);

		float bounding=(playerSprite.width+playerSprite.height)/4;
		SteerableComponent steerableComponent =new SteerableComponent(bounding,isIndependentFace);
		steerableComponent.getPosition().set(transform.x,transform.y);
		steerableComponent.setOrientation(transform.rotation* MathUtils.degreesToRadians);

		return steerableComponent;
	}

	public static B2DSteerableComponent createB2dSteerC(Entity entity){

		B2DSteerableComponent steer=createB2dSteerC(entity,false);
		return steer;
	}

	public static B2DSteerableComponent createB2dSteerC(Entity entity, boolean isIndependentFace){

		DimensionsComponent spriteComponent=entity.getComponent(DimensionsComponent.class);
		PhysicsBodyComponent physicsComponent=entity.getComponent(PhysicsBodyComponent.class);
		float bounding=(spriteComponent.width+spriteComponent.height)/4;
		B2DSteerableComponent b2dSteer=new B2DSteerableComponent(physicsComponent.body,isIndependentFace,bounding);

		b2dSteer.getPosition().set(physicsComponent.body.getPosition());
		b2dSteer.setOrientation(physicsComponent.body.getAngle());

		return b2dSteer;
	}

	public static FormationMemberComponent createFormationMemberComponent(Entity member, Entity leader){

		FormationMemberComponent memberComponent=new FormationMemberComponent(leader);
		member.add(memberComponent);
		LeaderComponent leaderComponent=leader.getComponent(LeaderComponent.class);
		leaderComponent.addMember(member);

		return memberComponent;
	}

	public static boolean isPlayerBullet(Entity entity){

		boolean b=false;
/*
		if(entity.getWorld().getSystem(PlayerManager.class).getPlayer(entity).equals(Enums.Player.PLAYER.value) &&
				entity.getWorld().getSystem(GroupManager.class).isInGroup(entity, Enums.Group.PLAYER_BULLET.value))
*/
			b=true;
		
		return b;
	}

	public static boolean isEnemyBullet(Entity entity){

		boolean b=false;
	/*	if(entity.getWorld().getSystem(PlayerManager.class).getPlayer(entity).equals(Enums.Player.NPC.name()) &&
				entity.getWorld().getSystem(GroupManager.class).isInGroup(entity, Enums.Group.PLAYER_BULLET.name()))
			b=true;
	*/
		return b;
	}

	public static void setVisibleText(Entity text){
		LabelComponent textComponent=text.getComponent(LabelComponent.class);
		//Color color=textComponent.getColor();
		//textComponent.setColor(color.r,color.g,color.b,1);
	}

	public static void setInVisibleText(Entity text){
		LabelComponent textComponent=text.getComponent(LabelComponent.class);
		//Color color=textComponent.getColor();
		//textComponent.setColor(color.r,color.g,color.b,0);
	}

	public static void setVisible(TintComponent tint){
			Color color=tint.color;
			color.set(new Color(color.r,color.g,color.b,1));
	}

	public static void setInvisible(TintComponent tint){
			Color color=tint.color;
			color.set(new Color(color.r,color.g,color.b,0));
	}

	public static void addActionComponent(Entity entity){

		ActionComponent actionComponent = new ActionComponent();
		//actionComponent.dataArray.add(Actions.sequence(Actions.alpha(0, 1)));

		entity.add(actionComponent);
	}

}
