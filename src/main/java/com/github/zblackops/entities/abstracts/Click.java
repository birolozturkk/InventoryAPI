package com.github.zblackops.entities.abstracts;

import com.github.zblackops.entities.concretes.GUI;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface Click {

    void onClick(InventoryClickEvent clickEvent);

}
