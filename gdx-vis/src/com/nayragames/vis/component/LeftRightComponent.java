package com.nayragames.vis.component;

import com.artemis.Component;

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