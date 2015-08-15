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
 * This class has been created on 14-8-2015 at 20:01 by cooltimmetje.
 */
public class KledingManager {

    public static HashMap<Material, String> nameCat = new HashMap<>();
    public static HashMap<Material, String[]> loreCat = new HashMap<>();
    public static HashMap<Material, Integer> costCat = new HashMap<>();
    public static HashMap<Material, Integer> enchantCostCat = new HashMap<>();
    public static HashMap<Material, String> permCat = new HashMap<>();
    public static HashMap<Material, String> enchantPermCat = new HashMap<>();

    public static void setCategories(){

        nameCat.put(Material.LEATHER_CHESTPLATE, "Leahter");
        loreCat.put(Material.LEATHER_CHESTPLATE, "&3Geen paniek! Dit is geen echt leer. Maar je kunt het toch \n&3dragen met swag, omdat je het ook kan kleuren!".split("\n"));
        costCat.put(Material.LEATHER_CHESTPLATE, 100);
        enchantCostCat.put(Material.LEATHER_CHESTPLATE, 100);
        permCat.put(Material.LEATHER_CHESTPLATE, "pretparkcore.kleding.leather");
        enchantPermCat.put(Material.LEATHER_CHESTPLATE, "pretparkcore.enchant.leather");

        nameCat.put(Material.CHAINMAIL_CHESTPLATE, "Chain");
        loreCat.put(Material.CHAINMAIL_CHESTPLATE, "&3Sterk, maar flexibel, en toch swaggy! Wat wil je nog meer?".split("\n"));
        costCat.put(Material.CHAINMAIL_CHESTPLATE, 200);
        enchantCostCat.put(Material.CHAINMAIL_CHESTPLATE, 200);
        permCat.put(Material.CHAINMAIL_CHESTPLATE, "pretparkcore.kleding.chain");
        enchantPermCat.put(Material.CHAINMAIL_CHESTPLATE, "pretparkcore.enchant.chain");

        nameCat.put(Material.IRON_CHESTPLATE, "Iron");
        loreCat.put(Material.IRON_CHESTPLATE, "&3Een beetje zoals chain, maar dan sterker en minder flexibel,\n&3maar toch hebben ze een ding gemeen: &lSWAG!".split("\n"));
        costCat.put(Material.IRON_CHESTPLATE, 300);
        enchantCostCat.put(Material.IRON_CHESTPLATE, 300);
        permCat.put(Material.IRON_CHESTPLATE, "pretparkcore.kleding.iron");
        enchantPermCat.put(Material.IRON_CHESTPLATE, "pretparkcore.enchant.iron");

        nameCat.put(Material.GOLD_CHESTPLATE, "Gold");
        loreCat.put(Material.GOLD_CHESTPLATE, "&3SHINEEEY!!!!! GOOOOOOOOOOOOOOOOOOOOOLDDD! &lSWAG".split("\n"));
        costCat.put(Material.GOLD_CHESTPLATE, 400);
        enchantCostCat.put(Material.GOLD_CHESTPLATE, 400);
        permCat.put(Material.GOLD_CHESTPLATE, "pretparkcore.kleding.gold");
        enchantPermCat.put(Material.GOLD_CHESTPLATE, "pretparkcore.enchant.gold");

        nameCat.put(Material.DIAMOND_CHESTPLATE, "Diamond");
        loreCat.put(Material.DIAMOND_CHESTPLATE, "&3Lekker duur en lekker veel swag!".split("\n"));
        costCat.put(Material.DIAMOND_CHESTPLATE, 500);
        enchantCostCat.put(Material.DIAMOND_CHESTPLATE, 500);
        permCat.put(Material.DIAMOND_CHESTPLATE, "pretparkcore.kleding.diamond");
        enchantPermCat.put(Material.DIAMOND_CHESTPLATE, "pretparkcore.enchant.diamond");

    }

}
