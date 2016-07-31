package com.nayragames.vis.component;

import com.artemis.Component;
import com.artemis.Entity;
import com.badlogic.gdx.ai.fma.FormationMember;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.nayragames.gdxutils.ai.Scene2dLocation;

/**
 * (c) Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/9/2015.
 *
 */

public class FormationMemberComponent extends Component implements FormationMember<Vector2>{

	private Scene2dLocation targetLocation;
	public Entity leader;
	
	public FormationMemberComponent(Entity entity) {
		this.leader =entity;
		targetLocation = new Scene2dLocation();
	}

	public Location<Vector2> getTargetLocation() {
		return null;
	}

	/*@Override
	public Location<Vector2> getTargetLocation() {
		return targetLocation;
	}*/

}
