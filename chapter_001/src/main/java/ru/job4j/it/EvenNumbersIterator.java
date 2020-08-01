package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    int[] data;
    int index = 0;

    public EvenNumbersIterator(int[] ints) {
        data = ints;
    }

    private int getNextEvenPosition() {
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean hasNext() {
        return getNextEvenPosition() > -1;
    }

    @Override
    public Integer next() {
        int next = getNextEvenPosition();
        if (next == -1) {
            throw new NoSuchElementException();
        }
        index = next + 1;
        return data[next];
    }
}
