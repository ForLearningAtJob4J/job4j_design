package ru.job4j.lsp.stores;

import ru.job4j.lsp.products.Food;

import java.util.List;

public interface Store {
    List<Food> getFoods();
    boolean match(Food food);
}
