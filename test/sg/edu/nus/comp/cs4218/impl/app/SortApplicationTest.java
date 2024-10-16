package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.fileSeparator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.SortException;

import java.io.*;

public class SortApplicationTest {

    private SortApplication sortApp;
    private OutputStream stdout;
    private static final String CWD = System.getProperty("user.dir");
    private static final String SORT_RESOURCES = fileSeparator() + "test" + fileSeparator() + "resources" + fileSeparator() + "app" + fileSeparator() + "sort";


    @BeforeEach
    public void setUp() {
        sortApp = new SortApplication();
        stdout = new ByteArrayOutputStream();
        Environment.currentDirectory = CWD + SORT_RESOURCES;
    }
    @AfterEach
    public void tearDown() throws Exception {
        Environment.currentDirectory = CWD;
        stdout.close();
    }
    @Test
    public void sortFromFiles_Null_ThrowNullException() throws AbstractApplicationException {
        SortException exception = assertThrows(SortException.class, () -> {
            sortApp.run(new String[]{"nonexistent.txt"}, null, stdout);
        });
        assertEquals("sort: No such file or directory", exception.getMessage());
    }
    @Test
    public void sortFromFiles_EmptyFile_ReturnsEmptyString() throws AbstractApplicationException {
        String filename = Environment.currentDirectory + fileSeparator() + "emptyfile.txt";
        String result = sortApp.sortFromFiles(false, false, false, filename);
        assertEquals("" + STRING_NEWLINE, result);
    }
    @Test
    public void sortFromFiles_SpecialCharacters_SortsLines() throws AbstractApplicationException {
        String filename = Environment.currentDirectory + fileSeparator() + "specialCharsFile.txt";
        String result = sortApp.sortFromFiles(false, false, false, filename);
        String expected = "#Fruit" + STRING_NEWLINE +
                "$Market" + STRING_NEWLINE +
                "1Orange" + STRING_NEWLINE +
                "3Bananas" + STRING_NEWLINE +
                "Apple" + STRING_NEWLINE +
                "Zebra" + STRING_NEWLINE;
        assertEquals(expected, result);
    }
    @Test
    public void sortFromFiles_SortFromMultipleFiles_SortedAlphabetically() throws AbstractApplicationException {
        String[] filenames = {Environment.currentDirectory + fileSeparator() + "file1.txt",
                Environment.currentDirectory + fileSeparator() + "file2.txt"};
        String result = sortApp.sortFromFiles(false, false, false, filenames);
        String expected = "apple" + STRING_NEWLINE +
                "banana" + STRING_NEWLINE +
                "cherry" + STRING_NEWLINE +
                "date" + STRING_NEWLINE +
                "elderberry" + STRING_NEWLINE +
                "fig" + STRING_NEWLINE;
        assertEquals(expected, result);
    }


    @Test
    public void sortFromFiles_WithSortedInput_SortsLinesAlphabetically() throws AbstractApplicationException {
        String filename = Environment.currentDirectory + fileSeparator() + "testfile1.txt";
        String result = sortApp.sortFromFiles(false, false, false, filename);
        assertEquals("apple" + STRING_NEWLINE + "banana" + STRING_NEWLINE + "cherry" + STRING_NEWLINE + "date" + STRING_NEWLINE + "fig" + STRING_NEWLINE, result);
    }

    @Test
    public void sortFromFiles_ReverseOrder_SortsLinesInDescendingOrder() throws AbstractApplicationException {
        String filename = Environment.currentDirectory + fileSeparator() + "testfile2.txt";
        String result = sortApp.sortFromFiles(false, true, false, filename);
        assertEquals("zebra" + STRING_NEWLINE + "orange" + STRING_NEWLINE + "grape" + STRING_NEWLINE + "banana" + STRING_NEWLINE + "apple" + STRING_NEWLINE, result);
    }

    @Test
    public void sortFromFiles_CaseInsensitive_SortsIgnoringCase() throws AbstractApplicationException {
        String filename = Environment.currentDirectory + fileSeparator() + "testfile3.txt";
        String result = sortApp.sortFromFiles(false, false, true, filename);
        assertEquals("apple" + STRING_NEWLINE + "Banana" + STRING_NEWLINE + "Grape" + STRING_NEWLINE + "Orange" + STRING_NEWLINE + "Zebra" + STRING_NEWLINE, result);
    }

