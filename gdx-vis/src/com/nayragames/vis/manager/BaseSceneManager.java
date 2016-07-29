package com.nayragames.vis.manager;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.Manager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.kotcrab.vis.runtime.component.VisMusic;
import com.kotcrab.vis.runtime.component.VisSprite;
import com.kotcrab.vis.runtime.system.CameraManager;
import com.kotcrab.vis.runtime.system.VisIDManager;
import com.kotcrab.vis.runtime.util.AfterSceneInit;
import com.nayragames.vis.GenericEntityBuilder;
import com.nayragames.vis.component.Bounds;


/**
 * Parent Manager class of all Manager class.
 *
 * @author ARYAN on 30-11-2015.
 */

public abstract class BaseSceneManager extends Manager implements InputProcessor, AfterSceneInit {

	private static final String TAG = "[" + BaseSceneManager.class.getSimpleName() + "]";
	protected ComponentMapper<VisSprite> spriteCm;
	protected ComponentMapper<Bounds> boundsCm;
	protected Game game;
	protected CameraManager cameraManager;
	protected VisIDManager idManager;
	protected Vector3 unProjectVec = new Vector3();
	protected Vector3 unProjectVecDrag = new Vector3();
	public static InputProcessor sceneInput ;
	public static VisMusic musicComponent;

	public BaseSceneManager(Game game) {
		this.game = game;
		sceneInput=this;
	}

	/**
	 * @param id entity id as a String
	 * @return sprite of SpriteComponent that entity have.
	 *
	 */

	protected Bounds getBounds (String id) {
		Entity entity = idManager.get(id);
		return boundsCm.get(entity);
	}

	@Override
	public void afterSceneInit () {
		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);
		//musicComponent= GenericEntityBuilder.createMusicEntity(world);
	}

/*	public static void pause(){
		if(musicComponent!=null) musicComponent.pause();
	}

	public static void resume(){
		if(musicComponent!=null&& Pref.getBoolean(Pref.BooleanValue.MUSIC_ENABLED))
			musicComponent.play();
	}*/

	@Override
	public boolean scrolled (int amount) {
		return false;
	}

	@Override
	public boolean keyDown (int keycode) {

		/*if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.BACK) {
			GameManager.setScreen(new MainScreen(game));
			GameManager.resume();
		}*/

		return false;
	}

	@Override
	public boolean keyUp (int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped (char character) {
		return false;
	}

	@Override
	public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp (int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged (int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved (int screenX, int screenY) {
		return false;
	}

	public static void musicAction(boolean isPlay){
		if(isPlay)
			BaseSceneManager.musicComponent.play();
		else
			BaseSceneManager.musicComponent.pause();
	}

}
