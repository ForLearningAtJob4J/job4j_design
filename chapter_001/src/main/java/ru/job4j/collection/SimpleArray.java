package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private final float multiplier = 1.5f;
    private Object[] container;
    private int modCount = 0;
    private int size;

    SimpleArray() {
        container = new Object[10];
    }

    public int size() {
        return size;
    }

    public boolean add(T model) {
        if (++size == container.length) {
            container = Arrays.copyOf(container, (int) (container.length * multiplier));
        }
        container[size - 1] = model;
        modCount++;
        return true;
    }

    @SuppressWarnings("unchecked")
    public T set(int index, T model) {
        T oldValue = (T) container[Objects.checkIndex(index, size)];
        container[index] = model;
        return oldValue;
    }

    public void remove(int index) {
        index = Objects.checkIndex(index, size);
        System.arraycopy(container, index + 1, container, index, size - 1 - index);
        size--;
        modCount++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) container[Objects.checkIndex(index, size)];
    }

    @SuppressWarnings("unchecked")
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int pointer = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return pointer < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[pointer++];
            }
        };
    }
}