package ru.job4j.lsp.productstorage.stores;

import ru.job4j.lsp.productstorage.products.Food;

import java.util.List;

public interface Store {
    boolean match(Food f);

    void add(Food f);

    List<Food> clear();
}
