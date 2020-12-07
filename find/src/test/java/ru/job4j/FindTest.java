package ru.job4j;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FindTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    static final String RESULT_FILE_NAME = "result.txt";
    static final String PS = FileSystems.getDefault().getSeparator();
    File testFolder;

    @Before
    public void setUp() throws IOException {
        testFolder = folder.newFolder("Test");
        String rootTestFolder = "Test" + PS;
        folder.newFile(rootTestFolder + "temp.txt");
        folder.newFile(rootTestFolder + "temp1.txt");
        folder.newFile(rootTestFolder + "log.txt");

        folder.newFolder(rootTestFolder, "SubFolder");
        String rootSubTestFolder = rootTestFolder + "SubFolder" + PS;
        folder.newFile(rootSubTestFolder + "temp.txt");
        folder.newFile(rootSubTestFolder + "temp2.txt");
        folder.newFile(rootSubTestFolder + "log12.txt");
    }

    @Test
    public void whenFindByFullName() throws Exception {
        File resultFile = folder.newFile(RESULT_FILE_NAME);
        Find.main(new String[] {"-n", "temp.txt", "-o", resultFile.toString(), "-d", testFolder.toString()});

        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(resultFile.toString()))) {
            in.lines().peek(System.out::println).forEach(rsl::add);
        }
        assertEquals(rsl.size(), 2);
    }
}