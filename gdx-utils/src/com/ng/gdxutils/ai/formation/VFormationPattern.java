package com.ng.gdxutils.ai.formation;

import com.badlogic.gdx.ai.fma.FormationPattern;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * (c) Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since  14-12-2015.
 */
public class VFormationPattern implements FormationPattern<Vector2> {

	private int numberOfSlots;
	private float memberRadius;
	private float angle;

	private Vector2 side1 = new Vector2();
	private Vector2 side2 = new Vector2();

	public VFormationPattern(float angle, float memberRadius) {
		this.memberRadius = memberRadius;
		setAngle(angle);
	}

	public float getMemberRadius() {
		return memberRadius;
	}

	public void setMemberRadius(float memberRadius) {
		this.memberRadius = memberRadius;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
		
		side1.x = -(float) Math.sin(angle / 2 + 90 * MathUtils.degreesToRadians);
		side1.y = (float) Math.cos(-angle / 2 + 90 * MathUtils.degreesToRadians);
	
		side2.x = -(float) Math.sin(angle / 2 + 90 * MathUtils.degreesToRadians);
		side2.y = (float) Math.cos(-angle / 2 + 90 * MathUtils.degreesToRadians);

	}

	@Override
	public boolean supportsSlots(int slotCount) {
		return true;
	}

	@Override
	public void setNumberOfSlots(int numberOfSlots) {
		this.numberOfSlots = numberOfSlots;
	}

	@Override
	public Location<Vector2> calculateSlotLocation(
            Location<Vector2> outLocation, int slotNumber) {
		Vector2 side = ((slotNumber + 1) % 2) == 0 ? side1 : side2;
		float radius = ((slotNumber + 1) / 2) * (memberRadius + memberRadius);
		outLocation.getPosition().set(side).scl(radius);
		outLocation.setOrientation(0);
		return outLocation;
	}

}
