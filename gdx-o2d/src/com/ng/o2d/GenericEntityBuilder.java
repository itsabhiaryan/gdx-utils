package com.ng.o2d;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.ng.gdxutils.b2d.GenericPhysicsHelper;
import com.ng.gdxutils.model.Position;
import com.ng.gdxutils.model.Scale;
import com.ng.gdxutils.model.Size;
import com.ng.o2d.component.ShapeComponent;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.*;
import com.uwsoft.editor.renderer.components.physics.PhysicsBodyComponent;
import com.uwsoft.editor.renderer.data.CompositeItemVO;
import com.uwsoft.editor.renderer.data.LightVO;
import com.uwsoft.editor.renderer.data.MainItemVO;
import com.uwsoft.editor.renderer.physics.PhysicsBodyLoader;
import com.uwsoft.editor.renderer.resources.FontSizePair;
import com.uwsoft.editor.renderer.resources.IResourceRetriever;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/12/2015.
 *
 * Collection of generic method for entity creation having different types of components.
 *
 */

public class GenericEntityBuilder {

    private static final boolean IS_CENTRIC=true;
    private static final String TAG = "[" + GenericEntityBuilder.class.getSimpleName() + "]";


/*public static Entity createLeftRightEntity(World world, float width, float height){

        Entity entity=createEntity(world, Assets.imageAsset.alien3s,Size.makeSize(width*.1f,height*.1f),Position.makePosition(width/2f, height*.9f),true, Enums.Player.NPC,180);
        entity.edit().add(new LeftRightComponent());
        entity.edit().add(EntityManager.createFSMC(entity));
        entity.edit().add(new BulletSpawnComponent());

        return entity;
    }

    public static Entity createEntity(World world, TextureRegion texture, Size size, Position position, boolean isCentric, Enums.Player playerTag, Enums.Group groupTag, float angle){

        Entity entity=createEntity(world, texture, size, position, isCentric,playerTag,angle);
        world.getSystem(GroupManager.class).add(entity, groupTag.value);
        return entity;
    }

    public static Entity createEntity(World world, TextureRegion texture, Size size, Position position, boolean isCentric, Enums.Player playerTag, float angle){

        Entity entity=createEntity(world, texture, size, position, isCentric,angle);
        world.getSystem(PlayerManager.class).setPlayer(entity, playerTag.value);
        return entity;
    }

    public static Entity createEntity(World world,TextureRegion texture,Size size,Position position,boolean isCentric,float angle){

        Entity entity=createEntity(world, Enums.Layer.NP_LAYER.value,0,texture,size,position,isCentric,angle);

        return entity;
    }*//*


    */
/*public static Entity createEntity(World world,int layer,int renderComponent,TextureRegion texture,Size size,Position position,boolean isCentric,float angle){

        Entity entity =world.createEntity();
        entity.edit().add(new Layer(layer));
        entity.edit().add(new Renderable(renderComponent));
        entity.edit().add(addTextureRegion(texture,size));
        //,position,isCentric,angle

        return entity;
    }*//*


    public static void createTweenEntity(World world, int count, _Main game){

        */
/*Entity e=createEntity(world,Assets.imageAsset.alien3s,Size.makeSize(1,1),Position.makePosition(3,3),true, Player.PLAYER,0);
        VisSprite spriteComponent=e.getComponent(VisSprite.class);

        Tween.to(spriteComponent, TransformAccessor.POS_XY,1).target(count,1).ease(TweenEquations.easeOutBack).start(game.tweenManager);
        Tween.to(spriteComponent, TransformAccessor.TINT,1).target(1,0,1).repeatYoyo(Tween.INFINITY,0).start(game.tweenManager);
*//*

        count++;
    }

    */
/*public static Entity createAnimation(World world, TextureRegion[] regions, Size size, Position origin){

        Entity entity=world.createEntity();
        entity.edit().add(new Layer(0));
        entity.edit().add(new Renderable(1));

        AnimationComponent animation=new AnimationComponent(regions,.1f, Animation.PlayMode.LOOP);
        BasicComponent basicComponent=new BasicComponent();

        basicComponent.setScaleX(4);
        basicComponent.setScaleY(4);
        basicComponent.setRotation(90);
        entity.edit().add(basicComponent);

        return entity;
    }*//*


   // GenericEntityBuilder.createAnimation(world,Assets.animationAsset.blast1, Size.makeSize(1,1), Position.makePosition(1,1));

    public static Entity createAnimation(World world, TextureRegion[] regions, Enums.Layer layer, Size size, Position origin, float angle){

        Entity entity=world.createEntity();
        entity.edit().add(new Layer(layer.value));
        entity.edit().add(new Renderable(1));

        entity.edit().add(new AnimationComponent(regions,.1f, Animation.PlayMode.LOOP));
        EntityManager.addTransform(entity,origin,Scale.makeUnScale(),angle);

        return entity;
    }

    */
/*public static Entity createLeaderEntity(World world, float width, float height){

        Entity entity =world.createEntity();
        entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
        entity.edit().add(new Renderable(0));
        //entity.edit().add(addTextureRegion(Assets.imageAsset.alien3s,Size.makeSize(width*.075f,height*.075f),Position.makePosition(width/2f, height*.9f),true,180));
        entity.edit().add(createAC(Assets.animationAsset.dangerFlipY,.1f, Animation.PlayMode.LOOP_PINGPONG));
        entity.edit().add(new BasicComponent(Size.makeSize(width*.075f,height*.075f),Position.makePosition(width/2f, height*.9f),180));



        world.getSystem(PlayerManager.class).setPlayer(entity,  Player.NPC.value);
        world.getSystem(GroupManager.class).add(entity, Enums.Group.ENEMY_SHIP.value);

        entity.edit().add(EntityManager.createSteerC(entity));
        entity.edit().add(new LeaderComponent(entity.getComponent(SteerableComponent.class), LeaderComponent.FormationType.DEFENSIVE_CIRCLE,.25f));
        entity.edit().add(new LeftRightComponent(1,.01f));
        entity.edit().add(new CollisionComponent());

        entity.edit().add(new BulletSpawnComponent(false, BulletSpawnComponent.BulletSpawnType.CIRCULAR));

        //entity.edit().add(EntityManager.createFSMC(entity));
        //entity.getComponent(FSMComponent.class).getStateMachine().changeState(EntityState.MOVE_DOWN);

        HealthComponent health=new HealthComponent();
        entity.edit().add(health);

        float percent=(health.health/health.maximumHealth)*100;
        VisText textComponent=new VisText(Assets.bitmapFontAsset.bitmapFont,String.valueOf((int)percent));
        //textComponent.setScale(.5f, .5f);
        entity.edit().add(textComponent);

        return entity;
    }*//*



    public static Entity createFormationMemberEntity(World world, TextureRegion[] textureRegions, float width, float height, Entity leader){

        Entity entity =world.createEntity();
        entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
        entity.edit().add(new Renderable(0));
        //entity.edit().add(addTextureRegion(Assets.imageAsset.circularBullet,Size.makeSize(width*.075f,height*.075f),Position.makePosition(1, 1),true,0));
        entity.edit().add(new AnimationComponent(textureRegions,.1f, Animation.PlayMode.LOOP));

        EntityManager.addTransform(entity,Position.makePosition(MathUtils.random(0,4.8f), 4),Scale.makeUnScale(),0);
        world.getSystem(PlayerManager.class).setPlayer(entity, Enums.Player.NPC.value);
        world.getSystem(GroupManager.class).add(entity, Enums.Group.ENEMY_SHIP.value);

        entity.edit().add(EntityManager.createSteerC(entity,true));
        //entity.edit().add(new BulletSpawnComponent(false, BulletSpawnComponent.BulletSpawnType.SINGLE));

        EntityManager.createFormationMemberComponent(entity,leader);
        entity.edit().add(EntityManager.createFSMC(entity));
        entity.edit().add(new CollisionComponent());

        HealthComponent health=new HealthComponent(5);
        entity.edit().add(health);

        float percent=(health.health/health.maximumHealth)*100;
        VisText textComponent=new VisText(Assets.bitmapFontAsset.bitmapFont,String.valueOf((int)percent));
        //textComponent.setScale(.5f, .5f);

        entity.edit().add(textComponent);

        return entity;
    }




    */
/*public static void createAnimationEntity(World world){

		*//*
*/
/*Entity leader =world.createEntity();
		leader.edit().add(new LayerComponent(Layer.GAME_LAYER.value));
		leader.edit().add(new RenderableComponent(0));
		leader.edit().add(addTextureRegion(Resource.PLAYER_BULLET,Size.makeSize(size,size),point,true,0));*//*
*/
/*
    }*//*


    public static Entity createPhysicsBoundary(World world, Size size, Position position, boolean isCentric, float angle){

        Entity entity=world.createEntity();
        entity.edit().add(new Renderable(0));
        entity.edit().add(new Layer(0));

        com.badlogic.gdx.physics.box2d.World physicsWorld=world.getSystem(PhysicsSystem.class).getPhysicsWorld();

        BodyDef bodyDef=new BodyDef();
        bodyDef.position.set(position.x, position.y);
        bodyDef.type= BodyDef.BodyType.StaticBody;

		*/
/*PolygonShape polygonShape=new PolygonShape();
		polygonShape.setAsBox(sc.getWidth(), sc.getHeight());*//*


        ChainShape chainShape=new ChainShape();
        chainShape.createChain(new Vector2[]{new Vector2(-size.x/2, -size.y/2),new Vector2(size.x/2, -size.y/2),new Vector2(size.x/2, size.y/2)});

        FixtureDef fixture=new FixtureDef();
        fixture.shape=chainShape;
        fixture.restitution=0;

        Body body=physicsWorld.createBody(bodyDef);
        body.setUserData(entity);

        body.createFixture(fixture);

        PhysicsBody physicsBody=new PhysicsBody(body);
        entity.edit().add(physicsBody);

        EntityManager.addOrigin(entity,size.x/2,size.y/2);

        return entity;
    }

    public static VisMusic createMusicEntity(World world){
        Entity entity=world.createEntity();
        VisMusic musicComponent=new VisMusic(Assets.musicAsset.music);
        musicComponent.setLooping(true);
        //musicComponent.play();
        entity.edit().add(musicComponent);
        return musicComponent;
    }



    public static Entity createText(World world, int layerId , BitmapFont font, String text, Position point, float angle){

         */
        /* VisText,Transform,Origin,Tint,VisID,Layer,Renderable*//*

        Entity entity=world.createEntity();
        VisText visText=EntityManager.addVisText(entity,font,text);
        EntityManager.addTransform(entity,point, Scale.makeUnScale(),angle);
        EntityManager.addOrigin(entity,visText.getWidth()/2,visText.getHeight()/2);
        EntityManager.addTint(entity);
        //EntityManager.addVisID(entity,"");
        EntityManager.addLayer(entity,layerId);
        EntityManager.addRenderer(entity,0);

        return entity;
    }

    public static Entity createPhysicsSprite(World world, int layerId, TextureRegion textureRegion, Size size, Position point, float angle, BodyDef.BodyType type, float density){

        //PhysicsProperties,VisPolygon,VisSprite,PhysicsBody,PhysicsSprite,Transform,Origin,Tint,VisID,Layer,Renderer

        Entity entity=world.createEntity();
        EntityManager.addTextureRegion(entity,textureRegion,size);
        EntityManager.addTransform(entity,point, Scale.makeUnScale(),angle);
        EntityManager.addOrigin(entity,0,0);
        EntityManager.addTint(entity);
        //EntityManager.addVisID(entity,"");
        EntityManager.addLayer(entity,layerId);
        EntityManager.addRenderer(entity,0);
        //entity.edit().add(new PhysicsOrigin());

        EntityManager.addPhysicsBody(entity,type,density,point.x,point.y,size.x,size.y,true,angle);
        entity.edit().add(new PhysicsSprite());

        return entity;
    }

    public static Entity createPhysicsSprite(World world, int layerId, TextureRegion textureRegion, Size size, Position point, float angle, BodyDef.BodyType type, float density, String name, BodyEditorLoader loader, float scaleFactor){

        //PhysicsProperties,VisPolygon,VisSprite,PhysicsBody,PhysicsSprite,Transform,Origin,Tint,VisID,Layer,Renderer

        Entity entity=world.createEntity();
        EntityManager.addTextureRegion(entity,textureRegion,size);
        EntityManager.addTransform(entity,point, Scale.makeScale(scaleFactor,scaleFactor),angle);
        EntityManager.addOrigin(entity,size.x/2,size.y/2);
        EntityManager.addTint(entity);
        //EntityManager.addVisID(entity,"");
        EntityManager.addLayer(entity,layerId);
        EntityManager.addRenderer(entity,0);

        EntityManager.addPhysicsBody(entity,type,density,point.x,point.y,size.x,size.y,true,angle,name,loader,scaleFactor);
        Variables variables=EntityManager.addVariable(entity);
        variables.putInt("phy",0);
        //entity.edit().add(new PhysicsSprite());

        return entity;
    }

    public static Entity createAnimationEntity(World world, int layerId, TextureRegion[] textureRegion, Size size, Position point, float angle){

        Entity entity=world.createEntity();
        EntityManager.addAnimation(entity,textureRegion,size);
        EntityManager.addTransform(entity,point, Scale.makeUnScale(),angle);
        EntityManager.addOrigin(entity,0,0);
        EntityManager.addTint(entity);
        //EntityManager.addVisID(entity,"");
        EntityManager.addLayer(entity,layerId);
        EntityManager.addRenderer(entity,0);
        //entity.edit().add(new PhysicsOrigin());

        return entity;
    }
*/
    public static Entity createPhysicsAnimation(int layerId, TextureRegion[] textureRegion, Size size, Position point, float angle, BodyDef.BodyType type, float density){

        Entity entity=new Entity();
        EntityManager.addAnimation(entity,textureRegion,size);
        EntityManager.addTransform(entity,point, Scale.makeUnScale(),angle);
        //EntityManager.addOrigin(entity,0,0);
        EntityManager.addTint(entity);
        //EntityManager.addVisID(entity,"");
        //EntityManager.addLayer(entity,layerId);
        EntityManager.addRenderer(entity,0);
        //new PhysicsOrigin());
        //EntityManager.addPhysicsBody(entity,type,density,point.x,point.y,size.x,size.y,true,angle);
        //Variables variables=EntityManager.addVariable(entity);
        //variables.putInt("phy",0);

        return entity;
    }

