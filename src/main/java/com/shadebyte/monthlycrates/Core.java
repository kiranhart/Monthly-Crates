package com.shadebyte.monthlycrates;

import com.shadebyte.monthlycrates.cmd.CommandManager;
import com.shadebyte.monthlycrates.language.Locale;
import com.shadebyte.monthlycrates.listeners.MGUIListener;
import com.shadebyte.monthlycrates.utils.ConfigWrapper;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    //Instance variable.
    private static Core instance;

    //Configuration Files
    private static ConfigWrapper crates;

    //Locale
    private Locale locale;

    //Settings
    private Settings settings = null;

    //Command Manager
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

        //Initialize the instance value to this class
        instance = this;
        initFiles();

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        //Locales
        Locale.init(this);
        Locale.saveDefaultLocale("en_US");
        this.locale = Locale.getLocale(this.getConfig().getString("Locale", "en_US"));

        initEvents();

        settings = new Settings();
        commandManager = new CommandManager();

        commandManager.initialize();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        //Remove initialization from instance
        instance = null;
    }

    private void initFiles() {
        crates = new ConfigWrapper(this, "", "crates.yml");
        crates.saveConfig();
    }

    private void initEvents() {
        PluginManager p = Bukkit.getPluginManager();
        p.registerEvents(new MGUIListener(), this);
    }

    /**
     * @return the instance value
     */
    public static Core getInstance() {
        return instance;
    }

    /**
     * @return the crates file.
     */
    public static ConfigWrapper getCrates() {
        return crates;
    }

    /**
     * @return the Locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * @return settings
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * @return command manager
     */
    public CommandManager getCommandManager() {
        return commandManager;
    }
}
