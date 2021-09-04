package com.github.scropytr.entities.concretes;

import com.github.scropytr.entities.abstracts.Click;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@Getter
public class Item {

    private String displayName;
    private Material material;
    private int data;
    private int amount;
    private List<String> lore;

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

    public Item(String displayName, Material material, int data, int amount, List<String> lore) {
        this(new ItemStack(material, amount, (short) data));
        setDisplayName(displayName);
        setAmount(amount);
        setLore(lore);
    }

    public Item(String displayName, Material material, int data, int amount, List<String> lore, Click click) {
        this(new ItemStack(material, amount, (short) data), click);
        setDisplayName(displayName);
        setAmount(amount);
        setLore(lore);
    }

    public Item setDisplayName(String displayName) {
        this.displayName = displayName;
        this.meta.setDisplayName(displayName);
        return this;
    }

    public Item setMaterial(Material material) {
        this.material = material;
        this.itemStack.setType(material);
        return this;
    }

    public Item setData(int data) {
        this.data = data;
        this.itemStack.setDurability((short) data);
        return this;
    }

    public Item setAmount(int amount) {
        this.amount = amount;
        this.itemStack.setAmount(amount);
        return this;
    }

    public Item setLore(List<String> lore) {
        this.lore = lore;
        this.meta.setLore(lore);
        return this;
    }

}
