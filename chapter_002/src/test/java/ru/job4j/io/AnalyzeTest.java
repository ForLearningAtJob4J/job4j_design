package ru.job4j.io;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class AnalyzeTest {
    @Test
    public void whenServerWasUnavailable() {
        Analyze analizy = new Analyze();
        analizy.unavailable("../data/status.txt", "../data/res.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader("../data/res.txt"))) {
            assertThat(reader.lines().collect(Collectors.toList()),
                    is(new ArrayList<String>() { {
                        add("10:57:01;10:59:01;");
                        add("11:01:02;11:02:02;");
                    } }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}