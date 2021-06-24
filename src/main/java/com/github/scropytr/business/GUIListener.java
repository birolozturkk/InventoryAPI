package com.github.scropytr.business;

import com.github.scropytr.entities.concretes.GUI;
import com.github.scropytr.entities.concretes.ItemX;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class GUIListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() != null && event.getInventory().getHolder() instanceof GUI) {
            if (event.getWhoClicked() instanceof Player) {
                Player player = (Player) event.getWhoClicked();
                GUI gui = (GUI) event.getInventory().getHolder();
                gui.onClick(event);

                ItemX itemX = gui.getItemBySlot(event.getSlot());
                if(itemX==null || itemX.getClick()==null) return;
                itemX.getClick().onClick(event);
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        if (event.getInventory().getHolder() != null && event.getInventory().getHolder() instanceof GUI) {
            if (event.getPlayer() instanceof Player) {
                Player player = (Player) event.getPlayer();
                GUI gui = (GUI) event.getInventory().getHolder();
                gui.onClose(event);

                }
            }
        }

    @EventHandler
    public void onOpen(InventoryOpenEvent event){
        if (event.getInventory().getHolder() != null && event.getInventory().getHolder() instanceof GUI) {
            if (event.getPlayer() instanceof Player) {
                Player player = (Player) event.getPlayer();
                GUI gui = (GUI) event.getInventory().getHolder();
                gui.onOpen(event);

                }
            }
        }
    }
