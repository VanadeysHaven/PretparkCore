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

import me.Cooltimmetje.PretparkCore.Managers.KledingManager;
import me.Cooltimmetje.PretparkCore.Utilities.ChatUtils;
import me.Cooltimmetje.PretparkCore.Utilities.ItemUtils;
import me.Cooltimmetje.PretparkCore.Utilities.Vars;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * This class has been created on 14-8-2015 at 20:00 by cooltimmetje.
 */
public class KledingUI implements Listener {

    public static void openKleding(Player p){
        Inventory inv = Bukkit.createInventory(null, 36, "Kleding Sets");

        int slot = 12;
        for(Material m : KledingManager.nameCat.keySet()){
            String name,lore;
            boolean hasPerm;
            hasPerm = p.hasPermission(KledingManager.permCat.get(m));

            StringBuilder nameSb = new StringBuilder();
            if(hasPerm){
                nameSb.append("&a");
            } else {
                nameSb.append("&c");
            }
            name = nameSb.append(KledingManager.nameCat.get(m)).toString().trim();

            String[] loreArray = KledingManager.loreCat.get(m);
            StringBuilder loreSb = new StringBuilder();
            loreSb.append(" \n");
            for (String aLoreArray : loreArray) {
                loreSb.append(aLoreArray).append("\n");
            }
            loreSb.append(" \n");

            if(hasPerm){
                loreSb.append("&aUNLOCKED");
            } else {
                loreSb.append("&cLOCKED");
            }

            lore = loreSb.toString().trim();

            if(hasPerm){
                ItemUtils.createChestDisplay(m, 1, 0, name, lore, inv, slot);
            } else {
                ItemUtils.createChestDisplay(Material.STAINED_GLASS_PANE, 1, 14, name, lore, inv, slot);
            }

            slot = slot + 1;
        }

        ItemUtils.createChestDisplay(Material.GLASS, 1, 0, "&cClear alle armor.", null, inv, 23);

        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(event.getInventory().getName().equals("Kleding Sets")){
            Player p = (Player) event.getWhoClicked();
            event.setCancelled(true);
            if(event.getCurrentItem().hasItemMeta()) {
                if(event.getCurrentItem().getType() != Material.GLASS){
                    openKit(p, event.getCurrentItem().getType());
                } else {
                    p.getInventory().setHelmet(new ItemStack(Material.AIR));
                    p.getInventory().setChestplate(new ItemStack(Material.AIR));
                    p.getInventory().setLeggings(new ItemStack(Material.AIR));
                    p.getInventory().setBoots(new ItemStack(Material.AIR));

                    Vars.helmet.put(p.getName(), Material.AIR);
                    Vars.chest.put(p.getName(), Material.AIR);
                    Vars.legs.put(p.getName(), Material.AIR);
                    Vars.boots.put(p.getName(), Material.AIR);

                    ChatUtils.sendMsgTag(p, "KledingKast", "Alle armor is gecleard!");
                    p.playSound(p.getLocation(), Sound.NOTE_BASS, 50, 1);
                }
            } else {
                return;
            }
        } else {
            return;
        }
    }

    private void openKit(Player p, Material m) {
        Inventory inv = Bukkit.createInventory(null, 36, "Kleding Kit");

        String type = m.toString().toLowerCase().replace("_chestplate", " ").trim();

        ItemUtils.createChestDisplay(Material.ARMOR_STAND, 1, 0, "&aKleding", "&3Kies hier wat je wilt dragen uit deze categorie.", inv, 12);
        ItemUtils.createChestDisplay(Material.getMaterial((type + "_helmet").toUpperCase()), 1, 0, "&aHelmet", "&3Klik om te equippen.", inv, 13);
        ItemUtils.createChestDisplay(Material.getMaterial((type + "_chestplate").toUpperCase()), 1, 0, "&aChestplate", "&3Klik om te equippen.", inv, 14);
        ItemUtils.createChestDisplay(Material.getMaterial((type + "_leggings").toUpperCase()), 1, 0, "&aLeggings", "&3Klik om te equippen.", inv, 15);
        ItemUtils.createChestDisplay(Material.getMaterial((type + "_boots").toUpperCase()), 1, 0, "&aBoots", "&3Klik om te equippen.", inv, 16);

        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClickLeather(InventoryClickEvent event){
        String invName = event.getInventory().getName();
        if(invName.equals("Kleding Kit")){
            Player p = (Player) event.getWhoClicked();
            event.setCancelled(true);
            if(event.getCurrentItem().hasItemMeta()) {
                Material m = event.getCurrentItem().getType();
                p.playSound(p.getLocation(), Sound.NOTE_PLING, 50, 1);
                if(m.toString().toLowerCase().contains("helmet")){
                    Vars.helmet.put(p.getName(), m);
                    p.getInventory().setHelmet(new ItemStack(m));
                } else if(m.toString().toLowerCase().contains("chestplate")){
                    Vars.chest.put(p.getName(), m);
                    p.getInventory().setChestplate(new ItemStack(m));
                } else if(m.toString().toLowerCase().contains("leggings")){
                    Vars.legs.put(p.getName(), m);
                    p.getInventory().setLeggings(new ItemStack(m));
                } else if(m.toString().toLowerCase().contains("boots")){
                    Vars.boots.put(p.getName(), m);
                    p.getInventory().setBoots(new ItemStack(m));
                }
            } else {
                return;
            }
        } else {
            return;
        }
    }

}
