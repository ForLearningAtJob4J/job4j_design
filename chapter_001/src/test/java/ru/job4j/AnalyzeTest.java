package ru.job4j;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.Analyze.User;

public class AnalyzeTest {

    @Test
    public void diff() {
        List<User> prev = new ArrayList<>();
        prev.add(new User(1, "AAA"));
        prev.add(new User(2, "AAB"));
        prev.add(new User(3, "AAC"));
        prev.add(new User(4, "DDD"));

        List<User> curr = new ArrayList<>();
        curr.add(new User(3, "AAC"));
        curr.add(new User(4, "AAA"));
        curr.add(new User(5, "AAA"));

        Analyze analyze = new Analyze();
        Analyze.Info info = analyze.diff(prev, curr);
        assertEquals(info.deleted, 2);
        assertEquals(info.changed, 1);
        assertEquals(info.added, 1);
    }
}