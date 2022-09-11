package com.me.exendv2.vasilityenchantsrecoded.listeners;

import com.me.exendv2.vasilityenchantsrecoded.VasilityEnchants;
import com.me.exendv2.vasilityenchantsrecoded.events.OnPlaceEvent;
import com.me.exendv2.vasilityenchantsrecoded.utils.ConfigManager;
import com.me.exendv2.vasilityenchantsrecoded.utils.GUIManager;
import com.me.exendv2.vasilityenchantsrecoded.utils.ItemManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Sound.BLOCK_ANVIL_LAND;
import static org.bukkit.Sound.ENTITY_PLAYER_LEVELUP;

public class enchantListener implements Listener {

    GUIManager guiManager = new GUIManager();
    Economy eco = VasilityEnchants.econ;
    ConfigManager configManager = new ConfigManager();
    FileConfiguration config = VasilityEnchants.getPlugin(VasilityEnchants.class).getConfig();
    ItemManager itemManager = new ItemManager();

    @EventHandler
    public void onPlaceEvent(OnPlaceEvent e) {

        guiManager.bookGUI(e.getItem());

    }

    @EventHandler
    public void onClickEvent(InventoryClickEvent e) {

        if (!(e.getClickedInventory() == GUIManager.MainGUI)) return;

        ItemStack item = e.getClickedInventory().getItem(13);
        Player p = (Player) e.getWhoClicked();
        double balance = eco.getBalance(p);
        if (item.getType() == Material.BARRIER) {
            return;
        }

        // ENCHANT LISTENER
        for (String itemtype : config.getConfigurationSection("").getKeys(false)) {

            if (item.getType().name().contains(itemtype)) {

                for (Enchantment enchantment : Enchantment.values()) {

                    if (config.getConfigurationSection(itemtype).getKeys(false).contains(enchantment.getKey().getKey().toUpperCase())) {

                        if (e.getSlot() == configManager.getSlot(item, enchantment)) {

                            double price = configManager.getPrice(item, enchantment);
                            if (item.getEnchantmentLevel(enchantment) >= configManager.getMaxLevel(item, enchantment)) {

                                p.playSound(p.getLocation(), BLOCK_ANVIL_LAND, 10, 1);
                                return;
                            }
                            if (price > balance) {
                                p.playSound(p.getLocation(), BLOCK_ANVIL_LAND, 10, 1);
                                return;
                            }
                            item.addUnsafeEnchantment(enchantment, item.getEnchantmentLevel(enchantment) + 1);
                            eco.withdrawPlayer(p, price);
                            p.playSound(p.getLocation(), ENTITY_PLAYER_LEVELUP, 10, 1);
                            guiManager.bookGUI(item);
                            return;
                        }
                    }
                }
            }
        }
    }
}
