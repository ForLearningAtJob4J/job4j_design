package ru.job4j.tictactoe;

import ru.job4j.tictactoe.boards.OutputStream3DCubicBoard;
import ru.job4j.tictactoe.marks.OutputStreamMarkO;
import ru.job4j.tictactoe.marks.OutputStreamMarkX;
import ru.job4j.tictactoe.players.OutputStreamPlayer;
import ru.job4j.tictactoe.positions.Position;
import ru.job4j.tictactoe.positions.Position3D;
import ru.job4j.tictactoe.rules.StandardRulesForTwoOutputStreamPlayers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class The3DGameWithStandardRulesForTwoOutputStreamPlayers {
    static public final int BOARD_SIZE = 3;
    private final OutputStream output;
    private final InputStream input;
    private boolean wantsExit = false;

    OutputStream3DCubicBoard cubicBoard3D;
    OutputStreamPlayer player1;
    OutputStreamPlayer player2;
    StandardRulesForTwoOutputStreamPlayers rules;

    public The3DGameWithStandardRulesForTwoOutputStreamPlayers(InputStream input,
                                                               OutputStream output,
                                                               OutputStream3DCubicBoard cubicBoard3D,
                                                               OutputStreamPlayer player1,
                                                               OutputStreamPlayer player2,
                                                               StandardRulesForTwoOutputStreamPlayers rules) {
        this.input = input;
        this.output = output;
        this.cubicBoard3D = cubicBoard3D;
        this.player1 = player1;
        this.player2 = player2;
        this.rules = rules;
    }

    public static void main(String[] args) throws IOException {
        OutputStreamPlayer player1 = new OutputStreamPlayer("Dora", new OutputStreamMarkX());
        OutputStreamPlayer player2 = new OutputStreamPlayer("Kate", new OutputStreamMarkO());
        OutputStream3DCubicBoard board = new OutputStream3DCubicBoard(BOARD_SIZE);
        StandardRulesForTwoOutputStreamPlayers rules = new StandardRulesForTwoOutputStreamPlayers(player1, player2);

        The3DGameWithStandardRulesForTwoOutputStreamPlayers game =
                new The3DGameWithStandardRulesForTwoOutputStreamPlayers(
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
            cubicBoard3D.printBoard(output);
            if (cubicBoard3D.hasGap()) {
                Position position = getInput();
                if (wantsExit) {
                    output.write((rules.nextPlayer().getName() + " win! ").getBytes(StandardCharsets.UTF_8));
                    break;
                }
                if (!cubicBoard3D.setMark(position, currentPlayer.getMark())) {
                    output.write(("This cell is busy! Give another coordinates!" + System.lineSeparator()).getBytes(StandardCharsets.UTF_8));
                    continue;
                }
            } else {
                output.write("Nobody win! ".getBytes(StandardCharsets.UTF_8));
                break;
            }
            if (cubicBoard3D.hasWinner()) {
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
        int z = -1;
        boolean okX = false;
        boolean okY = false;
        boolean okZ = false;
        output.write((name + ", you need to enter three positive integers, from range [0-" + (BOARD_SIZE - 1) + "] or -1 to exit:").getBytes(StandardCharsets.UTF_8));
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

        do {
            if (s.hasNextInt()) {
                z = s.nextInt();
                if (z == -1) {
                    wantsExit = true;
                    return null;
                }
                okZ = z >= 0 && z < BOARD_SIZE;
                if (!okZ) {
                    output.write((name + ", you need to enter for Z positive number less than " + BOARD_SIZE + ": ").getBytes(StandardCharsets.UTF_8));
                }
            } else {
                s.next();
            }
        } while (!okZ);

        return new Position3D(x, y, z);
    }
}