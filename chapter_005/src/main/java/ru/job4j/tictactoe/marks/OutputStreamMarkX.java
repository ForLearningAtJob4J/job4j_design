package ru.job4j.tictactoe.marks;

import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamMarkX implements Mark<OutputStream> {
    @Override
    public boolean isMark(String mark) {
        return "X".equals(mark);
    }

    @Override
    public void print(OutputStream screen) {
        try {
            screen.write("X".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
