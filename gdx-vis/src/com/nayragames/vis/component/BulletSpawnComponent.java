package com.nayragames.vis.component;

import com.artemis.Component;
import com.badlogic.gdx.math.MathUtils;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 01-08-2016
 *
 */
public class BulletSpawnComponent extends Component {

	public enum BulletSpawnType {
		SINGLE(0),
		DOUBLE(1),
		TRIPLE(3),
		CIRCULAR(4);

		public int defaultValue;
		BulletSpawnType(int type){
			defaultValue=type;
		}
	}

	public boolean isFire;
	public int counter;
	public BulletSpawnType bulletSpawnType= BulletSpawnType.SINGLE;
	public float time=100;
	//public Enums.Player player;

	public BulletSpawnComponent() {
		this(false, BulletSpawnType.SINGLE);
	}
	
	public BulletSpawnComponent(Boolean isFire,BulletSpawnType type) {
		//this(Enums.Player.NPC,type);
		this.isFire=isFire;
		counter= MathUtils.random(10);
	}

	//public BulletSpawnComponent(Enums.Player player, BulletSpawnType type){
		//this.player=player;
		//bulletSpawnType=type;
	//}
}
