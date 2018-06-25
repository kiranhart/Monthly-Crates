package com.shadebyte.monthlycrates;

import com.shadebyte.monthlycrates.utils.ConfigWrapper;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Core extends JavaPlugin {

    //Instance variable.
    private static Core instance;

    //Configuration Files
    private static ConfigWrapper crates;

    @Override
    public void onEnable() {
        // Plugin startup logic

        //Initialize the instance value to this class
        instance = this;
        initFiles();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        //Remove initialization from instance
        instance = null;
    }

    private void initFiles () {
        crates = new ConfigWrapper(this, "", "crates.yml");
        crates.saveConfig();
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

    public static void main(String[] args) {

    }
}
