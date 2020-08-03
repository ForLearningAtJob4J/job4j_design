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
        assertThat(array.size(), is(4));
        assertThat(array.get(array.size() - 1), is("fourth"));
    }


    @Test
    public void setTest() {
        array.set(2, "changed");
        assertThat(array.size(), is(3));
        assertThat(array.get(2), is("changed"));
    }

    @Test
    public void removeTest() {
        array.remove(1);
        assertThat(array.size(), is(2));
        assertThat(array.get(0), is("first"));
    }

    @Test
    public void getTest() {
        assertThat(array.get(0), is("first"));
        assertThat(array.get(2), is("third"));
    }

    @Test
    public void iteratorTest() {
        var it = array.iterator();
        assertThat(it.next(), is("first"));
        assertThat(it.next(), is("second"));
        assertThat(it.next(), is("third"));
        assertThat(it.hasNext(), is(false));
    }
}