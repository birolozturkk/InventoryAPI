package com.github.scropytr.legendinventoryapi;

import com.github.scropytr.legendinventoryapi.listeners.GUIListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class InventoryAPI {

    public static void setup(Plugin plugin) {

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new GUIListener(), plugin);

    }

}
