package com.github.zblackops.entities.concretes;

import com.github.zblackops.InventoryAPI;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;
import java.util.List;

public class GUI implements InventoryHolder {

    private String title;
    private int size;

    private Inventory inventory;
    private final InventoryAPI inventoryAPI;
    @Getter private Pagination pagination;

    private HashMap<Integer, ItemX> items = new HashMap<>();

    public GUI(InventoryAPI inventoryAPI, String title, int size) {
        this.inventoryAPI = inventoryAPI;
        this.title = title;
        this.size = size;
        this.inventory = Bukkit.createInventory(this, size, title);
        this.items = new HashMap<Integer, ItemX>();
    }

    public GUI(InventoryAPI inventoryAPI, Inventory inventory) {
        this.inventoryAPI = inventoryAPI;
        this.inventory = inventory;
        this.title = inventory.getTitle();
        this.size = inventory.getSize();
    }

    public void open(Player player) {
        this.inventoryAPI.getCurrentGUI().put(player.getUniqueId(), this);
        player.openInventory(this.inventory);
    }

    public void setItem(int slot, ItemX itemX) {
        if (this.inventory.getSize() <= slot){
        throw new IndexOutOfBoundsException("Slot cannot be bigger than inventory size!");
    }
        this.items.put(slot, itemX);
        this.inventory.setItem(slot, itemX.getItem());
    }

    public void addItem(ItemX itemX) {
        setItem(this.inventory.firstEmpty(), itemX);
    }

    public void addItemMultiple(List<ItemX> itemXList) {
        itemXList.stream().findFirst().ifPresent(itemX -> addItem(itemX));
    }

    public void onOpen(InventoryOpenEvent event) {
    }

    public void onClose(InventoryCloseEvent event) {
    }

    public void onClick(InventoryClickEvent event) {
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    public Pagination getPagination(){
        this.pagination = new Pagination(this);
        return pagination;
    }

    public ItemX getItemBySlot(int slot){
        return this.items.get(slot);
    }

    public ItemX getItemBySlotAndPage(int slot, int page){
        if (slot < 0 || slot > this.inventory.getSize())
            return null;

        return getItemBySlot((page * this.pagination.getSlots().size()) + slot);
        }

    public boolean containsItemBySlot(int slot){
        return this.items.containsKey(slot);
    }

}
