package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String fileName) {
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            List<String> lines = new ArrayList<>();
            in.lines().forEach(lines::add);
            return lines.stream()
                    .filter(s -> s.substring(s.lastIndexOf(" ") - 3, s.lastIndexOf(" ")).equals("404"))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}