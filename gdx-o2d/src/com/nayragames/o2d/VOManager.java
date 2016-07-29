package com.nayragames.o2d;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.uwsoft.editor.renderer.data.*;
import com.uwsoft.editor.renderer.resources.IResourceRetriever;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 25-01-2016.
 */
public class VOManager {

    public static CompositeItemVO getCompositeActorFromLib(IResourceRetriever ir, String name){

        ProjectInfoVO projectInfo = ir.getProjectVO();
        CompositeItemVO menuButtonData = projectInfo.libraryItems.get(name);
        //CompositeActor buttonActor = new CompositeActor(menuButtonData, ir);

        return menuButtonData;
    }

   /* public static ColorPrimitiveVO getColorPrimitiveVO(float x, float y, float width,float  height){

        ColorPrimitiveVO colorPrimitiveVO=new ColorPrimitiveVO();
        colorPrimitiveVO.x=x;
        colorPrimitiveVO.y=y;
        colorPrimitiveVO.layerName="game";
        //colorPrimitiveVO.shape=ShapeVO.createRect(width,height);

        colorPrimitiveVO.shape= VOManager.getShapeVO(new float[]{0,0,0,height,width,height,width,0});


        return colorPrimitiveVO;
    }
*/
    public static ShapeVO getShapeVO(float vertices[]){

        ShapeVO vo=new ShapeVO();
        vo.polygons = new Vector2[1][1];
        vo.polygons[0] = new Vector2[vertices.length/2];
        vo.polygons[0][0] = new Vector2(vertices[0], vertices[1]);
        vo.polygons[0][1] = new Vector2(vertices[2], vertices[3]);
        vo.polygons[0][2] = new Vector2(vertices[4], vertices[5]);
        vo.polygons[0][3] = new Vector2(vertices[6], vertices[7]);

        return vo;
    }

    public static SimpleImageVO getSimpleImageVO(String imageName, float x, float y, float rotation, ShapeVO shapeVO){

        SimpleImageVO simpleImageVO=new SimpleImageVO();
        simpleImageVO.imageName=imageName;
        simpleImageVO.x=x;
        simpleImageVO.y=y;
        simpleImageVO.rotation=rotation;
        simpleImageVO.isPolygon=true;
        simpleImageVO.physics=getPhysicsVO(BodyDef.BodyType.DynamicBody,new Vector2(0,0),1);
        simpleImageVO.shape=shapeVO;
        simpleImageVO.isRepeat=false;
        simpleImageVO.layerName="game";

        return simpleImageVO;
    }

    public static SimpleImageVO getSimpleImageVO(String imageName, float x, float y, float rotation, String layerName){

        SimpleImageVO simpleImageVO=new SimpleImageVO();
        simpleImageVO.imageName=imageName;
        simpleImageVO.x=x;
        simpleImageVO.y=y;
        simpleImageVO.rotation=rotation;
        simpleImageVO.layerName=layerName;

        return simpleImageVO;
    }

    public static ParticleEffectVO getParticleVO(String particleName, float x, float y, float rotation){

        ParticleEffectVO particleEffectVO=new ParticleEffectVO();
        particleEffectVO.particleName=particleName;
        particleEffectVO.x=x;
        particleEffectVO.y=y;
        particleEffectVO.particleHeight=400;
        particleEffectVO.particleWidth=400;
        particleEffectVO.rotation=rotation;

        return particleEffectVO;
    }


    public static LightVO getLightVO(LightVO.LightType lightType, float x, float y, int rays, float distance, float directionDegree, float softnessLength){

        LightVO lightVO=new LightVO();
        lightVO.type=lightType;
        lightVO.x=x;
        lightVO.y=y;
        lightVO.directionDegree=directionDegree;
        lightVO.distance=distance;
        lightVO.softnessLength=softnessLength;
        lightVO.rays=rays;

        return lightVO;
    }

    public static SpriteAnimationVO getSpriteAnimationVO(String animationName, float x, float y, int fps, Animation.PlayMode playMode){

        SpriteAnimationVO spriteAnimationVO=new SpriteAnimationVO();
        spriteAnimationVO.animationName=animationName;
        spriteAnimationVO.x=x;
        spriteAnimationVO.y=y;
        spriteAnimationVO.fps=fps;

        if(playMode== Animation.PlayMode.NORMAL) spriteAnimationVO.playMode=0;
        if(playMode== Animation.PlayMode.REVERSED) spriteAnimationVO.playMode=1;
        if(playMode== Animation.PlayMode.LOOP) spriteAnimationVO.playMode=2;
        if(playMode== Animation.PlayMode.LOOP_REVERSED) spriteAnimationVO.playMode=3;
        if(playMode== Animation.PlayMode.LOOP_PINGPONG) spriteAnimationVO.playMode=4;
        if(playMode== Animation.PlayMode.LOOP_RANDOM) spriteAnimationVO.playMode=5;

        return spriteAnimationVO;
    }

    public static LabelVO getLabelVO(String text, String style, float x, float y, int size){

        LabelVO labelVO=new LabelVO();
        labelVO.x=x;
        labelVO.y=y;
        labelVO.text=text;
        labelVO.style=style;
        labelVO.size=size;
        labelVO.layerName="UI";

        return labelVO;
    }

    public static PhysicsBodyDataVO getPhysicsVO(BodyDef.BodyType bodyType, Vector2 center, float density){

        PhysicsBodyDataVO physicsBodyDataVO=new PhysicsBodyDataVO();
        physicsBodyDataVO.allowSleep=false;
        if(bodyType == BodyDef.BodyType.StaticBody)
        physicsBodyDataVO.bodyType=0;
        else if(bodyType== BodyDef.BodyType.KinematicBody)
            physicsBodyDataVO.bodyType=1;
        else
            physicsBodyDataVO.bodyType=2;

        physicsBodyDataVO.centerOfMass=center;
        physicsBodyDataVO.density=density;
        physicsBodyDataVO.restitution=.2f;
        physicsBodyDataVO.awake=true;


        return physicsBodyDataVO;
    }

    public static SimpleImageVO getPhysicsImageVO(String imageName, float x, float y, float rotation, BodyDef.BodyType bodyType, float density, String layerName){

        SimpleImageVO simpleImageVO=getSimpleImageVO(imageName,x,y,rotation,layerName);
        simpleImageVO.physics=getPhysicsVO(bodyType,new Vector2(0,0),density);
        //simpleImageVO.shape=getShapeVO(new Vector2[][]{{new Vector2(0,0)},{new Vector2(0,1)},{new Vector2(1,1)}});

        return simpleImageVO;
    }

    public static SpriteAnimationVO getPhysicsAnimationVO(String animationName, float x, float y, int fps, int playMode, BodyDef.BodyType bodyType, float density){

        SpriteAnimationVO spriteAnimationVO=new SpriteAnimationVO();
        spriteAnimationVO.animationName=animationName;
        spriteAnimationVO.x=x;
        spriteAnimationVO.y=y;
        spriteAnimationVO.fps=fps;
        spriteAnimationVO.playMode=playMode;
        spriteAnimationVO.physics=getPhysicsVO(bodyType,new Vector2(0,0),density);

        return spriteAnimationVO;
    }

    public static ShapeVO getShapeVO(Vector2[][] points){

        ShapeVO shapeVO=new ShapeVO();
        shapeVO.polygons=points;

        return shapeVO;
    }

}
