
package com.nayragames.o2d.system;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.TweenCallback;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.Bag;
import com.badlogic.ashley.utils.ImmutableArray;
import com.nayragames.o2d.Constants;
import com.nayragames.o2d.Enums;
import com.nayragames.o2d.TweenBuilder;
import com.nayragames.o2d.component.*;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.DimensionsComponent;
import com.uwsoft.editor.renderer.components.MainItemComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.label.LabelComponent;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 30-11-2015.
 *
 * System that handle collision of entity which having CollisionComponent.
 */

public class CollisionSystem extends IteratingSystem {

	private static final String TAG = "[" + CollisionSystem.class.getSimpleName() + "]";

	ComponentMapper<FormationMemberComponent> memberMapper;
	ComponentMapper<LeaderComponent> leaderMapper;
	ComponentMapper<HealthComponent> healthMapper;
	ComponentMapper<LabelComponent> textMapper;
	ComponentMapper<CollisionComponent> collisionMapper;
	ComponentMapper<TransformComponent> transformCm;
//	ComponentMapper<VisSprite> spriteMapper;
	ComponentMapper<SteerableComponent> steerMapper;

	SceneLoader sceneLoader;

	private static final int LIFE_INCREMENT=50;
	private boolean isDragonHit;
	private Bag<CollisionPair> collisionPairs;

	public CollisionSystem(final SceneLoader sceneLoader) {
		super(Family.all(CollisionComponent.class, TransformComponent.class).get());

		this.sceneLoader=sceneLoader;
		collisionPairs=new Bag<CollisionPair>();


		collisionPairs.add(new CollisionPair(Enums.Group.PLAYER.value, Enums.Group.COLLECTABLE.value, new CollisionHandler() {
			@Override
			public void handleCollision(Entity player, final Entity collection) {

				TransformComponent basicComponent1 = transformCm.get(player);
				TransformComponent basicComponent = transformCm.get(collection);
				CollisionComponent collisionComponent = collisionMapper.get(collection);

				if(steerMapper.has(collection)){
					SteerableComponent steer=collection.getComponent(SteerableComponent.class);
					steer.setSteeringBehavior(null);
				}

				if(!collisionMapper.get(collection).isCollected) {

					collisionMapper.get(collection).isCollected=true;

					/*TweenBuilder.applyScale(basicComponent1);
					TweenBuilder.applyCollectionTween(basicComponent,new TweenCallback() {
						@Override
						public void onEvent(int type, BaseTween<?> source) {
							sceneLoader.getEngine().removeEntity(collection);
						}
					});*/
				}

			}
		}));
	}


/**
	 * Decrease health of enemy by particular value which is associated with HealthComponent.
	 * @param entity Entity whose health is going to be decrease.
	 *
	 */


	private HealthComponent decrementHealth(Entity entity){

		HealthComponent health = healthMapper.get(entity);
		if(health.health>0) health.health -= health.decrement;
		float percent=(health.health/health.maximumHealth)*100;
		LabelComponent text=textMapper.get(entity);
		text.setText(String.valueOf((int) (percent)));

		return health;
	}


/**
	 * Set value to TextComponent after increasing the value of TextComponent by value.
	 * @param text TextComponent on which operation performed.
	 * @param value value which is increased.
	 *
	 */


	private void setText(LabelComponent text, int value){
		int total = Integer.parseInt(text.getText().toString());
		total += value;
		text.setText(String.valueOf(total));
	}

	private void isMemberEntity(Entity entity){
		if(memberMapper.has(entity)){
			FormationMemberComponent formationMember=memberMapper.get(entity);
			if(leaderMapper.has(formationMember.leader)) {
				LeaderComponent leader=leaderMapper.get(formationMember.leader);
				leader.removeMember(entity);
			}
		}
	}

	private void isLeaderEntity(Entity entity){
		if(leaderMapper.has(entity)){
			LeaderComponent leader=leaderMapper.get(entity);
			for(Entity e:leader.memberEntity)
				leader.removeMember(e);
		}
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {

	}

	@Override
	public void update(float deltaTime) {

		for(int i = 0; collisionPairs.size() > i; i++) {
			collisionPairs.get(i).checkForCollisions();
		}

	}


/**
	 * Method called when rocket is try to fire by player .
	 * @return total enemy which is either ship or dragon and having position above the player.
	 *
	 */



/*public Array<Entity> getEnemyShip(){

		Array<Entity> ship=new Array<Entity>();
		ImmutableBag<Entity> x=playerManager.getEntitiesOfPlayer(Player.NPC.value);

		for (Entity entity:x) {
			if((groupManager.isInGroup(entity,Group.ENEMY_SHIP.value)||groupManager.isInGroup(entity, Group.ENEMY_DRAGON.value))&& collisionMapper.has(entity)&& isTopPlayer(entity))
				ship.add(entity);
		}
		return ship;
	}
*/


/*private boolean isTopPlayer(Entity entity){

		Transform basic = transformCm.get(entity);
		return  gm.playerBasic.getY() + gm.playerBasic.getHeight() < basic.getY() + basic.getHeight();
	}*/



/**
	 * CollisionPair class handle collision between two group of entity.
	 * we can add number of different pair of group and handler that handle if entities of group collide.
	 *
	 */


	private class CollisionPair {

		private ImmutableArray<Entity> groupEntitiesA;
		private ImmutableArray<Entity> groupEntitiesB;
		private CollisionHandler handler;

		public CollisionPair(String group1, String group2, CollisionHandler handler) {
			//groupEntitiesA = world.getSystem(GroupManager.class).getEntities(group1);

			//ItemWrapper itemWrapper=new ItemWrapper(sceneLoader.getRoot());
			//itemWrapper.addChild()

			ImmutableArray<Entity> immutableArray=sceneLoader.getEngine().getEntitiesFor(Family.all(CollisionComponent.class).get());


			for(Entity e:immutableArray){
				if(!ComponentRetriever.get(e, MainItemComponent.class).tags.contains(Enums.Group.PLAYER.value))
					System.out.println();
			//		immutableArray.

			}


			//groupEntitiesB = world.getSystem(GroupManager.class).getEntities(group2);
			this.handler = handler;
		}

		public void checkForCollisions() {
			for(int a = 0; groupEntitiesA.size() > a; a++) {
				for(int b = 0; groupEntitiesB.size() > b; b++) {
					Entity entityA = groupEntitiesA.get(a);
					Entity entityB = groupEntitiesB.get(b);
					if(collisionExists(entityA, entityB)) {
						handler.handleCollision(entityA, entityB);
					}
				}
			}
		}

		private boolean collisionExists(Entity e1, Entity e2) {

			if(e1 == null || e2 == null) {
				return false;
			}

			DimensionsComponent dimensionsComponent= ComponentRetriever.get(e1,DimensionsComponent.class);
			DimensionsComponent dimensionsComponent1= ComponentRetriever.get(e2,DimensionsComponent.class);

			return dimensionsComponent.boundBox.overlaps(dimensionsComponent1.boundBox);
		}
	}

	private interface CollisionHandler {
		void handleCollision(Entity a, Entity b);
	}

	@Override
	public boolean checkProcessing() {
		return !Constants.isPaused();
	}
}

