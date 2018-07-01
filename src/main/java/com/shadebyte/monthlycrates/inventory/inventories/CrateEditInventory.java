package com.shadebyte.monthlycrates.inventory.inventories;

import com.shadebyte.monthlycrates.Core;
import com.shadebyte.monthlycrates.api.CrateAPI;
import com.shadebyte.monthlycrates.crate.Crate;
import com.shadebyte.monthlycrates.inventory.MGUI;
import com.shadebyte.monthlycrates.language.Lang;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
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
    private Player player;

    private CrateEditInventory(String name, Player player) {
        this.name = name;
        this.player = player;
    }

    public static CrateEditInventory getInstance(String name, Player player) {
        if (instance == null) {
            instance = new CrateEditInventory(name, player);
        }
        return instance;
    }

    @Override
    public void click(InventoryClickEvent e, ItemStack clicked, int slot) {
        e.setCancelled(true);
        if (clicked.isSimilar(CrateAPI.getInstance().createConfigItem("guis.edit.items.animationtheme", 0))) {
            e.getWhoClicked().sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.DISABLED.getNode()));
            return;
        }

        if(slot == 21) {
            player.closeInventory();
            Core.getInstance().editingTitle.add(player.getUniqueId());
            player.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.CRATE_EDIT_TITLE.getNode()));
        }
    }

    @Override
    public void close(InventoryCloseEvent e) {

    }

    @Override
    public void drag(InventoryDragEvent e) {

    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, 54, ChatColor.translateAlternateColorCodes('&', Core.getInstance().getConfig().getString("guis.edit.title").replace("{crate_name}", name)));
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

        //Items
        inventory.setItem(13, Crate.getInstance(name).getItemStack(player));
        inventory.setItem(21, CrateAPI.getInstance().createConfigItem("guis.edit.items.name", 0));
        inventory.setItem(22, CrateAPI.getInstance().createConfigItem("guis.edit.items.item", 0));
        inventory.setItem(23, CrateAPI.getInstance().createConfigItem("guis.edit.items.animationtheme", 0));

        int pane = 1;
        for (int i = 36; i < 45; i++) {
            inventory.setItem(i, CrateAPI.getInstance().createConfigItem("guis.edit.items.normalpane", pane));
            pane++;
        }
        inventory.setItem(49, CrateAPI.getInstance().createConfigItem("guis.edit.items.finalpane", 0));

        return inventory;
    }

    public String getName() {
        return name;
    }
}