    public static Entity createShape(SceneLoader sceneLoader, int layerId, Color color, Size size, Position point , float angle){

        Entity entity=new Entity();
        EntityManager.addShape(entity,size);

        if(point.isCentric) {
            EntityManager.addTransform(entity, point.x - size.x / 2f, point.y - size.y / 2f, Scale.makeUnScale(), angle);
        }
        else {
            EntityManager.addTransform(entity, point, Scale.makeUnScale(), angle);
        }

        //EntityManager.addOrigin(entity,size.x/2f,size.y/2f);
        EntityManager.addTint(entity,color);
        //EntityManager.addVisID(entity,""));
        //EntityManager.addLayer(entity,layerId);
        EntityManager.addRenderer(entity,0);

        return entity;
    }

    public static Entity createShape(SceneLoader sceneLoader, int layerId, Color color, float radius, Position point, float angle){

        Entity entity=new Entity();
        EntityManager.addShape(entity,radius);

        if(point.isCentric)
        EntityManager.addTransform(entity,point.x-radius/2,point.y-radius/2, Scale.makeUnScale(),angle);
        else
        EntityManager.addTransform(entity,point.x,point.y, Scale.makeUnScale(),angle);
        EntityManager.addTint(entity,color);
        //EntityManager.addVisID(entity,""));
        //EntityManager.addLayer(entity,layerId);
        EntityManager.addRenderer(entity,0);

        return entity;
    }

