package ru.job4j.tictactoe.positions;

import org.junit.Test;

import static org.junit.Assert.*;

public class Position3DTest {

    @Test
    public void whenEqualsIsTrue() {
        Position position1 = new Position3D(1, 1, 1);
        Position position2 = new Position3D(1, 1, 1);
        assertEquals(position1, position2);
    }

    @Test
    public void whenEqualsIsFalse() {
        Position position1 = new Position3D(1, 1, 1);
        Position position2 = new Position3D(0, 1, 1);
        assertNotEquals(position1, position2);
    }
}