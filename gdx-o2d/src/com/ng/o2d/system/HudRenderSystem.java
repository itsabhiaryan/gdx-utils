package com.ng.o2d.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ng.gdxutils._GameManager;
import com.ng.gdxutils.model.LogManager;
import com.ng.gdxutils.model.Position;
import com.ng.o2d.GenericEntityBuilder;
import com.ng.o2d.ResourceLoader;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.label.LabelComponent;
import com.uwsoft.editor.renderer.components.physics.PhysicsBodyComponent;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/8/2015.
 *
 * System is used only for debugging purpose used for getting Total Entity count and what is FPS.
 */

public class HudRenderSystem extends EntitySystem {

    LabelComponent fpsText,totalEntityText,physicsBodyText;
    Camera camera;
    SceneLoader sceneLoader;

    public HudRenderSystem(SceneLoader sceneLoader, Viewport viewport){
        this.sceneLoader=sceneLoader;
        camera=viewport.getCamera();
        LogManager.initTime();

        float w=viewport.getScreenWidth();
        float h=viewport.getScreenHeight();

        Position fpsPoint= Position.makePosition(w/2,h/2);
        Position totalPoint= Position.makePosition(w/2,h/2);
        Position physicsPoint= Position.makePosition(w/2,w/2);

        if(w==4.8f && h==8) {
            fpsPoint= Position.makePosition(1,7);
            totalPoint= Position.makePosition(1,6.5f);
            physicsPoint.set(1,7.5f);
        }else if(w==8f && h==4.8f){
            fpsPoint= Position.makePosition(5,4.5f);
            totalPoint= Position.makePosition(5,4);
            physicsPoint.set(5,3.5f);
        }

        ItemWrapper wrapper=new ItemWrapper(sceneLoader.getRoot());
        //fpsText=wrapper.getChild("fps").getEntity().getComponent(LabelComponent.class);
        //physicsBodyText=wrapper.getChild("physicstotalentity").getEntity().getComponent(LabelComponent.class);
        //totalEntityText=wrapper.getChild("totalentity").getEntity().getComponent(LabelComponent.class);

        fpsText= ComponentRetriever.get(GenericEntityBuilder.createLabel(sceneLoader,sceneLoader.getRoot(),650,200,"FPS", ResourceLoader.fontSizePair_2),LabelComponent.class);
        physicsBodyText= ComponentRetriever.get(GenericEntityBuilder.createLabel(sceneLoader,sceneLoader.getRoot(),650,150,"physicstotalentity", ResourceLoader.fontSizePair_2),LabelComponent.class);
        totalEntityText= ComponentRetriever.get(GenericEntityBuilder.createLabel(sceneLoader,sceneLoader.getRoot(),650,100,"totalentity", ResourceLoader.fontSizePair_2),LabelComponent.class);

        fpsText.getStyle().fontColor= com.badlogic.gdx.graphics.Color.BLACK;
        physicsBodyText.getStyle().fontColor= com.badlogic.gdx.graphics.Color.BLACK;
        totalEntityText.getStyle().fontColor= com.badlogic.gdx.graphics.Color.BLACK;

     /*   float width=viewPort.getWorldWidth();
        float height=viewPort.getWorldHeight();

        Entity fpsEntity = EntityFactory.createTextEntity(world, Size.makeSize(1, 1), Position.makePosition(.5f, height-1f), false);
        fpsText = fpsEntity.getComponent(TextComponent.class);

        Entity totalEntity=EntityFactory.createTextEntity(world, Size.makeSize(1, 1), Position.makePosition(.5f,height-1.5f), false);
        totalEntityText=totalEntity.getComponent(TextComponent.class);*/

//        totalEntityText= GenericEntityBuilder.createText(sceneLoader, 3, Assets.bitmapFontAsset.bitmapFont,"", Position.makePosition(totalPoint.x,totalPoint.y),0).getComponent(VisText.class);
//        fpsText= GenericEntityBuilder.createText(sceneLoader,3, Assets.bitmapFontAsset.bitmapFont,"", Position.makePosition(fpsPoint.x,fpsPoint.y),0).getComponent(VisText.class);
//        physicsBodyText= GenericEntityBuilder.createText(sceneLoader,3, Assets.bitmapFontAsset.bitmapFont,"", Position.makePosition(physicsPoint.x,physicsPoint.y),0).getComponent(VisText.class);
        //setInvisible(totalEntityText);
        //setInvisible(fpsText);

    }

    private void setInvisible(LabelComponent text){
        //Color color=text.getColor();
        //text.setColor(color.r,color.g,color.b,0);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        //LogManager.log(fpsText);

        totalEntityText.setText("Total Entity : "+sceneLoader.getEngine().getEntities().size());
        physicsBodyText.setText("Physics Body : "+sceneLoader.getEngine().getEntitiesFor(Family.all(PhysicsBodyComponent.class).get()).size());

    }

    @Override
    public boolean checkProcessing() {
        return !_GameManager.isPaused();
    }
}
