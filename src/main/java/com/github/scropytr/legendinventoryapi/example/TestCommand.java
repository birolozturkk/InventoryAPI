package com.github.scropytr.legendinventoryapi.example;

import com.cryptomorin.xseries.XMaterial;
import com.github.scropytr.legendinventoryapi.InventoryAPI;
import com.github.scropytr.legendinventoryapi.gui.Pagination;
import com.github.scropytr.legendinventoryapi.item.Item;
import org.bukkit.Material;
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

            String title = "GUI Page: %page%";
            ExampleGUI exampleGUI = new ExampleGUI(title.replace("%page%", "1"), 27);

            List<Item> items = new ArrayList<>();
            for (int i = 0; i <= 100; i++) {
                Item item = new Item(XMaterial.PLAYER_HEAD).setDisplayName("display name").setLore(Arrays.asList("s", "s1", "s2")).setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzYxZDY1N2M2ZWUwODNiZGE1NjQxMzQ1N2Y3YzEwY2JhZmVkNjdlMmJkNmFjOWYzZjRlNDg1Yzk0Zjg5Yjg3MSJ9fX0=").build();
                items.add(item);
            }

            Pagination pagination = exampleGUI.getPagination();
            pagination.setSlots(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19));

            pagination.setItems(items);

            Item nextPageButton = new Item(XMaterial.PLAYER_HEAD).setDisplayName("next")
                    .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWE5MmFkNDU2Zjc2ZWM3Y2FhMzU5NTkyMmQ1ZmNjMzhkY2E5MjhkYzY3MTVmNzUyZTc0YzhkZGRlMzQ0ZSJ9fX0=")
                    .setAction(clickEvent -> {
                        pagination.nextPage(player);
                        exampleGUI.update(player, title.replace("%page%", String.valueOf(pagination.getCurrentPage()+1)));
                    }).build();

            Item previousPageButton = new Item(XMaterial.PLAYER_HEAD).setDisplayName("previous")
                    .setTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGZhNjA1ZTI1ZjRmYzJjZWE1YTc2NmQ3OWE4YmZhMjkwMzEzZTQ1ZDhmNWU5NTdkOTU4YTBmMzNmY2IxNiJ9fX0=")
                    .setAction(clickEvent -> {
                        pagination.previousPage(player);
                        exampleGUI.update(player, title.replace("%page%", String.valueOf(pagination.getCurrentPage()+1)));
                    })
                    .build();


            exampleGUI.setItem(nextPageButton, 23);
            exampleGUI.setItem(previousPageButton, 22);

            exampleGUI.open(player);
        }
        return false;
    }
}
