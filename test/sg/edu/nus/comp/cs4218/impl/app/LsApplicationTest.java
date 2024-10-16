package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.LsException;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


import static org.junit.jupiter.api.Assertions.*;

public class LsApplicationTest {
    private LsApplication lsApplication;
    private OutputStream stdout;
    private InputStream stdin;

    private final static String FOLDER_WITH_FILES_PATH = "test" + StringUtils.fileSeparator() + "resources" + StringUtils.fileSeparator() + "testSample" + StringUtils.fileSeparator() + "FolderWithFilesOnly";
    private final static String FOLDER_WITH_SUBFOLDERS_PATH = "test" + StringUtils.fileSeparator() + "resources" + StringUtils.fileSeparator() + "testSample" + StringUtils.fileSeparator() + "FolderWithInnerFolders";
    private final static String FOLDER_WITH_FILE_AND_FOLDER_PATH = "test" + StringUtils.fileSeparator() + "resources" + StringUtils.fileSeparator() + "testSample" + StringUtils.fileSeparator() + "FolderWithFilesAndFolders";
    private final static String FOLDER_A_PATH = FOLDER_WITH_SUBFOLDERS_PATH + StringUtils.fileSeparator() + "FolderA";
    private final static String FOLDER_B_PATH = FOLDER_WITH_SUBFOLDERS_PATH + StringUtils.fileSeparator() + "FolderB";
    private final static String FOLDER_ONE_PATH = FOLDER_WITH_FILE_AND_FOLDER_PATH + StringUtils.fileSeparator() + "Folder1";
    private final static String INVALID_PATH = "Invalid Dir1";
    private final static String ERROR_ONE_MSG = "ls: cannot access '"+INVALID_PATH+"': No such file or directory";

    @BeforeEach
    public void setUp() {
        lsApplication = new LsApplication();
        stdout = new ByteArrayOutputStream();
        stdin = null;
    }

    @AfterEach
    public void tearDown() throws Exception {
        stdout.close();
    }

    @Test
    public void run_NullArgument_ThrowsException() {
        assertThrows(LsException.class, () -> lsApplication.run(null, stdin, stdout));
    }

    @Test
    public void run_NoArguments_DisplayCorrectResultInCurrentDir() throws AbstractApplicationException {
        //Given
        String[] args = {};
        //When
        lsApplication.run(args, stdin, stdout);
        //Then
        assertNotEquals("", stdout.toString());
    }

    @Test
    public void run_NoOptionWithSingleFolderPath_DisplayCorrectResult() throws AbstractApplicationException {
        //Given
        String[] args = {FOLDER_WITH_FILES_PATH};
        //When
        lsApplication.run(args, stdin, stdout);
        //Then
        assertEquals(FOLDER_WITH_FILES_PATH + ":\nA.txt\nB\n", stdout.toString());
    }

    @Test
    public void run_NoOptionWithMultiFolderPath_DisplayCorrectResult()
            throws AbstractApplicationException {
        //Given
        String[] args = {FOLDER_WITH_FILES_PATH, FOLDER_WITH_SUBFOLDERS_PATH};
        //When
        lsApplication.run(args, stdin, stdout);
        //Then
        assertEquals(FOLDER_WITH_FILES_PATH + ":\nA.txt\nB\n\n" +
                FOLDER_WITH_SUBFOLDERS_PATH + ":\nFolderA\nFolderB\n", stdout.toString());
    }

    @Test
    public void run_NoOptionWithInvalidDirectoryPath_DisplayErrorMessage()
            throws AbstractApplicationException {
        //Given
        String[] args = {INVALID_PATH};
        //When
        lsApplication.run(args, stdin, stdout);
        //Then
        assertEquals(ERROR_ONE_MSG + "\n", stdout.toString());
    }

    @Test
    public void run_NoOptionWithInvalidPathThenValidPath_DisplayErrorMessageThenFilesFromValidPath()
            throws AbstractApplicationException {
        //Given
        String[] args = {INVALID_PATH, FOLDER_WITH_FILES_PATH};
        //When
        lsApplication.run(args, stdin, stdout);
        //Then
        assertEquals(ERROR_ONE_MSG +"\n\n" + FOLDER_WITH_FILES_PATH +":\nA.txt\nB\n",
                stdout.toString());
    }

