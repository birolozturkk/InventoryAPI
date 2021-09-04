package com.github.scropytr.entities.concretes;

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

public class GUI implements InventoryHolder {

    private String title;
    private int size;
    private HashMap<Integer, Item> items = new HashMap<>();

    private transient Inventory inventory;

    @Getter
    private transient Pagination pagination;

    public GUI(String title, int size) {
        this.title = title;
        this.size = size;
        createInventory(size, title);
    }

    public GUI(Inventory inventory) {
        this.inventory = inventory;
        this.title = inventory.getTitle();
        this.size = inventory.getSize();
    }

    public void open(Player player) {
        player.openInventory(this.inventory);
    }

    public void setItem(int slot, Item item) {
        if (this.inventory.getSize() <= slot){
        throw new IndexOutOfBoundsException("Slot cannot be bigger than inventory size!");
    }
        this.items.put(slot, item);
        this.inventory.setItem(slot, item.getItemStack());
    }

    public void addItem(Item item) {
        setItem(this.inventory.firstEmpty(), item);
    }

    public void addItemMultiple(List<Item> itemList) {
        itemList.stream().findFirst().ifPresent(itemX -> addItem(itemX));
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

    public Item getItemBySlot(int slot){
        return this.items.get(slot);
    }

    public void setTitle(String title){
        createInventory(this.size, title);
    }

    public void setSize(int size){
        createInventory(size, this.title);
    }

    public void createInventory(int size, String title){
        this.inventory = Bukkit.createInventory(this, size, title);
    }


}
