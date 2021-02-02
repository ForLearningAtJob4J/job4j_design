package ru.job4j.tictactoe.marks;

import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamMarkEmpty implements Mark<OutputStream> {

    @Override
    public void print(OutputStream screen) {
        try {
            screen.write("-".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
