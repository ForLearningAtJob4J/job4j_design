package ru.job4j.lsp.productstorage.stores;

import ru.job4j.lsp.productstorage.products.Food;

import java.util.List;

public interface Store {
    List<Food> getFoods();
    boolean match(Food food);
}
