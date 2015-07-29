package me.Cooltimmetje.PretparkCore.Utilities;

import me.Cooltimmetje.PretparkCore.Main;

import java.util.HashMap;

/**
 * This class has been created on 28-7-2015 at 19:04 by cooltimmetje.
 */
public class Vars {

    public static String PRETPARK_NAAM = MiscUtils.color("&2&lPingFinity");

    public static int COIN_GAIN = 30;
    public static int COIN_TIME = 60; //in minutes
    public static int DOUBLE_CHANCE = 10; //in %

    /* HASH MAPS */
    public static HashMap<String, Integer> coins = new HashMap<>();
    public static HashMap<String, Integer> coinsTime = new HashMap<>();
    public static HashMap<String, Integer> globaldata = new HashMap<>();

    public static void setGlobaldata(){
        globaldata.put("uniqueplayers", Main.getPlugin().getConfig().getInt("Globaldata.uniqueusers"));
    }

    public static void saveUp(){
        Main.getPlugin().getConfig().set("Globaldata.uniqueusers", globaldata.get("uniqueplayers"));
        Main.getPlugin().saveConfig();
    }
}
