package ru.job4j.tictactoe.marks;

import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class OutputStreamMarkXTest {

    @Test
    public void print() {
        var out = new ByteArrayOutputStream();
        new OutputStreamMarkX().print(out);
        assertEquals("X", out.toString());
    }
}