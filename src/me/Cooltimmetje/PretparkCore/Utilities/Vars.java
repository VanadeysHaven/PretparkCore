/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Tim Medema
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.Cooltimmetje.PretparkCore.Utilities;

import me.Cooltimmetje.PretparkCore.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.HashMap;

/**
 * This class has been created on 28-7-2015 at 19:04 by cooltimmetje.
 */
public class Vars {

    public static String PRETPARK_NAAM = MiscUtils.color("&a&lSoonTM");
    public static String MOTD = MiscUtils.color(Vars.PRETPARK_NAAM + " &8\u00BB &aBinnenkort... \n");
    public static String WORLD_NAME = "world";

    public static int COIN_GAIN = 30;
    public static int COIN_TIME = 60; //in minutes
    public static int DOUBLE_CHANCE = 10; //in %

    /* HASH MAPS */
    public static HashMap<String, Integer> coins = new HashMap<>();
    public static HashMap<String, Integer> coinsTime = new HashMap<>();
    public static HashMap<String, Integer> globaldata = new HashMap<>();
    public static HashMap<Integer, String> rideName = new HashMap<>();
    public static HashMap<Integer, Location> rideLocation = new HashMap<>();
    public static HashMap<Integer, String> rideStatus = new HashMap<>();
    public static HashMap<Integer, Integer> rideSlot = new HashMap<>();
    public static HashMap<Integer, String> rideLook = new HashMap<>();
    public static HashMap<Integer, String> rpLink = new HashMap<>();

    public static HashMap<String, Material> helmet = new HashMap<>();
    public static HashMap<String, Material> chest = new HashMap<>();
    public static HashMap<String, Material> legs = new HashMap<>();
    public static HashMap<String, Material> boots = new HashMap<>();
    public static HashMap<String, Boolean> enchantment = new HashMap<>();
    public static HashMap<String, Integer> rp = new HashMap<>();



    /* ARRAY LISTS */

    /* Locations */
    public static Location signSpawnTopLeft = new Location(Bukkit.getWorld(WORLD_NAME), -1197, 56, 208);
    public static Location signSpawnTopRight = new Location(Bukkit.getWorld(WORLD_NAME), -1197, 56, 200);
    public static Location signSpawnBottemLeft = new Location(Bukkit.getWorld(WORLD_NAME), -1197, 55, 208);
    public static Location signSpawnBottemRight = new Location(Bukkit.getWorld(WORLD_NAME), -1197, 55, 200);

    public static void setGlobaldata(){
        globaldata.put("uniqueplayers", Main.getPlugin().getConfig().getInt("Globaldata.uniqueusers"));
        globaldata.put("onderhoud", Main.getPlugin().getConfig().getInt("Globaldata.server_onderhoud"));
    }

    public static void saveUp(){
        Main.getPlugin().getConfig().set("Globaldata.uniqueusers", globaldata.get("uniqueplayers"));
        Main.getPlugin().saveConfig();
    }

    public static void saveSo(){
        Main.getPlugin().getConfig().set("Globaldata.server_onderhoud", globaldata.get("onderhoud"));
        Main.getPlugin().saveConfig();
    }

    public static void setRpLink(){
        rpLink.put(1, "https://www.dropbox.com/s/s2odyn70j652tn4/Themepark%20-%20Lite.zip?dl=1"); //lite
        rpLink.put(2, "https://www.dropbox.com/s/k858bnq8wi1lss3/Themepark%20-%20Heavy.zip?dl=1"); //heavy
    }
}
