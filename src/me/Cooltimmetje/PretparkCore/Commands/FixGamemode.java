package me.Cooltimmetje.PretparkCore.Commands;

import me.Cooltimmetje.PretparkCore.Utilities.ChatUtils;
import me.Cooltimmetje.PretparkCore.Utilities.MiscUtils;
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
            if(sender instanceof Player){
                Player p = (Player) sender;
                if(p.isOp()){
                    fixGm();
                    ChatUtils.sendMsg(p, "&eAll gamemodes have been fixed!");
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
