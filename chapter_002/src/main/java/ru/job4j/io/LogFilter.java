package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String fileName) {
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            return in.lines()
                    .filter(s -> s.substring(s.lastIndexOf(" ") - 3, s.lastIndexOf(" ")).equals("404"))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void save(List<String> log, String fileName) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            log.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}