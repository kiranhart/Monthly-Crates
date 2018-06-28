package com.shadebyte.monthlycrates.api;

import com.shadebyte.monthlycrates.Core;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
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

    public ItemStack createConfigItem(String node, int paneNumberOption) {
        String[] rawItem = Core.getInstance().getConfig().getString(node + ".item").split(":");
        ItemStack stack = new ItemStack(Material.valueOf(rawItem[0].toUpperCase()), 1, Short.parseShort(rawItem[1]));
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Core.getInstance().getConfig().getString(node + ".name").replace("{normal_pane_number}", String.valueOf(paneNumberOption))));
        List<String> lore = new ArrayList<>();
        Core.getInstance().getConfig().getStringList(node + ".lore").forEach(s -> lore.add(ChatColor.translateAlternateColorCodes('&', s)));
        meta.setLore(lore);
        stack.setItemMeta(meta);
        return stack;
    }

    public ItemStack filler(int color) {
        return new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) color);
    }
}
