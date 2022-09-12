package com.me.exendv2.vasilityenchantsrecoded.listeners;

import com.me.exendv2.vasilityenchantsrecoded.events.OnPlaceEvent;
import com.me.exendv2.vasilityenchantsrecoded.utils.GUIManager;
import com.me.exendv2.vasilityenchantsrecoded.utils.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class mainClickListener implements Listener {

    GUIManager guiManager = new GUIManager();
    ItemManager itemManager = new ItemManager();


    @EventHandler
    public void ClickListener(InventoryClickEvent e) {

        // Checks if inventory is the MainGUI
        if (!(e.getClickedInventory() == GUIManager.MainGUI)) return;
        e.setCancelled(true);
        // Checks if the item is not enchantable
        if (!(e.getSlot() == 13)){
            return;
        }
        if (Objects.requireNonNull(e.getClickedInventory()).getItem(13) == null) {return;}
        if (!(Objects.requireNonNull(e.getClickedInventory().getItem(13)).getType() == Material.BARRIER)) {
            guiManager.openGUI(e.getWhoClicked());
            e.getWhoClicked().getInventory().addItem(e.getCurrentItem());
            return;
        }
        OnPlaceEvent onPlaceEvent = new OnPlaceEvent(e.getCursor(), (Player) e.getWhoClicked());
        if (itemManager.isEnchantable(e.getCursor())) {
            // Sets the item in slot 13 to player's cursor item and remove the item from the cursor
            GUIManager.MainGUI.setItem(13, new ItemStack(Objects.requireNonNull(e.getCursor())));
            e.getWhoClicked().setItemOnCursor(null);
            Bukkit.getPluginManager().callEvent(onPlaceEvent);
        }
    }
}
