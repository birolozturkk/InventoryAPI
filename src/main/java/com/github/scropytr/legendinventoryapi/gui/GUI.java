package com.github.scropytr.legendinventoryapi.gui;

import com.github.scropytr.legendinventoryapi.item.Item;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;

@Getter
public abstract class GUI implements InventoryHolder {

    private String title;
    private int size;
    private HashMap<Integer, Item> items = new HashMap<>();

    private transient Inventory inventory;

    public GUI(String title, int size) {
        this.title = title;
        this.size = size;
        this.inventory = Bukkit.createInventory(this, size, title);
    }

    public GUI(Inventory inventory) {
        this.inventory = inventory;
        this.title = inventory.getTitle();
        this.size = inventory.getSize();
    }

    public void open(Player player) {
        player.openInventory(this.inventory);
    }

    public void setItem(Item item, int slot) {
        if (this.inventory.getSize() <= slot) {
            throw new IndexOutOfBoundsException("Slot cannot be bigger than inventory size!");
        }
        this.items.put(slot, item);
        this.inventory.setItem(slot, item.getItemStack());
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void createInventory(int size, String title) {
        this.inventory = Bukkit.createInventory(this, size, title);
        items.forEach((slot, item) -> inventory.setItem(slot, item.getItemStack()));
    }

    public void update(Player player, String title) {/*
        EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
        PacketPlayOutOpenWindow packet = new PacketPlayOutOpenWindow(entityPlayer.activeContainer.windowId, "minecraft:chest",
                new ChatMessage(title), player.getOpenInventory().getTopInventory().getSize());
        entityPlayer.playerConnection.sendPacket(packet);
        entityPlayer.updateInventory(entityPlayer.activeContainer);*/
    }


}
