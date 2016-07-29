package com.nayragames.vis.component;

import com.artemis.Component;

/**
 * Created by ARYAN on 04-12-2015.
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
