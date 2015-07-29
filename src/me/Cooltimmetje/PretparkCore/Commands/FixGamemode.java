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

import me.Cooltimmetje.PretparkCore.Utilities.ChatUtils;
import me.Cooltimmetje.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 28-7-2015 at 20:51 by cooltimmetje.
 */
public class FixGamemode implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("fixgamemodes")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.isOp()) {
                    fixGm();
                    ChatUtils.sendMsg(p, "&eAll gamemodes have been fixed!");
                } else {
                    ChatUtils.sendMsgTag(p, "FixGM", ChatUtils.error + "Je mag dit niet doen!");
                }
            } else {
                fixGm();
                sender.sendMessage("All gamemodes have been fixed!");
            }
        }
        return true;
    }

    private void fixGm() {
        for(Player p : Bukkit.getOnlinePlayers()){
            PlayerUtils.fixGamemode(p);
        }
    }

}
