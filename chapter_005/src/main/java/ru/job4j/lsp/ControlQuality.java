package ru.job4j.lsp;

import ru.job4j.lsp.products.Food;
import ru.job4j.lsp.stores.Store;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControlQuality {
    public static LocalDateTime datePointer = LocalDateTime.now();

    List<Store> stores;
    ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    void redistribute(List<Food> foods) {
        stores.forEach(store -> store.getFoods().clear());

        foods.forEach(
                food -> stores.stream()
                        .filter(s -> s.match(food))
                        .findFirst().ifPresent(s -> {
                            if (!s.getFoods().contains(food)) {
                                s.getFoods().add(food);
                            }
                        })
        );
    }

    void redistribute() {
        redistribute(stores.stream()
                .flatMap(store -> store.getFoods().stream())
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    void redistributeAddition(List<Food> foods) {
        List<Food> list = stores.stream()
                .flatMap(store -> store.getFoods().stream())
                .collect(Collectors.toCollection(ArrayList::new));
        list.addAll(foods);
        redistribute(list);
    }
}
