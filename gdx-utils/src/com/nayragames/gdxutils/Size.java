package com.nayragames.gdxutils;

import com.badlogic.gdx.math.Vector2;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 24-07-2016
 *
 */

public class Size extends Vector2 {

	public Size(float x, float y) {
		super(x,y);
	}
	
	public static Size makeSize(float x, float y) {
		return new Size(x, y);
	}
	
	
}
