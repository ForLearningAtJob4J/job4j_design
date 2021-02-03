package ru.job4j.tictactoe.players;

import org.junit.Test;
import ru.job4j.tictactoe.marks.OutputStreamMarkO;

import static org.junit.Assert.*;

public class OutputStreamPlayerTest {

    @Test
    public void whenGetName() {
        OutputStreamPlayer player = new OutputStreamPlayer("Player", new OutputStreamMarkO());
        assertEquals("Player", player.getName());
    }

    @Test
    public void whenGetMark() {
        OutputStreamPlayer player = new OutputStreamPlayer("Player", new OutputStreamMarkO());
        assertTrue(player.getMark().isMark("O"));
    }
}