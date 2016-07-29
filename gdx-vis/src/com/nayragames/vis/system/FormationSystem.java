package com.nayragames.vis.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.nayragames.vis.Constants;
import com.nayragames.vis.component.LeaderComponent;

/**
 * This system is used to update enemyPower of member with respect to its lead.
 * Formation is part of Artificial Intelligence (gdx-ai)
 * Created by ARYAN on 12/9/2015.
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
		return !Constants.isPaused();
	}
}
