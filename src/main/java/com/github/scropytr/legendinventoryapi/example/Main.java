package com.github.scropytr.legendinventoryapi.example;

import com.github.scropytr.legendinventoryapi.gui.GUI;
import com.github.scropytr.legendinventoryapi.listeners.GUIListener;
import org.bukkit.Bukkit;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    public static List<User> users = new ArrayList<>();

    @Override
    public void onEnable() {
        for(int i = 0; i<100; i++) {
            users.add(new User("BlackOPS"));
        }

/*        Bukkit.getScheduler().runTaskTimer(this, () -> Bukkit.getServer().getOnlinePlayers().forEach(player -> {
            InventoryHolder inventoryHolder = player.getOpenInventory().getTopInventory().getHolder();
            if (inventoryHolder instanceof GUI) {
                ((GUI) inventoryHolder).addContent();
                System.out.println("auto");
            }
        }), 0, 20);*/

        getCommand("test").setExecutor(new TestCommand());
        getServer().getPluginManager().registerEvents(new GUIListener(), this);
    }
}
