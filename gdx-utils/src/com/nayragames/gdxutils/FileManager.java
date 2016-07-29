package com.nayragames.gdxutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Date;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 24-07-2016
 *
 */
public class FileManager {

    //static DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

   /* *//* Checks if external storage is available for read and write *//*
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    *//* Checks if external storage is available to at least read *//*
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }*/

    public static ArrayList<Integer> getHintData(String paramString, int lineNo) {

        ArrayList<Integer> localArrayList = new ArrayList<Integer>();
        String str1 = Gdx.files.internal(paramString).readString().split("\r\n")[(lineNo - 1)];
        String str2 = str1.substring(1 + str1.indexOf("?"));
        //System.out.println("lineString is:" + str2);
        String[] arrayOfString=null;

        if (!str2.equals("#")) {
            //System.err.println("This is :\n" + str2);
            arrayOfString = str2.split(",");
        }

        for (int i = 0;; i++) {
            if (i >= arrayOfString.length) {
                if (localArrayList.size() == 0)
                    System.out.println("this is a null line");

                return localArrayList;
            }
            localArrayList.add(Integer.valueOf(Integer.parseInt(arrayOfString[i])));
        }
    }

    public static Array<Integer> getDataByBlock(String paramString, int lineNo, int blockNo) {

        Array<Integer> localArrayList = new Array<Integer>();
        String str1 = Gdx.files.internal(paramString).readString().split("\r\n")[(lineNo - 1)];
        String str2 = str1.substring(1 + str1.indexOf("?")).split("#")[blockNo];
       // System.out.println(str2);
       // String str3 = str2.substring(0, str2.length() - 1);
       // System.out.println(str3);
        //String str4 = str3.substring(1, str3.length()).replaceAll("\\s*", "");
        String[] arrayOfString=null;

        if (!str2.equals(""))
            arrayOfString = str2.split(",");

        for (int i = 0;; i++) {
            if (i >= arrayOfString.length)
                return localArrayList;

            localArrayList.add(Integer.valueOf(Integer.parseInt(arrayOfString[i])));
        }
    }

    public static void writeToFile(String paramString, String fileName, int paramInt) {

        Date date=new Date();
       // paramString+=dateFormat.format(date)+ File.separator+paramString;
        Gdx.files.absolute(fileName).writeString(paramString, true);
    }

}
