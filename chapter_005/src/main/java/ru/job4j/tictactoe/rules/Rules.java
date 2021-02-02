package ru.job4j.tictactoe.rules;

import ru.job4j.tictactoe.players.Player;

public interface Rules {
    boolean hasWinner();

    boolean hasGap();

    Player nextPlayer();

    Player getPlayer();
}
