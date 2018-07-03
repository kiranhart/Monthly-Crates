package com.shadebyte.monthlycrates.cmd.subcmds;

import com.shadebyte.monthlycrates.Core;
import com.shadebyte.monthlycrates.api.CrateAPI;
import com.shadebyte.monthlycrates.api.enums.Permissions;
import com.shadebyte.monthlycrates.cmd.SubCommand;
import com.shadebyte.monthlycrates.crate.Crate;
import com.shadebyte.monthlycrates.language.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 6/28/2018
 * Time Created: 11:59 AM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public class GiveCommand extends SubCommand {

    @Override
    public void onCommand(CommandSender sender, String[] args) {

        if (!sender.hasPermission(Permissions.GIVE_CMD.getNode())) {
            sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.NO_PERMISSION.getNode()));
            return;
        }

        if (args.length == 1) {
            sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.GIVE_COMMAND.getNode()));
            return;
        }

        if (args.length == 2) {
            if (!Crate.getInstance(args[1].toLowerCase()).exist()) {
                sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.CRATE_MISSING.getNode()));
            } else {
                sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.GIVE_COMMAND.getNode()));
            }
        }

        if (args.length == 3) {
            if (!Crate.getInstance(args[1].toLowerCase()).exist()) {
                sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.CRATE_MISSING.getNode()));
            } else {
                Player target = Bukkit.getPlayerExact(args[2]);
                if (target != null) {
                    sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.GIVE_COMMAND.getNode()));
                } else {
                    sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.PLAYER_OFFLINE.getNode()));
                }
            }
        }

        if (args.length == 4) {
            if (!Crate.getInstance(args[1].toLowerCase()).exist()) {
                sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.CRATE_MISSING.getNode()));
            } else {
                Player target = Bukkit.getPlayerExact(args[2]);
                if (target != null) {
                    if (CrateAPI.getInstance().isInteger(args[3])) {
                        for (int i = 0; i < Integer.parseInt(args[3]); i++) {
                            target.getInventory().addItem(Crate.getInstance(args[1].toLowerCase()).getItemStack(target));
                        }
                        target.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.CRATE_RECEIVED.getNode()).replace("{player}", sender.getName()));
                        sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.CRATE_GIVE.getNode()).replace("{crate_name}", args[1]).replace("{amount}", args[3]).replace("{player}", target.getName()));
                    } else {
                        sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.NOT_A_NUMBER.getNode()));
                    }
                } else {
                    sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.PLAYER_OFFLINE.getNode()));
                }
            }
        }
    }

    @Override
    public String name() {
        return Core.getInstance().getCommandManager().give;
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
