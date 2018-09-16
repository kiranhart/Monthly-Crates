package com.shadebyte.monthlycrates.inventory.inventories;

import com.shadebyte.monthlycrates.Core;
import com.shadebyte.monthlycrates.api.CrateAPI;
import com.shadebyte.monthlycrates.api.enums.Sounds;
import com.shadebyte.monthlycrates.api.task.SlotGridShuffleTask;
import com.shadebyte.monthlycrates.crate.Crate;
import com.shadebyte.monthlycrates.crate.CratePane;
import com.shadebyte.monthlycrates.inventory.MGUI;
import com.shadebyte.monthlycrates.language.Lang;
import com.shadebyte.monthlycrates.utils.Debugger;
import com.shadebyte.monthlycrates.utils.Serializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
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

    private static Map<UUID,Inventory> inventories = new HashMap<>();
    private String crateName;

    private CrateContentInventory(String crateName) {
        this.crateName = crateName;
    }

    public static CrateContentInventory getInstance(String crateName) {
        return new CrateContentInventory(crateName);
    }

    public static Map<UUID,Inventory> getInventories() {
        return inventories;
    }

    @Override
    public void click(InventoryClickEvent e, ItemStack clicked, int slot) {
        e.setCancelled(true);
        if (slot == 12 && clicked.isSimilar(CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0)))
            new SlotGridShuffleTask(crateName, e.getClickedInventory(), slotAnimationSlots[0], 12, 25).runTaskTimer(Core.getInstance(), 0, Core.getInstance().getConfig().getInt("tickrates.rowanimation"));
        if (slot == 13 && clicked.isSimilar(CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0)))
            new SlotGridShuffleTask(crateName, e.getClickedInventory(), slotAnimationSlots[1], 13, 25).runTaskTimer(Core.getInstance(), 0, Core.getInstance().getConfig().getInt("tickrates.rowanimation"));
        if (slot == 14 && clicked.isSimilar(CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0)))
            new SlotGridShuffleTask(crateName, e.getClickedInventory(), slotAnimationSlots[2], 14, 25).runTaskTimer(Core.getInstance(), 0, Core.getInstance().getConfig().getInt("tickrates.rowanimation"));
        if (slot == 21 && clicked.isSimilar(CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0)))
            new SlotGridShuffleTask(crateName, e.getClickedInventory(), slotAnimationSlots[3], 21, 25).runTaskTimer(Core.getInstance(), 0, Core.getInstance().getConfig().getInt("tickrates.rowanimation"));
        if (slot == 22 && clicked.isSimilar(CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0)))
            new SlotGridShuffleTask(crateName, e.getClickedInventory(), slotAnimationSlots[4], 22, 25).runTaskTimer(Core.getInstance(), 0, Core.getInstance().getConfig().getInt("tickrates.rowanimation"));
        if (slot == 23 && clicked.isSimilar(CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0)))
            new SlotGridShuffleTask(crateName, e.getClickedInventory(), slotAnimationSlots[5], 23, 25).runTaskTimer(Core.getInstance(), 0, Core.getInstance().getConfig().getInt("tickrates.rowanimation"));
        if (slot == 30 && clicked.isSimilar(CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0)))
            new SlotGridShuffleTask(crateName, e.getClickedInventory(), slotAnimationSlots[6], 30, 25).runTaskTimer(Core.getInstance(), 0, Core.getInstance().getConfig().getInt("tickrates.rowanimation"));
        if (slot == 31 && clicked.isSimilar(CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0)))
            new SlotGridShuffleTask(crateName, e.getClickedInventory(), slotAnimationSlots[7], 31, 25).runTaskTimer(Core.getInstance(), 0, Core.getInstance().getConfig().getInt("tickrates.rowanimation"));
        if (slot == 32 && clicked.isSimilar(CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0)))
            new SlotGridShuffleTask(crateName, e.getClickedInventory(), slotAnimationSlots[8], 32, 25).runTaskTimer(Core.getInstance(), 0, Core.getInstance().getConfig().getInt("tickrates.rowanimation"));
        if (slot == 49 && clicked.isSimilar(CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0))) {
            Core.getInstance().openingCrate.remove(e.getWhoClicked().getUniqueId());
            try {
                e.getInventory().setItem(49, Crate.getInstance(crateName).getPaneItems(CratePane.TEN).get(ThreadLocalRandom.current().nextInt(Crate.getInstance(crateName).getPaneItems(CratePane.TEN).size())));
            } catch (Exception e1) {
                Debugger.report(e1);
            }
        }
    }

    @Override
    public void close(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        if (Core.getInstance().openingCrate.contains(p.getUniqueId())) {
            Bukkit.getServer().getScheduler().runTaskLater(Core.getInstance(), () -> {
                p.openInventory(e.getInventory());
                p.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.CRATE_CANT_EXIT.getNode()));
            }, 1);
        } else {
            if (CrateAPI.getInstance().availableSlots(p.getInventory()) < 10) {
                Arrays.stream(normalItemSlots).forEach(slot -> p.getWorld().dropItemNaturally(p.getLocation(), e.getInventory().getItem(slot)));
                p.getWorld().dropItemNaturally(p.getLocation(), e.getInventory().getItem(49));
            } else {
                Arrays.stream(normalItemSlots).forEach(slot -> p.getInventory().addItem(e.getInventory().getItem(slot)));
                p.getInventory().addItem(e.getInventory().getItem(49));
            }
            p.playSound(p.getLocation(), Sounds.valueOf(Core.getInstance().getConfig().getString("sounds.close").toUpperCase()).bukkitSound(), 1.0f, 1.0f);
            inventories.remove(p.getUniqueId());
            Core.getUsersOpening().getConfig().set("player-uuids."+p.getUniqueId(),null);
            Core.getUsersOpening().saveConfig();
        }
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory;
        inventory = Bukkit.createInventory(this, 54, ChatColor.translateAlternateColorCodes('&',
                Core.getInstance().getConfig().getString("guis.crate.title").replace("{crate_name}", Crate.getInstance(crateName).getDisplayName())));
        if (Core.getInstance().getConfig().getBoolean("guis.crate.items.fill.enabled"))
            for (int i = 0; i < inventory.getSize(); i++)
                inventory.setItem(i, CrateAPI.getInstance().createConfigItem("guis.crate.items.fill", 0, 0));

        Arrays.stream(normalItemSlots).forEach(slot -> inventory.setItem(slot, CrateAPI.getInstance().createConfigItem("guis.crate.items.normal", 0, 0)));
        inventory.setItem(49, CrateAPI.getInstance().createConfigItem("guis.crate.items.final-locked", 0, 0));
        return inventory;
    }

    public static void openFromLogin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        Inventory inv;
        if (inventories.get(uuid) != null) {
            inv = inventories.get(uuid);
        } else if (Core.getUsersOpening().getConfig().contains("player-uuids."+uuid)) {
            String node = Core.getUsersOpening().getConfig().getString("player-uuids."+uuid+".node");
            if (Crate.getInstance(node).exist())
                inv = CrateContentInventory.getInstance(node).getInventory();
            else return;
        } else
            return;
        int[] slots = new int[]{12,13,13,21,22,23,30,31,32,49};
        int item = 1;
        for (int i : slots) {
            try {
                if (!Core.getInstance().getConfig().isSet("player-uuids."+uuid+"."+item)) continue;
                inv.setItem(i, Serializer.getInstance().fromBase64(Core.getUsersOpening()
                        .getConfig().getString("player-uuids." + uuid + "." + item)).get(0));
                item++;
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
        new BukkitRunnable() {
            public void run() {
                p.openInventory(inv);
                inventories.put(uuid,inv);
            }
        }.runTask(Core.getInstance());
    }

    @Override
    public void drag(InventoryDragEvent e) {
    }
}
