package com.github.scropytr.legendinventoryapi.entities.abstracts;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

public interface Click {

    void onClick(InventoryClickEvent clickEvent);

}