    @Test
    public void sortFromFiles_NumericalSort_SortsNumerically() throws AbstractApplicationException {
        String filename = Environment.currentDirectory + fileSeparator() + "numericFile.txt";
        String result = sortApp.sortFromFiles(true, false, false, filename);
        String expected = "1 apple" + STRING_NEWLINE +
                "2 banana" + STRING_NEWLINE +
                "10 cherry" + STRING_NEWLINE;
        assertEquals(expected, result);
    }
    @Test
    public void sortFromFiles_LexicographicallySort_SortsLexicographically() throws AbstractApplicationException {
        String filename = Environment.currentDirectory + fileSeparator() + "numericFile.txt";
        String result = sortApp.sortFromFiles(false, false, false, filename);
        String expected = "1 apple" + STRING_NEWLINE +
                "10 cherry" + STRING_NEWLINE +
                "2 banana" + STRING_NEWLINE;
        assertEquals(expected, result);
    }
    @Test
    public void sortFromFiles_CaseInsensitiveSort_SortsIgnoringCase() throws AbstractApplicationException {
        String filename = Environment.currentDirectory + fileSeparator() + "caseFile.txt";
        String result = sortApp.sortFromFiles(false, false, true, filename);
        String expected = "apple" + STRING_NEWLINE +
                "Banana" + STRING_NEWLINE +
                "cherry" + STRING_NEWLINE;
        assertEquals(expected, result);
    }
    @Test
    public void sortFromFiles_CaseInsensitiveAndReverseSort_SortsIgnoringCase() throws AbstractApplicationException {
        String filename = Environment.currentDirectory + fileSeparator() + "caseFile.txt";
        String result = sortApp.sortFromFiles(false, true, true, filename);
        String expected =  "cherry"+ STRING_NEWLINE +
                "Banana" + STRING_NEWLINE + "apple" + STRING_NEWLINE;
        assertEquals(expected, result);
    }

    @Test
    void sortFromStdin_RegularInput_SortsAlphabetically() throws Exception {
        String input = "banana" + STRING_NEWLINE + "apple" + STRING_NEWLINE + "cherry";
        InputStream stdin = new ByteArrayInputStream(input.getBytes());
        String result = sortApp.sortFromStdin(false, false, false, stdin);
        assertEquals("apple" + STRING_NEWLINE + "banana" + STRING_NEWLINE + "cherry" + STRING_NEWLINE, result);
    }

    @Test
    void sortFromStdin_NumericInput_SortsNumerically() throws Exception {
        String input = "10 apple" + STRING_NEWLINE + "2 banana" + STRING_NEWLINE + "3 cherry";
        InputStream stdin = new ByteArrayInputStream(input.getBytes());
        String result = sortApp.sortFromStdin(true, false, false, stdin);
        assertEquals("2 banana" + STRING_NEWLINE + "3 cherry" + STRING_NEWLINE + "10 apple" + STRING_NEWLINE, result);
    }

    @Test
    void sortFromStdin_ReverseOrder_SortsInDescendingOrder() throws Exception {
        String input = "banana" + STRING_NEWLINE + "apple" + STRING_NEWLINE + "cherry";
        InputStream stdin = new ByteArrayInputStream(input.getBytes());
        String result = sortApp.sortFromStdin(false, true, false, stdin);
        assertEquals("cherry" + STRING_NEWLINE + "banana" + STRING_NEWLINE + "apple" + STRING_NEWLINE, result);
    }

    @Test
    void sortFromStdin_CaseInsensitive_SortsIgnoringCase() throws Exception {
        String input = "Banana" + STRING_NEWLINE + "apple" + STRING_NEWLINE + "Cherry";
        InputStream stdin = new ByteArrayInputStream(input.getBytes());
        String result = sortApp.sortFromStdin(false, false, true, stdin);
        assertEquals("apple" + STRING_NEWLINE + "Banana" + STRING_NEWLINE + "Cherry" + STRING_NEWLINE, result);
    }

    @Test
    void sortFromStdin_NullInputStream_ThrowsSortException() {
        assertThrows(SortException.class, () -> sortApp.sortFromStdin(false, false, false, null));
    }
    @Test
    void sortFromFiles_ReverseOrderAndCaseInsensitive_SortsCorrectly() throws AbstractApplicationException {
        String filename = Environment.currentDirectory + fileSeparator() + "caseFile.txt";
        String result = sortApp.sortFromFiles(false, true, true, filename);
        String expected = "cherry" + STRING_NEWLINE + "Banana" + STRING_NEWLINE + "apple" + STRING_NEWLINE;
        assertEquals(expected, result);
    }

    @Test
    void sortFromFiles_InvalidFilePath_ThrowsException() {
        String invalidPath = "invalid/file/path.txt";
        assertThrows(SortException.class, () -> sortApp.sortFromFiles(false, false, false, invalidPath));
    }

    @Test
    void run_SortWithFirstWordNumber_SortedNumerically() throws Exception {
        InputStream stdin = new ByteArrayInputStream(("10 apple" + STRING_NEWLINE + "2 banana" + STRING_NEWLINE + "3 cherry").getBytes());
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        sortApp.run(new String[]{"-n"}, stdin, stdout);
        assertEquals("2 banana" + STRING_NEWLINE + "3 cherry" + STRING_NEWLINE + "10 apple" + STRING_NEWLINE, stdout.toString());
    }


