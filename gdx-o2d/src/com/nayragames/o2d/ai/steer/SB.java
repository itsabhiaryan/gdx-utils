package com.nayragames.o2d.ai.steer;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.ai.steer.behaviors.*;
import com.badlogic.gdx.ai.steer.limiters.*;
import com.badlogic.gdx.ai.steer.proximities.RadiusProximity;
import com.badlogic.gdx.ai.steer.utils.Path;
import com.badlogic.gdx.ai.steer.utils.paths.LinePath.LinePathParam;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nayragames.o2d.component.SteerableComponent;


public class SB {

	public static SteeringBehavior<Vector2> createSB1(Steerable<Vector2> steer, Location<Vector2> target){

		Arrive<Vector2> arriveSB = new Arrive<Vector2>(steer, target) //
				.setLimiter(new LinearLimiter(3500, 1000)) //
				.setTimeToTarget(0.1f) //
				.setArrivalTolerance(0.001f) //
				.setDecelerationRadius(400);

		ReachOrientation<Vector2> reachOrientationSB = new ReachOrientation<Vector2>(steer, target) //
				.setLimiter(new AngularLimiter(100, 20)) //
				.setTimeToTarget(0.1f) //
				.setAlignTolerance(0.001f) //
				.setDecelerationRadius(MathUtils.PI);

	/*	LookWhereYouAreGoing<Vector2> lookWhereYouAreGoingSB = new LookWhereYouAreGoing<Vector2>(steer) //
				.setLimiter(new AngularLimiter(100, 20)) //
				.setTimeToTarget(0.1f) //
				.setAlignTolerance(0.001f) //
				.setDecelerationRadius(MathUtils.PI);
*/
		BlendedSteering<Vector2> reachPositionAndOrientationSB = new BlendedSteering<Vector2>(steer)
				.setLimiter(NullLimiter.NEUTRAL_LIMITER) //
				.add(arriveSB, 1f)//
				.add(reachOrientationSB, 1f);//;
				///.add(lookWhereYouAreGoingSB, 1f);

			return reachPositionAndOrientationSB;
	}

	public static SteeringBehavior<Vector2> createDragonSteerable(Steerable<Vector2> steer, Location<Vector2> target){

		Arrive<Vector2> arriveSB = new Arrive<Vector2>(steer, target) //
				.setLimiter(new LinearLimiter(3500, 1000)) //
				.setTimeToTarget(0.1f) //
				.setArrivalTolerance(0.001f) //
				.setDecelerationRadius(40);

		/*ReachOrientation<Vector2> reachOrientationSB = new ReachOrientation<Vector2>(steer, target) //
				.setLimiter(new AngularLimiter(100, 20)) //
				.setTimeToTarget(0.1f) //
				.setAlignTolerance(0.001f) //
				.setDecelerationRadius(MathUtils.PI);*/

	/*	LookWhereYouAreGoing<Vector2> lookWhereYouAreGoingSB = new LookWhereYouAreGoing<Vector2>(steer) //
				.setLimiter(new AngularLimiter(100, 20)) //
				.setTimeToTarget(0.1f) //
				.setAlignTolerance(0.001f) //
				.setDecelerationRadius(MathUtils.PI);
*/
		BlendedSteering<Vector2> reachPositionAndOrientationSB = new BlendedSteering<Vector2>(steer)
				.setLimiter(NullLimiter.NEUTRAL_LIMITER) //
				.add(arriveSB, 1f);//
				//.add(reachOrientationSB, 1f);//;
		///.add(lookWhereYouAreGoingSB, 1f);

		return reachPositionAndOrientationSB;
	}

	public static SteeringBehavior<Vector2> createBulletBehaviour(Steerable<Vector2> steer, Location<Vector2> target){

		Arrive<Vector2> arriveSB = new Arrive<Vector2>(steer, target) //
				.setLimiter(new LinearLimiter(3500, 1000)) //
				.setTimeToTarget(0.1f) //
				.setArrivalTolerance(0.001f) //
				.setDecelerationRadius(100);

		ReachOrientation<Vector2> reachOrientationSB = new ReachOrientation<Vector2>(steer, target) //
				.setLimiter(new AngularLimiter(100, 20)) //
				.setTimeToTarget(0.1f) //
				.setAlignTolerance(0.001f) //
				.setDecelerationRadius(MathUtils.PI);

		LookWhereYouAreGoing<Vector2> lookWhereYouAreGoingSB = new LookWhereYouAreGoing<Vector2>(steer) //
				.setLimiter(new AngularLimiter(100, 20)) //
				.setTimeToTarget(0.1f) //
				.setAlignTolerance(0.001f) //
				.setDecelerationRadius(MathUtils.PI);

		BlendedSteering<Vector2> reachPositionAndOrientationSB = new BlendedSteering<Vector2>(steer)
					.setLimiter(NullLimiter.NEUTRAL_LIMITER) //
					.add(arriveSB, 1f)//
					.add(reachOrientationSB, 1f);//
				//	.add(lookWhereYouAreGoingSB, 1f);


		return reachPositionAndOrientationSB;

	}

