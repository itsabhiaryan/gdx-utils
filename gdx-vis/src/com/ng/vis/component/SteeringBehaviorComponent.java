package com.ng.vis.component;

import com.artemis.Component;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/28/2015.
 *
 */

public class SteeringBehaviorComponent extends Component implements Poolable {

	private SteeringBehavior<Vector2> behavior;

	/** Can only be created by PooledEngine */
	private SteeringBehaviorComponent () {
		// private constructor
	}

	public SteeringBehaviorComponent init (SteeringBehavior<Vector2> behavior) {
		setBehavior(behavior);
		return this;
	}

	public void setBehavior (SteeringBehavior<Vector2> behavior) {
		this.behavior = behavior;
	}

	public SteeringBehavior<Vector2> getBehavior () {
		return behavior;
	}

	public void reset () {
		behavior = null;
	}

}
