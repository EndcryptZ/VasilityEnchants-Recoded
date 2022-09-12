package com.me.exendv2.vasilityenchantsrecoded.listeners;

import com.me.exendv2.vasilityenchantsrecoded.VasilityEnchants;
import com.me.exendv2.vasilityenchantsrecoded.utils.GUIManager;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class anvilListener implements Listener {

    FileConfiguration config = VasilityEnchants.getPlugin(VasilityEnchants.class).getConfig();
    GUIManager guiManager = new GUIManager();

    @EventHandler
    public void onAnvilClick(PlayerInteractEvent e){

        if (!(config.getBoolean("AnvilOpenGUI"))) {return;}
        if (e.getClickedBlock() == null) {return;}
        if (!e.getClickedBlock().getType().equals(Material.ANVIL)) {return;}
        if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) {return;}
        e.setCancelled(true);
        guiManager.openGUI(e.getPlayer());

    }

}
