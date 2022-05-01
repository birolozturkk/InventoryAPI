package com.github.scropytr.legendinventoryapi.gui;

import com.cryptomorin.xseries.XMaterial;
import com.github.scropytr.legendinventoryapi.item.Item;
import com.github.scropytr.legendinventoryapi.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.inventory.Inventory;

import java.util.List;

public abstract class PaginatedGUI<T> extends GUI {

    @Getter
    private int currentPage;

    public Item getBackgroundItem() {
        return new Item(XMaterial.AIR);
    }

    public abstract List<Integer> getSlots();
    public abstract List<T> getPaginatedObjects();
    public abstract Item getItem(T t);

    @Override
    public void addContent() {

        int itemsSize = getPaginatedObjects().size();
        int slotsSize = getSlots().size();

        for (int i = 0; i < slotsSize; i++) {
            Item item;
            int index = currentPage * slotsSize + i;
            if(index >= itemsSize) item = getBackgroundItem();
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
