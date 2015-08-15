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
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 15-8-2015 at 16:38 by cooltimmetje.
 */
public class ClearChatCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(cmd.getLabel().equalsIgnoreCase("cc")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(p.hasPermission("pretparkcore.clearchat")){
                    for(Player pl : Bukkit.getOnlinePlayers()){
                        if(!pl.hasPermission("pretparkcore.clearchat")) {
                            ChatUtils.clearChat(pl);
                        }
                    }
                    ChatUtils.bcMsgTag("ClearChat", "De chat werd gecleard door: " + p.getDisplayName() + "&a!");
                    for(Player pl : Bukkit.getOnlinePlayers()){
                        if(pl.hasPermission("pretparkcore.clearchat")){
                            ChatUtils.sendMsgTag(pl, "ClearChat", "Jouw chat is niet gecleard omdat je een permissie hiervoor hebt!");
                        }
                    }
                } else {
                    ChatUtils.sendNoPremTag(p, "ClearChat");
                }
            } else {
                sender.sendMessage("Only players can use this!");
            }
        }
        return true;
    }

}
