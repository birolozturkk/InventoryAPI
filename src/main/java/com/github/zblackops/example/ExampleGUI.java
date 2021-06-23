package com.github.zblackops.example;

import com.github.zblackops.InventoryAPI;
import com.github.zblackops.entities.concretes.GUI;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class ExampleGUI extends GUI {

    public ExampleGUI(InventoryAPI inventoryAPI, String title, int size) {
        super(inventoryAPI, title, size);
    }
}
