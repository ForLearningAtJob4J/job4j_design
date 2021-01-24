package ru.job4j.lsp.productstorage;

import ru.job4j.lsp.productstorage.products.Food;
import ru.job4j.lsp.productstorage.stores.Store;

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

    void resort(List<Food> foods) {
        stores.forEach(Store::clear);

        foods.forEach(
                food -> stores.stream()
                        .filter(s -> s.match(food))
                        .findFirst().ifPresent(s -> s.add(food)
                        ));
    }

    void resort() {
        resort(stores.stream()
                .flatMap(store -> store.clear().stream())
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    void resortWithNew(List<Food> foods) {
        List<Food> list = stores.stream()
                .flatMap(store -> store.clear().stream())
                .collect(Collectors.toCollection(ArrayList::new));
        list.addAll(foods);
        resort(list);
    }
}
