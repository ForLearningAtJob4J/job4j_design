package ru.job4j.gc;

import java.io.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileCache {
    private static Cache cache;
    private static FileCache fileCache;
    private static String pathToFiles;

    private FileCache() {
    }

    /**
     *
     * @param path  - directory to files
     * @param loader - Function to load file
     * @return file cache instance
     */
    public static FileCache of(String path, Function<String, String> loader) {
        if (fileCache == null) {
            cache = new Cache(loader);
            fileCache = new FileCache();
            pathToFiles = path;
        }

        return fileCache;
    }

    public String get(String key) {
        return cache.get(pathToFiles + File.separator + key);
    }

    public static void main(String[] args) throws IOException {
        String fileName = "res.txt";
        FileCache fc =
                FileCache.of(new java.io.File(".").getCanonicalPath() + File.separator + "data", x -> {
            try (BufferedReader br = new BufferedReader(new FileReader(x))) {
                return br.lines().collect(Collectors.joining(System.lineSeparator()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });

        int phaseNumber = 1;
        System.out.println(fc.get(fileName));
        System.out.printf("Phase %d completed%n%n", phaseNumber++);
        System.out.println(fc.get(fileName));
        System.out.printf("Phase %d completed%n%n", phaseNumber++);
        fillMem();
        fillMem();
        System.gc();
        System.out.println(fc.get(fileName));
        System.out.printf("Phase %d completed%n%n", phaseNumber);
    }

    public static int[] fillMem() {
        int[] array = new int[200_000];
        System.out.println("I will eat a little pie of heap memory %)");
        for (int i = 0; i < 200_000; i++) {
            array[i] = i;
        }
        return array;
    }
}