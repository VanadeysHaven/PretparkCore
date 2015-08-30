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

package me.Cooltimmetje.PretparkCore.Bundles.Parkour;

import me.Cooltimmetje.PretparkCore.Utilities.ChatUtils;
import me.Cooltimmetje.PretparkCore.Utilities.MiscUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;

/**
 * This class has been created on 23-8-2015 at 20:29 by cooltimmetje.
 */
public class ParkourTracker implements Listener {

    private HashMap<String, Long> startTime = new HashMap<>();

    @EventHandler
    public void onPressurePlate(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if(event.getAction() == Action.PHYSICAL){
            if(event.getClickedBlock().getType() == Material.IRON_PLATE){
                if(!startTime.containsKey(p.getName())){
                    startTime.put(p.getName(), System.currentTimeMillis());
                    ChatUtils.sendMsgTag(p, "Parkour", "Parkour challenge gestart!");
                }
            } else if(event.getClickedBlock().getType() == Material.GOLD_PLATE){
                if(startTime.containsKey(p.getName())) {
                    String finalTime = MiscUtils.formatTime(startTime.get(p.getName()), System.currentTimeMillis());
                    ChatUtils.sendMsgTag(p, "Parkour", "Gefeliciteed je hebt het parkour voltooid!");
                    ChatUtils.sendMsg(p, "&aJe hebt er &l" + finalTime + " &aover gedaan!");
                    startTime.remove(p.getName());
                } else {
                    ChatUtils.sendMsgTag(p, "Parkour", "Dit is het einde! Begin bij de start.");
                }
            }
        }
    }

}
