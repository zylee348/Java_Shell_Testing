package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;

public class FileSystemUtilsTest {
    static final String CWD = System.getProperty("user.dir");
    static final String TESTING_PATH = CHAR_FILE_SEP + "test" + CHAR_FILE_SEP + "resources" + CHAR_FILE_SEP + "util";
    static final String SAMPLE_FILE = "sample.txt";
    static final String SAMPLE_DIR = "sample";
    static final String NEW_FILE = "new.txt";
    static final String NEW_DIR = "new";
    static final String NEW_SUB_DIR = "newsubdir";
    static final String NONEXISTENT_FILE ="nonexistent";

    @BeforeEach
    void setCurrentDirectory() {
        Environment.currentDirectory += TESTING_PATH;
    }

    @AfterEach
    void reset() {
        Environment.currentDirectory = CWD;
    }

    @Test
    void getAbsolutePathName_Filename_ReturnCorrectPath() {
        String expectedPath = Environment.currentDirectory + CHAR_FILE_SEP + SAMPLE_FILE;
        assertEquals(expectedPath, FileSystemUtils.getAbsolutePathName(SAMPLE_FILE));
    }

    @Test
    void isSubDirectory_ExistingDirWithSubDir_ReturnsTrue() {
        String newDirPath = Environment.currentDirectory + CHAR_FILE_SEP + NEW_DIR;
        String newSubDirPath = newDirPath + CHAR_FILE_SEP + NEW_SUB_DIR;

        File newDir = new File(newDirPath);
        File newSubDir = new File(newSubDirPath);

        newDir.mkdir();
        newSubDir.mkdir();
        assertTrue(FileSystemUtils.isSubDirectory(NEW_DIR, NEW_DIR + CHAR_FILE_SEP + NEW_SUB_DIR));
        newSubDir.delete();
        newDir.delete();
    }

    @Test
    void isSubDirectory_TwoSeparateExistingDir_ReturnsFalse() {
        String newDirPath = Environment.currentDirectory + CHAR_FILE_SEP + NEW_DIR;
        File newDir1 = new File(newDirPath);
        newDir1.mkdir();
        assertFalse(FileSystemUtils.isSubDirectory(NEW_DIR, SAMPLE_DIR));
        newDir1.delete();
    }

    @Test
    void isSubDirectory_ExistingDirAndExistingFile_ReturnsFalse() {
        assertFalse(FileSystemUtils.isSubDirectory(SAMPLE_FILE, SAMPLE_DIR));
    }

    @Test
    void joinPath_SingleFileName_ReturnsCorrectPath() {
        assertEquals(NEW_FILE + CHAR_FILE_SEP, FileSystemUtils.joinPath(NEW_FILE));
    }

    @Test
    void joinPath_MultipleFileNames_ReturnsCorrectPath() {
        assertEquals(NEW_DIR +  CHAR_FILE_SEP + NEW_SUB_DIR
                + CHAR_FILE_SEP + NEW_FILE + CHAR_FILE_SEP, FileSystemUtils.joinPath(NEW_DIR, NEW_SUB_DIR, NEW_FILE));
    }

    @Test
    void joinPath_NoFileName_ReturnsCorrectPath() {
        assertEquals("" + CHAR_FILE_SEP, FileSystemUtils.joinPath());
    }

    @Test
    void isDirectory_Directory_ReturnsTrue() {
        String newDirPath = Environment.currentDirectory + CHAR_FILE_SEP + NEW_DIR;
        File newDir = new File(newDirPath);
        newDir.mkdir();
        assertTrue(FileSystemUtils.isDirectory(newDirPath));
        newDir.delete();
    }

    @Test
    void isDirectory_NotDirectory_ReturnsFalse() {
        String newFilePath = Environment.currentDirectory + CHAR_FILE_SEP + NEW_FILE;
        File newFile = new File(newFilePath);

        assertFalse(FileSystemUtils.isDirectory(newFilePath));
        newFile.delete();
    }

    @Test
    void exists_FileDoesNotExist_ReturnsFalse() {
        String nonExistentPath = Environment.currentDirectory + CHAR_FILE_SEP + NONEXISTENT_FILE;
        assertFalse(FileSystemUtils.exists(nonExistentPath));
    }
}
