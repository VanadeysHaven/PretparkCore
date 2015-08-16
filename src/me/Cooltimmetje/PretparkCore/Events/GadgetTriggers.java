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

import com.darkblade12.particleeffect.ParticleEffect;
import me.Cooltimmetje.PretparkCore.Managers.GadgetManager;
import me.Cooltimmetje.PretparkCore.Utilities.ChatUtils;
import me.Cooltimmetje.PretparkCore.Utilities.GadgetMethods;
import me.Cooltimmetje.PretparkCore.Utilities.MiscUtils;
import me.Cooltimmetje.PretparkCore.Utilities.Packets.TitleUtils;
import me.Cooltimmetje.PretparkCore.Utilities.ScheduleUtils;
import org.bukkit.*;
import org.bukkit.block.Jukebox;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;

/**
 * This class has been created on 30-7-2015 at 21:34 by cooltimmetje.
 */
public class GadgetTriggers implements Listener {

    static HashMap<String, Long> cdFirework = new HashMap<>();
    HashMap<String, Long> cdFireworkRide = new HashMap<>();
    static int cdFireworkSec = GadgetManager.cooldown.get(Material.FIREWORK_CHARGE);
    int cdFireworkRideSec = GadgetManager.cooldown.get(Material.FIREWORK);

    HashMap<String, Long> cdPunch = new HashMap<>();
    HashMap<String, Long> cdPunchStaff = new HashMap<>();
    int cdPunchSec = GadgetManager.cooldown.get(Material.PISTON_STICKY_BASE);

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
                            shootFirework(p);
                            break;
                        case FIREWORK:
                            if(event.getItem().getItemMeta().hasDisplayName()){
                                if(ChatColor.stripColor(event.getItem().getItemMeta().getDisplayName()).contains("Gadget")){
                                    event.setCancelled(true);
                                    shootFireworkRide(p);
                                }
                            }
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

    @EventHandler
    @SuppressWarnings("all")
    public void onRightClick(PlayerInteractEntityEvent event){
        if(!(event.getRightClicked() instanceof Minecart)){
            if(event.getPlayer().getItemInHand() != null){
                if(event.getPlayer().getItemInHand().getType() == Material.PISTON_STICKY_BASE){
                    if(event.getPlayer().getItemInHand().hasItemMeta()){
                        Player p = event.getPlayer();
                        if(event.getRightClicked() instanceof Player){
                            Player target = (Player) event.getRightClicked();
                            if(target.hasPermission("pretparkcore.staffbepunch") || target.isOp()){
                                if(!cdPunch.containsKey(p.getName()) || MiscUtils.cooldownCheck(cdPunch.get(p.getName()), cdPunchSec)){
                                    if(!cdPunchStaff.containsKey(target.getName()) || MiscUtils.cooldownCheck(cdPunchStaff.get(target.getName()), cdPunchSec)){
                                        ParticleEffect.EXPLOSION_LARGE.display(5, 5, 5, 1, 47, target.getLocation(), 16);
                                        Bukkit.getWorld(target.getWorld().getName()).playSound(target.getLocation(), Sound.EXPLODE, 20, 1);
                                        target.setFlying(false);
                                        target.setVelocity(new Vector(0, 3, 0));
                                        cdPunch.put(p.getName(), System.currentTimeMillis());
                                        cdPunchStaff.put(target.getName(), System.currentTimeMillis());
                                    } else {
                                        TitleUtils.sendActionTag(p, "StaffPunch", ChatUtils.error + "Je moet nog &c" + MiscUtils.formatTime(MiscUtils.getTimeRemaining(cdPunchStaff.get(p.getName()), cdPunchSec)) +
                                                " &awachten voordat&c" + target.getName() + "&aje weer kan punchen!");
                                    }
                                } else {
                                    TitleUtils.sendActionTag(p, "StaffPunch", ChatUtils.error + "Je moet nog &c" + MiscUtils.formatTime(MiscUtils.getTimeRemaining(cdPunch.get(p.getName()), cdPunchSec)) +
                                            " &awachten voordat je dit weer mag gebruiken.");
                                }
                            } else {
                                TitleUtils.sendActionTag(p, "StaffPunch", ChatUtils.error + "Dit is geen staff member!");
                            }
                        } else {
                            TitleUtils.sendActionTag(p, "StaffPunch", ChatUtils.error + "Dit is geen staff member!");
                        }
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        if(event.getPlayer().getItemInHand().getType() == Material.PISTON_STICKY_BASE){
            if(event.getPlayer().getItemInHand().hasItemMeta()){
                event.setCancelled(true);
            }
        }
    }

    private void shootFireworkRide(Player p) {
        if(!cdFireworkRide.containsKey(p.getName()) || MiscUtils.cooldownCheck(cdFireworkRide.get(p.getName()), cdFireworkRideSec)){
            final Location loc = p.getLocation();
            Firework fw = GadgetMethods.shootFirework(p.getLocation(), p.getWorld().getName());
            fw.setPassenger(p);
            TitleUtils.sendActionTag(p, "FireworkRide", "&lWHEEE!");
            final Player pfinal = p;
            cdFireworkRide.put(p.getName(), System.currentTimeMillis());
            ScheduleUtils.scheduleTask(100, new Runnable() {
                @Override
                public void run() {
                    pfinal.teleport(loc);
                }
            });
        } else {
            TitleUtils.sendActionTag(p, "FireworkRide", ChatUtils.error + "Je moet nog &c" + MiscUtils.formatTime(MiscUtils.getTimeRemaining(cdFireworkRide.get(p.getName()), cdFireworkRideSec)) +
                    " &awachten voordat je dit weer mag gebruiken.");
        }
    }

    public static void shootFirework(Player p) {
        if(!cdFirework.containsKey(p.getName()) || MiscUtils.cooldownCheck(cdFirework.get(p.getName()), cdFireworkSec)) {
            GadgetMethods.shootFirework(p.getLocation(), p.getWorld().getName());
            TitleUtils.sendActionTag(p, "Firework", "Je stak een vuurwerkje af! &lWAT EEN MOOI DING.");
            cdFirework.put(p.getName(), System.currentTimeMillis());
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "playsound custom.Vuurwerk " + p.getName() + " " + p.getLocation().getBlockX() + " " + p.getLocation().getBlockY() + " " + p.getLocation().getBlockZ());
        } else {
            TitleUtils.sendActionTag(p, "Firework", ChatUtils.error + "Je moet nog &c" + MiscUtils.formatTime(MiscUtils.getTimeRemaining(cdFirework.get(p.getName()), cdFireworkSec)) +
                    " &awachten voordat je dit weer mag gebruiken.");
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