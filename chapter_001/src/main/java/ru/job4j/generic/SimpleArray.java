package ru.job4j.generic;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private final float multiplier = 1.5f;
    private Object[] data;
    private int size;

    SimpleArray(int capacity) {
        data = new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean add(T model) {
        if (++size == data.length) {
            Object[] newData = new Object[(int) (data.length * multiplier)];
            newData = Arrays.copyOf(newData, size);
            data = newData;
        }
        data[size - 1] = model;
        return true;
    }

    @SuppressWarnings("unchecked")
    public T set(int index, T model) {
        T oldValue = (T) data[Objects.checkIndex(index, size)];
        data[Objects.checkIndex(index, size)] = model;
        return oldValue;
    }

    public void remove(int index) {
        index = Objects.checkIndex(index, size);
        System.arraycopy(data, index + 1, data, index, size - 1 - index);
        size--;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) data[Objects.checkIndex(index, size)];
    }

    @SuppressWarnings("unchecked")
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int point = 0;

            @Override
            public boolean hasNext() {
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) data[point++];
            }
        };
    }
}
