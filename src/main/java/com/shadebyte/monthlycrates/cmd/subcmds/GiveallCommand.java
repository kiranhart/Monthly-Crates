package com.shadebyte.monthlycrates.cmd.subcmds;

import com.shadebyte.monthlycrates.Core;
import com.shadebyte.monthlycrates.api.CrateAPI;
import com.shadebyte.monthlycrates.api.enums.Permissions;
import com.shadebyte.monthlycrates.cmd.SubCommand;
import com.shadebyte.monthlycrates.crate.Crate;
import com.shadebyte.monthlycrates.language.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 6/28/2018
 * Time Created: 11:59 AM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public class GiveallCommand extends SubCommand {

    @Override
    public void onCommand(CommandSender sender, String[] args) {

        if (!sender.hasPermission(Permissions.GIVEALL_CMD.getNode())) {
            sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.NO_PERMISSION.getNode()));
            return;
        }

        if (args.length == 1) {
            sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.GIVEALL_COMMAND.getNode()));
            return;
        }

        if (args.length == 2) {
            String name = args[1].toLowerCase();
            if (!Crate.getInstance(name).exist()) {
                sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.CRATE_MISSING.getNode()));
            } else {
                sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.GIVEALL_COMMAND.getNode()));
            }
        }

        if (args.length == 3) {
            String name = args[1].toLowerCase();
            if (!Crate.getInstance(name).exist()) {
                sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.CRATE_MISSING.getNode()));
            } else {
                if (CrateAPI.getInstance().isInteger(args[2])) {
                    Bukkit.getOnlinePlayers().forEach(p -> {
                        for (int i = 0; i < Integer.parseInt(args[2]); i++) {
                            p.getInventory().addItem(Crate.getInstance(args[1].toLowerCase()).getItemStack(p));
                        }
                        p.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.CRATE_RECEIVED.getNode()).replace("{player}", sender.getName()));
                    });
                    sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.CRATE_GIVEALL.getNode()).replace("{crate_name}", args[1]).replace("{amount}", args[2]));
                } else {
                    sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.NOT_A_NUMBER.getNode()));
                }
            }
        }
    }

    @Override
    public String name() {
        return Core.getInstance().getCommandManager().giveall;
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
