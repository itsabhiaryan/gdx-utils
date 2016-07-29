package com.nayragames.o2d.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.MathUtils;
import com.nayragames.o2d.Enums;

public class BulletSpawnComponent implements Component {

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
	public Enums.Player player;

	public BulletSpawnComponent() {
		this(false, BulletSpawnType.SINGLE);
	}
	
	public BulletSpawnComponent(Boolean isFire, BulletSpawnType type) {
		this(Enums.Player.NPC,type);
		this.isFire=isFire;
		counter= MathUtils.random(10);
	}

	public BulletSpawnComponent(Enums.Player player, BulletSpawnType type){
		this.player=player;
		bulletSpawnType=type;
	}
}
