package com.shadebyte.monthlycrates.cmd;

import com.shadebyte.monthlycrates.Core;
import com.shadebyte.monthlycrates.cmd.subcmds.*;
import com.shadebyte.monthlycrates.api.enums.Permissions;
import com.shadebyte.monthlycrates.language.Lang;
import com.shadebyte.monthlycrates.utils.Debugger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 6/27/2018
 * Time Created: 7:41 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public class CommandManager implements CommandExecutor {

    private List<SubCommand> commands = new ArrayList<>();

    public CommandManager() {
    }

    public final String main = "monthlycrates";

    public final String help = "help";
    public final String create = "create";
    public final String remove = "remove";
    public final String edit = "edit";
    public final String list = "list";
    public final String giveall = "giveall";
    public final String give = "give";

    public void initialize() {
        Core.getInstance().getCommand(main).setExecutor(this);

        this.commands.add(new HelpCommand());
        this.commands.add(new CreateCommand());
        this.commands.add(new RemoveCommand());
        this.commands.add(new EditCommand());
        this.commands.add(new GiveallCommand());
        this.commands.add(new ListCommand());
        this.commands.add(new GiveCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission(Permissions.BASE.getNode())) {
            sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.NO_PERMISSION.getNode()));
            return true;
        }

        //Main command text
        if (command.getName().equalsIgnoreCase(main)) {
            if (args.length == 0) {
                sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.HELP.getNode()));
                return true;
            }

            //Handle Sub commands
            SubCommand target = this.getSubcommand(args[0]);

            if (target == null) {
                sender.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.INVALID_SUBCOMMAND.getNode()));
                return true;
            }

            List<String> list = new ArrayList<>();
            list.addAll(Arrays.asList(args));
            list.remove(0);

            try {
                target.onCommand(sender, args);
            } catch (Exception e) {
                Debugger.report(e);
            }
        }

        return true;
    }

    private SubCommand getSubcommand(String name) {
        Iterator<SubCommand> subcommands = this.commands.iterator();
        while (subcommands.hasNext()) {
            SubCommand sc = subcommands.next();

            if (sc.name().equalsIgnoreCase(name)) {
                return sc;
            }

            String[] aliases;
            int length = (aliases = sc.aliases()).length;

            for (int var5 = 0; var5 < length; ++var5) {
                String alias = aliases[var5];
                if (name.equalsIgnoreCase(alias)) {
                    return sc;
                }

            }
        }
        return null;
    }
}
