package com.github.scropytr.legendinventoryapi.item;

import com.cryptomorin.xseries.XMaterial;
import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTListCompound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class ItemBuilder {

    private ItemStack itemStack;

    private final boolean supports = XMaterial.supports(16);

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemBuilder(XMaterial material) {
        this.itemStack = material.parseItem();
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        return change(itemStack -> itemStack.addUnsafeEnchantment(enchantment, level));
    }

    public ItemBuilder removeEnchantment(Enchantment enchantment) {
        return change(itemStack -> itemStack.removeEnchantment(enchantment));
    }

    public ItemBuilder setDamage(int damage) {
        return change(itemStack -> itemStack.setDurability((short) (itemStack.getType().getMaxDurability() - damage)));
    }

    public ItemBuilder setDisplayName(String displayName) {
        return changeMeta(itemMeta -> itemMeta.setDisplayName(displayName));
    }

    public ItemBuilder setType(XMaterial material) {
        return change(itemStack -> itemStack.setType(material.parseMaterial()));
    }

    public ItemBuilder setAmount(int amount) {
        return change(itemStack -> itemStack.setAmount(amount));
    }

    public ItemBuilder glowing(boolean glowing) {
        if (glowing)
            return addEnchantment(Enchantment.LUCK, 1).changeMeta(im -> im.addItemFlags(ItemFlag.HIDE_ENCHANTS));
        return this;
    }

    public ItemBuilder glowing() {
        return glowing(true);
    }

    public ItemBuilder setLore(List<String> lore) {
        return changeMeta(itemStack -> itemStack.setLore(lore));
    }

    public ItemBuilder addLore(String line) {
        if (line == null) return this;
        List<String> lore = itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore() ?
                new ArrayList<>(itemStack.getItemMeta().getLore()) : new ArrayList<>();
        lore.add(line);
        return setLore(lore);
    }

    public ItemBuilder changeMeta(Consumer<? super ItemMeta> consumer) {
        return change(itemStack -> {
            ItemMeta meta = itemStack.getItemMeta();
            consumer.accept(meta);
            itemStack.setItemMeta(meta);
        });
    }

    public ItemBuilder change(Consumer<? super ItemStack> consumer) {
        consumer.accept(itemStack);
        return this;
    }

    public ItemBuilder setHeadData(String headData) {
        if (itemStack.getType() != XMaterial.PLAYER_HEAD.parseMaterial()) return this;
        if (headData == null) return this;

        NBTItem nbtItem = new NBTItem(itemStack);
        NBTCompound skull = nbtItem.addCompound("SkullOwner");
        if (supports) {
            skull.setUUID("Id", UUID.randomUUID());
        } else {
            skull.setString("Id", UUID.randomUUID().toString());
        }

        NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
        texture.setString("Value", headData);
        itemStack = nbtItem.getItem();
        return this;
    }

    public Item build() {
        return new Item(itemStack.clone());
    }

}
