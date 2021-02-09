package ru.job4j.tictactoe;

import org.junit.Test;
import ru.job4j.tictactoe.boards.OutputStream2DSquareBoard;
import ru.job4j.tictactoe.marks.OutputStreamMarkO;
import ru.job4j.tictactoe.marks.OutputStreamMarkX;
import ru.job4j.tictactoe.players.OutputStreamPlayer;
import ru.job4j.tictactoe.rules.StandardRulesForTwoOutputStreamPlayers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class The2DGameWithStandardRulesForTwoOutputStreamPlayersTest {

    @Test
    public void whenPlayersIvanAndJohnArePlayingInThe2DGameAndIvanWantsExitThenPrintJohnWin() throws IOException {
        var out = new ByteArrayOutputStream();
        var in = new ByteArrayInputStream("-1\n".getBytes());

        OutputStreamPlayer player1 = new OutputStreamPlayer("Ivan", new OutputStreamMarkX());
        OutputStreamPlayer player2 = new OutputStreamPlayer("John", new OutputStreamMarkO());
        OutputStream2DSquareBoard board = new OutputStream2DSquareBoard(3);
        StandardRulesForTwoOutputStreamPlayers rules = new StandardRulesForTwoOutputStreamPlayers(player1, player2);

        The2DGameWithStandardRulesForTwoOutputStreamPlayers game =
                new The2DGameWithStandardRulesForTwoOutputStreamPlayers(
                        in,
                        out,
                        board,
                        player1,
                        player2,
                        rules);
        game.start();
        assertTrue(out.toString().endsWith("John win! Goodbye!"));
    }
}