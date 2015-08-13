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
                openGadget(p);
            }
        }
    }

    private void openGadget(Player p) {
        Inventory inv = Bukkit.createInventory(null, 54, "Gadget Shop");

        if(p.hasPermission("pretparkcore.gadget.firework")){
            ItemUtils.createChestDisplay(Material.FIREWORK_CHARGE, 1, 0, "&aVuurwerk", "&3COOLDOWN: &b15 seconden\n \n&3Een leuk vuurwerkje, lekker simpel! \n&3&lWAT EEN MOOI DING!\n " +
                    "\n&aUNLOCKED", inv, 1);
        } else {
            ItemUtils.createChestDisplay(Material.FIREWORK_CHARGE, 1, 0, "&cVuurwerk", "&3COOLDOWN: &b15 seconden\n \n&3Een leuk vuurwerkje, lekker simpel! " +
                    "\n&3&lWAT EEN MOOI DING!\n \n&cLOCKED &8\u00BB &aKlik om te kopen.\n" +
                    "&3Dit kost: &6" + FIREWORK_COST + " coins", inv, 1);
        }
        if(p.hasPermission("pretparkcore.gadget.fireworkride")){
            ItemUtils.createChestDisplay(Material.FIREWORK, 1, 0, "&aVuurwerk Ritje", "&3COOLDOWN: &b30 seconden\n \n&3Het zelfde vuurwerkje, maar dan next level! " +
                    "\n&3Probeer het eens uit!\n \n&3&lLET OP! &3Om freerunnen te voorkomen word je na het\n" +
                    "&3gebruik van dit gadget na 5 seconden terug geteleporteerd\n&3waar je dit gadget gebruikt hebt!\n \n&aUNLOCKED", inv, 2);
        } else {
            ItemUtils.createChestDisplay(Material.FIREWORK, 1, 0, "&cVuurwerk Ritje", "&3COOLDOWN: &b30 seconden\n \n&3Het zelfde vuurwerkje, maar dan next level! " +
                    "\n&3Probeer het eens uit!\n \n&3&lLET OP! &3Om freerunnen te voorkomen word je na het\n" +
                    "&3gebruik van dit gadget na 5 seconden terug geteleporteerd\n&3waar je dit gadget gebruikt hebt!\n \n&cLOCKED &8\u00BB &aKlik om te kopen. \n" +
                    "&3Dit kost: &6" + FIREWORKRIDE_COST + " coins", inv, 2);
        }
        if(p.hasPermission("pretparkcore.gadget.stafflaunch")){
            ItemUtils.createChestDisplay(Material.PISTON_STICKY_BASE, 1, 0, "&aStaff Launcher", "&3COOLDOWN: &b60 seconden\n \n&3Vind je het leuk om staff te pesten?" +
                    "\n&3Dan is dit echt iets voor jou!\n&3Rechtermuis klik met mij op \n&3een staff member en zie ze vliegen!\n \n&aUNLOCKED", inv, 3);
        } else {
            ItemUtils.createChestDisplay(Material.PISTON_STICKY_BASE, 1, 0, "&cStaff Launcher", "&3COOLDOWN: &b60 seconden\n \n&3Vind je het leuk om staff te pesten?" +
                    "\n&3Dan is dit echt iets voor jou!\n&3Rechtermuis klik met mij op \n&3een staff member en zie ze vliegen!\n \n&cLOCKED &8\u00BB &aKlik om te kopen.\n" +
                    "&3Dit kost: &6" + STAFFPUNCH_COST + " coins", inv, 3);
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
                switch (event.getCurrentItem().getType()) {
                    default:
                        break;
                    case FIREWORK_CHARGE:
                        if (p.hasPermission("pretparkcore.gadget.firework")) {
                            ChatUtils.sendMsgTag(p, "GadgetShop", ChatUtils.error + "Je hebt dit al gekocht!");
                        } else {
                            if (PlayerUtils.getCoins(p) >= FIREWORK_COST) {
                                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "manuaddp " + p.getName() + " pretparkcore.gadget.firework");
                                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mansave");
                                PlayerUtils.takeCoins(p, FIREWORK_COST, "je hebt een gadget gekocht!");
                                openGadget(p);
                            } else {
                                ChatUtils.sendMsgTag(p, "GadgetShop", ChatUtils.error + "Je hebt niet genoeg coins!");
                            }
                        }
                        break;
                    case FIREWORK:
                        if (p.hasPermission("pretparkcore.gadget.fireworkride")) {
                            ChatUtils.sendMsgTag(p, "GadgetShop", ChatUtils.error + "Je hebt dit al gekocht!");
                        } else {
                            if (PlayerUtils.getCoins(p) >= FIREWORKRIDE_COST) {
                                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "manuaddp " + p.getName() + " pretparkcore.gadget.firework");
                                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mansave");
                                PlayerUtils.takeCoins(p, FIREWORKRIDE_COST, "je hebt een gadget gekocht!");
                                openGadget(p);
                            } else {
                                ChatUtils.sendMsgTag(p, "GadgetShop", ChatUtils.error + "Je hebt niet genoeg coins!");
                            }
                        }
                        break;
                    case PISTON_STICKY_BASE:
                        if (p.hasPermission("pretparkcore.gadget.fireworkride")) {
                            ChatUtils.sendMsgTag(p, "GadgetShop", ChatUtils.error + "Je hebt dit al gekocht!");
                        } else {
                            if (PlayerUtils.getCoins(p) >= STAFFPUNCH_COST) {
                                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "manuaddp " + p.getName() + " pretparkcore.gadget.stafflaunch");
                                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mansave");
                                PlayerUtils.takeCoins(p, STAFFPUNCH_COST, "je hebt een gadget gekocht!");
                                openGadget(p);
                            } else {
                                ChatUtils.sendMsgTag(p, "GadgetShop", ChatUtils.error + "Je hebt niet genoeg coins!");
                            }
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
