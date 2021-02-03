package ru.job4j.tictactoe.rules;

import ru.job4j.tictactoe.players.Player;

public interface Rules<T> {
    Player<T> nextPlayer();

    Player<T> getPlayer();
}
