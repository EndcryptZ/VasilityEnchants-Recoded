package com.me.exendv2.vasilityenchantsrecoded.listeners;

import com.me.exendv2.vasilityenchantsrecoded.VasilityEnchants;
import com.me.exendv2.vasilityenchantsrecoded.events.OnPlaceEvent;
import com.me.exendv2.vasilityenchantsrecoded.utils.ConfigManager;
import com.me.exendv2.vasilityenchantsrecoded.utils.GUIManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static org.bukkit.Sound.BLOCK_ANVIL_LAND;
import static org.bukkit.Sound.ENTITY_PLAYER_LEVELUP;

public class enchantListener implements Listener {

    GUIManager guiManager = new GUIManager();
    Economy eco = VasilityEnchants.econ;
    ConfigManager configManager = new ConfigManager();
    FileConfiguration config = VasilityEnchants.getPlugin(VasilityEnchants.class).getConfig();

    @EventHandler
    public void onPlaceEvent(OnPlaceEvent e) {

        guiManager.bookGUI(e.getItem());

    }

    @EventHandler
    public void onClickEvent(InventoryClickEvent e) {

        if (!(e.getClickedInventory() == GUIManager.MainGUI)) {return;}

        if (Objects.requireNonNull(e.getClickedInventory()).getItem(13) == null) {return;}
        ItemStack item = e.getClickedInventory().getItem(13);
        Player p = (Player) e.getWhoClicked();
        double balance = eco.getBalance(p);
        if (Objects.requireNonNull(item).getType() == Material.BARRIER) {
            return;
        }

        // ENCHANT LISTENER
        for (String itemtype : Objects.requireNonNull(config.getConfigurationSection("")).getKeys(false)) {

            if (item.getType().name().contains(itemtype)) {

                for (Enchantment enchantment : Enchantment.values()) {

                    if (Objects.requireNonNull(config.getConfigurationSection(itemtype)).getKeys(false).contains(enchantment.getKey().getKey().toUpperCase())) {

                        if (e.getSlot() == configManager.getSlot(item, enchantment)) {

                            double price = configManager.getPrice(item, enchantment);
                            String enchantname = enchantment.getKey().getKey().toUpperCase().replaceAll("_", " ");
                            if (item.getEnchantmentLevel(enchantment) >= configManager.getMaxLevel(item, enchantment)) {

                                p.playSound(p.getLocation(), BLOCK_ANVIL_LAND, 10, 1);
                                p.sendMessage(ConfigManager.ColorChanger(configManager.getPrefix + "&cYour &e" + enchantname + " &cenchantment is already on max level!"));
                                return;
                            }
                            if (price > balance) {
                                p.playSound(p.getLocation(), BLOCK_ANVIL_LAND, 10, 1);
                                p.sendMessage(ConfigManager.ColorChanger(configManager.getPrefix + "&cYou don't have enough money to upgrade &e" + enchantname + " enchantment!"));
                                return;
                            }
                            int level = item.getEnchantmentLevel(enchantment);
                            int nextlevel = item.getEnchantmentLevel(enchantment) + 1;
                            item.addUnsafeEnchantment(enchantment, nextlevel);
                            eco.withdrawPlayer(p, price);
                            p.playSound(p.getLocation(), ENTITY_PLAYER_LEVELUP, 10, 1);
                            p.sendMessage(ConfigManager.ColorChanger(configManager.getPrefix + "&aSuccessfully upgraded &e" + enchantname + " &afrom level &e" + level + "&a to level &e" + nextlevel + "!"));
                            guiManager.bookGUI(item);
                            return;
                        }
                    }
                }
            }
        }
    }
}
