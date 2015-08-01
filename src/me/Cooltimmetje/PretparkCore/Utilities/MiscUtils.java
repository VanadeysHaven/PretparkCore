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

import me.Cooltimmetje.PretparkCore.RemoteControl.SignLinkEvent;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * This class has been created on 28-7-2015 at 19:05 by cooltimmetje.
 */
public class MiscUtils {

    public static String color(String s){
        return s.replace('&', '\u00A7');
    }

    public static int randomInt(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public static boolean isInt(String str){
        try{
            int num = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Firework shootFirework(Location loc, String world){
        //Spawn the Firework, get the FireworkMeta.
        final Firework fw = (Firework) Bukkit.getWorld(world).spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();

        //Our random generator
        Random r = new Random();

        //Get the type
        int rt = r.nextInt(4) + 1;
        FireworkEffect.Type type = FireworkEffect.Type.BALL;
        if (rt == 1) type = FireworkEffect.Type.BALL;
        if (rt == 2) type = FireworkEffect.Type.BURST;
        if (rt == 3) type = FireworkEffect.Type.STAR;
        if (rt == 4) type = FireworkEffect.Type.CREEPER;
        if (rt == 5) type = FireworkEffect.Type.BALL_LARGE;

        //Get our random colours
        int r1i = r.nextInt(17) + 1;
        int r2i = r.nextInt(17) + 1;
        Color c1 = getColor(r1i);
        Color c2 = getColor(r2i);

        //Create our effect with this
        FireworkEffect effect = FireworkEffect.builder().flicker(false).withColor(c1).withFade(c2).with(type).trail(true).build();

        //Then apply the effect to the meta
        fwm.addEffect(effect);

        //Generate some random power and set it
        int rp = r.nextInt(2) + 1;
        fwm.setPower(rp);

        //Then apply this to our rocket
        fw.setFireworkMeta(fwm);
        return fw;
    }

    private static Color getColor(int i) {
        Color c = null;
        if(i==1){
            c=Color.AQUA;
        }
        if(i==2){
            c=Color.BLACK;
        }
        if(i==3){
            c=Color.BLUE;
        }
        if(i==4){
            c=Color.FUCHSIA;
        }
        if(i==5){
            c=Color.GRAY;
        }
        if(i==6){
            c=Color.GREEN;
        }
        if(i==7){
            c=Color.LIME;
        }
        if(i==8){
            c=Color.MAROON;
        }
        if(i==9){
            c=Color.NAVY;
        }
        if(i==10){
            c=Color.OLIVE;
        }
        if(i==11){
            c=Color.ORANGE;
        }
        if(i==12){
            c=Color.PURPLE;
        }
        if(i==13){
            c=Color.RED;
        }
        if(i==14){
            c=Color.SILVER;
        }
        if(i==15){
            c=Color.TEAL;
        }
        if(i==16){
            c=Color.WHITE;
        }
        if(i==17){
            c= Color.YELLOW;
        }

        return c;
    }

    public static Location formatLocation(String location) {
        String[] xyz = location.split(",");
        return Bukkit.getWorld("world").getBlockAt(Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]), Integer.parseInt(xyz[2])).getLocation();
    }

    public static String locationToString(Location location) {
        DecimalFormat df = new DecimalFormat("###.#");
        return df.format(location.getX()) + "," + df.format(location.getY()) + "," + df.format(location.getZ());
    }

    public static String getStateString(String stateChar){
        switch(stateChar){
            case "o":
                return "&2open";
            case "d":
                return "&cdicht";
            case "m":
                return "&6onderhoud";
            default:
                return null;
        }
    }

    public static int getStateClay(String stateChar) {
        switch(stateChar){
            case "o":
                return 5;
            case "d":
                return 14;
            case "m":
                return 1;
            default:
                return 0;
        }
    }

    public static float getYaw(String yawPitch){
        String[] yawAndPitch = yawPitch.split(",");
        return Float.parseFloat(yawAndPitch[0]);
    }

    public static float getPitch(String yawPitch){
        String[] yawAndPitch = yawPitch.split(",");
        return Float.parseFloat(yawAndPitch[1]);
    }

    public static String getVar(String varName){
        return SignLinkEvent.variableValues.get(varName);
    }

    public static boolean cooldownCheck(long lastused, int cdtime){
        long currentTime = System.currentTimeMillis();
        int cdmillis = cdtime * 1000;
        return currentTime - lastused >= cdmillis;
    }

    public static int getTimeRemaining(long lastUsed, int cdtime){
        long currentTime = System.currentTimeMillis();

        return (int) (((currentTime - lastUsed ) / 1000) - cdtime) * -1;
    }

    public static String formatTime(int seconds){
        int minutes = 0;
        while(seconds >= 60){
            seconds = seconds - 60;
            minutes = minutes + 1;
        }
        return minutes + "m" + seconds + "s";
    }
}
