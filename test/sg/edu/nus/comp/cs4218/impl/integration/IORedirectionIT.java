package sg.edu.nus.comp.cs4218.impl.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.util.FileSystemUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.testutils.TestStringUtils.*;

public class IORedirectionIT { //NOPMD - suppressed ClassNamingConventions
    private ShellImpl shell;
    private static final String CWD = System.getProperty("user.dir") + CHAR_FILE_SEP + "test" + CHAR_FILE_SEP + "resources";
    private static final String IO_IT_RESOURCES = CHAR_FILE_SEP + "integration" + CHAR_FILE_SEP + "IORedirectionIT" ;
    private static final String FILE_ONE_NAME = "file1.txt";
    private static final File FILE_ONE = new File(FILE_ONE_NAME);
    private static final String FILE_ONE_CONTENT = "file1 content\nfile1 content\n";
    private static final String FILE_TWO_NAME = "file2.txt";
    private static final File FILE_TWO = new File(FILE_TWO_NAME);
    private static final String FILE_TWO_CONTENT = "file2 content\n";
    private static final String FILE_OUTPUT_NAME = "output.txt";
    private static final File FILE_OUTPUT = new File(FILE_OUTPUT_NAME);
    private OutputStream outputStream;

    @BeforeEach
    void setUp() throws IOException {
        shell = new ShellImpl();

        Environment.currentDirectory = CWD + IO_IT_RESOURCES;

        Path filePath1 = Path.of(Environment.currentDirectory + CHAR_FILE_SEP + FILE_ONE_NAME);
        if (!Files.exists(filePath1)) {
            Files.createFile(filePath1);
        }
        Files.write(filePath1, Arrays.asList("file1 content", "file1 content"));

        Path filePath2 = Path.of(Environment.currentDirectory + CHAR_FILE_SEP + FILE_TWO_NAME);
        if (!Files.exists(filePath2)) {
            Files.createFile(filePath2);
        }
        Files.write(filePath2, Arrays.asList("file2 content"));

        outputStream = new ByteArrayOutputStream();
    }

    @AfterEach
    void cleanUp() throws IOException {
        outputStream.close();

        FILE_ONE.delete();
        FILE_TWO.delete();
        FILE_OUTPUT.delete();

        Environment.currentDirectory = CWD;
    }

    public static void writeToFileWithText(File file, String text) throws IOException {
        FileWriter writer = new FileWriter(file); //NOPMD

        if (text == null || text.isBlank()) {
            writer.close();
            return;
        }

        writer.write(text);
        writer.close();
    }

