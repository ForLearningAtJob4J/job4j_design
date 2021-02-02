package ru.job4j.tictactoe.players;

import ru.job4j.tictactoe.marks.Mark;
import ru.job4j.tictactoe.positions.Position;

import java.io.IOException;

public interface Player<T, U> {
    String getName();
    Mark getMark();
}
