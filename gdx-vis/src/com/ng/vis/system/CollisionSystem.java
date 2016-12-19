package com.ng.vis.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.managers.GroupManager;
import com.artemis.managers.PlayerManager;
import com.artemis.utils.Bag;
import com.artemis.utils.ImmutableBag;
import com.kotcrab.vis.runtime.component.Transform;
import com.kotcrab.vis.runtime.component.VisSprite;
import com.kotcrab.vis.runtime.component.VisText;
import com.ng.gdxutils._GameManager;
import com.ng.vis.component.*;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 30-11-2015.
 *
 * System that handle collision of entity which having CollisionComponent.
 *
 */

public class CollisionSystem extends EntitySystem {

	private static final String TAG = "[" + CollisionSystem.class.getSimpleName() + "]";

	ComponentMapper<FormationMemberComponent> memberMapper;
	ComponentMapper<LeaderComponent> leaderMapper;
	ComponentMapper<HealthComponent> healthMapper;
	ComponentMapper<VisText> textMapper;
	ComponentMapper<CollisionComponent> collisionMapper;
	ComponentMapper<Transform> transformCm;
	ComponentMapper<VisSprite> spriteMapper;
	ComponentMapper<SteerableComponent> steerMapper;

	ComponentMapper<Bounds> boundsCm;

	GroupManager groupManager;
	PlayerManager playerManager;

	private static final int LIFE_INCREMENT=50;
	private boolean isDragonHit;
	public Bag<CollisionPair> collisionPairs;

	public CollisionSystem() {
		super(Aspect.all(CollisionComponent.class, Transform.class));
	}

	@Override
	protected void initialize() {
		super.initialize();

		collisionPairs=new Bag<CollisionPair>();

		/*collisionPairs.add(new CollisionPair(Group.PLAYER.value, Group.COLLECTABLE.value, new CollisionHandler() {
			@Override
			public void handleCollision(Entity player,final Entity collection) {

				Transform basicComponent1 = transformCm.get(player);
				Transform basicComponent = transformCm.get(collection);
				CollisionComponent collisionComponent = collisionMapper.get(collection);

					if(steerMapper.has(collection)){
						SteerableComponent steer=collection.getComponent(SteerableComponent.class);
						steer.setSteeringBehavior(null);
					}

					if(!collisionMapper.get(collection).isCollected) {

						collisionMapper.get(collection).isCollected=true;

					TweenBuilder.applyScale(basicComponent1);
					TweenBuilder.applyCollectionTween(basicComponent,new TweenCallback() {
						@Override
						public void onEvent(int type, BaseTween<?> source) {
							collection.deleteFromWorld();
						}
					});
				}

			}
		}));*/
	}

	@Override
	protected void processSystem() {

		for(int i = 0; collisionPairs.size() > i; i++) {
			collisionPairs.get(i).checkForCollisions();
		}
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
		VisText text=textMapper.get(entity);
		text.setText(String.valueOf((int) (percent)));

		return health;
	}

	/**
	 * Set value to TextComponent after increasing the value of TextComponent by value.
	 * @param text TextComponent on which operation performed.
	 * @param value value which is increased.
	 *
	 */

	private void setText(VisText text, int value){
		int total = Integer.parseInt(text.getText());
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

		private ImmutableBag<Entity> groupEntitiesA;
		private ImmutableBag<Entity> groupEntitiesB;
		private CollisionHandler handler;

		public CollisionPair(String group1, String group2, CollisionHandler handler) {
			groupEntitiesA = world.getSystem(GroupManager.class).getEntities(group1);
			groupEntitiesB = world.getSystem(GroupManager.class).getEntities(group2);
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

			Bounds p1 = boundsCm.get(e1);
			Bounds p2 = boundsCm.get(e2);

			return p1.bounds.overlaps(p2.bounds);
		}
	}

	private interface CollisionHandler {
		void handleCollision(Entity a, Entity b);
	}

	@Override
	protected boolean checkProcessing() {
		return !_GameManager.isPaused();
	}
}
