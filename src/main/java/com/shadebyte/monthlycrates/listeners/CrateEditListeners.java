package com.shadebyte.monthlycrates.listeners;

import com.shadebyte.monthlycrates.Core;
import com.shadebyte.monthlycrates.crate.Crate;
import com.shadebyte.monthlycrates.language.Lang;
import com.shadebyte.monthlycrates.utils.Debugger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * The current file has been created by Kiran Hart
 * Date Created: 6/29/2018
 * Time Created: 3:32 PM
 * Usage of any code found within this class is prohibited unless given explicit permission otherwise.
 */
public class CrateEditListeners implements Listener {

    /*
    Crate Title Edit
     */
    @EventHandler
    public void onCrateTitleEdit(AsyncPlayerChatEvent e) {
        try {

            Player p = e.getPlayer();

            if (!Core.getInstance().editingCrate.containsKey(p.getUniqueId()) && !Core.getInstance().editingTitle.contains(p.getUniqueId())) {
                return;
            }

            String msg = e.getMessage();

            if (msg.equalsIgnoreCase(Core.getInstance().getConfig().getString("cancel-word"))) {
                Core.getInstance().editingCrate.remove(p.getUniqueId());
                Core.getInstance().editingTitle.remove(p.getUniqueId());
                p.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.EDIT_CANCEL.getNode()));
            } else {
                Crate.getInstance(Core.getInstance().editingCrate.get(p.getUniqueId())).setDisplayName(msg);
                Core.getInstance().editingCrate.remove(p.getUniqueId());
                Core.getInstance().editingTitle.remove(p.getUniqueId());
                p.sendMessage(Core.getInstance().getSettings().getPrefix() + Core.getInstance().getLocale().getMessage(Lang.CRATE_SAVED.getNode()));
                e.setCancelled(true);
            }
        } catch (Exception ex) {
            Debugger.report(ex);
        }
    }
}
