package com.nayragames.o2d.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.nayragames.o2d.Constants;
import com.nayragames.o2d.component.MovementComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;

/**
 * If entity having MovementComponent then that entity follow his path with particular speed.
 *
 * Created by ARYAN on 09-12-2015.
 */

public class MovementSystem extends IteratingSystem {

	private static final String TAG = "[" + MovementSystem.class.getSimpleName() + "]";

	ComponentMapper<TransformComponent> transformMapper;
	ComponentMapper<MovementComponent> movementMapper;
  
	public MovementSystem() {
		super(Family.all(MovementComponent.class,TransformComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {

		TransformComponent transformComponent= transformMapper.get(entity);
		MovementComponent movementComponent=movementMapper.get(entity);

		float angle=transformComponent.rotation;
		float angleInRad= MathUtils.degRad*angle;

		transformComponent.x=(transformComponent.x - movementComponent.speed * (float) (Math.sin(angleInRad)));
		transformComponent.y=(transformComponent.y + movementComponent.speed * (float) (Math.cos(angleInRad)));

	}

	@Override
	public boolean checkProcessing() {
		return !Constants.isPaused();
	}


}
