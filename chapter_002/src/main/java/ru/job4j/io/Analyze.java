package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Analyze {
    public void unavailable(String sourceFileName, String targetFileName) {
        List<String> lines = new ArrayList<>();
        String line;
        String buf = "";
        try (BufferedReader source = new BufferedReader(new FileReader(sourceFileName))) {
            boolean isFailed = false;
            while ((line = source.readLine()) != null) {
                if (!isFailed && (line.startsWith("400")
                        || (line.startsWith("500")))) {
                    buf = line.substring(line.indexOf(" ") + 1) + ";";
                    isFailed = true;
                } else if (isFailed && (line.startsWith("200")
                        || (line.startsWith("300")))) {
                    buf += line.substring(line.indexOf(" ") + 1) + ";";
                    lines.add(buf);
                    isFailed = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileOutputStream(targetFileName))) {
            lines.forEach(out::println);
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