    @Test
    public void run_NoOptionWithValidPathThenInvalidPath_DisplayFilesFromValidPathThenErrorMessage()
            throws AbstractApplicationException {
        //Given
        String[] args = {FOLDER_WITH_FILES_PATH, INVALID_PATH};
        //When
        lsApplication.run(args, stdin, stdout);
        //Then
        assertEquals(FOLDER_WITH_FILES_PATH +":\nA.txt\nB\n\n" + ERROR_ONE_MSG + "\n", stdout.toString());
    }

    @Test
    public void run_InvalidOption_DisplayErrorMessage() throws AbstractApplicationException {
        //Given
        String[] args = {FOLDER_WITH_FILES_PATH, "-I"};
        //When
        LsException exception = assertThrows(LsException.class, () -> lsApplication.run(args, stdin, stdout));
        //Then
        assertEquals("ls: illegal option -- I", exception.getMessage());
    }

    @Test
    public void run_InvalidOptionWithValidOption_DisplayErrorMessage() throws AbstractApplicationException {
        //Given
        String[] args = {"-R", FOLDER_WITH_FILES_PATH, "-I"};
        //When
        LsException exception = assertThrows(LsException.class, () -> lsApplication.run(args, stdin, stdout));
        //Then
        assertEquals("ls: illegal option -- I", exception.getMessage());
    }

    @Test
    public void run_RecursiveOptionWithSinglePathFilesOnlyFolder_DisplayCorrectResult()
            throws AbstractApplicationException {
        //Given
        String[] args = {"-R", FOLDER_WITH_FILES_PATH};
        //When
        lsApplication.run(args, stdin, stdout);
        //Then
        assertEquals(FOLDER_WITH_FILES_PATH + ":\nA.txt\nB\n", stdout.toString());
    }

    @Test
    public void run_RecursiveOptionWithSinglePathFolderWithSubFolders_DisplayCorrectResult()
            throws AbstractApplicationException {
        //Given
        String[] args = {"-R", FOLDER_WITH_SUBFOLDERS_PATH};
        //When
        lsApplication.run(args, stdin, stdout);
        //Then
        assertEquals(FOLDER_WITH_SUBFOLDERS_PATH +":\nFolderA\nFolderB\n\n" +
                FOLDER_A_PATH +":\nInsideFolderA-1.txt\nInsideFolderA-2\n\n" +
                FOLDER_B_PATH +":\nInsideFolderB-1\n", stdout.toString());
    }

    @Test
    public void run_RecursiveOptionWithMultiPathFolderInsideFoldersThenFileOnlyFolder_DisplayCorrectResult()
            throws AbstractApplicationException {
        //Given
        String[] args = {"-R", FOLDER_WITH_SUBFOLDERS_PATH, FOLDER_WITH_FILES_PATH};
        //When
        lsApplication.run(args, stdin, stdout);
        //Then
        assertEquals(FOLDER_WITH_SUBFOLDERS_PATH +":\nFolderA\nFolderB\n\n" +
                FOLDER_A_PATH +":\nInsideFolderA-1.txt\nInsideFolderA-2\n\n" +
                FOLDER_B_PATH +":\nInsideFolderB-1\n\n" + FOLDER_WITH_FILES_PATH +":\nA.txt\nB\n", stdout.toString());
    }

    @Test
    public void run_RecursiveOptionWithMultiPathFileOnlyFoldersThenFoldersInsideFolders_DisplayCorrectResult()
            throws AbstractApplicationException {
        //Given
        String[] args = {"-R", FOLDER_WITH_FILES_PATH, FOLDER_WITH_SUBFOLDERS_PATH};
        //When
        lsApplication.run(args, stdin, stdout);
        //Then
        assertEquals(FOLDER_WITH_FILES_PATH + ":\nA.txt\nB\n\n" +
                FOLDER_WITH_SUBFOLDERS_PATH +":\nFolderA\nFolderB\n\n" +
                FOLDER_A_PATH +":\nInsideFolderA-1.txt\nInsideFolderA-2\n\n" +
                FOLDER_B_PATH +":\nInsideFolderB-1\n", stdout.toString());
    }

    @Test
    public void run_ArgumentWithROptionAndMultiPathInDiffSequence_DisplayCorrectResult()
            throws AbstractApplicationException {
        //Given
        String[] args = {INVALID_PATH, FOLDER_WITH_SUBFOLDERS_PATH, "-R"};
        //When
        lsApplication.run(args, stdin, stdout);
        //Then
        assertEquals(ERROR_ONE_MSG +"\n\n" +
                FOLDER_WITH_SUBFOLDERS_PATH +":\nFolderA\nFolderB\n\n" +
                FOLDER_A_PATH +":\nInsideFolderA-1.txt\nInsideFolderA-2\n\n" +
                FOLDER_B_PATH +":\nInsideFolderB-1\n", stdout.toString());
    }

