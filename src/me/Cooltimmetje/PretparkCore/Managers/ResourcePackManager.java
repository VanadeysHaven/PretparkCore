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

package me.Cooltimmetje.PretparkCore.Managers;

import de.inventivegames.rpapi.ResourcePackStatusEvent;
import de.inventivegames.rpapi.Status;
import me.Cooltimmetje.PretparkCore.Main;
import me.Cooltimmetje.PretparkCore.Utilities.*;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

/**
 * This class has been created on 31-7-2015 at 17:52 by cooltimmetje.
 */
public class ResourcePackManager implements Listener {

    static HashMap<String, BukkitTask> tasks = new HashMap<>();

    public static void setRP(Player p) {

        if(PlayerUtils.getRP(p) != 0){
            sendRp(p);
        } else {
            openMenu(p);
        }

    }

    private static void openMenu(Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, "Kies een ResourcePack");

        ItemUtils.createChestDisplay(Material.COMMAND, 1, 0, "&a'&oBakbeest Pack&a'", " \n&3Kies deze als je een sterke computer hebt! \n \n&a\u00BB &33D Textures \n&c\u00BB &3Vereist sterke computer", inv, 12);
        ItemUtils.createChestDisplay(Material.POTATO_ITEM, 1, 0, "&a'&oPotato Pack&a'", " \n&3Kies deze als je een minder sterke computer hebt! \n \n&c\u00BB &3Geen 3D Textures \n&a\u00BB &3Geschikt voor minder sterke computers", inv, 16);

        p.openInventory(inv);

        startConfirmTimer(p);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event){
        if(ChatColor.stripColor(event.getInventory().getName()).equals("Kies een ResourcePack")){
            Player p = (Player) event.getWhoClicked();
            if(event.getCurrentItem() != null){
                if(event.getCurrentItem().hasItemMeta()){
                    tasks.get(p.getName()).cancel();
                    tasks.remove(p.getName());
                    switch(event.getCurrentItem().getType()){
                        default:
                            break;
                        case COMMAND:
                            p.closeInventory();
                            Vars.rp.put(p.getName(), 2);
                            sendRp(p);
                            break;
                        case POTATO_ITEM:
                            p.closeInventory();
                            Vars.rp.put(p.getName(), 1);
                            sendRp(p);
                            break;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onRPStatus(ResourcePackStatusEvent event){
        Player p = event.getPlayer();
        final Player pfinal = p;
        Status status = event.getStatus();

        if(tasks.containsKey(p.getName())){
            tasks.get(p.getName()).cancel();
        }

        if(status == Status.ACCEPTED){
            p.sendMessage("");
            ChatUtils.sendMsgTag(p, "ResourcePack", "Bevestiging ontvangen! De download start nu!");
            p.sendMessage("");
        } else if(status == Status.DECLINED){
            p.sendMessage("");
            ChatUtils.sendMsgTag(p, "ResourcePack", ChatUtils.error + "Je hebt de ResourcePack niet geaccepteerd, je moet dit doen om te kunnen spelen! " +
                    "Klik op de volgende link voor meer informatie: [TODO]");
            p.sendMessage("");
            ChatUtils.sendMsgTag(p, "ResourcePack", ChatUtils.error + "Je zal over 10 seconden gekickt worden!");
            p.sendMessage("");
            ScheduleUtils.scheduleTask(200, new Runnable() {
                @Override
                public void run() {
                    pfinal.kickPlayer(MiscUtils.color("&9ResourcePacks> &aJe hebt de ResourcePack niet geaccepteerd, je moet dit doen om te kunnen spelen! " +
                            "Ga naar de volgende link voor meer informatie: [TODO]"));
                }
            });
        } else if(status == Status.SUCCESSFULLY_LOADED) {
            for (int i = 0; i < 100; i++) {
                p.sendMessage("");
            }
            ChatManager.joinDisable.remove(p);
            if (!p.isOp()) {
                p.removePotionEffect(PotionEffectType.SLOW);
                p.removePotionEffect(PotionEffectType.JUMP);
                p.removePotionEffect(PotionEffectType.BLINDNESS);
            }
            ChatUtils.sendMsgTag(p, "ResourcePack", "Download geslaagd! Applausje voor je zelf! ^.^ &lVeel plezier!");
            ChatUtils.sendMsgTag(p, "Chat", "&lJe kunt nu chatten!");
            p.sendMessage("");
        } else if(status == Status.FAILED_DOWNLOAD){
            p.sendMessage("");
            ChatUtils.sendMsgTag(p, "ResourcePack", ChatUtils.error + "Oeps! Er ging iets mis, we gaan je over 10 seconden kicken, daarna kun je opnieuw joinen om het nog eens te proberen!");
            p.sendMessage("");
            ScheduleUtils.scheduleTask(200, new Runnable() {
                @Override
                public void run() {
                    pfinal.kickPlayer(MiscUtils.color("&9ResourcePacks> &aEr ging iets mis tijdens het downloaden van de resourcepack. Sorry! Join opnieuw om het nog eens te proberen!"));
                }
            });
        }
    }

    private static void sendRp(Player p) {
        ChatUtils.sendMsgTag(p, "ResourcePack", "ResourcePack versturen... Druk op &lJA &aals er om een bevestiging word gevraagd!");
        p.setResourcePack(Vars.rpLink.get(PlayerUtils.getRP(p)));

        startConfirmTimer(p);
    }

    private static void startConfirmTimer(final Player p) {
        BukkitTask task = Bukkit.getServer().getScheduler().runTaskLater(Main.getPlugin(), new Runnable() {
            @Override
            public void run() {
                p.kickPlayer(MiscUtils.color("&9ResourcePacks> &aWe hebben geen bevestiging ontvangen, probeer het nog eens! Of ga naar deze link voor meer informatie: [TODO]"));
            }
        }, 400);
        tasks.put(p.getName(), task);
    }
}
