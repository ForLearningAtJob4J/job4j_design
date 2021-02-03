package ru.job4j.tictactoe.rules;

import org.junit.Test;
import ru.job4j.tictactoe.marks.OutputStreamMarkX;
import ru.job4j.tictactoe.players.OutputStreamPlayer;

import static org.junit.Assert.*;

public class StandardRulesForTwoOutputStreamPlayersTest {

    @Test
    public void whenNextPlayerIsPlayer2() {
        OutputStreamPlayer player1 = new OutputStreamPlayer("Gvidon", new OutputStreamMarkX());
        OutputStreamPlayer player2 = new OutputStreamPlayer("Babariha", new OutputStreamMarkX());
        StandardRulesForTwoOutputStreamPlayers rules = new StandardRulesForTwoOutputStreamPlayers(player1, player2);
        assertEquals(player2, rules.nextPlayer());
        assertEquals(player2, rules.getPlayer());
    }

    @Test
    public void whenGetPlayerIsFirstPlayerWhenGameJustStarted() {
        OutputStreamPlayer player1 = new OutputStreamPlayer("Gvidon", new OutputStreamMarkX());
        OutputStreamPlayer player2 = new OutputStreamPlayer("Babariha", new OutputStreamMarkX());
        StandardRulesForTwoOutputStreamPlayers rules = new StandardRulesForTwoOutputStreamPlayers(player1, player2);
        assertEquals(player1, rules.getPlayer());
    }
}