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

import me.Cooltimmetje.PretparkCore.Utilities.Packets.WorldPackets;
import org.bukkit.entity.Player;

/**
 * This class has been created on 14-8-2015 at 19:07 by cooltimmetje.
 */
public class WorldUtils {

    public static void updateSpawnSigns(Player p) {

        WorldPackets.setPlayerSign(p, Vars.signSpawnTopLeft, "&lWelkom", p.getName() + "!", "Je speelt nu op:", Vars.PRETPARK_NAAM);
        WorldPackets.setPlayerSign(p, Vars.signSpawnTopRight, "&lWelkom", p.getName() + "!", "Je speelt nu op:", Vars.PRETPARK_NAAM);
        WorldPackets.setPlayerSign(p, Vars.signSpawnBottemLeft, "&8[&5Jouw Stats&8]", "&6" + PlayerUtils.getCoins(p) + " coins", "", "");
        WorldPackets.setPlayerSign(p, Vars.signSpawnBottemRight, "&8[&5Jouw Stats&8]", "&6" + PlayerUtils.getCoins(p) + " coins", "", "");

    }

}