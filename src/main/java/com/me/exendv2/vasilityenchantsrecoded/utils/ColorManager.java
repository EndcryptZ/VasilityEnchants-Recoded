package com.me.exendv2.vasilityenchantsrecoded.utils;

import org.bukkit.ChatColor;

public class ColorManager {

    public static String ColorChanger(String message){
        message = ChatColor.translateAlternateColorCodes('&', message);
        return message;
    }

}
