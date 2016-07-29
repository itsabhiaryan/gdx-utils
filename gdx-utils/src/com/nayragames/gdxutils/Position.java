package com.nayragames.gdxutils;

import com.badlogic.gdx.math.Vector2;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 24-07-2016
 *
 */

public class Position extends Vector2 {

	public boolean isCentric;

	public Position(float x, float y) {
		super(x,y);
	}
	
	public static Position makePosition(float x, float y) {

		return new Position(x, y);
	}

	public static Position addPoint(Position point1, Position point2 ) {

		return makePosition(point1.x + point2.x, point1.y + point2.y);
	}

	public static Position makePosition(float x, float y,boolean isCentric) {

		Position position=new Position(x,y);
		position.isCentric=isCentric;

		return position;
	}

	public Position addPoint(float x, float y) {

		this.x=this.x+x;
		this.y=this.y+y;

		return this;
	}

	public static double findDistance(Position endPoint, Position startPoint){

		float distanceX = (endPoint.x - startPoint.x) * (endPoint.x - startPoint.x);
		float distanceY = (endPoint.y - endPoint.y) * (endPoint.y - startPoint.y);
		float distanceSquare = distanceX + distanceY;
		final double distance = Math.sqrt(distanceSquare);

		return  distance;
	}

	public static float distance(Position touchStart, Position touchEnd) {

		float f1 = touchEnd.x - touchStart.x;
		float f2 = touchEnd.y - touchStart.y;
		return (float)Math.sqrt(f1 * f1 + f2 * f2);

	}

	public static Position sub(Position paramGpoint1, Position paramGpoint2) {
		return makePosition(paramGpoint1.x - paramGpoint2.x, paramGpoint1.y - paramGpoint2.y);
	}

}
