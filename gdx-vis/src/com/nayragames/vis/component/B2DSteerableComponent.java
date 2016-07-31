package com.nayragames.vis.component;

import com.artemis.Component;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.nayragames.gdxutils.ai.Scene2dLocation;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 26-11-2015.
 *
 */

public class B2DSteerableComponent extends Component implements Steerable<Vector2> {

	public Body body;

	float boundingRadius;
	boolean tagged;

	float maxLinearSpeed;
	float maxLinearAcceleration;
	float maxAngularSpeed;
	float maxAngularAcceleration;

	boolean independentFacing;

	public SteeringBehavior<Vector2> steeringBehavior;
	public SteeringAcceleration<Vector2> steeringOutput = new SteeringAcceleration<Vector2>(new Vector2());

	public B2DSteerableComponent(Body body, boolean independentFacing, float boundingRadius) {
	
		this.body = body;
		this.independentFacing = independentFacing;
		this.boundingRadius = boundingRadius;
		this.tagged = false;

		body.setUserData(this);
	}
	
	
	public Body getBody () {
		return body;
	}

	public void setBody (Body body) {
		this.body = body;
	}

	public boolean isIndependentFacing () {
		return independentFacing;
	}

	public void setIndependentFacing (boolean independentFacing) {
		this.independentFacing = independentFacing;
	}

	/*@Override
	public Vector2 getPosition () {
		return body.getPosition();
	}

	@Override
	public float getOrientation () {
		return body.getAngle();
	}

	@Override
	public void setOrientation (float orientation) {
		body.setTransform(getPosition(), orientation);
	}

	@Override
	public Vector2 getLinearVelocity () {
		return body.getLinearVelocity();
	}

	@Override
	public float getAngularVelocity () {
		return body.getAngularVelocity();
	}

	@Override
	public float getBoundingRadius () {
		return boundingRadius;
	}

	@Override
	public boolean isTagged () {
		return tagged;
	}

	@Override
	public void setTagged (boolean tagged) {
		this.tagged = tagged;
	}

	@Override
	public Location<Vector2> newLocation () {
		return new Scene2dLocation();
	}

	@Override
	public float vectorToAngle (Vector2 vector) {
		return (float)(Math.atan2(-vector.x, vector.y));
	}

	@Override
	public Vector2 angleToVector (Vector2 outVector, float angle) {
		
		outVector.x=-(float)Math.sin(angle);
		outVector.y=(float)Math.cos(angle);
		return outVector;
	}*/

	public SteeringBehavior<Vector2> getSteeringBehavior () {
		return steeringBehavior;
	}

	public void setSteeringBehavior (SteeringBehavior<Vector2> steeringBehavior) {
		this.steeringBehavior = steeringBehavior;
	}

	public void update (float deltaTime) {
		if (steeringBehavior != null) {
			// Calculate steering acceleration
			steeringBehavior.calculateSteering(steeringOutput);

			/*
			 * Here you might want to add a motor control layer filtering steering accelerations.
			 * 
			 * For instance, a car in a driving game has physical constraints on its movement: it cannot turn while stationary; the
			 * faster it moves, the slower it can turn (without going into a skid); it can brake much more quickly than it can
			 * accelerate; and it only moves in the direction it is facing (ignoring power slides).
			 */

			// Apply steering acceleration
			//applySteering(steeringOutput, deltaTime);
		}

		
	}

	

	// the display area is considered to wrap around from top to bottom
	// and from left to right
	protected void wrapAround (float maxX, float maxY) {
		float k = Float.POSITIVE_INFINITY;
		Vector2 pos = body.getPosition();

		if (pos.x > maxX) k = pos.x = 0.0f;

		if (pos.x < 0) k = pos.x = maxX;

		if (pos.y < 0) k = pos.y = maxY;

		if (pos.y > maxY) k = pos.y = 0.0f;

		if (k != Float.POSITIVE_INFINITY) body.setTransform(pos, body.getAngle());
	}

	public Vector2 getLinearVelocity() {
		return null;
	}

	public float getAngularVelocity() {
		return 0;
	}

	public float getBoundingRadius() {
		return 0;
	}

	public boolean isTagged() {
		return false;
	}

	public void setTagged(boolean tagged) {

	}

	public float getZeroLinearSpeedThreshold() {
		return 0;
	}

	public void setZeroLinearSpeedThreshold(float value) {

	}

	public float getMaxLinearSpeed() {
		return 0;
	}

	public void setMaxLinearSpeed(float maxLinearSpeed) {

	}

	public float getMaxLinearAcceleration() {
		return 0;
	}

	public void setMaxLinearAcceleration(float maxLinearAcceleration) {

	}

	public float getMaxAngularSpeed() {
		return 0;
	}

	public void setMaxAngularSpeed(float maxAngularSpeed) {

	}

	public float getMaxAngularAcceleration() {
		return 0;
	}

	public void setMaxAngularAcceleration(float maxAngularAcceleration) {

	}

	public Vector2 getPosition() {
		return null;
	}

	public float getOrientation() {
		return 0;
	}

	public void setOrientation(float orientation) {

	}

	public float vectorToAngle(Vector2 vector) {
		return 0;
	}

	public Vector2 angleToVector(Vector2 outVector, float angle) {
		return null;
	}

	public Location<Vector2> newLocation() {
		return null;
	}

	/*public void draw (Batch batch) {
		Vector2 pos = body.getPosition();
		float w = region.getRegionWidth();
		float h = region.getRegionHeight();
		float ox = w / 2f;
		float oy = h / 2f;

		batch.draw(region, //
			Box2dSteeringTest.metersToPixels(pos.x) - ox, Box2dSteeringTest.metersToPixels(pos.y) - oy, //
			ox, oy, //
			w, h, //
			1, 1, //
			body.getRotation() * MathUtils.radiansToDegrees); //
	}*/

	//
	// Limiter implementation
	//

	/*@Override
	public float getMaxLinearSpeed () {
		return maxLinearSpeed;
	}

	@Override
	public void setMaxLinearSpeed (float maxLinearSpeed) {
		this.maxLinearSpeed = maxLinearSpeed;
	}

	@Override
	public float getMaxLinearAcceleration () {
		return maxLinearAcceleration;
	}

	@Override
	public void setMaxLinearAcceleration (float maxLinearAcceleration) {
		this.maxLinearAcceleration = maxLinearAcceleration;
	}

	@Override
	public float getMaxAngularSpeed () {
		return maxAngularSpeed;
	}

	@Override
	public void setMaxAngularSpeed (float maxAngularSpeed) {
		this.maxAngularSpeed = maxAngularSpeed;
	}

	@Override
	public float getMaxAngularAcceleration () {
		return maxAngularAcceleration;
	}

	@Override
	public void setMaxAngularAcceleration (float maxAngularAcceleration) {
		this.maxAngularAcceleration = maxAngularAcceleration;
	}

	@Override
	public float getZeroLinearSpeedThreshold () {
		return 0.001f;
	}

	@Override
	public void setZeroLinearSpeedThreshold (float value) {
		throw new UnsupportedOperationException();
	}
	*/
}
