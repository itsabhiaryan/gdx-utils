package com.nayragames.o2d.component;


import com.badlogic.ashley.core.Component;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/9/2015.
 */
public class HealthComponent implements Component {

    public float health=10,maximumHealth=10;
    public float decrement=1;

    public HealthComponent(float health, float maximumHealth){
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