    @Test
    public void run_SortOptionWithSinglePathFilesOnlyFolder_DisplayCorrectResult()
            throws AbstractApplicationException {
        //Given
        String[] args = {"-X", FOLDER_WITH_FILES_PATH};
        //When
        lsApplication.run(args, stdin, stdout);
        //Then
        assertEquals(FOLDER_WITH_FILES_PATH +":\nB\nA.txt\n", stdout.toString());
    }

    @Test
    public void run_SortOptionWithSinglePathFolderWithSubFolders_DisplayCorrectResult()
            throws AbstractApplicationException {
        //Given
        String[] args = {"-X", FOLDER_WITH_SUBFOLDERS_PATH};
        //When
        lsApplication.run(args, stdin, stdout);
        //Then
        assertEquals(FOLDER_WITH_SUBFOLDERS_PATH +":\nFolderA\nFolderB\n", stdout.toString());
    }

    @Test
    public void run_SortOptionWithSinglePathFolderWithMixedFoldersAndFiles_DisplayCorrectResult()
            throws AbstractApplicationException {
        //Given
        String[] args = {"-X", FOLDER_WITH_FILE_AND_FOLDER_PATH};
        //When
        lsApplication.run(args, stdin, stdout);
        //Then
        assertEquals(FOLDER_WITH_FILE_AND_FOLDER_PATH +":\nFile1\nFolder1\nFile2.txt\n", stdout.toString());
    }

    @Test
    public void run_SortOptionWithMultiPath_DisplayCorrectResult()
            throws AbstractApplicationException {
        //Given
        String[] args = {"-X", FOLDER_WITH_SUBFOLDERS_PATH,
                FOLDER_WITH_FILES_PATH};
        //When
        lsApplication.run(args, stdin, stdout);
        //Then
        assertEquals(FOLDER_WITH_SUBFOLDERS_PATH +":\nFolderA\nFolderB\n\n" +
                FOLDER_WITH_FILES_PATH +":\nB\nA.txt\n", stdout.toString());
    }

    @Test
    public void run_BothOptionWithSinglePathFilesOnlyFolder_DisplayCorrectResult()
            throws AbstractApplicationException {
        //Given
        String[] args = {"-R", "-X", FOLDER_WITH_FILES_PATH};
        //When
        lsApplication.run(args, stdin, stdout);
        //Then
        assertEquals(FOLDER_WITH_FILES_PATH +":\nB\nA.txt\n", stdout.toString());
    }

    @Test
    public void run_BothOptionWithSinglePathFolderWithSubFolders_DisplayCorrectResult()
            throws AbstractApplicationException {
        //Given
        String[] args = {"-R", "-X", FOLDER_WITH_SUBFOLDERS_PATH};
        //When
        lsApplication.run(args, stdin, stdout);
        //Then
        assertEquals(FOLDER_WITH_SUBFOLDERS_PATH +":\nFolderA\nFolderB\n\n" +
                FOLDER_A_PATH +":\nInsideFolderA-2\nInsideFolderA-1.txt\n\n" +
                FOLDER_B_PATH +":\nInsideFolderB-1\n", stdout.toString());
    }

    @Test
    public void run_BothOptionWithSinglePathFolderWithFilesAndFolders_DisplayCorrectResult()
            throws AbstractApplicationException {
        //Given
        String[] args = {"-R", "-X", FOLDER_WITH_FILE_AND_FOLDER_PATH};
        //When
        lsApplication.run(args, stdin, stdout);
        //Then
        assertEquals(FOLDER_WITH_FILE_AND_FOLDER_PATH +":\nFile1\nFolder1\nFile2.txt\n\n" +
                FOLDER_ONE_PATH +":\n", stdout.toString());
    }
    @Test
    public void run_BothOptionNotSeparated_DisplaySameResultAsSeparatedOptions()
            throws AbstractApplicationException {
        //Given
        String[] args = {"-XR", FOLDER_WITH_FILE_AND_FOLDER_PATH};
        //When
        lsApplication.run(args, stdin, stdout);
        //Then
        assertEquals(FOLDER_WITH_FILE_AND_FOLDER_PATH +":\nFile1\nFolder1\nFile2.txt\n\n" +
                FOLDER_ONE_PATH +":\n", stdout.toString());
    }

