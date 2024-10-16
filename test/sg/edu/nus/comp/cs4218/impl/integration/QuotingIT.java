package sg.edu.nus.comp.cs4218.impl.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.PasteException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.fileSeparator;

public class QuotingIT { //NOPMD - suppressed ClassNamingConventions

    private ShellImpl shell;
    private ByteArrayOutputStream outContent;
    private static final String NEW_DIR = "testDir";
    private static final String CWD = System.getProperty("user.dir") + fileSeparator() + "test" + fileSeparator()
            + "resources";
    private static final String QUOTE_IT_RESOURCES = fileSeparator() + "integration" + fileSeparator() + "quoteIT";
    private final String resourcesDirPath = CWD + QUOTE_IT_RESOURCES;
    private final String newDirPathString = resourcesDirPath + fileSeparator() + NEW_DIR;
    private final Path newDirPath = Paths.get(newDirPathString);
    private final Path resourcesPath = Paths.get(resourcesDirPath);
    private final Path testFile = resourcesPath.resolve("test.txt");

    @BeforeEach
    public void setUp() throws IOException {
        shell = new ShellImpl();
        outContent = new ByteArrayOutputStream();

        // Set the current directory to our temporary test directory
        Environment.currentDirectory = CWD + QUOTE_IT_RESOURCES;

        if (!Files.exists(newDirPath)) {
            Files.createDirectories(resourcesPath);
        }

        // Create sample files starting with 'q'
        Path filePath1 = Path.of(Environment.currentDirectory + fileSeparator() + "qfile1.txt");
        if (!Files.exists(filePath1)) {
            Files.createFile(filePath1);
        }
        Files.write(filePath1, Arrays.asList("Line 1", "Line 2", "Line 3"));

        Path filePath2 = Path.of(Environment.currentDirectory + fileSeparator() + "qfile2.txt");
        if (!Files.exists(filePath2)) {
            Files.createFile(filePath2);
        }
        Files.write(filePath2, Arrays.asList("Line 4", "Line 5", "Line 6"));
    }

    @AfterEach
    public void tearDown() throws IOException {
        outContent.close();
         if (Files.exists(newDirPath)) {
         Files.delete(newDirPath);
         }
        if (Files.exists(resourcesPath)) {
            Files.walk(resourcesPath)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(file -> {
                        try {
                            Files.deleteIfExists(file.toPath());
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                            ; // Handle the exception as needed
                        }
                    });
        }
        Environment.currentDirectory = CWD;
    }

