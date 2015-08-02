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

import me.Cooltimmetje.PretparkCore.Managers.MaintenanceManager;
import me.Cooltimmetje.PretparkCore.Utilities.ChatUtils;
import me.Cooltimmetje.PretparkCore.Utilities.Vars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 2-8-2015 at 14:36 by cooltimmetje.
 */
public class MaintenanceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getLabel().equalsIgnoreCase("togglem")){
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(p.isOp()) {
                    boolean m = toggle();
                    if (m){
                        ChatUtils.sendMsgTag(p, "ToggleOnderhoud", "Onderhoud staat nu &2aan&a!");
                    } else {
                        ChatUtils.sendMsgTag(p, "ToggleOnderhoud", "Onderhoud staat nu &cuit&a!");
                    }
                    MaintenanceManager.fix();
                } else {
                    ChatUtils.sendMsgTag(p, "ToggleOnderhoud", ChatUtils.error + "Je mag dit niet doen!");
                }
            } else {
                boolean m = toggle();
                sender.sendMessage("Onderhoud staat nu op" + m);
                MaintenanceManager.fix();
            }
        }
        return false;
    }

    private boolean toggle(){
        if(Vars.globaldata.get("onderhoud") == 1){
            Vars.globaldata.put("onderhoud", 0);
        } else {
            Vars.globaldata.put("onderhoud", 1);
        }
        return Vars.globaldata.get("onderhoud") == 1;
    }
}
