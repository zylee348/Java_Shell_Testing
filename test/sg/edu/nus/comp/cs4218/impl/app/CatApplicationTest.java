package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CatException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.*;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class CatApplicationTest {

    private CatApplication catApplication;
    private OutputStream stdout;
    private final String inputString = "Hello" + STRING_NEWLINE + "World" + STRING_NEWLINE + "This is a test";

    private InputStream stdin;
    private static final String CWD = System.getProperty("user.dir");
    private static final String CAT_RESOURCES = fileSeparator() + "test" + fileSeparator() + "resources" + fileSeparator() + "app" + fileSeparator() + "cat";

    @BeforeEach
    public void setUp() throws IOException {
        catApplication = new CatApplication();
        stdin = System.in;
        stdout = new ByteArrayOutputStream();
        Environment.currentDirectory = CWD + CAT_RESOURCES;
    }
    @AfterEach
    public void tearDown() throws Exception {
        Environment.currentDirectory = CWD;
        stdout.close();
    }
    @Test
    public void readFromStreamWithLineNumbers_WithLineNumbers_ShouldHaveLineNumbers() throws CatException {
        String inputString = "Hello" + STRING_NEWLINE + "World" + STRING_NEWLINE + "This is a test";
        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));

        String expectedOutput = buildExpectedOutputWithLineNumbers("Hello", "World", "This is a test");
        String actualOutput = catApplication.readFromStreamWithLineNumbers(inputStream, true);
        assertEquals(expectedOutput, actualOutput, "The line numbers are not added correctly");
    }

    @Test
    public void readFromStreamWithLineNumbers_WithoutLineNumbers_ShouldNotHaveLineNumbers() throws CatException {
        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));

        String expectedOutput = buildExpectedOutputWithoutLineNumbers("Hello", "World", "This is a test");
        String actualOutput = catApplication.readFromStreamWithLineNumbers(inputStream, false);

        assertEquals(expectedOutput, actualOutput, "The output should not have line numbers");
    }

    @Test
    public void catStdin_WithLineNumbers_ShouldHaveLineNumbers() throws AbstractApplicationException {
        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));

        String expectedOutput = buildExpectedOutputWithLineNumbers("Hello", "World", "This is a test");
        String actualOutput = catApplication.catStdin( true, inputStream);
        assertEquals(expectedOutput, actualOutput, "The line numbers are not added correctly");
    }

    @Test
    public void catStdin_WithoutLineNumbers_ShouldNotHaveLineNumbers() throws AbstractApplicationException {
        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));

        String expectedOutput = buildExpectedOutputWithoutLineNumbers("Hello", "World", "This is a test");
        String actualOutput = catApplication.catStdin(false, inputStream);

        assertEquals(expectedOutput, actualOutput, "The output should not have line numbers");
    }
    @Test
    public void catFiles_CatTwoFiles_ShouldCatTwoFiles() throws Exception {
        // Arrange
        String realFilePath1 = "myfile.txt";
        String realFilePath2 = "myfile2.txt";
        CatApplication catApp = new CatApplication();
        String result = catApp.catFiles(false, realFilePath1, realFilePath2);
        // Assert
        String expected = "This is myfile." + STRING_NEWLINE + "2nd one." + STRING_NEWLINE;
        assertEquals(expected, result);

    }
    @Test
    public void catFiles_SingleFileValidInput_ShouldCatFile() throws AbstractApplicationException {
        String arg = "myfile.txt" ;
        String expectedOutput = "This is myfile." + STRING_NEWLINE;
        String actualOutput = catApplication.catFiles(false, arg);
        assertEquals(expectedOutput, actualOutput, "The output should match the content of myfile.txt");
    }
    @Test
    public void catFiles_SingleFileValidInputWithLineNumbers_ShouldCatFile() throws AbstractApplicationException {
        String arg = "myfile.txt" ;
        String expectedOutput = "1 This is myfile." + STRING_NEWLINE;
        String actualOutput = catApplication.catFiles(true, arg);
        assertEquals(expectedOutput, actualOutput, "The output should match the content of myfile.txt");
    }
    @Test
    public void run_SingleFileValidInput_ShouldCatFile() throws AbstractApplicationException {
        String arg = "myfile.txt" ;
        String[] args = { arg };
        catApplication.run(args, stdin, stdout);
        String expectedOutput = "This is myfile." + STRING_NEWLINE;
        String actualOutput = stdout.toString();
        assertEquals(expectedOutput, actualOutput, "The output should match the content of myfile.txt");
    }

    @Test
    public void catFiles_NonExistentFile_ThrowsException() {
        String nonExistentFile = "nonexistent.txt";
        CatException thrown = assertThrows(CatException.class,
                () -> catApplication.catFiles(false, nonExistentFile));
        assertEquals("cat: " +  nonExistentFile + ": No such file or directory" , thrown.getMessage(),
                "Expected an exception for non-existent file");
    }
    @Test
    @DisabledOnOs(OS.MAC)
    public void catFiles_DirectoryInsteadOfFile_ThrowsException() {
        String directoryPath = "folder"; // Ensure this directory exists in your test environment
        // Setup: Create directory if it does not exist
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        CatException thrown = assertThrows(CatException.class,
                () -> catApplication.catFiles(false, directoryPath));
        assertEquals("cat: folder: Is a directory", thrown.getMessage(),
                "Expected an exception for directory path instead of file");
    }
    @Test
    public void catFiles_AbsolutePathDirectoryInsteadOfFile_ThrowsException() {
        String directoryPath = CWD + CHAR_FILE_SEP + "test"; // Ensure this directory exists in your test environment
        CatException thrown = assertThrows(CatException.class,
                () -> catApplication.catFiles(false, directoryPath));
        assertEquals("cat: " + directoryPath + ": Is a directory", thrown.getMessage(),
                "Expected an exception for directory path instead of file");
    }
    @Test
    public void run_WithNullStdinAndNoFileArguments_ThrowsException() {
        String[] args = {};
        assertThrows(CatException.class, () -> catApplication.run(args, null, stdout));
    }
    @Test
    public void run_WithStdinAndNoFileArguments_ReadsFromStdin() throws AbstractApplicationException, IOException {
        String[] args = {};
        InputStream stdin = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));
        catApplication.run(args, stdin, stdout);
        assertEquals(inputString, stdout.toString().trim());
    }
    @Test
    public void run_NonExistentFileArgument_ThrowsException() {
        String[] args = {"nonexistent.txt"};
        assertThrows(CatException.class, () -> catApplication.run(args, stdin, stdout));
    }
    @Test
    public void run_DirectoryInsteadOfFileArgument_ThrowsException() {
        String[] args = {"folder"}; // Ensure this directory exists in your test environment
        assertThrows(CatException.class, () -> catApplication.run(args, stdin, stdout));
    }
    private String buildExpectedOutputWithLineNumbers(String... lines) {
        StringBuilder stringBuilder = new StringBuilder();
        int lineNum = 1;
        for (String line : lines) {
            stringBuilder.append(lineNum++).append(CHAR_SPACE).append(line).append(STRING_NEWLINE);
        }
        return stringBuilder.toString();
    }

    private String buildExpectedOutputWithoutLineNumbers(String... lines) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : lines) {
            stringBuilder.append(line).append(STRING_NEWLINE);
        }
        return stringBuilder.toString();
    }
}

