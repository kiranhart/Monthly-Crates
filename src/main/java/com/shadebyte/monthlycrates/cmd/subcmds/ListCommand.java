package com.shadebyte.monthlycrates.cmd.subcmds;

import com.shadebyte.monthlycrates.Core;
import com.shadebyte.monthlycrates.api.enums.Permissions;
import com.shadebyte.monthlycrates.cmd.SubCommand;
import com.shadebyte.monthlycrates.inventory.inventories.CrateListInventory;
import com.shadebyte.monthlycrates.language.Lang;
import com.shadebyte.monthlycrates.utils.Debugger;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 6/27/2018
 * Time Created: 8:40 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public class ListCommand extends SubCommand {

    @Override
    public void onCommand(CommandSender sender, String[] args) {

        if (!sender.hasPermission(Permissions.LIST_CMD.getNode())) {
            sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.NO_PERMISSION.getNode()));
            return;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.PLAYERS_ONLY.getNode()));
            return;
        }

        Player p = (Player) sender;

        try {
            ConfigurationSection section = Core.getCrates().getConfig().getConfigurationSection("crates");

            if (section.getKeys(false).size() == 0) {
                p.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.LISTING_NONE.getNode()));
                return;
            }

            p.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.LISTING_FOUND.getNode()).replace("{amount}", String.valueOf(section.getKeys(false).size())));
            p.openInventory(CrateListInventory.getInstance(p).getInventory());
        } catch (Exception e) {
            Debugger.report(e);
        }
    }

    @Override
    public String name() {
        return Core.getInstance().getCommandManager().list;
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
