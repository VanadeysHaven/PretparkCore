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

import me.Cooltimmetje.PretparkCore.Utilities.ItemUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * This class has been created on 29-7-2015 at 14:57 by cooltimmetje.
 */
public class InventoryManager implements Listener{

    public static void setInventory(Player p){
        p.getInventory().clear();

        ItemStack skull = ItemUtils.createItemstack(Material.SKULL_ITEM, 1, (byte)3, "&aJouw Profiel &3(Rechter Klik)", "&7&oTODO"); //TODO: MAKE LORE
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwner(p.getName());
        skull.setItemMeta(skullMeta);
        ItemUtils.createInventoryDisplay(p, skull, 1);

        ItemUtils.createInventoryDisplay(p, Material.MINECART, 1, (byte)0, "&aAttracties &3(Rechter Klik)", "&7&oTODO", 2); //TODO: MAKE LORE
        if(p.hasPermission("pretparkcore.controlrides")) {
            ItemUtils.createInventoryDisplay(p, Material.COMMAND_MINECART, 1, (byte) 0, "&aAttractie Besturing &3(Rechter Klik)", "&7&oTODO", 3); //TODO: MAKE LORE
        }
        ItemUtils.createInventoryDisplay(p, Material.CHEST, 1, (byte) 0, "&aSwag Menu &3(Rechter Klik)", "&7Upgrade je swag naar &lOVER 9000!", 9); //TODO: MAKE LORE
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        Player p = event.getPlayer();
        if(!p.hasPermission("pretparkcore.bypassgm") && !p.isOp()){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event){
        Player p = event.getPlayer();
        if(!p.hasPermission("pretparkcore.bypassgm") && !p.isOp()){
            event.setCancelled(true);
        }
    }

}
