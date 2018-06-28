package com.shadebyte.monthlycrates.crate;

import com.shadebyte.monthlycrates.Core;
import com.shadebyte.monthlycrates.utils.NBTEditor;
import com.shadebyte.monthlycrates.utils.Serializer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 6/25/2018
 * Time Created: 3:51 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public class Crate {

    private String node;

    public Crate(String node) {
        this.node = node;
    }

    public boolean exist() {
        return Core.getCrates().getConfig().contains("crates." + node.toLowerCase());
    }

    public String getDisplayName() {
        return Core.getCrates().getConfig().getString("crates." + node.toLowerCase() + ".item.name");
    }

    public List<String> getLore() {
        return Core.getCrates().getConfig().getStringList("crates." + node.toLowerCase() + ".item.lore");
    }

    public ItemStack getItemStack() {
        String[] item = Core.getCrates().getConfig().getString("crates." + node.toLowerCase() + ".item.material").split(":");
        ItemStack stack = new ItemStack(Material.valueOf(item[0].toUpperCase()), 1, Short.parseShort(item[1]));
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(getDisplayName());
        List<String> lore = new ArrayList<>();
        getLore().forEach((element) -> lore.add(ChatColor.translateAlternateColorCodes('&', element)));
        meta.setLore(lore);
        stack.setItemMeta(meta);
        stack = NBTEditor.setItemTag(stack, node, "MCrate");
        return stack;
    }

    public List<ItemStack> getPaneItems(CratePane pane) throws IOException {
        return Serializer.getInstance().fromBase64(Core.getCrates().getConfig().getString("crates." + node.toLowerCase() + ".panes." + pane.getVal()));
    }

    public void create() {
        if (!exist()) {
            Core.getCrates().getConfig().set("crates." + node.toLowerCase() + ".item.name", "&e&l" + node + " &6&l Crate");
            Core.getCrates().getConfig().set("crates." + node.toLowerCase() + ".item.material", "ENDER_CHEST:0");
            Core.getCrates().getConfig().set("crates." + node.toLowerCase() + ".item.lore", Arrays.asList("&7Temporary lore"));
            Core.getCrates().saveConfig();
        }
    }

    public String getNode() {
        return node;
    }
}
