package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DubFinderTest {
    @Test
    public void getCopiesTest() throws IOException {
        DubFinder dirWalker = new DubFinder();
        Files.walkFileTree(Paths.get("../data/test"), dirWalker);
        System.out.println(Paths.get("../data/test").toAbsolutePath());
        var dubsMap = dirWalker.getCopies();
        dubsMap.forEach((fileInfo, fileInfos) -> {
            System.out.println(fileInfo.name + " == " + fileInfo.size + " bytes");
            fileInfos.forEach(path -> System.out.println("\t" + path.realPath));
        });
        assertThat(dubsMap.size(), is(2));
        DubFinder.FileInfo fi1 = new DubFinder.FileInfo("res.txt", 40);
        assertThat(dubsMap.get(fi1), notNullValue());
        assertThat(dubsMap.get(fi1).size(), is(3));
        DubFinder.FileInfo fi2 = new DubFinder.FileInfo("status.txt", 82);
        assertThat(dubsMap.get(fi2).size(), notNullValue());
        assertThat(dubsMap.get(fi2).size(), is(2));
    }
}