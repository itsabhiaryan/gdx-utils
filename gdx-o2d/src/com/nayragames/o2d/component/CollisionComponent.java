package com.nayragames.o2d.component;

import com.badlogic.ashley.core.Component;

/**
 * Created by ARYAN on 04-12-2015.
 */
public class CollisionComponent implements Component {

    public enum Type {
        MYSTRY,COLLECTION,
    }

    public Type collisionType= Type.COLLECTION;
    public boolean isBulletAssign;
    public int type;
    public boolean isCollected;

}
