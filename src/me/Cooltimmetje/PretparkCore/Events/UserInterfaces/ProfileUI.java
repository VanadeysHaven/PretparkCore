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
import me.Cooltimmetje.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * This class has been created on 29-7-2015 at 15:53 by cooltimmetje.
 */
public class ProfileUI implements Listener {

    public static void openUI(Player p) {
        Inventory inv = Bukkit.createInventory(null, 18, "Jouw Profiel");

        ItemStack skull = ItemUtils.createItemstack(Material.SKULL_ITEM, 1, (byte) 3, "&eProfiel van " + p.getDisplayName(), null);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwner(p.getName());
        skull.setItemMeta(skullMeta);
        ItemUtils.createChestDisplay(skull, inv, 5);

        ItemUtils.createChestDisplay(Material.GOLD_NUGGET, 1, (byte) 0, "&eJe hebt momenteel &6" + PlayerUtils.getCoins(p) + " coins&a!",
                "&7Wees online om meer te verdienen!", inv, 13);




        ItemUtils.createChestDisplay(Material.ARROW, 1, (byte) 0, "&cSluiten", null, inv, 10);

        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(event.getInventory().getName().equals("Jouw Profiel")){
            Player p = (Player) event.getWhoClicked();
            event.setCancelled(true);
            if(event.getCurrentItem().hasItemMeta()) {
                switch (event.getCurrentItem().getType()){
                    default:
                        break;
                    case ARROW:
                        p.closeInventory();
                        p.playSound(p.getLocation(), Sound.CLICK, 50, 1);
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