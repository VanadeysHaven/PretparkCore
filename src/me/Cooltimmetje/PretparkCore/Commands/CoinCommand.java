package me.Cooltimmetje.PretparkCore.Commands;

import me.Cooltimmetje.PretparkCore.Main;
import me.Cooltimmetje.PretparkCore.Utilities.ChatUtils;
import me.Cooltimmetje.PretparkCore.Utilities.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 29-7-2015 at 14:41 by cooltimmetje.
 */
public class CoinCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("coins")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (args.length >= 1) {
                    if(p.hasPermission("pretparkcore.coins.other") || p.isOp()){
                        Player target = Bukkit.getPlayer(args[0]);
                        if(target != null){
                            ChatUtils.sendMsgTag(p, "Coins", target.getDisplayName() + " &aheeft momenteel &6" + PlayerUtils.getCoins(target) + " &acoins!");
                        } else {
                            ChatUtils.sendMsgTag(p, "Coins", ChatUtils.error + "Deze speler bestaat niet of is niet online!");
                        }
                    } else {
                        ChatUtils.sendMsgTag(p, "Coins", "Je hebt momenteel &6" + PlayerUtils.getCoins(p) + " &acoins!");
                    }
                } else {
                    ChatUtils.sendMsgTag(p, "Coins", "Je hebt momenteel &6" + PlayerUtils.getCoins(p) + " &acoins!");
                }
            } else {
                Main.getPlugin().getLogger().warning("This command can only be executed by a player!");
            }
        }
        return true;
    }

}
