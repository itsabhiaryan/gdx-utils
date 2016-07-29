
package com.nayragames.o2d.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.fsm.StateMachine;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.nayragames.o2d.ai.fsm.EntityState;


public class FSMComponent implements Component, Telegraph {

	private DefaultStateMachine<Entity,EntityState> stateMachine;

	public FSMComponent(Entity entity) {
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
