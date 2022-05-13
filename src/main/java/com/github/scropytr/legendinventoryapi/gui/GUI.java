package com.github.scropytr.legendinventoryapi.gui;

import com.github.scropytr.legendinventoryapi.item.Item;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;

@Getter
public abstract class GUI implements InventoryHolder {

    private final HashMap<Integer, Item> items = new HashMap<>();
    private Inventory inventory;

    public abstract String getTitle();
    public abstract int getSize();

    @Override
    public Inventory getInventory() {
        inventory = Bukkit.createInventory(this, getSize(), getTitle());
        addContent();
        return inventory;
    }

    public abstract void addContent();

    public void setItem(Item item, int slot) {
        if (this.inventory.getSize() <= slot) {
            throw new IndexOutOfBoundsException("Slot cannot be bigger than inventory size!");
        }
        this.items.put(slot, item);
        this.inventory.setItem(slot, item.getItemStack());
    }

    public void setItem(Item item, List<Integer> slots) {
        slots.forEach(slot -> setItem(item, slot));
    }

    public void addItem(Item item) {
        setItem(item, inventory.firstEmpty());
    }

    public abstract void onOpen(InventoryOpenEvent event);
    public abstract void onClose(InventoryCloseEvent event);
    public abstract void onClick(InventoryClickEvent event);

    public Item getItemBySlot(int slot) {
        return this.items.get(slot);
    }

}
