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

package me.Cooltimmetje.PretparkCore.Utilities;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/**
 * This class has been created on 29-7-2015 at 15:01 by cooltimmetje.
 */
public class ItemUtils {

    public static void createInventoryDisplay(Player p, Material m, int amount, byte data, String name, String lore, int slot){
        ItemStack item = new ItemStack(m, amount, data);
        ItemMeta itemMeta = item.getItemMeta();
        if (name != null) {
            itemMeta.setDisplayName(MiscUtils.color(name));
        }
        if(lore != null){
            ArrayList<String> loreList = new ArrayList<>();
            String[] loreArray = lore.split("\n");
            for (String aLoreArray : loreArray) {
                loreList.add(MiscUtils.color(aLoreArray));
            }
            itemMeta.setLore(loreList);
        }
        item.setItemMeta(itemMeta);
        p.getInventory().setItem(slot - 1, item);
    }

    public static void createInventoryDisplay(Player p, ItemStack is, int slot){
        p.getInventory().setItem(slot - 1, is);
    }

    public static ItemStack createItemstack(Material m, int amount, byte data, String name, String lore){
        ItemStack item = new ItemStack(m, amount, data);
        ItemMeta itemMeta = item.getItemMeta();
        if (name != null) {
            itemMeta.setDisplayName(MiscUtils.color(name));
        }
        if(lore != null){
            ArrayList<String> loreList = new ArrayList<>();
            String[] loreArray = lore.split("\n");
            for (String aLoreArray : loreArray) {
                loreList.add(MiscUtils.color(aLoreArray));
            }
            itemMeta.setLore(loreList);
        }
        item.setItemMeta(itemMeta);
        return item;
    }

    public static void createChestDisplay(Material m, int amount, int data, String name, String lore, Inventory inv, int slot){
        ItemStack item = new ItemStack(m, amount,(byte) data);
        ItemMeta itemMeta = item.getItemMeta();
        if(!(name == null)){
            itemMeta.setDisplayName(MiscUtils.color(name));
        }
        if(!(lore == null)){
            ArrayList<String> Lore = new ArrayList<>();
            String[] loreArray = lore.split("\n");
            for (String aLoreArray : loreArray) {
                Lore.add(MiscUtils.color(aLoreArray));
            }
            itemMeta.setLore(Lore);
        }
        item.setItemMeta(itemMeta);
        inv.setItem(slot - 1, item);
    }

    public static void createChestDisplay(ItemStack is, Inventory inv, int slot){
        inv.setItem(slot - 1, is);
    }

    public static void test(){
        Inventory inv = Bukkit.createInventory(null, 9, "KoolInventory");

        createChestDisplay(Material.POTATO_ITEM, 1, 0, "KoolPotato", "This is a multi\nline item lore", inv, 1);
    }
}
