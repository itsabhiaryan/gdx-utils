package com.nayragames.o2d;

import com.badlogic.gdx.physics.box2d.*;
import com.uwsoft.editor.renderer.SceneLoader;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 30-12-2015.
 *
 */
public class ContactListener implements com.badlogic.gdx.physics.box2d.ContactListener {

    Body playerBody;
    public static boolean isCollide;
    SceneLoader sceneLoader;
    private static final String TAG = "[" + ContactListener.class.getSimpleName() + "]";

    public ContactListener(SceneLoader sceneLoader, Body body){
        this.sceneLoader=sceneLoader;
        this.playerBody=body;
        //Helper.getPhysicsWorld(world).setContactListener(this);
    }

    @Override
    public void beginContact(Contact contact) {

        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if(!isCollide && (fixtureA.getFilterData().categoryBits==CollisionBits.SlideCharacter ||fixtureB.getFilterData().categoryBits==CollisionBits.SlideCharacter)) {
            isCollide=true;
        }

        //System.out.println("beginContact");
        //if(gM.isStart)
        //gM.playerBody.setLinearVelocity(2,0);

       // Fixture fixtureA = contact.getFixtureA();
        //Fixture fixtureB = contact.getFixtureB();

        //if (fixtureA == m_platform || fixtureB == m_platform) {

        if(fixtureA.getFilterData().categoryBits==CollisionBits.Character||fixtureB.getFilterData().categoryBits==CollisionBits.Character){
                if (!isCollide) {
                    //world.getSystem(GameSceneManager.class).playerEntity.getComponent(Variables.class).putInt("phy", 2);
                    //world.getSystem(PlayerSystem.class).isCollide = true;
                    isCollide = true;
                }
        }
    }

    @Override
    public void endContact(Contact contact) {

        /*
//        System.out.println("begin touch");
        //System.out.println("beginContact");
        //if(gM.isStart)
        //gM.playerBody.setLinearVelocity(2,0);

        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        //if (fixtureA == m_platform || fixtureB == m_platform) {

        if(fixtureA.getBody().equals(gameManager.playerBody)||fixtureB.getBody().equals(gameManager.playerBody)) {
            //          if(!isTouched) {
            System.out.println("In Touched");
            if(!isCollide){
                EntityFactory.createPlayerParts(gameManager.playerBody,world);
                isCollide =true;
            }
            isTouched=true;
            //        }
        }
*/
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        if((fixtureA.getFilterData().categoryBits==CollisionBits.SlideCharacter ||fixtureB.getFilterData().categoryBits==CollisionBits.SlideCharacter)) {
            contact.setTangentSpeed(2.0f);
        }

        //System.out.println("presolve");
        /*Fixture fixture;
        if(contact.getFixtureA().getBody().equals(gameManager.playerBody))
            fixture=contact.getFixtureB();
        else
            fixture=contact.getFixtureA();

        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();*/

        //if (fixtureA == m_platform || fixtureB == m_platform) {

/*        if(fixtureA.getBody().equals(gameManager.playerBody)||fixtureB.getBody().equals(gameManager.playerBody)) {
            contact.setTangentSpeed(2.0f);

            if(!isTouched) {
                System.out.println("In Touched");
                //isTouched=true;
            }
        }*/
        //}
        //System.out.println("in presolve ");
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

       /* Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        if(!isCollide && (fixtureA.getFilterData().categoryBits==CollisionBits.SlideCharacter ||fixtureB.getFilterData().categoryBits==CollisionBits.SlideCharacter)) {
            isCollide=true;
        }*/

        //System.out.println("post resolve");
        //Fixture fixtureA = contact.getFixtureA();
        //Fixture fixtureB = contact.getFixtureB();
        //if(fixtureA.getBody().equals(gameManager.playerBody)||fixtureB.getBody().equals(gameManager.playerBody)) {
        //}
    }

    /*	@Override
	public void beginContact(Contact contact) {
		//System.out.println("beginContact");

		if(gM.isStart)
			gM.playerBody.setLinearVelocity(1.5f,0);
	}

	@Override
	public void endContact(Contact contact) {
		//System.out.println("end contact");
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		//System.out.println("presolve");

		Fixture fixture;
		if(contact.getFixtureA().getBody().equals(gM.playerBody))
			fixture=contact.getFixtureB();
		else
			fixture=contact.getFixtureA();

		*//*if(gM.playerPosition.getLinearVelocity().y>0)
		  fixture.setSensor(true);
		else
			fixture.setSensor(false);*//*
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		//System.out.println("post resolve");
	}*/
}
