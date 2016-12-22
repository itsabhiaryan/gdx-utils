package com.ng;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 03-06-2015.
 */
public class Constants {

    /**
     * Game name is name of this game.
     * 1. XXXXXXXX
     *
     */

    static final String GAME_NAME="XXXXXXXXX";
    static final String PACKAGE_NAME="com.ng.XX";
    public static final String GOOGLE_PLAY_LINK="https://play.google.com/store/apps/details?id="+PACKAGE_NAME;

    public enum PublisherIDLink{
        GOOGLE("https://play.google.com/store/apps/developer?id="),
        AMAZON("sds"),
        SAMSUNG("sd"),
        SLIDEME("sd"),
        ;

        public String defaultValue;
        PublisherIDLink(String defaultValue){

            this.defaultValue = defaultValue;
        }
    }

    public static float startY;
    static float cardGap;
    public static final int COLUMN =10;
    public static int OBJECT_COLOR =7;
    static int TOTAL_LEVEL=130;
    public static int COMPLETED_LEVEL=129;

    public static boolean isTouch=false;
    public static final int EXTRA_MOVE=5;
    public static final int EXTRA_TIME=30;
    public static final int WITHDRAW_DIAMOND=7;

    public static final int TOTAL_HEART=5;
    static final int TOTAL_DIAMOND=21;
    public static final int LEVEL_DIAMOND=10;
    public static final long RENEWAL_TIME =2*60;

    public static boolean PAUSE;

    public static final int MATCH_AMOUNT=3;
    public static final int MAXIMUM_SPECIAL_ON_BOARD=2;

    public static final long HINT_TIME=8000;
    static final int TOTAL_FRAME_TYPE=5;

    public static float userProgress;
    private static boolean flushFrames;

    static void resetGameData(){
            flushFrames=true;
    }

    /**
     * Version 1.0 First Release 06-09-2015.
     *
     *
     */

    public static final String VERSION="1.0";

    /**
     * Method that is substitute of String.format() method.
     * @param number Number that you want to format.
     * @param pads Zero digit that that pads your number.
     * @return Formatted string.
     * This method is used in many place like LevelScreen,GameScreen and on different dialog box.
     */

    public static String fn(int number, int pads) {
     /*   if(num>=10)return Integer.toString(num);
        char[] z;

        z= new char[pads];
        for(int i=0; i<z.length; i++){
            z[i] = '0';
        }
        return "" + new String(z) + Integer.toString(num);*/

        String givenDigit=Integer.toString(number);
        char[] z;
        if(givenDigit.length()>=pads)
            return givenDigit;
        else {

            z= new char[pads-givenDigit.length()];
            for(int i=0; i<z.length; i++){
                z[i] = '0';
            }

            return "" + new String(z) + givenDigit;
        }
    }

    public static String fn1(int number,int pads){

        String givenDigit=Integer.toString(number);
        char[] z;
        if(givenDigit.length()>=pads)
            return givenDigit;
        else {

                z= new char[pads-givenDigit.length()];
                for(int i=0; i<z.length; i++){
                   z[i] = '0';
                }

                return "" + new String(z) + givenDigit;
            }
    }

    public enum CurrentScreen{
        SPLASH_SCREEN,
        HOME_SCREEN,
        LEVEL_SCREEN,
        GAME_SCREEN
    }

    enum Pack{

        COMMON("pack/common.pack"),
        LEVEL("pack/levelscreen.pack"),
        GAME("pack/gamescreen.pack"),
        BACK_PANE("pack/backpane.pack"),
        GAME_OBJECT("pack/gameobject.pack");

        public String name;

         Pack(String name){
            this.name=name;
        }
    }

}
