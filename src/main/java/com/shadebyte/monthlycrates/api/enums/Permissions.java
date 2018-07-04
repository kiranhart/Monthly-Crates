package com.shadebyte.monthlycrates.api.enums;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 6/27/2018
 * Time Created: 7:48 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public enum Permissions {

    BASE("MonthlyCrates"),
    ADMIN(BASE.getNode() + ".admin"),
    HELP_CMD(BASE.getNode() + ".cmd.help"),
    CREATE_CMD(BASE.getNode() + ".cmd.create"),
    REMOVE_CMD(BASE.getNode() + ".cmd.remove"),
    EDIT_CMD(BASE.getNode() + ".cmd.edit"),
    GIVE_CMD(BASE.getNode() + ".cmd.give"),
    GIVEALL_CMD(BASE.getNode() + ".cmd.giveall"),
    LIST_CMD(BASE.getNode() + ".cmd.list"),

    ;

    private String node;

    Permissions(String node) {
        this.node = node;
    }

    public String getNode() {
        return node;
    }
}
