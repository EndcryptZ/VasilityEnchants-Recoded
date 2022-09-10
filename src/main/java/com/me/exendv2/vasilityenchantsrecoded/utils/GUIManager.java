package com.me.exendv2.vasilityenchantsrecoded.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GUIManager {

    ConfigManager configManager = new ConfigManager();
    public static Inventory MainGUI;

    public Inventory GUI() {
        MainGUI = Bukkit.createInventory(null, 54, configManager.getGUIName);

        return MainGUI;
    }

    public void openGUI(HumanEntity p) {
        p.openInventory(GUI());

        basegui();

    }

    public void basegui() {
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

    public void helmetGUI(ItemStack item){
        MainGUI.setItem(19, bookitem(configManager.getEnchantName(Enchantment.WATER_WORKER)));

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

    private ItemStack bookitem(String name){
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        meta.setLore(configManager.configLore());

        item.setItemMeta(meta);

        return item;
    }
}
