package com.github.zblackops.example;

import com.github.zblackops.InventoryAPI;
import com.github.zblackops.entities.concretes.GUI;
import com.github.zblackops.entities.concretes.ItemX;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;

public class ExampleGUI extends GUI {

    public ExampleGUI(InventoryAPI inventoryAPI, String title, int size) {
        super(inventoryAPI, title, size);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        super.onOpen(event);

        ItemX itemX = new ItemX(new ItemStack(Material.GOLD_BLOCK), clickEvent -> {
            clickEvent.getWhoClicked().sendMessage(clickEvent.getSlot() + "clicked to Slot");
        });

        //Adds to specific slot
        setItem(5, itemX);

        //Adds into first empty slot
        addItem(itemX);

    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        super.onClose(event);

        event.getPlayer().sendMessage("ınventory closed");
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        super.onClick(event);

        event.setCancelled(true);
    }
}
