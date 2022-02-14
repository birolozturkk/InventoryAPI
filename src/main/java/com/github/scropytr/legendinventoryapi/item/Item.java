package com.github.scropytr.legendinventoryapi.item;

import com.cryptomorin.xseries.XMaterial;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Item {

    private XMaterial material = XMaterial.AIR;
    private String displayName = "";
    private int amount = 1;
    private short data = 0;
    private List<String> lore = new ArrayList<>();

    private transient ItemStack itemStack;
    private transient Click click = clickEvent -> {};

    public Item(XMaterial material) {
        this.itemStack = material.parseItem();
    }

    public Item setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public Item setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public Item setData(short data) {
        this.data = data;
        return this;
    }

    public Item setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public Item setAction(Click click) {
        this.click = click;
        return this;
    }

    public Item setTexture(String texture) {
        if(itemStack == null) itemStack = XMaterial.PLAYER_HEAD.parseItem();
        itemStack.setAmount(amount);
        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();

        if (texture.isEmpty()) return this;

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", texture));

        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);

        } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException | SecurityException exception) {
            exception.printStackTrace();
        }
        itemStack.setItemMeta(meta);
        return this;
    }

    public Item setSkullOwner(String headName) {
        if(itemStack == null) itemStack = XMaterial.PLAYER_HEAD.parseItem();
        itemStack.setAmount((short) 3);
        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
        meta.setOwner(headName);
        itemStack.setItemMeta(meta);
        return this;
    }

    public Item build() {
        if(itemStack == null) itemStack = material.parseItem();
        itemStack.setAmount(amount);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return this;
    }

}
