package com.shadebyte.monthlycrates.cmd.subcmds;

import com.shadebyte.monthlycrates.Core;
import com.shadebyte.monthlycrates.cmd.SubCommand;
import com.shadebyte.monthlycrates.crate.Crate;
import com.shadebyte.monthlycrates.api.enums.Permissions;
import com.shadebyte.monthlycrates.language.Lang;
import org.bukkit.command.CommandSender;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 6/28/2018
 * Time Created: 11:59 AM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public class CreateCommand extends SubCommand {

    @Override
    public void onCommand(CommandSender sender, String[] args) {

        if (!sender.hasPermission(Permissions.CREATE_CMD.getNode())) {
            sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.NO_PERMISSION.getNode()));
            return;
        }

        if (args.length == 1) {
            sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.CREATE_COMMAND.getNode()));
            return;
        }

        if (args.length == 2) {
            String name = args[1].toLowerCase();
            if (Crate.getInstance(name).exist()) {
                sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.CRATE_EXIST.getNode()));
            } else {
                Crate.getInstance(name).create();
                sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.CRATE_CREATED.getNode()).replace("{crate_name}", name.toLowerCase()));
            }
        }
    }

    @Override
    public String name() {
        return Core.getInstance().getCommandManager().create;
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
