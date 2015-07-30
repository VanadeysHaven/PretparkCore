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

import me.Cooltimmetje.PretparkCore.MysqlManager.Database;
import me.Cooltimmetje.PretparkCore.Utilities.ChatUtils;
import me.Cooltimmetje.PretparkCore.Utilities.MiscUtils;
import me.Cooltimmetje.PretparkCore.Utilities.Vars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

/**
 * This class has been created on 30-7-2015 at 10:25 by cooltimmetje.
 */
public class RideCommands implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("changeride")) { // <id> <state>
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if(p.isOp()){
                    if(args.length >= 2){
                        if(MiscUtils.isInt(args[0])){
                            if(Objects.equals(args[1], "o") || Objects.equals(args[1], "d") || Objects.equals(args[1], "m")){
                                int i = Integer.parseInt(args[0]);
                                if (Vars.rideName.containsKey(i)){
                                    Vars.rideStatus.remove(i);
                                    Vars.rideStatus.put(i, args[1]);
                                    ChatUtils.sendMsgTag(p, "ChangeRide", "De &6" + Vars.rideName.get(i) +
                                            " &ais nu gemarkeerd als " + MiscUtils.getStateString(Vars.rideStatus.get(i)));
                                } else {
                                    ChatUtils.sendMsgTag(p, "ChangeRide", ChatUtils.error + "Deze attractie bestaat niet! Gebruik &o/listrides &aom het juiste id te vinden!");
                                }
                            } else {
                                ChatUtils.sendMsgTag(p, "ChangeRide", ChatUtils.error + "Zorg dat het 2e argument een o,d of m is! Gebruik deze command zo: &o/changeride <id> <o/d/m>");
                            }
                        } else {
                            ChatUtils.sendMsgTag(p, "ChangeRide", ChatUtils.error + "Zorg dat het 1e argument een nummer is! Gebruik deze command zo: &o/changeride <id> <o/d/m>");
                        }
                    } else {
                        ChatUtils.sendMsgTag(p, "ChangeRide", ChatUtils.error + "Niet genoeg argumenten! Gebruik deze command zo: &o/changeride <id> <o/d/m>");
                    }
                } else {
                    ChatUtils.sendMsgTag(p, "ChangeRide", ChatUtils.error + "Je mag dit niet doen!");
                }
            } else {
                if(args.length >= 2){
                    if(MiscUtils.isInt(args[0])){
                        if(Objects.equals(args[1], "o") || Objects.equals(args[1], "d") || Objects.equals(args[1], "m")){
                            int i = Integer.parseInt(args[0]);
                            if (Vars.rideName.containsKey(i)){
                                Vars.rideStatus.remove(i);
                                Vars.rideStatus.put(i, args[1]);
                                sender.sendMessage("De " + Vars.rideName.get(i) +
                                        " is nu gemarkeerd als " + MiscUtils.getStateString(Vars.rideStatus.get(i)));
                            } else {
                                sender.sendMessage("Deze attractie bestaat niet! Gebruik /listrides om het juiste id te vinden!");
                            }
                        } else {
                            sender.sendMessage("Zorg dat het 2e argument een o,d of m is! Gebruik deze command zo: /changeride <id> <o/d/m>");
                        }
                    } else {
                        sender.sendMessage("Zorg dat het 1e argument een nummer is! Gebruik deze command zo: /changeride <id> <o/d/m>");
                    }
                } else {
                    sender.sendMessage("Niet genoeg argumenten! Gebruik deze command zo: /changeride <id> <o/d/m>");
                }
            }
        } else if (cmd.getLabel().equalsIgnoreCase("listrides")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(p.isOp()) {
                    ChatUtils.sendMsg(p, "&3------ &aALLE ATTRACTIES &3------");
                    ChatUtils.sendMsg(p, "&aID &b- &aNaam &b- &aStatus &b- &aLocatie");
                    for (int i : Vars.rideName.keySet()) {
                        String status = MiscUtils.getStateString(Vars.rideStatus.get(i));

                        ChatUtils.sendMsg(p, "&a" + i + " &b- &a" + Vars.rideName.get(i) + " &b- &a" + status + " &b- &a" + MiscUtils.locationToString(Vars.rideLocation.get(i)));
                    }
                } else {
                    ChatUtils.sendMsgTag(p, "ListRides", ChatUtils.error + "Je mag dit niet doen!");
                }
            } else {
                sender.sendMessage("------ ALLE ATTRACTIES ------");
                sender.sendMessage("ID - Naam - Status - Locatie");
                for(int i : Vars.rideName.keySet()){
                    String status = MiscUtils.getStateString(Vars.rideStatus.get(i));

                    sender.sendMessage(i + " - " + Vars.rideName.get(i) + " - "  + status + "&r - " + MiscUtils.locationToString(Vars.rideLocation.get(i)));
                }
            }
        } else if (cmd.getLabel().equalsIgnoreCase("reloadrides")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(p.isOp()){
                    for(int i : Vars.rideName.keySet()) {
                        Database.saveRides(i);
                    }
                    Database.loadRides();
                    ChatUtils.sendMsgTag(p, "ReloadRides", "Alle attracties zijn opnieuw geladen vanuit de database!");
                } else {
                    ChatUtils.sendMsgTag(p, "ReloadRides", ChatUtils.error + "Je mag dit niet doen!");
                }
            } else {
                Database.loadRides();
                sender.sendMessage("Alle attracties zijn opnieuw geladen vanuit de database!");
            }
        }
        return true;
    }

}
