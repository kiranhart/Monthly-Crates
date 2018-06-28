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

/**
 * The current file has been created by Kiran Hart
 * Date Created: 6/28/2018
 * Time Created: 2:56 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public class CrateEditInventory implements MGUI {

    private static CrateEditInventory instance;
    private String name;

    private CrateEditInventory(String name) {
        this.name = name;
    }

    public static CrateEditInventory getInstance(String name) {
        if (instance == null) {
            instance = new CrateEditInventory(name);
        }
        return instance;
    }

    @Override
    public void click(InventoryClickEvent e, ItemStack clicked, int slot) {
        Bukkit.broadcastMessage("test");
    }

    @Override
    public void close(InventoryCloseEvent e) {

    }

    @Override
    public void drag(InventoryDragEvent e) {

    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', Core.getInstance().getConfig().getString("guis.edit.title").replace("{crate_name}", name)));
        boolean main = true;
        for (int i = 0; i < inventory.getSize(); i++) {
            if (main) {
                inventory.setItem(i, CrateAPI.getInstance().filler(0));
                main = false;
            } else {
                inventory.setItem(i, CrateAPI.getInstance().filler(8));
                main = true;
            }
        }
        return inventory;
    }

    public String getName() {
        return name;
    }
}
