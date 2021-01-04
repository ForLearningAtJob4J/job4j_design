package ru.job4j.kiss;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MaxMinTest {
    List<Integer> list = Arrays.asList(1, 2, 3, 4);
    MaxMin mm = new MaxMin();

    @Test
    public void max() {
        assertEquals(mm.max(list, Comparator.naturalOrder()), Integer.valueOf(4));
    }

    @Test
    public void min() {
        assertEquals(mm.min(list, Comparator.naturalOrder()), Integer.valueOf(1));
    }
}