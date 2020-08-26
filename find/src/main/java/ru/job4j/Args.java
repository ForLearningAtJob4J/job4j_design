package ru.job4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Args {
    enum Method {
        Regex,
        Mask,
        FullName
    }
    private Path directory;
    private String outputFileName;
    private String pattern;

    private Method method;

    public Path getDirectory() {
        return directory;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public Method getMethod() {
        return method;
    }

    public String getPattern() {
        return pattern;
    }

    public Args(String[] args) {
        parse(args);
        validate();
    }

    private void parse(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if ("-d".equals(args[i]) && i + 1 < args.length) {
                directory = Paths.get(args[i + 1]);
            } else if ("-o".equals(args[i]) && i + 1 < args.length) {
                outputFileName = args[i + 1];
            } else if ("-n".equals(args[i]) && i + 1 < args.length) {
                pattern = args[i + 1];
            } else if ("-m".equals(args[i])) {
                method = Method.Mask;
            } else if ("-f".equals(args[i])) {
                method = Method.FullName;
            } else if ("-r".equals(args[i])) {
                method = Method.Regex;
            } else if ("-h".equals(args[i])) {
                throw new IllegalArgumentException("");
            }
        }
    }

    private void validate() {
        if (pattern == null) {
            System.out.println("No pattern was specified!");
            if (method == null) {
                System.out.printf("No method was specified!%nUsed method: mask (switch -m) and pattern *.* (switch -n *.*)%n");
                pattern = "*.*";
                method = Method.Mask;
            } else if (method == Method.FullName) {
                throw new IllegalArgumentException("Specify full file name with the -n switch!");
            } else if (method == Method.Mask) {
                System.out.println("Used mask: *.*");
                pattern = "*.*";
            } else if (method == Method.Regex) {
                System.out.println("Used mask: *.*");
                pattern = "*.*";
                method = Method.Mask;
            }
        }

        if (method == null) {
            System.out.println("No method was specified!");
            if (pattern != null) {
                if (pattern.matches("[.\\w\\d]+")) {
                    System.out.println("Used method: full file name (switch -f)");
                    method = Method.FullName;
                } else {
                    System.out.println("Used method: mask (switch -m)");
                    method = Method.Mask;
                }
            }
        }

        if (directory == null) {
            directory = Paths.get(".");
        } else {
            if (!Files.exists(directory) || !Files.isDirectory(directory)) {
                throw new IllegalArgumentException("Specified directory doesn't exists!");
            }
        }

        if (outputFileName == null) {
            System.out.printf("No output file name was specified!%nUsed output file name: result.txt%n");
            outputFileName = "result.txt";
        }
    }
}