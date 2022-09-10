package com.me.exendv2.vasilityenchantsrecoded.utils;

import com.me.exendv2.vasilityenchantsrecoded.VasilityEnchants;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    public static String ColorChanger(String message){
        message = ChatColor.translateAlternateColorCodes('&', message);
        return message;
    }

}
