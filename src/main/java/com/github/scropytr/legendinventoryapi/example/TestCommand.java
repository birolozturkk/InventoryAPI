package com.github.scropytr.legendinventoryapi.example;

import com.cryptomorin.xseries.XMaterial;
import com.github.scropytr.legendinventoryapi.gui.PaginatedGUI;
import com.github.scropytr.legendinventoryapi.item.Item;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            ExampleGUI exampleGUI = new ExampleGUI("Title", 54);

            List<Item> items = new ArrayList<>();
            for (int i = 0; i <= 105; i++) {
                Item item = new Item(XMaterial.CACTUS).build();
                item.setAction(clickEvent -> {
                    clickEvent.getWhoClicked().sendMessage("clicked");
                });
                items.add(item);
            }

            exampleGUI.setSlots(Arrays.asList(0, 1, 2, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 17));
            exampleGUI.setItems(items);

            Item nextPageButton = new Item(XMaterial.PLAYER_HEAD).setDisplayName("next")
                    .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWE5MmFkNDU2Zjc2ZWM3Y2FhMzU5NTkyMmQ1ZmNjMzhkY2E5MjhkYzY3MTVmNzUyZTc0YzhkZGRlMzQ0ZSJ9fX0=")
                    .setAction(clickEvent -> {
                        exampleGUI.nextPage(player);
                        clickEvent.getWhoClicked().sendMessage("Sayfa: " + exampleGUI.getCurrentPage());
                    }).build();

            Item previousPageButton = new Item(XMaterial.PLAYER_HEAD).setDisplayName("previous")
                    .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGZhNjA1ZTI1ZjRmYzJjZWE1YTc2NmQ3OWE4YmZhMjkwMzEzZTQ1ZDhmNWU5NTdkOTU4YTBmMzNmY2IxNiJ9fX0=")
                    .setAction(clickEvent -> {
                        exampleGUI.previousPage(player);
                        clickEvent.getWhoClicked().sendMessage("Sayfa: " + exampleGUI.getCurrentPage());
                    }).build();


            exampleGUI.setItem(nextPageButton, 23);
            exampleGUI.setItem(previousPageButton, 22);

            exampleGUI.open(player);
        }
        return false;
    }
}
