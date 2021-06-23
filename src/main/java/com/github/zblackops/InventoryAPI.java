package com.github.zblackops;

import com.github.zblackops.business.GUIListener;
import com.github.zblackops.entities.concretes.GUI;
import com.github.zblackops.entities.concretes.ItemX;
import com.github.zblackops.example.ExampleGUI;
import com.github.zblackops.example.TestCommand;
import com.hakan.invapi.inventory.invs.HInventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class InventoryAPI extends JavaPlugin {

    public InventoryAPI(){

    }

    public static void setup(Plugin plugin){

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new GUIListener(), plugin);

    }

}
