package com.nayragames.vis.component;

import com.artemis.Component;

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
