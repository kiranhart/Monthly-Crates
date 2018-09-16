package com.shadebyte.monthlycrates.listeners;

import com.shadebyte.monthlycrates.inventory.MGUI;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 6/28/2018
 * Time Created: 2:29 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public class MGUIListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof MGUI)) return;
        ItemStack item = e.getCurrentItem();
        if (item == null || item.getType() == Material.AIR) return;

        MGUI gui = (MGUI) e.getInventory().getHolder();
        gui.click(e, item, e.getRawSlot());
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().getHolder() instanceof MGUI) {
            MGUI gui = (MGUI) e.getInventory().getHolder();
            gui.close(e);
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent e) {
        if (e.getInventory().getHolder() instanceof MGUI) {
            MGUI gui = (MGUI) e.getInventory().getHolder();
            gui.drag(e);
        }
    }
}
