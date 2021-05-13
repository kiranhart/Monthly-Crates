package ca.tweetzy.monthlycrates;

import ca.tweetzy.core.TweetyCore;
import ca.tweetzy.core.TweetyPlugin;
import ca.tweetzy.core.commands.CommandManager;
import ca.tweetzy.core.compatibility.ServerProject;
import ca.tweetzy.core.compatibility.ServerVersion;
import ca.tweetzy.core.compatibility.XMaterial;
import ca.tweetzy.core.configuration.Config;
import ca.tweetzy.core.gui.GuiManager;
import ca.tweetzy.core.utils.Metrics;
import ca.tweetzy.monthlycrates.settings.Settings;
import co.aikar.taskchain.BukkitTaskChainFactory;
import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainFactory;
import lombok.Getter;

import java.util.List;

/**
 * The current file has been created by Kiran Hart
 * Date Created: May 13 2021
 * Time Created: 2:00 p.m.
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise
 */
public class MonthlyCrates extends TweetyPlugin {

    private static TaskChainFactory taskChainFactory;

    @Getter
    private static MonthlyCrates instance;

    @Getter
    private final Config data = new Config(this, "data.yml");

    @Getter
    private GuiManager guiManager;

    @Getter
    private CommandManager commandManager;

    Metrics metrics;

    @Override
    public void onPluginLoad() {
        instance = this;
    }

    @Override
    public void onPluginEnable() {
        TweetyCore.registerPlugin(this, 5, XMaterial.ENDER_CHEST.name());

        // Stop the plugin if the server version is not 1.8 or higher
        if (ServerVersion.isServerVersionAtOrBelow(ServerVersion.V1_7)) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Check server type
        if (ServerProject.isServer(ServerProject.CRAFTBUKKIT, ServerProject.GLOWSTONE, ServerProject.TACO, ServerProject.UNKNOWN)) {
            // I can't remember if spigot allows me to disable a plugin if its running a specific jar so just tell them AGAIN
            // that they're running a non-supported server project even though the plugin page will state it....
            getLogger().severe("You're running Monthly Crates on a non-supported server project. There is no guarantee anything will work.");
        }

        taskChainFactory = BukkitTaskChainFactory.create(this);

        // Setup the settings file
        Settings.setup();

        // Setup the locale
        setLocale(Settings.LANG.getString(), false);

        // Load the data file
        this.data.load();

        // Managers
        this.guiManager = new GuiManager(this);
        this.commandManager = new CommandManager(this);
        this.guiManager.init();

        // Listeners

        // Commands

        this.metrics = new Metrics(this, 7689);
    }

    @Override
    public void onPluginDisable() {

    }

    @Override
    public void onConfigReload() {

    }

    @Override
    public List<Config> getExtraConfig() {
        return null;
    }

    public static <T> TaskChain<T> newChain() {
        return taskChainFactory.newChain();
    }
}
