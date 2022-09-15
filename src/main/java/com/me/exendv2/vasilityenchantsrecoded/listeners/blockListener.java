package com.me.exendv2.vasilityenchantsrecoded.listeners;

import com.me.exendv2.vasilityenchantsrecoded.VasilityEnchants;
import com.me.exendv2.vasilityenchantsrecoded.utils.GUIManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class blockListener implements Listener {
    GUIManager guiManager = new GUIManager();

    @EventHandler
    public void onAnvilClick(PlayerInteractEvent e){

        if (!(VasilityEnchants.instance.getConfig().getBoolean("AnvilOpenGUI"))) {return;}
        if (e.getClickedBlock() == null) {return;}
        Material block = e.getClickedBlock().getType();
        if (!(block.equals(Material.ANVIL) || block.equals(Material.CHIPPED_ANVIL) || block.equals(Material.DAMAGED_ANVIL))) {return;}
        if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) {return;}
        e.setCancelled(true);
        guiManager.openGUI(e.getPlayer());

    }

    @EventHandler
    public void onEnchantmentTableClick(PlayerInteractEvent e){

        if (!(VasilityEnchants.instance.getConfig().getBoolean("EnchantmentTableOpenGUI"))) {return;}
        if (e.getClickedBlock() == null) {return;}
        if (!e.getClickedBlock().getType().equals(Material.ENCHANTING_TABLE)) {return;}
        if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) {return;}
        e.setCancelled(true);
        guiManager.openGUI(e.getPlayer());

    }

}
