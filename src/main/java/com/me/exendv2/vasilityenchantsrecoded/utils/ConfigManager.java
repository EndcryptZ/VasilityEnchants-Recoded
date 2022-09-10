package com.me.exendv2.vasilityenchantsrecoded.utils;

import com.me.exendv2.vasilityenchantsrecoded.VasilityEnchants;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;

import java.util.List;

public class ConfigManager {
    FileConfiguration config = VasilityEnchants.getPlugin(VasilityEnchants.class).getConfig();

    public List<String> getBookLore = config.getStringList("BookItem.Lore");

    public String getPrefix = ColorChanger(config.getString("PREFIX"));

    public String getBookName(Enchantment enchantment) {
        String path = "BookItem.Name";
        return ColorChanger(config.getString(path).replaceAll("(?i)%enchantment%", enchantment.getKey().getKey().replaceAll("_", " ")));
    }

    public static String ColorChanger(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }


}
