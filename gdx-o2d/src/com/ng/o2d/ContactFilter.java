package com.ng.o2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.uwsoft.editor.renderer.SceneLoader;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Arayan
 * @since 30-12-2015.
 *
 * Used to decide collision between to body is about to happen or not.
 */

public class ContactFilter implements com.badlogic.gdx.physics.box2d.ContactFilter {

    private static final String TAG = "[" + ContactFilter.class.getSimpleName() + "]";

    Body playerBody;

    public ContactFilter(SceneLoader sceneLoader){
        sceneLoader.world.setContactFilter(this);
    }

    @Override
    public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {

        //if ((fixtureA == m_platform && fixtureB == m_character) || (fixtureA == m_platform && fixtureB == m_character)) {

        Body body;
        if(fixtureA.getBody().equals(playerBody))
            body=fixtureB.getBody();
        else
            body=fixtureA.getBody();


        Vector2 position = playerBody.getPosition();
        if (position.y < body.getPosition().y+.5f)
            return false;
        else
            return true;

    }
}

