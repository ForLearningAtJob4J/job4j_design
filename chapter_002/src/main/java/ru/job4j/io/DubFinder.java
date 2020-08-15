package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static java.nio.file.FileVisitResult.CONTINUE;

public class DubFinder implements FileVisitor<Path> {
    public class FileInfo {
        Path file;
        String name;
        String fullCanonicalName;
        long size;

        public FileInfo(Path file) throws IOException {
            this.file = file;
            this.name = file.getFileName().toString();
            this.fullCanonicalName = file.toRealPath(LinkOption.NOFOLLOW_LINKS).toString();
            this.size = Files.size(file);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            FileInfo fileInfo = (FileInfo) o;

            if (size != fileInfo.size) {
                return false;
            }

            return name.equals(fileInfo.name);
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + (int) size;
            return result;
        }
    }

    List<FileInfo> paths;
    Map<FileInfo, List<FileInfo>> copies;

    public DubFinder() {
        paths = new ArrayList<>();
        copies = new HashMap<>();
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)  {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileInfo fi = new FileInfo(file);
        if (paths.contains(fi)) {
            List<FileInfo> list = copies.get(fi);
            if (list == null) {
                list = new ArrayList<>();
                list.add(paths.get(paths.indexOf(fi)));
            }
            list.add(fi);
            copies.put(fi, list);
        } else {
            paths.add(fi);
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        return CONTINUE;
    }

    public Map<FileInfo, List<FileInfo>> getCopies() {
        return copies;
    }
}