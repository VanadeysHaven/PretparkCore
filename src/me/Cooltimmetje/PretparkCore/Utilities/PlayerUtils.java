package me.Cooltimmetje.PretparkCore.Utilities;

import com.evilmidget38.UUIDFetcher;
import me.Cooltimmetje.PretparkCore.Managers.InventoryManager;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * This class has been created on 28-7-2015 at 19:58 by cooltimmetje.
 */
public class PlayerUtils {

    public static void fixGamemode(Player p){
        if(p.isOp() || p.hasPermission("pretparkcore.bypassgm")){
            p.setGameMode(GameMode.CREATIVE);
        } else {
            p.setGameMode(GameMode.ADVENTURE);
            InventoryManager.setInventory(p);
        }
    }

    public static int getCoins(Player p){
        return Vars.coins.get(p.getName());
    }

    public static int getCoinTime(Player p){
        return Vars.coinsTime.get(p.getName());
    }

    public static void addCoins(Player p, int coinsGained, String reason){
        int curCoins = getCoins(p);
        Vars.coins.remove(p.getName());
        Vars.coins.put(p.getName(), coinsGained + curCoins);
        ChatUtils.sendMsg(p, "&6+" + coinsGained + " coins! (" + reason + ")");
        p.playSound(p.getLocation(), Sound.LEVEL_UP, 100, 1);
    }

    public static void setCoinTime(Player p, int time){
        Vars.coinsTime.remove(p.getName());
        Vars.coinsTime.put(p.getName(), time);
    }

    public static String getUUID(Player p) {
        String name, uuid = null;
        name = p.getName();

        try {
            uuid = new UUIDFetcher(Arrays.asList(name)).call().get(name).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uuid;
    }

}
