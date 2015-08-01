/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Tim Medema
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.Cooltimmetje.PretparkCore.Utilities;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;

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
        Scoreboard board = scoreboards.get(p.getName());
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
