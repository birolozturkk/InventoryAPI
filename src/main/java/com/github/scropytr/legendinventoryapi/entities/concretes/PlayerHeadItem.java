package com.github.scropytr.legendinventoryapi.entities.concretes;

import org.bukkit.Material;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class PlayerHeadItem extends Item {

    private transient SkullMeta meta;

    public PlayerHeadItem(String displayName, String headName, int slot, int amount, List<String> lore) {
        super(displayName, slot, Material.SKULL_ITEM, 3, amount, lore);
        meta = (SkullMeta) getItemStack().getItemMeta();
        meta.setOwner(headName);
        build();
    }

    @Override
    public void build() {
        meta.setDisplayName(getDisplayName());
        meta.setLore(getLore());
        getItemStack().setAmount(getAmount());
        getItemStack().setDurability((short) getData());
        getItemStack().setItemMeta(meta);
    }

}
