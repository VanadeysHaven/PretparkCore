package me.Cooltimmetje.PretparkCore.Utilities;

import me.Cooltimmetje.PretparkCore.Main;
import org.bukkit.Bukkit;

/**
 * This class has been created on 28-7-2015 at 21:16 by cooltimmetje.
 */
public class ScheduleUtils {

    public static void scheduleTask(long delayTicks, Runnable runnable){
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), runnable, delayTicks);
    }

    public static void repeatTask(long startDelay, long repeatDelay, Runnable runnable){
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), runnable, startDelay, repeatDelay);
    }

}
