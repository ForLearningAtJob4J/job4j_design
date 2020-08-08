package ru.job4j.map;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class SimpleHashMapTest {

    @Test
    public void whenAddThenHereItIs() {
        SimpleHashMap<String, User> map = new SimpleHashMap<>();
        map.insert("AAA", new User("George"));
        assertThat(map.get("AAA"), is(new User("George")));
    }

    @Test
    public void whenAdd2ThenSizeEq2() {
        SimpleHashMap<String, User> map = new SimpleHashMap<>();
        map.insert("AAA", new User("George"));
        map.insert("AAB", new User("John"));
        assertThat(map.size(), is(2));
    }

    @Test
    public void whenAdd2AndDelete1SizeEq1() {
        SimpleHashMap<String, User> map = new SimpleHashMap<>();
        map.insert("AAA", new User("George"));
        map.insert("AAB", new User("John"));
        map.delete("AAB");
        assertThat(map.size(), is(1));
    }

    @Test
    public void whenAddTheSameThenFalse() {
        SimpleHashMap<String, User> map = new SimpleHashMap<>();
        map.insert("AAA", new User("Ivan"));
        assertThat(map.insert("AAA", new User("Mary")), is(false));
    }

    @Test
    public void whenIteratorHasNoMoreElements() {
        SimpleHashMap<String, User> map = new SimpleHashMap<>();
        map.insert("A", new User());
        Iterator<User> iterator = map.iterator();
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElementException() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurrentModificationException() {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.insert("A", 1);
        map.insert("B", 2);
        Iterator<Integer> iterator = map.iterator();
        map.insert("C", 3);
        iterator.next();
    }
}