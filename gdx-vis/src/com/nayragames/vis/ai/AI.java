package com.nayragames.vis.ai;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.artemis.managers.PlayerManager;
import com.badlogic.gdx.ai.steer.behaviors.Arrive;
import com.badlogic.gdx.ai.steer.limiters.LinearLimiter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.kotcrab.vis.runtime.component.Layer;
import com.kotcrab.vis.runtime.component.Renderable;
import com.nayragames.gdxutils.model.Position;
import com.nayragames.gdxutils.model.Scale;
import com.nayragames.vis.EntityManager;
import com.nayragames.vis.Enums;
import com.nayragames.vis.component.AnimationComponent;
import com.nayragames.vis.component.CollisionComponent;
import com.nayragames.vis.component.HealthComponent;
import com.nayragames.vis.component.SteerableComponent;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 31-07-2016.
 */
public class AI {


    public static Arrive<Vector2> getArrive(SteerableComponent steer, SteerableComponent target,float declearationRadius){

        Arrive<Vector2> arriveSB = new Arrive<Vector2>(steer, target) //
                .setLimiter(new LinearLimiter(3500, 1000)) //
                .setTimeToTarget(0.1f) //
                .setArrivalTolerance(0.001f) //
                .setDecelerationRadius(declearationRadius);
        return arriveSB;
    }


    public static Entity createFormationMemberEntity(World world, TextureRegion[] textureRegions, float width, float height, Entity leader){

        Entity entity =world.createEntity();
        entity.edit().add(new Layer(Enums.Layer.PLAYER_LAYER.value));
        entity.edit().add(new Renderable(0));
        //entity.edit().add(addVisSprite(Assets.imageAsset.circularBullet,Size.makeSize(width*.075f,height*.075f),Position.makePosition(1, 1),true,0));
        entity.edit().add(new AnimationComponent(textureRegions,.1f, Animation.PlayMode.LOOP));

        EntityManager.addTransform(entity, Position.makePosition(MathUtils.random(0,4.8f), 4), Scale.makeUnScale(),0);
        world.getSystem(PlayerManager.class).setPlayer(entity, Enums.Player.NPC.value);
        world.getSystem(GroupManager.class).add(entity, Enums.Group.ENEMY_SHIP.value);

        entity.edit().add(Test.createSteerC(entity,true));
        //entity.edit().add(new BulletSpawnComponent(false, BulletSpawnComponent.BulletSpawnType.SINGLE));

        // EntityManager.createFormationMemberComponent(entity,leader);
        entity.edit().add(Test.createFSMC(entity));
        entity.edit().add(new CollisionComponent());

        HealthComponent health=new HealthComponent(5);
        entity.edit().add(health);

        float percent=(health.health/health.maximumHealth)*100;
        //VisText textComponent=new VisText(Assets.bitmapFontAsset.bitmapFont,String.valueOf((int)percent));
        //textComponent.setScale(.5f, .5f);

        //entity.edit().add(textComponent);

        return entity;
    }

}
