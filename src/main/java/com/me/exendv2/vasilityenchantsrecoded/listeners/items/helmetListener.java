package com.me.exendv2.vasilityenchantsrecoded.listeners.items;

import com.me.exendv2.vasilityenchantsrecoded.VasilityEnchants;
import com.me.exendv2.vasilityenchantsrecoded.events.OnPlaceEvent;
import com.me.exendv2.vasilityenchantsrecoded.utils.GUIManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class helmetListener implements Listener {

    GUIManager guiManager = new GUIManager();
    Economy eco = VasilityEnchants.econ;

    @EventHandler
    public void onPlaceEvent(OnPlaceEvent e){

        if(!e.getItem().getType().name().contains("HELMET")){return;}
        guiManager.helmetGUI(e.getItem());

    }

    @EventHandler
    public void onClickEvent(InventoryClickEvent e){

        if(!(e.getClickedInventory() == GUIManager.MainGUI)) return;

        ItemStack item = e.getClickedInventory().getItem(13);

        if(!item.getType().name().contains("HELMET")){return;}

        Player p = (Player) e.getWhoClicked();
        double balance = eco.getBalance(p);

        // AQUA_AFFINITY
        if(e.getSlot() == 19) {
            Enchantment enchantment = Enchantment.WATER_WORKER;
            int level = item.getEnchantmentLevel(enchantment);

        }
    }
}
