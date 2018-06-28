package com.shadebyte.monthlycrates.utils;

import com.shadebyte.monthlycrates.Core;
import static org.bukkit.ChatColor.translateAlternateColorCodes;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 6/27/2018
 * Time Created: 7:57 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public class Debugger {

    public static void report(Exception e) {
        if (debugEnabled()) {
            System.out.println(translateAlternateColorCodes('&', "&b================================================================"));
            System.out.println(translateAlternateColorCodes('&', "&eMonthly Crates has ran into an error, report this to the author."));
            System.out.println(translateAlternateColorCodes('&', "&b----------------------------------------------------------------"));
            e.printStackTrace();
            System.out.println(translateAlternateColorCodes('&', "&b================================================================"));
        }
    }

    private static boolean debugEnabled() {
        return Core.getInstance().getConfig().getBoolean("debugger");
    }
}
