package ru.job4j.io;

import java.io.*;

public class ResultFile {
    static final int SIZE = 20;
    public static void main(String[] args) {
        int checker = SIZE * SIZE;
        String formatString = "%" + (getDigitCapacity(checker) + 1) + "d";
        try (PrintStream ps = new PrintStream(new FileOutputStream("result.txt"))) {
            for (int i = 1; i <= SIZE; i++) {
                for (int j = 1; j <= SIZE; j++) {
                    ps.printf(formatString, i * j);
                }
                ps.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int getDigitCapacity(int number) {
        number = Math.abs(number);
        int ret = 1;
        while (number / 10 >= 1) {
            number = number / 10;
            ret++;
        }
        return ret;
    }
}