package com.shadebyte.monthlycrates.crate;

import com.shadebyte.monthlycrates.Core;
import com.shadebyte.monthlycrates.utils.Debugger;
import com.shadebyte.monthlycrates.utils.NBTEditor;
import com.shadebyte.monthlycrates.utils.Serializer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
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

    private static Crate instance;
    private String node;

    private Crate(String node) {
        this.node = node;
    }

    public static Crate getInstance(String node) {
        instance = new Crate(node);
        return instance;
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

    public void setDisplayName(String name) {
        Core.getCrates().getConfig().set("crates." + node.toLowerCase() + ".name", name);
        Core.getCrates().saveConfig();
    }

    public void setName(String name) {
        Core.getCrates().getConfig().set("crates." + node.toLowerCase() + ".item.name", name);
        Core.getCrates().saveConfig();
    }

    public ItemStack getItemStack(Player player) {
        String[] item = Core.getCrates().getConfig().getString("crates." + node.toLowerCase() + ".item.material").split(":");
        ItemStack stack = new ItemStack(Material.valueOf(item[0].toUpperCase()), 1, Short.parseShort(item[1]));
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getDisplayName()));
        List<String> lore = new ArrayList<>();
        getLore().forEach((element) -> lore.add(ChatColor.translateAlternateColorCodes('&', element.replace("{player}", player.getName()))));
        meta.setLore(lore);
        stack.setItemMeta(meta);
        stack = NBTEditor.setItemTag(stack, node, "MCrate");
        stack = NBTEditor.setItemTag(stack, player.getUniqueId().toString(), "MCrateOwner");
        return stack;
    }

    public ItemStack getItemStack() {
        String[] item = Core.getCrates().getConfig().getString("crates." + node.toLowerCase() + ".item.material").split(":");
        ItemStack stack = new ItemStack(Material.valueOf(item[0].toUpperCase()), 1, Short.parseShort(item[1]));
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getDisplayName()));
        List<String> lore = new ArrayList<>();
        getLore().forEach((element) -> lore.add(ChatColor.translateAlternateColorCodes('&', element.replace("{player}", "A Player"))));
        meta.setLore(lore);
        stack.setItemMeta(meta);
        stack = NBTEditor.setItemTag(stack, node, "MCrate");
        return stack;
    }

    public List<ItemStack> getPaneItems(CratePane pane) {
        List<ItemStack> contents = null;
        try {
            contents = Serializer.getInstance().fromBase64(Core.getCrates().getConfig().getString("crates." + node.toLowerCase() + ".panes." + pane.getVal()));
        } catch (IOException e) {
            Debugger.report(e);
        }
        return contents;
    }

    public void create() {
        if (!exist()) {
            Core.getCrates().getConfig().set("crates." + node.toLowerCase() + ".name", node);
            Core.getCrates().getConfig().set("crates." + node.toLowerCase() + ".item.name", "&6&l** &e&l" + node + " Crate &6&l**");
            Core.getCrates().getConfig().set("crates." + node.toLowerCase() + ".item.material", "ENDER_CHEST:0");
            Core.getCrates().getConfig().set("crates." + node.toLowerCase() + ".item.lore", Arrays.asList(
                    "&eUnlocked at buy.example.com by &n{player}",
                    "",
                    "&f&l&nADMIN ITEMS",
                    "&f&l* &fFirst Item",
                    "&f&l* &fSecond Item",
                    "&f&l* &fThird Item",
                    "",
                    "&E&l&nCOSMETIC ITEMS",
                    "&E&l* &EFirst Item",
                    "&E&l* &ESecond Item",
                    "&E&l* &EThird Item",
                    "",
                    "&6&l&nENCHANTMENT ITEMS",
                    "&6&l* &6First Item",
                    "&6&l* &6Second Item",
                    "&6&l* &6Third Item",
                    "",
                    "&c&l&nBONUS ITEMS",
                    "&c&l* &cBonus One",
                    "&c&l* &cBonus Two"));
            for (CratePane cratePane : CratePane.values()) {
                Core.getCrates().getConfig().set("crates." + node + ".panes." + cratePane.getVal(), Serializer.getInstance().toBase64(Arrays.asList(new ItemStack(Material.STAINED_GLASS_PANE, 1))));
            }
            Core.getCrates().saveConfig();
        }
    }

    public void remove() {
        if (exist()) {
            Core.getCrates().getConfig().set("crates." + node.toLowerCase(), null);
            Core.getCrates().saveConfig();
        }
    }

    public String getNode() {
        return node;
    }
}
