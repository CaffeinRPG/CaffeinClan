package tokyo.ramune.caffeinclan;

import org.bukkit.plugin.java.JavaPlugin;
import tokyo.ramune.caffeinclan.command.CommandManager;
import tokyo.ramune.caffeinclan.command.TabComleterManager;
import tokyo.ramune.caffeinclan.database.Config;
import tokyo.ramune.caffeinclan.database.DataBaseManager;
import tokyo.ramune.caffeinclan.database.MySQL;
import tokyo.ramune.caffeinclan.listener.MenuListener;
import tokyo.ramune.caffeinclan.listener.PlayerJoinListener;
import tokyo.ramune.caffeinclan.listener.PlayerQuitListener;

public final class CaffeinClan extends JavaPlugin {

    private static JavaPlugin plugin;
    public static Config config;

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();
        config = new Config(getConfig());

        MySQL.connect(true);
        DataBaseManager.createTable();

        this.getServer().getPluginManager().registerEvents(new MenuListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        this.getCommand("clan").setExecutor(new CommandManager());
        this.getCommand("clan").setTabCompleter(new TabComleterManager());

        getLogger().info("The plugin has been enabled.");
    }

    @Override
    public void onDisable() {
        MySQL.disconnect();
        getLogger().info("The plugin has been disabled.");
    }

    public static JavaPlugin getInstance() {
        return plugin;
    }

}
