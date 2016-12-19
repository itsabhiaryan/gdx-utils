package com.ng.vis.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.ng.gdxutils._GameManager;
import com.ng.vis.component.LeaderComponent;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/9/2015.
 *
 * This system is used to update enemyPower of member with respect to its lead.
 * Formation is part of Artificial Intelligence (gdx-ai)
 *
 */

@Wire
public class FormationSystem extends EntityProcessingSystem {

	private static final String TAG = "[" + FormationSystem.class.getSimpleName() + "]";
	ComponentMapper<LeaderComponent> teamMapper;
	
	public FormationSystem() {
		super(Aspect.all(LeaderComponent.class));
	}

	@Override
	protected void process(Entity e) {

		LeaderComponent leaderComponent=teamMapper.get(e);
		if(leaderComponent.isUpdateSlot)
			leaderComponent.formation.updateSlots();
	}

	@Override
	protected boolean checkProcessing() {
		return !_GameManager.isPaused();
	}
}
