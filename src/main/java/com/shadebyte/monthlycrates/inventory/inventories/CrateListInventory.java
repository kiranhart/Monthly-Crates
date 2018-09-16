package com.shadebyte.monthlycrates.inventory.inventories;

import com.google.common.collect.Lists;
import com.shadebyte.monthlycrates.Core;
import com.shadebyte.monthlycrates.api.CrateAPI;
import com.shadebyte.monthlycrates.api.enums.Permissions;
import com.shadebyte.monthlycrates.crate.Crate;
import com.shadebyte.monthlycrates.inventory.MGUI;
import com.shadebyte.monthlycrates.utils.NBTEditor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 7/1/2018
 * Time Created: 12:57 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public class CrateListInventory implements MGUI {

    private static CrateListInventory instance;
    private Player p;

    private CrateListInventory(Player p) {
        this.p = p;
    }

    public static CrateListInventory getInstance(Player p) {
        if (instance == null) {
            instance = new CrateListInventory(p);
        }
        return instance;
    }

    private static int[] borderNumbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53};

    private int PAGE = 1;


    @Override
    public void click(InventoryClickEvent e, ItemStack clicked, int slot) {
        e.setCancelled(true);
        if (e.getClickedInventory() != e.getInventory())
            return;
        Player p = (Player) e.getWhoClicked();

        if (e.getWhoClicked().hasPermission(Permissions.ADMIN.getNode())) {
            if (NBTEditor.getItemTag(clicked, "MCrate") != null) {
                String node = (String) NBTEditor.getItemTag(clicked, "MCrate");
                e.getWhoClicked().getInventory().addItem(Crate.getInstance(node).getItemStack((Player) e.getWhoClicked()));
            }
        }

        try {
            if (PAGE >= 1 && slot == 48) p.openInventory(this.setPage(this.getPage() - 1).getInventory());
            if (PAGE >= 1 && slot == 50) p.openInventory(this.setPage(this.getPage() + 1).getInventory());
        } catch (Exception ex) {
            //Hide error for now
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
        Inventory inventory = Bukkit.createInventory(this, 54, ChatColor.translateAlternateColorCodes('&', Core.getInstance().getConfig().getString("guis.listing.title")));
        if (Core.getInstance().getConfig().getBoolean("guis.listing.border.enabled")) {
            Arrays.stream(borderNumbers).forEach(slot -> inventory.setItem(slot, CrateAPI.getInstance().createConfigItem("guis.listing.border.item", 0, 0)));
        }

        inventory.setItem(48, CrateAPI.getInstance().createConfigItem("guis.listing.items.previouspage", 0, 0));
        inventory.setItem(49, CrateAPI.getInstance().createConfigItem("guis.listing.items.currentpage", 0, PAGE));
        inventory.setItem(50, CrateAPI.getInstance().createConfigItem("guis.listing.items.nextpage", 0, 0));

        List<List<ItemStack>> chunks = Lists.partition(CrateAPI.getInstance().getListOfCrates(), 28);
        //Pagination
        chunks.get(getPage() - 1).forEach(stack -> inventory.addItem(stack));

        return inventory;
    }

    public CrateListInventory setPage(int page) {
        if (page <= 0)
            PAGE = 1;
        else
            PAGE = page;
        return this;
    }

    public int getPage() {
        return PAGE;
    }
}
