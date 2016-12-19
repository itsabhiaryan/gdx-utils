package com.ng.vis;

import com.artemis.BaseSystem;
import com.artemis.managers.GroupManager;
import com.artemis.managers.PlayerManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.runtime.RuntimeConfiguration;
import com.kotcrab.vis.runtime.RuntimeContext;
import com.kotcrab.vis.runtime.data.LayerData;
import com.kotcrab.vis.runtime.data.PhysicsSettings;
import com.kotcrab.vis.runtime.data.SceneData;
import com.kotcrab.vis.runtime.scene.*;
import com.kotcrab.vis.runtime.system.physics.PhysicsSystem;
import com.kotcrab.vis.runtime.system.render.RenderBatchingSystem;
import com.kotcrab.vis.runtime.util.EntityEngineConfiguration;
import com.ng.gdxutils._Main;
import com.ng.vis.system.*;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 11/29/2015.
 *
 * This class is used to load Scene and unload it.
 *
 */
public class SceneManager {

    private static final String TAG = SceneManager.class.getSimpleName();
    private SpriteBatch batch;
    private VisAssetManager manager;
    public Scene scene;
    private _Main game;
    private static String scenePath;
    public static Array<LayerData> layers;

    /**
     * initialization of VisAssetManager which is used when we load any scene.
     *
     * @see #setRuntimeConfig() after initialization of VisAssetManager.
     *
     */

    public SceneManager(_Main game){
        this.game=game;

        batch = new SpriteBatch();
        manager=new VisAssetManager(batch);

        setRuntimeConfig();
    }

    private void setRuntimeConfig(){
        RuntimeConfiguration configuration = new RuntimeConfiguration();
        manager.getSceneLoader().setRuntimeConfig(configuration);
    }

    public void update(){
        scene.render();
    }

    public void load(String sceneName,SceneLoader.SceneParameter sceneParameter){

        unloadPreviousScene();
        scenePath=sceneName;
        scene = manager.loadSceneNow(scenePath, sceneParameter);
    }


    public void load(String sceneName, BaseSystem[] baseSystems, Class<? extends BaseSystem>... systemClass){

        unloadPreviousScene();
        scenePath=sceneName;
        SceneLoader.SceneParameter parameter = new SceneLoader.SceneParameter();

        for (Class classes:systemClass)
            parameter.config.addSystem(classes);

        for(final BaseSystem baseSystem1:baseSystems){

            parameter.config.addSystem(new SystemProvider() {
                @Override
                public BaseSystem create(EntityEngineConfiguration config, RuntimeContext context, SceneData data) {
                    return baseSystem1;
                }
            });
        }
        scene = manager.loadSceneNow(scenePath, parameter);
    }

    public void load(String sceneName,Class<? extends BaseSystem>... systemClass){

        unloadPreviousScene();
        scenePath=sceneName;
        SceneLoader.SceneParameter parameter = new SceneLoader.SceneParameter();

        for (Class classes:systemClass)
            parameter.config.addSystem(classes);

        scene = manager.loadSceneNow(scenePath, parameter);

    }


    /**
     * Different method for loading of different scene.
     * @see #unloadPreviousScene() First unload previous scene from VisAssetManager.
     *
     */


    public void loadMainScene () {
        unloadPreviousScene();

        SceneLoader.SceneParameter parameter = new SceneLoader.SceneParameter();
        parameter.config.disable(SceneFeature.BOX2D_DEBUG_RENDER_SYSTEM);

        parameter.config.addSystem(BoundsCreator.class);
        parameter.config.addSystem(BoundsUpdater.class);
        parameter.config.addSystem(SoundEffectSystem.class);
        parameter.config.addSystem(MainPageSystem.class);
        parameter.config.addSystem(PhysicsUpdateSystem.class, SceneConfig.Priority.HIGH);
        parameter.config.addSystem(PhysicsManager.class, SceneConfig.Priority.HIGH);

        parameter.config.addSystem(new SystemProvider() {
            @Override
            public BaseSystem create(EntityEngineConfiguration config, RuntimeContext context, SceneData data) {
                return new ShapeRendererSystem(config.getSystem(RenderBatchingSystem.class));
            }
        });

        /*parameter.config.addSystem(new SystemProvider() {
            @Override
            public BaseSystem create(EntityEngineConfiguration config, RuntimeContext context, SceneData data) {
                return new MainSceneManager(game);
            }
        });*/

        //scenePath = SceneConstant.SceneName.MAIN_SCENE.value;
        scene = manager.loadSceneNow(scenePath, parameter);
        //game.setOrientation(IGoogleServices.LANDSCAPE);
    }

    public void loadMenuScene () {
        unloadPreviousScene();

        SceneLoader.SceneParameter parameter = new SceneLoader.SceneParameter();

        parameter.config.addSystem(BoundsCreator.class);
        parameter.config.addSystem(BoundsUpdater.class);
        parameter.config.addSystem(SoundEffectSystem.class);
        /*parameter.config.addSystem(new SystemProvider() {
            @Override
            public BaseSystem create(EntityEngineConfiguration config, RuntimeContext context, SceneData data) {
                return new MenuSceneManager(game);
            }
        });*/

        //scenePath = SceneConstant.SceneName.MENU_SCENE.value;
        scene = manager.loadSceneNow(scenePath, parameter);
    }

