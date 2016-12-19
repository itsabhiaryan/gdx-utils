package com.ng.vis.component;

import com.artemis.Component;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 04-12-2015.
 */
public class CollisionComponent extends Component {

    public enum Type {
        MYSTRY,COLLECTION,
    }

    public Type collisionType= Type.COLLECTION;
    public boolean isBulletAssign;
    public int type;
    public boolean isCollected;

}
