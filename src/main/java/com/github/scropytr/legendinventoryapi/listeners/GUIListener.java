package com.github.scropytr.legendinventoryapi.listeners;

import com.github.scropytr.legendinventoryapi.gui.GUI;
import com.github.scropytr.legendinventoryapi.item.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class GUIListener implements Listener {

    private final Plugin owner;

    public GUIListener(Plugin owner) {
        this.owner = owner;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() != null && event.getInventory().getHolder() instanceof GUI) {
            if (event.getWhoClicked() instanceof Player) {
                if(event.getClickedInventory() == null) return;
                GUI gui = (GUI) event.getInventory().getHolder();
                if(!gui.getOwner().equals(owner)) return;
                gui.onClick(event);

               if(event.getClickedInventory().getHolder() == null ||
                       !(event.getClickedInventory().getHolder() instanceof GUI)) return;

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
