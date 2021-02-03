package ru.job4j.tictactoe.boards;

import ru.job4j.tictactoe.marks.Mark;
import ru.job4j.tictactoe.marks.OutputStreamMarkEmpty;
import ru.job4j.tictactoe.marks.OutputStreamMarkX;
import ru.job4j.tictactoe.positions.Position;
import ru.job4j.tictactoe.positions.Position3D;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Predicate;

public class OutputStream3DCubicBoard implements Board<OutputStream> {
    private final int size;
    private final int dimensionCount = 3;

    private final Map<Position, Mark<OutputStream>> marks = new HashMap<>();

    public OutputStream3DCubicBoard(int boardSize) {
        this.size = boardSize;
    }

    public static void main(String[] args) {
        OutputStream3DCubicBoard board = new OutputStream3DCubicBoard(3);
        board.setMark(new Position3D(2, 2, 2), new OutputStreamMarkX());
        board.printBoard(System.out);
    }

    @Override
    public void printBoard(OutputStream screen) {
        try {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    for (int k = 0; k < size; k++) {
                        Mark<OutputStream> mark = marks.get(new Position3D(i, j, k));
                        if (mark == null) {
                            new OutputStreamMarkEmpty().print(screen);
                        } else {
                            mark.print(screen);
                        }
                    }
                    screen.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
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

    private boolean fillBy(Predicate<Mark<OutputStream>> predicate, int startX, int startY, int startZ, int deltaX, int deltaY, int deltaZ) {
        boolean result = true;
        for (int index = 0; index != size; index++) {
            Mark<OutputStream> mark = marks.get(new Position3D(startX, startY, startZ));
            if (mark == null) {
                result = false;
                break;
            }
            startX += deltaX;
            startY += deltaY;
            startZ += deltaZ;
            if (!predicate.test(mark)) {
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean isWinnerMark(String markSign) {
        return fillBy(mark -> mark.isMark(markSign), 0, 0, 0, 0, 0, 1)
                || fillBy(mark -> mark.isMark(markSign), 1, 0, 0, 0, 0, 1)
                || fillBy(mark -> mark.isMark(markSign), 2, 0, 0, 0, 0, 1)
                || fillBy(mark -> mark.isMark(markSign), 0, 1, 0, 0, 0, 1)
                || fillBy(mark -> mark.isMark(markSign), 1, 1, 0, 0, 0, 1)
                || fillBy(mark -> mark.isMark(markSign), 2, 1, 0, 0, 0, 1)
                || fillBy(mark -> mark.isMark(markSign), 0, 2, 0, 0, 0, 1)
                || fillBy(mark -> mark.isMark(markSign), 1, 2, 0, 0, 0, 1)
                || fillBy(mark -> mark.isMark(markSign), 2, 2, 0, 0, 0, 1)

                || fillBy(mark -> mark.isMark(markSign), 0, 0, 0, 1, 1, 0)
                || fillBy(mark -> mark.isMark(markSign), 0, 0, 1, 1, 1, 0)
                || fillBy(mark -> mark.isMark(markSign), 0, 0, 2, 1, 1, 0)
                || fillBy(mark -> mark.isMark(markSign), 2, 0, 0, -1, 1, 0)
                || fillBy(mark -> mark.isMark(markSign), 2, 0, 1, -1, 1, 0)
                || fillBy(mark -> mark.isMark(markSign), 2, 0, 2, -1, 1, 0)

                || fillBy(mark -> mark.isMark(markSign), 0, 0, 0, 0, 1, 0)
                || fillBy(mark -> mark.isMark(markSign), 1, 0, 0, 0, 1, 0)
                || fillBy(mark -> mark.isMark(markSign), 2, 0, 0, 0, 1, 0)
                || fillBy(mark -> mark.isMark(markSign), 0, 0, 1, 0, 1, 0)
                || fillBy(mark -> mark.isMark(markSign), 1, 0, 1, 0, 1, 0)
                || fillBy(mark -> mark.isMark(markSign), 2, 0, 1, 0, 1, 0)
                || fillBy(mark -> mark.isMark(markSign), 0, 0, 2, 0, 1, 0)
                || fillBy(mark -> mark.isMark(markSign), 1, 0, 2, 0, 1, 0)
                || fillBy(mark -> mark.isMark(markSign), 2, 0, 2, 0, 1, 0)

                || fillBy(mark -> mark.isMark(markSign), 0, 0, 0, 1, 0, 1)
                || fillBy(mark -> mark.isMark(markSign), 0, 1, 0, 1, 0, 1)
                || fillBy(mark -> mark.isMark(markSign), 0, 2, 0, 1, 0, 1)
                || fillBy(mark -> mark.isMark(markSign), 2, 0, 0, -1, 0, 1)
                || fillBy(mark -> mark.isMark(markSign), 2, 1, 0, -1, 0, 1)
                || fillBy(mark -> mark.isMark(markSign), 2, 2, 0, -1, 0, 1)

                || fillBy(mark -> mark.isMark(markSign), 0, 0, 0, 1, 0, 0)
                || fillBy(mark -> mark.isMark(markSign), 0, 1, 0, 1, 0, 0)
                || fillBy(mark -> mark.isMark(markSign), 0, 2, 0, 1, 0, 0)
                || fillBy(mark -> mark.isMark(markSign), 0, 0, 1, 1, 0, 0)
                || fillBy(mark -> mark.isMark(markSign), 0, 1, 1, 1, 0, 0)
                || fillBy(mark -> mark.isMark(markSign), 0, 2, 1, 1, 0, 0)
                || fillBy(mark -> mark.isMark(markSign), 0, 0, 2, 1, 0, 0)
                || fillBy(mark -> mark.isMark(markSign), 0, 1, 2, 1, 0, 0)
                || fillBy(mark -> mark.isMark(markSign), 0, 2, 2, 1, 0, 0)

                || fillBy(mark -> mark.isMark(markSign), 0, 0, 0, 1, 1, 0)
                || fillBy(mark -> mark.isMark(markSign), 1, 0, 0, 1, 1, 0)
                || fillBy(mark -> mark.isMark(markSign), 2, 0, 0, 1, 1, 0)
                || fillBy(mark -> mark.isMark(markSign), 0, 2, 0, 1, -1, 0)
                || fillBy(mark -> mark.isMark(markSign), 1, 2, 0, 1, -1, 0)
                || fillBy(mark -> mark.isMark(markSign), 2, 2, 0, 1, -1, 0)

                || fillBy(mark -> mark.isMark(markSign), 0, 0, 0, 1, 1, 1)
                || fillBy(mark -> mark.isMark(markSign), 2, 0, 0, -1, 1, 1)
                || fillBy(mark -> mark.isMark(markSign), 0, 2, 0, 1, -1, 1)
                || fillBy(mark -> mark.isMark(markSign), 0, 0, 2, 1, 1, -1);
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
}