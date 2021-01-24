package ru.job4j.lsp.productstorage.stores;

import ru.job4j.lsp.productstorage.products.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractStore implements Store {
    private final List<Food> foods;
    private final Predicate<Food> expirationChecker;

    AbstractStore(List<Food> foods, Predicate<Food> expirationChecker) {
        this.foods = foods;
        this.expirationChecker = expirationChecker;
    }

    @Override
    public boolean match(Food food) {
        return expirationChecker.test(food);
    }

    @Override
    public void add(Food food) {
        foods.add(food);
    }

    @Override
    public List<Food> clear() {
        List<Food> result = new ArrayList<>(foods);
        foods.clear();
        return result;
    }
}