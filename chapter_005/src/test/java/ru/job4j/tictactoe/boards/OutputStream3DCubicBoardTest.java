package ru.job4j.tictactoe.boards;

import org.junit.Test;
import ru.job4j.tictactoe.marks.OutputStreamMarkO;
import ru.job4j.tictactoe.marks.OutputStreamMarkX;
import ru.job4j.tictactoe.positions.Position3D;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

public class OutputStream3DCubicBoardTest {

    @Test
    public void printBoardWithXOn2x2x2AndOOn1x1x1() {
        final String LS = System.lineSeparator();
        var out = new ByteArrayOutputStream();
        OutputStream3DCubicBoard board = new OutputStream3DCubicBoard(3);
        board.setMark(new Position3D(2, 2, 2), new OutputStreamMarkX());
        board.setMark(new Position3D(1, 1, 1), new OutputStreamMarkO());
        board.printBoard(out);
        assertEquals("---" + LS
                + "---" + LS
                + "---" + LS
                + LS
                + "---" + LS
                + "-O-" + LS
                + "---" + LS
                + LS
                + "---" + LS
                + "---" + LS
                + "--X" + LS + LS, out.toString());
    }
}