package com.shadebyte.monthlycrates.api;

import com.shadebyte.monthlycrates.Core;
import com.shadebyte.monthlycrates.crate.Crate;
import com.shadebyte.monthlycrates.utils.Debugger;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 6/28/2018
 * Time Created: 2:51 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public class CrateAPI {

    private static CrateAPI instance;

    private CrateAPI() {
    }

    public static CrateAPI getInstance() {
        if (instance == null) {
            instance = new CrateAPI();
        }
        return instance;
    }

    public ItemStack createConfigItem(String node, int paneNumberOption, int optionalPage) {
        String[] rawItem = Core.getInstance().getConfig().getString(node + ".item").split(":");
        ItemStack stack = new ItemStack(Material.valueOf(rawItem[0].toUpperCase()), 1, Short.parseShort(rawItem[1]));
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Core.getInstance().getConfig().getString(node + ".name").replace("{page}", String.valueOf(optionalPage)).replace("{normal_pane_number}", String.valueOf(paneNumberOption))));
        List<String> lore = new ArrayList<>();
        Core.getInstance().getConfig().getStringList(node + ".lore").forEach(s -> lore.add(ChatColor.translateAlternateColorCodes('&', s)));
        meta.setLore(lore);
        stack.setItemMeta(meta);
        return stack;
    }

    public List<ItemStack> getListOfCrates() {
        List<ItemStack> stack = new ArrayList<>();
        for (String s : Core.getCrates().getConfig().getConfigurationSection("crates").getKeys(false)) {
            stack.add(Crate.getInstance(s).getItemStack());
        }
        return stack;
    }

    public List<ItemStack> fillerItems(int amount) {
        List<ItemStack> list = new ArrayList<>();
        for (int i = 0; i <= amount; i++) {
            ItemStack stack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 1);
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&l" + i));
            stack.setItemMeta(meta);
            list.add(stack);
        }
        return list;
    }

    public ItemStack filler(int color) {
        return new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) color);
    }

    public boolean isInteger(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            Debugger.report(e);
            return false;
        }
        return true;
    }

    public int availableSlots(PlayerInventory inventory) {
        int count = 0;
        for (ItemStack i : inventory) {
            if (i == null) {
                count++;
            }
        }
        return count;
    }
}
