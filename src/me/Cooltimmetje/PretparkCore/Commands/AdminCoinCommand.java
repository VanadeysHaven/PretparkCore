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

package me.Cooltimmetje.PretparkCore.Commands;

import me.Cooltimmetje.PretparkCore.Main;
import me.Cooltimmetje.PretparkCore.Utilities.ChatUtils;
import me.Cooltimmetje.PretparkCore.Utilities.MiscUtils;
import me.Cooltimmetje.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 29-7-2015 at 18:46 by cooltimmetje.
 */
public class AdminCoinCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("givecoins")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.isOp()) {
                    if (args.length >= 2) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            if (MiscUtils.isInt(args[1])) {
                                int amount = Integer.parseInt(args[1]);
                                if(amount > 0){
                                    ChatUtils.sendMsgTag(p, "GiveCoins", "Je hebt &6" + amount + " coins &aaan " + target.getDisplayName() + " &agegeven!");
                                    PlayerUtils.addCoins(target, amount, "Gekregen van " + p.getDisplayName() + "&6");
                                } else {
                                    ChatUtils.sendMsgTag(p, "GiveCoins", ChatUtils.error + "Je kan alleen 1 coin of meer geven!");
                                }
                            } else {
                                ChatUtils.sendMsgTag(p, "GiveCoins", ChatUtils.error + "Zorg er voor dat het 2e argument een nummer is!");
                            }
                        } else {
                            ChatUtils.sendMsgTag(p, "GiveCoins", ChatUtils.error + "Deze speler bestaat niet of is niet online!");
                        }
                    } else {
                        ChatUtils.sendMsgTag(p, "GiveCoins", ChatUtils.error + "Niet genoeg argumenten! Gebruik deze command zo: &o/givecoins <player> <hoeveelheid>");
                    }
                } else {
                    ChatUtils.sendMsgTag(p, "GiveCoins", ChatUtils.error + "Je mag dit niet doen!");
                }
            } else {
                Main.getPlugin().getLogger().warning("This command can only be executed by a player!");
            }
        } else if (cmd.getName().equalsIgnoreCase("masscoins")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.isOp()) {
                    if (args.length >= 1) {
                        if (MiscUtils.isInt(args[0])) {
                            int amount = Integer.parseInt(args[0]);
                            if(amount > 0){
                                ChatUtils.sendMsgTag(p, "MassCoins", "Je hebt &6" + amount + " coins &aaan iedereen gegeven!");
                                ChatUtils.bcMsgTag("MassCoins", p.getDisplayName() + " &aheeft &6" + amount + " coins &aaan iedereen gegeven.");
                                for (Player pl : Bukkit.getOnlinePlayers()) {
                                    PlayerUtils.addCoins(pl, amount, "Gekregen van " + p.getDisplayName() + "&6");
                                }
                            } else {
                                ChatUtils.sendMsgTag(p, "MassCoins", ChatUtils.error + "Je kan alleen 1 coin of meer geven!");
                            }
                        } else {
                            ChatUtils.sendMsgTag(p, "MassCoins", ChatUtils.error + "Zorg er voor dat het 1e argument een nummer is!");
                        }
                    } else {
                        ChatUtils.sendMsgTag(p, "MassCoins", ChatUtils.error + "Niet genoeg argumenten! Gebruik deze command zo: &o/masscoins <hoeveelheid>");
                    }
                } else {
                    ChatUtils.sendMsgTag(p, "MassCoins", ChatUtils.error + "Je mag dit niet doen!");
                }
            } else {
                Main.getPlugin().getLogger().warning("This command can only be executed by a player!");
            }
        } else if (cmd.getLabel().equalsIgnoreCase("takecoins")){
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.isOp()) {
                    if (args.length >= 2) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            if (MiscUtils.isInt(args[1])) {
                                int amount = Integer.parseInt(args[1]);
                                if(amount > 0){
                                    ChatUtils.sendMsgTag(p, "TakeCoins", "Je hebt &6" + amount + " coins &avan " + target.getDisplayName() + " &aafgepakt!");
                                    PlayerUtils.takeCoins(target, amount, "Afgepakt door " + p.getDisplayName() + "&6");
                                } else {
                                    ChatUtils.sendMsgTag(p, "TakeCoins", ChatUtils.error + "Je kan alleen 1 coin of meer afpakken!");
                                }
                            } else {
                                ChatUtils.sendMsgTag(p, "TakeCoins", ChatUtils.error + "Zorg er voor dat het 2e argument een nummer is!");
                            }
                        } else {
                            ChatUtils.sendMsgTag(p, "TakeCoins", ChatUtils.error + "Deze speler bestaat niet of is niet online!");
                        }
                    } else {
                        ChatUtils.sendMsgTag(p, "TakeCoins", ChatUtils.error + "Niet genoeg argumenten! Gebruik deze command zo: &o/takecoins <player> <hoeveelheid>");
                    }
                } else {
                    ChatUtils.sendMsgTag(p, "TakeCoins", ChatUtils.error + "Je mag dit niet doen!");
                }
            } else {
                Main.getPlugin().getLogger().warning("This command can only be executed by a player!");
            }
        } else if (cmd.getLabel().equalsIgnoreCase("setcoins")){
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.isOp()) {
                    if (args.length >= 2) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            if (MiscUtils.isInt(args[1])) {
                                int amount = Integer.parseInt(args[1]);
                                if(amount > 0){
                                    ChatUtils.sendMsgTag(p, "SetCoins", target.getDisplayName() + " &aheeft nu &6" + amount + " coins&a!");
                                    PlayerUtils.setCoins(target, amount, "Ingesteld door " + p.getDisplayName() + "&6");
                                } else {
                                    ChatUtils.sendMsgTag(p, "SetCoins", ChatUtils.error + "Je kan alleen 1 coin of meer instellen!");
                                }
                            } else {
                                ChatUtils.sendMsgTag(p, "SetCoins", ChatUtils.error + "Zorg er voor dat het 2e argument een nummer is!");
                            }
                        } else {
                            ChatUtils.sendMsgTag(p, "SetCoins", ChatUtils.error + "Deze speler bestaat niet of is niet online!");
                        }
                    } else {
                        ChatUtils.sendMsgTag(p, "SetCoins", ChatUtils.error + "Niet genoeg argumenten! Gebruik deze command zo: &o/setcoins <player> <hoeveelheid>");
                    }
                } else {
                    ChatUtils.sendMsgTag(p, "SetCoins", ChatUtils.error + "Je mag dit niet doen!");
                }
            } else {
                Main.getPlugin().getLogger().warning("This command can only be executed by a player!");
            }
        }
        return true;
    }
}

