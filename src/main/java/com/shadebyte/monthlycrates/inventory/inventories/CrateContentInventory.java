package com.shadebyte.monthlycrates.inventory.inventories;

import com.shadebyte.monthlycrates.Core;
import com.shadebyte.monthlycrates.api.CrateAPI;
import com.shadebyte.monthlycrates.api.task.SlotGridShuffleTask;
import com.shadebyte.monthlycrates.crate.Crate;
import com.shadebyte.monthlycrates.crate.CratePane;
import com.shadebyte.monthlycrates.inventory.MGUI;
import com.shadebyte.monthlycrates.utils.Debugger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 7/1/2018
 * Time Created: 6:36 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public class CrateContentInventory implements MGUI {

    private static int[] normalItemSlots = {12, 13, 14, 21, 22, 23, 30, 31, 32};
    private static int[][] slotAnimationSlots = {
            {3, 39, 48, 9, 10, 11, 15, 16, 17}, {4, 40, 9, 10, 11, 15, 16, 17}, {5, 41, 50, 9, 10, 11, 15, 16, 17},
            {3, 39, 48, 18, 19, 20, 24, 25, 26}, {4, 40, 18, 19, 20, 24, 25, 26}, {5, 41, 50, 18, 19, 20, 24, 25, 26},
            {3, 39, 48, 27, 28, 29, 33, 34, 35}, {4, 40, 27, 28, 29, 33, 34, 35}, {5, 41, 50, 27, 28, 29, 33, 34, 35}
    };

    private String crateName;
    private static CrateContentInventory instance;

    private CrateContentInventory(String crateName) {
        this.crateName = crateName;
    }

    public static CrateContentInventory getInstance(String crateName) {
        if (instance == null) {
            instance = new CrateContentInventory(crateName);
        }
        return instance;
    }

    @Override
    public void click(InventoryClickEvent e, ItemStack clicked, int slot) {
        e.setCancelled(true);
        if (slot == 12 && clicked.isSimilar(CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0)))
            new SlotGridShuffleTask(crateName, e.getClickedInventory(), slotAnimationSlots[0], 12, 25).runTaskTimer(Core.getInstance(), 0, 4);
        if (slot == 13 && clicked.isSimilar(CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0)))
            new SlotGridShuffleTask(crateName, e.getClickedInventory(), slotAnimationSlots[1], 13,25).runTaskTimer(Core.getInstance(), 0, 4);
        if (slot == 14 && clicked.isSimilar(CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0)))
            new SlotGridShuffleTask(crateName, e.getClickedInventory(), slotAnimationSlots[2], 14,25).runTaskTimer(Core.getInstance(), 0, 4);
        if (slot == 21 && clicked.isSimilar(CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0)))
            new SlotGridShuffleTask(crateName, e.getClickedInventory(), slotAnimationSlots[3], 21,25).runTaskTimer(Core.getInstance(), 0, 4);
        if (slot == 22 && clicked.isSimilar(CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0)))
            new SlotGridShuffleTask(crateName, e.getClickedInventory(), slotAnimationSlots[4], 22,25).runTaskTimer(Core.getInstance(), 0, 4);
        if (slot == 23 && clicked.isSimilar(CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0)))
            new SlotGridShuffleTask(crateName, e.getClickedInventory(), slotAnimationSlots[5], 23,25).runTaskTimer(Core.getInstance(), 0, 4);
        if (slot == 30 && clicked.isSimilar(CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0)))
            new SlotGridShuffleTask(crateName, e.getClickedInventory(), slotAnimationSlots[6], 30,25).runTaskTimer(Core.getInstance(), 0, 4);
        if (slot == 31 && clicked.isSimilar(CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0)))
            new SlotGridShuffleTask(crateName, e.getClickedInventory(), slotAnimationSlots[7], 31,25).runTaskTimer(Core.getInstance(), 0, 4);
        if (slot == 32 && clicked.isSimilar(CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0)))
            new SlotGridShuffleTask(crateName, e.getClickedInventory(), slotAnimationSlots[8], 32,25).runTaskTimer(Core.getInstance(), 0, 4);
        if (slot == 49 && clicked.isSimilar(CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0))) {
            Core.getInstance().openingCrate.remove(e.getWhoClicked().getUniqueId());
            try {
                e.getInventory().setItem(49, Crate.getInstance(crateName).getPaneItems(CratePane.TEN).get(ThreadLocalRandom.current().nextInt(Crate.getInstance(crateName).getPaneItems(CratePane.TEN).size())));
            } catch (IOException e1) {
                Debugger.report(e1);
            }
        }
    }

    @Override
    public void close(InventoryCloseEvent e) {
        if (Core.getInstance().openingCrate.contains(e.getPlayer().getUniqueId())) {
            Bukkit.getServer().getScheduler().runTaskLater(Core.getInstance(), () -> e.getPlayer().openInventory(e.getInventory()), 1);
        }
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, 54, ChatColor.translateAlternateColorCodes('&', Core.getInstance().getConfig().getString("guis.crate.title").replace("{crate_name}", crateName)));
        if (Core.getInstance().getConfig().getBoolean("guis.crate.items.fill.enabled"))
            for (int i = 0; i < inventory.getSize(); i++)
                inventory.setItem(i, CrateAPI.getInstance().createConfigItem("guis.crate.items.fill", 0, 0));

        Arrays.stream(normalItemSlots).forEach(slot -> inventory.setItem(slot, CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0)));
        inventory.setItem(49, CrateAPI.getInstance().createConfigItem("guis.crate.items.final-locked", 0, 0));
        return inventory;
    }

    @Override
    public void drag(InventoryDragEvent e) {
    }
}
