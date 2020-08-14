package ru.job4j.io;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class AnalyzeTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenServerWasUnavailable()  throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");

        StringBuilder sb = new StringBuilder()
                .append("200 10:56:01").append(System.lineSeparator())
                .append("500 10:57:01").append(System.lineSeparator())
                .append("400 10:58:01").append(System.lineSeparator())
                .append("200 10:59:01").append(System.lineSeparator())
                .append("500 11:01:02").append(System.lineSeparator())
                .append("200 11:02:02").append(System.lineSeparator());

        Analyze analyze = new Analyze();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println(sb);
        }
        analyze.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try (BufferedReader reader = new BufferedReader(new FileReader(target.getAbsolutePath()))) {
            assertThat(reader.lines().collect(Collectors.toList()),
                    is(new ArrayList<String>() { {
                        add("10:57:01;10:59:01;");
                        add("11:01:02;11:02:02;");
                    } }));
        }
    }
}