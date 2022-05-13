package com.github.scropytr.legendinventoryapi.listeners;

import com.github.scropytr.legendinventoryapi.gui.GUI;
import com.github.scropytr.legendinventoryapi.item.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class GUIListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getClickedInventory() == null) return;
        if (event.getClickedInventory().getHolder() != null && event.getClickedInventory().getHolder() instanceof GUI) {
            if (event.getWhoClicked() instanceof Player) {
                GUI gui = (GUI) event.getInventory().getHolder();
                gui.onClick(event);

                Item item = gui.getItemBySlot(event.getSlot());
                if(item == null || item.getClickAction() == null) return;
                item.getClickAction().accept(event);
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        if (event.getInventory().getHolder() != null && event.getInventory().getHolder() instanceof GUI) {
            if (event.getPlayer() instanceof Player) {
                GUI gui = (GUI) event.getInventory().getHolder();
                gui.onClose(event);

                }
            }
        }

    @EventHandler
    public void onOpen(InventoryOpenEvent event){
        if (event.getInventory().getHolder() != null && event.getInventory().getHolder() instanceof GUI) {
            if (event.getPlayer() instanceof Player) {
                GUI gui = (GUI) event.getInventory().getHolder();
                gui.onOpen(event);

                }
            }
        }
    }
