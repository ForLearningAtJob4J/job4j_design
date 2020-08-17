package ru.job4j.io;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Chat {
    final static String BOT_PHRASE_FILE_NAME = "bot_phrase.txt";
    final static String LOG_FILE_NAME = "chat_log.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random(System.currentTimeMillis());
        boolean active = true;
        String userInput;
        do {
            if (active) {
                String answer = getBotInput(random.nextInt());
                System.out.println(answer);
                write(answer);
            }
            userInput = scanner.nextLine();
            write(userInput);
            if ("стоп".equals(userInput)) {
                active = false;
            } else if ("продолжить".equals(userInput)) {
                active = true;
            }
        } while (!"завершить".equals(userInput));
    }

    private static void write(String line) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(LOG_FILE_NAME, true)))) {
            out.println(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getBotInput(long sentenceNum) {
        String ret = "";
        sentenceNum = Math.abs(sentenceNum) % countLines();
        try (BufferedReader in = new BufferedReader(new FileReader(BOT_PHRASE_FILE_NAME))) {
            ret = in.lines().skip(sentenceNum).findFirst().orElse("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    private static long countLines() {
        long ret = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(BOT_PHRASE_FILE_NAME))) {
            ret = in.lines().count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}