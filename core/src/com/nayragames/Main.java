package com.nayragames;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.nayragames.gdxutils.b2d.PhysicsHelper;

public class Main extends Game implements InputProcessor {


	OrthographicCamera camera;

	@Override
	public void create() {

		camera=new OrthographicCamera();
		camera.setToOrtho(true,800/ PhysicsHelper.PIXEL_PER_METER,480/PhysicsHelper.PIXEL_PER_METER);

		PhysicsHelper.createWorld(camera);

		BodyDef bodyDef=PhysicsHelper.createBodyDef(BodyDef.BodyType.DynamicBody,10,10);
		Body body=PhysicsHelper.getWorld().createBody(bodyDef);


		CircleShape circleshape=new CircleShape();
		circleshape.setPosition(new Vector2(.5f, .10f));    //body is still in center only ball shape is 5 meter right from center
		circleshape.setRadius(.25f);

		body.createFixture(PhysicsHelper.createFixtureDef(circleshape,1,1,.1f));

		Gdx.input.setInputProcessor(this);

	}

	@Override
	public void render() {
		super.render();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

		PhysicsHelper.update();


	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		camera.setToOrtho(true,width/PhysicsHelper.PIXEL_PER_METER,height/PhysicsHelper.PIXEL_PER_METER);


	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {


		BodyDef bodyDef=PhysicsHelper.createBodyDef(BodyDef.BodyType.DynamicBody,screenX/32f,screenY/32f);
		Body body=PhysicsHelper.getWorld().createBody(bodyDef);

		CircleShape circleshape=new CircleShape();
		//circleshape.setPosition(new Vector2(5, 10));    //body is still in center only ball shape is 5 meter right from center
		circleshape.setRadius(.25f);
		body.createFixture(PhysicsHelper.createFixtureDef(circleshape,1,1,.1f));

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		BodyDef bodyDef=PhysicsHelper.createBodyDef(BodyDef.BodyType.StaticBody,screenX/32f,screenY/32f);
		Body body=PhysicsHelper.getWorld().createBody(bodyDef);

		CircleShape circleshape=new CircleShape();
		//circleshape.setPosition(new Vector2(5, 10));    //body is still in center only ball shape is 5 meter right from center
		circleshape.setRadius(.05f);
		body.createFixture(PhysicsHelper.createFixtureDef(circleshape,1,1,.1f));

		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}