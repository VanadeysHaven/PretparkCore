package me.Cooltimmetje.PretparkCore.Utilities;


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

}
