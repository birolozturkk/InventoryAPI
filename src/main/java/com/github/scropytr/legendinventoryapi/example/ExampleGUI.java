package com.github.scropytr.legendinventoryapi.example;

import com.github.scropytr.legendinventoryapi.gui.PaginatedGUI;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class ExampleGUI extends PaginatedGUI {

    public ExampleGUI(String title, int size) {
        super(title, size);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {

    }

    @Override
    public void onClose(InventoryCloseEvent event) {

    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
    }

    @Override
    public void addContent() {

    }

}
