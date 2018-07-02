package com.shadebyte.monthlycrates.api.task;

import com.shadebyte.monthlycrates.api.CrateAPI;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 7/2/2018
 * Time Created: 2:27 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public class FinalAnimationTask extends BukkitRunnable {

    private static int[][] columns = {
            {0, 9, 18, 27, 36, 45},
            {1, 10, 19, 28, 37, 46},
            {2, 11, 20, 29, 38, 47},
            {3, 39, 48},
            {4, 40},
            {5, 41, 50},
            {6, 15, 24, 33, 42, 51},
            {7, 16, 25, 34, 43, 52},
            {8, 17, 26, 35, 44, 53},
    };

    private static int[][] designSlots = {
            {0, 9, 18, 28, 37, 46, 2, 11, 20, 39, 48, 4, 41, 50, 6, 15, 24, 24, 43, 52, 8, 17, 26, 34},
            {27, 26, 45, 29, 38, 47, 40, 33, 42, 51, 35, 44, 53, 1, 10, 19, 3, 5, 7, 16, 25, 36}
    };

    //0 8
    //1 7
    //2 6
    //3 5 4

    private Inventory inventory;
    private int stage = 0;
    private int part = 0;

    public FinalAnimationTask(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public void run() {

        if (stage == 0) {
            for (int[] column : columns) {
                for (int i : column) {
                    inventory.setItem(i, CrateAPI.getInstance().filler(9));
                }
            }
            stage = 1;
        }

        if (stage == 1) {
            part++;
            part(5);
        }

        if (stage == 2) {
            part++;
            part(4);
        }

        if (stage == 3) {
            part++;
            part(1);
        }

        if (stage == 4) {
            part++;
            part(14);
        }

        if (stage == 5) {
            part = 0;
            stage = 0;
            design();
            this.cancel();
        }

    }

    private void set(int[] slots, int color) {
        for (int slot : slots) {
            inventory.setItem(slot, CrateAPI.getInstance().filler(color));
        }
    }

    private void part(int color) {
        if (part == 1) {
            set(columns[0], color);
            set(columns[8], color);
        }

        if (part == 2) {
            set(columns[1], color);
            set(columns[7], color);
        }

        if (part == 3) {
            set(columns[2], color);
            set(columns[6], color);
        }

        if (part == 4) {
            set(columns[3], color);
            set(columns[5], color);
            set(columns[4], color);
            part = 0;
            stage = stage + 1;
        }
    }

    private void design() {
        inventory.setItem(49, CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0));
        for (int i : designSlots[0]) {
            inventory.setItem(i, CrateAPI.getInstance().filler(5));
        }

        for (int i : designSlots[1]) {
            inventory.setItem(i, CrateAPI.getInstance().filler(3));
        }
    }
}
