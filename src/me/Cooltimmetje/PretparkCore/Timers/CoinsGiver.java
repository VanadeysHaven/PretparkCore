package me.Cooltimmetje.PretparkCore.Timers;

import me.Cooltimmetje.PretparkCore.Utilities.MiscUtils;
import me.Cooltimmetje.PretparkCore.Utilities.PlayerUtils;
import me.Cooltimmetje.PretparkCore.Utilities.ScheduleUtils;
import me.Cooltimmetje.PretparkCore.Utilities.Vars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * This class has been created on 29-7-2015 at 14:30 by cooltimmetje.
 */
public class CoinsGiver {

    public static void coinsGiver(){
        ScheduleUtils.repeatTask(20, 1200, new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()){
                    if (PlayerUtils.getCoinTime(p) == 0){
                        int chance = MiscUtils.randomInt(1,100);
                        if(chance <= Vars.DOUBLE_CHANCE){
                            PlayerUtils.addCoins(p, Vars.COIN_GAIN * 2, "1 uur online, dubbel coins");
                            PlayerUtils.setCoinTime(p, Vars.COIN_TIME);
                        } else {
                            PlayerUtils.addCoins(p, Vars.COIN_GAIN, "1 uur online");
                            PlayerUtils.setCoinTime(p, Vars.COIN_TIME);
                        }
                    } else {
                        PlayerUtils.setCoinTime(p, PlayerUtils.getCoinTime(p) - 1);
                    }
                }
            }
        });
    }

}
