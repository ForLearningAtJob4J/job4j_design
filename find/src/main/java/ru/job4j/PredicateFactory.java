package ru.job4j;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.function.Predicate;

public class PredicateFactory {
    public static Predicate<Path> of(Args.Method method, String pattern) {
        Predicate<Path> predicate;
        switch (method) {
            case Regex:
                predicate = path -> {
                    PathMatcher matcher = FileSystems.getDefault().getPathMatcher("regex:" + pattern);
                    return matcher.matches(path) && !Files.isDirectory(path);
                };
                break;
            case Mask:
                predicate = path -> {
                    PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*" + pattern);
                    return matcher.matches(path) && !Files.isDirectory(path);
                };
                break;
            default:
                predicate = path -> path.getFileName().toString().equals(pattern);
        }
        return predicate;
    }
}
