package com.ng.o2d.component;

import com.badlogic.ashley.core.Component;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 04-12-2015.
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
