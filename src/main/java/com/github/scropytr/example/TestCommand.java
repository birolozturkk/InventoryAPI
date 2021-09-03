package com.github.scropytr.example;

import com.github.scropytr.entities.concretes.Item;
import com.github.scropytr.entities.concretes.Pagination;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCommand implements CommandExecutor {

    com.github.scropytr.InventoryAPI inventoryAPI;

    public TestCommand(com.github.scropytr.InventoryAPI inventoryAPI) {
        this.inventoryAPI = inventoryAPI;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            ExampleGUI exampleGUI = new ExampleGUI("exampleGUI", 54);
            Pagination pagination = exampleGUI.getPagination();

            List<Item> items = new ArrayList<>();

            for(int i = 0; i < 304; i++){
                int finalI = i;
                Item item = new Item(new ItemStack(Material.GOLD_BLOCK), event -> {
                    event.getWhoClicked().sendMessage(String.valueOf(finalI));
                });
                items.add(item);
            }

            pagination.setSlots(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35));
            pagination.setItems(items);

            Item nextButton = new Item(new ItemStack(Material.PAPER), clickEvent -> {
                clickEvent.setCancelled(true);
                pagination.nextPage(clickEvent.getWhoClicked());
                clickEvent.getWhoClicked().sendMessage("sayfa : "+pagination.getCurrentPage());
            });

            Item previousButton = new Item(new ItemStack(Material.PAPER), clickEvent -> {
                clickEvent.setCancelled(true);
                pagination.previousPage(clickEvent.getWhoClicked());
                clickEvent.getWhoClicked().sendMessage("sayfa : "+pagination.getCurrentPage());
            });

            exampleGUI.setItem(42, nextButton);
            exampleGUI.setItem(40, previousButton);

            exampleGUI.open(player);
        }
        return false;
    }
}
