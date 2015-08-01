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

package me.Cooltimmetje.PretparkCore.RemoteControl.RideControl;

import me.Cooltimmetje.PretparkCore.RemoteControl.SignLinkEvent;
import me.Cooltimmetje.PretparkCore.Utilities.ItemUtils;
import me.Cooltimmetje.PretparkCore.Utilities.MiscUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

/**
 * This class has been created on 1-8-2015 at 10:37 by cooltimmetje.
 */
public class BlackCobraControl implements Listener {

    // ? = \u2713

    private static Inventory inv = Bukkit.createInventory(null, 18, "Black Cobra");

    public static void openUI(Player p){
       p.openInventory(inv);
    }

    public static void setupRide() {
        setItems();
    }

    public static void updateRide() {
        setItems();
    }

    private static void setItems() {
        ItemUtils.createChestDisplay(Material.RAILS, 1, 0, "&aVrijgeven &8\u00BB " + SignLinkEvent.variableValues.get("bcstart"), null, inv, 2);
        ItemUtils.createChestDisplay(Material.STICK, 1, 0, "&aBeugels &8\u00BB " + SignLinkEvent.variableValues.get("bcbeugels"), null, inv, 3);
        ItemUtils.createChestDisplay(Material.FENCE_GATE, 1, 0, "&aPoortjes &8\u00BB " + SignLinkEvent.variableValues.get("bcpoortjes"), null, inv, 4);
        ItemUtils.createChestDisplay(Material.MINECART, 1, 0, "&aInrangeren &8\u00BB " + SignLinkEvent.variableValues.get("bcinrang"), null, inv, 6);
        ItemUtils.createChestDisplay(Material.TNT, 1, 0, "&aUitrangeren &8\u00BB " + SignLinkEvent.variableValues.get("bctrein"), null, inv, 7);
        ItemUtils.createChestDisplay(Material.SKULL_ITEM, 1, 3, "&aAttractie &8\u00BB " + SignLinkEvent.variableValues.get("blackcobra"), null, inv, 8);

        if(ChatColor.stripColor(MiscUtils.getVar("bcstart")).equalsIgnoreCase("Toegestaan")) {
            ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, 5, "&aVrijgeven &8\u00BB " + SignLinkEvent.variableValues.get("bcstart"), "&2\u2713 &3Klik om te activeren.", inv, 11);
        } else {
            ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, 14, "&aVrijgeven &8\u00BB " + SignLinkEvent.variableValues.get("bcstart"), "&3Niet beschikbaar.", inv, 11);
        }
        if(ChatColor.stripColor(MiscUtils.getVar("bcbeugels")).equalsIgnoreCase("Open")) {
            ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, 5, "&aBeugels &8\u00BB " + SignLinkEvent.variableValues.get("bcbeugels"), "&2\u2713 &3Klik om te togglen.", inv, 12);
        } else {
            ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, 14, "&aBeugels &8\u00BB " + SignLinkEvent.variableValues.get("bcbeugels"), "&2\u2713 &3Klik om te togglen.", inv, 12);
        }
        if(ChatColor.stripColor(MiscUtils.getVar("bcpoortjes")).equalsIgnoreCase("Open")) {
            ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, 5, "&aPoortjes &8\u00BB " + SignLinkEvent.variableValues.get("bcpoortjes"), "&2\u2713 &3Klik om te togglen.", inv, 13);
        } else {
            ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, 14, "&aPoortjes &8\u00BB " + SignLinkEvent.variableValues.get("bcpoortjes"), "&2\u2713 &3Klik om te togglen.", inv, 13);
        }
        if(ChatColor.stripColor(MiscUtils.getVar("bcinrang")).equalsIgnoreCase("Toegestaan")){
            ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, 5, "&aInrangeren &8\u00BB " + SignLinkEvent.variableValues.get("bcinrang"), "&2\u2713 &3Klik om te activeren.", inv, 15);
        } else {
            ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, 14, "&aInrangeren &8\u00BB " + SignLinkEvent.variableValues.get("bcinrang"), "&3Niet beschikbaar.", inv, 15);
        }
        if(ChatColor.stripColor(MiscUtils.getVar("bctrein")).equalsIgnoreCase("geen trein")){
            ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, 14, "&aUitrangeren &8\u00BB " + SignLinkEvent.variableValues.get("bctrein"), "&3Niet beschikbaar.", inv, 16);
        } else {
            ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, 5, "&aUitrangeren &8\u00BB " + SignLinkEvent.variableValues.get("bctrein"), "&2\u2713 &3Klik om te activeren.", inv, 16);
        }
        if(ChatColor.stripColor(MiscUtils.getVar("blackcobra")).equalsIgnoreCase("open")){
            ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, 5, "&aAttractie &8\u00BB " + SignLinkEvent.variableValues.get("blackcobra"), "&2\u2713 &3Klik om te togglen.", inv, 17);
        } else {
            ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, 14, "&aAttractie &8\u00BB " + SignLinkEvent.variableValues.get("blackcobra"), "&2\u2713 &3Klik om te togglen.", inv, 17);
        }
    }

    @EventHandler
    public void onSignClick(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(event.getClickedBlock().getType() == Material.WALL_SIGN){
                Sign sign = (Sign) event.getClickedBlock().getState();
                if(p.hasPermission("pretparkcore.controlrides")) {
                    if (sign.getLine(1).equalsIgnoreCase("[AttractieMenu]")) {
                        if (ChatColor.stripColor(sign.getLine(2)).equalsIgnoreCase("Black Cobra")) {
                            openUI(p);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public static void onInventoryClick(InventoryClickEvent event){
        Player p = (Player) event.getWhoClicked();
        if(event.getClickedInventory().getName().equals("Black Cobra")){
            if(event.getCurrentItem() != null){
                event.setCancelled(true);
                switch (event.getSlot()){
                    default:
                        break;
                    case 10:
                        if(event.getCurrentItem().getDurability() == 5){
                            p.playSound(p.getLocation(), Sound.CLICK, 50, 1);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "rsc bcstart");
                        } else {
                            p.playSound(p.getLocation(), Sound.ITEM_BREAK, 50, 1);
                        }
                        break;
                    case 11:
                        p.playSound(p.getLocation(), Sound.CLICK, 50, 1);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "rsc bcbeugels");
                        break;
                    case 12:
                        p.playSound(p.getLocation(), Sound.CLICK, 50, 1);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "rsc bcpoortjes");
                        break;
                    case 14:
                        if(event.getCurrentItem().getDurability() == 5){
                            p.playSound(p.getLocation(), Sound.CLICK, 50, 1);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "rsc bcinrang");
                        } else {
                            p.playSound(p.getLocation(), Sound.ITEM_BREAK, 50, 1);
                        }
                        break;
                    case 15:
                        if(event.getCurrentItem().getDurability() == 5){
                            p.playSound(p.getLocation(), Sound.CLICK, 50, 1);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "rsc bcuitrang");
                        } else {
                            p.playSound(p.getLocation(), Sound.ITEM_BREAK, 50, 1);
                        }
                        break;
                    case 16:
                        p.playSound(p.getLocation(), Sound.CLICK, 50, 1);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "rsc blackcobra");
                        break;
                }
            }
        }
    }


}
