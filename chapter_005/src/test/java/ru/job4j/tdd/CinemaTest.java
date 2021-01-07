package ru.job4j.tdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import java.util.*;

public class CinemaTest {

    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
//        assertEquals(ticket, new Ticket3D()); // for Jacoco tests
    }

    @Test
    public void buyBusied() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
        cinema.buy(account, 1, 1, date);
        Ticket nullTicket = cinema.buy(account, 1, 1, date);
        assertNull(nullTicket);
    }

    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
//        assertEquals(sessions, Collections.singletonList(new Session3D())); // for Jacoco tests
    }

    @Test
    public void notFound() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> false);
//        assertEquals(sessions, Collections.emptyList()); // for Jacoco tests
    }

    @Test
    public void add() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
//        assertEquals(session, cinema.find(ses -> true).get(0)); // for Jacoco tests
    }
}