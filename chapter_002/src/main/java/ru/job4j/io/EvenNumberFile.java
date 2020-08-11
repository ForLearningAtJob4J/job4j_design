package ru.job4j.io;

import java.io.FileInputStream;
import java.util.Arrays;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            Arrays.stream(text.toString().split(System.lineSeparator()))
                    .mapToInt(Integer::parseInt)
                    .forEach(value -> System.out.printf("%4d : %s%n", value, (value % 2 == 0 ? "четное" : "нечетное")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}