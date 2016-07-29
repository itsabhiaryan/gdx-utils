package com.nayragames.o2d;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.nayragames.gdxutils._Main;
import com.nayragames.gdxutils.services.OrientationServices;
import com.nayragames.o2d.component.ShapeComponent;
import com.nayragames.o2d.shaperenderer.ShapeRendererType;
import com.nayragames.o2d.system.*;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 11/29/2015.
 *
 * This class is used to load Scene and unload it.
 */
public class SceneManager {

    private static final String TAG = "[" + SceneManager.class.getSimpleName() + "]";
    public SceneLoader sceneLoader;
    private static String scenePath;
    private StretchViewport stretchViewport;
    private static _Main game;

    public ResourceLoader resourceManager;

    /**
     * initialization of ResourceManager which is used when we load any scene.
     *
     *
     */

    public SceneManager(_Main game){
        SceneManager.game=game;
        resourceManager=new ResourceLoader(game.width,game.height);
        resourceManager.initAllResources();

        stretchViewport=new StretchViewport(game.width,game.height);
        sceneLoader=new SceneLoader(resourceManager);
        //addExternalMapperToRetriever();
        isLoaded=true;
    }

    public void addExternalMapperToRetriever(){
        ComponentRetriever.addMapper(ShapeComponent.class);
    }

    /**
     * Different method for loading of different scene.
     *
     */

    public boolean isLoaded;
    public static ShapeRendererType shapeRendererType;

    public SceneLoader loadMainScene () {

        unloadPreviousScene();
        scenePath = SceneConstant.SceneName.MAIN_SCENE.value;
        sceneLoader.loadScene(scenePath,stretchViewport);
        //sceneLoader.getEngine().addSystem(new MainSceneManager(game));
        sceneLoader.getEngine().addSystem(new BoundsUpdater());
        sceneLoader.getEngine().addSystem(new CircularMotionSystem());
        game.setOrientation(OrientationServices.Orientation.PORTRAIT);
        return sceneLoader;
    }

    public SceneLoader loadSplashScene(){
        unloadPreviousScene();

        scenePath = SceneConstant.SceneName.MAIN_SCENE.value;
        sceneLoader.loadScene(scenePath,stretchViewport);
        return sceneLoader;
    }

    public SceneLoader loadMenuScene () {
        unloadPreviousScene();

        scenePath = SceneConstant.SceneName.MENU_SCENE.value;
        sceneLoader.loadScene(scenePath,stretchViewport);
        //sceneLoader.getEngine().addSystem(new LevelSceneManager(game));

        game.setOrientation(OrientationServices.Orientation.PORTRAIT);

        return sceneLoader;
    }

    public SceneLoader loadSettingScene(){
        unloadPreviousScene();

        scenePath = SceneConstant.SceneName.SETTING_SCENE.value;
        sceneLoader.loadScene(scenePath,stretchViewport);
        //sceneLoader.getEngine().addSystem(new SettingSceneManager(game));
        game.setOrientation(OrientationServices.Orientation.PORTRAIT);



        return sceneLoader;
    }

    public SceneLoader loadHelpScene () {
        unloadPreviousScene();

        scenePath = SceneConstant.SceneName.HELP_SCENE.value;
        sceneLoader.loadScene(scenePath,stretchViewport);

        sceneLoader.injectExternalItemType(shapeRendererType=new ShapeRendererType(stretchViewport));
        //sceneLoader.getEngine().addSystem(new HelpSceneManager(game));
        sceneLoader.getEngine().addSystem(new Box2dDebugRenderSystem(sceneLoader,stretchViewport));
        sceneLoader.getEngine().addSystem(new HudRenderSystem(sceneLoader,stretchViewport));
        sceneLoader.getEngine().addSystem(new CircularMotionSystem());


        game.setOrientation(OrientationServices.Orientation.LANDSCAPE);


        return sceneLoader;
    }

    public SceneLoader loadAboutScene () {
        unloadPreviousScene();

        scenePath = SceneConstant.SceneName.ABOUT_SCENE.value;
        sceneLoader.loadScene(scenePath,stretchViewport);

        sceneLoader.injectExternalItemType(shapeRendererType=new ShapeRendererType(stretchViewport));
        //AboutSceneManager aboutSceneManager=new AboutSceneManager(game,new ItemWrapper(sceneLoader.getRoot()));
        //sceneLoader.getEngine().addSystem(aboutSceneManager);
        //sceneLoader.getEngine().addSystem(new Box2dDebugRenderSystem(sceneLoader,scratch));
        sceneLoader.getEngine().addSystem(new HudRenderSystem(sceneLoader,stretchViewport));

        game.setOrientation(OrientationServices.Orientation.LANDSCAPE);


        return sceneLoader;
    }

    public SceneLoader loadGameOverScene () {
        unloadPreviousScene();

        scenePath = SceneConstant.SceneName.GAME_OVER_SCENE.value;
        sceneLoader.loadScene(scenePath,stretchViewport);
        //sceneLoader.getEngine().addSystem(new GameOverSceneManager(game,sceneLoader));
        sceneLoader.getEngine().addSystem(new HudRenderSystem(sceneLoader,stretchViewport));
        sceneLoader.getEngine().addSystem(new CircularMotionSystem());

        game.setOrientation(OrientationServices.Orientation.LANDSCAPE);

        return sceneLoader;
    }

    public SceneLoader loadGameScene() {
        unloadPreviousScene();

        scenePath = SceneConstant.SceneName.GAME.value;
        sceneLoader.loadScene(scenePath,stretchViewport);
        //sceneLoader.getEngine().addSystem(new GameSceneManager(sceneLoader,game));
        sceneLoader.getEngine().addSystem(new CircularMotionSystem());
        sceneLoader.getEngine().addSystem(new RotationSystem());
        sceneLoader.getEngine().addSystem(new BoundsUpdater());
        sceneLoader.getEngine().addSystem(new ExpiringSystem(sceneLoader.getEngine()));

        game.setOrientation(OrientationServices.Orientation.PORTRAIT);

        return sceneLoader;
    }


    private static void unloadPreviousScene () {

    }

    public void dispose(){
        resourceManager.dispose();
    }
}
