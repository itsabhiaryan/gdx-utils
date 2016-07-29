package com.nayragames.o2d;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 1/6/2016.
 *
 */
public class SceneConstant {

    public enum SceneName{
        MAIN_SCENE("main"),
        MENU_SCENE("menu"),

        GAME("game"),
        GAME_SCENE1("game1"),
        GAME_SCENE2("game2"),
        GAME_SCENE3("game3"),

        GAME_OVER_SCENE("gameover"),

        HELP_SCENE("help"),
        ABOUT_SCENE("about"),
        SETTING_SCENE("setting");

        public  String value;
        SceneName(String value){
            this.value=value;
        }
    }

    public enum AboutSceneEntity {
        GAME_NAME("gameName"),
        GAME_VERSION("gameVersion")
        ;

        public String value;
        AboutSceneEntity(String value){
            this.value=value;
        }
    }

    public enum HelpSceneEntity {
        XYX("xyz");

        public String value;
        HelpSceneEntity(String value){
            this.value=value;
        }
    }

    public enum MainSceneEntity{
        PLAY_BUTTON("playButton")
        ;

        public String value;
        MainSceneEntity(String value){
            this.value=value;
        }
    }

    public enum MenuSceneEntity {
        PLAY_BUTTON("playButton"),
        HELP_BUTTON("helpButton"),
        ABOUT_BUTTON("aboutButton"),
        MORE_GAMES("moreGamesButton"),
        LEADERBOARD("leaderboard"),

        RATE_GAME("rate"),

        SOUND_ON("unmute"),
        SOUND_OFF("mute"),

        ;
        public String value;
        MenuSceneEntity(String value){
            this.value=value;
        }
    }

    public enum GameSceneEntity {
        GEAR("gear"),
        ROCK("rock"),
        LEFTBOARD("leftBoard"),
        CIRCLESPAWN("circlespawn");

        public String value;
        GameSceneEntity(String value){
            this.value=value;
        }
    }
}
