package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return testValue(value, comparator, x -> x > 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return testValue(value, comparator, x -> x < 0);
    }

    private <T> T testValue(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
        T res = null;
        if (value.size() > 0) {
            res = value.get(0);
            for (T item: value) {
                if (predicate.test(comparator.compare(item, res))) {
                    res = item;
                }
            }
        }
        return res;
    }
}