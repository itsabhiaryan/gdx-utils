package com.nayragames.vis;

import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.managers.GroupManager;
import com.artemis.managers.PlayerManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ObjectMap;
import com.kotcrab.vis.runtime.component.*;
import com.nayragames.gdxutils.Position;
import com.nayragames.gdxutils.Scale;
import com.nayragames.gdxutils.Size;
import com.nayragames.vis.component.*;

/**
 * Collection of different method for creation of different component of of Entity.
 *
 * Created by ARYAN on 30-11-2015.
 */

public class EntityManager {

	private static final String TAG = "[" + EntityManager.class.getSimpleName() + "]";

	/*public static Body addPhysicsBody(Entity entity,BodyType bodyType,float gravity){

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



	public static ShapeComponent addShape(Entity entity,Size size){

		ShapeComponent shapeComponent=new ShapeComponent();
		shapeComponent.setSize(size.x,size.y);
		entity.edit().add(shapeComponent);
		return shapeComponent ;
	}

	public static ShapeComponent addShape(Entity entity,float radius){

		ShapeComponent shapeComponent=new ShapeComponent();
		shapeComponent.setShape(ShapeComponent.Shape.CIRCLE);
		shapeComponent.setRadius(radius);
		entity.edit().add(shapeComponent);
		return shapeComponent ;
	}

	public static AnimationComponent addAnimation(Entity entity, TextureRegion[] textureRegion, Size size){
		AnimationComponent animationComponent=new AnimationComponent(textureRegion,.1f, Animation.PlayMode.LOOP);
		animationComponent.setSize(size.x,size.y);
		entity.edit().add(animationComponent);
		return animationComponent;
	}




	public static VisText addVisText(Entity entity, BitmapFont font, String text){
		VisText visText=new VisText(font, text);
		entity.edit().add(visText);
		return visText;
	}

	public static VisSprite addVisSprite(Entity entity, TextureRegion texture, Size size){

		VisSprite visSprite=new VisSprite(texture);
		visSprite.setSize(size.x,size.y);
		entity.edit().add(visSprite);
		return visSprite;
	}

	public static Transform addTransform(Entity entity, Vector2 point, Scale scale, float angle){

		Transform transform=new Transform();
		transform.setPosition(point.x,point.y);
		transform.setScale(scale.x,scale.y);
		transform.setRotation(angle);
		entity.edit().add(transform);
		return transform;
	}

	public static Transform addTransform(Entity entity, float x, float y, Scale scale, float angle){

		Transform transform=new Transform();
		transform.setPosition(x,y);
		transform.setScale(scale.x,scale.y);
		transform.setRotation(angle);
		entity.edit().add(transform);
		return transform;
	}

	public static Variables addVariable(Entity e){

		ObjectMap<String, String> var=new ObjectMap<String, String>();
		Variables variables=new Variables(var);
		e.edit().add(variables);

		return variables;
	}

	public static Origin addOrigin(Entity entity, float originX, float originY){
		Origin origin=new Origin(originX,originY);
		entity.edit().add(origin);
		return origin;
	}

	public static Tint addTint(Entity entity, Color color){
		Tint tint=new Tint(color);
		entity.edit().add(tint);
		return tint;
	}

	public static Tint addTint(Entity entity){
		Tint tint=new Tint();
		entity.edit().add(tint);
		return tint;
	}

	public static VisID addVisID(Entity entity, String name){

		VisID visID=new VisID(name);
		entity.edit().add(visID);
		return visID;
	}

	public static Layer addLayer(Entity entity, int layerId){
		Layer layer=new Layer(layerId);
		entity.edit().add(layer);
		return layer;
	}

	public static Renderable addRenderer(Entity entity, int zIndex){
		Renderable renderable=new Renderable(zIndex);
		entity.edit().add(renderable);
		return renderable;
	}

	public static void addSound(Entity entity, Sound sound){
		//if(Pref.isUnMute)
			entity.edit().add(new VisSound(sound));
	}

	public static CircularMotion addCircularMotion(Entity entity, float x, float y, float radius, float speed, CircularMotion.MotionType motionType){

		CircularMotion circularMotion = new CircularMotion(x, y, radius,speed, motionType);
		entity.edit().add(circularMotion);

		return circularMotion;
	}

	public static VisParticle addVisParticle(Entity entity, float scale, String path, String imagePath){

		ParticleEffect particleEffect = new ParticleEffect();
		particleEffect.load(Gdx.files.internal(path), Gdx.files.internal(imagePath));
		particleEffect.scaleEffect(scale);
		VisParticle visParticle=new VisParticle(particleEffect);

		entity.edit().add(visParticle);
		return visParticle;
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
		entity.edit().add(component);
		//component.getStateMachine().setInitialState(EntityState.NONE);
		return component;
	}

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
		steerableComponent.setOrientation(transform.getRotation()*MathUtils.degreesToRadians);

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

	public static FormationMemberComponent createFormationMemberComponent(Entity member,Entity leader){

		FormationMemberComponent memberComponent=new FormationMemberComponent(leader);
		member.edit().add(memberComponent);
		LeaderComponent leaderComponent=leader.getComponent(LeaderComponent.class);
		leaderComponent.addMember(member);

		return memberComponent;
	}

	public static boolean isPlayerBullet(Entity entity){

		boolean b=false;
		if(entity.getWorld().getSystem(PlayerManager.class).getPlayer(entity).equals(Enums.Player.PLAYER.value) &&
				entity.getWorld().getSystem(GroupManager.class).isInGroup(entity, Enums.Group.PLAYER_BULLET.value))
			b=true;
		
		return b;
	}
	
	public static boolean isEnemyBullet(Entity entity){

		boolean b=false;
		if(entity.getWorld().getSystem(PlayerManager.class).getPlayer(entity).equals(Enums.Player.NPC.name()) &&
				entity.getWorld().getSystem(GroupManager.class).isInGroup(entity, Enums.Group.PLAYER_BULLET.name()))
			b=true;
		
		return b;
	}

	public static void setVisibleText(Entity text){
		VisText textComponent=text.getComponent(VisText.class);
		//Color color=textComponent.getColor();
		//textComponent.setColor(color.r,color.g,color.b,1);
	}

	public static void setInVisibleText(Entity text){
		VisText textComponent=text.getComponent(VisText.class);
		//Color color=textComponent.getColor();
		//textComponent.setColor(color.r,color.g,color.b,0);
	}

	public static void setVisible(Tint tint){
			Color color=tint.getTint();
			tint.setTint(new Color(color.r,color.g,color.b,1));
	}

	public static void setInvisible(Tint tint){
			Color color=tint.getTint();
			tint.setTint(new Color(color.r,color.g,color.b,0));
	}
}
