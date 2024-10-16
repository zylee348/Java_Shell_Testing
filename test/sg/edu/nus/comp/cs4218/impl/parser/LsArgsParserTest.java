package sg.edu.nus.comp.cs4218.impl.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.InvalidArgsException;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LsArgsParserTest {
    private static final String DIRECTORY_COMMAND = "ls " + StringUtils.fileSeparator() + "DirPath";
    private LsArgsParser lsParser;
    @BeforeEach
    void setUp() {
        lsParser = new LsArgsParser();
    }

    @Test
    public void isRecursive_NoRecursiveOption_ReturnFalse() throws InvalidArgsException {
        String[] args = {DIRECTORY_COMMAND};
        lsParser.parse(args);
        assertFalse(lsParser.isRecursive());
    }

    @Test
    public void isRecursive_WithRecursiveOption_ReturnTrue() throws InvalidArgsException {
        String[] args = {DIRECTORY_COMMAND, "-R"};
        lsParser.parse(args);
        assertTrue(lsParser.isRecursive());
    }

    @Test
    public void isSortByExt_NoSortOption_ReturnFalse() throws InvalidArgsException {
        String[] args = {DIRECTORY_COMMAND};
        lsParser.parse(args);
        assertFalse(lsParser.isSortByExt());
    }

    @Test
    public void isSortByExt_WithSortOption_ReturnTrue() throws InvalidArgsException {
        String[] args = {DIRECTORY_COMMAND, "-X"};
        lsParser.parse(args);
        assertTrue(lsParser.isSortByExt());
    }

    @Test
    public void getDirectories_NoOptionAndSinglePath_ReturnListOfCorrectDirectory() throws InvalidArgsException {
        String[] args = {DIRECTORY_COMMAND};
        lsParser.parse(args);
        List<String> expected = new ArrayList<>(List.of(DIRECTORY_COMMAND));
        assertEquals(expected, lsParser.getDirectories());
    }

    @Test
    public void getDirectories_NoOptionWithMultiPath_ReturnListOfCorrectDirectory()
            throws InvalidArgsException {
        String[] args = {DIRECTORY_COMMAND, DIRECTORY_COMMAND};
        lsParser.parse(args);
        List<String> expected = new ArrayList<>(List.of(DIRECTORY_COMMAND, DIRECTORY_COMMAND));
        assertEquals(expected, lsParser.getDirectories());
    }

    @Test
    public void getDirectories_WithOptionsAndSinglePath_ReturnListOfDirectoryOnly() throws InvalidArgsException {
        String[] args = {DIRECTORY_COMMAND, "-R", "-X"};
        lsParser.parse(args);
        List<String> expected = new ArrayList<>(List.of(DIRECTORY_COMMAND));
        assertEquals(expected, lsParser.getDirectories());
    }

    @Test
    public void getDirectories_WithOptionsAndMultiPath_ReturnListOfDirectoryOnly() throws InvalidArgsException {
        String[] args = {DIRECTORY_COMMAND,DIRECTORY_COMMAND, "-R", "-X"};
        lsParser.parse(args);
        List<String> expected = new ArrayList<>(List.of(DIRECTORY_COMMAND, DIRECTORY_COMMAND));
        assertEquals(expected, lsParser.getDirectories());
    }


}
