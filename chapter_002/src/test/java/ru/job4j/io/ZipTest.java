package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ZipTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenPackFilesInTemporary() throws Exception {
        File f = folder.newFolder("ZipTest");
        String rootZipFolder = "ZipTest" + FileSystems.getDefault().getSeparator();
        folder.newFile(rootZipFolder + "temp.txt");
        folder.newFile(rootZipFolder + "temp1.txt");
        folder.newFile(rootZipFolder + "log.txt");

        folder.newFolder(rootZipFolder, "SubFolder");
        String rootSubZipFolder = rootZipFolder + "SubFolder" + FileSystems.getDefault().getSeparator();
        folder.newFile(rootSubZipFolder + "temp.txt");
        folder.newFile(rootSubZipFolder + "temp2.txt");
        folder.newFile(rootSubZipFolder + "log12.txt");

        File fileZip = folder.newFile("result.zip");

        SearchFiles searcher = new SearchFiles(path -> {
            PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**temp*.txt");
            return !matcher.matches(path) && path.toFile().isFile();
        });
        Files.walkFileTree(f.toPath(), searcher);
        List<Path> fileList = searcher.getPaths();

        Zip.rootFolder = folder.getRoot().toPath();
        Zip.packFiles(
                fileList.stream().map(Path::toFile).collect(Collectors.toList()),
                fileZip
        );

        int c = 0;
        try (ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(fileZip)))) {
            while (zipInputStream.getNextEntry() != null) {
                c++;
            }
        }
        assertThat(c, is(2));
    }
}