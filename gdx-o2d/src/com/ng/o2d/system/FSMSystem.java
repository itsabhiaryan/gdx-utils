package com.ng.o2d.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.ng.gdxutils._GameManager;
import com.ng.o2d.component.FSMComponent;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/9/2015.
 *
 * This system is used to update FiniteStateMachine if entity having FSMComponent.
 * Finite State Machine is part of Artificial Intelligence (gdx-ai)
 *
 */

public class FSMSystem extends IteratingSystem {

	private static final String TAG = "[" + FSMSystem.class.getSimpleName() + "]";
	ComponentMapper<FSMComponent> fsmMapper;

	public FSMSystem() {
		super(Family.all(FSMComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		fsmMapper.get(entity).update();
	}

	@Override
	public boolean checkProcessing() {
		return !_GameManager.isPaused();
	}


}
