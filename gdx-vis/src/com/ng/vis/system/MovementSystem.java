package com.ng.vis.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.kotcrab.vis.runtime.component.Transform;
import com.ng.gdxutils._GameManager;
import com.ng.vis.component.MovementComponent;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 09-12-2015.
 *
 * If entity having MovementComponent then that entity follow his path with particular speed.
 *
 */

public class MovementSystem extends EntityProcessingSystem {

	private static final String TAG = "[" + MovementSystem.class.getSimpleName() + "]";

	ComponentMapper<Transform> transformMapper;
	ComponentMapper<MovementComponent> movementMapper;
  
	public MovementSystem() {
		super(Aspect.all(MovementComponent.class,Transform.class));
	}

	@Override
	protected void process(Entity e) {

		Transform basicComponent= transformMapper.get(e);
		MovementComponent movementComponent=movementMapper.get(e);

		float angle=basicComponent.getRotation();
		float angleInRad= MathUtils.degRad*angle;

		basicComponent.setX(basicComponent.getX() - movementComponent.speed * (float) (Math.sin(angleInRad)));
		basicComponent.setY(basicComponent.getY() + movementComponent.speed * (float) (Math.cos(angleInRad)));
	}

	@Override
	protected boolean checkProcessing() {
		return !_GameManager.isPaused();
	}
}
