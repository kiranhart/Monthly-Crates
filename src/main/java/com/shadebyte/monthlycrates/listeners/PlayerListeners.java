package com.shadebyte.monthlycrates.listeners;

import com.shadebyte.monthlycrates.Core;
import com.shadebyte.monthlycrates.api.CrateAPI;
import com.shadebyte.monthlycrates.inventory.inventories.CrateContentInventory;
import com.shadebyte.monthlycrates.language.Lang;
import com.shadebyte.monthlycrates.utils.NBTEditor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 7/1/2018
 * Time Created: 12:53 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public class PlayerListeners implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if (Core.getInstance().editingTitle.contains(p.getUniqueId())) {
            Core.getInstance().editingTitle.remove(p.getUniqueId());
        }

        if (Core.getInstance().editingCrate.containsKey(p.getUniqueId())) {
            Core.getInstance().editingCrate.remove(p.getUniqueId());
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        try {
            if (NBTEditor.getItemTag(CrateAPI.getItemInHand(p), "MCrate") != null) {
                e.setCancelled(true);
            }
        } catch (Exception ex) {
        }
    }

    @EventHandler
    public void onCrateAttemptOpen(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        try {
            if (e.getAction() == Action.RIGHT_CLICK_AIR) {

                if (CrateAPI.getItemInHand(p).getType() == Material.AIR) {
                    return;
                }

                if (CrateAPI.getItemInHand(p) == null) {
                    return;
                }

                if (NBTEditor.getItemTag(CrateAPI.getItemInHand(p), "MCrate") != null) {
                    String node = (String) NBTEditor.getItemTag(CrateAPI.getItemInHand(p), "MCrate");

                    if (!Core.getCrates().getConfig().contains("crates." + node)) {
                        return;
                    }

                    String owner = (String) NBTEditor.getItemTag(CrateAPI.getItemInHand(p), "MCrateOwner");
                    if (Core.getInstance().getConfig().getBoolean("allow-nonowner-to-open")) {
                        if (p.getUniqueId().toString().equalsIgnoreCase(owner)) {
                            p.openInventory(CrateContentInventory.getInstance(node).getInventory());
                            Core.getInstance().openingCrate.add(p.getUniqueId());
                            remove(p);
                        } else {
                            e.getPlayer().sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.CRATE_CANT_OPEN.getNode()));
                        }
                    } else {
                        p.openInventory(CrateContentInventory.getInstance(node).getInventory());
                        Core.getInstance().openingCrate.add(p.getUniqueId());
                        remove(p);
                    }
                }
            }
        } catch (Exception ex) {
        }
    }

    private void remove(Player p) {
        if (CrateAPI.getItemInHand(p).getAmount() >= 2) {
            CrateAPI.getItemInHand(p).setAmount(CrateAPI.getItemInHand(p).getAmount() - 1);
            p.updateInventory();
        } else {
            CrateAPI.setItemInHand(p, null);
            p.updateInventory();
        }
    }
}
