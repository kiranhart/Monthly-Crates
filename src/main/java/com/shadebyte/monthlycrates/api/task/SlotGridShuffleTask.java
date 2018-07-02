package com.shadebyte.monthlycrates.api.task;

import com.shadebyte.monthlycrates.Core;
import com.shadebyte.monthlycrates.api.CrateAPI;
import com.shadebyte.monthlycrates.crate.Crate;
import com.shadebyte.monthlycrates.crate.CratePane;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 7/1/2018
 * Time Created: 7:01 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public class SlotGridShuffleTask extends BukkitRunnable {

    private String crate;
    private Inventory inventory;
    private int[] slots;
    private int clickedSlot;
    private CratePane pane;
    private int timer = 0;
    private int endTimer;

    public SlotGridShuffleTask(String crate, Inventory inventory, int[] slots, int clickedSlot, int endTimer) {
        this.crate = crate;
        this.inventory = inventory;
        this.slots = slots;
        this.clickedSlot = clickedSlot;
        this.endTimer = endTimer;
        switch (clickedSlot) {
            case 12:
                pane = CratePane.ONE;
                break;
            case 13:
                pane = CratePane.TWO;
                break;
            case 14:
                pane = CratePane.THREE;
                break;
            case 21:
                pane = CratePane.FOUR;
                break;
            case 22:
                pane = CratePane.FIVE;
                break;
            case 23:
                pane = CratePane.SIX;
                break;
            case 30:
                pane = CratePane.SEVEN;
                break;
            case 31:
                pane = CratePane.EIGHT;
                break;
            case 32:
                pane = CratePane.NINE;
                break;
        }
    }

    @Override
    public void run() {
        timer++;
        Arrays.stream(slots).forEach(slot -> inventory.setItem(slot, CrateAPI.getInstance().filler(ThreadLocalRandom.current().nextInt(0, 16))));

        try {
            inventory.setItem(clickedSlot, Crate.getInstance(crate).getPaneItems(pane).get(ThreadLocalRandom.current().nextInt(Crate.getInstance(crate).getPaneItems(pane).size())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (timer >= endTimer) {
            timer = 0;
            this.cancel();
            if (isDone()) {
                new FinalAnimationTask(inventory).runTaskTimer(Core.getInstance(), 0, 5);
            }
        }
    }

    private boolean isDone() {
        if (!similar(12) && !similar(13) && !similar(14) && !similar(21) && !similar(22) && !similar(23) && !similar(30) && !similar(31) && !similar(32)) {
            return true;
        }
        return false;
    }

    private boolean similar(int slot) {
        return inventory.getItem(slot).isSimilar(CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0));
    }
}
