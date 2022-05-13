package com.github.scropytr.legendinventoryapi.item;

import com.cryptomorin.xseries.XMaterial;
import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTListCompound;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

@AllArgsConstructor
@Getter
public class Item {

    @Setter
    private ItemStack itemStack;

    @Setter
    private Consumer<InventoryClickEvent> clickAction;

    public Item(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public Item(XMaterial material) {
        this.itemStack = material.parseItem();
    }

    public Item withListener(Consumer<InventoryClickEvent> clickAction) {
        this.clickAction = clickAction;
        return this;
    }


}
