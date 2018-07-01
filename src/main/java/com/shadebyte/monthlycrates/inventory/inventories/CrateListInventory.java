package com.shadebyte.monthlycrates.inventory.inventories;

import com.shadebyte.monthlycrates.Core;
import com.shadebyte.monthlycrates.api.CrateAPI;
import com.shadebyte.monthlycrates.inventory.MGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 7/1/2018
 * Time Created: 12:57 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public class CrateListInventory implements MGUI {

    private static int[] borderNumbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};

    @Override
    public void click(InventoryClickEvent e, ItemStack clicked, int slot) {
        e.setCancelled(true);
    }

    @Override
    public void close(InventoryCloseEvent e) {

    }

    @Override
    public void drag(InventoryDragEvent e) {

    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, 54, ChatColor.translateAlternateColorCodes('&', Core.getInstance().getConfig().getString("guis.listing.title")));
        if (Core.getInstance().getConfig().getBoolean("guis.listing.border.enabled")) {
            Arrays.stream(borderNumbers).forEach(slot -> inventory.setItem(slot, CrateAPI.getInstance().createConfigItem("guis.listing.border.item", 0)));
        }
        return inventory;
    }
}
