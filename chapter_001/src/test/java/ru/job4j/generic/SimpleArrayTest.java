package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    private SimpleArray<String> array;

    @Before
    public void setUp() {
        array = new SimpleArray<>(10);
        array.add("first");
        array.add("second");
        array.add("third");
    }

    @Test
    public void addTest() {
        array.add("fourth");
        assertEquals(array.size(), 4);
        assertEquals(array.get(array.size() - 1), "fourth");
    }


    @Test
    public void setTest() {
        array.set(2, "changed");
        assertEquals(array.size(), 3);
        assertEquals(array.get(2), "changed");
    }

    @Test
    public void removeTest() {
        array.remove(1);
        assertEquals(array.size(), 2);
        assertEquals(array.get(0), "first");
    }

    @Test
    public void getTest() {
        assertEquals(array.get(0), "first");
        assertEquals(array.get(2), "third");
    }

    @Test
    public void iteratorTest() {
        var it = array.iterator();
        assertEquals(it.next(), "first");
        assertEquals(it.next(), "second");
        assertEquals(it.next(), "third");
        assertFalse(it.hasNext());
    }
}