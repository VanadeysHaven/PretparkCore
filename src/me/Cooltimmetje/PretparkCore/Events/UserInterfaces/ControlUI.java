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

package me.Cooltimmetje.PretparkCore.Events.UserInterfaces;

import me.Cooltimmetje.PretparkCore.RemoteControl.RideControl.BlackCobraControl;
import me.Cooltimmetje.PretparkCore.Utilities.ItemUtils;
import me.Cooltimmetje.PretparkCore.Utilities.MiscUtils;
import me.Cooltimmetje.PretparkCore.Utilities.Vars;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * This class has been created on 1-8-2015 at 18:31 by cooltimmetje.
 */
public class ControlUI implements Listener {

    public static void openUI(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9, "Attractie Besturing");

        ItemUtils.createChestDisplay(Material.MINECART, 1, 0, "&aBlack Cobra &8\u00BB " + MiscUtils.getStateString(Vars.rideStatus.get(1)), "&3Klik om te besturen.", inv, 1);

        p.openInventory(inv);
    }

    @EventHandler
    @SuppressWarnings("all")
    public void onInventoryClick(InventoryClickEvent event){
        if(event.getInventory().getName().equals("Attractie Besturing")){
            Player p = (Player) event.getWhoClicked();
            event.setCancelled(true);
            if(event.getCurrentItem().hasItemMeta()) {
                switch (event.getCurrentItem().getType()){
                    default:
                        break;
                    case MINECART:
                        if(event.getSlot() == 0){
                            p.playSound(p.getLocation(), Sound.CLICK, 50, 1);
                            BlackCobraControl.openUI(p);
                        }
                        break;
                }
            } else {
                return;
            }
        } else {
            return;
        }
    }

}
