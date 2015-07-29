package me.Cooltimmetje.PretparkCore.Timers;

import me.Cooltimmetje.PretparkCore.MysqlManager.Database;
import me.Cooltimmetje.PretparkCore.Utilities.ScheduleUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * This class has been created on 29-7-2015 at 14:25 by cooltimmetje.
 */
public class DataSaver {

    public static void dataSaver(){
        ScheduleUtils.repeatTask(12000, 12000, new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()){
                    Database.saveData(p, false);
                }
            }
        });
    }

}
