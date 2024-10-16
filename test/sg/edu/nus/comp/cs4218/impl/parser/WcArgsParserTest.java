package sg.edu.nus.comp.cs4218.impl.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WcArgsParserTest {
    private WcArgsParser parser;
    public static final String PARSER_FLAG_BYTE = "-c";

    public static final String PARSER_FLAG_LINE = "-l";

    public static final String PARSER_FLAG_WORD = "-w";
    public static final String INVALID = "invalid";
    public static final char FLAG_IS_BYTE_COUNT = 'c';

    public static final char FLAG_IS_LINE_COUNT = 'l';

    public static final char FLAG_IS_WORD_COUNT = 'w';
    @BeforeEach
    void setUp() {
        parser = new WcArgsParser();
    }

    @Test
    void parse_ByteFlagPresent_ReturnsTrue() throws InvalidArgsException {
        String[] args = {PARSER_FLAG_BYTE, "dir"};
        parser.parse(args);
        assertTrue(parser.isByteCount());
    }

    @Test
    void parse_LineFlagPresent_ReturnsTrue() throws InvalidArgsException {
        String[] args = {PARSER_FLAG_LINE, "dir"};
        parser.parse(args);
        assertTrue(parser.isLineCount());
    }
    @Test
    void parse_WordFlagPresent_ReturnsTrue() throws InvalidArgsException {
        String[] args = {PARSER_FLAG_WORD, "dir"};
        parser.parse(args);
        assertTrue(parser.isWordCount());
    }

    @Test
    void parse_ByteFlagAbsent_ReturnsFalse() throws InvalidArgsException {
        String[] args = {INVALID, "dir"};
        parser.parse(args);
        assertFalse(parser.isByteCount());
    }

    @Test
    void parse_LineFlagAbsent_ReturnsFalse() throws InvalidArgsException {
        String[] args = {INVALID, "dir"};
        parser.parse(args);
        assertFalse(parser.isLineCount());
    }
    @Test
    void parse_WordFlagAbsent_ReturnsFalse() throws InvalidArgsException {
        String[] args = {INVALID, "dir"};
        parser.parse(args);
        assertFalse(parser.isWordCount());
    }

    @Test
    void parse_GetFileNames_ReturnsFileNames() throws InvalidArgsException {
        String[] args = {PARSER_FLAG_WORD, "valid_file.txt valid_file2.txt"};
        parser.parse(args);
        List<String> expectedFilenames = new ArrayList<>(Arrays.asList("valid_file.txt valid_file2.txt"));
        List<String> actualFilenames = parser.getFileNames();
        assertEquals(expectedFilenames, actualFilenames);
    }

}
