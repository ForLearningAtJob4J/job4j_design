package ru.job4j.tictactoe;

import ru.job4j.tictactoe.boards.OutputStream2DSquareBoard;
import ru.job4j.tictactoe.marks.OutputStreamMarkO;
import ru.job4j.tictactoe.marks.OutputStreamMarkX;
import ru.job4j.tictactoe.players.OutputStreamInputStreamPlayer;
import ru.job4j.tictactoe.players.Player;
import ru.job4j.tictactoe.positions.Position;
import ru.job4j.tictactoe.positions.Position2D;
import ru.job4j.tictactoe.rules.Rules;
import ru.job4j.tictactoe.rules.StandardRulesForTwoPlayers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ConsoleTwoPlayers2DGameWithStandardRules {
    static public final int BOARD_SIZE = 3;
    private final OutputStream output;
    private final InputStream input;
    private boolean wantsExit = false;

    OutputStream2DSquareBoard squareBoard2D = new OutputStream2DSquareBoard(BOARD_SIZE);
    Player<InputStream, OutputStream> player1 = new OutputStreamInputStreamPlayer("Player-X", new OutputStreamMarkX());
    Player<InputStream, OutputStream> player2 = new OutputStreamInputStreamPlayer("Player-O", new OutputStreamMarkO());
    Rules rules = new StandardRulesForTwoPlayers(player1, player2);

    public ConsoleTwoPlayers2DGameWithStandardRules(InputStream input, OutputStream output) {
        this.input = input;
        this.output = output;
    }

    public static void main(String[] args) throws IOException {
        ConsoleTwoPlayers2DGameWithStandardRules game = new ConsoleTwoPlayers2DGameWithStandardRules(System.in, System.out);
        game.start();
    }

    public void start() throws IOException {
        Player currentPlayer = rules.getPlayer();
        while (!wantsExit) {
            squareBoard2D.printBoard(output);
            if (rules.hasGap()) {
                Position position = getInput();
                if (wantsExit) {
                    output.write((rules.nextPlayer().getName() + " win!").getBytes(StandardCharsets.UTF_8));
                    break;
                }
                squareBoard2D.setMark(position, currentPlayer.getMark());
            } else {
                output.write("No one win!".getBytes(StandardCharsets.UTF_8));
                break;
            }
            if (rules.hasWinner()) {
                output.write((currentPlayer.getName() + " win!").getBytes(StandardCharsets.UTF_8));
                break;
            }
            currentPlayer = rules.nextPlayer();
        }
        output.write("Goodbye!".getBytes(StandardCharsets.UTF_8));
    }

    public Position getInput() throws IOException {
        Scanner s = new Scanner(input);
        int x = -1;
        int y = -1;
        boolean okX = false;
        boolean okY = false;
        output.write(("You need to enter two positive integers, from range [0-" + (BOARD_SIZE - 1) + "] or -1 to exit:").getBytes(StandardCharsets.UTF_8));
        do {
            if (s.hasNextInt()) {
                x = s.nextInt();
                if (x == -1) {
                    wantsExit = true;
                    return null;
                }
                okX = x >= 0 && x < BOARD_SIZE;
                if (!okX) {
                    output.write(("You need to enter for X positive number less than " + BOARD_SIZE).getBytes(StandardCharsets.UTF_8));
                }
            }
        } while (!okX);

        do {
            if (s.hasNextInt()) {
                y = s.nextInt();
                if (y == -1) {
                    wantsExit = true;
                    return null;
                }
                okY = y >= 0 && y < BOARD_SIZE;
                if (!okY) {
                    output.write(("You need to enter for Y positive number less than " + BOARD_SIZE).getBytes(StandardCharsets.UTF_8));
                }
            }
        } while (!okY);
        return new Position2D(x, y);
    }
}