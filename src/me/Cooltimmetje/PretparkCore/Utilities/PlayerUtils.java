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

import com.evilmidget38.UUIDFetcher;
import me.Cooltimmetje.PretparkCore.Managers.InventoryManager;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * This class has been created on 28-7-2015 at 19:58 by cooltimmetje.
 */
public class PlayerUtils {

    public static void fixGamemode(Player p){
        if(p.isOp() || p.hasPermission("pretparkcore.bypassgm")){
            p.setGameMode(GameMode.CREATIVE);
        } else {
            p.setGameMode(GameMode.ADVENTURE);
            InventoryManager.setInventory(p);
        }
    }

    public static int getCoins(Player p){
        return Vars.coins.get(p.getName());
    }

    public static int getCoinTime(Player p){
        return Vars.coinsTime.get(p.getName());
    }

    public static void addCoins(Player p, int coinsGained, String reason){
        int curCoins = getCoins(p);
        Vars.coins.remove(p.getName());
        Vars.coins.put(p.getName(), coinsGained + curCoins);
        ChatUtils.sendMsg(p, "&6+" + coinsGained + " coins! (" + reason + ")");
        p.playSound(p.getLocation(), Sound.LEVEL_UP, 100, 1);
        ScoreboardUtils.updateScoreboard(p, false);
        WorldUtils.updateSpawnSigns(p);
    }

    public static void setCoins(Player p, int amount, String reason){
        Vars.coins.remove(p.getName());
        Vars.coins.put(p.getName(), amount);
        p.playSound(p.getLocation(), Sound.LEVEL_UP, 100, 1);
        ScoreboardUtils.updateScoreboard(p, false);
        ChatUtils.sendMsg(p, "&6=" + amount + " coins! (" + reason + ")");
        WorldUtils.updateSpawnSigns(p);
    }

    public static void takeCoins(Player p, int coinsGained, String reason){
        int curCoins = getCoins(p);
        Vars.coins.remove(p.getName());
        Vars.coins.put(p.getName(), curCoins - coinsGained);
        ChatUtils.sendMsg(p, "&6-" + coinsGained + " coins! (" + reason + ")");
        p.playSound(p.getLocation(), Sound.LEVEL_UP, 100, 1);
        ScoreboardUtils.updateScoreboard(p, false);
        WorldUtils.updateSpawnSigns(p);
    }

    public static void setCoinTime(Player p, int time){
        Vars.coinsTime.remove(p.getName());
        Vars.coinsTime.put(p.getName(), time);
    }

    public static String getUUID(Player p) {
        String name, uuid = null;
        name = p.getName();

        try {
            uuid = new UUIDFetcher(Arrays.asList(name)).call().get(name).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uuid;
    }

}
