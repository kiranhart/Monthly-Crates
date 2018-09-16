package com.shadebyte.monthlycrates.inventory.inventories;

import com.shadebyte.monthlycrates.Core;
import com.shadebyte.monthlycrates.api.CrateAPI;
import com.shadebyte.monthlycrates.crate.Crate;
import com.shadebyte.monthlycrates.crate.CratePane;
import com.shadebyte.monthlycrates.inventory.MGUI;
import com.shadebyte.monthlycrates.language.Lang;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 6/28/2018
 * Time Created: 2:56 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public class CrateEditInventory implements MGUI {

    private String name;
    private UUID uuid;

    private CrateEditInventory(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public static CrateEditInventory getInstance(String name, Player player) {
        return new CrateEditInventory(name, player.getUniqueId());
    }

    @Override
    public void click(InventoryClickEvent e, ItemStack clicked, int slot) {
        e.setCancelled(true);
        Player player = toPlayer(uuid);

        if (slot <= -1 || clicked == null || clicked.getType() == Material.AIR) {
            return;
        }

        if (clicked.isSimilar(CrateAPI.getInstance().createConfigItem("guis.edit.items.animationtheme", 0, 0))) {
            e.getWhoClicked().sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.DISABLED.getNode()));
            return;
        }

        if (slot == 21) {
            player.closeInventory();
            Core.getInstance().editingTitle.add(player.getUniqueId());
            player.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.CRATE_EDIT_TITLE.getNode()));
            return;
        }

        if (slot == 22) {
            player.closeInventory();
            Core.getInstance().editingStack.add(player.getUniqueId());
            player.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.CRATE_EDIT_STACK_TITLE.getNode()));
            return;
        }

        switch (slot) {
            case 36:
                Core.getInstance().editingCrate.put(player.getUniqueId(), name);
                Core.getInstance().editingCrateItems.put(player.getUniqueId(), CratePane.ONE);
                player.openInventory(CrateItemEditInventory.getInstance(player, name).getInventory());
                break;
            case 37:
                Core.getInstance().editingCrate.put(player.getUniqueId(), name);
                Core.getInstance().editingCrateItems.put(player.getUniqueId(), CratePane.TWO);
                player.openInventory(CrateItemEditInventory.getInstance(player, name).getInventory());
                break;
            case 38:
                Core.getInstance().editingCrate.put(player.getUniqueId(), name);
                Core.getInstance().editingCrateItems.put(player.getUniqueId(), CratePane.THREE);
                player.openInventory(CrateItemEditInventory.getInstance(player, name).getInventory());
                break;
            case 39:
                Core.getInstance().editingCrate.put(player.getUniqueId(), name);
                Core.getInstance().editingCrateItems.put(player.getUniqueId(), CratePane.FOUR);
                player.openInventory(CrateItemEditInventory.getInstance(player, name).getInventory());
                break;
            case 40:
                Core.getInstance().editingCrate.put(player.getUniqueId(), name);
                Core.getInstance().editingCrateItems.put(player.getUniqueId(), CratePane.FIVE);
                player.openInventory(CrateItemEditInventory.getInstance(player, name).getInventory());
                break;
            case 41:
                Core.getInstance().editingCrate.put(player.getUniqueId(), name);
                Core.getInstance().editingCrateItems.put(player.getUniqueId(), CratePane.SIX);
                player.openInventory(CrateItemEditInventory.getInstance(player, name).getInventory());
                break;
            case 42:
                Core.getInstance().editingCrate.put(player.getUniqueId(), name);
                Core.getInstance().editingCrateItems.put(player.getUniqueId(), CratePane.SEVEN);
                player.openInventory(CrateItemEditInventory.getInstance(player, name).getInventory());
                break;
            case 43:
                Core.getInstance().editingCrate.put(player.getUniqueId(), name);
                Core.getInstance().editingCrateItems.put(player.getUniqueId(), CratePane.EIGHT);
                player.openInventory(CrateItemEditInventory.getInstance(player, name).getInventory());
                break;
            case 44:
                Core.getInstance().editingCrate.put(player.getUniqueId(), name);
                Core.getInstance().editingCrateItems.put(player.getUniqueId(), CratePane.NINE);
                player.openInventory(CrateItemEditInventory.getInstance(player, name).getInventory());
                break;
            case 49:
                Core.getInstance().editingCrate.put(player.getUniqueId(), name);
                Core.getInstance().editingCrateItems.put(player.getUniqueId(), CratePane.TEN);
                player.openInventory(CrateItemEditInventory.getInstance(player, name).getInventory());
                break;
            default:
                break;
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
        inventory.setItem(13, Crate.getInstance(name).getItemStack(toPlayer(uuid)));
        inventory.setItem(21, CrateAPI.getInstance().createConfigItem("guis.edit.items.name", 0, 0));
        inventory.setItem(22, CrateAPI.getInstance().createConfigItem("guis.edit.items.stacktitle", 0, 0));
        inventory.setItem(23, CrateAPI.getInstance().createConfigItem("guis.edit.items.animationtheme", 0, 0));

        int pane = 1;
        for (int i = 36; i < 45; i++) {
            inventory.setItem(i, CrateAPI.getInstance().createConfigItem("guis.edit.items.normalpane", pane, 0));
            pane++;
        }
        inventory.setItem(49, CrateAPI.getInstance().createConfigItem("guis.edit.items.finalpane", 0, 0));

        return inventory;
    }

    public String getName() {
        return name;
    }

    private Player toPlayer(UUID uuid) {
        return Bukkit.getPlayer(uuid);
    }
}
