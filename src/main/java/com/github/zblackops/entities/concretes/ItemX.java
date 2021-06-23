package com.github.zblackops.entities.concretes;

import com.github.zblackops.entities.abstracts.Click;
import lombok.Data;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@Data
public class ItemX {

    ItemStack item;
    ItemMeta meta;
    Click click;

    public ItemX(ItemStack item, Click click) {
        this(item);
        this.click = click;
    }

    public ItemX(ItemStack item) {
        this.item = item;
        this.meta = item.getItemMeta();
        this.click = event -> {};
    }

    public ItemX setLore(List<String> lore) {
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }

    public ItemX setDisplayName(String displayName) {
        meta.setDisplayName(displayName);
        item.setItemMeta(meta);
        return this;
    }

    public ItemX setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

}
