package com.ng;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nayragames.gdxutils._Main;
import com.nayragames.gdxutils.b2d.PhysicsHelper;
import com.nayragames.o2d.SceneManager;
import com.nayragames.o2d.system.BoundsUpdater;
import com.nayragames.o2d.system.CircularMotionSystem;
import com.nayragames.o2d.system.MovementSystem;
import com.ng.manager.MainSceneManager;
import com.ng.screen.SplashScreen;

public class Main extends _Main implements InputProcessor {

	OrthographicCamera camera;
	public SceneManager sM;

	@Override
	public void create() {
		super.create();
		gameManager=new GameManager(this);
		sM =new SceneManager(this);

		sM.resourceManager.addSprite("gud",new TextureRegion(new Texture("image/img.png")));

	/*	sM.loadScene("menu").addSystems(
				new MainSceneManager(this, sM.sceneLoader),
				new BoundsUpdater(),new CircularMotionSystem(),new MovementSystem());
*/
		setScreen(new SplashScreen(this));

		camera=new OrthographicCamera();
		camera.setToOrtho(true,800/ PhysicsHelper.PIXEL_PER_METER,480/PhysicsHelper.PIXEL_PER_METER);

	/*	Helper.createWorld(camera);

		BodyDef bodyDef=Helper.createBodyDef(BodyDef.BodyType.DynamicBody,10,10);
		Body body=Helper.getWorld().createBody(bodyDef);

		CircleShape circleshape=new CircleShape();
		circleshape.setPosition(new Vector2(.5f, .10f));    //body is still in center only ball shape is 5 meter right from center
		circleshape.setRadius(.25f);

		body.createFixture(Helper.createFixtureDef(circleshape,1,1,.1f));
*/
		//Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render() {
		super.render();


		sM.update();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		//camera.setToOrtho(true,width/Helper.PIXEL_PER_METER,height/Helper.PIXEL_PER_METER);
	}

	@Override
	public void pause() {
		super.pause();
		gameManager.pause(true);
	}

	@Override
	public void resume() {
		super.resume();
		gameManager.resume();
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


/*
		BodyDef bodyDef=Helper.createBodyDef(BodyDef.BodyType.DynamicBody,screenX/32f,screenY/32f);
		Body body=Helper.getWorld().createBody(bodyDef);

		CircleShape circleshape=new CircleShape();
		//circleshape.setPosition(new Vector2(5, 10));    //body is still in center only ball shape is 5 meter right from center
		circleshape.setRadius(.25f);
		body.createFixture(Helper.createFixtureDef(circleshape,1,1,.1f));
*/

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		/*BodyDef bodyDef=Helper.createBodyDef(BodyDef.BodyType.StaticBody,screenX/32f,screenY/32f);
		Body body=Helper.getWorld().createBody(bodyDef);

		CircleShape circleshape=new CircleShape();
		//circleshape.setPosition(new Vector2(5, 10));    //body is still in center only ball shape is 5 meter right from center
		circleshape.setRadius(.05f);
		body.createFixture(Helper.createFixtureDef(circleshape,1,1,.1f));
*/
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