package com.ng;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;

import java.util.HashMap;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 20-01-2016.
 */
public class Pref {

    /**
     * The Preferences object to store GameData.
     */

    /**
     * Preferences name where all game data save in user device
     * Not changed when game name is change because previous data got void.
     *
     */

    private static Preferences prefs = Gdx.app.getPreferences(Constants.PACKAGE_NAME+".pref");    //For New Game
    private static boolean hasSound,isFullScreen;
    private static float fxVolume, musicVolume;
    public static int score,death;
    private static int level,heart,diamond;
    public static long renewalTime;
    public static String resolution;
    private static int[] levelStar;

    //public static Gson gson;

    public static Json json;
    static HashMap<String, String> hashTable = new HashMap<String, String>();

    // static IntArray intArray;
    // static Hashtable<String, String> hashTable = new Hashtable<String, String>();

    public static void init(){

        levelStar=new int[Constants.TOTAL_LEVEL];

        //FloatArray floatArray=new FloatArray();
        //IntArray intArray=new IntArray();
        //intArray.add();

//For released Android
        /*gson=new Gson();
         if(prefs.contains("Puzzle"))
            levelStar=gson.fromJson(prefs.getString("Puzzle", null),int[].class);
        else
            prefs.putString("Puzzle",gson.toJson(levelStar));*/

//For Html
       /* json = new Json();
        if(prefs.getString("Puzzle",null)==null) {
            hashTable.put("Puzzle", json.toJson(levelStar) ); //here you are serializing the array
            prefs.put(hashTable);
            prefs.flush();
        }
        else {
            String serializedInts =prefs.getString("Puzzle");
            levelStar = json.fromJson(int[].class, serializedInts); //you need to pass the class type - be aware of it!
        }*/



        /*
        if(gson.fromJson(Pref.getString(StringValue.LEVEL_STAR),int[].class)==null)
            putString(StringValue.LEVEL_STAR,gson.toJson(levelStar));
        else
            levelStar=gson.fromJson(Pref.getString(StringValue.LEVEL_STAR),int[].class);*/


     /*   if(Pref.getString(StringValue.LEVEL_STAR)==null) {
            System.out.println("Got NOthing");
            intArray=new IntArray();
            for (int i=0;i<Main.TOTAL_LEVEL;i++)
                intArray.add(0);
            String levelsJson = json.toJson(intArray,IntArray.class);
            Pref.putString(StringValue.LEVEL_STAR,levelsJson);
        }
        else {

            intArray=json.fromJson(IntArray.class, Pref.getString(StringValue.LEVEL_STAR));
            System.out.println("Got Something");
        }*/

      /*  if(prefs.getString("test",null)==null) {
           // Json json = new Json();
           // hashTable.put("test", json.toJson(levelStar) ); //here you are serializing the array
           // prefs.put(hashTable);
             hashTable.put("test", json.toJson(levelStar) ); //here you are serializing the array
             prefs.put(hashTable);
             prefs.flush();
        }
        else {

            //intArray=json.fromJson(IntArray.class, Pref.getString(StringValue.LEVEL_STAR));
            String serializedInts = prefs.getString("test");
            levelStar = json.fromJson(int[].class, serializedInts); //you need to pass the class type - be aware of it!

            }
        */

        //if(isHtml)
        initLevelStar();
        //else
        //initLevelStarAndroid();

        hasSound = Pref.getBoolean(BooleanValue.SOUND_ENABLED);
        score= Pref.getInteger(IntegerValue.SCORE);
        death=Pref.getInteger(IntegerValue.DEATH);

        musicVolume=Pref.getFloat(FloatValue.MUSIC_VOLUME);
        fxVolume=Pref.getFloat(FloatValue.SOUND_VOLUME);

        level=getInteger(IntegerValue.LEVEL);
        heart=getInteger(IntegerValue.HEART);
        diamond=getInteger(IntegerValue.DIAMOND);

        renewalTime=getLong(LongValue.RENEWAL_TIME);
        isFullScreen=getBoolean(BooleanValue.GRAPHICS_FULLSCREEN);

        resolution=getString(StringValue.PORTRAIT);
    }

    public static void initLevelStar(){

        json = new Json();
        String serializedInts =getString(StringValue.LEVEL_STAR);
        if(serializedInts==null) {
            hashTable.put(StringValue.LEVEL_STAR.toString(), json.toJson(levelStar) ); //here you are serializing the array
            prefs.put(hashTable);
            prefs.flush();
        }
        else {
            levelStar = json.fromJson(int[].class, serializedInts); //you need to pass the class type - be aware of it!
        }
    }

    //public static Gson gson;

 /*   public static void initLevelStarAndroid(){

        gson=new Gson();
        int[] serializedInts=gson.fromJson(Pref.getString(StringValue.LEVEL_STAR),int[].class);
        if(serializedInts==null)
            putString(StringValue.LEVEL_STAR,gson.toJson(levelStar));
        else
            levelStar=serializedInts;
    }*/