    public static Entity createPhysicsShape(SceneLoader sceneLoader, int layerId, Color color, Size size, Position point, float angle, BodyDef.BodyType type, float density){

        Entity entity=new Entity();
        EntityManager.addShape(entity,size);
        EntityManager.addTransform(entity,point, Scale.makeUnScale(),angle);
        EntityManager.addTint(entity,color);
        //EntityManager.addVisID(entity,"");
        //EntityManager.addLayer(entity,layerId);
        EntityManager.addRenderer(entity,0);
        //new PhysicsOrigin());
        //EntityManager.addPhysicsBody(entity,type,density,point.x,point.y,size.x,size.y,false,angle);

        //Variables variables=EntityManager.addVariable(entity);
        //variables.putInt("phy",0);

        return entity;
    }

    public static class BodyProperties {

        BodyDef.BodyType type;
        float density;
        float friction;

       BodyProperties (BodyDef.BodyType type, float density, float friction){
        this.type=type;
        this.density=density;
        this.friction=friction;

       }
    }

    public static BodyProperties getBodyProperties(BodyDef.BodyType bodyType, float density, float friction){

        return new BodyProperties(bodyType,density,friction);
    }

    public static void addPointEntity(){

        //Transform,VisID,Point

        Entity entity =new Entity();
        EntityManager.addTransform(entity, Position.makePosition(1,1), Scale.makeUnScale(),0);
        EntityManager.addVisID(entity,"");
        //EntityManager.addPoint();
    }

