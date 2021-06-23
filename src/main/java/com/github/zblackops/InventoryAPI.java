package com.github.zblackops;

import com.github.zblackops.business.GUIListener;
import com.github.zblackops.entities.concretes.GUI;
import com.github.zblackops.entities.concretes.ItemX;
import com.github.zblackops.example.ExampleGUI;
import com.github.zblackops.example.TestCommand;
import com.hakan.invapi.inventory.invs.HInventory;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class InventoryAPI extends JavaPlugin {

    private HashMap<UUID, GUI> currentGUI = new HashMap<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new GUIListener(this), this);
        getCommand("test").setExecutor(new TestCommand(this));
    }

    public InventoryAPI(){

    }

    public HashMap<UUID, GUI> getCurrentGUI() {
        return currentGUI;
    }
}
