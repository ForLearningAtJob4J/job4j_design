package ru.job4j.tictactoe.marks;

import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class OutputStreamMarkOTest {

    @Test
    public void print() {
        var out = new ByteArrayOutputStream();
        new OutputStreamMarkO().print(out);
        assertEquals("O", out.toString());
    }
}