    public static void addRoot(){

        /*DimensionsComponent, MainItemComponent, LayerMapComponent, TintComponent, ZIndexComponent,
        ScriptComponent, NodeComponent, CompositeTransformComponent, TransformComponent, ViewPortComponent*/


    }

    public static Entity createCompositeEntity(SceneLoader sceneLoader, Entity parent, String name, float x, float y, float angle, float scale, boolean isCentric){

     /*   DimensionsComponent, MainItemComponent, TransformComponent, TintComponent, ZIndexComponent,
        ScriptComponent, ParentNodeComponent, NodeComponent, CompositeTransformComponent, LayerMapComponent*/

        IResourceRetriever ir=sceneLoader.getRm();
        CompositeItemVO compositeItemVO=VOManager.getCompositeActorFromLib(ir,name);
        compositeItemVO.x=x;
        compositeItemVO.y=y;
        compositeItemVO.rotation=angle;
        compositeItemVO.scaleX=scale;
        compositeItemVO.scaleY=scale;

         if(isCentric){
             compositeItemVO.x-=compositeItemVO.width/2f*scale;
             compositeItemVO.y-=compositeItemVO.height/2f*scale;
         }

        Entity newEntity=sceneLoader.getEntityFactory().createEntity(parent,compositeItemVO);
        sceneLoader.getEntityFactory().initAllChildren(sceneLoader.getEngine(),newEntity,compositeItemVO.composite);
        sceneLoader.engine.addEntity(newEntity);

        return newEntity;
    }

