package com.ng.vis.component;

import com.artemis.Component;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/9/2015.
 *
 */

public class LeftRightComponent extends Component {
	
	public enum Direction {
		FRONT,LEFT,RIGHT
	}
	
	public Direction direction= Direction.FRONT;
	public float margin,speed;

	public LeftRightComponent(){
		this(2,.05f);
	}

	public LeftRightComponent(float margin, float speed){
		this.margin=margin;
		this.speed=speed;
	}
}
