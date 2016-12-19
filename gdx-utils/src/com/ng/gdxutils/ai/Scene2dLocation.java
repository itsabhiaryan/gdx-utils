package com.ng.gdxutils.ai;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;

/**
 * (c) Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since  14-12-2015.
 *
 */
public class Scene2dLocation implements Location<Vector2> {

	Vector2 position;
	float orientation;

	public Scene2dLocation() {
		this.position = new Vector2();
		this.orientation = 0;
	}

	@Override
	public Vector2 getPosition () {
		return position;
	}

	@Override
	public float getOrientation () {
		return orientation;
	}

	@Override
	public void setOrientation (float orientation) {
		this.orientation = orientation;
	}

	@Override
	public Location<Vector2> newLocation () {
		return new Scene2dLocation();
	}

	@Override
	public float vectorToAngle (Vector2 vector) {
		return (float)Math.atan2(-vector.x, vector.y);
	}

	@Override
	public Vector2 angleToVector (Vector2 outVector, float angle) {
		outVector.x = -(float)Math.sin(angle);
		outVector.y = (float)Math.cos(angle);
		return outVector;
	}

	public void setPosition(Vector2 position){
		this.position=position;
	}
}
