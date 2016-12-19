package com.ng.vis.system;/*
package com.nayragames.vis.system;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.runtime.component.PhysicsBody;
import com.kotcrab.vis.runtime.component.Transform;
import com.kotcrab.vis.runtime.component.Variables;
import com.kotcrab.vis.runtime.system.CameraManager;
import com.kotcrab.vis.runtime.system.physics.PhysicsSystem;
import com.nayragames.GameManager;
import com.nayragames.Pref;
import com.nayragames.ecs.EntityFactory;
import com.nayragames.ecs.GenericEntityBuilder;
import com.nayragames.ecs.PhysicsHelper;
import com.nayragames.ecs.manager.GameSceneManager;
import com.nayragames.model.Position;
import com.nayragames.model.Size;
import com.nayragames.view.screens.GameOverScreen;

*/
/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 28-12-2015.
 *
 * System that is helper of game, Entity insertion, deletion and camera movement by player position.
 *
 *//*


public class PlayerSystem extends BaseSystem {

	private static final String TAG = "[" + PlayerSystem.class.getSimpleName() + "]";

	private float maxVelocity = 3.5f;

	private CameraManager cameraManager;
	private PhysicsSystem physicsSystem;
	private PhysicsManager physicsManager;
	private ComponentMapper<Variables> variablesCm;

	private World physicsWorld;
	public boolean isCollide, isBodyRemove, isBodyCreate;

	float pos[]=new float[]{61.5f,10,26,38};
	public boolean status[]=new boolean[]{false,false,false,false};
	public Array<Entity> array[]=new Array[4];

	@Override
	protected void initialize() {
		physicsWorld =physicsSystem.getPhysicsWorld();
	}

	@Override
	protected void begin() {
		isBodyRemove =true;
	}

	@Override
	protected void processSystem() {

		if(gm.playerPosition.getX()>gm.milestone[GameSceneManager.timeline+1]){
			if(GameSceneManager.timeline<gm.milestone.length-2) {
				GameSceneManager.timeline++;
				if(GameSceneManager.stage==Pref.getInteger(Pref.IntegerValue.STAGE) &&GameSceneManager.timeline>Pref.getInteger(Pref.IntegerValue.TIMELINE))
					Pref.putInteger(Pref.IntegerValue.TIMELINE, GameSceneManager.timeline);
			}
			else {
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						GameManager.setScreen(new GameOverScreen(GameManager.game));
					}
				});
			}
		}

		Vector3 camPos=cameraManager.getCamera().position;
		if(!gm.contactListener.isCollide && camPos.x<99.5f)
			camPos.set(gm.playerPosition.getX() + 2, camPos.y, 0);

		gm.playerBody.setLinearVelocity(gm.playerBody.getLinearVelocity().clamp(0, maxVelocity));
		updateScore();

		activateGearEntity(camPos);
		activateRock(camPos);
		spawnEntity(camPos);
		//addDynamicObject(camPos);

		    if(!physicsWorld.isLocked() ) {
            	if (isBodyRemove) removeBodyFromWorld();

            	isBodyCreate = true;
            	isBodyRemove = false;

            	if (isBodyCreate) addBodyIntoWorld();
        	}

			//activateBodyOnScreen();
	}

	private void removeBodyFromWorld(){
		for (int i = 0; i < physicsManager.entities.size(); i++) {
			if (!physicsManager.checkAwake(physicsManager.entities.get(i)))
				getWorld().getEntity(physicsManager.entities.get(i)).deleteFromWorld();
			else if (variablesCm.get(physicsManager.entities.get(i)) != null) {
				if (variablesCm.get(physicsManager.entities.get(i)).getInt("phy") == 1)
					physicsManager.removeBody(physicsManager.entities.get(i));
				else if (variablesCm.get(physicsManager.entities.get(i)).getInt("phy") == 2) {
					getWorld().getEntity(physicsManager.entities.get(i)).deleteFromWorld();
				}
			}
		}
	}

	private void addBodyIntoWorld(){

		if(isCollide) {
			Gdx.app.postRunnable(new Runnable() {
				@Override
				public void run() {
					EntityFactory.createPlayerParts(gm.playerBody, getWorld(), gm.playerPosition, gm.milestone[GameSceneManager.timeline] + 2.5f);
					gm.contactListener.isCollide = true;
					isCollide = false;
					isBodyRemove = true;
					isBodyCreate = false;
				}
			});
		}
	}

	private void activateBodyOnScreen(){
		*/
/*        for(int i=0;i<physicsManager.entities.size();i++) {
            Entity entity=getWorld().getEntity(physicsManager.entities.get(i));
            Bounds cm=boundsCm.get(entity);
            PhysicsBody physics=physicsCm.get(entity);
            if(boundsCm.has(entity)) {
                if (cm.overlaps(rectangle))
                    physics.body.setActive(true);
                else
                    physics.body.setActive(false);
            }
        }*//*

	}

	private void updateScore(){
		gm.scoreText.setText("Score : "+String.valueOf(GameSceneManager.stage*104+(int) gm.playerPosition.getX()));
		GameSceneManager.score=GameSceneManager.stage*104+ gm.playerPosition.getX();
	}

	private void activateGearEntity(Vector3 camPos){
		for (Entity e : gm.gearEntities)
			if (!physicsWorld.isLocked() && e.getComponent(Transform.class).getX() - camPos.x < 2) {
				if (!e.getComponent(PhysicsBody.class).body.isActive()) {
					e.getComponent(PhysicsBody.class).body.setActive(true);
					e.getComponent(PhysicsBody.class).body.setAngularVelocity(2);
				}
			}
	}

	private void activateRock(Vector3 camPos){
		for (Entity e : gm.rocks)
			if (!physicsWorld.isLocked() &&e.getComponent(Transform.class).getX() - camPos.x < 2) {
				if (!e.getComponent(PhysicsBody.class).body.isActive()) {
					e.getComponent(PhysicsBody.class).body.setActive(true);
				}
			}
	}

	private void spawnEntity(Vector3 camPos) {

		for (Entity e : gm.spawner) {
			if (!physicsWorld.isLocked() && e.getComponent(Transform.class).getX() - camPos.x < 2 && !e.getComponent(Variables.class).getBoolean("Boolean")) {
				createBallWithTimer(e.getComponent(Transform.class).getX(), e.getComponent(Transform.class).getY());
				e.getComponent(Variables.class).putBoolean("Boolean", true);
			}
		}
	}

	private void addDynamicObject(Vector3 camPos){

			for (int i=0;i<pos.length;i++)
				if(!gm.contactListener.isCollide)
				if(!status[i] ){
					if(pos[i]-camPos.x<6 && pos[i]-camPos.x>-5 ) {
						array[i] = PhysicsHelper.addObject(getWorld(),i);
						status[i] = true;
					}
				}else{
					if(pos[i]-camPos.x<-5) {
						deleteArray(i);
						status[i] = false;
					}
				}
	}

	private void createBallWithTimer(float x,float y){
		for(int i = 0; i< MathUtils.random(2,3); i++) {
			GenericEntityBuilder.createPhysicsShape(getWorld(), 1, Color.BLACK, Size.makeSize(.25f, .25f), Position.makePosition(x, y), 0, BodyDef.BodyType.DynamicBody, 1);
		}
	}

	public void deleteArray(int i){
		for (Entity e:array[i]) {
			e.getComponent(Variables.class).putInt("phy", 2);
		}
	}

	@Override
	protected boolean checkProcessing() {
		return !GameManager.isPaused();
	}
}
*/
