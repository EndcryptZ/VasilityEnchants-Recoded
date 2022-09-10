package com.me.exendv2.vasilityenchantsrecoded;

import com.me.exendv2.vasilityenchantsrecoded.commands.mainCommand;
import com.me.exendv2.vasilityenchantsrecoded.listeners.closeInventoryListener;
import com.me.exendv2.vasilityenchantsrecoded.listeners.items.helmetListener;
import com.me.exendv2.vasilityenchantsrecoded.listeners.mainClickListener;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class VasilityEnchants extends JavaPlugin {

    public static Economy econ = null;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        setupEconomy();
        getServer().getPluginCommand("vasilityenchants").setExecutor(new mainCommand());
        getServer().getPluginManager().registerEvents(new mainClickListener(), this);
        getServer().getPluginManager().registerEvents(new closeInventoryListener(), this);
        getServer().getPluginManager().registerEvents(new helmetListener(), this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
