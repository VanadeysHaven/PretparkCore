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

import me.Cooltimmetje.PretparkCore.Utilities.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * This class has been created on 30-7-2015 at 19:35 by cooltimmetje.
 */
public class SwagUI implements Listener {

    public static void openUI(Player p){
        Inventory inv = Bukkit.createInventory(null, 27, "Swag Menu");

        ItemUtils.createChestDisplay(Material.PISTON_BASE, 1, 0, "&aGadgets", "&3De nieuwste snufjes in Minecraft Technoligie\n&3vind je hier! Probeer ze allemaal!", inv, 13);
        ItemUtils.createChestDisplay(Material.IRON_CHESTPLATE, 1, 0, "&aKleding Kast", "&3Opzoek naar wat &odraagbare swag&3?\n&3Kijk hier! We hebben voor ieder wat wils!", inv, 15);

        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(event.getInventory().getName().equals("Swag Menu")){
            Player p = (Player) event.getWhoClicked();
            event.setCancelled(true);
            if(event.getCurrentItem().hasItemMeta()) {
                switch (event.getCurrentItem().getType()){
                    default:
                        break;
                    case PISTON_BASE:
                        GadgetUI.openUI(p);
                        p.playSound(p.getLocation(), Sound.CLICK, 50, 1);
                        break;
                    case IRON_CHESTPLATE:
                        KledingUI.openKleding(p);
                        p.playSound(p.getLocation(), Sound.CLICK, 50, 1);
                }
            } else {
                return;
            }
        } else {
            return;
        }
    }

}
