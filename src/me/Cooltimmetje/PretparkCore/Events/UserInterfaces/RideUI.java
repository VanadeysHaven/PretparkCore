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

import me.Cooltimmetje.PretparkCore.Utilities.ChatUtils;
import me.Cooltimmetje.PretparkCore.Utilities.ItemUtils;
import me.Cooltimmetje.PretparkCore.Utilities.MiscUtils;
import me.Cooltimmetje.PretparkCore.Utilities.Vars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * This class has been created on 30-7-2015 at 09:52 by cooltimmetje.
 */
public class RideUI implements Listener {

    public static void openUI(Player p){
        Inventory inv = Bukkit.createInventory(null, 54, "Attracties");

        int slot = 1;
        for(int i : Vars.rideName.keySet()){
            String status = MiscUtils.getStateString(Vars.rideStatus.get(i));
            int color = MiscUtils.getStateClay(Vars.rideStatus.get(i));

            ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) color, "&a" + Vars.rideName.get(i) + " &8\u00BB " + status, "&aKlik om te warpen!\n&8ID: " + i, inv, slot);
            slot = slot + 1;
        }


        ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) 0, "&aZet filter uit. &7&o(Geselecteerd)", "&aKlik om de filter uit te zetten.", inv, 51);
        ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) 5, "&aFilter op: &2open", "&aKlik om te filteren.", inv, 52);
        ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) 14, "&aFilter op: &cdicht", "&aKlik om te filteren.", inv, 53);
        ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) 1, "&aFilter op: &6onderhoud", "&aKlik om te filteren.", inv, 54);

        p.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(event.getInventory().getName().equals("Attracties")){
            Player p = (Player) event.getWhoClicked();
            event.setCancelled(true);
            if(event.getCurrentItem().hasItemMeta()) {
                switch (event.getCurrentItem().getType()){
                    default:
                        break;
                    case STAINED_CLAY:
                        if(event.getSlot() == 50 || event.getSlot() == 51 || event.getSlot() == 52 || event.getSlot() == 53){
                            switch (event.getSlot()){
                                case 50:
                                    resetFilter(event.getInventory());
                                    break;
                                case 51:
                                    filterOpen(event.getInventory());
                                    break;
                                case 52:
                                    filterClose(event.getInventory());
                                    break;
                                case 53:
                                    filterMaintenance(event.getInventory());
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            p.closeInventory();
                            int id = Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(1).replace("ID: ", " ")).trim());
                            Location loc = Vars.rideLocation.get(id);
                            String name = Vars.rideName.get(id);
                            p.teleport(loc);
                            p.teleport(p.getLocation().add(0.5, 0, 0.5));
                            ChatUtils.sendMsgTag(p, "Teleport", "Je bent geteleporteerd naar de &6" + name + "&a!");
                            break;
                        }
                }
            } else {
                return;
            }
        } else {
            return;
        }
    }

    private void resetFilter(Inventory inv) {
        int slot = 1;
        for(int i : Vars.rideName.keySet()){
            String status = MiscUtils.getStateString(Vars.rideStatus.get(i));
            int color = MiscUtils.getStateClay(Vars.rideStatus.get(i));

            ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) color, "&a" + Vars.rideName.get(i) + " &8\u00BB " + status, "&aKlik om te warpen!\n&8ID: " + i, inv, slot);
            slot = slot + 1;
        }


        ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) 0, "&aZet filter uit. &7&o(Geselecteerd)", "&aKlik om de filter uit te zetten.", inv, 51);
        ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) 5, "&aFilter op: &2open", "&aKlik om te filteren.", inv, 52);
        ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) 14, "&aFilter op: &cdicht", "&aKlik om te filteren.", inv, 53);
        ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) 1, "&aFilter op: &6onderhoud", "&aKlik om te filteren.", inv, 54);
    }

    private void filterMaintenance(Inventory inv) {
        inv.clear();

        int slot = 1;
        for(int i : Vars.rideName.keySet()){
            String status = MiscUtils.getStateString(Vars.rideStatus.get(i));
            int color = MiscUtils.getStateClay(Vars.rideStatus.get(i));
            if(color == 1) {
                ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) color, "&a" + Vars.rideName.get(i) + " &8\u00BB " + status, "&aKlik om te warpen!\n&8ID: " + i, inv, slot);
                slot = slot + 1;
            }
        }

        ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) 0, "&aZet filter uit.", "&aKlik om de filter uit te zetten", inv, 51);
        ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) 5, "&aFilter op: &2open", "&aKlik om te filteren.", inv, 52);
        ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) 14, "&aFilter op: &cdicht", "&aKlik om te filteren.", inv, 53);
        ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) 1, "&aFilter op: &6onderhoud &7&o(Geselecteerd)", "&aKlik om te filteren.", inv, 54);
    }

    private void filterClose(Inventory inv) {
        inv.clear();

        int slot = 1;
        for(int i : Vars.rideName.keySet()){
            String status = MiscUtils.getStateString(Vars.rideStatus.get(i));
            int color = MiscUtils.getStateClay(Vars.rideStatus.get(i));
            if(color == 14) {
                ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) color, "&a" + Vars.rideName.get(i) + " &8\u00BB " + status, "&aKlik om te warpen!\n&8ID: " + i, inv, slot);
                slot = slot + 1;
            }
        }

        ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) 0, "&aZet filter uit.", "&aKlik om de filter uit te zetten", inv, 51);
        ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) 5, "&aFilter op: &2open", "&aKlik om te filteren.", inv, 52);
        ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) 14, "&aFilter op: &cdicht &7&o(Geselecteerd)", "&aKlik om te filteren.", inv, 53);
        ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) 1, "&aFilter op: &6onderhoud", "&aKlik om te filteren.", inv, 54);
    }

    private void filterOpen(Inventory inv) {
        inv.clear();

        int slot = 1;
        for(int i : Vars.rideName.keySet()){
            String status = MiscUtils.getStateString(Vars.rideStatus.get(i));
            int color = MiscUtils.getStateClay(Vars.rideStatus.get(i));
            if(color == 5) {
                ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) color, "&a" + Vars.rideName.get(i) + " &8\u00BB " + status, "&aKlik om te warpen!\n&8ID: " + i, inv, slot);
                slot = slot + 1;
            }
        }

        ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) 0, "&aZet filter uit.", "&aKlik om de filter uit te zetten", inv, 51);
        ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) 5, "&aFilter op: &2open &7&o(Geselecteerd)", "&aKlik om te filteren.", inv, 52);
        ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) 14, "&aFilter op: &cdicht", "&aKlik om te filteren.", inv, 53);
        ItemUtils.createChestDisplay(Material.STAINED_CLAY, 1, (byte) 1, "&aFilter op: &6onderhoud", "&aKlik om te filteren.", inv, 54);

    }

}
