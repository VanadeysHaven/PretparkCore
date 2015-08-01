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

package me.Cooltimmetje.PretparkCore.Timers;

import me.Cooltimmetje.PretparkCore.MysqlManager.Database;
import me.Cooltimmetje.PretparkCore.RemoteControl.SignLinkEvent;
import me.Cooltimmetje.PretparkCore.Utilities.ScheduleUtils;
import me.Cooltimmetje.PretparkCore.Utilities.Vars;
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
                for(int i : Vars.rideStatus.keySet()){
                    Database.saveRides(i);
                }
                for(String s : SignLinkEvent.variableValues.keySet()){
                    Database.saveVars(s, SignLinkEvent.variableValues.get(s));
                }
            }
        });
    }

    public static void saveData(){
        for(int i : Vars.rideStatus.keySet()){
            Database.saveRides(i);
        }
        for(String s : SignLinkEvent.variableValues.keySet()){
            Database.saveVars(s, SignLinkEvent.variableValues.get(s));
        }
    }
}
