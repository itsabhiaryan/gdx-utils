package com.ng.vis.component;

import com.artemis.Component;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/9/2015.
 *
 */

public class MovementComponent extends Component {

	public float speed;
	public boolean isFire;
	public int counter;
	
	public MovementComponent(){
		this(.01f);
	}
	
	public MovementComponent(float speed) {
		this.speed=speed;
	}
	
	/*public BulletComponent(float speed,boolean isFire) {
		this.speed=speed;
		this.isFire=isFire;
	}*/
	
}
