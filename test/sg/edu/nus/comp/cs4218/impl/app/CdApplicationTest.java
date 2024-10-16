package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.io.TempDir;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.CdException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.fileSeparator;

class CdApplicationTest {

    private CdApplication cdApplication;
    private static final String CWD = System.getProperty("user.dir");
    private static final String CD_RESOURCES = fileSeparator() + "test" + fileSeparator() + "resources" + fileSeparator() + "app" + fileSeparator() + "cd";;

    @TempDir
    private File tempDir;

    @BeforeEach
    void setUp() {
        cdApplication = new CdApplication();
        // Set the initial directory to a known state
        Environment.currentDirectory = CWD + CD_RESOURCES;
    }

    @AfterEach
    void tearDown() {
        Environment.currentDirectory = CWD;
    }

    @Test
    void changeToDirectory_withValidRelativePath_ShouldUpdateCurrentDirectory() throws Exception {
        String validPath ="testCd";
        Path newDir = Paths.get(Environment.currentDirectory, validPath);
        Files.createDirectories(newDir);

        cdApplication.changeToDirectory(validPath);

        // Resolve and normalize the expected path
        String expectedPath = newDir.toAbsolutePath().normalize().toString();
        System.out.println("Expected Path: " + expectedPath);
        System.out.println("Actual Current Directory: " + Environment.currentDirectory);

        assertEquals(expectedPath, Environment.currentDirectory);
    }

    @Test
    void changeToDirectory_withInvalidPath_ShouldThrowException() {
        String invalidPath = "nonexistentpath";
        assertThrows(CdException.class, () -> cdApplication.changeToDirectory(invalidPath));
    }
    @Test
    @EnabledOnOs(OS.WINDOWS)
    void changeToDirectory_withValidAbsolutePath_ShouldUpdateCurrentDirectory() throws Exception {
        Path newDir = tempDir.toPath().resolve("newAbsDir");
        Files.createDirectories(newDir);
        String absolutePath = newDir.toAbsolutePath().toString();
        cdApplication.changeToDirectory(absolutePath);
        assertEquals(absolutePath, Environment.currentDirectory, "Current directory should be updated to the absolute path");
    }

    @Test
    void changeToDirectory_withRelativePath_ShouldUpdateCurrentDirectory() throws Exception {
        Path relativePath = tempDir.toPath().resolve("subdir");
        assertTrue(relativePath.toFile().mkdir());

        cdApplication.changeToDirectory(relativePath.toString());
        assertEquals(relativePath.toAbsolutePath().toString(), Environment.currentDirectory);
    }
    @Test
    void changeToDirectory_withNonDirectoryPath_ShouldThrowException() throws Exception {
        File file = new File(tempDir, "file.txt");
        assertTrue(file.createNewFile());
        assertThrows(CdException.class, () -> cdApplication.changeToDirectory(file.getAbsolutePath()));
    }
    @Test
    void changeToDirectory_toParentDirectory_ShouldUpdateCurrentDirectory() throws Exception {
        Path parentDir = Paths.get(Environment.currentDirectory).getParent();
        cdApplication.changeToDirectory("..");
        assertEquals(parentDir.toString(), Environment.currentDirectory);
    }
    @Test
    void changeToDirectory_toCurrentDirectory_ShouldNotChangeCurrentDirectory() throws Exception {
        Environment.currentDirectory = CWD;
        String dir = CWD;
        cdApplication.changeToDirectory(".");
        assertEquals(dir, Environment.currentDirectory);
    }
    @Test
    void changeToDirectory_withComplexRelativePath_ShouldUpdateCurrentDirectory() throws Exception {
        // Assuming Environment.currentDirectory is already set to a subdirectory
        Path someDir = Paths.get(Environment.currentDirectory).getParent().getParent().resolve("some/other/directory");
        Files.createDirectories(someDir); // Ensures the target directory exists
        System.out.println();
        cdApplication.changeToDirectory("../../some/other/directory");

        String expectedPath = someDir.toAbsolutePath().normalize().toString();
        assertEquals(expectedPath, Environment.currentDirectory);
    }
    @Test
    void changeToDirectory_withPathNormalization_ShouldResolveCorrectly() throws Exception {
        Path baseDir = Paths.get(Environment.currentDirectory);
        Path folder2 = baseDir.resolve("folder2");
        Files.createDirectories(folder2); // Ensures the target directory exists
        cdApplication.changeToDirectory("./testCd/../folder2");
        String expectedPath = folder2.toAbsolutePath().normalize().toString();
        assertEquals(expectedPath, Environment.currentDirectory);
    }
    @Test
    void changeToDirectory_withTrailingSlash_ShouldUpdateCurrentDirectory() throws Exception {
        Path targetDir = Paths.get(Environment.currentDirectory).resolve("testDir");
        Files.createDirectories(targetDir); // Ensures the target directory exists

        cdApplication.changeToDirectory("testDir/"); // Note the trailing slash

        String expectedPath = targetDir.toAbsolutePath().normalize().toString();
        assertEquals(expectedPath, Environment.currentDirectory);
    }
    @Test
    void run_withoutOutputStream_ShouldThrowException() throws IOException {
        String[] args = {"someDir"};
        assertThrows(CdException.class, () -> cdApplication.run(args, System.in, null));
    }

    @Test
    void run_withoutInputStream_ShouldThrowException() {
        String[] args = {"someDir"};
        assertThrows(CdException.class, () -> cdApplication.run(args, null, System.out));
    }

    @Test
    void run_withNullArgs_ShouldThrowException() {
        assertThrows(CdException.class, () -> cdApplication.run(null, System.in, System.out));
    }

    @Test
    void run_withTooManyArguments_ShouldThrowException() {
        String[] args = {"dir1", "dir2"};
        assertThrows(CdException.class, () -> cdApplication.run(args, System.in, System.out));
    }

    @Test
    void run_withNoArguments_ShouldChangeToUserDirectory() throws Exception {
        String[] args = {};
        cdApplication.run(args, System.in, System.out);
        assertEquals(System.getProperty("user.dir"), Environment.currentDirectory);
    }

}

