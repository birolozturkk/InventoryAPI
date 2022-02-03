package com.github.scropytr.legendinventoryapi.item;

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
public class ItemBuilder {

    private Material material;
    private String displayName = "";
    private int amount = 1;
    private short data = 0;
    private List<String> lore = new ArrayList<>();

    private transient ItemStack itemStack;
    private transient Click click = clickEvent -> {};

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemBuilder(Material material) {
        this.material = material;
    }

    public ItemBuilder setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder setData(short data) {
        this.data = data;
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemBuilder setAction(Click click) {
        this.click = click;
        return this;
    }

    public ItemBuilder setTexture(String texture) {
        if(itemStack == null) itemStack = new ItemStack(Material.SKULL_ITEM, amount, (short) 3);
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

    public ItemBuilder setSkullOwner(String headName) {
        if(itemStack == null) itemStack = new ItemStack(Material.SKULL_ITEM, amount, (short) 3);
        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
        meta.setOwner(headName);
        itemStack.setItemMeta(meta);
        return this;
    }

    public Item build() {
        if(itemStack == null) itemStack = new ItemStack(material, amount, data);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return new Item(itemStack, click);
    }

}