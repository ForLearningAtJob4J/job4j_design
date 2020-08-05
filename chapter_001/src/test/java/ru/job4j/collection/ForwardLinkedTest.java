package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ForwardLinkedTest {
    @Test
    public void whenAddThenGet() {
        ForwardLinked<String> list = new ForwardLinked<>();
        list.add("first");
        String rsl = list.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        ForwardLinked<String> list = new ForwardLinked<>();
        list.add("first");
        String rsl = list.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        ForwardLinked<String> list = new ForwardLinked<>();
        list.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        ForwardLinked<String> list = new ForwardLinked<>();
        list.add("first");
        list.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        ForwardLinked<String> list = new ForwardLinked<>();
        list.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        ForwardLinked<String> list = new ForwardLinked<>();
        list.add("first");
        Iterator<String> it = list.iterator();
        list.add("second");
        it.next();
    }

    @Test
    public void whenGetSizeOfTwoThenTwo() {
        ForwardLinked<String> list = new ForwardLinked<>();
        list.add("first");
        list.add("second");
        assertThat(list.size(), is(2));
    }
}