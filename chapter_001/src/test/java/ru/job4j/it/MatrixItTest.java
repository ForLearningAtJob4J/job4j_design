package ru.job4j.it;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.NoSuchElementException;

public class MatrixItTest {
    @Test
    public void when4El() {
        int[][] in = {
                {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals(it.next(), Integer.valueOf(1));
    }

    @Test
    public void whenFirstEmptyThenNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals(it.next(), Integer.valueOf(1));
    }

    @Test
    public void whenFirstEmptyThenHasNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertTrue(it.hasNext());
    }

    @Test
    public void whenRowHasDiffSize() {
        int[][] in = {
                {1}, {2, 3}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals(it.next(), Integer.valueOf(1));
        assertEquals(it.next(), Integer.valueOf(2));
        assertEquals(it.next(), Integer.valueOf(3));
    }

    @Test
    public void whenFewEmpty() {
        int[][] in = {
                {1}, {}, {}, {}, {2}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals(it.next(), Integer.valueOf(1));
        assertEquals(it.next(), Integer.valueOf(2));
    }

    @Test
    public void whenEmpty() {
        int[][] in = {
                {}
        };
        MatrixIt it = new MatrixIt(in);
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyThenNext() {
        int[][] in = {
                {}
        };
        MatrixIt it = new MatrixIt(in);
        it.next();
    }

    @Test
    public void whenMultiHasNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
    }

    @Test
    public void whenFirstNonEmptyThenHasNext() {
        int[][] in = {
                {1}, {},
        };
        MatrixIt it = new MatrixIt(in);
        assertTrue(it.hasNext());
    }

    @Test
    public void whenSeveralEmpty() {
        int[][] in = {
                {1}, {}, {2}, null, {}
        };
        MatrixIt it = new MatrixIt(in);
        assertEquals(it.next(), Integer.valueOf(1));
        assertEquals(it.next(), Integer.valueOf(2));
        assertFalse(it.hasNext());
    }

    @Test
    public void whenComplex() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertTrue(it.hasNext());
        assertEquals(it.next(), Integer.valueOf(1));
        assertFalse(it.hasNext());
    }
}