    public static Entity createSpriteByShape(SceneLoader sceneLoader, Entity parent, String  textureRegion, float x, float y, float width, float height, float angle, boolean isCentric){

        sceneLoader.getRm().getTextureRegion("pix").setRegionHeight((int)height*2);
        sceneLoader.getRm().getTextureRegion("pix").setRegionWidth((int)width*2);

        Entity entity= sceneLoader.entityFactory.createEntity(parent, VOManager.getSimpleImageVO(textureRegion,x,y,angle, VOManager.getShapeVO(new float[]{0,0,0,height/2,width,height,width,0})));

        TransformComponent transformComponent=Mappers.tCM.get(entity);
        transformComponent.originY=0;
        transformComponent.originX=0;
        sceneLoader.getEngine().addEntity(entity);

        ZIndexComponent zIndexComponent=ComponentRetriever.get(entity,ZIndexComponent.class);
        System.out.println(zIndexComponent.getZIndex()+"ZINDEX");
        zIndexComponent.layerIndex=1;
        zIndexComponent.needReOrder=true;

        zIndexComponent.setZIndex(0);

        TintComponent tintComponent=ComponentRetriever.get(entity,TintComponent.class);
        tintComponent.color= Color.WHITE;

        /*TextureRegionComponent textureRegionComponent=ComponentRetriever.get(entity,TextureRegionComponent.class);

        TextureRegion textureRegion1=new TextureRegion(TextureManager.getPixmapTexture(Color.RED));
        textureRegion1.setRegionWidth((int)width);
        textureRegion1.setRegionHeight((int)height);
        textureRegionComponent.region=textureRegion1 ;*/

        return entity;
    }

    public static Entity createSprite(SceneLoader sceneLoader, Entity parent, TextureRegion textureRegion, float x, float y, float width, float height, float angle, boolean isCentric){

        Entity entity=new Entity();
        EntityManager.addDimension(entity,width,height);
        EntityManager.addMainItem(entity);
        EntityManager.addTransform(entity,x,y,Scale.makeUnScale(),angle);
        EntityManager.addTint(entity);
        EntityManager.addZIndex(entity,0,0);
        EntityManager.addScript(entity);
        EntityManager.addTextureRegion(entity,textureRegion);
        EntityManager.addParentNode(entity,parent);
        EntityManager.addTONode(entity,parent);

        sceneLoader.entityFactory.postProcessEntity(entity);
        sceneLoader.getEngine().addEntity(entity);
        return entity;
    }


    public static Entity createSprite(SceneLoader sceneLoader, Entity parent, String  textureRegion, float x, float y, float width, float height, float angle, boolean isCentric, String layerName){

        //DimensionsComponent, MainItemComponent, TransformComponent, TintComponent, ZIndexComponent,
        //ScriptComponent, TextureRegionComponent, ParentNodeComponent

        //new float[]{0,0,0,scale*height/2,width*scale,height*scale,width*scale,0})

        float scale=1;
        //float width1=sceneLoader.getRm().getTextureRegion("brick").getRegionWidth();
        //float height1=sceneLoader.getRm().getTextureRegion("brick").getRegionHeight();

        Entity entity= sceneLoader.entityFactory.createEntity(parent, VOManager.getSimpleImageVO(textureRegion,x,y,angle,layerName));

        PolygonComponent polygonComponent=new PolygonComponent();
        polygonComponent.makeRectangle(width,height);

        TransformComponent transformComponent=Mappers.tCM.get(entity);

        DimensionsComponent dimensionsComponent=Mappers.dCM.get(entity);
        dimensionsComponent.width=width;
        dimensionsComponent.height=height;

        if(isCentric) {
            transformComponent.x = x - width / 2;
            transformComponent.y = y - height / 2;
        }

        transformComponent.originY=width/2;
        transformComponent.originX=width/2;



        sceneLoader.getEngine().addEntity(entity);

        return entity;
    }

    public static Entity createSprite(SceneLoader sceneLoader, Entity parent, String  textureRegion, float x, float y, float width, float height, float angle, boolean isCentric){

        //DimensionsComponent, MainItemComponent, TransformComponent, TintComponent, ZIndexComponent,
        //ScriptComponent, TextureRegionComponent, ParentNodeComponent

        //new float[]{0,0,0,scale*height/2,width*scale,height*scale,width*scale,0})

        float scale=1;
        //float width1=sceneLoader.getRm().getTextureRegion("brick").getRegionWidth();
        //float height1=sceneLoader.getRm().getTextureRegion("brick").getRegionHeight();

        Entity entity= sceneLoader.entityFactory.createEntity(parent, VOManager.getSimpleImageVO(textureRegion,x,y,angle,""));

        PolygonComponent polygonComponent=new PolygonComponent();
        polygonComponent.makeRectangle(width,height);

        TransformComponent transformComponent=Mappers.tCM.get(entity);

        DimensionsComponent dimensionsComponent=Mappers.dCM.get(entity);
        dimensionsComponent.width=width;
        dimensionsComponent.height=height;

        if(isCentric) {
            transformComponent.x = x - width / 2;
            transformComponent.y = y - height / 2;
        }

        transformComponent.originY=width/2;
        transformComponent.originX=width/2;



        sceneLoader.getEngine().addEntity(entity);

        return entity;
    }

