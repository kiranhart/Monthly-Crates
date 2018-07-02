package com.shadebyte.monthlycrates.crate;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 6/25/2018
 * Time Created: 4:24 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public enum CratePane {

    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10");

    private String val;

    CratePane(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }
}
