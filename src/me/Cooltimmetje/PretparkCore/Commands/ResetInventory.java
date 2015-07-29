package me.Cooltimmetje.PretparkCore.Commands;

import me.Cooltimmetje.PretparkCore.Main;
import me.Cooltimmetje.PretparkCore.Managers.InventoryManager;
import me.Cooltimmetje.PretparkCore.Utilities.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class has been created on 29-7-2015 at 15:32 by cooltimmetje.
 */
public class ResetInventory implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("resetinv")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if(p.hasPermission("pretparkcore.bypassgm") || p.isOp()) {
                    ChatUtils.sendMsgTag(p, "Inventory", "Je inventory is gereset!");
                    InventoryManager.setInventory(p);
                } else {
                    ChatUtils.sendMsgTag(p, "Inventory", ChatUtils.error + "Je mag dit niet doen!");
                }
            } else {
                Main.getPlugin().getLogger().warning("This command can only be executed by a player!");
            }
        }
        return true;
    }

}