    public static Entity createPSprite(SceneLoader sceneLoader, Entity parent, String  textureRegion, float x, float y, float width, float height, boolean isCentric , BodyDef.BodyType bodyType, float density, Enums.Shape shape, String layerName){

        //TextureRegionComponent, DimensionsComponent, MainItemComponent, TransformComponent, TintComponent,
        //ZIndexComponent, ScriptComponent, PolygonComponent, PhysicsBodyComponent, ParentNodeComponent

        Entity entity=sceneLoader.entityFactory.createEntity(parent, VOManager.getPhysicsImageVO(textureRegion,x,y,0,bodyType,1,layerName));
        TransformComponent transformComponent=Mappers.tCM.get(entity);
        transformComponent.x=x;
        transformComponent.y=y;


        DimensionsComponent dimensionsComponent=Mappers.dCM.get(entity);
        dimensionsComponent.width=width;
        dimensionsComponent.height=height;

        if(isCentric) {
            transformComponent.x -=  width / 2;
            transformComponent.y -=  height / 2;
        }

        //if(isCentric) {
            transformComponent.originX = 0;
            transformComponent.originY = 0;
        //}

        if(shape== Enums.Shape.RECTANGLE) {
            //PolygonComponent polygonComponent = new PolygonComponent();
            //polygonComponent.makeRectangle(width, height);
            //entity.add(polygonComponent);
            entity.add(getPhysicsBodyComponent(sceneLoader,entity,transformComponent.x,transformComponent.y,width,height,shape,IS_CENTRIC, bodyType,density,0));
        }
        else if(shape== Enums.Shape.CIRCLE) {

            entity.add(getPhysicsBodyComponent(sceneLoader,entity,transformComponent.x,transformComponent.y,width,height,shape,IS_CENTRIC, bodyType,density,0));
        }
        sceneLoader.getEngine().addEntity(entity);

        System.out.println(entity.getComponent(PhysicsBodyComponent.class).body);

        return entity;
    }

    public static Entity createParticle(SceneLoader sceneLoader, Entity parent, String name, float x, float y){

       //DimensionsComponent, MainItemComponent, TransformComponent ,TintComponent ,ZIndexComponent
       //ScriptComponent, ParentNodeComponent ,ParticleComponent

        Entity entity=sceneLoader.entityFactory.createEntity(parent, VOManager.getParticleVO(name,x,y,0));
        sceneLoader.getEngine().addEntity(entity);

        return entity;
    }


    public static Entity createLight(SceneLoader sceneLoader, Entity parent, LightVO.LightType lightType, float x, float y, int rays, float distance, float directionDegree, float softnessLength){

        //DimensionsComponent, MainItemComponent, TransformComponent, TintComponent,
        //ZIndexComponent, ScriptComponent, ParentNodeComponent, LightObjectComponent

        Entity entity=sceneLoader.entityFactory.createEntity(parent, VOManager.getLightVO(lightType,x,y,rays,distance,directionDegree,softnessLength));
        sceneLoader.getEngine().addEntity(entity);

        return entity;
    }


    public static Entity createSpriteAnimation(SceneLoader sceneLoader, Entity parent, String animationName, float x, float y, float width, float height, float angle , int fps, Animation.PlayMode playMode, boolean isCentric){

        //DimensionsComponent, MainItemComponent, TransformComponent, TintComponent,
        //ZIndexComponent, ScriptComponent, ParentNodeComponent, TextureRegionComponent,
        //SpriteAnimationStateComponent, AnimationComponent, SpriteAnimationComponent

        Entity entity=sceneLoader.entityFactory.createEntity(parent,VOManager.getSpriteAnimationVO(animationName,x,y,fps,playMode));

        DimensionsComponent dimensionsComponent=Mappers.dCM.get(entity);
        dimensionsComponent.width=width;
        dimensionsComponent.height=height;

        TransformComponent transformComponent=Mappers.tCM.get(entity);

        if(isCentric){
            transformComponent.x-=width/2;
            transformComponent.y-=height/2;
        }

        transformComponent.originX=width/2;
        transformComponent.originY=height/2;

        transformComponent.rotation=angle;
        sceneLoader.getEngine().addEntity(entity);
        return entity;
    }

    public static Entity createLabel(SceneLoader sceneLoader, Entity parent, float x, float y, String text, String style, int size){

        //DimensionsComponent, MainItemComponent, TransformComponent, TintComponent,
        //ZIndexComponent, ScriptComponent, ParentNodeComponent, LabelComponent

        Entity entity=sceneLoader.entityFactory.createEntity(parent,VOManager.getLabelVO(text,style,x,y,size));
        sceneLoader.getEngine().addEntity(entity);

        return entity;
    }


    public static Entity createLabel(SceneLoader sceneLoader, Entity parent, float x, float y, String text, FontSizePair fontSizePair){

        //DimensionsComponent, MainItemComponent, TransformComponent, TintComponent,
        //ZIndexComponent, ScriptComponent, ParentNodeComponent, LabelComponent

        Entity entity=sceneLoader.entityFactory.createEntity(parent,VOManager.getLabelVO(text,fontSizePair.fontName,x,y,fontSizePair.fontSize));
        sceneLoader.getEngine().addEntity(entity);

        return entity;
    }


