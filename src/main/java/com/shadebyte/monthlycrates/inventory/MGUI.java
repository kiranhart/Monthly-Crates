package com.shadebyte.monthlycrates.inventory;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 6/28/2018
 * Time Created: 2:25 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public interface MGUI extends InventoryHolder {

    void click(InventoryClickEvent e, ItemStack clicked, int slot);

    void close(InventoryCloseEvent e);

    void drag(InventoryDragEvent e);
}
