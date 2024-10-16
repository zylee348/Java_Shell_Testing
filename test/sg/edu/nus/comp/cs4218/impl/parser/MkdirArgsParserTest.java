package sg.edu.nus.comp.cs4218.impl.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;

import static org.junit.jupiter.api.Assertions.*;

public class MkdirArgsParserTest {

    private MkdirArgsParser parser;

    @BeforeEach
    void setUp() {
        parser = new MkdirArgsParser();
    }

    @Test
    void shouldCreateParents_FlagPresent_ReturnsTrue() throws InvalidArgsException {
        String[] args = {"-p", "dir"};
        parser.parse(args);
        assertTrue(parser.shouldCreateParents());
    }

    @Test
    void shouldCreateParents_FlagNotPresent_ReturnsFalse() throws InvalidArgsException {
        String[] args = {"dir"};
        parser.parse(args);
        assertFalse(parser.shouldCreateParents());
    }

    @Test
    void getDirectories_WithValidDirectory_ReturnsDirectory() throws InvalidArgsException {
        String[] args = {"dir"};
        parser.parse(args);
        assertEquals(1, parser.getDirectories().size());
        assertEquals("dir", parser.getDirectories().get(0));
    }

    @Test
    void getDirectories_WithMultipleDirectories_ReturnsDirectories() throws InvalidArgsException {
        String[] args = {"dir1", "dir2"};
        parser.parse(args);
        assertEquals(2, parser.getDirectories().size());
        assertEquals("dir1", parser.getDirectories().get(0));
        assertEquals("dir2", parser.getDirectories().get(1));
    }

    @Test
    void parse_WithInvalidFlag_ThrowsException() {
        String[] args = {"-x", "dir"};
        assertThrows(InvalidArgsException.class, () -> parser.parse(args));
    }
}