    public static PhysicsBodyComponent getPhysicsBodyComponent(SceneLoader sceneLoader, Entity entity, float x, float y, float width, float height, Enums.Shape shape, boolean isCenterOrigin, BodyDef.BodyType bodyType, float density, float angle){

        entity.add(new PolygonComponent());

        PhysicsBodyComponent physicsBodyComponent1=new PhysicsBodyComponent();
        physicsBodyComponent1.body=sceneLoader.world.createBody(GenericPhysicsHelper.createBodyDef(bodyType,x* PhysicsBodyLoader.getScale(),y* PhysicsBodyLoader.getScale()));

        System.out.println("Width is "+width+"Height is"+height);

        float physics_width=width  * PhysicsBodyLoader.getScale();
        float physics_height=height  * PhysicsBodyLoader.getScale();

        System.out.println("Width is "+physics_width+"Height is"+physics_height);

        Shape shape1=null;

        if(shape== Enums.Shape.RECTANGLE) {
            shape1 = new PolygonShape();

            if(isCenterOrigin) ((PolygonShape)shape1).setAsBox(physics_width/2f, physics_height/2f, new Vector2(physics_width/2f, physics_height/2f), angle);
            else ((PolygonShape)shape1).setAsBox(physics_width/2f,physics_height/2f);

        }
        else if(shape== Enums.Shape.CIRCLE) {
            shape1 = new CircleShape();
            shape1.setRadius(width / 2f * PhysicsBodyLoader.getScale());
            if(isCenterOrigin)
            ((CircleShape)shape1).setPosition(new Vector2(((CircleShape)shape1).getRadius(), ((CircleShape)shape1).getRadius()));
        }

        FixtureDef fixtureDef=new FixtureDef();
        fixtureDef.shape=shape1;
        fixtureDef.density=density;
        fixtureDef.restitution=.2f;


        //physicsBodyComponent1.body.createFixture(shape1,density);
        physicsBodyComponent1.body.createFixture(fixtureDef);
        shape1.dispose();

        physicsBodyComponent1.body.setUserData(entity);

        return physicsBodyComponent1;
    }

    public static Entity createPSpriteAnimation(SceneLoader sceneLoader, Entity parent, String animationName, float x, float y, float width, float height, int fps, int playMode, boolean isCentric, BodyDef.BodyType bodyType, float density, Enums.Shape shape, float angle){

        //DimensionsComponent, MainItemComponent, TransformComponent, TintComponent,
        //ZIndexComponent, ScriptComponent, ParentNodeComponent, TextureRegionComponent,
        //SpriteAnimationStateComponent, AnimationComponent, SpriteAnimationComponent

        Entity entity=sceneLoader.entityFactory.createEntity(parent,VOManager.getPhysicsAnimationVO(animationName,x,y,fps,playMode,bodyType,density));

        if(shape== Enums.Shape.RECTANGLE) {
            PolygonComponent polygonComponent=new PolygonComponent();
            polygonComponent.makeRectangle(width,height);
            entity.add(polygonComponent);
        }
        else if(shape== Enums.Shape.CIRCLE) {
            entity.add(getPhysicsBodyComponent(sceneLoader, entity, x, y, width, height,shape,true,bodyType,density,angle));
        }

        DimensionsComponent dimensionsComponent=Mappers.dCM.get(entity);
        dimensionsComponent.width=width;
        dimensionsComponent.height=height;

        TransformComponent transformComponent=Mappers.tCM.get(entity);

        if(isCentric){
            transformComponent.x-=width/2;
            transformComponent.y-=height/2;
        }

        transformComponent.originX=0;
        transformComponent.originY=0;

        sceneLoader.getEngine().addEntity(entity);
        return entity;
    }

    public static Entity createColorPrimitive(SceneLoader sceneLoader, Entity parent, float x, float y, float width, float height, boolean isCentric){

        Entity entity=null;
        // =sceneLoader.getEntityFactory().createEntity(parent,VOManager.getColorPrimitiveVO(x,y,height,width));

  /*      TintComponent component=new TintComponent();
        component.color=Color.RED;

        DimensionsComponent dimensionsComponent=Mappers.dCM.get(entity);
        dimensionsComponent.width=width;
        dimensionsComponent.height=height;

        TransformComponent transformComponent=Mappers.tCM.get(entity);

        if(isCentric){
            transformComponent.x-=width/2;
            transformComponent.y-=height/2;
        }

        entity.add(component);*/
        sceneLoader.getEngine().addEntity(entity);

        return entity;
    }

