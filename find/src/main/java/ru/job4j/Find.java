package ru.job4j;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Find {
    static Args args;
    public static void main(String[] a) throws IOException {
        try {
            args = new Args(a);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("Usage: java -jar find.jar -d c:/ -n *.txt -m -o log.txt");
        }

        Files.write(Paths.get(args.getOutputFileName()),
                execute().stream().map(Path::toString).collect(Collectors.toList()), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    private static List<Path> execute() throws IOException {
        ConditionFileVisitor searcher = new ConditionFileVisitor();
        Predicate<Path> predicate;
        switch (args.getMethod()) {
            case Regex:
                predicate = path -> {
                    PathMatcher matcher = FileSystems.getDefault().getPathMatcher("regex:" + args.getPattern());
                    return matcher.matches(path) && !Files.isDirectory(path);
                };
                break;
            case Mask:
                predicate = path -> {
                    PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*" + args.getPattern());
                    return matcher.matches(path) && !Files.isDirectory(path);
                };
                break;
            default:
                predicate = path -> path.getFileName().toString().equals(args.getPattern());
        }
        searcher.setCondition(predicate);
        Files.walkFileTree(args.getDirectory(), searcher);
        return searcher.getPaths();
    }
}