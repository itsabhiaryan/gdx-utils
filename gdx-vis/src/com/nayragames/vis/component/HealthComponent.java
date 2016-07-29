package com.nayragames.vis.component;

import com.artemis.Component;

/**
 * Created by ARYAN on 12/9/2015.
 */
public class HealthComponent extends Component {

    public float health=10,maximumHealth=10;
    public float decrement=1;

    public HealthComponent (float health,float maximumHealth){
       this.health=health;
       this.maximumHealth=maximumHealth;
    }

    public HealthComponent(){
        this(10,10);
    }

    public HealthComponent(float decrement){
        this();
        this.decrement=decrement;
    }
}
