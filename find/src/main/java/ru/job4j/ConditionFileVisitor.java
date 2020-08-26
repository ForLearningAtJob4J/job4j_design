package ru.job4j;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class ConditionFileVisitor extends SimpleFileVisitor<Path> {
    List<Path> paths;
    Predicate<Path> condition;

    public void setCondition(Predicate<Path> condition) {
        this.condition = condition;
    }

    public ConditionFileVisitor() {
        paths = new ArrayList<>();
        condition = x -> true;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (condition.test(file)) {
            paths.add(file);
        }
        return CONTINUE;
    }

    public List<Path> getPaths() {
        return paths;
    }
}
