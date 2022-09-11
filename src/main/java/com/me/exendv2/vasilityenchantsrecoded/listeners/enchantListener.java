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
    ItemManager itemManager = new ItemManager();
    ConfigManager configManager = new ConfigManager();
    FileConfiguration config = VasilityEnchants.getPlugin(VasilityEnchants.class).getConfig();

    Enchantment enchantment;

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
        if (item.getType() == Material.BARRIER) {return;}

        // ENCHANT LISTENER
        for (Enchantment en : Enchantment.values()) {
            if (itemManager.isHelmet(item)) {
                if (config.getConfigurationSection("HELMET").getKeys(false).contains(en.getKey().getKey().toUpperCase())) {
                    enchantment = en;
                }
            }
            else if (itemManager.isChestplate(item)) {
                if (config.getConfigurationSection("CHESTPLATE").getKeys(false).contains(en.getKey().getKey().toUpperCase())) {
                    enchantment = en;
                }
            }
            else if (itemManager.isLeggings(item)) {
                if (config.getConfigurationSection("LEGGINGS").getKeys(false).contains(en.getKey().getKey().toUpperCase())) {
                    enchantment = en;
                }
            }
            else if (itemManager.isBoots(item)) {
                if (config.getConfigurationSection("BOOTS").getKeys(false).contains(en.getKey().getKey().toUpperCase())) {
                    enchantment = en;
                }
            }
            else if (itemManager.isSword(item)) {
                if (config.getConfigurationSection("SWORD").getKeys(false).contains(en.getKey().getKey().toUpperCase())) {
                    enchantment = en;
                }
            }
            else if (itemManager.isPickaxe(item)) {
                if (config.getConfigurationSection("PICKAXE").getKeys(false).contains(en.getKey().getKey().toUpperCase())) {
                    enchantment = en;
                }
            }
            else if (itemManager.isAxe(item)) {
                if (config.getConfigurationSection("AXE").getKeys(false).contains(en.getKey().getKey().toUpperCase())) {
                    enchantment = en;
                }
            }
            else if (itemManager.isShovel(item)) {
                if (config.getConfigurationSection("SHOVEL").getKeys(false).contains(en.getKey().getKey().toUpperCase())) {
                    enchantment = en;
                }
            }
            else if (itemManager.isHoe(item)) {
                if (config.getConfigurationSection("HOE").getKeys(false).contains(en.getKey().getKey().toUpperCase())) {
                    enchantment = en;
                }
            }
            else if (itemManager.isBow(item)) {
                if (config.getConfigurationSection("BOW").getKeys(false).contains(en.getKey().getKey().toUpperCase())) {
                    enchantment = en;
                }
            }
            else if (itemManager.isTrident(item)) {
                if (config.getConfigurationSection("TRIDENT").getKeys(false).contains(en.getKey().getKey().toUpperCase())) {
                    enchantment = en;
                }
            }
            else if (itemManager.isFishingRod(item)) {
                if (config.getConfigurationSection("FISHING_ROD").getKeys(false).contains(en.getKey().getKey().toUpperCase())) {
                    enchantment = en;
                }
            }
            else if (itemManager.isCrossbow(item)) {
                if (config.getConfigurationSection("CROSSBOW").getKeys(false).contains(en.getKey().getKey().toUpperCase())) {
                    enchantment = en;
                }
            }
            else if (itemManager.isShield(item)) {
                if (config.getConfigurationSection("SHIELD").getKeys(false).contains(en.getKey().getKey().toUpperCase())) {
                    enchantment = en;
                }
            }
            else if (itemManager.isElytra(item)) {
                if (config.getConfigurationSection("ELYTRA").getKeys(false).contains(en.getKey().getKey().toUpperCase())) {
                    enchantment = en;
                }
            }
            else if (itemManager.isFlintAndSteel(item)) {
                if (config.getConfigurationSection("FLINT_AND_STEEL").getKeys(false).contains(en.getKey().getKey().toUpperCase())) {
                    enchantment = en;
                }
            }
            else if (itemManager.isCarrotOnAStick(item)) {
                if (config.getConfigurationSection("CARROT_ON_A_STICK").getKeys(false).contains(en.getKey().getKey().toUpperCase())) {
                    enchantment = en;
                }
            }
            else if (itemManager.isWarpedFungusOnAStick(item)) {
                if (config.getConfigurationSection("WARPED_FUNGUS_ON_A_STICK").getKeys(false).contains(en.getKey().getKey().toUpperCase())) {
                    enchantment = en;
                }
            }
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
                break;
            }
        }
    }
}
