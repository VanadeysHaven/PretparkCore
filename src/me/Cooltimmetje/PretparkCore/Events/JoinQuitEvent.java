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

package me.Cooltimmetje.PretparkCore.Events;

import me.Cooltimmetje.PretparkCore.Managers.ChatManager;
import me.Cooltimmetje.PretparkCore.Managers.ResourcePackManager;
import me.Cooltimmetje.PretparkCore.MysqlManager.Database;
import me.Cooltimmetje.PretparkCore.Utilities.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * This class has been created on 28-7-2015 at 19:33 by cooltimmetje.
 */
public class JoinQuitEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        final Player pfinal = event.getPlayer();

        Database.loadData(p);
        Database.loadSettings(p);
        ScoreboardUtils.constructScoreboard(p);

        ScheduleUtils.scheduleTask(20, new Runnable() {
            @Override
            public void run() {
                PlayerUtils.fixGamemode(pfinal);
            }
        });
        for(Player pl : Bukkit.getOnlinePlayers()){
            ScoreboardUtils.updateScoreboard(pl, false);
        }





        ScheduleUtils.scheduleTask(20, new Runnable() {
            @Override
            public void run() {
                ChatManager.joinDisable.add(pfinal);
                pfinal.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999, 255, false, false));
                pfinal.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999, 127, false, false));
                pfinal.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999, 129, false, false));

                for(int i=0; i < 100; i ++) {
                    pfinal.sendMessage("");
                }

                ChatUtils.sendMsgTag(pfinal, "ResourcePack", "Over 5 seconden gaan we de ResourcePack naar je sturen... Druk op &lJA &aals er om een bevestiging word gevraagd! " +
                        "&a&nAls je op nee drukt krijg je een kick!");
                pfinal.sendMessage("");
            }
        });

        ScheduleUtils.scheduleTask(120, new Runnable() {
            @Override
            public void run() {
                ResourcePackManager.setRP(pfinal);
            }
        });

        WorldUtils.updateSpawnSigns(p);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player p = event.getPlayer();

        ScoreboardUtils.destroyScoreboard(p);
        Database.saveData(p, true);
        Database.saveSettings(p, true);

//        SwagUI.removeBalloon(p);

        for(Player pl : Bukkit.getOnlinePlayers()){
            if(p != pl) {
                ScoreboardUtils.updateScoreboard(pl, true);
            }
        }
    }

}
