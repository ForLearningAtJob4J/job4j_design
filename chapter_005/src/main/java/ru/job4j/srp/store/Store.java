package ru.job4j.srp.store;

import java.util.List;
import java.util.function.Predicate;

public interface Store<T> {
    List<T> findBy(Predicate<T> filter);
}