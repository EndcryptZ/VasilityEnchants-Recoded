package com.me.exendv2.vasilityenchantsrecoded.utils;

import com.me.exendv2.vasilityenchantsrecoded.VasilityEnchants;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;

import java.util.List;

public class ConfigManager {

    public final FileConfiguration config = VasilityEnchants.getPlugin(VasilityEnchants.class).getConfig();

    public String getPluginPrefix = ColorChanger(config.getString("PREFIX"));

    public String getGUIName = ColorChanger(config.getString("GUIName"));

    private String ColorChanger(String message){
        message = ChatColor.translateAlternateColorCodes('&', message);
        return message;
    }

    public void configReload(){
        VasilityEnchants.getPlugin(VasilityEnchants.class).reloadConfig();
        VasilityEnchants.getPlugin(VasilityEnchants.class).saveDefaultConfig();
    }

    public List<String> configLore(){
        String path = "BookItem.Lore";

        return config.getStringList(path);
    }

    public String getEnchantName(Enchantment enchantment){
        String path = "BookItem.Name";

        return ColorChanger(config.getString(path).replaceAll("(?i)%enchantment%", enchantment.getKey().getKey()));
    }

}
