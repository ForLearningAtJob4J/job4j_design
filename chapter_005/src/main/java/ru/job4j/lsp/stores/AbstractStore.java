package ru.job4j.lsp.stores;

import ru.job4j.lsp.products.Food;

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
    public List<Food> getFoods() {
        return foods;
    }
}