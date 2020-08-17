package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Chat {
    private static final String BOT_PHRASE_FILE_NAME = "bot_phrase.txt";
    private static final String LOG_FILE_NAME = "chat_log.txt";

    private static final String STOP_WORD = "стоп";
    private static final String CONTINUE_WORD = "продолжить";
    private static final String QUIT_WORD = "завершить";

    static List<String> botPhrases = new ArrayList<>();
    static StringBuffer logBuffer = new StringBuffer();
    private static final int NUMBER_OF_SYMBOLS_TO_FLUSH = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random(System.currentTimeMillis());
        boolean active = true;
        String userInput;
        init();
        do {
            if (active) {
                String answer = botPhrases.get(Math.abs(random.nextInt()) % botPhrases.size());
                System.out.println(answer);
                write(answer);
            }
            userInput = scanner.nextLine();
            write(userInput);
            if (STOP_WORD.equals(userInput)) {
                active = false;
            } else if (CONTINUE_WORD.equals(userInput)) {
                active = true;
            }
        } while (!QUIT_WORD.equals(userInput));

        writeLogToFile();
    }

    private static void write(String line) {
        logBuffer.append(line).append(System.lineSeparator());

        if (logBuffer.length() > NUMBER_OF_SYMBOLS_TO_FLUSH) {
            writeLogToFile();
        }
    }

    private static void writeLogToFile() {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(LOG_FILE_NAME, true)))) {
            out.write(logBuffer.toString());
            logBuffer = new StringBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void init() {
        try (BufferedReader in = new BufferedReader(new FileReader(BOT_PHRASE_FILE_NAME))) {
            in.lines().forEach(botPhrases::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}