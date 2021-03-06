package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<E> implements Iterable<E> {
    private SimpleArray<E> container = new SimpleArray<>();

    public int size() {
        return container.size();
    }

    public boolean contains(E e) {
        for (E item :container) {
            if (Objects.equals(item, e)) {
                return true;
            }
        }
        return false;
    }

    void add(E e) {
        if (!contains(e)) {
            container.add(e);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return container.iterator();
    }
}
