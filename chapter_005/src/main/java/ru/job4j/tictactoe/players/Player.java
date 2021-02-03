package ru.job4j.tictactoe.players;

import ru.job4j.tictactoe.marks.Mark;
import ru.job4j.tictactoe.positions.Position;

import java.io.IOException;
import java.io.OutputStream;

public interface Player<T> {
    String getName();
    Mark<T> getMark();
}
