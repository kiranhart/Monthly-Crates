package com.shadebyte.monthlycrates.cmd.subcmds;

import com.shadebyte.monthlycrates.Core;
import com.shadebyte.monthlycrates.cmd.SubCommand;
import com.shadebyte.monthlycrates.api.enums.Permissions;
import com.shadebyte.monthlycrates.language.Lang;
import org.bukkit.command.CommandSender;
import static org.bukkit.ChatColor.translateAlternateColorCodes;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 6/27/2018
 * Time Created: 8:40 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public class HelpCommand extends SubCommand {

    @Override
    public void onCommand(CommandSender sender, String[] args) {

        if (!sender.hasPermission(Permissions.HELP_CMD.getNode())) {
            sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.NO_PERMISSION.getNode()));
            return;
        }

        sender.sendMessage(translateAlternateColorCodes('&', "&e=========================================="));
        sender.sendMessage(translateAlternateColorCodes('&', ""));
        sender.sendMessage(translateAlternateColorCodes('&', "&7/MonthlyCrates create <crate name>"));
        sender.sendMessage(translateAlternateColorCodes('&', "&7/MonthlyCrates remove <crate name>"));
        sender.sendMessage(translateAlternateColorCodes('&', "&7/MonthlyCrates edit <crate name>"));
        sender.sendMessage(translateAlternateColorCodes('&', "&7/MonthlyCrates give <player> <crate> <#>"));
        sender.sendMessage(translateAlternateColorCodes('&', "&7/MonthlyCrates giveall <crate> <#>"));
        sender.sendMessage(translateAlternateColorCodes('&', "&7/MonthlyCrates list"));
        sender.sendMessage(translateAlternateColorCodes('&', ""));
        sender.sendMessage(translateAlternateColorCodes('&', "&e=========================================="));
    }

    @Override
    public String name() {
        return Core.getInstance().getCommandManager().help;
    }

    @Override
    public String info() {
        return null;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
