package net.enganxe.meetupuhc.events;

import net.enganxe.meetupuhc.Main;
import net.enganxe.meetupuhc.utils.UI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {
    private Main plugin;

    public InventoryClick (Main plugin){
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void OnClick(InventoryClickEvent e){
        String title = e.getView().getTitle();
        if (title.equals(UI.inventory_name)){
            e.setCancelled(true);
            if (e.getCurrentItem() == null){
                return;
            }
        }
    }
}
