package ru.job4j.tictactoe.boards;

import ru.job4j.tictactoe.marks.Mark;
import ru.job4j.tictactoe.marks.OutputStreamMarkEmpty;
import ru.job4j.tictactoe.marks.OutputStreamMarkX;
import ru.job4j.tictactoe.positions.Position;
import ru.job4j.tictactoe.positions.Position2D;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Predicate;

public class OutputStream2DSquareBoard implements Board<OutputStream> {
    private final int size;
    private final int dimensionCount = 2;

    private final Map<Position, Mark<OutputStream>> marks = new HashMap<>();

    public OutputStream2DSquareBoard(int boardSize) {
        this.size = boardSize;
    }

    public static void main(String[] args) {
        OutputStream2DSquareBoard board = new OutputStream2DSquareBoard(3);
        board.setMark(new Position2D(2, 2), new OutputStreamMarkX());
        board.printBoard(System.out);
    }

    private boolean fillBy(Predicate<Mark<OutputStream>> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != size; index++) {
            Mark<OutputStream> mark = marks.get(new Position2D(startX, startY));
            if (mark == null) {
                result = false;
                break;
            }
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(mark)) {
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean isWinnerMark(String markSign) {
        int dim = dimensionCount;
        int[] deltas;
        while (dim > 0) {
            dim--;
            deltas = new int[2];
            deltas[dim] = 1;
            for (int i = 0; i < size; i++) {
                if (fillBy(mark -> mark.isMark(markSign), i * deltas[1], i * deltas[0], deltas[0], deltas[1])) {
                    return true;
                }
            }
        }
        return fillBy(mark -> mark.isMark(markSign), 0, 0, 1, 1)
                || fillBy(mark -> mark.isMark(markSign), size - 1, 0, -1, 1);
    }

    @Override
    public boolean hasWinner() {
        return new HashSet<>(marks.values()).stream().anyMatch(
                outputStreamMark -> {
                    var out = new ByteArrayOutputStream();
                    outputStreamMark.print(out);
                    return isWinnerMark(out.toString());
                });
    }

    @Override
    public boolean hasGap() {
        return marks.size() < Math.pow(size, dimensionCount);
    }

    @Override
    public void printBoard(OutputStream screen) {
        try {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    Mark<OutputStream> mark = marks.get(new Position2D(i, j));
                    if (mark == null) {
                        new OutputStreamMarkEmpty().print(screen);
                    } else {
                        mark.print(screen);
                    }
                }
                screen.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean setMark(Position position, Mark<OutputStream> mark) {
        if (marks.get(position) == null) {
            marks.put(position, mark);
            return true;
        } else {
            return false;
        }
    }
}