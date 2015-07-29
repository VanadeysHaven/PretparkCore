package me.Cooltimmetje.PretparkCore;

import me.Cooltimmetje.PretparkCore.Commands.CoinCommand;
import me.Cooltimmetje.PretparkCore.Commands.FixGamemode;
import me.Cooltimmetje.PretparkCore.Commands.ResetInventory;
import me.Cooltimmetje.PretparkCore.Events.InventoryTriggers;
import me.Cooltimmetje.PretparkCore.Events.JoinQuitEvent;
import me.Cooltimmetje.PretparkCore.Events.UserInterfaces.ProfileUI;
import me.Cooltimmetje.PretparkCore.Managers.InventoryManager;
import me.Cooltimmetje.PretparkCore.MysqlManager.Database;
import me.Cooltimmetje.PretparkCore.Timers.CoinsGiver;
import me.Cooltimmetje.PretparkCore.Timers.DataSaver;
import me.Cooltimmetje.PretparkCore.Utilities.PlayerUtils;
import me.Cooltimmetje.PretparkCore.Utilities.ScoreboardUtils;
import me.Cooltimmetje.PretparkCore.Utilities.Vars;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
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

        getLogger().info("Registering events...");
        /* EVENT START */
            registerEvents(this,
                    new JoinQuitEvent(),
                    new InventoryManager(),
                    new InventoryTriggers(),
                    new ProfileUI()
            );
        /* EVENT END */

        getLogger().info("Registering commands...");
        /* COMMAND START */
        getCommand("fixgamemodes").setExecutor(new FixGamemode());
        getCommand("coins").setExecutor(new CoinCommand());
        getCommand("resetinv").setExecutor(new ResetInventory());
        /* COMMAND END */

        getLogger().info("Opening API hooks...");
        /* API START */
//        hookAPI("TitleManager");
        /* API END */

        getLogger().info("Setting up...");
        /* SETUP START */
        Vars.setGlobaldata();
        Database.connectToDatabase();
        for(Player p : Bukkit.getOnlinePlayers()){
            Database.loadData(p);
            PlayerUtils.fixGamemode(p);
            ScoreboardUtils.constructScoreboard(p);
        }
        DataSaver.dataSaver();
        CoinsGiver.coinsGiver();
        /* SETUP STOP */

        getLogger().info("Plugin loading succeeded! The plugin is now ready for use. (Loadtime: " + stopLoad() + "ms)");
    }

    @Override
    public void onDisable(){
        for(Player p : Bukkit.getOnlinePlayers()){
            ScoreboardUtils.destroyScoreboard(p);
            Database.saveData(p, true);
        }

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

    public void registerCommand(String cmd, CommandExecutor executor){
        getCommand(cmd).setExecutor(executor);
    }

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