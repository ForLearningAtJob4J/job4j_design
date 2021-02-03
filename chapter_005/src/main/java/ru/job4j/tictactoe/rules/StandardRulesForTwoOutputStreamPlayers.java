package ru.job4j.tictactoe.rules;

import ru.job4j.tictactoe.players.OutputStreamPlayer;

import java.io.OutputStream;

public class StandardRulesForTwoOutputStreamPlayers implements Rules<OutputStream> {
    private OutputStreamPlayer currentPlayer;
    private OutputStreamPlayer player1;
    private OutputStreamPlayer player2;

    public StandardRulesForTwoOutputStreamPlayers(OutputStreamPlayer player1, OutputStreamPlayer player2) {
        currentPlayer = player1;
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public OutputStreamPlayer nextPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
        return currentPlayer;
    }

    @Override
    public OutputStreamPlayer getPlayer() {
        return currentPlayer;
    }
}
