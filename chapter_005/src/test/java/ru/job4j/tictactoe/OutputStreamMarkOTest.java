package ru.job4j.tictactoe;

import org.junit.Test;
import ru.job4j.tictactoe.marks.OutputStreamMarkO;

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