    @Test
    void run_SortNonexistentFile_ThrowsSortException() {
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        SortException exception = assertThrows(SortException.class, () -> {
            sortApp.run(new String[]{"nonexistent.txt"}, null, stdout);
        });
        assertEquals("sort: No such file or directory", exception.getMessage());
    }

    @Test
    void run_SortWithDirectory_ThrowsSortException() {
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        SortException exception = assertThrows(SortException.class, () -> {
            sortApp.run(new String[]{"/path/to/directory"}, null, stdout);
        });
        assertEquals("sort: No such file or directory", exception.getMessage());
    }

    @Test
    public void run_SortWithStdinInput_SortedLexicographically() throws AbstractApplicationException {
        String[] args = {};
        String input = "line3" + STRING_NEWLINE +
                "line1" + STRING_NEWLINE +
                "line2" + STRING_NEWLINE;
        InputStream stdin = new ByteArrayInputStream(input.getBytes());
        sortApp.run(args, stdin, stdout);
        String expectedOutput = "line1" + STRING_NEWLINE +
                "line2" + STRING_NEWLINE +
                "line3" + STRING_NEWLINE;
        assertEquals(expectedOutput, stdout.toString());
    }
    @Test
    void run_SortInReverseOrder_SortsInDescendingOrder() throws Exception {
        String input = "c" + STRING_NEWLINE + "b" + STRING_NEWLINE + "a";
        InputStream stdin = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        sortApp.run(new String[]{"-r"}, stdin, stdout);
        assertEquals("c" + STRING_NEWLINE + "b" + STRING_NEWLINE + "a" + STRING_NEWLINE, stdout.toString());
    }

    @Test
    void run_SortCaseInsensitive_SortsIgnoringCase() throws Exception {
        String input = "C" + STRING_NEWLINE + "b" + STRING_NEWLINE + "A";
        InputStream stdin = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        sortApp.run(new String[]{"-f"}, stdin, stdout);
        assertEquals("A" + STRING_NEWLINE + "b" + STRING_NEWLINE + "C" + STRING_NEWLINE, stdout.toString());
    }

    @Test
    void run_CombineFlags_SortsAccordingly() throws Exception {
        String input = "10 apple" + STRING_NEWLINE + "2 banana" + STRING_NEWLINE + "3 cherry";
        InputStream stdin = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        sortApp.run(new String[]{"-nr"}, stdin, stdout);
        assertEquals("10 apple" + STRING_NEWLINE + "3 cherry" + STRING_NEWLINE + "2 banana" + STRING_NEWLINE, stdout.toString());
    }

    @Test
    void run_EmptyInputStream_ProducesNoOutput() throws Exception {
        InputStream stdin = new ByteArrayInputStream("".getBytes());
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        sortApp.run(new String[]{}, stdin, stdout);
        assertEquals("" + STRING_NEWLINE, stdout.toString());
    }

    @Test
    void run_InvalidFlags_ThrowsSortException() {
        InputStream stdin = new ByteArrayInputStream("input".getBytes());
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        assertThrows(SortException.class, () -> {
            sortApp.run(new String[]{"-x"}, stdin, stdout);
        });
    }
    @Test
    void sortFromStdin_sortWithMixedContentTypes_SortsCorrectly() throws AbstractApplicationException, IOException {
        // Define the content to sort
        String[] content = {
                "+special", "10number", "Acapital", "asmall", "2number"
        };
        String input = String.join(STRING_NEWLINE, content);

        // Create an InputStream from the content
        InputStream stdin = new ByteArrayInputStream(input.getBytes());

        // Perform sorting without any special flags
        String result = sortApp.sortFromStdin(false, false, false, stdin);

        // Define the expected output
        String expectedOutput = "+special" + STRING_NEWLINE +
                "10number" + STRING_NEWLINE +
                "2number" + STRING_NEWLINE +
                "Acapital" + STRING_NEWLINE +
                "asmall" + STRING_NEWLINE;

        // Assert that the result matches the expected output
        assertEquals(expectedOutput, result);
    }
    @Test
    void sortFromStdin_SortMixedContentWithNegativeNumbers_SortsCorrectly() throws AbstractApplicationException, IOException {
        // Define the content to sort, including negative numbers
        String[] content = {
                "-10", "5", "-2", "apple", "Banana", "0"
        };
        String input = String.join(STRING_NEWLINE, content);

        // Create an InputStream from the content
        InputStream stdin = new ByteArrayInputStream(input.getBytes());

        // Perform sorting with the numerical sort flag enabled
        String result = sortApp.sortFromStdin(true, false, false, stdin);

        // Define the expected output, numbers should be sorted numerically
        String expectedOutput = "-10" + STRING_NEWLINE +
                "-2" + STRING_NEWLINE +
                "0" + STRING_NEWLINE +
                "5" + STRING_NEWLINE +
                "Banana" + STRING_NEWLINE +
                "apple" + STRING_NEWLINE;

        // Assert that the result matches the expected output
        assertEquals(expectedOutput, result);
    }
}
