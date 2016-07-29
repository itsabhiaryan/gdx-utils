package com.nayragames.o2d.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class PhysicsHelperComponent implements Component {
	
	public Vector2 bottleModelOrigin;
	public boolean isDeleted;
	public boolean isRemove;

	
	public PhysicsHelperComponent(float x, float y) {
		bottleModelOrigin=new Vector2(x, y);
	}

}
