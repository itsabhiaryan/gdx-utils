
package com.nayragames.vis.component;

import com.artemis.Component;
import com.artemis.Entity;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.nayragames.vis.ai.fsm.EntityState;


public class FSMComponent extends Component implements Telegraph {

	private DefaultStateMachine<Entity,EntityState> stateMachine;

	public FSMComponent (Entity entity) {
		stateMachine = new DefaultStateMachine<Entity,EntityState>(entity);
	}

	public void update () {
		stateMachine.update();
	}

	public void changeState (State<Entity> state) {
		//stateMachine.changeState(state);
	}

	public StateMachine<Entity,EntityState> getStateMachine () {
		return stateMachine;
	}

	@Override
	public boolean handleMessage (Telegram msg) {
		return stateMachine.handleMessage(msg);
	}

}