    @Test
    public void parseAndEvaluate_globWithIOSingleQuoteAndSubstitution_ShouldThrowException()
            throws AbstractApplicationException, ShellException, IOException {
        String testCommand = "paste 'ls q*' > all.txt";
        PasteException exception = assertThrows(PasteException.class,
                () -> shell.parseAndEvaluate(testCommand, outContent));

        String expectedErrorMessage = "paste: ls q*: " + ERR_FILE_NOT_FOUND;
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void parseAndEvaluate_globWithIODoubleQuoteAndSubstitution_ShouldThrowException()
            throws AbstractApplicationException, ShellException, IOException {
        String testCommand = "paste \"ls q*\" > test_all.txt";
        PasteException exception = assertThrows(PasteException.class,
                () -> shell.parseAndEvaluate(testCommand, outContent));

        String expectedErrorMessage = "paste: ls q*: " + ERR_FILE_NOT_FOUND;
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void parseAndEvaluate_EchoWithDoubleQuotedGlob_ShouldNotExpand()
            throws AbstractApplicationException, ShellException, IOException {
        String testCommand = "echo \"q*\"";
        shell.parseAndEvaluate(testCommand, outContent);

        // Assert that the output is the literal string "x*" and not expanded
        String expectedOutput = "q*";
        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    public void parseAndEvaluate_EchoWithBackquotedGlob_ShouldThrowException() {
        String testCommand = "echo `q*`";
        ShellException exception = assertThrows(ShellException.class,
                () -> shell.parseAndEvaluate(testCommand, outContent));

        String expectedErrorMessage = "shell: qfile1.txt: " + ERR_INVALID_APP;
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void parseAndEvaluate_SingleQuoteWithIOEchoAndSubstitution_ShouldThrowException()
            throws AbstractApplicationException, ShellException, IOException {
        String testCommand = "paste `echo 'hello'` > test_all.txt";
        PasteException exception = assertThrows(PasteException.class,
                () -> shell.parseAndEvaluate(testCommand, outContent));

        String expectedErrorMessage = "paste: hello: " + ERR_FILE_NOT_FOUND;
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void parseAndEvaluate_DoubleQuoteWithIOEchoAndSubstitution_ShouldThrowException()
            throws AbstractApplicationException, ShellException, IOException {
        String testCommand = "paste `echo \"hello\"` > test_all.txt";
        PasteException exception = assertThrows(PasteException.class,
                () -> shell.parseAndEvaluate(testCommand, outContent));

        String expectedErrorMessage = "paste: hello: " + ERR_FILE_NOT_FOUND;
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void parseAndEvaluate_doubleQuoteWithSubstitutionAndEcho_ShouldNotThrowException()
            throws AbstractApplicationException, ShellException, IOException {
        String testCommand = "echo \"This is space:`echo \" \"`.\"";
        shell.parseAndEvaluate(testCommand, outContent);
        String expected = "This is space:  .";
        assertEquals(expected, outContent.toString().trim());
    }

    @Test
    public void parseAndEvaluate_singleQuoteWithSubstitutionAndEcho_ShouldNotThrowException()
            throws AbstractApplicationException, ShellException, IOException {
        String testCommand = "echo 'This is space:`echo \" \"`.'";
        shell.parseAndEvaluate(testCommand, outContent);
        String expected = "This is space:`echo \" \"`.";
        assertEquals(expected, outContent.toString().trim());
    }

    @Test
    public void parseAndEvaluate_singleQuoteWithDoubleQuoteAndSubstitutionEcho_ShouldThrowException()
            throws AbstractApplicationException, ShellException, IOException {
        String testCommand = "echo '\"This is space `echo \" \"`\"'";
        shell.parseAndEvaluate(testCommand, outContent);
        String expected = "\"This is space `echo \" \"`\"";
        assertEquals(expected, outContent.toString().trim());
    }

    @Test
    public void parseAndEvaluate_EchoDoubleQuotesThenCatWithRedirection_ShouldCreateFileWithContent()
            throws IOException, AbstractApplicationException, ShellException {
        // Test command to echo a string into a file and then read it with cat
        String testContent = "Hello, World!";
        String testCommand = "echo \"Hello, World!\"" + " > test.txt; cat test.txt";

        shell.parseAndEvaluate(testCommand, outContent);

        // The output should contain the echoed string
        String output = outContent.toString();
        assertEquals(testContent + STRING_NEWLINE, output);

        // Also, check that the file actually contains the content
        String fileContent = Files.readString(testFile);
        assertEquals(testContent + STRING_NEWLINE, fileContent);
    }

    @Test
    public void parseAndEvaluate_EchoSingleQuotesThenCatWithRedirection_ShouldCreateFileWithContent()
            throws IOException, AbstractApplicationException, ShellException {
        // Test command to echo a string into a file and then read it with cat
        String testContent = "Hello, World!";
        String testCommand = "echo 'Hello, World!'" + " > test.txt; cat test.txt";

        shell.parseAndEvaluate(testCommand, outContent);

        // The output should contain the echoed string
        String output = outContent.toString();
        assertEquals(testContent + STRING_NEWLINE, output);

        // Also, check that the file actually contains the content
        String fileContent = Files.readString(testFile);
        assertEquals(testContent + STRING_NEWLINE, fileContent);
    }

    @Test
    public void parseAndEvaluate_mkdirThenCdSingleQuotes_ShouldSucceed() throws Exception {
        String testCommand = "mkdir '" + NEW_DIR + "'; cd '" + NEW_DIR + "'";
        shell.parseAndEvaluate(testCommand, outContent);

        assertEquals(Environment.currentDirectory, newDirPath.toString());
    }

    @Test
    public void parseAndEvaluate_mkdirThenCdDoubleQuotes_ShouldSucceed() throws Exception {
        String testCommand = "mkdir \"" + NEW_DIR + "\"; cd \"" + NEW_DIR + "\"";
        shell.parseAndEvaluate(testCommand, outContent);

        assertEquals(Environment.currentDirectory, newDirPath.toString());
    }

    @Test
    public void parseAndEvaluate_mkdirThenCdAndBackQuotes_ShouldSucceed() throws Exception {
        String testCommand = "mkdir `" + NEW_DIR + "`; cd `" + NEW_DIR + "`";
        shell.parseAndEvaluate(testCommand, outContent);

        assertEquals(Environment.currentDirectory + "/testDir", newDirPath.toString());
    }

    @Test
    public void parseAndEvaluate_MismatchedBackQuotesWithSubstitution_ThrowsShellError() {
        String testCommand = "echo `echo test``";
        ShellException shellException = assertThrows(ShellException.class,
                () -> shell.parseAndEvaluate(testCommand, outContent));
        assertEquals("shell: " + ERR_SYNTAX, shellException.getMessage());
    }

    @Test
    public void parseAndEvaluate_EmptyBackQuotesWithSubstitution_ReturnsEmptyResult() {
        String testCommand = "``";
        String expected = "";
        assertDoesNotThrow(() -> shell.parseAndEvaluate(testCommand, outContent));
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void parseAndEvaluate_NestedBackQuotesWithSubstitutionAndEcho_ReturnsResult() {
        // Rather than behave like nested commands, `echo ` is evaluated first, then ``.
        String testCommand = "echo `echo `echo test123``";
        String expected = "echo test123" + System.lineSeparator();
        assertDoesNotThrow(() -> shell.parseAndEvaluate(testCommand, outContent));
        assertEquals(expected, outContent.toString());
    }

}
