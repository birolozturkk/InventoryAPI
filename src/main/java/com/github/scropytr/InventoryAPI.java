package com.github.scropytr;

import com.github.scropytr.listeners.GUIListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class InventoryAPI extends JavaPlugin {

    public InventoryAPI(){

    }

    public static void setup(Plugin plugin){

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new GUIListener(), plugin);

    }

}
