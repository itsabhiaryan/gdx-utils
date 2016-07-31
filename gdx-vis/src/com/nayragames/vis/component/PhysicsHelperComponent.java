package com.nayragames.vis.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/9/2015.
 *
 */

public class PhysicsHelperComponent extends Component {
	
	public Vector2 bottleModelOrigin;
	public boolean isDeleted;
	public boolean isRemove;

	
	public PhysicsHelperComponent(float x, float y) {
		bottleModelOrigin=new Vector2(x, y);
	}

}
