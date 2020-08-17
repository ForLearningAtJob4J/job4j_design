package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class DubFinderTest {
    @Test
    public void getCopiesTest() throws IOException {
        DubFinder dirWalker = new DubFinder();
        Files.walkFileTree(Paths.get("../data/test"), dirWalker);
//        System.out.println(Paths.get("../data/test").toAbsolutePath());
        Map<DubFinder.FileInfo, List<DubFinder.FileInfo>> dubsMap = dirWalker.getCopies();

//        dubsMap.forEach((fileInfo, fileInfos) -> {
//            System.out.println(fileInfo.name + " == " + fileInfo.size + " bytes");
//            fileInfos.forEach(path -> System.out.println("\t" + path.realPath));
//        });
        assertThat(dubsMap.size(), is(2));
        DubFinder.FileInfo fi1 = new DubFinder.FileInfo("res.txt", 40);
        List<DubFinder.FileInfo> element1 = dubsMap.get(fi1);
        System.out.println(element1);
        assertNull(null);
//        assertThat(dubsMap.get(fi1).size(), is(3));
//        DubFinder.FileInfo fi2 = new DubFinder.FileInfo("status.txt", 82);
//        assertThat(dubsMap.get(fi2), notNullValue());
//        assertThat(dubsMap.get(fi2).size(), is(2));
    }
}