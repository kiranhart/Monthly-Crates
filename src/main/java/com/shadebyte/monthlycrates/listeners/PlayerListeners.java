package com.shadebyte.monthlycrates.listeners;

import com.shadebyte.monthlycrates.Core;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
}
