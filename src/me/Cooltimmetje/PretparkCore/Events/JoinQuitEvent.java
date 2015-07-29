package me.Cooltimmetje.PretparkCore.Events;

import me.Cooltimmetje.PretparkCore.MysqlManager.Database;
import me.Cooltimmetje.PretparkCore.Utilities.PlayerUtils;
import me.Cooltimmetje.PretparkCore.Utilities.ScheduleUtils;
import me.Cooltimmetje.PretparkCore.Utilities.ScoreboardUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * This class has been created on 28-7-2015 at 19:33 by cooltimmetje.
 */
public class JoinQuitEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        final Player pfinal = event.getPlayer();

        Database.loadData(p);
        ScoreboardUtils.constructScoreboard(p);

        ScheduleUtils.scheduleTask(20, new Runnable() {
            @Override
            public void run() {
                PlayerUtils.fixGamemode(pfinal);
            }
        });
        for(Player pl : Bukkit.getOnlinePlayers()){
            ScoreboardUtils.updateScoreboard(pl, false);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player p = event.getPlayer();

        ScoreboardUtils.destroyScoreboard(p);
        Database.saveData(p, true);

        for(Player pl : Bukkit.getOnlinePlayers()){
            if(p != pl) {
                ScoreboardUtils.updateScoreboard(pl, true);
            }
        }
    }

}
