package me.Cooltimmetje.PretparkCore.Utilities;

import java.util.Random;

/**
 * This class has been created on 28-7-2015 at 19:05 by cooltimmetje.
 */
public class MiscUtils {

    public static String color(String s){
        return s.replace('&', '\u00A7');
    }

    public static int randomInt(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public static boolean isInt(String str){
        try{
            int num = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
