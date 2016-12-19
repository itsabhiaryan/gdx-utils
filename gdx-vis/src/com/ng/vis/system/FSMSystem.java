package com.ng.vis.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.ng.gdxutils._GameManager;
import com.ng.vis.component.FSMComponent;

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

public class FSMSystem extends EntityProcessingSystem {

	private static final String TAG = "[" + FSMSystem.class.getSimpleName() + "]";
	ComponentMapper<FSMComponent> fsmMapper;

	public FSMSystem() {
		super(Aspect.all(FSMComponent.class));
	}

	@Override
	protected void process(Entity e) {
		fsmMapper.get(e).update();
	}

	@Override
	protected boolean checkProcessing() {
		return !_GameManager.isPaused();
	}
}
