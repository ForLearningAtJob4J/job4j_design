package ru.job4j.io;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class DubFinderTest {

    @Test
    public void getCopiesTest() throws IOException {
        DubFinder dirWalker = new DubFinder();
        Files.walkFileTree(Paths.get("../data/test"), dirWalker);
        System.out.println(Paths.get("../data/test").toAbsolutePath());
        dirWalker.getCopies().forEach((fileInfo, fileInfos) -> {
            System.out.println(fileInfo.name + " == " + fileInfo.size + " bytes");
            fileInfos.forEach(path -> System.out.println("\t" + path.fullCanonicalName));
        });
    }
}