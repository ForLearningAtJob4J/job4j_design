package ru.job4j.srp.store;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MemStore<T> implements Store<T> {
    private final List<T> elements = new ArrayList<>();

    public void add(T em) {
        elements.add(em);
    }

    @Override
    public List<T> findBy(Predicate<T> filter) {
        return elements.stream().filter(filter).collect(Collectors.toList());
    }
}