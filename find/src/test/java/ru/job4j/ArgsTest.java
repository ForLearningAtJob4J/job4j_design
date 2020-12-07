package ru.job4j;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ArgsTest {
    @Test
    public void whenNoArgs() {
        Args args = new Args(new String[] {""});
        assertEquals(args.getDirectory(), Paths.get("."));
        assertEquals(args.getPattern(), "*.*");
        assertEquals(args.getMethod(), Args.Method.Mask);
    }

//    @Test(expected = IllegalArgumentException.class)
//    public void when() {
//    }
}