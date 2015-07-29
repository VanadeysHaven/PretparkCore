package me.Cooltimmetje.PretparkCore.Utilities;

import org.bukkit.entity.Player;

/**
 * This class has been created on 28-7-2015 at 20:54 by cooltimmetje.
 */
public class ChatUtils {

    public static void sendMsg(Player p, String msg){
        p.sendMessage(MiscUtils.color(msg));
    }

}
