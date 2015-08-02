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

package me.Cooltimmetje.PretparkCore.Events;

import me.Cooltimmetje.PretparkCore.Utilities.MiscUtils;
import me.Cooltimmetje.PretparkCore.Utilities.Vars;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.io.File;

/**
 * This class has been created on 2-8-2015 at 14:08 by cooltimmetje.
 */
public class ServerPingEvent implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent event){
        if(Vars.globaldata.get("onderhoud") == 1){
            event.setMotd(MiscUtils.color(Vars.MOTD + "&c&lONDERHOUD!"));
            try {
                event.setServerIcon(Bukkit.loadServerIcon(new File("closed.png")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            event.setMotd(MiscUtils.color(Vars.MOTD + "&a&lKOEKJES!")); //TODO MAKE RANDOM MOTD.
            try {
                event.setServerIcon(Bukkit.loadServerIcon(new File("open.png")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
