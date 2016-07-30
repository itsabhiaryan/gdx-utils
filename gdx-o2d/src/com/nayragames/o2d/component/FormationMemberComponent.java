package com.nayragames.o2d.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.fma.FormationMember;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;

import com.nayragames.gdxutils.ai.Scene2dLocation;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/9/2015.
 *
 */

public class FormationMemberComponent implements Component, FormationMember<Vector2> {

	private Scene2dLocation targetLocation;
	public Entity leader;
	
	public FormationMemberComponent(Entity entity) {
		this.leader =entity;
		targetLocation = new Scene2dLocation();
	}

	@Override
	public Location<Vector2> getTargetLocation() {
		return targetLocation;
	}

}
