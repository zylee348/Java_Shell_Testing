package sg.edu.nus.comp.cs4218.impl.parser;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MvArgsParserTest {
    private final MvArgsParser mvArgsParser = new MvArgsParser();
    private static final String NO_OVERWRITE_FLAG = "-n";
    private static final String INVALID_FLAG = "-d";
    private static final String FILE_1 = "file1.txt";
    private static final String FILE_2 = "file2.txt";
    private static final String FILE_3 = "dir";

    @Test
    void parse_ValidArguments_DoesNotThrowException() {
        String[] args = {NO_OVERWRITE_FLAG, FILE_1, FILE_2};
        assertDoesNotThrow(() -> mvArgsParser.parse(args));
    }

    @Test
    void parse_InvalidArguments_ThrowsInvalidArgumentsException() {
        String[] args = {INVALID_FLAG, FILE_1, FILE_2};
        Throwable thrown = assertThrows(InvalidArgsException.class, () -> mvArgsParser.parse(args));
        String expected = ArgsParser.ILLEGAL_FLAG_MSG + "d";
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    void isOverwrite_HasNoOverwriteFlag_ReturnsFalse() throws InvalidArgsException {
        String[] args = {NO_OVERWRITE_FLAG, FILE_1, FILE_2};
        mvArgsParser.parse(args);
        assertEquals(false, mvArgsParser.isOverwrite());
    }

    @Test
    void isOverwrite_HasNoNoOverwriteFlag_ReturnsTrue() throws InvalidArgsException {
        String[] args = {FILE_1, FILE_2};
        mvArgsParser.parse(args);
        assertEquals(true, mvArgsParser.isOverwrite());
    }

    @Test
    void getSourceFiles_NoFiles_ReturnsEmptyArray() throws InvalidArgsException {
        String[] args = {};
        mvArgsParser.parse(args);
        assertArrayEquals(new String[0], mvArgsParser.getSourceFiles());
    }

    @Test
    void getSourceFiles_OneFile_ReturnsEmptyArray() throws InvalidArgsException {
        String[] args = {FILE_1};
        mvArgsParser.parse(args);
        assertArrayEquals(new String[0], mvArgsParser.getSourceFiles());
    }

    @Test
    void getSourceFiles_TwoFiles_ReturnsCorrectSourceFile() throws InvalidArgsException {
        String[] args = {FILE_1, FILE_2};
        mvArgsParser.parse(args);
        String[] expected = {FILE_1};
        assertArrayEquals(expected, mvArgsParser.getSourceFiles());
    }

    @Test
    void getSourceFiles_ThreeFiles_ReturnsCorrectSourceFile() throws InvalidArgsException {
        String[] args = {FILE_1, FILE_2, FILE_3};
        mvArgsParser.parse(args);
        String[] expected = {FILE_1, FILE_2};
        assertArrayEquals(expected, mvArgsParser.getSourceFiles());
    }

    @Test
    void getDestFile_NoFiles_ReturnsNull() throws InvalidArgsException {
        String[] args = {};
        mvArgsParser.parse(args);
        assertEquals(null, mvArgsParser.getDestFile());
    }

    @Test
    void getDestFile_OneFile_ReturnsNull() throws InvalidArgsException {
        String[] args = {FILE_1};
        mvArgsParser.parse(args);
        assertEquals(null, mvArgsParser.getDestFile());
    }

    @Test
    void getDestFile_TwoFiles_ReturnsCorrectDestFile() throws InvalidArgsException {
        String[] args = {FILE_1, FILE_2};
        mvArgsParser.parse(args);
        String expected = FILE_2;
        assertEquals(expected, mvArgsParser.getDestFile());
    }

    @Test
    void getDestFile_ThreeFiles_ReturnsCorrectDestFile() throws InvalidArgsException {
        String[] args = {FILE_1, FILE_2, FILE_3};
        mvArgsParser.parse(args);
        String expected = FILE_3;
        assertEquals(expected, mvArgsParser.getDestFile());
    }
}
