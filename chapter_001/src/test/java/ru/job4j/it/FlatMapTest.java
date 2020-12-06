package ru.job4j.it;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class FlatMapTest {
    @Test
    public void whenDiffNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator(),
                List.of(2, 3).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertEquals(flat.next(), Integer.valueOf(1));
        assertEquals(flat.next(), Integer.valueOf(2));
        assertEquals(flat.next(), Integer.valueOf(3));
    }

    @Test
    public void whenSeqNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertEquals(flat.next(), Integer.valueOf(1));
        assertEquals(flat.next(), Integer.valueOf(2));
        assertEquals(flat.next(), Integer.valueOf(3));
    }

    @Test
    public void whenMultiHasNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertTrue(flat.hasNext());
        assertTrue(flat.hasNext());
    }

    @Test
    public void whenHasNextFalse() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertEquals(flat.next(), Integer.valueOf(1));
        assertFalse(flat.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmpty() {
        Iterator<Iterator<Object>> data = List.of(
                Collections.emptyIterator()
        ).iterator();
        FlatMap<Object> flat = new FlatMap<>(data);
        flat.next();
    }

    @Test
    public void whenHasEmptyInTheMiddle() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator(),
                Collections.<Integer>emptyIterator(),
                List.of(2).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        assertEquals(flat.next(), Integer.valueOf(1));
        assertEquals(flat.next(), Integer.valueOf(2));
        assertFalse(flat.hasNext());
    }
}