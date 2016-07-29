package com.nayragames.vis.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

public class PhysicsHelperComponent extends Component {
	
	public Vector2 bottleModelOrigin;
	public boolean isDeleted;
	public boolean isRemove;

	
	public PhysicsHelperComponent(float x, float y) {
		bottleModelOrigin=new Vector2(x, y);
	}

}
