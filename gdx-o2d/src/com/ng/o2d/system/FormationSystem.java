package com.ng.o2d.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.ng.gdxutils._GameManager;
import com.ng.o2d.component.LeaderComponent;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/9/2015.
 *
 * This system is used to update enemyPower of member with respect to its lead.
 * Formation is part of Artificial Intelligence (gdx-ai)
 */

public class FormationSystem extends IteratingSystem {

	private static final String TAG = "[" + FormationSystem.class.getSimpleName() + "]";
	ComponentMapper<LeaderComponent> teamMapper;
	
	public FormationSystem() {
		super(Family.all(LeaderComponent.class).get());
		teamMapper= ComponentMapper.getFor(LeaderComponent.class);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		LeaderComponent leaderComponent=teamMapper.get(entity);
		if(leaderComponent.isUpdateSlot)
			leaderComponent.formation.updateSlots();
	}

	@Override
	public boolean checkProcessing() {
		return !_GameManager.isPaused();
	}
}
