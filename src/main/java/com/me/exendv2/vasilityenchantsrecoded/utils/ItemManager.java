package com.me.exendv2.vasilityenchantsrecoded.utils;

import org.bukkit.inventory.ItemStack;

public class ItemManager {

    public String[] EnchantableList = {"HELMET", "CHESTPLATE", "LEGGINGS", "BOOTS", "SWORD", "PICKAXE"
            , "AXE", "SHOVEL", "HOE", "BOW", "FISHING_ROD", "TRIDENT", "CROSSBOW", "BOW"
            , "SHEARS", "SHIELD", "ELYTRA", "FLINT_AND_STEEL", "CARROT_ON_A_STICK"
            , "WARPED_FUNGUS_ON_A_STICK"};

    public boolean isEnchantable(ItemStack itemStack) {
        boolean bool = false;
        for (String s : EnchantableList) {
            if (itemStack.getType().name().contains(s)) {
                bool = true;
                break;
            }
        }
        return bool;
    }

    public boolean isHelmet(ItemStack itemStack) {
        return itemStack.getType().name().contains("HELMET");
    }

    public boolean isChestplate(ItemStack itemStack) {
        return itemStack.getType().name().contains("CHESTPLATE");
    }

    public boolean isLeggings(ItemStack itemStack) {
        return itemStack.getType().name().contains("LEGGINGS");
    }

    public boolean isBoots(ItemStack itemStack) {
        return itemStack.getType().name().contains("BOOTS");
    }

    public boolean isSword(ItemStack itemStack) {
        return itemStack.getType().name().contains("SWORD");
    }

    public boolean isPickaxe(ItemStack itemStack) {
        return itemStack.getType().name().contains("PICKAXE");
    }

    public boolean isAxe(ItemStack itemStack) {
        return itemStack.getType().name().contains("AXE");
    }

    public boolean isShovel(ItemStack itemStack) {
        return itemStack.getType().name().contains("SHOVEL");
    }

    public boolean isHoe(ItemStack itemStack) {
        return itemStack.getType().name().contains("HOE");
    }
}
