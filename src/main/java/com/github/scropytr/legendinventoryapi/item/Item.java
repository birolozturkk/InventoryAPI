package com.github.scropytr.legendinventoryapi.item;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

@Getter
public class Item {

    private transient ItemStack itemStack;
    private transient Click click;

    public Item(ItemStack itemStack) {
        this(itemStack, clickEvent -> {});
    }

    public Item(ItemStack itemStack, Click click) {
        this.itemStack = itemStack;
        this.click = click;
    }

}
