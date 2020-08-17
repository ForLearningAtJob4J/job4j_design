package ru.job4j.io;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public static Path rootFolder;
    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source: sources) {
                String zipEntryName;
                if (rootFolder != null) {
                    zipEntryName = rootFolder.relativize(source.toPath()).toString();
                } else {
                    zipEntryName = source.getPath();
                }
                if (zipEntryName.startsWith(".." + FileSystems.getDefault().getSeparator())) {
                    zipEntryName = zipEntryName.substring(3);
                }
                zip.putNextEntry(new ZipEntry(zipEntryName));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgZip argZip = processArgs(args);

        var fileList = searchFiles(argZip.directory(), argZip.exclude());

        rootFolder = Path.of(argZip.directory());

        System.out.println("Packing " + fileList.size() + " files. Please wait...");
        packFiles(fileList.stream().map(Path::toFile).collect(Collectors.toList()), new File(argZip.output()));
        System.out.println("Done!");
    }

    private static ArgZip processArgs(String[] args) {
        ArgZip argZip = new ArgZip(args);
        if (!argZip.valid()) {
            throw new IllegalArgumentException("Usage: java -jar zip.jar -d=FOLDER_NAME -e=MASK_TO_EXCLUSION -o=OUTPUT_FILENAME");
        }

        Path folder = Path.of(argZip.directory());
        if (!Files.exists(folder)) {
            throw new IllegalArgumentException("Specified folder doesn't exist!");
        }
        if (!Files.isDirectory(folder)) {
            throw new IllegalArgumentException("Specified parameter is not a folder!");
        }
        return argZip;
    }

    private static List<Path> searchFiles(String directory, String exclude) throws IOException {
        SearchFiles searcher = new SearchFiles(path -> {
            PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*" + exclude);
            return !matcher.matches(path) && !Files.isDirectory(path);
        });
        Files.walkFileTree(Path.of(directory), searcher);

        return searcher.getPaths();
    }
}