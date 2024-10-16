package sg.edu.nus.comp.cs4218.impl.parser;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeeArgsParserTest {
    private final TeeArgsParser teeArgsParser = new TeeArgsParser();
    private static final String APPEND_FLAG = "-a";
    private static final String INVALID_FLAG = "-d";
    private static final String FILE_1 = "file1.txt";
    private static final String FILE_2 = "file2.txt";

    @Test
    void parse_ValidArguments_DoesNotThrowException() {
        String[] args = {APPEND_FLAG, FILE_1, FILE_2};
        assertDoesNotThrow(() -> teeArgsParser.parse(args));
    }

    @Test
    void parse_InvalidArguments_ThrowsInvalidArgumentsException() {
        String[] args = {INVALID_FLAG, FILE_1, FILE_2};
        Throwable thrown = assertThrows(InvalidArgsException.class, () -> teeArgsParser.parse(args));
        String expected = ArgsParser.ILLEGAL_FLAG_MSG + "d";
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    void isAppend_HasAppendFlag_ReturnsTrue() throws InvalidArgsException {
        String[] args = {APPEND_FLAG, FILE_1, FILE_2};
        teeArgsParser.parse(args);
        assertEquals(true, teeArgsParser.isAppend());
    }

    @Test
    void isAppend_HasNoAppendFlag_ReturnsFalse() throws InvalidArgsException {
        String[] args = {FILE_1, FILE_2};
        teeArgsParser.parse(args);
        assertEquals(false, teeArgsParser.isAppend());
    }

    @Test
    void getFileNames_HasFileNames_ReturnsFileNames() throws InvalidArgsException {
        String[] args = {FILE_1, FILE_2};
        teeArgsParser.parse(args);
        List<String> expected = List.of(FILE_1, FILE_2);
        assertEquals(expected, teeArgsParser.getFileNames());
    }
}
