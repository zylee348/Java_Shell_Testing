package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.GrepException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class GrepApplicationTest {
    private final GrepApplication grepApplication = new GrepApplication();
    private InputStream inputStream;
    private OutputStream outputStream;

    private static final String CASE_INSENSITIVE_FLAG = "-i";
    private static final String COUNT_LINES_FLAG = "-c";
    private static final String PREFIX_FILE_NAME_FLAG = "-H";
    private static final String GREP_EXCEPTION_PREFIX = "grep: ";
    private static final Path CWD = Paths.get(Environment.currentDirectory);
    private static final String CONTENT_1 = "unix is great os." + STRING_NEWLINE +
            "learn operating system." + STRING_NEWLINE +
            "Unix or linux which one would you pick." + STRING_NEWLINE +
            "uNix is an easy to learn OS. unix is a multiuser os.Learn unix.unix is a powerful os.";
    private static final String CONTENT_2 = "macos is great os. MacOS was developed by Apple." + STRING_NEWLINE +
            "LEARN OPERATING SYSTEMS." + STRING_NEWLINE +
            "unix or macos which one would you choose." + STRING_NEWLINE +
            "macos is fun to learn..Learn macos .it is powerful.";
    private static final String CONTENT_3 = "linux and unix and macos are all operating systems." + STRING_NEWLINE +
            "what are their differences?" + STRING_NEWLINE +
            "UNIX" + STRING_NEWLINE +
            "LINUX" + STRING_NEWLINE +
            "MACOS";
    private static final String PATTERN_UNIX = "unix";
    private static final String PATTERN_OS = "os";
    private String fileNameA;
    private String fileNameB;
    private String directory;

    private Path fileA;
    private Path fileB;
    private Path dir;

    @BeforeEach
    void setUp() throws IOException {
        fileA = Files.createTempFile(CWD, "A", ".txt");
        fileB = Files.createTempFile(CWD, "B", ".txt");
        dir = Files.createTempDirectory(CWD, "dir");

        Files.write(fileA, CONTENT_1.getBytes());
        Files.write(fileB, CONTENT_2.getBytes());

        fileNameA = fileA.toString();
        fileNameB = fileB.toString();
        directory = dir.toString();

        inputStream = new ByteArrayInputStream(CONTENT_3.getBytes());
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

    @Test
    void run_WithOneFileNoFlags_ReturnCorrectOutput() throws AbstractApplicationException {
        String[] args = {PATTERN_UNIX, fileNameA};
        grepApplication.run(args, inputStream, outputStream);
        String expected = "unix is great os." + STRING_NEWLINE +
                "uNix is an easy to learn OS. unix is a multiuser os.Learn unix.unix is a powerful os." + STRING_NEWLINE;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void run_WithMultipleFilesNoFlags_ReturnCorrectOutput() throws AbstractApplicationException {
        String[] args = {PATTERN_UNIX, fileNameA, fileNameB};
        grepApplication.run(args, inputStream, outputStream);
        String expected = fileNameA + ": unix is great os." + STRING_NEWLINE +
                fileNameA + ": uNix is an easy to learn OS. unix is a multiuser os.Learn unix.unix is a powerful os." + STRING_NEWLINE +
                fileNameB + ": unix or macos which one would you choose." + STRING_NEWLINE;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void run_WithOneFileCaseInsensitiveFlag_ReturnsCorrectOutput() throws AbstractApplicationException {
        String[] args = {CASE_INSENSITIVE_FLAG, PATTERN_UNIX, fileNameA};
        grepApplication.run(args, inputStream, outputStream);
        String expected = "unix is great os." + STRING_NEWLINE +
                "Unix or linux which one would you pick." + STRING_NEWLINE +
                "uNix is an easy to learn OS. unix is a multiuser os.Learn unix.unix is a powerful os." + STRING_NEWLINE;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void run_WithMultipleFilesCaseInsensitiveFlag_ReturnCorrectOutput() throws AbstractApplicationException {
        String[] args = {PATTERN_OS, fileNameA, fileNameB};
        grepApplication.run(args, inputStream, outputStream);
        String expected = fileNameA + ": unix is great os." + STRING_NEWLINE +
                fileNameA + ": uNix is an easy to learn OS. unix is a multiuser os.Learn unix.unix is a powerful os." + STRING_NEWLINE +
                fileNameB + ": macos is great os. MacOS was developed by Apple." + STRING_NEWLINE +
                fileNameB + ": unix or macos which one would you choose." + STRING_NEWLINE +
                fileNameB + ": macos is fun to learn..Learn macos .it is powerful." + STRING_NEWLINE;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void run_WithOneFileCountFlag_ReturnsCorrectOutput() throws AbstractApplicationException {
        String[] args = {COUNT_LINES_FLAG, PATTERN_UNIX, fileNameA};
        grepApplication.run(args, inputStream, outputStream);
        String expected = "2" + STRING_NEWLINE;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void run_WithMultipleFilesCountFlag_ReturnsCorrectOutput() throws AbstractApplicationException {
        String[] args = {COUNT_LINES_FLAG, PATTERN_UNIX, fileNameA, fileNameB};
        grepApplication.run(args, inputStream, outputStream);
        String expected = fileNameA + ": 2" + STRING_NEWLINE +
                fileNameB + ": 1" + STRING_NEWLINE;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void run_WithOneFilePrefixFileNameFlag_ReturnsCorrectOutput() throws AbstractApplicationException {
        String[] args = {PREFIX_FILE_NAME_FLAG, PATTERN_UNIX, fileNameA};
        grepApplication.run(args, inputStream, outputStream);
        String expected = fileNameA + ": unix is great os." + STRING_NEWLINE +
                fileNameA + ": uNix is an easy to learn OS. unix is a multiuser os.Learn unix.unix is a powerful os." + STRING_NEWLINE;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void run_WithMultipleFilesPrefixFileNameFlag_ReturnsCorrectOutput() throws AbstractApplicationException {
        String[] args = {PREFIX_FILE_NAME_FLAG, PATTERN_UNIX, fileNameA, fileNameB};
        grepApplication.run(args, inputStream, outputStream);
        String expected = fileNameA + ": unix is great os." + STRING_NEWLINE +
                fileNameA + ": uNix is an easy to learn OS. unix is a multiuser os.Learn unix.unix is a powerful os." + STRING_NEWLINE +
                fileNameB + ": unix or macos which one would you choose." + STRING_NEWLINE;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void run_WithStdinNoFlags_ReturnsCorrectOutput() throws AbstractApplicationException {
        String[] args = {PATTERN_UNIX};
        grepApplication.run(args, inputStream, outputStream);
        String expected = "linux and unix and macos are all operating systems." + STRING_NEWLINE;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void run_WithStdinCountFlag_ReturnsCorrectOutput() throws AbstractApplicationException {
        String[] args = {COUNT_LINES_FLAG, PATTERN_UNIX};
        grepApplication.run(args, inputStream, outputStream);
        String expected = "1" + STRING_NEWLINE;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void run_WithStdinCaseInsensitiveFlag_ReturnsCorrectOutput() throws AbstractApplicationException {
        String[] args = {CASE_INSENSITIVE_FLAG, PATTERN_UNIX};
        grepApplication.run(args, inputStream, outputStream);
        String expected = "linux and unix and macos are all operating systems." + STRING_NEWLINE +
                "UNIX" + STRING_NEWLINE;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void run_WithStdinPrefixFileNameFlag_ReturnsCorrectOutput() throws AbstractApplicationException {
        String[] args = {PREFIX_FILE_NAME_FLAG, PATTERN_UNIX};
        grepApplication.run(args, inputStream, outputStream);
        String expected = "(standard input): linux and unix and macos are all operating systems." + STRING_NEWLINE;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void run_WithStdinAndFileNoFlags_ReturnsCorrectOutput() throws AbstractApplicationException {
        String[] args = {PATTERN_UNIX, fileNameA, "-"};
        grepApplication.run(args, inputStream, outputStream);
        String expected = fileNameA + ": unix is great os." + STRING_NEWLINE +
                fileNameA + ": uNix is an easy to learn OS. unix is a multiuser os.Learn unix.unix is a powerful os." + STRING_NEWLINE +
                "(standard input): linux and unix and macos are all operating systems." + STRING_NEWLINE;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void run_WithStdinAndFileCountFlag_ReturnsCorrectOutput() throws AbstractApplicationException {
        String[] args = {COUNT_LINES_FLAG, PATTERN_UNIX, fileNameA, "-"};
        grepApplication.run(args, inputStream, outputStream);
        String expected = fileNameA + ": 2" + STRING_NEWLINE +
                "(standard input): 1" + STRING_NEWLINE;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void run_WithStdinAndFileCaseInsensitiveFlag_ReturnsCorrectOutput() throws AbstractApplicationException {
        String[] args = {CASE_INSENSITIVE_FLAG, PATTERN_UNIX, fileNameA, "-"};
        grepApplication.run(args, inputStream, outputStream);
        String expected = fileNameA + ": unix is great os." + STRING_NEWLINE +
                fileNameA + ": Unix or linux which one would you pick." + STRING_NEWLINE +
                fileNameA + ": uNix is an easy to learn OS. unix is a multiuser os.Learn unix.unix is a powerful os." + STRING_NEWLINE +
                "(standard input): linux and unix and macos are all operating systems." + STRING_NEWLINE +
                "(standard input): UNIX" + STRING_NEWLINE;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void run_WithStdinAndFilePrefixFileNameFlag_ReturnsCorrectOutput() throws AbstractApplicationException {
        String[] args = {PREFIX_FILE_NAME_FLAG, PATTERN_UNIX, fileNameA, "-"};
        grepApplication.run(args, inputStream, outputStream);
        String expected = fileNameA + ": unix is great os." + STRING_NEWLINE +
                fileNameA + ": uNix is an easy to learn OS. unix is a multiuser os.Learn unix.unix is a powerful os." + STRING_NEWLINE +
                "(standard input): linux and unix and macos are all operating systems." + STRING_NEWLINE;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void run_WithOneFileAllFlags_ReturnsCorrectOutput() throws AbstractApplicationException {
        String[] args = {COUNT_LINES_FLAG, CASE_INSENSITIVE_FLAG, PREFIX_FILE_NAME_FLAG, PATTERN_UNIX, fileNameA};
        grepApplication.run(args, inputStream, outputStream);
        String expected = fileNameA + ": 3" + STRING_NEWLINE;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void run_WithStdinAllFlags_ReturnsCorrectOutput() throws AbstractApplicationException {
        String[] args = {COUNT_LINES_FLAG, CASE_INSENSITIVE_FLAG, PREFIX_FILE_NAME_FLAG, PATTERN_UNIX};
        grepApplication.run(args, inputStream, outputStream);
        String expected = "(standard input): 2" + STRING_NEWLINE;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void run_WithStdinAndFileAllFlags_ReturnsCorrectOutput() throws AbstractApplicationException {
        String[] args = {COUNT_LINES_FLAG, CASE_INSENSITIVE_FLAG, PREFIX_FILE_NAME_FLAG, PATTERN_UNIX, fileNameA, "-"};
        grepApplication.run(args, inputStream, outputStream);
        String expected = fileNameA + ": 3" + STRING_NEWLINE +
                "(standard input): 2" + STRING_NEWLINE;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void run_WithDir_ThrowsException() throws AbstractApplicationException {
        String[] args = {PATTERN_UNIX, directory};
        grepApplication.run(args, inputStream, outputStream);
        String expected = directory + ": Is a directory" + STRING_NEWLINE;
        assertEquals(expected, outputStream.toString());
    }

    @Test
    void run_WithInvalidPattern_ThrowsException() throws AbstractApplicationException {
        String[] args = {"([az", fileNameA};
        assertThrows(Exception.class, () -> grepApplication.run(args, inputStream, outputStream));

        args[0] = null;
        assertThrows(Exception.class, () -> grepApplication.run(args, inputStream, outputStream));

        args[0] = "";
        assertThrows(Exception.class, () -> grepApplication.run(args, inputStream, outputStream));
    }

    @Test
    void run_WithNullArgs_ThrowsException() {
        Throwable thrown = assertThrows(GrepException.class, () -> grepApplication.run(null, null, null));
        String expected = GREP_EXCEPTION_PREFIX + ERR_NULL_ARGS;
        assertEquals(expected, thrown.getMessage());
    }
}
