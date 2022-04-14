package com.github.scropytr.legendinventoryapi.example;

import com.cryptomorin.xseries.XMaterial;
import com.github.scropytr.legendinventoryapi.gui.GUI;
import com.github.scropytr.legendinventoryapi.gui.PaginatedGUI;
import com.github.scropytr.legendinventoryapi.item.Item;
import com.github.scropytr.legendinventoryapi.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.*;
import java.util.function.Consumer;

public class ExampleGUI extends PaginatedGUI<User> {

    @Override
    public String getTitle() {
        return "§8§nChoose a player";
    }

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public void addContent() {
        List<Integer> slots = Arrays.asList(0,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 51, 52, 53);

        setItem(new ItemBuilder(XMaterial.BLACK_STAINED_GLASS_PANE).setDisplayName("").build(), slots);

        Item closeItem = new ItemBuilder(XMaterial.BARRIER).setDisplayName("§c§lClose")
                .glowing().build()
                .withListener(clickEvent -> clickEvent.getWhoClicked().closeInventory());

        Item nextPage = new ItemBuilder(XMaterial.ARROW).setDisplayName("§a§lNext").build()
                .withListener(clickEvent -> nextPage());

        Item previousPage = new ItemBuilder(XMaterial.ARROW).setDisplayName("§e§lPrevious").build()
                .withListener(clickEvent -> previousPage());

        setItem(closeItem, 49);
        setItem(previousPage, 48);
        setItem(nextPage, 50);

        super.addContent();
    }


    @Override
    public List<User> getPaginatedObjects() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i<60; ++i) {
            users.add(new User("Scropy"));
        }
        return users;
    }

    @Override
    public Item getItem(User user) {
        return new ItemBuilder(XMaterial.PLAYER_HEAD).setDisplayName("§b" + user.getName())
                .setLore(Arrays.asList("", " §e► Click to select player"))
                .setHeadData(SkinUtils.getHeadData(SkinUtils.getUUID("StopDontTalkToMe")))
                .build();
    }

    @Override
    public List<Integer> getSlots() {
        return Arrays.asList(
                10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43
        );
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

}