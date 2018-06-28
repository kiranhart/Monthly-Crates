package com.shadebyte.monthlycrates.enums;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 6/27/2018
 * Time Created: 7:48 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public enum Permissions {

    BASE("MonthlyCrates");

    private String node;

    Permissions(String node) {
        this.node = node;
    }

    public String getNode() {
        return node;
    }
}