    @Test
    public void run_BothOptionWithDup_DisplaySameResultAsSingleOptions()
            throws AbstractApplicationException {
        //Given
        String[] args = {"-X", "-R", "-X", FOLDER_WITH_FILE_AND_FOLDER_PATH};
        //When
        lsApplication.run(args, stdin, stdout);
        //Then
        assertEquals(FOLDER_WITH_FILE_AND_FOLDER_PATH +":\nFile1\nFolder1\nFile2.txt\n\n" +
                FOLDER_ONE_PATH +":\n", stdout.toString());
    }

    @Test
    void listFolderContent_RecursiveIsTrueInFilesOnlyFolder_ReturnAllFilenameInFolder()
            throws AbstractApplicationException {
        // Given
        Boolean isRecursive = true;
        Boolean isSortByExt = false;
        String[] folderName = new String[]{FOLDER_WITH_FILES_PATH};

        //When
        String listOfItemName = lsApplication.listFolderContent(isRecursive, isSortByExt, folderName);

        assertEquals(FOLDER_WITH_FILES_PATH +":\nA.txt\nB", listOfItemName);
    }

    @Test
    void listFolderContent_RecursiveIsTrueInFolderWithSubFolders_ReturnAllFoldersAndFoldersContent()
            throws AbstractApplicationException {
        // Given
        Boolean isRecursive = true;
        Boolean isSortByExt = false;
        String[] folderName = new String[]{FOLDER_WITH_SUBFOLDERS_PATH};

        //When
        String listOfItemName = lsApplication.listFolderContent(isRecursive, isSortByExt, folderName);

        assertEquals(FOLDER_WITH_SUBFOLDERS_PATH +":\nFolderA\nFolderB\n\n" +
                FOLDER_A_PATH +":\nInsideFolderA-1.txt\nInsideFolderA-2\n\n" +
                FOLDER_B_PATH +":\nInsideFolderB-1", listOfItemName);
    }

    @Test
    void listFolderContent_RecursiveIsTrueInMultiPath_ReturnAllFoldersAndFileName()
            throws AbstractApplicationException {
        // Given
        Boolean isRecursive = true;
        Boolean isSortByExt = false;
        String[] folderName = new String[]{FOLDER_WITH_SUBFOLDERS_PATH,
                FOLDER_WITH_FILES_PATH};

        //When
        String listOfItemName = lsApplication.listFolderContent(isRecursive, isSortByExt, folderName);

        assertEquals(FOLDER_WITH_SUBFOLDERS_PATH +":\nFolderA\nFolderB\n\n" +
                FOLDER_A_PATH +":\nInsideFolderA-1.txt\nInsideFolderA-2\n\n" +
                FOLDER_B_PATH +":\nInsideFolderB-1\n\n" + FOLDER_WITH_FILES_PATH +":\nA.txt\nB", listOfItemName);
    }

    @Test
    void listFolderContent_SortIsTrueInFilesOnlyFolder_FileSortWithNonExtensionThenWithExtension()
            throws AbstractApplicationException {
        // Given
        Boolean isRecursive = false;
        Boolean isSortByExt = true;
        String[] folderName = new String[]{FOLDER_WITH_FILES_PATH};

        //When
        String listOfItemName = lsApplication.listFolderContent(isRecursive, isSortByExt, folderName);

        assertEquals(FOLDER_WITH_FILES_PATH + ":\nB\nA.txt", listOfItemName);
    }

    @Test
    void listFolderContent_SortIsTrueInFolderWithInnerFolders_FolderSortCorrectly()
            throws AbstractApplicationException {
        // Given
        Boolean isRecursive = false;
        Boolean isSortByExt = true;
        String[] folderName = new String[]{FOLDER_WITH_SUBFOLDERS_PATH};

        //When
        String listOfItemName = lsApplication.listFolderContent(isRecursive, isSortByExt, folderName);

        assertEquals(FOLDER_WITH_SUBFOLDERS_PATH +":\nFolderA\nFolderB", listOfItemName);
    }

    @Test
    void listFolderContent_SortIsTrueInFolderWithMixFoldersAndFiles_ResultSortCorrectly()
            throws AbstractApplicationException {
        // Given
        Boolean isRecursive = false;
        Boolean isSortByExt = true;
        String[] folderName = new String[]{FOLDER_WITH_FILE_AND_FOLDER_PATH};

        //When
        String listOfItemName = lsApplication.listFolderContent(isRecursive, isSortByExt, folderName);

        assertEquals(FOLDER_WITH_FILE_AND_FOLDER_PATH +":\nFile1\nFolder1\nFile2.txt", listOfItemName);
    }

