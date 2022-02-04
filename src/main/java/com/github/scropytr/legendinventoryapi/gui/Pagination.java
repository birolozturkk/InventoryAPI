package com.github.scropytr.legendinventoryapi.gui;

import com.github.scropytr.legendinventoryapi.item.Item;
import lombok.Data;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

@Data
public class Pagination {

    private GUI gui;

    private List<Item> items;
    private List<Integer> slots;
    private HashMap<Integer, List<Item>> getItemsByPage = new HashMap<>();

    private int currentPage;

    public Pagination(GUI gui) {
        this.gui = gui;
        this.currentPage = 0;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        refreshGUI(currentPage);
    }

    public boolean refreshGUI(int newPage) {//5

        int itemsSize = this.items.size(); //8
        int slotsSize = this.slots.size(); //3

        int m = 0;
        for (int first = newPage * slotsSize; first < (newPage + 1) * slotsSize; first++) {
            Item item;
            if (first >= itemsSize) item = new Item(new ItemStack(Material.AIR));
            else item = this.items.get(first);
            this.gui.setItem(item, slots.get(m));
            m++;
        }
        return true;
    }

    public void setPage(HumanEntity viewer, int newPage) {
        if (newPage < 0) return;
        if (newPage >= (double) this.items.size() / (double) this.slots.size()) return;
        this.currentPage = newPage;
        refreshGUI(newPage);
        viewer.openInventory(this.gui.getInventory());

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
