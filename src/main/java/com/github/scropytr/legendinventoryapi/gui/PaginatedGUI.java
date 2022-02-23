package com.github.scropytr.legendinventoryapi.gui;

import com.cryptomorin.xseries.XMaterial;
import com.github.scropytr.legendinventoryapi.item.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.List;


public abstract class PaginatedGUI extends GUI {

    @Getter
    private int currentPage = 0;

    @Setter
    private List<Item> items;
    @Setter
    private List<Integer> slots;


    public PaginatedGUI(String title, int size) {
        super(title, size);
    }

    @Override
    public void open(Player player) {
        refreshGUI();
        super.open(player);
    }

    public void refreshGUI() {

        int itemsSize = this.items.size();
        int slotsSize = this.slots.size();

        for (int i = 0; i < slotsSize; i++) {
            Item item;
            int index = currentPage * slotsSize + i;
            if(index >= itemsSize) item = new Item(XMaterial.AIR);
            else item = this.items.get(index);
            setItem(item, slots.get(i));
        }
    }

    public void setPage(HumanEntity viewer, int newPage) {
        if (newPage < 0) return;
        if (newPage >= (double) this.items.size() / (double) this.slots.size()) return;
        this.currentPage = newPage;
        refreshGUI();
        viewer.openInventory(getInventory());
    }

    public void nextPage(HumanEntity viewer) {
        setPage(viewer, this.currentPage + 1);
    }

    public void previousPage(HumanEntity viewer) {
        setPage(viewer, this.currentPage - 1);
    }

    public void firstPage(HumanEntity viewer) {
        setPage(viewer, 0);
    }

    public void lastPage(HumanEntity viewer) {
        setPage(viewer, this.items.size() / this.slots.size() - 1);
    }
}
