package com.nayragames.vis.component;


import com.artemis.Component;
import com.artemis.Entity;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by ARYAN on 09-12-2015.
 */
public class DragonComponent extends Component {

    public enum Direction {
        FRONT,LEFT,RIGHT
    }

    Vector2 firstPoint,secondPoint;
    Vector2 centerPoint;
    public Direction direction= Direction.FRONT;
    public float margin=1;
    public float speed=.05f;
    public static float MARGIN=1;


    public DragonComponent(int type,Entity textEntity,Entity leftHandEntity,Entity rightHandEntity){

        firstPoint=new Vector2();
        secondPoint=new Vector2();
        centerPoint=new Vector2();
    }

    public DragonComponent(){

    }

}