    public static Path getFilePath(String name) {
        return Paths.get(Environment.currentDirectory).resolve(name);
    }
    public static String getFileContent(String name) {
        try {
            Path filePath = getFilePath(name);
            return Files.readString(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void parseAndEvaluate_redirectionWithRedirectionCatMultipleOutputRedirections_RedirectOutputToFile() {
        // ASSUMPTION: file2's contents get erased after this command, following ubuntu shell behavior
        String testCommand = "cat file1.txt > file2.txt > output.txt";

        assertDoesNotThrow(() -> {
            shell.parseAndEvaluate(testCommand, outputStream);
            System.out.println(FileSystemUtils.getAbsolutePathName(FILE_ONE_NAME));
            String outputFileContent = getFileContent(FILE_OUTPUT_NAME);
            assertEquals(FILE_ONE_CONTENT, outputFileContent);

            String file1Content = getFileContent(FILE_ONE_NAME);
            assertEquals(FILE_ONE_CONTENT, file1Content);

            String file2Content = getFileContent(FILE_TWO_NAME);
            assertEquals("", file2Content);
        });
    }

    @Test
    void parseAndEvaluate_redirectionWithRedirectionCatMultipleInputRedirections_RedirectInputToStdOut() {
        String testCommand = "cat < file1.txt < file2.txt";

        assertDoesNotThrow(() -> {
            shell.parseAndEvaluate(testCommand, outputStream);
            assertEquals(FILE_TWO_CONTENT, outputStream.toString());

            String file1Content = getFileContent(FILE_ONE_NAME);
            assertEquals(FILE_ONE_CONTENT, file1Content);

            String file2Content = getFileContent(FILE_TWO_NAME);
            assertEquals(FILE_TWO_CONTENT, file2Content);
        });
    }

    @Test
    void parseAndEvaluate_redirectionWithRedirectionTee_RedirectOutputToFile() {
        String testCommand = "tee -a file1.txt < file2.txt";

        assertDoesNotThrow(() -> {
            shell.parseAndEvaluate(testCommand, outputStream);
            String file1Content = getFileContent(FILE_ONE_NAME);
            assertEquals(FILE_ONE_CONTENT + FILE_TWO_CONTENT, file1Content);
            assertEquals(FILE_TWO_CONTENT, outputStream.toString());

            String file2Content = getFileContent(FILE_TWO_NAME);
            assertEquals(FILE_TWO_CONTENT, file2Content);
        });
    }

    @Test
    void parseAndEvaluate_redirectionWithRedirectionPasteStdin_RedirectOutputToFile() {
        String testCommand = "paste - file1.txt - < file2.txt > output.txt";

        assertDoesNotThrow(() -> {
            shell.parseAndEvaluate(testCommand, outputStream);
            String outputFileContent = getFileContent(FILE_OUTPUT_NAME);
            assertEquals("file2 content" + CHAR_TAB + "file1 content" + CHAR_TAB + STRING_NEWLINE
                    + CHAR_TAB + "file1 content" + CHAR_TAB + STRING_NEWLINE, outputFileContent);
        });
    }

    @Test
    void parseAndEvaluate_redirectionWithRedirectionPasteStdinSerial_RedirectOutputToFile() {
        String testCommand = "paste -s - file1.txt < file2.txt > output.txt";

        assertDoesNotThrow(() -> {
            shell.parseAndEvaluate(testCommand, outputStream);
            String outputFileContent = getFileContent(FILE_OUTPUT_NAME);
            assertEquals("file2 content" + STRING_NEWLINE
                    + "file1 content" + CHAR_TAB + "file1 content" + STRING_NEWLINE, outputFileContent);
        });
    }

    @Test
    void parseAndEvaluate_redirectionWithRedirectionPasteSerialFlag_RedirectOutputToFile() {
        String testCommand = "paste -s file1.txt file2.txt > output.txt";

        assertDoesNotThrow(() -> {
            shell.parseAndEvaluate(testCommand, outputStream);
            String outputFileContent = getFileContent(FILE_OUTPUT_NAME);
            assertEquals("file1 content" + CHAR_TAB + "file1 content" + STRING_NEWLINE
                    + "file2 content" + STRING_NEWLINE, outputFileContent);
        });
    }

    @Test
    void parseAndEvaluate_redirectionWithPipeLsGrep_PipeAndRedirectOutputToFile() {
        String testCommand = "ls | grep \"file\" > output.txt";

        assertDoesNotThrow(() -> {
            shell.parseAndEvaluate(testCommand, outputStream);
            String outputFileContent = getFileContent(FILE_OUTPUT_NAME);
            assertEquals(FILE_ONE_NAME + STRING_NEWLINE + FILE_TWO_NAME + STRING_NEWLINE, outputFileContent);
        });
    }

    @Test
    void parseAndEvaluate_redirectionWithSubstitutionPasteLs_RedirectCommandOutputToFile() {
        String testCommand = "paste `ls file*` > output.txt";

        assertDoesNotThrow(() -> {
            shell.parseAndEvaluate(testCommand, outputStream);
            String outputFileContent = getFileContent(FILE_OUTPUT_NAME);
            assertEquals("file1 content" + CHAR_TAB + "file2 content" + STRING_NEWLINE
                    + "file1 content" + CHAR_TAB + STRING_NEWLINE, outputFileContent);
        });
    }

    @Test
    void parseAndEvaluate_redirectionWithQuotingEcho_RedirectCommandOutputToFile() {
        String testCommand = "echo 'Hello world!' > output.txt";

        assertDoesNotThrow(() -> {
            shell.parseAndEvaluate(testCommand, outputStream);
            String outputFileContent = getFileContent(FILE_OUTPUT_NAME);
            assertEquals("Hello world!" + STRING_NEWLINE, outputFileContent);
        });
    }

    @Test
    void parseAndEvaluate_redirectionWithQuotingGrep_RedirectOutputToFile() {
        String testCommand = "grep -H \"1 content\" < file1.txt";

        assertDoesNotThrow(() -> {
            shell.parseAndEvaluate(testCommand, outputStream);
            assertEquals("(standard input): file1 content" + STRING_NEWLINE +
                    "(standard input): file1 content" + STRING_NEWLINE , outputStream.toString());
        });
    }

    @Test
    void parseAndEvaluate_inputRedirectionWithNonexistent_ThrowsException() {
        String testCommand = "cat < error.txt";

        assertThrows(ShellException.class, () -> {
            shell.parseAndEvaluate(testCommand, outputStream);
        });
    }

    @Test
    void parseAndEvaluate_outputRedirectionWithNonexistent_ShouldNotThrowException() {
        String testCommand = "cat file1.txt > nonexistent.txt";

        assertDoesNotThrow(() -> {
            shell.parseAndEvaluate(testCommand, outputStream);
            String nonexistentFileContent = getFileContent("nonexistent.txt");
            assertEquals(FILE_ONE_CONTENT, nonexistentFileContent);
        });
    }

    @Test
    void parseAndEvaluate_redirectionWithNoFiles_ThrowsException() {
        String testCommand = "cat <";

        assertThrows(ShellException.class, () -> {
            shell.parseAndEvaluate(testCommand, outputStream);
        });
    }
}
