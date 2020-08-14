package ru.job4j.io;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        Arrays.stream(Objects.requireNonNull(file.listFiles()))
                .filter(File::isFile)
                .map(subFile -> String.format("%s; %,d bytes", subFile.getName(), subFile.getTotalSpace()))
                .forEach(System.out::println);
    }
}