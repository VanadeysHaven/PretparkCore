package me.Cooltimmetje.PretparkCore.Utilities;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * This class has been created on 28-7-2015 at 20:54 by cooltimmetje.
 */
public class ChatUtils {

    public static void sendMsg(Player p, String msg){
        p.sendMessage(MiscUtils.color(msg));
    }

    public static void sendMsgTag(Player p, String tag, String msg){
        p.sendMessage(MiscUtils.color("&9" + tag + "&9> &a" + msg));
    }

    public static void bcMsgTag(String tag, String msg){
        Bukkit.broadcastMessage(MiscUtils.color("&9" + tag + "&9> &a" + msg));
    }

    public static String error = "&c&lERROR! &a";

}
