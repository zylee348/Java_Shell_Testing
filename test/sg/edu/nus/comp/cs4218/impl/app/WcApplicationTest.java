package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.WcException;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_STREAMS;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.fileSeparator;


public class WcApplicationTest {

    private WcApplication wcApplication;
    InputStream stdin;
    OutputStream stdout;

    private static final String NON_EXISTING_FILE = "non_existent.txt";
    private static final String CWD = System.getProperty("user.dir");
    private static final String WC_RESOURCES = fileSeparator() + "test" + fileSeparator() + "resources" + fileSeparator() + "app" + fileSeparator() + "wc";;
    @BeforeEach
    public void setUp() {
        wcApplication = new WcApplication();
        stdin = System.in;
        stdout = new ByteArrayOutputStream();
        Environment.currentDirectory = CWD + WC_RESOURCES;
    }
    @AfterEach
    void reset() {
        Environment.currentDirectory = CWD;
    }

    @SuppressWarnings("PMD.ExcessiveMethodLength")
    @Test
    public void countFromFiles_WithEmptyFile_ShouldWcCorrectly() {
        // Setup
        String fileName = "empty.txt";
        try {
            createEmptyFile(Environment.currentDirectory + fileSeparator() + fileName); // Helper method to create an empty file
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Execute
        String result = null; // Count only bytes
        try {
//            System.out.println(Environment.currentDirectory);
            result = wcApplication.countFromFiles(false, false, true, fileName);
        } catch (AbstractApplicationException e) {
            throw new RuntimeException(e);
        }
        // Assert
        assertEquals("0 empty.txt", result.trim());
    }
    @Test
    void countFromFiles_NonExistingFile_ShowsFileNotFound() throws Exception {
        String result = wcApplication.countFromFiles(true, true, true, NON_EXISTING_FILE);
        assertEquals("wc: " + ERR_FILE_NOT_FOUND + STRING_NEWLINE, result);
    }
    @Test
    void countFromFiles_CountLinesAndWords_ShouldReturnCorrectResult() {
        // Setup
        String fileName = "sample.txt";
        try {
            createSampleFile(Environment.currentDirectory + fileSeparator() + fileName, "This is a sample file." + STRING_NEWLINE + "It has multiple lines." + STRING_NEWLINE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Execute
        String result = null;
        try {
            result = wcApplication.countFromFiles(false, true, true, fileName);
        } catch (AbstractApplicationException e) {
            throw new RuntimeException(e);
        }
        // Assert
        assertEquals("3\t9\tsample.txt", result.trim().replaceAll("\\s+", "\t"));
    }
    @Test
    void countFromFileAndStdin_WithNullFile_ShouldThrowException() {
        assertThrows(WcException.class, () -> {
            wcApplication.countFromFileAndStdin(true, true, true, stdin, null, "-");
        });
    }
    @Test
    void countFromFileAndStdin_WithNullStdin_ShouldThrowException() {
        String validFileName = "valid.txt";
        assertThrows(WcException.class, () -> {
            wcApplication.countFromFileAndStdin(true, true, true, null, validFileName, "-");
        });
    }
    @Test
    void countFromStdin_WithEmptyInput_ShouldReturnZeroCounts() {
        // Execute
        String result = null;
        try {
            result = wcApplication.countFromStdin(true, true, true, new ByteArrayInputStream("".getBytes()));
        } catch (AbstractApplicationException e) {
            throw new RuntimeException(e);
        }
        assertEquals("0\t0\t0", result.trim().replaceAll("\\s+", "\t"));
    }
    @Test
    public void countFromStdin_withLineWordByteCount_ShouldReturnValidOutput() throws AbstractApplicationException {
        // Prepare the test input
        String testInput = "This is a test." + STRING_NEWLINE + "With 3 lines." + STRING_NEWLINE;
        InputStream stdin = new ByteArrayInputStream(testInput.getBytes());
        byte[] testInputBytes = testInput.getBytes(StandardCharsets.UTF_8);
        // Call countFromStdin method
        Boolean isBytes = true;
        Boolean isLines = true;
        Boolean isWords = true;
        String result = wcApplication.countFromStdin(isBytes, isLines, isWords, stdin);
        // Calculate the expected byte count
        int expectedByteCount = testInputBytes.length;
        // Expected output: Lines, Words, Bytes
        String expectedOutput = String.format("%d\t%d\t%d", 3, 7, expectedByteCount);
        assertEquals(expectedOutput.trim(), result.trim());
    }
@Test
    public void countFromStdin_WithWord_ShouldReturnValidOutput() throws AbstractApplicationException {
        // Prepare the test input
        String testInput = "This is a test." + STRING_NEWLINE + "With two lines." + STRING_NEWLINE;
        InputStream stdin = new ByteArrayInputStream(testInput.getBytes());
        byte[] testInputBytes = testInput.getBytes(StandardCharsets.UTF_8);
        // Call countFromStdin method
        Boolean isBytes = false;
        Boolean isLines = false;
        Boolean isWords = true;
        String result = wcApplication.countFromStdin(isBytes, isLines, isWords, stdin);
        // Calculate the expected byte count
        // Expected output: Lines, Words, Bytes
        String expectedOutput = String.format("\t%d", 7);
        assertEquals(expectedOutput.trim(), result.trim());
    }

    @Test
    public void run_withFileInput_ShouldReturnValidOutput() throws WcException {
        String[] args = {"-l", "-w", "testfile.txt"};
        String testInput = "This is a test." + STRING_NEWLINE + "With two lines." + STRING_NEWLINE;

        // Prepare a sample file
        try (FileWriter fileWriter = new FileWriter(Environment.currentDirectory + fileSeparator() + "testfile.txt")) {
            fileWriter.write(testInput);
        } catch (IOException e) {
            fail("Setup failed: Unable to create test file.");
        }

        wcApplication.run(args, stdin, stdout);
        String actualOutput = stdout.toString().trim();
        String expectedOutput = "3\t7\ttestfile.txt";

        // Normalize whitespace for comparison
        String normalizedActual = actualOutput.replaceAll("\\s+", " ");
        String normalizedExpected = expectedOutput.replaceAll("\\s+", " ");

        assertEquals(normalizedExpected, normalizedActual);
        // Cleanup the test file
        new File("testfile.txt").delete();
    }
    @Test
    @EnabledOnOs({OS.WINDOWS})
    void run_WithMultipleFilesWindows_ShouldWcCorrectly() throws WcException {
        String [] array = {"test1.txt", "test2.txt"};
        String expected = "\t1\t1\t10 test1.txt" + STRING_NEWLINE +
                "\t2\t2\t22 test2.txt" + STRING_NEWLINE +
                "\t3\t3\t32 total" + STRING_NEWLINE;
        wcApplication.run(array, stdin, stdout);
        assertEquals(expected, stdout.toString());
    }
    @Test
    @EnabledOnOs({OS.MAC})
    void run_WithMultipleFilesMac_ShouldWcCorrectly() throws WcException {
        String [] array = {"test1.txt", "test2.txt"};
        String expected = "\t1\t1\t10 test1.txt" + STRING_NEWLINE +
                "\t2\t2\t21 test2.txt" + STRING_NEWLINE +
                "\t3\t3\t31 total" + STRING_NEWLINE;
        wcApplication.run(array, stdin, stdout);
        assertEquals(expected, stdout.toString());
    }
    @Test
    @EnabledOnOs(OS.WINDOWS)
    public void run_withIORedirection_ShouldReturnValidOutput() throws WcException, FileNotFoundException {
        String[] args = {"-l", "-c"};
        String expectedOutput = "4\t24";
        try (InputStream simulatedStdin = new FileInputStream(Environment.currentDirectory + File.separator + "test.txt");
             ByteArrayOutputStream simulatedStdout = new ByteArrayOutputStream()) {
            wcApplication.run(args, simulatedStdin, simulatedStdout);
            String actualOutput = simulatedStdout.toString().trim();
            assertEquals(expectedOutput, actualOutput);
        } catch (IOException e) {
            throw new RuntimeException("Error handling streams", e);
        }
    }

    @Test
    @EnabledOnOs(OS.MAC)
    public void run_withIORedirectionMac_ShouldReturnValidOutput() throws WcException, FileNotFoundException {
        String[] args = {"-l", "-c"};
        String expectedOutput = "4\t21";
        try (InputStream simulatedStdin = new FileInputStream(Environment.currentDirectory + File.separator + "test.txt");
             ByteArrayOutputStream simulatedStdout = new ByteArrayOutputStream()) {
            wcApplication.run(args, simulatedStdin, simulatedStdout);
            String actualOutput = simulatedStdout.toString().trim();
            assertEquals(expectedOutput, actualOutput);
        } catch (IOException e) {
            throw new RuntimeException("Error handling streams", e);
        }
    }

    @Test
    void run_WithNullStdout_ShouldThrowException() {
        stdout = null;
        String[] args = {};
        WcException wcException = assertThrows(WcException.class, () -> wcApplication.run(args, stdin, stdout));
        assertEquals("wc: " + ERR_NULL_STREAMS, wcException.getMessage());
    }

    @Test
    void countFromFileAndStdin_CountFromFileAndStdinValidFile_CountsCorrectly() throws Exception {
        String expected =  "1\t1\t10 test1.txt";
        String result = wcApplication.countFromFileAndStdin(true, true, true, stdin,
                "test1.txt");
        assertEquals(expected.trim(), result.trim());
    }

    @Test
    void getCountReport_FromStdin_ShouldReturnRightResult() throws AbstractApplicationException {
        String testData = "Hello, world!" + STRING_NEWLINE +
                "This is a test." + STRING_NEWLINE +
                "Third line.";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(testData.getBytes());
        // Execute
        long[] result = wcApplication.getCountReport(inputStream);
        // Validate
        assertEquals(3, result[0], "Number of lines should be 3.");
        assertEquals(8, result[1], "Number of words should be 7.");
        assertEquals(testData.length(), result[2], "Number of bytes should match the length of the testData.");
    }

    @Test
    public void run_withNoArgumentsAndStdinInput_ShouldCountCorrectly() throws Exception {
        String testInput = "Hello\nWorld\n";
        stdin = new ByteArrayInputStream(testInput.getBytes());
        String[] args = {};
        wcApplication.run(args, stdin, stdout);
        String expectedOutput = "3\t2\t12"; // 2 lines, 2 words, 12 bytes (including newlines)
        assertEquals(expectedOutput, stdout.toString().trim());
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    public void run_withCOptionAndFileInput_ShouldCountBytes() throws Exception {
        String[] args = {"-c", "testfile.txt"};
        wcApplication.run(args, stdin, stdout);
        String expectedOutput = "34 testfile.txt";
        assertEquals(expectedOutput, stdout.toString().trim());
    }
    @Test
    @EnabledOnOs(OS.MAC)
    public void run_withCOptionAndFileInputMac_ShouldCountBytes() throws Exception {
        String[] args = {"-c", "testfile.txt"};
        wcApplication.run(args, stdin, stdout);
        String expectedOutput = "32 testfile.txt";
        assertEquals(expectedOutput, stdout.toString().trim());
    }

    @Test
    public void run_withNullStdin_ShouldThrowException() {
        String[] args = {};
        stdin = null;
        assertThrows(WcException.class, () -> wcApplication.run(args, stdin, stdout));
    }

    @Test
    public void run_withNullStdout_ShouldThrowException() {
        String[] args = {};
        stdout = null;
        assertThrows(WcException.class, () -> wcApplication.run(args, stdin, stdout));
    }


    // Helper method to create a sample file with the given content
    private void createSampleFile(String fileName, String content) throws IOException {
        File file = new File(fileName);
        try (Writer writer = new FileWriter(file)) {
            writer.write(content);
        }
    }
    private static void createEmptyFile(String fileName) throws IOException {
        File file = new File(fileName);

        // Create the file. If the file already exists, overwrite it.
        if (file.createNewFile() || file.isFile()) {
            // The file is either created or already exists.
        } else {
            throw new IOException("Failed to create the file: " + fileName);
        }
    }
}
