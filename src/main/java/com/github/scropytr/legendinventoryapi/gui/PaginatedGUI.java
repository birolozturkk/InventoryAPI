package com.github.scropytr.legendinventoryapi.gui;

import com.cryptomorin.xseries.XMaterial;
import com.github.scropytr.legendinventoryapi.item.Item;
import com.github.scropytr.legendinventoryapi.item.ItemBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class PaginatedGUI<T> extends GUI {

    @Getter
    private int currentPage;

    @Override
    public void addContent() {

        int itemsSize = getPaginatedObjects().size();
        int slotsSize = getSlots().size();

        for (int i = 0; i < slotsSize; i++) {
            Item item;
            int index = currentPage * slotsSize + i;
            if(index >= itemsSize) item = new ItemBuilder(XMaterial.AIR).build();
            else item = getItem(getPaginatedObjects().get(index));
            setItem(item, getSlots().get(i));
        }
    }

    public void setPage(int newPage) {
        if (newPage < 0) return;
        if (newPage >= (double) getPaginatedObjects().size() / (double) getSlots().size()) return;
        this.currentPage = newPage;
        addContent();
    }

    public abstract List<Integer> getSlots();

    public abstract List<T> getPaginatedObjects();

    public abstract Item getItem(T t);

    public void nextPage() {
        setPage(this.currentPage + 1);
    }

    public void previousPage() {
        setPage(currentPage - 1);
    }

    public void firstPage() {
        setPage(0);
    }

    public void lastPage() {
        setPage(getPaginatedObjects().size() / getPaginatedObjects().size() - 1);
    }
}
