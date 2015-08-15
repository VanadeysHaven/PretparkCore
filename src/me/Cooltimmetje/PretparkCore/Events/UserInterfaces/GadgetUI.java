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

import me.Cooltimmetje.PretparkCore.Managers.GadgetManager;
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
 * This class has been created on 30-7-2015 at 21:17 by cooltimmetje.
 */
public class GadgetUI implements Listener {

    public static void openUI(Player p){
        Inventory inv = Bukkit.createInventory(null, 54, "Gadgets");

        int slot = 1;
        for(Material m : GadgetManager.name.keySet()){
            boolean hasPerm = p.hasPermission(GadgetManager.permission.get(m));
            StringBuilder nameSb = new StringBuilder();
            if(hasPerm) {
                nameSb.append("&a");
            } else {
                nameSb.append("&c");
            }
            nameSb.append(GadgetManager.name.get(m));
            String name = nameSb.toString().trim();

            String[] loreArray = GadgetManager.lore.get(m);
            StringBuilder loreSb = new StringBuilder();
            loreSb.append("&3COOLDOWN: &b").append(GadgetManager.cooldown.get(m)).append(" seconden \n \n");
            for (String aLoreArray : loreArray) {
                loreSb.append(aLoreArray).append("\n");
            }
            loreSb.append(" \n");
            if(hasPerm){
                loreSb.append("&aUNLOCKED");
            } else {
                loreSb.append("&cLOCKED");
            }
            String lore = loreSb.toString().trim();

            if(hasPerm) {
                ItemUtils.createChestDisplay(m, 1, 0, name, lore, inv, slot);
            } else {
                ItemUtils.createChestDisplay(Material.STAINED_GLASS_PANE, 1, 14, name, lore, inv, slot);
            }
            slot = slot + 1;
        }

        p.openInventory(inv);
    }

    @EventHandler
    @SuppressWarnings("all")
    public void onInventoryClick(InventoryClickEvent event){
        if(event.getInventory().getName().equals("Gadgets")){
            Player p = (Player) event.getWhoClicked();
            event.setCancelled(true);
            if(event.getCurrentItem().hasItemMeta()) {
                Material m = event.getCurrentItem().getType();

                StringBuilder nameSb = new StringBuilder();
                String name = nameSb.append("&aGadget &8\u00BB &a").append(GadgetManager.name.get(m)).append(" &3(Rechter Klik)").toString().trim();

                String[] loreArray = GadgetManager.lore.get(m);
                StringBuilder loreSb = new StringBuilder();
                loreSb.append("&3COOLDOWN: &b").append(GadgetManager.cooldown.get(m)).append(" seconden \n \n");
                for(int i = 0; i < loreArray.length; i++){
                    loreSb.append(loreArray[i]).append("\n");
                }
                String lore = loreSb.toString().trim();

                ItemUtils.createInventoryDisplay(p, m, 1, (byte) 0, name, lore, 8);
                p.playSound(p.getLocation(), Sound.NOTE_PLING, 50, 1);
                p.getInventory().setHeldItemSlot(7);
                p.closeInventory();
            } else {
                return;
            }
        } else {
            return;
        }
    }


}
