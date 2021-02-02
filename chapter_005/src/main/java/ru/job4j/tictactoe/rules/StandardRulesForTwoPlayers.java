package ru.job4j.tictactoe.rules;

import ru.job4j.tictactoe.players.Player;

public class StandardRulesForTwoPlayers implements Rules {
    Player currentPlayer;
    Player player1;
    Player player2;

    public StandardRulesForTwoPlayers(Player player1, Player player2) {
        currentPlayer = player1;
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public boolean hasWinner() {
        return false;
    }

    @Override
    public boolean hasGap() {
        return true;
    }

    @Override
    public Player nextPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
        return currentPlayer;
    }

    @Override
    public Player getPlayer() {
        return currentPlayer;
    }
}
