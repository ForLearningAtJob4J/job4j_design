package ru.job4j.tictactoe.boards;

import ru.job4j.tictactoe.marks.Mark;
import ru.job4j.tictactoe.positions.Position;

import java.io.IOException;

public interface Board<T> {
    void printBoard(T screen) throws IOException;

    void setMark(Position position, Mark<T> mark);
}
