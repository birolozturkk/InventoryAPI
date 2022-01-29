package com.github.scropytr.legendinventoryapi.entities.concretes;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.meta.SkullMeta;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class HeadItem extends Item {

    private String material;

    @Getter
    @Setter
    private transient SkullMeta meta;

    public HeadItem(String displayName, String texture, int slot, int amount, List<String> lore) {
        super(displayName, slot, Material.SKULL_ITEM, 3, amount, lore);

        meta = (SkullMeta) getItemStack().getItemMeta();

        if (texture.isEmpty()) return;

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", texture));

        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);

        } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException | SecurityException exception) {
            exception.printStackTrace();
        }
        build();
    }

    @Override
    public void build() {
        meta.setDisplayName(getDisplayName());
        meta.setLore(getLore());
        getItemStack().setItemMeta(meta);
    }
}
