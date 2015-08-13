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

package me.Cooltimmetje.PretparkCore.RemoteControl;

import com.bergerkiller.bukkit.sl.API.VariableChangeEvent;
import me.Cooltimmetje.PretparkCore.RemoteControl.RideControl.BlackCobraControl;
import me.Cooltimmetje.PretparkCore.Utilities.MiscUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class has been created on 1-8-2015 at 10:38 by cooltimmetje.
 */
public class SignLinkEvent implements Listener,CommandExecutor {

    public static ArrayList<String> blackCobraSignLink = new ArrayList<>();
    public static HashMap<String,String> variableValues = new HashMap<>();

    @EventHandler
    public void onSignLinkChange(VariableChangeEvent event){
        String name = event.getVariable().getName();
        String value = event.getNewValue();

        if(blackCobraSignLink.contains(name)){
            variableValues.put(name, value);
        }

        if(blackCobraSignLink.contains(name)){
            BlackCobraControl.updateRide();
        }
    }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getLabel().equalsIgnoreCase("listvars")){
            for(String s : variableValues.keySet()){
                sender.sendMessage(MiscUtils.color("&a" + s + " &b- &a" + variableValues.get(s)));
            }
        }
        return true;
    }
}