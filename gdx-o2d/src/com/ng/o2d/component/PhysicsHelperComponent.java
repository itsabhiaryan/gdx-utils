package com.ng.o2d.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/9/2015.
 */

public class PhysicsHelperComponent implements Component {
	
	public Vector2 bottleModelOrigin;
	public boolean isDeleted;
	public boolean isRemove;

	
	public PhysicsHelperComponent(float x, float y) {
		bottleModelOrigin=new Vector2(x, y);
	}

}
