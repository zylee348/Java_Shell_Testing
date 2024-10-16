package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.MvException;
import sg.edu.nus.comp.cs4218.impl.util.FileSystemUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;

public class MvApplicationTest {
    private final MvApplication mvApplication = new MvApplication();
    private static final String NO_OVERWRITE_FLAG = "-n";
    private static final Path CWD = Paths.get(Environment.currentDirectory);
    private static final String MV_EXCEPTION_PREFIX = "mv: ";
    private Path fileA;
    private Path fileB;
    private Path dirA;
    private Path dirB;

    private InputStream inputStream;
    private OutputStream outputStream;

    @BeforeEach
    void setUp() throws IOException {
        fileA = Files.createTempFile(CWD, "A", ".txt");
        fileB = Files.createTempFile(CWD, "B", ".txt");
        dirA = Files.createTempDirectory(CWD, "dirA");
        dirB = Files.createTempDirectory(CWD, "dirB");

        inputStream = new ByteArrayInputStream(new byte[0]);
        outputStream = new ByteArrayOutputStream();
    }

    @AfterEach
    void cleanUp() throws IOException {
        delete(fileA, false);
        delete(fileB,false);
        delete(dirA, true);
        delete(dirB, true);

        inputStream.close();
        outputStream.close();
    }

    private String getFileNameStr(Path path) {
        return path.getFileName().toString();
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
    void run_RenameFileOverwrite_ShouldNotThrow() {
        String[] args = {getFileNameStr(fileA), getFileNameStr(fileB)};
        assertDoesNotThrow(() -> mvApplication.run(args, inputStream, outputStream));
        assertFalse(Files.exists(fileA));
        assertTrue(Files.exists(fileB));
    }

    @Test
    void run_MoveFileIntoDirectoryOverwrite_ShouldNotThrow() {
        String[] args = {getFileNameStr(fileA), getFileNameStr(dirA)};
        assertDoesNotThrow(() -> mvApplication.run(args, inputStream, outputStream));
        Path filePath = dirA.resolve(getFileNameStr(fileA));
        assertTrue(Files.exists(filePath) && Files.isRegularFile(filePath));
    }

    @Test
    void run_MoveFileIntoDirectoryNoOverwrite_ShouldNotThrow() {
        String[] args = {NO_OVERWRITE_FLAG, getFileNameStr(fileA), getFileNameStr(dirA)};
        assertDoesNotThrow(() -> mvApplication.run(args, inputStream, outputStream));
        Path filePath = dirA.resolve(getFileNameStr(fileA));
        assertTrue(Files.exists(filePath) && Files.isRegularFile(filePath));
    }

    @Test
    void run_MoveTwoFilesIntoDirectoryOverwrite_ShouldNotThrow() {
        String[] args = {NO_OVERWRITE_FLAG, getFileNameStr(fileA), getFileNameStr(fileB), getFileNameStr(dirA)};
        assertDoesNotThrow(() -> mvApplication.run(args, inputStream, outputStream));
        Path fileAPath = dirA.resolve(getFileNameStr(fileA));
        Path fileBPath = dirA.resolve(getFileNameStr(fileB));
        assertTrue(Files.exists(fileAPath) && Files.isRegularFile(fileAPath));
        assertTrue(Files.exists(fileBPath) && Files.isRegularFile(fileBPath));
    }

    @Test
    void run_MoveDirectoryIntoAnother_ShouldNotThrow() {
        String[] args = {getFileNameStr(dirB), getFileNameStr(dirA)};
        assertDoesNotThrow(() -> mvApplication.run(args, inputStream, outputStream));
        Path dirBPath = dirA.resolve(getFileNameStr(dirB));
        assertTrue(Files.exists(dirBPath) && Files.isDirectory(dirBPath));
    }

    @Test
    void run_WithNullArguments_ThrowsException() {
        String expected = MV_EXCEPTION_PREFIX + ERR_NULL_ARGS;
        Throwable thrown = assertThrows(MvException.class, () -> mvApplication.run(null, null, null));
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    void run_WithMissingFileArgument_ThrowsException() {
        String expected = MV_EXCEPTION_PREFIX + ERR_MISSING_ARG;
        String[] args = {getFileNameStr(fileA)};
        Throwable thrown = assertThrows(MvException.class, () -> mvApplication.run(args, null, null));
        assertEquals(expected, thrown.getMessage());
    }
}
