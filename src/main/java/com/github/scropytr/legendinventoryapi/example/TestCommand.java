package com.github.scropytr.legendinventoryapi.example;

import com.github.scropytr.legendinventoryapi.InventoryAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.function.Supplier;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            ExampleGUI exampleGUI = new ExampleGUI();

            exampleGUI.open(player);


        }
        return false;
    }
}