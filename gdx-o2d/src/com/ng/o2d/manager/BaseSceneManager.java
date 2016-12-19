package com.ng.o2d.manager;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.ng.gdxutils._Main;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.ViewPortComponent;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

/**
 * (c) 2010 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 30-11-2015.
 *
 * Parent Manager class of all Manager class.
 *
 */

public abstract class BaseSceneManager extends EntitySystem implements InputProcessor {

	private static final String TAG = BaseSceneManager.class.getSimpleName();
	protected _Main game;
	public Camera camera;
	protected Vector3 unProjectVec = new Vector3();
	protected Vector3 unProjectVecDrag = new Vector3();
	public static InputProcessor sceneInput ;
	public Entity root;
	protected SceneLoader sceneLoader;

	public BaseSceneManager(_Main game,SceneLoader sceneLoader) {
		this.game = game;
		sceneInput=this;
		this.sceneLoader=sceneLoader;
		root =this.sceneLoader.getRoot();

		System.out.println(sceneLoader);
		System.out.println("Value of"+root);

		camera=ComponentRetriever.get(root,ViewPortComponent.class).viewPort.getCamera();
		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);
	}



	@Override
	public boolean scrolled (int amount) {
		return false;
	}

	@Override
	public boolean keyDown (int keycode) {

	/*	if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.BACK) {
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

	/*public static void musicAction(boolean isPlay){
		if(isPlay)
			BaseSceneManager.musicComponent.play();
		else
			BaseSceneManager.musicComponent.pause();
	}*/

}
