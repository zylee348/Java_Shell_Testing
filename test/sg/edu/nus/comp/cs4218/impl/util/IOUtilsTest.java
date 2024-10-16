package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;

public class IOUtilsTest {
    private static final String VALID_FILE = "test" + StringUtils.fileSeparator() + "resources"
            + StringUtils.fileSeparator() + "testSample" + StringUtils.fileSeparator() + "FolderWithFilesOnly"
            + StringUtils.fileSeparator() + "A.txt";
    private static final String INVALID_FILE = "test" + StringUtils.fileSeparator() + "resources"
            + StringUtils.fileSeparator() + "testSample" + StringUtils.fileSeparator()
            + "FolderWithFilesOnly" + StringUtils.fileSeparator() + "invalid.txt";

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(INVALID_FILE));
    }

    @Test
    public void openInputStream_ValidFilePath_NotThrowException() {
        try {
            IOUtils.openInputStream(VALID_FILE);
            assertTrue(true);
        } catch (ShellException e) {
            fail("Input Stream throw Exception");
        }

    }

    @Test
    void openInputStream_InvalidFile_ThrowsFileNotFoundException() {
        ShellException exception = assertThrows(ShellException.class, () -> IOUtils.openInputStream(INVALID_FILE));
        assertEquals(new ShellException(ERR_FILE_NOT_FOUND).getMessage(), exception.getMessage());
    }

    @Test
    public void openOutputStream_ValidFilePath_NotThrowException() throws IOException {
        try (OutputStream outputStream = IOUtils.openOutputStream(VALID_FILE)) {
            assertTrue(true);
        } catch (ShellException e) {
            fail("Output Stream throws Exception");
        }
    }

    @Test
    public void openOutputStream_InvalidFilePath_CreateNewFile() throws IOException, ShellException {
        try(OutputStream outputStream = IOUtils.openOutputStream(INVALID_FILE)) {
            assertTrue(Files.exists(Paths.get(INVALID_FILE)));
        }
    }

    @Test
    public void closeInputStream_NullInput_DoesNotThrowException() {
        assertDoesNotThrow(() -> IOUtils.closeInputStream(null));
    }

    @Test
    public void closeInputStream_InputSystemIn_DoesNotThrowException() {
        assertDoesNotThrow(() -> IOUtils.closeInputStream(System.in));
    }

    @Test
    public void closeInputStream_NormalInputStream_DoesNotThrowException() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(VALID_FILE.getBytes());
        assertDoesNotThrow(() -> IOUtils.closeInputStream(inputStream));
    }

    @Test
    public void closeOutputStream_NullInput_DoesNotThrowException() {
        assertDoesNotThrow(() -> IOUtils.closeOutputStream(null));
    }

    @Test
    public void closeOutputStream_InputSystemOut_DoesNotThrowException() {
        assertDoesNotThrow(() -> IOUtils.closeOutputStream(System.out));
    }

    @Test
    public void closeOutputStream_NormalOutputStream_DoesNotThrowException() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        assertDoesNotThrow(() -> IOUtils.closeOutputStream(outputStream));
    }

    @Test
    void resolveFilePath_InputFilePath_ReturnFullPathName() {
        assertEquals(Paths.get(Environment.currentDirectory + StringUtils.fileSeparator() + VALID_FILE),
                IOUtils.resolveFilePath(VALID_FILE));
    }

    @Test
    public void getLinesFromInputStream_SingleLineInput_ReturnCorrectLine() throws IOException {
        //Given
        String testData = "Test1";
        InputStream inputStream = new ByteArrayInputStream(testData.getBytes());
        List<String> expected = new ArrayList<>(List.of(testData));

        // When
        List<String> result = IOUtils.getLinesFromInputStream(inputStream);

        // Then
        assertEquals(expected, result);
    }

    @Test
    public void getLinesFromInputStream_MultiLineInput_ReturnCorrectLines() throws IOException {
        //Given
        String testData = "Test1\nTest2\nTest3";
        InputStream inputStream = new ByteArrayInputStream(testData.getBytes());
        List<String> expected = new ArrayList<>(List.of("Test1", "Test2", "Test3"));

        // When
        List<String> result = IOUtils.getLinesFromInputStream(inputStream);

        // Then
        assertEquals(expected, result);
    }

    @Test
    void getLinesFromInputStream_EmptyInput_ReturnEmptyList() throws IOException {
        // Given
        String testData = "";
        InputStream inputStream = new ByteArrayInputStream(testData.getBytes());

        // When
        List<String> result = IOUtils.getLinesFromInputStream(inputStream);

        // Then
        assertTrue(result.isEmpty());
    }

}


