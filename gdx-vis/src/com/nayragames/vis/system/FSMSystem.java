package com.nayragames.vis.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.nayragames.vis.Constants;
import com.nayragames.vis.component.FSMComponent;

/**
 * This system is used to update FiniteStateMachine if entity having FSMComponent.
 * Finite State Machine is part of Artificial Intelligence (gdx-ai)
 *
 * Created by ARYAN on 12/9/2015.
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
		return !Constants.isPaused();
	}
}
