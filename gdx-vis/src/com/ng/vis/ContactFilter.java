package com.ng.vis;

import com.artemis.World;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.ng.vis.physics.PhysicsHelper;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 30-12-2015.
 *
 * Used to decide collision between to body is about to happen or not.
 *
 */
public class ContactFilter implements com.badlogic.gdx.physics.box2d.ContactFilter {

    World world;
    private static final String TAG = "[" + ContactFilter.class.getSimpleName() + "]";

    public ContactFilter(World world){
        this.world=world;
        PhysicsHelper.getPhysicsWorld(world).setContactFilter(this);
    }

    @Override
    public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {

        //if ((fixtureA == m_platform && fixtureB == m_character) || (fixtureA == m_platform && fixtureB == m_character)) {

        Body body;
        //if(fixtureA.getBody().equals(world.getSystem(GameSceneManager.class).playerBody))
          //  body=fixtureB.getBody();
        //else
          //  body=fixtureA.getBody();


       // Vector2 position = world.getSystem(GameSceneManager.class).playerBody.getPosition();
        //if (position.y < body.getPosition().y+.5f)
          //  return false;
        //else
          //  return true;
		/*} else
			return true;*/
		return true;
    }
}
