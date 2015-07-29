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
