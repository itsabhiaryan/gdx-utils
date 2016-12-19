package com.ng.vis.ai.fsm;

//@Wire
//public enum GameState implements State<Entity> {
	
/*	MOVE_DOWN(){

		@Override
		public void enter(Entity leader) {
			super.enter(leader);
		}
		
		@Override
		public void update(Entity leader) {

			LeftRightComponent c=leftRightMapper.get(leader);
			SpriteComponent s=spriteMapper.get(leader);
				c.margin-=c.speed;
				float y=s.getY();
				y-=c.speed;
				s.setY(y);
				
				if(c.margin<=0)
					fsmMapper.get(leader).getStateMachine().changeState(MOVE_LEFT);
		}

		@Override
		public void exit(Entity leader) {
			//Gdx.app.log(TAG,"exit Method");
			leftRightMapper.get(leader).margin=1;
		}

		@Override
		public boolean onMessage(Entity leader, Telegram telegram) {
			//Gdx.app.log(TAG,"message Recived");

			return false;
		}
		
	},
	
	MOVE_LEFT() {

		Random r=new Random();

		@Override
		public void enter(Entity leader) {
			super.enter(leader);
			//Gdx.app.log(TAG,"Enter in state1");
		}

		@Override
		public void update(Entity leader) {

			SpriteComponent s=spriteMapper.get(leader);
			LeftRightComponent c=leftRightMapper.get(leader);

			int randomValue=r.nextInt(1000);
			//if(randomValue<50)
			//	EntityFactory.createPhysicsEntity(world, GameResource.ENEMY, Size.makeSize(.25f,.25f), Position.makePosition(s.getX()+s.getWidth()/2,s.getY()+s.getHeight()/2),true,0,.5f);

			c.margin-=c.speed;
			float x=s.getX();
			x-=c.speed;
			s.setX(x);
			
			if(c.margin<=0)
				
				if(fsmMapper.get(leader).getStateMachine().getPreviousState().equals(GameState.MOVE_RIGHT))
					fsmMapper.get(leader).getStateMachine().changeState(GameState.MOVE_LEFT);
				else
					fsmMapper.get(leader).getStateMachine().changeState(GameState.MOVE_RIGHT);
		}

		@Override
		public void exit(Entity leader) {
			//Gdx.app.log(TAG,"exit in this state");
			leftRightMapper.get(leader).margin=1;
		}

		@Override
		public boolean onMessage(Entity leader, Telegram telegram) {
			//Gdx.app.log(TAG,"onMessage");
			return false;
		}
	
	},

	
	MOVE_RIGHT() {

		@Override
		public void enter(Entity leader) {
			super.enter(leader);
			//Gdx.app.log(TAG,"enter");
		}

		@Override
		public void update(Entity leader) {

			SpriteComponent s=spriteMapper.get(leader);
			LeftRightComponent c=leftRightMapper.get(leader);

			c.margin-=c.speed;
			float x=s.getX();
			x+=c.speed;
			s.setX(x);

			if(c.margin<=0){
		
			if(fsmMapper.get(leader).getStateMachine().getPreviousState().equals(GameState.MOVE_LEFT))
				fsmMapper.get(leader).getStateMachine().changeState(GameState.MOVE_RIGHT);
			else
				fsmMapper.get(leader).getStateMachine().changeState(GameState.MOVE_LEFT);
			}
		}

		@Override
		public void exit(Entity leader) {
			//Gdx.app.log(TAG,"exit");
			leftRightMapper.get(leader).margin=1;
		}

		@Override
		public boolean onMessage(Entity leader, Telegram telegram) {

			//Gdx.app.log(TAG,"onMessage");
			return false;
		}
	},

	RUNNING() {
		
		@Override
		public void enter(Entity leader) {
			//Gdx.app.log(TAG,"enter");
		}

		@Override
		public void update(Entity leader) {

			//Gdx.app.log(TAG,"update");
		}

		@Override
		public void exit(Entity leader) {

			//Gdx.app.log(TAG,"exit");
		}

		@Override
		public boolean onMessage(Entity leader, Telegram telegram) {

			//Gdx.app.log(TAG,"onMessage");
			return false;
		}
	
	};

	private static final String TAG = "[" + GameState.class.getSimpleName() + "]";

	@Override
	public void enter(Entity leader) {
		//Gdx.app.log(TAG, fsComponentMapper.get(leader).getStateMachine().toString());
	}*/
//}
