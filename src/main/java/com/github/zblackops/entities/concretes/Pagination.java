package com.github.zblackops.entities.concretes;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

@Data
public class Pagination {

    private GUI gui;

    private List<ItemX> items;

    private List<Integer> slots;

    private HashMap<Integer, List<ItemX>> getItemsByPage = new HashMap<>();

    private int currentPage;

    public Pagination(GUI gui) {
        this.gui = gui;
        this.currentPage = 0;
    }

    public void setItems(List<ItemX> items){
        this.items = items;
        refreshGUI(currentPage);
    }

    public boolean refreshGUI(int newPage) {

        int itemsSize = this.items.size();
        int slotsSize = this.slots.size();

        int m = 0;
        for (int first = newPage * slotsSize; first < (newPage + 1) * slotsSize; first++) {
            ItemX itemX;
            if (first >= itemsSize) itemX = new ItemX(new ItemStack(Material.AIR));
            else itemX = this.items.get(first);
            this.gui.setItem(this.slots.get(m), itemX);
            m++;
        }
        return true;
    }

    public void setPage(HumanEntity viewer, int newPage) {
        if(newPage<0) return;
        if(newPage>this.items.size()/this.slots.size()) return;
        if (!refreshGUI(newPage)) return;
        this.currentPage = newPage;
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
