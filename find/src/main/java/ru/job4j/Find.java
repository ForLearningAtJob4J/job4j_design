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

        ConditionFileVisitor searcher = new ConditionFileVisitor();
        searcher.setCondition(PredicateFactory.of(args.getMethod(), args.getPattern()));
        Files.walkFileTree(args.getDirectory(), searcher);

        Files.write(Paths.get(args.getOutputFileName()),
                searcher.getPaths().stream().map(Path::toString).collect(Collectors.toList()),
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}