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

package me.Cooltimmetje.PretparkCore;

import me.Cooltimmetje.PretparkCore.Commands.*;
import me.Cooltimmetje.PretparkCore.Events.GadgetTriggers;
import me.Cooltimmetje.PretparkCore.Events.InventoryTriggers;
import me.Cooltimmetje.PretparkCore.Events.JoinQuitEvent;
import me.Cooltimmetje.PretparkCore.Events.ServerPingEvent;
import me.Cooltimmetje.PretparkCore.Events.UserInterfaces.*;
import me.Cooltimmetje.PretparkCore.Managers.*;
import me.Cooltimmetje.PretparkCore.MysqlManager.Database;
import me.Cooltimmetje.PretparkCore.RemoteControl.ControlCommand;
import me.Cooltimmetje.PretparkCore.RemoteControl.RideControl.BlackCobraControl;
import me.Cooltimmetje.PretparkCore.RemoteControl.SignLinkController;
import me.Cooltimmetje.PretparkCore.RemoteControl.SignLinkEvent;
import me.Cooltimmetje.PretparkCore.Timers.CoinsGiver;
import me.Cooltimmetje.PretparkCore.Timers.DataSaver;
import me.Cooltimmetje.PretparkCore.Utilities.PlayerUtils;
import me.Cooltimmetje.PretparkCore.Utilities.ScoreboardUtils;
import me.Cooltimmetje.PretparkCore.Utilities.Vars;
import me.Cooltimmetje.PretparkCore.Utilities.WorldUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This class has been created on 28-7-2015 at 18:40 by cooltimmetje.
 */

public class Main extends JavaPlugin {

    private long loadStart;
    private static Plugin plugin;

    @Override
    public void onEnable(){
        plugin = this;
        this.saveDefaultConfig();
        getLogger().info("Starting plugin load protocol... Please wait...");
        startLoad();

        /* PRE SETUP START */
        GadgetManager.createGadgets();
        KledingManager.setCategories();
        /* PRE SETUP END */

        getLogger().info("Registering events...");
        /* EVENT START */
            registerEvents(this
                    , new JoinQuitEvent(), new InventoryManager()
                    , new InventoryTriggers(), new ProfileUI()
                    , new RideUI(), new SwagUI()
                    , new GadgetUI(), new GadgetTriggers()
                    , new ResourcePackManager(), new SignLinkEvent()
                    , new BlackCobraControl(), new ControlUI()
                    , new ServerPingEvent(), new NpcManager()
                    , new ChatManager(), new KledingUI()
            );
        /* EVENT END */

        getLogger().info("Registering commands...");
        /* COMMAND START */
        getCommand("fixgamemodes").setExecutor(new FixGamemode());
        getCommand("coins").setExecutor(new CoinCommand());
        getCommand("resetinv").setExecutor(new ResetInventory());
        getCommand("givecoins").setExecutor(new AdminCoinCommand());
        getCommand("masscoins").setExecutor(new AdminCoinCommand());
        getCommand("takecoins").setExecutor(new AdminCoinCommand());
        getCommand("setcoins").setExecutor(new AdminCoinCommand());
        getCommand("listrides").setExecutor(new RideCommands());
        getCommand("changeride").setExecutor(new RideCommands());
        getCommand("reloadrides").setExecutor(new RideCommands());
        getCommand("listvars").setExecutor(new SignLinkEvent());
        getCommand("control").setExecutor(new ControlCommand());
        getCommand("togglem").setExecutor(new MaintenanceCommand());
        getCommand("cc").setExecutor(new ClearChatCommand());
        /* COMMAND END */

        getLogger().info("Opening API hooks...");
        /* API START */
        hookAPI("ResourcePackApi");
        hookAPI("SignEdit");
        hookAPI("PCReloader");
        hookAPI("HolographicDisplays");
        /* API END */

        getLogger().info("Setting up...");
        /* SETUP START */
        Vars.setGlobaldata();
        Database.connectToDatabase();
        for(Player p : Bukkit.getOnlinePlayers()){
            Database.loadData(p);
            Database.loadSettings(p);
            PlayerUtils.fixGamemode(p);
            ScoreboardUtils.constructScoreboard(p);
            WorldUtils.updateSpawnSigns(p);
        }
        DataSaver.dataSaver();
        CoinsGiver.coinsGiver();
        Database.loadRides();
        Database.loadVars();
        SignLinkController.setup();
        MaintenanceManager.fix();
        NpcManager.spawnNPCs();
        HologramManager.spawnHolograms();
        /* SETUP STOP */

        getLogger().info("Plugin loading succeeded! The plugin is now ready for use. (Loadtime: " + stopLoad() + "ms)");
    }

    @Override
    public void onDisable(){
        for(Player p : Bukkit.getOnlinePlayers()){
            ScoreboardUtils.destroyScoreboard(p);
            Database.saveData(p, true);
        }
        NpcManager.removeNPCs();

        Vars.saveUp();



        DataSaver.saveData();
        Database.close();
        plugin = null;
    }

    public void startLoad() {
        loadStart = System.currentTimeMillis();
    }

    public long stopLoad(){
        return System.currentTimeMillis() - loadStart;
    }

    public static void registerEvents(Plugin plugin, Listener... listeners) {
        for(Listener listener : listeners){
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    /*public void registerCommand(String cmd, CommandExecutor executor){
        getCommand(cmd).setExecutor(executor);
    }*/

    public void hookAPI(String api){
        if (getServer().getPluginManager().getPlugin(api) != null && getServer().getPluginManager().getPlugin(api).isEnabled())
            getLogger().info("Successfully hooked into " + api + "!");
        else {
            getLogger().warning("Failed to hook into " + api + ", disabling plugin!");
            getPluginLoader().disablePlugin(this);
        }
    }

    public static Plugin getPlugin(){
        return plugin;
    }

}