    public static Entity createShape(SceneLoader sceneLoader, Entity parent, float x, float y, float width, float height, float angle, boolean isCentric , Color color){

        Entity entity=new Entity();
        SceneManager.shapeRendererType.getComponentFactory().createComponents(parent, entity, new MainItemVO());
        TransformComponent transformComponent = entity.getComponent(TransformComponent.class);
        transformComponent.x = x;
        transformComponent.y = y;

        DimensionsComponent dimensionsComponent=entity.getComponent(DimensionsComponent.class);
        dimensionsComponent.width=width;
        dimensionsComponent.height=height;

        if(isCentric){
            transformComponent.x-=width/2;
            transformComponent.y-=height/2;
        }

        transformComponent.rotation=angle;
        transformComponent.originX=width/2;
        transformComponent.originY=height/2;

        //if(isPhysics)
           // entity.add(getPhysicsBodyComponent(sceneLoader,entity,transformComponent.x,transformComponent.y,width,height, Enums.Shape.RECTANGLE,true));

        entity.getComponent(TintComponent.class).color=color;
        sceneLoader.getEntityFactory().postProcessEntity(entity);
        sceneLoader.getEngine().addEntity(entity);
        return entity;
    }


    public static Entity createPShape(SceneLoader sceneLoader, Entity parent, float x, float y, float width, float height, float angle , boolean isCentric, Color color, BodyDef.BodyType bodyType, float density){

        Entity entity=new Entity();
        SceneManager.shapeRendererType.getComponentFactory().createComponents(parent, entity, new MainItemVO());
        TransformComponent transformComponent = entity.getComponent(TransformComponent.class);
        transformComponent.x = x;
        transformComponent.y = y;

        DimensionsComponent dimensionsComponent=entity.getComponent(DimensionsComponent.class);
        dimensionsComponent.width=width;
        dimensionsComponent.height=height;

        if(isCentric){
            transformComponent.x-=width/2;
            transformComponent.y-=height/2;
        }

        transformComponent.originX=0;
        transformComponent.originY=0;

        entity.add(getPhysicsBodyComponent(sceneLoader,entity,transformComponent.x,transformComponent.y,width,height, Enums.Shape.RECTANGLE,IS_CENTRIC,bodyType,density,angle));

        entity.getComponent(TintComponent.class).color=color;
        sceneLoader.getEntityFactory().postProcessEntity(entity);
        sceneLoader.getEngine().addEntity(entity);

        return  entity;
    }

    public static Entity createShape(SceneLoader sceneLoader, Entity parent, float x, float y, float radius, boolean isCentric, Color color){

        Entity entity=new Entity();
        SceneManager.shapeRendererType.getComponentFactory().createComponents(parent, entity, new MainItemVO());
        TransformComponent transformComponent = entity.getComponent(TransformComponent.class);
        transformComponent.x = x;
        transformComponent.y = y;

        ShapeComponent shapeComponent=   ComponentRetriever.get(entity, ShapeComponent.class);
        shapeComponent.setShape(ShapeComponent.Shape.CIRCLE);
        shapeComponent.setRadius(radius);

        DimensionsComponent dimensionsComponent=entity.getComponent(DimensionsComponent.class);
        dimensionsComponent.width=radius;
        //dimensionsComponent.height=height;

        if(!isCentric){
            transformComponent.x+=radius;
            transformComponent.y+=radius;
        }

        transformComponent.originY=0;
        transformComponent.originX=0;

        entity.getComponent(TintComponent.class).color=color;
        sceneLoader.getEntityFactory().postProcessEntity(entity);
        sceneLoader.getEngine().addEntity(entity);

        return entity;
    }

    public static Entity createPShape(SceneLoader sceneLoader, Entity parent, float x, float y, float radius, boolean isCentric , Color color, BodyDef.BodyType bodyType, float density){

        Entity entity=new Entity();
        SceneManager.shapeRendererType.getComponentFactory().createComponents(parent, entity, new MainItemVO());
        TransformComponent transformComponent = entity.getComponent(TransformComponent.class);
        transformComponent.x = x;
        transformComponent.y = y;

        ShapeComponent shapeComponent= ComponentRetriever.get(entity, ShapeComponent.class);
        shapeComponent.setShape(ShapeComponent.Shape.CIRCLE);
        shapeComponent.setRadius(radius);

        DimensionsComponent dimensionsComponent=entity.getComponent(DimensionsComponent.class);
        dimensionsComponent.width=radius;
        dimensionsComponent.height=radius;

        if(!isCentric){
            transformComponent.x+=radius;
            transformComponent.y+=radius;
        }

        transformComponent.originX=0;
        transformComponent.originY=0;

        //if(isPhysics)
            entity.add(getPhysicsBodyComponent(sceneLoader,entity,transformComponent.x,transformComponent.y,2*radius,2*radius, Enums.Shape.CIRCLE,!IS_CENTRIC,bodyType,density,0));


        entity.getComponent(TintComponent.class).color=color;
        sceneLoader.getEntityFactory().postProcessEntity(entity);
        sceneLoader.getEngine().addEntity(entity);
        return entity;
    }



}

