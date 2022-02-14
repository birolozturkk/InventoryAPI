package com.github.scropytr.legendinventoryapi;

import com.github.scropytr.legendinventoryapi.example.TestCommand;
import com.github.scropytr.legendinventoryapi.listeners.GUIListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class InventoryAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        setup(this);
        getCommand("test").setExecutor(new TestCommand());
    }

    public static void setup(Plugin plugin){

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new GUIListener(), plugin);

    }

}
