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
        assertThat(set.size(), is(2));
    }

    @Test
    public void whenGetItAndHasNextTwoTimeAndThenHasnt() {
        Iterator<String> it  = set.iterator();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.hasNext(), is(false));
    }
}