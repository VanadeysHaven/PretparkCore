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

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.Cooltimmetje.PretparkCore.Main;
import me.Cooltimmetje.PretparkCore.Utilities.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

/**
 * This class has been created on 11-8-2015 at 14:19 by cooltimmetje.
 */
public class NpcManager implements Listener {

    static String world = "world";
    static Entity gadgetEntity;

    static int FIREWORK_COST = 100;
    static int FIREWORKRIDE_COST = 200;
    static int STAFFPUNCH_COST = 300;

    static ArrayList<Entity> entitys = new ArrayList<>();
    static ArrayList<Hologram> holograms = new ArrayList<>();

    public static void spawnNPCs(){
        Entity pet = Bukkit.getWorld(world).spawnEntity(new Location(Bukkit.getWorld(world), -1249, 55, 242, -120, 0).add(0.5, 0, 0.5), EntityType.VILLAGER);
        EntityUtils.noAI(pet);
        entitys.add(pet);

        Hologram petHolo = HologramsAPI.createHologram(Main.getPlugin(), pet.getLocation().add(0, 2.6, 0));
        petHolo.appendTextLine(MiscUtils.color("&bPet Shop"));
        petHolo.appendTextLine(MiscUtils.color("&7&lSoonTM"));
        holograms.add(petHolo);

        Entity merchant = Bukkit.getWorld(world).spawnEntity(new Location(Bukkit.getWorld(world), -1249, 55, 240, -90, 0).add(0.5, 0, 0.5), EntityType.VILLAGER);
        EntityUtils.noAI(merchant);
        entitys.add(merchant);

        Hologram merchantHolo = HologramsAPI.createHologram(Main.getPlugin(), merchant.getLocation().add(0, 2.6, 0));
        merchantHolo.appendTextLine(MiscUtils.color("&bKleding Shop"));
        merchantHolo.appendTextLine(MiscUtils.color("&7&lSoonTM"));
        holograms.add(merchantHolo);

        Entity gadget = Bukkit.getWorld(world).spawnEntity(new Location(Bukkit.getWorld(world), -1249, 55, 238, -50, 0).add(0.5,0,0.5), EntityType.VILLAGER);
        gadgetEntity = gadget;
        EntityUtils.noAI(gadget);
        entitys.add(gadget);

        Hologram gadgetHolo = HologramsAPI.createHologram(Main.getPlugin(), gadget.getLocation().add(0, 2.6, 0));
        gadgetHolo.appendTextLine(MiscUtils.color("&bGadget Shop"));
        gadgetHolo.appendTextLine(MiscUtils.color("&a&lRIGHT CLICK"));
        holograms.add(gadgetHolo);
    }

    public static void removeNPCs(){
        for(Hologram hologram : holograms){
            hologram.delete();
        }
        for(Entity entity : entitys){
            entity.remove();
        }
    }

    @EventHandler
    public void onEntityRightClick(PlayerInteractEntityEvent event){
        Player p = event.getPlayer();
        Entity e = event.getRightClicked();
        if((e instanceof Villager) && !(e instanceof Minecart)){
            if(e == gadgetEntity){
                event.setCancelled(true);
                openGadget(p);
            }
        }
    }

    private void openGadget(Player p) {
        Inventory inv = Bukkit.createInventory(null, 54, "Gadget Shop");

        int slot = 1;
        for(Material m : GadgetManager.name.keySet()){
            String name,lore;
            boolean hasPerm = p.hasPermission(GadgetManager.permission.get(m));

            StringBuilder nameSb = new StringBuilder();
            if(hasPerm){
                nameSb.append("&a");
            } else {
                nameSb.append("&c");
            }
            name = nameSb.append(GadgetManager.name.get(m)).toString().trim();

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
                loreSb.append("&cLOCKED &8\u00BB &aKlik om te kopen.");
                loreSb.append("&3Dit kost: &6").append(GadgetManager.cost.get(m));
            }
            lore = loreSb.toString().trim();

            ItemUtils.createChestDisplay(m, 1, 0, name, lore, inv, slot);
            slot = slot + 1;
        }

        p.openInventory(inv);
    }

    @EventHandler
    @SuppressWarnings("all")
    public void onInventoryClick(InventoryClickEvent event){
        if(event.getInventory().getName().equals("Gadget Shop")){
            Player p = (Player) event.getWhoClicked();
            event.setCancelled(true);
            if(event.getCurrentItem().hasItemMeta()) {
                Material m = event.getCurrentItem().getType();
                if (p.hasPermission(GadgetManager.permission.get(m))) {
                    ChatUtils.sendMsgTag(p, "GadgetShop", ChatUtils.error + "Je hebt dit al gekocht!");
                } else {
                    if (PlayerUtils.getCoins(p) >= GadgetManager.cost.get(m)) {
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "manuaddp " + p.getName() + " " + GadgetManager.permission.get(m));
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mansave");
                        PlayerUtils.takeCoins(p, GadgetManager.cost.get(m), "je hebt een gadget gekocht!");
                        openGadget(p);
                    } else {
                        ChatUtils.sendMsgTag(p, "GadgetShop", ChatUtils.error + "Je hebt niet genoeg coins!");
                    }
                }
            } else {
                return;
            }
        } else {
            return;
        }
    }


}
