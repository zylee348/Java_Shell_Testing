package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.TeeException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class TeeApplicationTest {
    private final TeeApplication teeApplication = new TeeApplication();
    private InputStream inputStream;
    private OutputStream outputStream;

    private static final String APPEND_FLAG = "-a";
    private static final Path CWD = Paths.get(Environment.currentDirectory);
    private static final String TEE_EXCEPTION_PREFIX = "tee: ";
    private static final String TEST_CONTENT = "test";
    private static final String SAMPLE = "Hello world" + STRING_NEWLINE;
    private String fileNameA;
    private String fileNameB;
    private String directory;

    private Path fileA;
    private Path fileB;
    private Path dir;

    @BeforeEach
    void setUp() throws IOException{
        fileA = Files.createTempFile(CWD, "A", ".txt");
        fileB = Files.createTempFile(CWD, "B", ".txt");
        dir = Files.createTempDirectory(CWD, "dir");

        Files.write(fileA, SAMPLE.getBytes());
        String contentForB = SAMPLE + SAMPLE;
        Files.write(fileB, contentForB.getBytes());

        fileNameA = fileA.toString();
        fileNameB = fileB.toString();
        directory = dir.toString();

        inputStream = new ByteArrayInputStream(TEST_CONTENT.getBytes());
        outputStream = new ByteArrayOutputStream();
    }

    @AfterEach
    void cleanUp() throws IOException {
        delete(fileA, false);
        delete(fileB,false);
        delete(dir, true);

        inputStream.close();
        outputStream.close();
    }

    private void delete(Path path, boolean isDirectory) throws IOException {
        if (Files.exists(path)) {
            if (isDirectory) {
                try (Stream<Path> walk = Files.walk(path)) {
                    walk.sorted(Comparator.reverseOrder())
                            .map(Path::toFile)
                            .forEach(File::delete);
                }
            } else {
                Files.delete(path);
            }
        }
    }

    private String getFileContent(Path path) throws IOException {
        return Files.readString(path);
    }

    @Test
    void run_WithNoFiles_CorrectOutputStream() throws AbstractApplicationException {
        String[] args = {};
        teeApplication.run(args, inputStream, outputStream);
        assertEquals(TEST_CONTENT + STRING_NEWLINE, outputStream.toString());
    }

    @Test
    void run_WithDash_CorrectOutputStream() throws AbstractApplicationException {
        String[] args = {"-"};
        teeApplication.run(args, inputStream, outputStream);
        assertEquals(TEST_CONTENT + STRING_NEWLINE, outputStream.toString());
    }

    @Test
    void run_WithNonEmptyFileNoAppend_CorrectFileContent() throws Exception {
        String[] args = {fileNameA};
        teeApplication.run(args, inputStream, outputStream);
        assertEquals(TEST_CONTENT + STRING_NEWLINE, getFileContent(fileA));
    }

    @Test
    void run_WithNonEmptyFilesNoAppend_CorrectOutputStream() throws Exception {
        String[] args = {fileNameA, fileNameB};
        teeApplication.run(args, inputStream, outputStream);
        assertEquals(TEST_CONTENT + STRING_NEWLINE, getFileContent(fileA));
        assertEquals(TEST_CONTENT + STRING_NEWLINE, getFileContent(fileB));
    }

    @Test
    void run_WithNonEmptyFileAppend_CorrectFileContent() throws Exception {
        String[] args = {APPEND_FLAG, fileNameA};
        teeApplication.run(args, inputStream, outputStream);
        assertEquals( SAMPLE + TEST_CONTENT + STRING_NEWLINE, getFileContent(fileA));
    }

    @Test
    void run_WithNonEmptyFilesAppend_CorrectFileContents() throws Exception {
        String[] args = {APPEND_FLAG, fileNameA, fileNameB};
        teeApplication.run(args, inputStream, outputStream);
        assertEquals( SAMPLE + TEST_CONTENT + STRING_NEWLINE, getFileContent(fileA));
        assertEquals(SAMPLE + SAMPLE + TEST_CONTENT + STRING_NEWLINE, getFileContent(fileB));
    }

    @Test
    void run_WithDir_ThrowsException() {
        String[] args = {directory};
        Throwable thrown = assertThrows(TeeException.class, () -> teeApplication.run(args, inputStream, outputStream));
        String expected = TEE_EXCEPTION_PREFIX + directory + ": " + ERR_IS_DIR;
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    void run_WithNullStdout_ThrowsException() {
        String[] args = {};
        Throwable thrown = assertThrows(TeeException.class, () -> teeApplication.run(args, inputStream, null));
        String expected = TEE_EXCEPTION_PREFIX + ERR_NO_OSTREAM;
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    void run_WithNullStdin_ThrowsException() {
        String[] args = {};
        Throwable thrown = assertThrows(TeeException.class, () -> teeApplication.run(args, null, outputStream));
        String expected = TEE_EXCEPTION_PREFIX + ERR_NO_ISTREAM;
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    void run_WithNullArgs_ThrowsException() {
        Throwable thrown = assertThrows(TeeException.class, () -> teeApplication.run(null, null, null));
        String expected = TEE_EXCEPTION_PREFIX + ERR_NULL_ARGS;
        assertEquals(expected, thrown.getMessage());
    }
}
