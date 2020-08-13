package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class Analyze {
    public void unavailable(String sourceFileName, String targetFileName) {
        try (BufferedReader source = new BufferedReader(new FileReader(sourceFileName));
             PrintWriter out = new PrintWriter(new FileOutputStream(targetFileName))) {
            List<String> lines = source.lines().collect(Collectors.toList());
            boolean isFailed = false;
            for (String s: lines) {
                if (!isFailed && (s.startsWith("400")
                        || (s.startsWith("500")))) {
                    out.printf("%s;", s.substring(s.indexOf(" ") + 1));
                    isFailed = true;
                } else if (isFailed && (s.startsWith("200")
                        || (s.startsWith("300")))) {
                    out.printf("%s;%n", s.substring(s.indexOf(" ") + 1));
                    isFailed = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}