    @Test
    void listFolderContent_RecursiveAndSortIsTrueInFilesOnlyFolder_ReturnCorrectResult()
            throws AbstractApplicationException {
        // Given
        Boolean isRecursive = true;
        Boolean isSortByExt = true;
        String[] folderName = new String[]{FOLDER_WITH_FILES_PATH};

        //When
        String listOfItemName = lsApplication.listFolderContent(isRecursive, isSortByExt, folderName);

        assertEquals(FOLDER_WITH_FILES_PATH +":\nB\nA.txt", listOfItemName);
    }

    @Test
    void listFolderContent_RecursiveAndSortIsTrueInFolderWithInnerFolders_ReturnCorrectResult()
            throws AbstractApplicationException {
        // Given
        Boolean isRecursive = true;
        Boolean isSortByExt = true;
        String[] folderName = new String[]{FOLDER_WITH_SUBFOLDERS_PATH};

        //When
        String listOfItemName = lsApplication.listFolderContent(isRecursive, isSortByExt, folderName);

        assertEquals(FOLDER_WITH_SUBFOLDERS_PATH +":\nFolderA\nFolderB\n\n" +
                FOLDER_A_PATH +":\nInsideFolderA-2\nInsideFolderA-1.txt\n\n" +
                FOLDER_B_PATH +":\nInsideFolderB-1", listOfItemName);
    }

    @Test
    void listFolderContent_BothOptionsFalseWithSingleFilePathProvided_DisplayResultCorrectly()
            throws AbstractApplicationException {
        // Given
        Boolean isRecursive = false;
        Boolean isSortByExt = false;
        String[] folderName = new String[]{FOLDER_WITH_FILES_PATH+"/A.txt"};

        //When
        String listOfItemName = lsApplication.listFolderContent(isRecursive, isSortByExt, folderName);

        assertEquals(FOLDER_WITH_FILES_PATH +"/A.txt", listOfItemName);
    }

    @Test
    void listFolderContent_BothOptionsFalseWithSingleFolderPathProvided_DisplayResultCorrectly()
            throws AbstractApplicationException {
        // Given
        Boolean isRecursive = false;
        Boolean isSortByExt = false;
        String[] folderName = new String[]{FOLDER_WITH_FILES_PATH};

        //When
        String listOfItemName = lsApplication.listFolderContent(isRecursive, isSortByExt, folderName);

        assertEquals(FOLDER_WITH_FILES_PATH +":\nA.txt\nB", listOfItemName);
    }

    @Test
    void listFolderContent_BothOptionsFalseWithSingleEmptyFolderPath_DisplayEmptyResult()
            throws AbstractApplicationException {
        // Given
        Boolean isRecursive = false;
        Boolean isSortByExt = false;
        String[] folderName = new String[]{FOLDER_ONE_PATH};

        //When
        String listOfItemName = lsApplication.listFolderContent(isRecursive, isSortByExt, folderName);

        assertEquals(FOLDER_ONE_PATH +":", listOfItemName);
    }

    @Test
    void listFolderContent_BothOptionsFalseWithMultipleFilePathProvided_DisplayResultCorrectly()
            throws AbstractApplicationException {
        // Given
        Boolean isRecursive = false;
        Boolean isSortByExt = false;
        String[] folderName = new String[]{FOLDER_WITH_FILES_PATH+"/A.txt",
                FOLDER_WITH_FILES_PATH+"/B"};

        //When
        String listOfItemName = lsApplication.listFolderContent(isRecursive, isSortByExt, folderName);

        assertEquals(FOLDER_WITH_FILES_PATH +"/A.txt\n\n" +
                FOLDER_WITH_FILES_PATH +"/B", listOfItemName);
    }

    @Test
    void listFolderContent_BothOptionsFalseWithMultipleFolderPathProvided_DisplayResultCorrectly()
            throws AbstractApplicationException {
        // Given
        Boolean isRecursive = false;
        Boolean isSortByExt = false;
        String[] folderName = new String[]{FOLDER_WITH_FILES_PATH,
                FOLDER_WITH_SUBFOLDERS_PATH};

        //When
        String listOfItemName = lsApplication.listFolderContent(isRecursive, isSortByExt, folderName);

        assertEquals(FOLDER_WITH_FILES_PATH +":\nA.txt\nB\n\n" +
                FOLDER_WITH_SUBFOLDERS_PATH +":\nFolderA\nFolderB", listOfItemName);
    }

}
