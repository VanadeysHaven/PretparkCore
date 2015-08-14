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

import org.bukkit.Material;

import java.util.HashMap;

/**
 * This class has been created on 14-8-2015 at 10:43 by cooltimmetje.
 */
public class GadgetManager {

    public static HashMap<Material, String> name = new HashMap<>();
    public static HashMap<Material, String[]> lore = new HashMap<>();
    public static HashMap<Material, Integer> cooldown = new HashMap<>();
    public static HashMap<Material, Integer> cost = new HashMap<>();
    public static HashMap<Material, String> permission = new HashMap<>();

    public static void createGadgets(){
        /* Firework */
        name.put(Material.FIREWORK_CHARGE, "Vuurwerk");
        lore.put(Material.FIREWORK_CHARGE, "&3Een leuk vuurwerkje, lekker simpel!\n&3&lWAT EEN MOOI DING!".split("\n"));
        cooldown.put(Material.FIREWORK_CHARGE, 15);
        cost.put(Material.FIREWORK_CHARGE, 100);
        permission.put(Material.FIREWORK_CHARGE, "pretparkcore.gadget.firework");

        /* Firework Ride */
        name.put(Material.FIREWORK, "Vuurwerk Ritje");
        lore.put(Material.FIREWORK, ("&3Het zelfde vuurwerkje, maar dan next level!\n&3Probeer het eens uit!\n \n&3&lLET OP!&3Om freerunnen te voorkomen word je na het\n&3gebruik van dit gadget na 5 seconden terug geteleporteerd naar\n&3waar je dit gadget gebruikt hebt!").split("\n"));
        cooldown.put(Material.FIREWORK, 30);
        cost.put(Material.FIREWORK, 200);
        permission.put(Material.FIREWORK, "pretparkcore.gadget.fireworkride");

        /* Staff Launcher */
        name.put(Material.PISTON_STICKY_BASE, "Staff Launcher");
        lore.put(Material.PISTON_STICKY_BASE, "&3Vind je het leuk om staff te pesten?\n&3Dan is dit echt iets voor jou!\n&3Rechtermuis klik met mij op \n&3een staff member en zie ze vliegen!".split("\n"));
        cooldown.put(Material.PISTON_STICKY_BASE, 60);
        cost.put(Material.PISTON_STICKY_BASE, 300);
        permission.put(Material.PISTON_STICKY_BASE, "pretparkcore.gadget.stafflaunch");
    }



}
