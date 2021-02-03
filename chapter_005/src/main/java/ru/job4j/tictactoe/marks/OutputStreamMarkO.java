package ru.job4j.tictactoe.marks;

import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamMarkO implements Mark<OutputStream> {
    @Override
    public void print(OutputStream screen) {
        try {
            screen.write("O".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isMark(String mark) {
        return "O".equals(mark);
    }
}
