package com.github.scropytr.legendinventoryapi;

import com.github.scropytr.legendinventoryapi.example.TestCommand;
import com.github.scropytr.legendinventoryapi.example.User;
import com.github.scropytr.legendinventoryapi.listeners.GUIListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InventoryAPI {

    public static void setup(Plugin plugin) {

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new GUIListener(), plugin);

    }

}