    public void loadHelpScene () {
        unloadPreviousScene();

        SceneLoader.SceneParameter parameter = new SceneLoader.SceneParameter();

        /*parameter.config.addSystem(new SystemProvider() {
            @Override
            public BaseSystem create(EntityEngineConfiguration config, RuntimeContext context, SceneData data) {
                return new HelpSceneManager(game);
            }
        });*/

        //scenePath = SceneConstant.SceneName.HELP_SCENE.value;
        scene = manager.loadSceneNow(scenePath, parameter);
    }

    public void loadAboutScene () {
        unloadPreviousScene();

        SceneLoader.SceneParameter parameter = new SceneLoader.SceneParameter();

        /*parameter.config.addSystem(new SystemProvider() {
            @Override
            public BaseSystem create(EntityEngineConfiguration config, RuntimeContext context, SceneData data) {
                return new AboutSceneManager(game);
            }
        });*/

        //scenePath = SceneConstant.SceneName.ABOUT_SCENE.value;
        scene = manager.loadSceneNow(scenePath, parameter);
    }

    public void loadGameOverScene () {
        unloadPreviousScene();
        SceneLoader.SceneParameter parameter = new SceneLoader.SceneParameter();

        parameter.config.addSystem(CircularMotionSystem.class);
        //parameter.config.addSystem(GameOverSystem.class);

        parameter.config.addSystem(new SystemProvider() {
            @Override
            public BaseSystem create(EntityEngineConfiguration config, RuntimeContext context, SceneData data) {
                return new ShapeRendererSystem(config.getSystem(RenderBatchingSystem.class));
            }
        });

        /*parameter.config.addSystem(new SystemProvider() {
            @Override
            public BaseSystem create(EntityEngineConfiguration config, RuntimeContext context, SceneData data) {
                return new GameOverSceneManager(game);
            }
        });*/

        //scenePath = SceneConstant.SceneName.GAME_OVER_SCENE.value;
        scene = manager.loadSceneNow(scenePath, parameter);
    }

    public Scene loadGameScene() {
        unloadPreviousScene();
        //MainSceneManager.timer.clear();

        /*int stage=GameSceneManager.stage;
        scenePath=(stage==0)?SceneConstant.SceneName.GAME_SCENE1.value
                 :(stage==1)?SceneConstant.SceneName.GAME_SCENE2.value
                 :(stage==2)?SceneConstant.SceneName.GAME_SCENE3.value
                 :SceneConstant.SceneName.GAME_SCENE1.value;

        scene = manager.loadSceneNow(scenePath, getParameter());
        game.setOrientation(IGoogleServices.LANDSCAPE);*/

        layers = scene.getLayerData();
        return scene;
    }

    private static SceneLoader.SceneParameter getParameter(){

        SceneLoader.SceneParameter parameter = new SceneLoader.SceneParameter();

        parameter.config.disable(SceneFeature.BOX2D_DEBUG_RENDER_SYSTEM);
        parameter.config.disable(SceneFeature.PHYSICS_SPRITE_UPDATE_SYSTEM);

        parameter.config.addSystem(GroupManager.class);
        parameter.config.addSystem(PlayerManager.class);

        //parameter.config.addSystem(HudRenderSystem.class);
        //parameter.config.addSystem(PlayerSystem.class, SceneConfig.Priority.HIGH);
        parameter.config.addSystem(PhysicsUpdateSystem.class, SceneConfig.Priority.HIGH);
        parameter.config.addSystem(PhysicsManager.class, SceneConfig.Priority.HIGH);
        parameter.config.addSystem(SoundEffectSystem.class);
        parameter.config.addSystem(CircularMotionSystem.class);

        /*parameter.config.addSystem(new SystemProvider() {
            @Override
            public BaseSystem create(EntityEngineConfiguration config, RuntimeContext context, SceneData data) {
                return new GameSceneManager(game);
            }
        });*/

        parameter.config.addSystem(new SystemProvider() {
            @Override
            public BaseSystem create(EntityEngineConfiguration config, RuntimeContext context, SceneData data) {
                return new ShapeRendererSystem(config.getSystem(RenderBatchingSystem.class));
            }
        });

        return parameter;
    }

    public static LayerCordsSystem getLayer(int zDepth){
        return layers.get(zDepth).cordsSystem;
    }

    private void unloadPreviousScene () {

        if (scenePath != null) {
            if(manager.containsAsset(scene))
            manager.unload(scenePath);
            if(ShapeRendererSystem.isRendererAttached) {
                ShapeRendererSystem.shapeRenderer.dispose();
                ShapeRendererSystem.isRendererAttached=false;
            }

            scenePath = null;
            scene = null;
        }
    }

    public VisAssetManager getSceneManager(){
        return manager;
    }

    private PhysicsSystem addPhysicsSystem(){
        PhysicsSettings physicsSettings=new PhysicsSettings();
        physicsSettings.allowSleep=true;
        physicsSettings.gravityX=0;
        physicsSettings.gravityY=-9.8f;
        physicsSettings.physicsEnabled=true;

        PhysicsSystem physicsSystem=new PhysicsSystem(new PhysicsSettings());
        return physicsSystem;
    }

    public  void dispose(){
        batch.dispose();
        manager.dispose();
        if(ShapeRendererSystem.isRendererAttached)
            ShapeRendererSystem.shapeRenderer.dispose();
    }
}
