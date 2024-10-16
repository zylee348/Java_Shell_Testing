package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.CdException;
import sg.edu.nus.comp.cs4218.testutils.TestEnvironmentUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.fileSeparator;

public class CdApplicationPublicTest {
    private static final String TEST_INVALID_DIR_PATH = "invalid/testDir";
    private static String initialDirectory;
    private CdApplication cdApplication;
    private static final String CWD = System.getProperty("user.dir");
    private static final String TEST_DIR = CWD + fileSeparator() + "temp_cd";
    private static final Path TEST_DIR_PATH = Paths.get(TEST_DIR).toAbsolutePath();

    @AfterEach
    void tearDown() throws IOException, NoSuchFieldException, IllegalAccessException {
        Files.deleteIfExists(TEST_DIR_PATH);
        Environment.currentDirectory = CWD;
    }

    @BeforeEach
    void setUp() throws IOException, NoSuchFieldException, IllegalAccessException {
        cdApplication = new CdApplication();
        Environment.currentDirectory = TEST_DIR;
        Files.createDirectory(TEST_DIR_PATH);
    }

    @Test
    void changeToDirectory_ValidPath_CorrectlyChangesEnvironment() throws NoSuchFieldException,
            IllegalAccessException {
        assertDoesNotThrow(() -> cdApplication.changeToDirectory(TEST_DIR));
        assertEquals(TEST_DIR_PATH.toString(), Environment.currentDirectory);
    }

    @Test
    void changeToDirectory_InvalidPath_CorrectlyChangesEnvironment() throws NoSuchFieldException,
            IllegalAccessException {
        assertThrows(CdException.class, () -> cdApplication.changeToDirectory(TEST_INVALID_DIR_PATH));
        assertNotEquals(Paths.get(TEST_INVALID_DIR_PATH).toAbsolutePath().toString(),
                        Environment.currentDirectory);
    }
}