    public static void setLevelStar(int level,int star){

        //levelStar[level]=star;
        //Pref.putString(StringValue.LEVEL_STAR,gson.toJson(levelStar));

        /*intArray.set(level,star);
        String levelsJson = json.toJson(intArray,IntArray.class);
        Pref.putString(StringValue.LEVEL_STAR,levelsJson);

       */

        /*levelStar[level]=star;
        prefs.putString("Puzzle",gson.toJson(levelStar));
        prefs.flush();*/


        /**
         * For json work with both android and html used
         *
         */

      //  if(isHtml) {

            levelStar[level] = star;
            hashTable.put(StringValue.LEVEL_STAR.toString(), json.toJson(levelStar)); //here you are serializing the array
            prefs.put(hashTable);
            prefs.flush();
      /*  }
        else {

            levelStar[level]=star;
            Pref.putString(StringValue.LEVEL_STAR,gson.toJson(levelStar));
        }*/

    }

    public static int getLevelStar(int level){
        return levelStar[level];
    }

    public static void setHasSound(boolean isSound) {
        Pref.hasSound = isSound;
        Pref.putBoolean(BooleanValue.SOUND_ENABLED,isSound);
    }

    public static boolean getHasSound(){
        return hasSound;
    }

    public static void setFxVolume(float volume){
        fxVolume=volume;
        Pref.putFloat(FloatValue.SOUND_VOLUME,volume);
        prefs.flush();
    }

    public static float getFxVolume(){
        return fxVolume;
    }

    public static void setMusicVolume(float volume) {
        musicVolume = volume;
        Pref.putFloat(FloatValue.MUSIC_VOLUME, volume);
        prefs.flush();
    }

    public static float getMusicVolume(){
        return musicVolume;
    }

    public static void setLevel(int level){
        Pref.level=level;
        Pref.putInteger(IntegerValue.LEVEL,level);
        prefs.flush();
    }

    public static int getLevel(){
        return level;
    }

    public static void setHeart(int heart){
        Pref.heart=heart;
        Pref.putInteger(IntegerValue.HEART,heart);
        prefs.flush();
    }

    public static int getHeart(){
        return heart;
    }

    public static void setDiamond(int diamond){
        Pref.diamond=diamond;
        Pref.putInteger(IntegerValue.DIAMOND,diamond);
        prefs.flush();
    }

    public static int getDiamond(){
        return diamond;
    }

    public static void setRenewalTime(long time){
        Pref.renewalTime=time;
        Pref.putLong(LongValue.RENEWAL_TIME,time);
        prefs.flush();
    }

    public static long getRenewalTime(){
        return renewalTime;
    }

    private enum FloatValue{
        MUSIC_VOLUME(0.5f),
        SOUND_VOLUME(0.5F);

        public float defaultValue;
        FloatValue(float defaultValue){
            this.defaultValue = defaultValue;
        }
    }

    private enum IntegerValue{
        SCORE(0),
        TIMELINE(0),
        STAGE(0),
        DIAMOND(Constants.TOTAL_DIAMOND),
        HEART(Constants.TOTAL_HEART),
      //  LEVEL(1),//for deployment
        LEVEL(1),
        DEATH(0);

        public int defaultValue;
        IntegerValue(int defaultValue){
            this.defaultValue = defaultValue;
        }
    }

    private enum LongValue {

        RENEWAL_TIME(Constants.RENEWAL_TIME);

        public long defaultValue;
        LongValue(long defaultValue){
            this.defaultValue=defaultValue;
        }
    }

    private enum StringValue{
        USER_NAME("user"),
        LEVEL_STAR(null),
        LANDSCAPE("800x480"),
        PORTRAIT("400x640"),
        ;

        private String defaultValue;
        StringValue(String defaultValue){
            this.defaultValue = defaultValue;
        }
    }

    private enum BooleanValue{

        FB_LOGGED(false),

        MUSIC_ENABLED(true),
        SOUND_ENABLED(true),

        GRAPHICS_FULLSCREEN(false),
        ;

        private boolean defaultValue;
        BooleanValue(boolean defaultValue){
            this.defaultValue = defaultValue;
        }
    }

    private static void putFloat(FloatValue key, float value){
        prefs.putFloat(key.toString(), value);
        prefs.flush();
    }

    private static float getFloat(FloatValue key){
        return prefs.getFloat(key.toString(), key.defaultValue);
    }

    private static void putInteger(IntegerValue key, int value){
        prefs.putInteger(key.toString(), value);
        prefs.flush();
    }

    private static Integer getInteger(IntegerValue key){

        return prefs.getInteger(key.toString(), key.defaultValue);
    }

    private static void putLong(LongValue key,long value){
        prefs.putLong(key.toString(),value);
        prefs.flush();
    }

    private static Long getLong(LongValue key){
        return prefs.getLong(key.toString(),key.defaultValue);
    }

    private static void putString(StringValue key, String value){
        prefs.putString(key.toString(), value);
        prefs.flush();
    }

    private static String getString(StringValue key){
        return prefs.getString(key.toString(), key.defaultValue);
    }

    private static void putBoolean(BooleanValue key, boolean value){
        prefs.putBoolean(key.toString(), value);
        prefs.flush();
    }

    private static boolean getBoolean(BooleanValue key){
        return prefs.getBoolean(key.toString(), key.defaultValue);
    }


}
