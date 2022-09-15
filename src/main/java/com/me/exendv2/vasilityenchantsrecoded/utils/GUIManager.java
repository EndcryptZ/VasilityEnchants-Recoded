package com.me.exendv2.vasilityenchantsrecoded.utils;

import com.me.exendv2.vasilityenchantsrecoded.VasilityEnchants;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

import static org.bukkit.Sound.BLOCK_BEACON_ACTIVATE;

public class GUIManager {
    FileConfiguration config = VasilityEnchants.getPlugin(VasilityEnchants.class).getConfig();
    ConfigManager configManager = new ConfigManager();
    ItemManager itemManager = new ItemManager();
    public static Inventory MainGUI;

    public Inventory GUI() {
        MainGUI = Bukkit.createInventory(null, 54, configManager.ColorChanger(config.getString("GUIName")));

        return MainGUI;
    }

    public void openGUI(HumanEntity p) {
        p.openInventory(GUI());

        basegui();
        ((Player) p).playSound(p.getLocation(), BLOCK_BEACON_ACTIVATE, 10, 1);

    }

    private void basegui() {
        for (int i = 0; i < 54; i++) {

            MainGUI.setItem(i, createGuiItem(Material.GREEN_STAINED_GLASS_PANE, "§1"));

        }

        MainGUI.setItem(13, createGuiItem(Material.BARRIER, "§c§lEMPTY", "", "§aDrag n' drop an enchantable item here."));

        MainGUI.setItem(0, createGuiItem(Material.LIME_STAINED_GLASS_PANE, "§1"));
        MainGUI.setItem(1, createGuiItem(Material.LIME_STAINED_GLASS_PANE, "§1"));
        MainGUI.setItem(9, createGuiItem(Material.LIME_STAINED_GLASS_PANE, "§1"));

        MainGUI.setItem(4, createGuiItem(Material.LIME_STAINED_GLASS_PANE, "§1"));

        MainGUI.setItem(7, createGuiItem(Material.LIME_STAINED_GLASS_PANE, "§1"));
        MainGUI.setItem(8, createGuiItem(Material.LIME_STAINED_GLASS_PANE, "§1"));
        MainGUI.setItem(17, createGuiItem(Material.LIME_STAINED_GLASS_PANE, "§1"));

        MainGUI.setItem(36, createGuiItem(Material.LIME_STAINED_GLASS_PANE, "§1"));
        MainGUI.setItem(45, createGuiItem(Material.LIME_STAINED_GLASS_PANE, "§1"));
        MainGUI.setItem(46, createGuiItem(Material.LIME_STAINED_GLASS_PANE, "§1"));

        MainGUI.setItem(49, createGuiItem(Material.LIME_STAINED_GLASS_PANE, "§1"));

        MainGUI.setItem(52, createGuiItem(Material.LIME_STAINED_GLASS_PANE, "§1"));
        MainGUI.setItem(53, createGuiItem(Material.LIME_STAINED_GLASS_PANE, "§1"));
        MainGUI.setItem(44, createGuiItem(Material.LIME_STAINED_GLASS_PANE, "§1"));

    }

    public void bookGUI(ItemStack item) {
        String path = null;
        if (itemManager.isHelmet(item)) {
            path = "HELMET";
        }
        else if (itemManager.isChestplate(item)) {
            path = "CHESTPLATE";
        }
        else if (itemManager.isLeggings(item)) {
            path = "LEGGINGS";
        }
        else if (itemManager.isBoots(item)) {
            path = "BOOTS";
        }
        else if (itemManager.isSword(item)) {
            path = "SWORD";
        }
        else if (itemManager.isPickaxe(item)) {
            path = "PICKAXE";
        }
        else if (itemManager.isAxe(item)) {
            path = "AXE";
        }
        else if (itemManager.isShovel(item)) {
            path = "SHOVEL";
        }
        else if (itemManager.isHoe(item)) {
            path = "HOE";
        }
        else if (itemManager.isBow(item)) {
            path = "BOW";
        }
        else if (itemManager.isTrident(item)) {
            path = "TRIDENT";
        }
        else if (itemManager.isFishingRod(item)) {
            path = "FISHING_ROD";
        }
        else if (itemManager.isCrossbow(item)) {
            path = "CROSSBOW";
        }
        else if (itemManager.isShears(item)) {
            path = "SHEARS";
        }
        else if (itemManager.isShield(item)) {
            path = "SHIELD";
        }
        else if (itemManager.isElytra(item)) {
            path = "ELYTRA";
        }
        else if (itemManager.isFlintAndSteel(item)) {
            path = "FLINT_AND_STEEL";
        }
        else if (itemManager.isCarrotOnAStick(item)) {
            path = "CARROT_ON_A_STICK";
        }
        else if (itemManager.isWarpedFungusOnAStick(item)) {
            path = "WARPED_FUNGUS_ON_A_STICK";
        }
        for (Enchantment e : Enchantment.values()) {
            if (config.getConfigurationSection(path).getKeys(false).contains(e.getKey().getKey().toUpperCase())) {
                setBook(item, e);
            }
        }
    }

    private ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    private ItemStack bookitem(String name, ItemStack itemStack, Enchantment enchantment) {
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        meta.setLore(configManager.getBookLore(itemStack, enchantment));

        item.setItemMeta(meta);

        return item;
    }

    private void setBook(ItemStack item, Enchantment enchantment){
        int slot = configManager.getSlot(item, enchantment);
        MainGUI.setItem(slot, bookitem(configManager.getBookName(enchantment), item, enchantment));
    }

}
