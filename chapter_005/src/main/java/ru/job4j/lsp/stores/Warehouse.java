package ru.job4j.lsp.stores;

import ru.job4j.lsp.products.Food;

import java.util.ArrayList;
import java.util.List;

public class Warehouse extends AbstractStore {
    public Warehouse() {
        this(new ArrayList<>());
    }
    public Warehouse(List<Food> foods) {
        super(foods, food -> food.getTimePercent() < 25);
    }
}
