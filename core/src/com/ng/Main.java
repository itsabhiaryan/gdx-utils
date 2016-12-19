package com.ng;

import com.artemis.BaseSystem;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.kotcrab.vis.runtime.RuntimeContext;
import com.kotcrab.vis.runtime.data.SceneData;
import com.kotcrab.vis.runtime.scene.SceneConfig;
import com.kotcrab.vis.runtime.scene.SceneLoader;
import com.kotcrab.vis.runtime.scene.SystemProvider;
import com.kotcrab.vis.runtime.system.render.RenderBatchingSystem;
import com.kotcrab.vis.runtime.util.EntityEngineConfiguration;
import com.ng.gdxutils._GameManager;
import com.ng.gdxutils._Main;
import com.ng.gdxutils.b2d.GenericPhysicsHelper;
import com.ng.vis.SceneManager;
import com.ng.manager.HomeSceneManager;
import com.ng.vis.system.*;

//import com.nayragames.o2d.SceneManager;

public class Main extends _Main implements InputProcessor {

	OrthographicCamera camera;
//	public SceneManager sM;
	public SceneManager sM;

	@Override
	public _GameManager createManager() {
		return new GameManager(this);
	}

	@Override
	public void create() {
		super.create();

//		sM =new SceneManager(this);
//		sM.resourceManager.addSprite("gud",new TextureRegion(new Texture("image/img.png")));

		/*	sM.loadScene("menu").addSystems(
				new MainSceneManager(this, sM.sceneLoader),
				new BoundsUpdater(),new CircularMotionSystem(),new MovementSystem());
		*/

		sM=new SceneManager(this);

		SceneLoader.SceneParameter parameter=new SceneLoader.SceneParameter();
		parameter.config.addSystem(new SystemProvider() {
			@Override
			public BaseSystem create(EntityEngineConfiguration config, RuntimeContext context, SceneData data) {
				return new ShapeRendererSystem(config.getSystem(RenderBatchingSystem.class));
			}
		});
		parameter.config.addSystem(new SystemProvider() {
			@Override
			public BaseSystem create(EntityEngineConfiguration config, RuntimeContext context, SceneData data) {
				return new HomeSceneManager(gameManager.game);
			}
		});

		parameter.config.addSystem(BoundsCreator.class);
		parameter.config.addSystem(BoundsUpdater.class);
		//parameter.config.addSystem(SoundEffectSystem.class);
		//parameter.config.addSystem(MainPageSystem.class);
		//parameter.config.addSystem(PhysicsUpdateSystem.class, SceneConfig.Priority.HIGH);
		//parameter.config.addSystem(PhysicsManager.class, SceneConfig.Priority.HIGH);
		//parameter.config.addSystem(PhysicsSystem.class);
		parameter.config.addSystem(PhysicsUpdateSystem.class, SceneConfig.Priority.HIGH);
		parameter.config.addSystem(PhysicsManager.class, SceneConfig.Priority.HIGH);
		sM.load("scene/firstscene.scene",parameter);

		//setScreen(new SplashScreen(this));

		camera=new OrthographicCamera();
		camera.setToOrtho(true,800/ GenericPhysicsHelper.PIXEL_PER_METER,480/GenericPhysicsHelper.PIXEL_PER_METER);

	/*	PhysicsHelper.createWorld(camera);
		BodyDef bodyDef=PhysicsHelper.createBodyDef(BodyDef.BodyType.DynamicBody,10,10);
		Body body=PhysicsHelper.getWorld().createBody(bodyDef);

		CircleShape circleshape=new CircleShape();
		circleshape.setPosition(new Vector2(.5f, .10f));    //body is still in center only ball shape is 5 meter right from center
		circleshape.setRadius(.25f);
		body.createFixture(PhysicsHelper.createFixtureDef(circleshape,1,1,.1f));
	*/
		//Gdx.input.setInputProcessor(this);
//		Gdx.files.internal();
	}

	@Override
	public void render() {
		super.render();

		sM.update();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		//camera.setToOrtho(true,width/PhysicsHelper.PIXEL_PER_METER,height/PhysicsHelper.PIXEL_PER_METER);
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
		BodyDef bodyDef=PhysicsHelper.createBodyDef(BodyDef.BodyType.DynamicBody,screenX/32f,screenY/32f);
		Body body=PhysicsHelper.getWorld().createBody(bodyDef);

		CircleShape circleshape=new CircleShape();
		//circleshape.setPosition(new Vector2(5, 10));    //body is still in center only ball shape is 5 meter right from center
		circleshape.setRadius(.25f);
		body.createFixture(PhysicsHelper.createFixtureDef(circleshape,1,1,.1f));
		*/
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		/*
		BodyDef bodyDef=PhysicsHelper.createBodyDef(BodyDef.BodyType.StaticBody,screenX/32f,screenY/32f);
		Body body=PhysicsHelper.getWorld().createBody(bodyDef);

		CircleShape circleshape=new CircleShape();
		//circleshape.setPosition(new Vector2(5, 10));    //body is still in center only ball shape is 5 meter right from center
		circleshape.setRadius(.05f);
		body.createFixture(PhysicsHelper.createFixtureDef(circleshape,1,1,.1f));
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