package com.shadebyte.monthlycrates.language;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 6/27/2018
 * Time Created: 8:11 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public enum Lang {

    PREFIX("prefix"),
    HELP("help"),

    CREATE_COMMAND("commands.create"),
    REMOVE_COMMAND("commands.remove"),
    EDIT_COMMAND("commands.edit"),

    NO_PERMISSION("nopermission"),
    PLAYERS_ONLY("playersonly"),
    INVALID_SUBCOMMAND("invalidsubcommand"),

    CRATE_CREATED("crate.created"),
    CRATE_REMOVED("crate.removed"),
    CRATE_SAVED("crate.saved"),
    CRATE_GIVE("crate.give"),
    CRATE_GIVEALL("crate.giveall"),
    CRATE_EXIST("crate.exist"),
    CRATE_MISSING("crate.missing"),


    ;

    private String node;

    Lang(String node) {
        this.node = node;
    }

    public String getNode() {
        return node;
    }
}
