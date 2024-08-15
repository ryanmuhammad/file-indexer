package com.csg;

import com.csg.rules.LongWordListRule;
import com.csg.rules.UppercaseWordCountRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FileProcessorTest {

    private static List<File> tempFiles;
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeAll
    public static void setUp() {
        tempFiles = new ArrayList<>();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        for (File file : tempFiles) {
            file.delete();
        }
        System.setOut(originalOut);
    }

    @Test
    public void testFileIsNotExist() {
        FileProcessor fileProcessor = new FileProcessor();
        fileProcessor.processFile(new File("file.txt"));

        assertThat(outContent.toString(), containsString("File not found: file.txt"));
    }

    @Test
    public void testFileProcessingWithUppercaseAndLongWords() throws IOException {
        File tempFile = createTempFile("This is a Test.\nAnother Line with Words like Example and Programming.");
        File tempFile2 = createTempFile("This is a Test.\nCreate an indexing process for a search system.");
        tempFiles.add(tempFile);
        tempFiles.add(tempFile2);

        var fileProcessor = new FileProcessor();
        var uppercaseRule = new UppercaseWordCountRule();
        var longWordRule = new LongWordListRule();

        fileProcessor.addIndexingRule(uppercaseRule);
        fileProcessor.addIndexingRule(longWordRule);

        fileProcessor.processFile(tempFile);
        fileProcessor.processFile(tempFile2);

        assertThat(uppercaseRule.getCount(), equalTo(10));
        List<String> longWords = longWordRule.getLongWords();
        assertThat(longWords.size(), equalTo(8));
        assertThat(longWords, hasItem("Another"));
        assertThat(longWords, hasItem("Example"));
        assertThat(longWords, hasItem("Programming"));
        assertThat(longWords, hasItem("Create"));
        assertThat(longWords, hasItem("indexing"));
        assertThat(longWords, hasItem("process"));
        assertThat(longWords, hasItem("search"));
        assertThat(longWords, hasItem("system"));
    }

    private File createTempFile(String content) throws IOException {
        File tempFile = Files.createTempFile("testFile", ".txt").toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(content);
        }
        return tempFile;
    }

}
