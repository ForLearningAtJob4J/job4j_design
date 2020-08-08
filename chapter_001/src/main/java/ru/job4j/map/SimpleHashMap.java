package ru.job4j.map;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;

@SuppressWarnings({"rawtypes", "unchecked"})
public class SimpleHashMap<K, V> implements Iterable<V> {
    private SimpleEntry<K, V>[] container = new SimpleEntry[4];
    private int modCount = 0;
    private int size = 0;

    private int hash(K key) {
        return key == null ? 0 : key.hashCode() ^ key.hashCode() >>> 16;
    }

    private int resize() {
        if (size > container.length * 0.75) {
            int newLength = container.length << 1;
            SimpleEntry<K, V>[] newContainer = new SimpleEntry[newLength];
            for (SimpleEntry<K, V> kvSimpleEntry : container) {
                if (kvSimpleEntry != null) {
                    newContainer[hash(kvSimpleEntry.getKey()) & (newLength)] = new SimpleEntry(kvSimpleEntry.getKey(), kvSimpleEntry.getValue());
                }
            }
            container = newContainer;
        }
        return container.length;
    }

    public boolean insert(K key, V value) {
        resize();
        int i = hash(key) & (container.length - 1);
        if (container[i] != null && Objects.equals(container[i].getKey(), key)) {
            return false;
        }
        container[i] = new SimpleEntry<>(key, value);
        modCount++;
        size++;
        return true;
    }

    public V get(K key) {
        V res = null;
        int i = hash(key) & (container.length - 1);
        if (container[i] != null && Objects.equals(container[i].getKey(), key)) {
            res = container[i].getValue();
        }
        return res;
    }

    public boolean delete(K key) {
        boolean res = false;
        int i = hash(key) & (container.length - 1);
        if (container[i] != null && Objects.equals(container[i].getKey(), key)) {
            res = true;
            container[i] = null;
            modCount++;
            size--;
        }
        return res;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            int position = 0;
            @Override
            public boolean hasNext() {
                boolean res = false;
                while (position < container.length) {
                    if (container[position] != null) {
                        res = true;
                        break;
                    }
                    position++;
                }
                return res;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[position++].getValue();
            }
        };
    }
}