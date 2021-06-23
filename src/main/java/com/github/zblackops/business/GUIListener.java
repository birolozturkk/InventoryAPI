package com.github.zblackops.business;

import com.github.zblackops.InventoryAPI;
import com.github.zblackops.entities.concretes.GUI;
import com.github.zblackops.entities.concretes.ItemX;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import sun.security.mscapi.CPublicKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
