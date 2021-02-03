package ru.job4j.tictactoe.boards;

import org.junit.Test;
import ru.job4j.tictactoe.marks.OutputStreamMarkO;
import ru.job4j.tictactoe.marks.OutputStreamMarkX;
import ru.job4j.tictactoe.positions.Position2D;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

public class OutputStream2DSquareBoardTest {

    @Test
    public void whenSetMarkIsTrue() {
        OutputStream2DSquareBoard board = new OutputStream2DSquareBoard(3);
        assertTrue(board.setMark(new Position2D(1, 2), new OutputStreamMarkX()));
    }

    @Test
    public void whenSetMarkIsFalse() {
        OutputStream2DSquareBoard board = new OutputStream2DSquareBoard(3);
        board.setMark(new Position2D(1, 2), new OutputStreamMarkX());
        assertFalse(board.setMark(new Position2D(1, 2), new OutputStreamMarkX()));
    }

    @Test
    public void whenHasWinnerIsTrue() {
        OutputStream2DSquareBoard board = new OutputStream2DSquareBoard(3);
        board.setMark(new Position2D(1, 0), new OutputStreamMarkX());
        board.setMark(new Position2D(1, 1), new OutputStreamMarkX());
        board.setMark(new Position2D(1, 2), new OutputStreamMarkX());
        assertTrue(board.hasWinner());
    }

    @Test
    public void whenHasWinnerIsFalseAndHasGapIsFalse() {
        OutputStream2DSquareBoard board = new OutputStream2DSquareBoard(3);
        board.setMark(new Position2D(0, 0), new OutputStreamMarkX());
        board.setMark(new Position2D(0, 1), new OutputStreamMarkX());
        board.setMark(new Position2D(0, 2), new OutputStreamMarkO());

        board.setMark(new Position2D(1, 0), new OutputStreamMarkO());
        board.setMark(new Position2D(1, 1), new OutputStreamMarkO());
        board.setMark(new Position2D(1, 2), new OutputStreamMarkX());

        board.setMark(new Position2D(2, 0), new OutputStreamMarkX());
        board.setMark(new Position2D(2, 1), new OutputStreamMarkO());
        board.setMark(new Position2D(2, 2), new OutputStreamMarkO());
        assertFalse(board.hasGap());
        assertFalse(board.hasWinner());
    }

    @Test
    public void whenHasGapIsTrueAndHasWinnerIsTrue() {
        OutputStream2DSquareBoard board = new OutputStream2DSquareBoard(3);
        board.setMark(new Position2D(0, 0), new OutputStreamMarkX());
        board.setMark(new Position2D(0, 1), new OutputStreamMarkX());
        board.setMark(new Position2D(0, 2), new OutputStreamMarkX());

        board.setMark(new Position2D(1, 0), new OutputStreamMarkO());
        board.setMark(new Position2D(1, 1), new OutputStreamMarkO());

        assertTrue(board.hasGap());
        assertTrue(board.hasWinner());
    }

    @Test
    public void whenHasGapIsTrueAndHasWinnerIsFalse() {
        OutputStream2DSquareBoard board = new OutputStream2DSquareBoard(3);
        board.setMark(new Position2D(0, 0), new OutputStreamMarkX());
        board.setMark(new Position2D(0, 1), new OutputStreamMarkX());

        board.setMark(new Position2D(1, 0), new OutputStreamMarkO());
        board.setMark(new Position2D(1, 1), new OutputStreamMarkO());

        assertTrue(board.hasGap());
        assertFalse(board.hasWinner());
    }

    @Test
    public void whenHasGapIsTrue() {
        OutputStream2DSquareBoard board = new OutputStream2DSquareBoard(3);
        board.setMark(new Position2D(1, 0), new OutputStreamMarkX());
        board.setMark(new Position2D(1, 1), new OutputStreamMarkX());
        board.setMark(new Position2D(1, 2), new OutputStreamMarkX());
        assertTrue(board.hasGap());
    }

    @Test
    public void printBoardWithXOn2x2() {
        var out = new ByteArrayOutputStream();
        OutputStream2DSquareBoard board = new OutputStream2DSquareBoard(3);
        board.setMark(new Position2D(2, 2), new OutputStreamMarkX());
        board.printBoard(out);
        assertEquals("---" + System.lineSeparator()
                + "---" + System.lineSeparator()
                + "--X" + System.lineSeparator(), out.toString());
    }
}