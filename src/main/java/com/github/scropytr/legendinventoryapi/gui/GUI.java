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

import java.util.HashMap;
import java.util.List;

@Getter
public abstract class GUI implements InventoryHolder {

    private final HashMap<Integer, Item> items = new HashMap<>();

    private final transient Inventory inventory;

    public GUI() {
        this.inventory = Bukkit.createInventory(this, getSize(), getTitle());
    }

    public GUI(Inventory inventory) {
        this.inventory = inventory;
    }

    public void open(Player player) {
        addContent();
        player.openInventory(this.inventory);
    }

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

    public abstract void addContent();

    public abstract String getTitle();
    public abstract int getSize();

    public Item getItemBySlot(int slot) {
        return this.items.get(slot);
    }

    public void update(Player player, String title) {/*
        EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
        PacketPlayOutOpenWindow packet = new PacketPlayOutOpenWindow(entityPlayer.activeContainer.windowId, "minecraft:chest",
                new ChatMessage(title), player.getOpenInventory().getTopInventory().getSize());
        entityPlayer.playerConnection.sendPacket(packet);
        entityPlayer.updateInventory(entityPlayer.activeContainer);*/
    }


}
