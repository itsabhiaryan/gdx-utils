package com.nayragames.o2d.component;


import com.badlogic.ashley.core.Component;

public class MovementComponent implements Component {

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
