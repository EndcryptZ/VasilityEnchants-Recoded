package com.me.exendv2.vasilityenchantsrecoded.listeners;

import com.me.exendv2.vasilityenchantsrecoded.utils.GUIManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class closeInventoryListener implements Listener {

    @EventHandler
    public void GUICloseListen(InventoryCloseEvent e){

        Inventory inv = e.getInventory();
        Player p = (Player) e.getPlayer();

        // Simple check if the GUI is from /enchants command
        if (!(inv == GUIManager.MainGUI)) return;

        ItemStack item = e.getInventory().getItem(13);

        // Checks if item in slot 13 is BARRIER
        if (item.getType() == Material.BARRIER) return;

        // Adds the item in player's inventory if the item isn't BARRIER
        p.getInventory().addItem(item);

    }

}
