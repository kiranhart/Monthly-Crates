package com.shadebyte.monthlycrates.utils;

import com.shadebyte.monthlycrates.Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * CREATED BY Songoda (https://www.github.com/Songoda/Arconix)
 */

public class Serializer {

    private static Serializer instance;

    private Map<String, Location> serializeCache = new HashMap<>();

    //make singleton
    private Serializer() {
    }

    public static Serializer getInstance() {
        if (instance == null)
            instance = new Serializer();
        return instance;
    }

    public String serializeLocation(Block b) {
        if (b == null)
            return "";
        return serializeLocation(b.getLocation());
    }

    public String serializeLocation(Location location) {
        if (location == null)
            return "";
        if (!location.getChunk().isLoaded()) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Core.getInstance(), () -> location.getChunk().load());
        }

        String w = location.getWorld().getName();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        String str = "w:" + w + "x:" + x + "y:" + y + "z:" + z;
        return str.replace(".0", "").replace(".", "~");
    }

    public Location unserializeLocation(String str) {
        if (str == null || str.equals(""))
            return null;

        if (serializeCache.containsKey(str)) {
            return serializeCache.get(str).clone();
        }
        String cachekey = str;
        str = str.replace("y:", ":").replace("z:", ":").replace("w:", "").replace("x:", ":").replace("~", ".");
        List<String> args = Arrays.asList(str.split("\\s*:\\s*"));

        World world = Bukkit.getWorld(args.get(0));
        double x = Double.parseDouble(args.get(1)), y = Double.parseDouble(args.get(2)), z = Double.parseDouble(args.get(3));
        Location location = new Location(world, x, y, z, 0, 0);
        serializeCache.put(cachekey, location.clone());
        return location;
    }

    public String toBase64(List<ItemStack> items) {
        if (items == null || items.size() < 1)
            return "";

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            dataOutput.writeInt(items.size());
            for (ItemStack item : items) {
                dataOutput.writeObject(item);
            }
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }

    public List<ItemStack> fromBase64(String data) throws IOException {
        if (data == null || data.equals(""))
            return null;

        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            List<ItemStack> items = new ArrayList<>();

            int size = dataInput.readInt();
            for (int i = 0; i < size; i++) {
                items.add((ItemStack) dataInput.readObject());
            }
            dataInput.close();
            return items;
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable to decode class type.", e);
        }
    }
}