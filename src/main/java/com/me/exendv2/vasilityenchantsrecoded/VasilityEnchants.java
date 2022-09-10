package com.me.exendv2.vasilityenchantsrecoded;

import com.me.exendv2.vasilityenchantsrecoded.commands.mainCommand;
import com.me.exendv2.vasilityenchantsrecoded.listeners.closeInventoryListener;
import com.me.exendv2.vasilityenchantsrecoded.listeners.items.helmetListener;
import com.me.exendv2.vasilityenchantsrecoded.listeners.mainClickListener;
import com.me.exendv2.vasilityenchantsrecoded.utils.ConfigManager;
import com.me.exendv2.vasilityenchantsrecoded.utils.GUIManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class VasilityEnchants extends JavaPlugin {

    public static Economy econ = null;

    @Override
    public void onEnable() {
        configReload();
        setupEconomy();
        getServer().getPluginCommand("vasilityenchants").setExecutor(new mainCommand());
        getServer().getPluginManager().registerEvents(new mainClickListener(), this);
        getServer().getPluginManager().registerEvents(new closeInventoryListener(), this);
        getServer().getPluginManager().registerEvents(new helmetListener(), this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getOpenInventory().getTopInventory() == GUIManager.MainGUI){
                p.closeInventory();
                p.sendMessage(ConfigManager.ColorChanger(getConfig().getString("PREFIX") + "GUI has been closed due to plugin "));
            }
        }
    }
        // Plugin shutdown logic

    private void setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return;
        }
        econ = rsp.getProvider();
    }

    public void configReload(){
        this.saveDefaultConfig();
        this.reloadConfig();
    }

}
