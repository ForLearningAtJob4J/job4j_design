package ru.job4j.io;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Shell {
    Path curDir = Paths.get("/");
    public void cd(String path) {
        curDir = curDir.resolve(Paths.get(path)).normalize();
    }

    public String pwd() {
        return curDir.toString();
    }
}