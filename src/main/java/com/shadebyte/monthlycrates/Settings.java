package com.shadebyte.monthlycrates;

import com.shadebyte.monthlycrates.language.Lang;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 6/27/2018
 * Time Created: 8:18 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public class Settings {

    private String prefix;

    public Settings() {
        prefix = Core.getInstance().getLocale().getMessage(Lang.PREFIX.getNode()) + " ";
    }

    public String getPrefix() {
        return prefix;
    }
}
