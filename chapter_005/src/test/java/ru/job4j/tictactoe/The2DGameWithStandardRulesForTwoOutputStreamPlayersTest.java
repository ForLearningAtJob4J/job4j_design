package ru.job4j.tictactoe;

import org.junit.Test;
import ru.job4j.tictactoe.boards.OutputStream2DSquareBoard;
import ru.job4j.tictactoe.marks.OutputStreamMarkO;
import ru.job4j.tictactoe.marks.OutputStreamMarkX;
import ru.job4j.tictactoe.players.OutputStreamPlayer;
import ru.job4j.tictactoe.rules.StandardRulesForTwoOutputStreamPlayers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.*;

public class The2DGameWithStandardRulesForTwoOutputStreamPlayersTest {
    private final OutputStream out = new ByteArrayOutputStream();
    private final OutputStreamPlayer player1 = new OutputStreamPlayer("Ivan", new OutputStreamMarkX());
    private final OutputStreamPlayer player2 = new OutputStreamPlayer("John", new OutputStreamMarkO());
    private final OutputStream2DSquareBoard board = new OutputStream2DSquareBoard(3);
    private final StandardRulesForTwoOutputStreamPlayers rules = new StandardRulesForTwoOutputStreamPlayers(player1, player2);

    @Test
    public void whenPlayersIvanAndJohnArePlayingInThe2DGameAndIvanWantsExitThenPrintJohnWin() throws IOException {
        final InputStream in = new MyInputStream(new String[]{"-1"});
        final The2DGameWithStandardRulesForTwoOutputStreamPlayers game =
                new The2DGameWithStandardRulesForTwoOutputStreamPlayers(in, out, board, player1, player2, rules);
        game.start();
        assertTrue(out.toString().endsWith("John win! Goodbye!"));
    }

    @Test
    public void whenPlayersIvanAndJohnArePlayingInThe2DGameAndTheyPlayThenIvanWin() throws IOException {
        final InputStream in = new MyInputStream(new String[]{
                "1 1",
                "0 0",
                "2 0",
                "1 0",
                "0 2"});

        final The2DGameWithStandardRulesForTwoOutputStreamPlayers game =
                new The2DGameWithStandardRulesForTwoOutputStreamPlayers(in, out, board, player1, player2, rules);
        game.start();
//        System.out.println(out.toString());
        assertTrue(out.toString().endsWith("Ivan win! Goodbye!"));
    }

    private class MyInputStream extends InputStream {
        private final byte[][] answers;
        private int indexBig = 0;
        private int index = 0;

        public MyInputStream(String[] theAnswers) {
            this.answers = new byte[theAnswers.length][];
            for (int i = 0; i < theAnswers.length; i++) {
                this.answers[i] = (theAnswers[i] + "\r\n").getBytes();
            }
        }

        @Override
        public int read() throws IOException {
            if (indexBig == answers.length) {
                return -1;
            }
            if (index == answers[indexBig].length) {
                indexBig++;
                index = 0;
                return -1;
            }
            int c = answers[indexBig][index++];
            out.write(c);
            return c;
        }
    }
}