	public static void addCollisionBehaviour(Entity entity, Array<SteerableComponent> all){

		SteerableComponent steer=entity.getComponent(SteerableComponent.class);

		RadiusProximity<Vector2> proximity = new RadiusProximity<Vector2>(steer, all,steer.getBoundingRadius()/4f);
		CollisionAvoidance<Vector2> collisionAvoidanceSB1 = new CollisionAvoidance<Vector2>(steer, proximity);
		all.add(steer);
		if(steer.getSteeringBehavior()==null)
			steer.setSteeringBehavior(collisionAvoidanceSB1);
		else
			addBlendBehaviour(entity,collisionAvoidanceSB1);
	}

	public static void addBlendBehaviour(Entity entity, SteeringBehavior<Vector2> newBehaviour){

		SteerableComponent steer=entity.getComponent(SteerableComponent.class);
		SteeringBehavior previousBehaviour=steer.getSteeringBehavior();
		BlendedSteering<Vector2> bleandBehaviour = new BlendedSteering<Vector2>(steer)
				.setLimiter(NullLimiter.NEUTRAL_LIMITER) //
				.add(previousBehaviour, 1f)//
				.add(newBehaviour, 1f);
		steer.setSteeringBehavior(bleandBehaviour);
	}

	public static SteeringBehavior<Vector2> createWander(Steerable<Vector2> playerSteer){
		
		Wander<Vector2> wanderSB = new Wander<Vector2>(playerSteer) //
				.setLimiter(new FullLimiter(.1f, .1f,.1f,.1f)) //
				
				//.setLimiter(limiter)
				.setFaceEnabled(false) // set to 0 because independent facing is off
				.setAlignTolerance(0.001f) //
				.setDecelerationRadius(5) //
				.setTimeToTarget(0.1f) //
				.setWanderOffset(70) //
				.setWanderOrientation(10) //
				.setWanderRadius(2) //
				.setWanderRate(MathUtils.PI2 *4);
		
		return wanderSB;
	}
	
	
	public static SteeringBehavior<Vector2> createWanderBehaviour(Steerable<Vector2> playerSteer, float maxAngularAcceleration, float maxAngularSpeed){
		
		Wander<Vector2> wanderSB = new Wander<Vector2>(playerSteer) //
		// Don't use Face internally because independent facing is off
		.setFaceEnabled(false) //
		// We don't need a limiter supporting angular components because Face is not used
		// No need to call setAlignTolerance, setDecelerationRadius and setTimeToTarget for the same reason
		.setLimiter(new LinearAccelerationLimiter(30)) //
		.setLimiter(new AngularLimiter(maxAngularAcceleration, maxAngularSpeed))
		.setWanderOffset(60) //
		.setWanderOrientation(10) //
		.setWanderRadius(40) //
		.setWanderRate(MathUtils.PI2 * 4);
	
		return wanderSB;
		
	}
	
	public static SteeringBehavior<Vector2> createPathBehaviour(Steerable<Vector2> target1, Path linePath){
		
		FollowPath<Vector2, LinePathParam>  followPathSB = new FollowPath<Vector2, LinePathParam>(target1, linePath, 1f)
				.setTimeToTarget(0.1f) 
				.setArrivalTolerance(0.001f) 
				.setDecelerationRadius(80);
		
		return followPathSB;
	}
	
	public static void x(){

		//playerSteer.setMaxAngularAcceleration(100);
		//playerSteer.setMaxLinearSpeed(50);

/*		Arrive<Vector2> arriveSB = new Arrive<Vector2>(playerSteer, playerSteer1) //
		.setLimiter(new LinearLimiter(3500, 80)) //
		.setTimeToTarget(0.1f) //
		.setArrivalTolerance(0.001f) //
		.setDecelerationRadius(3);
*/
		/*ReachOrientation<Vector2> reachOrientationSB = new ReachOrientation<Vector2>(b2dSteer, b2dSteer1) //
				.setLimiter(new AngularLimiter(10, 20)) //
				.setTimeToTarget(0.1f) //
				.setAlignTolerance(0.001f) //
				.setDecelerationRadius(MathUtils.PI);
*/
		//	b2dSteer.setSteeringBehavior(reachOrientationSB);
		//playerSteer.setSteeringBehavior(arriveSB);
		//SB.createWander(playerSteer);

		//playerSteer.setMaxLinearAcceleration(1000);
		//playerSteer.setMaxAngularSpeed(100);

		//playerSteer.setMaxAngularSpeed(210);
		//playerSteer.setMaxAngularAcceleration(1000);
		//b2dSteer.setSteeringBehavior(SB.createSB1(b2dSteer, b2dSteer1));

		//playerSteer.setSteeringBehavior(SB.createSB1(playerSteer, playerSteer1));


	}
	
}
