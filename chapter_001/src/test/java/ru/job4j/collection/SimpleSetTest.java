package ru.job4j.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    private SimpleSet<String> set;

    @Before
    public void makeUp() {
        set = new SimpleSet<>();
        set.add("first");
        set.add("second");
    }

    @Test
    public void whenAddsThenNoDubs() {
        set.add("first");
        set.add("second");
        assertEquals(set.size(), 2);
    }

    @Test
    public void whenGetItAndHasNextTwoTimeAndThenHasnt() {
        Iterator<String> it  = set.iterator();
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext());
    }
}