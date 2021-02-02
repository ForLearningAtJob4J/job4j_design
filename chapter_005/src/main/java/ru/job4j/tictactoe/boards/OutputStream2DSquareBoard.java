package ru.job4j.tictactoe.boards;

import ru.job4j.tictactoe.marks.Mark;
import ru.job4j.tictactoe.marks.OutputStreamMarkEmpty;
import ru.job4j.tictactoe.marks.OutputStreamMarkX;
import ru.job4j.tictactoe.positions.Position;
import ru.job4j.tictactoe.positions.Position2D;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class OutputStream2DSquareBoard implements Board<OutputStream> {
    private int size;
    private Map<Position, Mark<OutputStream>> marks = new HashMap<>();

    public OutputStream2DSquareBoard(int boardSize) {
        this.size = boardSize;
    }

    @Override
    public void printBoard(OutputStream screen) throws IOException {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Mark mark = marks.get(new Position2D(i, j));
                if (mark == null) {
                    new OutputStreamMarkEmpty().print(screen);
                } else {
                    mark.print(screen);
                }
            }
            screen.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
        }
    }

    @Override
    public void setMark(Position position, Mark<OutputStream> mark) {
        marks.put(position, mark);
    }

    public static void main(String[] args) throws IOException {
        OutputStream2DSquareBoard board = new OutputStream2DSquareBoard(3);
        board.setMark(new Position2D(2, 2), new OutputStreamMarkX());
        board.printBoard(System.out);
    }
}
