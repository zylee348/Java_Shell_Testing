package sg.edu.nus.comp.cs4218.impl.parser;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PasteArgsParserTest {
    private final PasteArgsParser pasteArgsParser = new PasteArgsParser();
    private static final String SERIAL_FLAG = "-s";
    private static final String INVALID_FLAG = "-d";
    private static final String FILE_1 = "file1.txt";
    private static final String FILE_2 = "file2.txt";

    @Test
    void parse_ValidArguments_DoesNotThrowException() {
        String[] args = {SERIAL_FLAG, FILE_1, FILE_2};
        assertDoesNotThrow(() -> pasteArgsParser.parse(args));
    }

    @Test
    void parse_InvalidArguments_ThrowsInvalidArgumentsException() {
        String[] args = {INVALID_FLAG, FILE_1, FILE_2};
        Throwable thrown = assertThrows(InvalidArgsException.class, () -> pasteArgsParser.parse(args));
        String expected = ArgsParser.ILLEGAL_FLAG_MSG + "d";
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    void isSerial_HasSerialFlag_ReturnsTrue() throws InvalidArgsException {
        String[] args = {SERIAL_FLAG, FILE_1, FILE_2};
        pasteArgsParser.parse(args);
        assertEquals(true, pasteArgsParser.isSerial());
    }

    @Test
    void isSerial_HasNoSerialFlag_ReturnsFalse() throws InvalidArgsException {
        String[] args = {FILE_1, FILE_2};
        pasteArgsParser.parse(args);
        assertEquals(false, pasteArgsParser.isSerial());
    }

    @Test
    void getFileNames_HasFileNames_ReturnsFileNames() throws InvalidArgsException {
        String[] args = {FILE_1, FILE_2};
        pasteArgsParser.parse(args);
        List<String> expected = List.of(FILE_1, FILE_2);
        assertEquals(expected, pasteArgsParser.getFileNames());
    }
}
