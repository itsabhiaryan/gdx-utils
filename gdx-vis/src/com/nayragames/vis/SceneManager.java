package com.nayragames.vis;

import com.artemis.BaseSystem;
import com.artemis.managers.GroupManager;
import com.artemis.managers.PlayerManager;
import com.badlogic.gdx.Game;
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
import com.nayragames.vis.system.*;

/**
 * This class is used to load Scene and unload it.
 *
 * Created by ARYAN on 11/29/2015.
 */
public class SceneManager {

    private static final String TAG = SceneManager.class.getSimpleName();
    private static SpriteBatch batch;
    private static VisAssetManager manager;
    public static Scene scene;
    private static String scenePath;
    private static Game game;
    public static Array<LayerData> layers;

    /**
     * initialization of VisAssetManager which is used when we load any scene.
     *
     * @see #setRuntimeConfig() after initialization of VisAssetManager.
     *
     */

    public static void init(Game game){
        SceneManager.game=game;

        batch = new SpriteBatch();
        manager=new VisAssetManager(batch);

        setRuntimeConfig();
    }

    private static void setRuntimeConfig(){
        RuntimeConfiguration configuration = new RuntimeConfiguration();
        manager.getSceneLoader().setRuntimeConfig(configuration);
    }

    /**
     * Different method for loading of different scene.
     * @see #unloadPreviousScene() First unload previous scene from VisAssetManager.
     *
     */

    public static void loadMainScene () {
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

    public static void loadMenuScene () {
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

    public static void loadHelpScene () {
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

    public static void loadAboutScene () {
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

    public static void loadGameOverScene () {
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

    public static Scene loadGameScene() {
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

    private static void unloadPreviousScene () {

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

    public static VisAssetManager getSceneManager(){
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

    public static void dispose(){
        batch.dispose();
        manager.dispose();
        if(ShapeRendererSystem.isRendererAttached)
            ShapeRendererSystem.shapeRenderer.dispose();
    }
}
