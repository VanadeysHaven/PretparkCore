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
 * This class has been created on 30-7-2015 at 21:17 by cooltimmetje.
 */
public class GadgetUI implements Listener {

    public static void openUI(Player p){
        Inventory inv = Bukkit.createInventory(null, 54, "Gadgets");

        ItemUtils.createChestDisplay(Material.FIREWORK_CHARGE, 1, 0, "&aVuurwerk", "&3COOLDOWN: &b15 seconden\n \n&3Een leuk vuurwerkje, lekker simpel! \n&3&lWAT EEN MOOI DING!", inv, 1);
        ItemUtils.createChestDisplay(Material.FIREWORK, 1, 0, "&aVuurwerk Ritje", "&3COOLDOWN: &b30 seconden\n \n&3Het zelfde vuurwerkje, maar dan next level! " +
                "\n&3Probeer het eens uit!'\n \n&3&lLET OP! &3Om freerunnen te voorkomen word je na het\n" +
                "&3gebruik van dit gadget na 5 seconden terug geteleporteerd\n&3waar je dit gadget gebruikt hebt!", inv, 2);
//        ItemUtils.createChestDisplay(Material.RECORD_11, 1, 0, "&a'Very Grown Up' muziek", " \n&3Heeft geen uitleg nodig, &lJUST HIT AND GO!", inv, 3);

        p.openInventory(inv);
    }

    @EventHandler
    @SuppressWarnings("all")
    public void onInventoryClick(InventoryClickEvent event){
        if(event.getInventory().getName().equals("Gadgets")){
            Player p = (Player) event.getWhoClicked();
            event.setCancelled(true);
            if(event.getCurrentItem().hasItemMeta()) {
                switch (event.getCurrentItem().getType()){
                    default:
                        break;
                    case FIREWORK_CHARGE:
                        p.closeInventory();
                        p.getInventory().setHeldItemSlot(7);
                        p.playSound(p.getLocation(), Sound.NOTE_PLING, 50, 1);
                        ItemUtils.createInventoryDisplay(p, Material.FIREWORK_CHARGE, 1, (byte)0, "&aGadget &8\u00BB &aVuurwerk &3(Rechter Klik)",
                                "&3COOLDOWN: &b15 seconden\n \n&3Een leuk vuurwerkje, lekker simpel! \n&3&lWAT EEN MOOI DING!", 8);
                        break;
                    case FIREWORK:
                        p.closeInventory();
                        p.getInventory().setHeldItemSlot(7);
                        p.playSound(p.getLocation(), Sound.NOTE_PLING, 50, 1);
                        ItemUtils.createInventoryDisplay(p, Material.FIREWORK, 1, (byte) 0, "&aGadget &8\u00BB &aVuurwerk Ritje &3(Rechter Klik)",
                                "&3COOLDOWN: &b30 seconden\n \n&3Het zelfde vuurwerkje, maar dan next level! \n&3Probeer het eens uit!\n \n&3&lLET OP! &3Om freerunnen te voorkomen word je na het\n" +
                                        "&3gebruik van dit gadget na 5 seconden terug geteleporteerd\n&3waar je dit gadget gebruikt hebt!", 8);
                        break;
                    case RECORD_11:
                        p.closeInventory();
                        p.getInventory().setHeldItemSlot(7);
                        p.playSound(p.getLocation(), Sound.NOTE_PLING, 50, 1);
                        ItemUtils.createInventoryDisplay(p, Material.RECORD_11, 1,(byte) 0, "&a'Very Grown Up' muziek", " \n&3Heeft geen uitleg nodig, &lJUST HIT AND GO!", 8);
                }
            } else {
                return;
            }
        } else {
            return;
        }
    }


}
