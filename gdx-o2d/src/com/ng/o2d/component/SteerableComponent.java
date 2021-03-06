package com.ng.o2d.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.ng.gdxutils.ai.Scene2dLocation;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 1/26/2016.
 *
 */

public class SteerableComponent implements Component, Steerable<Vector2> {
	
	float boundingRadius;
	boolean tagged;
	
	float maxLinearSpeed = 10;
	float maxLinearAcceleration = 10;
	float maxAngularSpeed = 6;
	float maxAngularAcceleration = 15;

	private Vector2 position;
	private float angle;
	private Vector2 linearVelocity;
	public  float angularVelocity;
	
	public Body body;
	
	public boolean independentFacing;
	
	public SteeringBehavior<Vector2> behavior;
	public SteeringAcceleration<Vector2> steeringOutput;
	
	public SteerableComponent(float boundingRadius) {
		this(boundingRadius, false);
	}
	
	public SteerableComponent(float boundingRadius, Vector2 position, float orientation) {
		this(boundingRadius, false);
		this.position=position;
		this.angle=orientation;
	}

	public SteerableComponent(float boundingRadius, boolean independentFacing) {
		
		this.independentFacing=independentFacing;
		this.position = new Vector2();
		this.linearVelocity = new Vector2();
		this.boundingRadius=boundingRadius;
		this.steeringOutput=new SteeringAcceleration<Vector2>(new Vector2());
	}
	
	public SteerableComponent(Body body, boolean independentFacing, float boundingRadius) {
		
		this.body = body;
		this.independentFacing = independentFacing;
		this.boundingRadius = boundingRadius;
		this.tagged = false;
		this.steeringOutput=new SteeringAcceleration<Vector2>(new Vector2());
		//body.setUserData(this);
	}
	
	public Body getBody () {
		return body;
	}

	public void setBody (Body body) {
		this.body = body;
	}

	@Override
	public float getMaxLinearSpeed() {
		return maxLinearSpeed;
	}

	@Override
	public void setMaxLinearSpeed(float maxLinearSpeed) {
		this.maxLinearSpeed=maxLinearSpeed;
	}

	@Override
	public float getMaxLinearAcceleration() {
		return maxLinearAcceleration;
	}

	@Override
	public void setMaxLinearAcceleration(float maxLinearAcceleration) {
		this.maxLinearAcceleration=maxLinearAcceleration;
	}

	@Override
	public float getMaxAngularSpeed() {
		return maxAngularSpeed;
	}

	@Override
	public void setMaxAngularSpeed(float maxAngularSpeed) {
		this.maxAngularSpeed=maxAngularSpeed;		
	}

	@Override
	public float getMaxAngularAcceleration() {
		return maxAngularAcceleration;
	}

	@Override
	public void setMaxAngularAcceleration(float maxAngularAcceleration) {
		this.maxAngularAcceleration=maxAngularAcceleration;
	}

	@Override
	public Vector2 getPosition() {
		if(body!=null)
		return body.getPosition();
		else
		return position;
	}

	@Override
	public float getOrientation() {
		if(body!=null)
			return body.getAngle();
		else
		return angle* MathUtils.degreesToRadians;
	}

	@Override
	public void setOrientation(float arg0) {
		angle =arg0* MathUtils.radiansToDegrees;
	}
	
	@Override
	public Vector2 getLinearVelocity() {
		return linearVelocity;
	}

	@Override
	public float getAngularVelocity() {
		return angularVelocity;
	}

	@Override
	public float getBoundingRadius() {
		return boundingRadius;
	}

	@Override
	public boolean isTagged() {
		return tagged;
	}

	@Override
	public void setTagged(boolean tagged) {
		this.tagged=tagged;
	}

	@Override
	public Location<Vector2> newLocation() {
		return new Scene2dLocation();
	}
	
	@Override
	public float vectorToAngle(Vector2 vector) {
		return (float)(Math.atan2(-vector.x, vector.y));
	}

	@Override
	public Vector2 angleToVector(Vector2 outVector, float angle) {
		outVector.x=-(float)Math.sin(angle);
		outVector.y=(float)Math.cos(angle);
		return outVector;
	}

	public SteeringBehavior<Vector2> getSteeringBehavior () {
		return behavior;
	}

	public void setSteeringBehavior (SteeringBehavior<Vector2> steeringBehavior) {
		this.behavior = steeringBehavior;
	}

	@Override
	public float getZeroLinearSpeedThreshold() {
		return 0.001f;
	}

	@Override
	public void setZeroLinearSpeedThreshold(float arg0) {
		throw new UnsupportedOperationException();
	}
	
	public void setIndpendentFacing(boolean isIndependent){
		this.independentFacing=isIndependent;	
	}
	
	public boolean isIndependentFacing () {
		return independentFacing;
	}

	public float localX,localY;

	public void setLocalPosition(float x,float y) {
		this.localX = x;
		this.localY = y;
	}
}
