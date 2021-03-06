package ru.job4j.tictactoe;

import ru.job4j.tictactoe.boards.OutputStream2DSquareBoard;
import ru.job4j.tictactoe.marks.OutputStreamMarkO;
import ru.job4j.tictactoe.marks.OutputStreamMarkX;
import ru.job4j.tictactoe.players.OutputStreamPlayer;
import ru.job4j.tictactoe.positions.Position;
import ru.job4j.tictactoe.positions.Position2D;
import ru.job4j.tictactoe.rules.StandardRulesForTwoOutputStreamPlayers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class The2DGameWithStandardRulesForTwoOutputStreamPlayers {
    static public final int BOARD_SIZE = 3;
    private final OutputStream output;
    private final InputStream input;
    private boolean wantsExit = false;

    OutputStream2DSquareBoard squareBoard2D;
    OutputStreamPlayer player1;
    OutputStreamPlayer player2;
    StandardRulesForTwoOutputStreamPlayers rules;

    public The2DGameWithStandardRulesForTwoOutputStreamPlayers(InputStream input,
                                                               OutputStream output,
                                                               OutputStream2DSquareBoard squareBoard2D,
                                                               OutputStreamPlayer player1,
                                                               OutputStreamPlayer player2,
                                                               StandardRulesForTwoOutputStreamPlayers rules) {
        this.input = input;
        this.output = output;
        this.squareBoard2D = squareBoard2D;
        this.player1 = player1;
        this.player2 = player2;
        this.rules = rules;
    }

    public static void main(String[] args) throws IOException {
        OutputStreamPlayer player1 = new OutputStreamPlayer("Ivan", new OutputStreamMarkX());
        OutputStreamPlayer player2 = new OutputStreamPlayer("John", new OutputStreamMarkO());
        OutputStream2DSquareBoard board = new OutputStream2DSquareBoard(BOARD_SIZE);
        StandardRulesForTwoOutputStreamPlayers rules = new StandardRulesForTwoOutputStreamPlayers(player1, player2);

        The2DGameWithStandardRulesForTwoOutputStreamPlayers game =
                new The2DGameWithStandardRulesForTwoOutputStreamPlayers(
                        System.in,
                        System.out,
                        board,
                        player1,
                        player2,
                        rules);
        game.start();
    }

    public void start() throws IOException {
        OutputStreamPlayer currentPlayer = rules.getPlayer();
        while (!wantsExit) {
            squareBoard2D.printBoard(output);
            if (squareBoard2D.hasGap()) {
                Position position = getInput();
                if (wantsExit) {
                    output.write((rules.nextPlayer().getName() + " win! ").getBytes(StandardCharsets.UTF_8));
                    break;
                }
                if (!squareBoard2D.setMark(position, currentPlayer.getMark())) {
                    output.write(("This cell is busy! Give another  coordinates!" + System.lineSeparator()).getBytes(StandardCharsets.UTF_8));
                    continue;
                }
            } else {
                output.write("Nobody win! ".getBytes(StandardCharsets.UTF_8));
                break;
            }
            if (squareBoard2D.hasWinner()) {
                output.write((currentPlayer.getName() + " win! ").getBytes(StandardCharsets.UTF_8));
                break;
            }
            currentPlayer = rules.nextPlayer();
        }
        output.write("Goodbye!".getBytes(StandardCharsets.UTF_8));
    }

    private Position getInput() throws IOException {
        Scanner s = new Scanner(input);
        String name = rules.getPlayer().getName();
        int x = -1;
        int y = -1;
        boolean okX = false;
        boolean okY = false;
        output.write((name + ", you need to enter two positive integers, from range [0-" + (BOARD_SIZE - 1) + "] or -1 to exit:").getBytes(StandardCharsets.UTF_8));
        do {
            if (s.hasNextInt()) {
                x = s.nextInt();
                if (x == -1) {
                    wantsExit = true;
                    return null;
                }
                okX = x >= 0 && x < BOARD_SIZE;
                if (!okX) {
                    output.write((name + ", you need to enter for X positive number less than " + BOARD_SIZE + ": ").getBytes(StandardCharsets.UTF_8));
                }
            } else {
                s.next();
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
                    output.write((name + ", you need to enter for Y positive number less than " + BOARD_SIZE + ": ").getBytes(StandardCharsets.UTF_8));
                }
            } else {
                s.next();
            }
        } while (!okY);
        return new Position2D(x, y);
    }
}