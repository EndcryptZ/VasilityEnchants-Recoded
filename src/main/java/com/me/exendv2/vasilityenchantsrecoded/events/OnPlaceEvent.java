package com.me.exendv2.vasilityenchantsrecoded.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class OnPlaceEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private Player player;
    private ItemStack item;

    public OnPlaceEvent(ItemStack itemStack, Player player){
        player = player;
        item = itemStack;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    public ItemStack getItem() {
        return item;
    }
    public Player getPlayer() {
        return player;
    }
}
