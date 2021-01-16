package ru.job4j.lsp.productstorage.stores;

import ru.job4j.lsp.productstorage.products.Food;

import java.util.ArrayList;
import java.util.List;

public class Trash extends AbstractStore {
    public Trash() {
        this(new ArrayList<>());
    }
    public Trash(List<Food> foods) {
        super(foods, food -> food.getTimePercent() == 100);
    }
}
