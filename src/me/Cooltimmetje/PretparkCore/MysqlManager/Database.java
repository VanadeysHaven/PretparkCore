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

package me.Cooltimmetje.PretparkCore.MysqlManager;

import com.zaxxer.hikari.HikariDataSource;
import me.Cooltimmetje.PretparkCore.Main;
import me.Cooltimmetje.PretparkCore.RemoteControl.SignLinkEvent;
import me.Cooltimmetje.PretparkCore.Utilities.MiscUtils;
import me.Cooltimmetje.PretparkCore.Utilities.PlayerUtils;
import me.Cooltimmetje.PretparkCore.Utilities.ScoreboardUtils;
import me.Cooltimmetje.PretparkCore.Utilities.Vars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class has been created on 28-7-2015 at 21:15 by cooltimmetje.
 */
public class Database {

    public static HikariDataSource hikari = null;

    public static void connectToDatabase(){
        hikari = new HikariDataSource();
        hikari.setMaximumPoolSize(10);

        hikari.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        hikari.addDataSourceProperty("serverName", Main.getPlugin().getConfig().getString("Database.serverName"));
        hikari.addDataSourceProperty("port", Main.getPlugin().getConfig().getString("Database.port"));
        hikari.addDataSourceProperty("databaseName", Main.getPlugin().getConfig().getString("Database.databaseName"));
        hikari.addDataSourceProperty("user", Main.getPlugin().getConfig().getString("Database.user"));
        hikari.addDataSourceProperty("password", Main.getPlugin().getConfig().getString("Database.password"));
    }

    public static void loadData(Player p) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String uuid = PlayerUtils.getUUID(p);
        String load = "SELECT * FROM playerdata WHERE uuid = '" + uuid + "';";

        try{
            c = hikari.getConnection();
            ps = c.prepareStatement(load);
            rs = ps.executeQuery();

            if(rs.next()){
                setData(rs, p);
            } else {
                createProfile(p);
            }

        } catch (SQLException e){
            e.printStackTrace();
            //TODO: ASK THIJS WHAT TO DO WHEN THIS HAPPENS
        } finally {
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void createProfile(Player p) {
        Main.getPlugin().getLogger().info("Creating profile for: " + p.getName());
        Connection c = null;
        PreparedStatement ps = null;
        String uuid = PlayerUtils.getUUID(p);
        String create = "INSERT INTO playerdata VALUES(?,?,0,?)";

        try{
            c = hikari.getConnection();
            ps = c.prepareStatement(create);

            ps.setString(1, uuid);
            ps.setString(2, p.getName());
            ps.setInt(3, Vars.COIN_TIME);

            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        Vars.coins.put(p.getName(), 0);
        Vars.coinsTime.put(p.getName(), Vars.COIN_TIME);

        int up = Vars.globaldata.get("uniqueplayers");
        Vars.globaldata.remove("uniqueplayers");
        Vars.globaldata.put("uniqueplayers", up + 1);
        Vars.saveUp();

        for(Player pl : Bukkit.getOnlinePlayers()){
            ScoreboardUtils.updateScoreboard(pl, false);
        }
    }

    private static void setData(ResultSet rs, Player p) {
        try {
            Vars.coins.put(p.getName(), rs.getInt("coins"));
            Vars.coinsTime.put(p.getName(), rs.getInt("coin_time"));
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void saveData(Player p, boolean leave){
        Connection c = null;
        PreparedStatement ps = null;
        String uuid = PlayerUtils.getUUID(p);
        String updateData = "UPDATE playerdata SET last_name=?,coins=?,coin_time=? WHERE uuid=?";

        try{
            c = hikari.getConnection();
            ps = c.prepareStatement(updateData);

            ps.setString(1, p.getName());
            ps.setInt(2, PlayerUtils.getCoins(p));
            ps.setInt(3, PlayerUtils.getCoinTime(p));
            ps.setString(4, uuid);

            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        if(leave){
            removeData(p);
        }
    }

    private static void removeData(Player p){
        Vars.coins.remove(p.getName());
        Vars.coinsTime.remove(p.getName());
    }

    public static void saveRides(int id){
        Connection c = null;
        PreparedStatement ps = null;
        String saveData = "UPDATE rides SET status=? WHERE id=?";

        try{
            c = hikari.getConnection();
            ps = c.prepareStatement(saveData);

            ps.setString(1, Vars.rideStatus.get(id));
            ps.setInt(2, id);

            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void loadRides(){
        Vars.rideName.clear();
        Vars.rideStatus.clear();
        Vars.rideLocation.clear();

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String load = "SELECT * FROM rides;";

        try{
            c = hikari.getConnection();
            ps = c.prepareStatement(load);
            rs = ps.executeQuery();

            int slot = 0;
            while(rs.next()){
                Vars.rideName.put(rs.getInt("id"), rs.getString("name"));
                Location loc = MiscUtils.formatLocation(rs.getString("location"));
                Vars.rideLocation.put(rs.getInt("id"), loc);
                Vars.rideStatus.put(rs.getInt("id"), rs.getString("status"));
                Vars.rideSlot.put(slot, rs.getInt("id"));
                Vars.rideLook.put(rs.getInt("id"), rs.getString("yaw_pitch"));
                slot = slot + 1;
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void loadVars(){
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String load = "SELECT * FROM signlink;";

        try {
            c = hikari.getConnection();
            ps = c.prepareStatement(load);
            rs = ps.executeQuery();

            while (rs.next()) {
                SignLinkEvent.variableValues.put(rs.getString("variable"), rs.getString("value"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void saveVars(String var, String value){
        Connection c = null;
        PreparedStatement ps = null;
        String load = "INSERT INTO signlink VALUES (?,?) ON DUPLICATE KEY UPDATE variable=?;";

        try{
            c = hikari.getConnection();
            ps = c.prepareStatement(load);

            ps.setString(1, var);
            ps.setString(2, value);
            ps.setString(3, var);

            ps.execute();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if(c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
   }
}
