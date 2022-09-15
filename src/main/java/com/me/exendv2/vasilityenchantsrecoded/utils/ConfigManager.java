package com.me.exendv2.vasilityenchantsrecoded.utils;

import com.me.exendv2.vasilityenchantsrecoded.VasilityEnchants;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Collectors;

public class ConfigManager {
    FileConfiguration config = VasilityEnchants.getPlugin(VasilityEnchants.class).getConfig();
    ItemManager itemManager = new ItemManager();

    public int getMaxLevel(ItemStack itemStack, Enchantment enchantment){
        String path = null;
        int i;
        for (String s : itemManager.EnchantableList){
            if (itemStack.getType().name().contains(s)){
                path = s + "." + enchantment.getKey().getKey().toUpperCase() + ".Prices";
                break;
            }
        }
        long test = config.getConfigurationSection(path).getKeys(false).size();
        i = (int) test;
        return i;
    }

    public List<String> getBookLore(ItemStack item, Enchantment enchantment){
        List<String> lores = null;
        if (item.getEnchantmentLevel(enchantment) >= getMaxLevel(item, enchantment)){
            lores = config.getStringList("BookItem.Lore")
                    .stream()
                    .map(line -> ColorChanger(line)
                            .replaceAll("%level%", ColorChanger(config.getString("MaxLevelOutput")))
                            .replaceAll("%price%", ColorChanger(config.getString("MaxLevelOutput")))
                            .replaceAll("%symbol%", ""))
                    .collect(Collectors.toList());
        }

        else {
            lores = config.getStringList("BookItem.Lore")
                    .stream()
                    .map(line -> ColorChanger(line)
                            .replaceAll("%level%", String.valueOf(item.getEnchantmentLevel(enchantment)))
                            .replaceAll("%price%", String.valueOf(getPrice(item, enchantment)))
                            .replaceAll("%symbol%", "\\" + config.getString("CurrencySymbol")))
                    .collect(Collectors.toList());

        }
        return lores;
    }

    public double getPrice(ItemStack itemStack, Enchantment enchantment){
        String path = null;
        for (String s : itemManager.EnchantableList) {
            if (itemStack.getType().name().contains(s)) {
                int level = itemStack.getEnchantmentLevel(enchantment) + 1;
                path = s + "." + enchantment.getKey().getKey().toUpperCase() + "." + "Prices." + level;
                break;
            }
        } return config.getLong(path);
    }

    public String getPrefix = ColorChanger(config.getString("PREFIX"));

    public String getBookName(Enchantment enchantment) {
        String path = "BookItem.Name";
        return ColorChanger(config.getString(path).replaceAll("(?i)%enchantment%", enchantment.getKey().getKey().replaceAll("_", " ").toUpperCase()));
    }

    public int getSlot(ItemStack itemStack, Enchantment enchantment){
        String path = null;
        for (String s : itemManager.EnchantableList) {
            if (itemStack.getType().name().contains(s)) {
                path = s + "." + enchantment.getKey().getKey().toUpperCase() + ".Slot";
                break;
            }
        } return config.getInt(path);
    }

    public boolean isAnvilEnabled(){
        return config.getBoolean("AnvilOpenGUI");
    }

    public String ColorChanger(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }


}
