package com.github.scropytr.legendinventoryapi.entities.concretes;

import com.github.scropytr.legendinventoryapi.entities.abstracts.Click;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@Getter
public class Item {

    private String material;
    private int slot;
    private String displayName;
    private int amount;
    private int data;
    private List<String> lore;

    private transient Material materialType;
    private transient ItemStack itemStack;
    private transient ItemMeta meta;
    private transient Click click;

    public Item(ItemStack itemStack, Click click) {
        this(itemStack);
        this.click = click;
    }

    public Item(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.meta = itemStack.getItemMeta();
        this.click = event -> {};
    }

    public Item(String displayName, int slot, Material material, int data, int amount, List<String> lore) {
        this(new ItemStack(material, amount, (short) data));
        setDisplayName(displayName);
        setAmount(amount);
        setLore(lore);
        setData(data);
        setMaterialType(material);
        this.slot = slot;
    }

    public Item(String displayName, int slot, Material material, int data, int amount, List<String> lore, Click click) {
        this(new ItemStack(material, amount, (short) data), click);
        setDisplayName(displayName);
        setAmount(amount);
        setLore(lore);
        setData(data);
        setMaterialType(material);
        this.slot = slot;
    }

    public Item setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public Item setMaterialType(Material materialType) {
        this.materialType = materialType;
        return this;
    }

    public Item setData(int data) {
        this.data = data;
        return this;
    }

    public Item setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public Item setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public void build() {
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        itemStack.setAmount(amount);
        itemStack.setType(materialType);
        itemStack.setDurability((short) data);
        itemStack.setItemMeta(meta);
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        meta = itemStack.getItemMeta();
    }

    public void setMeta(ItemMeta meta) {
        this.meta = meta;
    }

    public void setClick(Click click) {
        this.click = click;
    }
}
