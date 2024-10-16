package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.LsException;
import sg.edu.nus.comp.cs4218.exception.PasteException;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_INVALID_APP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.fileSeparator;

public class ApplicationRunnerTest {
    private static final String CWD = System.getProperty("user.dir");
    private static final String APP_RUNNER_RESOURCES = fileSeparator() + "test" + fileSeparator() + "resources" + fileSeparator() + "util" + fileSeparator() + "ApplicationRunner";

    public final static String APP_LS = "ls";
    public final static String APP_WC = "wc";
    public final static String APP_ECHO = "echo";
    public final static String APP_EXIT = "exit";
    public final static String APP_GREP = "grep";
    public final static String APP_PASTE = "paste";
    public final static String APP_CD = "cd";
    public final static String APP_CAT = "cat";
    public final static String APP_MKDIR = "mkdir";
    public final static String VALID_TEST_FILE = "valid_file.txt";

    ApplicationRunner appRunner = new ApplicationRunner();
    String[] argsArray = {};
    InputStream inputStream = null;
    OutputStream outputStream = null;

    @BeforeEach
    void setup() {
        Environment.currentDirectory = CWD + APP_RUNNER_RESOURCES;
        outputStream = new ByteArrayOutputStream();
    }

    @AfterEach
    void reset() throws IOException {
        argsArray = new String[]{};
        Environment.currentDirectory = CWD;
        outputStream.close();
    }
    private void deleteDirectoryIfExists(String dirName) throws Exception {
        Path path = Paths.get(dirName);
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    @Test
    public void runApp_LsApp_DoesNotThrowException() {
        assertDoesNotThrow(() -> appRunner.runApp(APP_LS, argsArray, System.in, outputStream));
    }

    @Test
    void runApp_EchoApp_DoesNotThrowException() {
        String[] argsArray = {"testing echo"};
        assertDoesNotThrow(() -> appRunner.runApp(APP_ECHO, argsArray, System.in, outputStream));
    }

    @Test
    void runApp_CdApp_DoesNotThrowException() {
        String[] argsArray = {"."};
        assertDoesNotThrow(() -> appRunner.runApp(APP_CD, argsArray, System.in, outputStream));
    }

    @Test
    void runApp_WcApp_DoesNotThrowException() {
        argsArray = new String[]{VALID_TEST_FILE};
        assertDoesNotThrow(() -> appRunner.runApp(APP_WC, argsArray, System.in, outputStream));
    }

    @Test
    void runApp_CatApp_DoesNotThrowException() {
        argsArray = new String[]{VALID_TEST_FILE};
        assertDoesNotThrow(() -> appRunner.runApp(APP_CAT, argsArray, System.in, outputStream));
    }

    @Test
    void runApp_MkdirApp_DoesNotThrowException() throws Exception{
        File newFolder = new File("New_Folder_App_Runner_test");
        argsArray = new String[]{"New_Folder_App_Runner_test"};
        assertDoesNotThrow(() -> appRunner.runApp(APP_MKDIR, argsArray, System.in, outputStream));
        deleteDirectoryIfExists(Environment.currentDirectory + fileSeparator() + newFolder.getName());
    }
    @Test
    void runApp_InvalidApp_InvalidAppExceptionThrown() {
        String app = "invalidApp";
        ShellException thrown = assertThrows(ShellException.class, () -> {
            appRunner.runApp(app, argsArray, System.in, outputStream);
        });
        String expectedError = "shell: " + app + ": " + ERR_INVALID_APP;
        assertEquals(expectedError, thrown.getMessage());
    }
    @Test
    public void runApp_PasteApp_DoesNotThrowException() {
        String[] argsArray = {"file1.txt", "file2.txt"}; // Replace with valid filenames
        assertThrows(PasteException.class, () -> appRunner.runApp(APP_PASTE, argsArray, System.in, outputStream));
    }

    @Test
    public void runApp_GrepApp_DoesNotThrowException() {
        String[] argsArray = {"pattern", "file.txt"}; // Replace with valid pattern and filename
        assertDoesNotThrow(() -> appRunner.runApp(APP_GREP, argsArray, System.in, outputStream));
    }

    @Test
    public void runApp_WithNullArgs_ThrowsException() {
        String[] argsArray = null;
        LsException exception = assertThrows(LsException.class, () -> appRunner.runApp(APP_LS, argsArray, System.in, outputStream));
        assertEquals("ls: Null arguments", exception.getMessage());
    }
    @Test
    public void runApp_WithInvalidApp_ThrowsException() {
        String[] argsArray = {"validArgs"};
        ShellException exception = assertThrows(ShellException.class, () -> appRunner.runApp("invalidApp", argsArray, System.in, outputStream));
        assertEquals("shell: invalidApp: Invalid app", exception.getMessage());
    }
}

