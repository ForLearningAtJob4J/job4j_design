package ru.job4j.tictactoe.boards;

import ru.job4j.tictactoe.marks.Mark;
import ru.job4j.tictactoe.marks.OutputStreamMarkEmpty;
import ru.job4j.tictactoe.marks.OutputStreamMarkX;
import ru.job4j.tictactoe.positions.Position;
import ru.job4j.tictactoe.positions.Position3D;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class OutputStream3DCubicBoard implements Board<OutputStream> {
    private int size;
    private Map<Position, Mark<OutputStream>> marks = new HashMap<>();

    public OutputStream3DCubicBoard(int boardSize) {
        this.size = boardSize;
    }

    public static void main(String[] args) throws IOException {
        OutputStream3DCubicBoard board = new OutputStream3DCubicBoard(3);
        board.setMark(new Position3D(2, 2, 2), new OutputStreamMarkX());
        board.printBoard(System.out);
    }

    @Override
    public void printBoard(OutputStream screen) throws IOException {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    Mark mark = marks.get(new Position3D(i, j, k));
                    if (mark == null) {
                        new OutputStreamMarkEmpty().print(screen);
                    } else {
                        mark.print(screen);
                    }
                }
            }
            screen.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
        }
    }

    @Override
    public void setMark(Position position, Mark<OutputStream> mark) {
        marks.put(position, mark);
    }
}