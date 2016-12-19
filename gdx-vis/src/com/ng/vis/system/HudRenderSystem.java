package com.ng.vis.system;

import com.artemis.Aspect;
import com.artemis.AspectSubscriptionManager;
import com.artemis.BaseSystem;
import com.artemis.EntitySubscription;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kotcrab.vis.runtime.component.PhysicsBody;
import com.kotcrab.vis.runtime.component.Transform;
import com.kotcrab.vis.runtime.component.VisSprite;
import com.kotcrab.vis.runtime.component.VisText;
import com.kotcrab.vis.runtime.system.CameraManager;
import com.kotcrab.vis.runtime.system.VisIDManager;
import com.kotcrab.vis.runtime.util.AfterSceneInit;
import com.ng.gdxutils._GameManager;
import com.ng.gdxutils.model.Position;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/8/2015.
 *
 * System is used only for debugging purpose used for getting Total Entity count and what is FPS.
 *
 */

public class HudRenderSystem extends BaseSystem implements AfterSceneInit {

    VisText fpsText,totalEntityText,physicsBodyText;
    EntitySubscription entitySubscription;
    AspectSubscriptionManager aspectSubscriptionManager;

    CameraManager cameraManager;
    VisIDManager idManager;
    static long startTime;
    @Override
    public void afterSceneInit() {

        initTime();
        Viewport viewPort=cameraManager.getViewport();

        float w=cameraManager.getCamera().viewportWidth;
        float h=cameraManager.getCamera().viewportHeight;

        Position fpsPoint= Position.makePosition(w/2,h/2);
        Position totalPoint= Position.makePosition(w/2,h/2);
        Position physicsPoint=Position.makePosition(w/2,w/2);

        if(w==4.8f && h==8) {
            fpsPoint= Position.makePosition(1,7);
            totalPoint= Position.makePosition(1,6.5f);
            physicsPoint.set(1,7.5f);
        }else if(w==8f && h==4.8f){
            fpsPoint= Position.makePosition(5,4.5f);
            totalPoint= Position.makePosition(5,4);
            physicsPoint.set(5,3.5f);
        }

     /*   float width=viewPort.getWorldWidth();
        float height=viewPort.getWorldHeight();

        Entity fpsEntity = EntityFactory.createTextEntity(world, Size.makeSize(1, 1), Position.makePosition(.5f, height-1f), false);
        fpsText = fpsEntity.getComponent(TextComponent.class);

        Entity totalEntity=EntityFactory.createTextEntity(world, Size.makeSize(1, 1), Position.makePosition(.5f,height-1.5f), false);
        totalEntityText=totalEntity.getComponent(TextComponent.class);*/

        //totalEntityText= GenericEntityBuilder.createText(world, 3, Assets.bitmapFontAsset.bitmapFont,"", Position.makePosition(totalPoint.x,totalPoint.y),0).getComponent(VisText.class);
       // fpsText=GenericEntityBuilder.createText(world,3, Assets.bitmapFontAsset.bitmapFont,"", Position.makePosition(fpsPoint.x,fpsPoint.y),0).getComponent(VisText.class);
      //  physicsBodyText=GenericEntityBuilder.createText(world,3, Assets.bitmapFontAsset.bitmapFont,"", Position.makePosition(physicsPoint.x,physicsPoint.y),0).getComponent(VisText.class);
        //setInvisible(totalEntityText);
        //setInvisible(fpsText);
    }

    private void setInvisible(VisText text){
        //Color color=text.getColor();
        //text.setColor(color.r,color.g,color.b,0);
    }

    public static void initTime(){
        startTime = TimeUtils.nanoTime();
    }

    @Override
    protected void initialize() {

      /*  Entity leader=world.createEntity();
        leader.edit().add(new LayerComponent(EntityFactory.Layer.UI_LAYER.value));
        leader.edit().add(new RenderableComponent(0));

        BitmapFont bitmapFont= SceneManager.manager.get("bmpfont/font.fnt",BitmapFont.class);

        TextComponent textComponent=new TextComponent(bitmapFont, "sdsdsd");
        textComponent.setScale(1, 1);

        //if(isCentric)
        textComponent.setPosition(1, 2);
     */   //leader.edit().add(textComponent);
        //else
          //  textComponent.setPosition(position.x, position.y);

        entitySubscription=aspectSubscriptionManager.get(Aspect.all(VisSprite.class));
    }

    @Override
    protected void processSystem() {
       log(fpsText);
        entitySubscription=aspectSubscriptionManager.get(Aspect.all(Transform.class));

        totalEntityText.setText("ToE"+entitySubscription.getEntities().size());
        physicsBodyText.setText("Ph Bo"+aspectSubscriptionManager.get(Aspect.all(PhysicsBody.class)).getEntities().size());

    }

    public static void log(VisText fpsComponent) {

        if (TimeUtils.nanoTime() - startTime > 1000000000) /* 1,000,000,000ns == one second */{
            // Gdx.app.log("FPSLogger", "fps: " + Gdx.graphics.getFramesPerSecond());

            fpsComponent.setText("FPS : "+String.valueOf(Gdx.graphics.getFramesPerSecond()));

            startTime = TimeUtils.nanoTime();
        }
    }

    @Override
    protected boolean checkProcessing() {
        return !_GameManager.isPaused();
    }
}
