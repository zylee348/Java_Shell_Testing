package sg.edu.nus.comp.cs4218.impl.parser;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UniqArgsParserTest {
    private final UniqArgsParser uniqArgsParser = new UniqArgsParser();
    private static final String COUNT_FLAG = "-c";
    private static final String REPEATED_FLAG = "-d";
    private static final String ALL_REPEATED_FLAG = "-D";
    private static final String INVALID_FLAG = "-s";
    private static final String FILE_1 = "file1.txt";
    private static final String FILE_2 = "file2.txt";
    private static final String FILE_3 = "file3.txt";
    @Test
    void parse_ValidArguments_DoesNotThrowException() {
        String[] args = {COUNT_FLAG, REPEATED_FLAG, ALL_REPEATED_FLAG, FILE_1, FILE_2};
        assertDoesNotThrow(() -> uniqArgsParser.parse(args));
    }

    @Test
    void parse_InvalidArguments_ThrowsInvalidArgumentsException() {
        String[] args = {INVALID_FLAG, FILE_1, FILE_2};
        Throwable thrown = assertThrows(InvalidArgsException.class, () -> uniqArgsParser.parse(args));
        String expected = ArgsParser.ILLEGAL_FLAG_MSG + "s";
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    void isCount_HasCountFlag_ReturnsTrue() throws InvalidArgsException {
        String[] args = {COUNT_FLAG, FILE_1, FILE_2};
        uniqArgsParser.parse(args);
        assertEquals(true, uniqArgsParser.isCount());
    }

    @Test
    void isCount_HasNoCountFlag_ReturnsFalse() throws InvalidArgsException {
        String[] args = {FILE_1, FILE_2};
        uniqArgsParser.parse(args);
        assertEquals(false, uniqArgsParser.isCount());
    }

    @Test
    void isRepeated_HasRepeatedFlag_ReturnsTrue() throws InvalidArgsException {
        String[] args = {REPEATED_FLAG, FILE_1, FILE_2};
        uniqArgsParser.parse(args);
        assertEquals(true, uniqArgsParser.isRepeated());
    }

    @Test
    void isRepeated_HasNoRepeatedFlag_ReturnsFalse() throws InvalidArgsException {
        String[] args = {FILE_1, FILE_2};
        uniqArgsParser.parse(args);
        assertEquals(false, uniqArgsParser.isRepeated());
    }

    @Test
    void isAllRepeated_HasAllRepeatedFlag_ReturnsTrue() throws InvalidArgsException {
        String[] args = {ALL_REPEATED_FLAG, FILE_1, FILE_2};
        uniqArgsParser.parse(args);
        assertEquals(true, uniqArgsParser.isAllRepeated());
    }

    @Test
    void isAllRepeated_HasNoAllRepeatedFlag_ReturnsFalse() throws InvalidArgsException {
        String[] args = {FILE_1, FILE_2};
        uniqArgsParser.parse(args);
        assertEquals(false, uniqArgsParser.isAllRepeated());
    }
    @Test
    void getFileNames_HasFileNames_ReturnsFileNames() throws InvalidArgsException {
        String[] args = {FILE_1, FILE_2};
        uniqArgsParser.parse(args);
        String[] expected = {FILE_1, FILE_2};
        assertArrayEquals(expected, uniqArgsParser.getFileNames());
    }

    @Test
    void getFileNames_HasMoreThanTwoFileNames_ThrowsException() throws InvalidArgsException {
        String[] args = {FILE_1, FILE_2, FILE_3};
        uniqArgsParser.parse(args);
        Throwable thrown = assertThrows(IllegalArgumentException.class, uniqArgsParser::getFileNames);
        String expected = "Expected 2 arguments, received 3 arguments";
        assertEquals(expected, thrown.getMessage());
    }
}
