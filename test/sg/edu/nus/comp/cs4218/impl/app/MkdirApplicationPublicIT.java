package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.MkdirException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.fileSeparator;


public class MkdirApplicationPublicIT { //NOPMD

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
    void run_NullInput_ThrowsException() {
        assertThrows(MkdirException.class, () -> {
            mkdirApplication.run(null, null, null);
        });
    }

    @Test
    void run_EmptyInput_Success() throws Exception {
        String[] args = new String[0];
        assertThrows(MkdirException.class, () -> {
            mkdirApplication.run(args, null, null);
        });
    }
    @Test
    public void run_NoArguments_ThrowsMkdirException() {
        MkdirException thrown = assertThrows(MkdirException.class, () -> {
            mkdirApplication.run(new String[]{}, null, null);
        });
        assertTrue(thrown.getMessage().contains(MkdirApplication.ERR_NO_FOLDERS));
    }

    @Test
    public void run_InvalidFolderName_ShouldThrowSyntaxException() {
        String invalidFolderName = "/InvalidName";
        String[] args = { invalidFolderName };
        Exception thrown = assertThrows(MkdirException.class, () -> {
            mkdirApplication.run(args, null, null);
        });
        assertTrue(thrown.getMessage().contains(MkdirApplication.ERR_SYNTAX));
    }

    @Test
    public void run_CreateDirectoryWithParents_ShouldCreateDirectoryWithParents() throws Exception {
        String[] args = {"-p", "parent/child/grandchild"};
        String filePath = Environment.currentDirectory + fileSeparator();
        deleteDirectoryIfExists("parent/child/grandchild");
        deleteDirectoryIfExists("parent/child");
        deleteDirectoryIfExists("parent");
        mkdirApplication.run(args, null, null);
        assertTrue(Files.exists(Paths.get(filePath + "parent")), "Parent directory should be created");
        assertTrue(Files.exists(Paths.get(filePath + "parent/child")), "Child directory should be created");
        assertTrue(Files.exists(Paths.get(filePath + "parent/child/grandchild")), "Grandchild directory should be created");
        deleteDirectoryIfExists("parent/child/grandchild");
        deleteDirectoryIfExists("parent/child");
        deleteDirectoryIfExists("parent");
    }
    @Test
    public void run_CreateDirectoryWithParentsWithoutFlag_ShouldThrowException() {
        String[] args = {"", "parent/child/grandchild"};
        Exception thrown = assertThrows(MkdirException.class, () -> {
            mkdirApplication.run(args, null, null);
        });
        assertTrue(thrown.getMessage().contains(MkdirApplication.ERR_SYNTAX));
    }
    @Test
    void run_TwoNewDirectoryInput_Success() throws Exception {
        String[] args = new String[2];
        String folderName1 = "testdir";
        String folderName2 = "testdir2";
        deleteDirectoryIfExists(folderName1);
        deleteDirectoryIfExists(folderName2);
        String path = Environment.currentDirectory + fileSeparator();
        args[0] = folderName1;
        args[1] = folderName2;
        mkdirApplication.run(args, null, null);
        assertTrue(Files.exists(Paths.get(path + folderName1)));
        assertTrue(Files.exists(Paths.get(path + folderName2)));
        deleteDirectoryIfExists(folderName1);
        deleteDirectoryIfExists(folderName2);
    }
    @Test
    void run_OneNewDirectoryInput_Success() throws Exception {
        String[] args = new String[1];
        String folderName1 = "testdir";
        args[0] = folderName1;
        deleteDirectoryIfExists(folderName1);
        String path = Environment.currentDirectory + fileSeparator();
        mkdirApplication.run(args, null, null);
        assertTrue(Files.exists(Paths.get(path + folderName1)));
        deleteDirectoryIfExists(folderName1);
    }
}
