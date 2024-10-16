package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_IS_DIR;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NON_EMPTY_DIR;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NO_OSTREAM;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_NULL_ARGS;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.RmException;

public class RmApplicationTest {
    private RmApplication app;
    private OutputStream stdout;
    private static final String TEMP_DIR = "temp-rm" + File.separator;
    private static final String TEMP_FILE = "temp.txt";

    @BeforeEach
    public void setUp() {
        app = new RmApplication();
        stdout = new ByteArrayOutputStream();
        Environment.currentDirectory = System.getProperty("user.dir");
    }

    @AfterEach
    public void clean() throws Exception {
        if (Files.exists(Paths.get(TEMP_FILE))) {
            Files.delete(Paths.get(TEMP_FILE));
        }

        if (Files.exists(Paths.get(TEMP_DIR))) {
            Files.walk(Paths.get(TEMP_DIR))
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }

    @Test
    void run_NullArgs_ThrowsNullArgsError() {
        RmException exception = assertThrows(RmException.class, () -> this.app.run(null, null, null));
        assertEquals(new RmException(ERR_NULL_ARGS).getMessage(), exception.getMessage());
    }

    @Test
    void run_NullStdout_ThrowsNullStdoutError() {
        String[] args = { "1" };
        RmException exception = assertThrows(RmException.class, () -> this.app.run(args, null, null));
        assertEquals(new RmException(ERR_NO_OSTREAM).getMessage(), exception.getMessage());
    }

    @Test
    void run_InvalidFlag_ThrowsInvalidFlagError() {
        String[] args = { "-b" };
        RmException exception = assertThrows(RmException.class, () -> this.app.run(args, null, stdout));
        assertEquals(new RmException("illegal option -- b").getMessage(), exception.getMessage());
    }

    @Test
    void remove_NonExistantFile_ThrowsFileNotFoundError() {
        RmException exception = assertThrows(RmException.class, () -> this.app.remove(false, false, "test.txt"));
        assertEquals(new RmException(ERR_FILE_NOT_FOUND).getMessage(), exception.getMessage());
    }

    @Test
    void remove_DirectoryNotRecursiveNotRemoveEmpty_ThrowsIsDirectoryError() throws IOException {
        Path tempPath = Paths.get(Environment.currentDirectory, TEMP_DIR);
        Files.createDirectory(tempPath);

        RmException exception = assertThrows(RmException.class,
                () -> this.app.remove(false, false, tempPath.toString()));
        assertEquals(new RmException(ERR_IS_DIR).getMessage(), exception.getMessage());
    }

    @Test
    void remove_DirectoryWithFileAndRemoveEmpty_ThrowsIsDirectoryError() throws Exception {
        Path tempPath = Paths.get(Environment.currentDirectory, TEMP_DIR);
        Files.createDirectory(tempPath);
        Files.createFile(Paths.get(TEMP_DIR + TEMP_FILE));

        RmException exception = assertThrows(RmException.class,
                () -> this.app.remove(true, false, tempPath.toString()));
        assertEquals(new RmException(ERR_NON_EMPTY_DIR).getMessage(), exception.getMessage());
    }

    @Test
    void remove_NestedDirectoryAndRemoveEmpty_ThrowsIsDirectoryError() throws Exception {
        Path tempPath = Paths.get(Environment.currentDirectory, TEMP_DIR);
        Files.createDirectories(Paths.get(TEMP_DIR + TEMP_DIR));

        RmException exception = assertThrows(RmException.class,
                () -> this.app.remove(true, false, tempPath.toString()));
        assertEquals(new RmException(ERR_NON_EMPTY_DIR).getMessage(), exception.getMessage());
    }

    @Test
    void remove_EmptyDirectoryAndRemoveEmpty_RemovesDirectory() throws Exception {
        Path tempPath = Paths.get(Environment.currentDirectory, TEMP_DIR);
        Files.createDirectory(tempPath);
        assertTrue(Files.exists(tempPath));

        this.app.remove(true, false, tempPath.toString());
        assertFalse(Files.exists(tempPath));
    }

    @Test
    void remove_FileAndRecursive_RemovesFileOnly() throws Exception {
        Path tempPath = Paths.get(Environment.currentDirectory, TEMP_FILE);
        Files.createFile(tempPath);

        assertTrue(Files.exists(tempPath));

        this.app.remove(false, true, tempPath.toString());
        assertFalse(Files.exists(tempPath));
    }

    @Test
    void remove_NestedDirectoryAndRecursive_RemovesDirectory() throws Exception {
        Path tempDir = Paths.get(Environment.currentDirectory, TEMP_DIR);
        Files.createDirectory(tempDir);
        Files.createFile(Paths.get(TEMP_DIR + TEMP_FILE));
        Files.createDirectory(Paths.get(TEMP_DIR + TEMP_DIR));
        Files.createFile(Paths.get(TEMP_DIR + TEMP_DIR + TEMP_FILE));

        assertTrue(Files.exists(tempDir));

        this.app.remove(false, true, tempDir.toString());
        assertFalse(Files.exists(tempDir));
    }
}
