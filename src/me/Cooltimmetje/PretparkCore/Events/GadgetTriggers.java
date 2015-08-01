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

import me.Cooltimmetje.PretparkCore.Utilities.GadgetMethods;
import me.Cooltimmetje.PretparkCore.Utilities.MiscUtils;
import me.Cooltimmetje.PretparkCore.Utilities.ScheduleUtils;
import org.bukkit.Material;
import org.bukkit.block.Jukebox;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * This class has been created on 30-7-2015 at 21:34 by cooltimmetje.
 */
public class GadgetTriggers implements Listener {

    @EventHandler
    public void onRightClickItem(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if(event.getItem() != null){
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getItem().hasItemMeta()) {
                    switch (event.getItem().getType()) {
                        default:
                            break;
                        case FIREWORK_CHARGE:
                            event.setCancelled(true);
                            GadgetMethods.shootFirework(p.getLocation(), p.getWorld().getName());
                            break;
                        case FIREWORK:
                            event.setCancelled(true);
                            Firework fw = GadgetMethods.shootFirework(p.getLocation(), p.getWorld().getName());
                            fw.setPassenger(p);
                            break;
                        case RECORD_11:
                            event.setCancelled(true);
                            playMusic(p);
                            break;
                    }
                }
            }
        }
    }

    private void playMusic(Player p) {
        p.getLocation().getBlock().setType(Material.JUKEBOX);
        final Jukebox jb = (Jukebox) p.getLocation().getBlock().getState();
        jb.setPlaying(Material.RECORD_11);

        MiscUtils.shootFirework(jb.getLocation().add(0.5,0,0.5), p.getWorld().getName());

        ScheduleUtils.scheduleTask(500, new Runnable() {
            @Override
            public void run() {
                jb.setPlaying(null);
                jb.getBlock().setType(Material.AIR);
            }
        });
    }

}