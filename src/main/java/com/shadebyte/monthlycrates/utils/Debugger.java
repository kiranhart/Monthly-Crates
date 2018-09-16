package com.shadebyte.monthlycrates.utils;

import com.shadebyte.monthlycrates.Core;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import static org.bukkit.ChatColor.translateAlternateColorCodes;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 6/27/2018
 * Time Created: 7:57 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public class Debugger {

    public static void report(Exception e) {
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        if (debugEnabled()) {
            console.sendMessage(translateAlternateColorCodes('&', "&b================================================================"));
            console.sendMessage(translateAlternateColorCodes('&', "&eMonthly Crates has run into an error. Report this to the author."));
            console.sendMessage(translateAlternateColorCodes('&', "&b----------------------------------------------------------------"));
            e.printStackTrace();
            console.sendMessage(translateAlternateColorCodes('&', "&b================================================================"));
        }
    }

    private static boolean debugEnabled() {
        return Core.getInstance().getConfig().getBoolean("debugger");
    }
}
