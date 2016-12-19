package com.ng.o2d.ai.fsm;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 30-07-2016
 *
 */

public enum  EntityState implements State<Entity> {

;
    @Override
    public void enter(Entity entity) {

    }

    @Override
    public void update(Entity entity) {

    }

    @Override
    public void exit(Entity entity) {

    }

    @Override
    public boolean onMessage(Entity entity, Telegram telegram) {
        return false;
    }




	/*NONE(){

	},

	START() {

		@Override
		public void enter(final Entity entity) {
			SpriteComponent sc=spriteMapper.get(entity);
			*//*if(playerManager.getPlayer(leader).equalsIgnoreCase(AGameConstant.Player.NPC.value)) {
				ParticleEffect particleEffect = new ParticleEffect();
				particleEffect.load(Gdx.files.internal("particle/explosion.p"), Gdx.files.internal("particle"));

				ParticleComponent pc = new ParticleComponent(particleEffect);

				leader.edit().add(pc);
				pc.effect.scaleEffect(.005f);

				pc.effect.setPosition(sc.getX()+sc.getWidth()/2, sc.getY()+sc.getHeight()/2);

			}*//*


			//final Entity playerEntity=EntityFactory.createParticleEntity(world,sc.getX()+sc.getWidth()/2,sc.getY()+sc.getHeight()/2,.005f);

			Timer.Task task1=new Timer.Task() {
				@Override
				public void run() {
					//playerEntity.deleteFromWorld();
				}
				};
			timer.scheduleTask(task1,1);

			//GameSceneManager.world.deleteEntity(leader);
			EntityManager.deleteEntity(entity);

			//sc.sprite.setAlpha(0);
			//System.out.println("Entity id of "+leader.getId());

			//pc.effect.setEmittersCleanUpBlendFunction(true);
           // pc.effect.start();

			//if(playerManager.getPlayer(leader).equalsIgnoreCase(AGameConstant.Player.PLAYER.value))
			//EntityManager.removeMovement(leader);
			//playerManager.deleted(leader.getId());
			//leader.getComponent(PhysicsHelperComponent.class).isBodyRemove=true;

			//if(leader.getComponent(BulletComponent.class)!=null)
          	//leader.getComponent(BulletComponent.class).speed=.01f;
			//leader.edit().remove(leader.getComponent(BulletComponent.class));

			//if(leader.getComponent(MovementComponent.class)!=null)
			//leader.edit().remove(leader.getComponent(MovementComponent.class));
			//if(leader.getComponent(SteerableComponent.class)!=null)
			//leader.edit().remove(leader.getComponent(SteerableComponent.class));
			//if(leader.getComponent(B2DSteerableComponent.class)!=null)
			//leader.edit().remove(leader.getComponent(B2DSteerableComponent.class));
			//if(leader.getComponent(PhysicsComponent.class)!=null)
			//leader.getComponent(PhysicsHelperComponent.class).isBodyRemove=true;
			//else{

			//leader.edit().remove(CollisionComponent.class);

		*//*		Timer t=new Timer();
				Timer.Task playerBulletTask=new Timer.Task() {

					@Override
					public void run() {

					//	if(leader.getComponent(MovementComponent.class)!=null)
					//	leader.edit().remove(leader.getComponent(MovementComponent.class));'

						//if(leader.getComponent(SteerableComponent.class)!=null)
							//leader.edit().remove(leader.getComponent(SteerableComponent.class));
						//if(leader.getComponent(B2DSteerableComponent.class)!=null)
							//leader.edit().remove(leader.getComponent(B2DSteerableComponent.class));

						//if(leader.getComponent(PhysicsComponent.class)!=null) {
							//leader.getComponent(PhysicsHelperComponent.class).isBodyRemove=true;
							//leader.getWorld().getSystem(PhysicsSystem.class).getPhysicsWorld().destroyBody(leader.getComponent(PhysicsComponent.class).body);
						//}
						//else

						GameSceneManager.world.deleteEntity(leader);

					}
				};
				t.scheduleTask(playerBulletTask, 1);

//			}*//*

			//if(EntityManager.isPlayerBullet(leader)){
	        	//GameSceneManager.world.deleteEntity(leader);
*//*
        	 Gdx.app.postRunnable(new Runnable() {
				@Override
				public void run() {

			     		 if(leader.getComponent(MovementComponent.class)!=null)
			         		leader.edit().remove(leader.getComponent(MovementComponent.class));
			         		if(leader.getComponent(SteerableComponent.class)!=null)
			         		leader.edit().remove(leader.getComponent(SteerableComponent.class));
				}
			});
*//*

        // }
       // 	 leader.getComponent(BulletComponent.class).speed=0;
       //  }
        	 *//*
         if(EntityManager.isEnemyBullet(leader)){
        //	 leader.getComponent(BulletComponent.class).speed=0;
        	//GameSceneManager.world.deleteEntity(leader);
       
        	 Gdx.app.postRunnable(new Runnable() {
				@Override
				public void run() {
					 if(leader.getComponent(MovementComponent.class)!=null)
			         	leader.edit().remove(leader.getComponent(MovementComponent.class));
			         if(leader.getComponent(SteerableComponent.class)!=null)
			         	leader.edit().remove(leader.getComponent(SteerableComponent.class));
					 if(leader.getComponent(PhysicsComponent.class)!=null)
						 leader.getComponent(PhysicsHelperComponent.class).isBodyRemove=true;
						//leader.getWorld().getSystem(PhysicsSystem.class).getPhysicsWorld().destroyBody(leader.getComponent(PhysicsComponent.class).body);
				}
			});
         }
			//GameSceneManager.world.deleteEntity(leader);

          	Timer t=new Timer();
          	Timer.Task playerBulletTask=new Timer.Task() {
				
				@Override
				public void run() {
					if(leader.getComponent(PhysicsComponent.class)!=null) {
						leader.getComponent(PhysicsHelperComponent.class).isBodyRemove=true;
						//leader.getWorld().getSystem(PhysicsSystem.class).getPhysicsWorld().destroyBody(leader.getComponent(PhysicsComponent.class).body);
					}
					//GameSceneManager.world.deleteEntity(leader);
				}
			};
          	t.scheduleTask(playerBulletTask, 1);*//*
		}

		@Override
		public void update(Entity entity) {
			*//*System.out.println("sdsfs");
			if(pc.effect.isComplete()){
				GameSceneManager.world.deleteEntity(leader);
				System.out.println("DELETED ENTY");
			}*//*
		}

		@Override
		public void exit(Entity entity) {
			
		}

		@Override
		public boolean onMessage(Entity entity, Telegram telegram) {
			return false;
		}
	},

	MOVE_DOWN(){

		@Override
		public void enter(Entity entity) {
			super.enter(entity);
		}

		@Override
		public void update(Entity entity) {

			BasicComponent basicComponent=basicMapper.get(entity);

			if(basicComponent.getY()<7) {
				float y=basicComponent.getY();
				y-=.05f;
				basicComponent.setY(y);
			}
			else {

				 }


		}

		@Override
		public void exit(Entity entity) {
			//Gdx.app.log(TAG,"exit Method");
			if(leftRightMapper.has(entity))
				leftRightMapper.get(entity).margin=1;
		}

		@Override
		public boolean onMessage(Entity entity, Telegram telegram) {
			//Gdx.app.log(TAG,"message Recived");

			return false;
		}

	},

	MOVE_LEFT() {

		Random r=new Random();

		@Override
		public void enter(Entity entity) {
			super.enter(entity);
			//Gdx.app.log(TAG,"Enter in state1");
		}

		@Override
		public void update(Entity entity) {

			if(spriteMapper.has(entity) && leftRightMapper.has(entity)){
			SpriteComponent s = spriteMapper.get(entity);
			LeftRightComponent c = leftRightMapper.get(entity);

			int randomValue = r.nextInt(1000);
			//if (randomValue < 50)
			//	EntityFactory.createPhysicsEntity(GameSceneManager.world, GameResource.ENEMY, Size.makeSize(.25f, .25f), Position.makePosition(s.getX() + s.getWidth() / 2, s.getY() + s.getHeight() / 2), true, 180, .5f);

			c.margin -= c.speed;
			float x = s.getX();
			x -= c.speed;
			s.setX(x);

			if (c.margin <= 0)

				if (fsmMapper.get(entity).getStateMachine().getPreviousState().equals(EntityState.MOVE_RIGHT))
					fsmMapper.get(entity).getStateMachine().changeState(EntityState.MOVE_LEFT);
				else
					fsmMapper.get(entity).getStateMachine().changeState(EntityState.MOVE_RIGHT);

			}
		}

		@Override
		public void exit(Entity entity) {
			//Gdx.app.log(TAG,"exit in this state");
			if(leftRightMapper.has(entity))
			leftRightMapper.get(entity).margin=1;
		}

		@Override
		public boolean onMessage(Entity entity, Telegram telegram) {
			//Gdx.app.log(TAG,"onMessage");
			return false;
		}
	},

	MOVE_RIGHT() {

		@Override
		public void enter(Entity entity) {
			super.enter(entity);
			//Gdx.app.log(TAG,"enter");
		}

		@Override
		public void update(Entity entity) {

			if(spriteMapper.has(entity) && leftRightMapper.has(entity)) {
				SpriteComponent s = spriteMapper.get(entity);
				LeftRightComponent c = leftRightMapper.get(entity);

				c.margin -= c.speed;
				float x = s.getX();
				x += c.speed;
				s.setX(x);

				if (c.margin <= 0) {

					if (fsmMapper.get(entity).getStateMachine().getPreviousState().equals(EntityState.MOVE_LEFT))
						fsmMapper.get(entity).getStateMachine().changeState(EntityState.MOVE_RIGHT);
					else
						fsmMapper.get(entity).getStateMachine().changeState(EntityState.MOVE_LEFT);
				}
			}
		}

		@Override
		public void exit(Entity entity) {
			//Gdx.app.log(TAG,"exit");
			if(leftRightMapper.has(entity))
			leftRightMapper.get(entity).margin=1;
		}

		@Override
		public boolean onMessage(Entity entity, Telegram telegram) {

			//Gdx.app.log(TAG,"onMessage");
			return false;
		}
	};

	int x;
	Timer timer=new Timer();


	@Override
	public void enter(Entity entity) {
	
	}

	@Override
	public void update(Entity entity) {
		
	}

	@Override
	public void exit(Entity entity) {
		
	}

	@Override
	public boolean onMessage(Entity entity, Telegram telegram) {
		return false;
	}*/

}
