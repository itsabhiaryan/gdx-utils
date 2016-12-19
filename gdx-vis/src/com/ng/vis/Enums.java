package com.ng.vis;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 15-12-2015.
 *
 * Collection of different Enums used in ECS.
 *
 */
public class Enums {

    private static final String TAG = "[" + Enums.class.getSimpleName() + "]";

    public enum CollectionType {
        COIN(0);

        public int value;
        CollectionType(int value){
            this.value = value;
        }
    }

    /**
     * Different groups of entity used in ECS by GroupManager.
     *
     */

    public enum Group {
        PLAYER_BULLET("PLAYER_BULLET"),
        ENEMY_BULLET("ENEMY_BULLET"),
        ENEMY_SHIP("ENEMY_SHIP"),
        PLAYER("PLAYER"),
        COLLECTABLE("COLLECTION");

        public String value;
        Group(String value){
            this.value = value;
        }
    }

    /**
     * Different layers for entity.
     *
     */

    public enum Layer {

        BG_LAYER(0),
        NP_LAYER(1),
        PLAYER_LAYER(2),
        UI_LAYER(3),
        ;
        public int value;
        Layer(int value) {
            this.value = value;
        }
    }

    /**
     * Different Player of entity used in ECS by PlayerManager.
     *
     */
    public enum Player{

        PLAYER("PLAYER"),
        NPC("NON-PLAYER"),
        ;
        public String value;
        Player(String value){
            this.value = value;
        }
    }
}
