package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.*;
import sg.edu.nus.comp.cs4218.exception.UniqException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class UniqApplicationTest {
    private final UniqApplication uniqApplication = new UniqApplication();
    private static final String COUNT_FLAG = "-c";
    private static final String REPEATED_FLAG = "-d";
    private static final String ALL_REPEATED_FLAG = "-D";
    private static final String HELLO_WORLD_STR = "Hello World";
    private static final String ALICE_STR = "Alice";
    private static final String BOB_STR = "Bob";
    private static final String UNIQ_EXCEPTION_PREFIX = "uniq: ";
    private static final String INPUTFILE_CONTENT = HELLO_WORLD_STR + STRING_NEWLINE +
            HELLO_WORLD_STR + STRING_NEWLINE +
            HELLO_WORLD_STR + STRING_NEWLINE +
            ALICE_STR + STRING_NEWLINE +
            ALICE_STR + STRING_NEWLINE +
            BOB_STR + STRING_NEWLINE +
            ALICE_STR + STRING_NEWLINE +
            BOB_STR;
    private static final String NO_FLAG_RESULT = HELLO_WORLD_STR + STRING_NEWLINE +
            ALICE_STR + STRING_NEWLINE +
            BOB_STR + STRING_NEWLINE +
            ALICE_STR + STRING_NEWLINE +
            BOB_STR + STRING_NEWLINE;
    private static final String COUNT_FLAG_RESULT = "3 " + HELLO_WORLD_STR + STRING_NEWLINE +
            "2 " + ALICE_STR + STRING_NEWLINE +
            "1 " + BOB_STR + STRING_NEWLINE +
            "1 " + ALICE_STR + STRING_NEWLINE +
            "1 " + BOB_STR + STRING_NEWLINE;
    private static final String REPEATED_FLAG_RESULT = HELLO_WORLD_STR + STRING_NEWLINE +
            ALICE_STR + STRING_NEWLINE;
    private static final String ALL_REPEATED_FLAG_RESULT = HELLO_WORLD_STR + STRING_NEWLINE +
            HELLO_WORLD_STR + STRING_NEWLINE +
            HELLO_WORLD_STR + STRING_NEWLINE +
            ALICE_STR + STRING_NEWLINE +
            ALICE_STR + STRING_NEWLINE;
    private static final String COUNT_REPEATED_FLAG_RESULT = "3 " + HELLO_WORLD_STR + STRING_NEWLINE +
            "2 " + ALICE_STR + STRING_NEWLINE;
    private static final String ALL_FLAGS_RESULT = "3 " + HELLO_WORLD_STR + STRING_NEWLINE +
            "3 " + HELLO_WORLD_STR + STRING_NEWLINE +
            "3 " + HELLO_WORLD_STR + STRING_NEWLINE +
            "2 " + ALICE_STR + STRING_NEWLINE +
            "2 " + ALICE_STR + STRING_NEWLINE;
    private static final String INPUTFILE_NAME = "input.txt";
    private static final String OUTPUTFILE_NAME = "output.txt";
    private static final String FILE_NONEXISTENT = "Nonexistent.txt";

    @BeforeEach
    public void setUp() throws IOException {
        File inputFile = new File(INPUTFILE_NAME);
        try (FileWriter writer = new FileWriter(inputFile)) {
            writer.write(INPUTFILE_CONTENT);
        }
    }

    @AfterEach
    public void cleanUp() {
        File inputFile = new File(INPUTFILE_NAME);
        File outputFile = new File(OUTPUTFILE_NAME);
        if (inputFile.exists()) {
            inputFile.delete();
        }
        if (outputFile.exists()) {
            outputFile.delete();
        }
    }

    @Test
    void uniqFromFile_WithNoFlags_ReturnsCorrect() throws Exception {
        String actual = uniqApplication.uniqFromFile(false, false, false,
                INPUTFILE_NAME, OUTPUTFILE_NAME);
        assertEquals(NO_FLAG_RESULT, actual);
    }

    @Test
    void uniqFromStdin_WithNoFlags_ReturnsCorrect() throws Exception {
        InputStream stdin = new ByteArrayInputStream(INPUTFILE_CONTENT.getBytes());
        String actual = uniqApplication.uniqFromStdin(false, false, false,
                stdin, OUTPUTFILE_NAME);
        assertEquals(NO_FLAG_RESULT, actual);
        stdin.close();
    }

    @Test
    void uniqFromFile_WithCountFlag_ReturnsCorrect() throws Exception {
        String actual = uniqApplication.uniqFromFile(true, false, false,
                INPUTFILE_NAME, OUTPUTFILE_NAME);
        assertEquals(COUNT_FLAG_RESULT, actual);
    }

    @Test
    void uniqFromStdin_WithCountFlag_ReturnsCorrect() throws Exception {
        InputStream stdin = new ByteArrayInputStream(INPUTFILE_CONTENT.getBytes());
        String actual = uniqApplication.uniqFromStdin(true, false, false,
                stdin, OUTPUTFILE_NAME);
        assertEquals(COUNT_FLAG_RESULT, actual);
    }

    @Test
    void uniqFromFile_WithRepeatedFlag_ReturnsCorrect() throws Exception {
        String actual = uniqApplication.uniqFromFile(false, true, false,
                INPUTFILE_NAME, OUTPUTFILE_NAME);
        assertEquals(REPEATED_FLAG_RESULT, actual);
    }

    @Test
    void uniqFromStdin_WithRepeatedFlag_ReturnsCorrect() throws Exception {
        InputStream stdin = new ByteArrayInputStream(INPUTFILE_CONTENT.getBytes());
        String actual = uniqApplication.uniqFromStdin(false, true, false,
                stdin, OUTPUTFILE_NAME);
        assertEquals(REPEATED_FLAG_RESULT, actual);
        stdin.close();
    }

    @Test
    void uniqFromFile_WithAllRepeatedFlag_ReturnsCorrect() throws Exception {
        String actual = uniqApplication.uniqFromFile(false, false, true,
                INPUTFILE_NAME, OUTPUTFILE_NAME);
        assertEquals(ALL_REPEATED_FLAG_RESULT, actual);
    }

    @Test
    void uniqFromStdin_WithAllRepeatedFlag_ReturnsCorrect() throws Exception {
        InputStream stdin = new ByteArrayInputStream(INPUTFILE_CONTENT.getBytes());
        String actual = uniqApplication.uniqFromStdin(false, false, true,
                stdin, OUTPUTFILE_NAME);
        assertEquals(ALL_REPEATED_FLAG_RESULT, actual);
        stdin.close();
    }

    @Test
    void uniqFromFile_WithCountAndRepeatedFlags_ReturnsCorrect() throws Exception {
        String actual = uniqApplication.uniqFromFile(true, true, false,
                INPUTFILE_NAME, OUTPUTFILE_NAME);
        assertEquals(COUNT_REPEATED_FLAG_RESULT, actual);
    }

    @Test
    void uniqFromStdin_WithCountAndRepeatedFlags_ReturnsCorrect() throws Exception {
        InputStream stdin = new ByteArrayInputStream(INPUTFILE_CONTENT.getBytes());
        String actual = uniqApplication.uniqFromStdin(true, true, false,
                stdin, OUTPUTFILE_NAME);
        assertEquals(COUNT_REPEATED_FLAG_RESULT, actual);
        stdin.close();
    }

    @Test
    void uniqFromFile_WithAllFlags_ReturnsCorrect() throws Exception {
        String actual = uniqApplication.uniqFromFile(true, true, true,
                INPUTFILE_NAME, OUTPUTFILE_NAME);
        assertEquals(ALL_FLAGS_RESULT, actual);
    }

    @Test
    void uniqFromStdin_WithAllFlags_ReturnsCorrect() throws Exception {
        InputStream stdin = new ByteArrayInputStream(INPUTFILE_CONTENT.getBytes());
        String actual = uniqApplication.uniqFromStdin(true, true, true,
                stdin, OUTPUTFILE_NAME);
        assertEquals(ALL_FLAGS_RESULT, actual);
        stdin.close();
    }

    @Test
    void run_WithDashAsStdIn_ReturnsCorrect() throws Exception {
        String[] args = {"-"};
        InputStream stdin = new ByteArrayInputStream(INPUTFILE_CONTENT.getBytes());
        OutputStream stdout = new ByteArrayOutputStream();
        uniqApplication.run(args, stdin, stdout);
        assertEquals(NO_FLAG_RESULT, stdout.toString());
        stdin.close();
        stdout.close();
    }

    @Test
    void run_WithOutputFile_ReturnsCorrectOutputFileContent() throws Exception {
        String[] args = {INPUTFILE_NAME, OUTPUTFILE_NAME};
        OutputStream stdout = new ByteArrayOutputStream();
        uniqApplication.run(args, null, stdout);
        assertEquals(NO_FLAG_RESULT, Files.readString(Path.of(OUTPUTFILE_NAME)));
    }

    @Test
    void run_WithValidFlags_ReturnsCorrect() throws Exception {
        String[] args = {COUNT_FLAG, REPEATED_FLAG, ALL_REPEATED_FLAG, INPUTFILE_NAME};
        OutputStream stdout = new ByteArrayOutputStream();
        uniqApplication.run(args, null, stdout);
        assertEquals(ALL_FLAGS_RESULT, stdout.toString());
        stdout.close();
    }

    @Test
    void run_WithNullArgs_ThrowsException() throws Exception {
        String expected = UNIQ_EXCEPTION_PREFIX + ERR_NULL_ARGS;
        Throwable thrown = assertThrows(UniqException.class, () -> {
            uniqApplication.run(null, null, null);
        });
        assertEquals(expected, thrown.getMessage());
    }
}
