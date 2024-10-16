package sg.edu.nus.comp.cs4218.impl.app;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.MkdirException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.app.MkdirApplication.ERR_ALREADY_EXISTS;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.fileSeparator;

public class MkdirApplicationTest {

    private MkdirApplication mkdirApplication;
    private static final String CWD = System.getProperty("user.dir");
    private static final String MKDIR_RESOURCES = fileSeparator() + "test" + fileSeparator() + "resources" + fileSeparator() + "app" + fileSeparator() + "mkdir";;

    @BeforeEach
    public void setUp() {
        mkdirApplication = new MkdirApplication();
        Environment.currentDirectory = CWD + MKDIR_RESOURCES;
        String folderPath = Environment.currentDirectory + fileSeparator();
        mkdirApplication.setLocation(folderPath);
    }

    @AfterEach
    public void tearDown() throws Exception {
        Environment.currentDirectory = CWD;
        mkdirApplication.setLocation(CWD);
    }

    private void deleteDirectoryIfExists(String dirName) throws Exception {
        Path path = Paths.get(Environment.currentDirectory + fileSeparator() + dirName);
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    @Test
    public void createFolder_validFolder_ShouldCreateFolder() throws Exception {
        String folderName = "testDir";
        deleteDirectoryIfExists(folderName);
        mkdirApplication.createFolder(folderName);
        assertTrue(Files.exists(Paths.get(Environment.currentDirectory + fileSeparator() + folderName)));
        deleteDirectoryIfExists(folderName); // Cleanup
    }
    @Test
    public void createFolder_duplicateFolder_ShouldThrowMkdirException() throws Exception {
        String folderName = "testDir";
        deleteDirectoryIfExists(folderName);
        mkdirApplication.createFolder(folderName);
        MkdirException actual = assertThrows(MkdirException.class, () -> mkdirApplication.createFolder(folderName));
        String expected = "mkdir: " + ERR_ALREADY_EXISTS + ": " + folderName;
        assertEquals(expected, actual.getMessage());
        deleteDirectoryIfExists(folderName); // Cleanup
    }
    @Test
    public void createFolder_withSpecialCharacterInName_ShouldCreateFolder() throws Exception {
        String folderName = "test@Folder";
        deleteDirectoryIfExists(folderName);
        mkdirApplication.createFolder(folderName);
        assertTrue(Files.exists(Paths.get(Environment.currentDirectory + fileSeparator() + folderName)));
        deleteDirectoryIfExists(folderName); // Cleanup
    }

    @Test
    public void createFolder_withSpaceInName_ShouldCreateFolder() throws Exception {
        String folderName = "test Folder";
        deleteDirectoryIfExists(folderName);
        mkdirApplication.createFolder(folderName);
        assertTrue(Files.exists(Paths.get(Environment.currentDirectory + fileSeparator() + folderName)));
        deleteDirectoryIfExists(folderName); // Cleanup
    }
    @Test
    public void createFolder_MultipleValidFolders_ShouldCreateFolders() throws Exception {
        String folderName1 = "testDir";
        String folderName2 = "testDir2";
        String folderName3 = "testDir3";
        String path = Environment.currentDirectory + fileSeparator();
        deleteDirectoryIfExists(folderName1);
        deleteDirectoryIfExists(folderName2);
        deleteDirectoryIfExists(folderName3);

        String[] args = new String[]{folderName1, folderName2, folderName3};
        mkdirApplication.createFolder(args);
        assertTrue(Files.exists(Paths.get(path + folderName1)));
        assertTrue(Files.exists(Paths.get(path + folderName2)));
        assertTrue(Files.exists(Paths.get(path + folderName3)));

        deleteDirectoryIfExists(folderName1);
        deleteDirectoryIfExists(folderName2);
        deleteDirectoryIfExists(folderName3);
    }
}

