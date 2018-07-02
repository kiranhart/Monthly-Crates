package com.shadebyte.monthlycrates.cmd.subcmds;

import com.shadebyte.monthlycrates.Core;
import com.shadebyte.monthlycrates.cmd.SubCommand;
import com.shadebyte.monthlycrates.crate.Crate;
import com.shadebyte.monthlycrates.api.enums.Permissions;
import com.shadebyte.monthlycrates.inventory.inventories.CrateEditInventory;
import com.shadebyte.monthlycrates.language.Lang;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 6/28/2018
 * Time Created: 11:59 AM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public class EditCommand extends SubCommand {

    @Override
    public void onCommand(CommandSender sender, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.PLAYERS_ONLY.getNode()));
            return;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(Permissions.EDIT_CMD.getNode())) {
            player.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.NO_PERMISSION.getNode()));
            return;
        }

        if (args.length == 1) {
            player.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.EDIT_COMMAND.getNode()));
            return;
        }

        if (args.length == 2) {
            String name = args[1].toLowerCase();
            if (Crate.getInstance(name).exist()) {
                if (Core.getInstance().editingCrate.containsKey(player.getUniqueId())) {
                    Core.getInstance().editingCrate.remove(player.getUniqueId());
                }

                player.openInventory(CrateEditInventory.getInstance(name, player).getInventory());
                Core.getInstance().editingCrate.put(player.getUniqueId(), name);
            }
        }
    }

    @Override
    public String name() {
        return Core.getInstance().getCommandManager().edit;
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
