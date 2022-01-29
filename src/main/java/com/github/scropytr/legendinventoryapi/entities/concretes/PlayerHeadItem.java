package com.github.scropytr.legendinventoryapi.entities.concretes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;
import java.util.UUID;

public class PlayerHeadItem extends Item {

    private transient SkullMeta meta;

    @Deprecated
    public PlayerHeadItem(String displayName, String headName, int slot, int amount, List<String> lore) {
        super(displayName, slot, Material.SKULL_ITEM, 3, amount, lore);

        UUID hashAsId = new UUID((long)headName.hashCode(), (long)headName.hashCode());
        setItemStack(Bukkit.getUnsafe().modifyItemStack(getItemStack(), "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + headName + "\"}]}}}"));
        meta = (SkullMeta) getItemStack().getItemMeta();
        //build();
    }

    @Override
    public void build() {
        meta.setDisplayName(getDisplayName());
        meta.setLore(getLore());
        getItemStack().setItemMeta(meta);
    }

}
