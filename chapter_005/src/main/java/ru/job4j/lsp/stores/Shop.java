package ru.job4j.lsp.stores;

import ru.job4j.lsp.products.Food;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AbstractStore {
    public Shop() {
        this(new ArrayList<>());
    }

    public Shop(List<Food> foods) {
        super(foods, food -> {
            int percent = food.getTimePercent();
            if (percent > 75 && food.getDiscount() == 0) {
                food.setDiscount(50);
            }
            return percent >= 25 && percent < 100;
        });
    }
}