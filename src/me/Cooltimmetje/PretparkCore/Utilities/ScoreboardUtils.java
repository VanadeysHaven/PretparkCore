package me.Cooltimmetje.PretparkCore.Utilities;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class has been created on 28-7-2015 at 19:54 by cooltimmetje.
 */
public class ScoreboardUtils {

    public static HashMap<String, Scoreboard> scoreboards = new HashMap<>();
    static ScoreboardManager manager = Bukkit.getScoreboardManager();

    public static void constructScoreboard(Player p){
        Scoreboard board = manager.getNewScoreboard();
        Objective objective = board.registerNewObjective("mainboard", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(Vars.PRETPARK_NAAM);

        Score coins = objective.getScore(MiscUtils.color("&7Coins:"));
        coins.setScore(Vars.coins.get(p.getName()));
        Score online = objective.getScore(MiscUtils.color("&7Nu online:"));
        online.setScore(Bukkit.getOnlinePlayers().size());
        Score uniek = objective.getScore(MiscUtils.color("&7Unieke spelers:"));
        uniek.setScore(Vars.globaldata.get("uniqueplayers"));

        p.setScoreboard(board);
        scoreboards.put(p.getName(), board);
    }

    public static void destroyScoreboard(Player p){
        Scoreboard board = scoreboards.get(p.getName());
        Objective objective = board.getObjective(DisplaySlot.SIDEBAR);
        objective.unregister();
        scoreboards.remove(p.getName());
    }

    public static void updateScoreboard(Player p, boolean leave){
        Scoreboard board = scoreboards.get(p.getName()); //TODO: != null
        if(board != null){
            scoreboards.remove(p.getName());
            Objective objective = board.getObjective(DisplaySlot.SIDEBAR);
            objective.unregister();
            objective = board.registerNewObjective("mainboard", "dummy");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName(Vars.PRETPARK_NAAM);

            Score coins = objective.getScore(MiscUtils.color("&7Coins:"));
            coins.setScore(Vars.coins.get(p.getName()));
            Score online = objective.getScore(MiscUtils.color("&7Nu online:"));
            if(leave) {
                online.setScore(Bukkit.getOnlinePlayers().size() - 1);
            } else {
                online.setScore(Bukkit.getOnlinePlayers().size());
            }
            Score uniek = objective.getScore(MiscUtils.color("&7Unieke spelers:"));
            uniek.setScore(Vars.globaldata.get("uniqueplayers"));

            scoreboards.put(p.getName(), board);
        } else {
            constructScoreboard(p);
        }
    }

}
