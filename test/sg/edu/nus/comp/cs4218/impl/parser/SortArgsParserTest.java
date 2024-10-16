package sg.edu.nus.comp.cs4218.impl.parser;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SortArgsParserTest {

    private SortArgsParser parser;

    @BeforeEach
    void setUp() {
        parser = new SortArgsParser();
    }

    @Test
    void parse_WithNumericFlag_ShouldSetIsFirstWordNumberTrue() throws Exception {
        String[] args = {"-n", "file.txt"};
        parser.parse(args);
        assertTrue(parser.isFirstWordNumber(), "Flag '-n' should set isFirstWordNumber to true");
    }

    @Test
    void parse_WithReverseFlag_ShouldSetIsReverseOrderTrue() throws Exception {
        String[] args = {"-r", "file.txt"};
        parser.parse(args);
        assertTrue(parser.isReverseOrder(), "Flag '-r' should set isReverseOrder to true");
    }

    @Test
    void parse_WithCaseInsensitiveFlag_ShouldSetIsCaseIndependentTrue() throws Exception {
        String[] args = {"-f", "file.txt"};
        parser.parse(args);
        assertTrue(parser.isCaseIndependent(), "Flag '-f' should set isCaseIndependent to true");
    }

    @Test
    void parse_WithMultipleFlags_ShouldSetFlagsCorrectly() throws Exception {
        String[] args = {"-n", "-r", "-f", "file.txt"};
        parser.parse(args);
        assertTrue(parser.isFirstWordNumber(), "Flag '-n' should be recognized");
        assertTrue(parser.isReverseOrder(), "Flag '-r' should be recognized");
        assertTrue(parser.isCaseIndependent(), "Flag '-f' should be recognized");
    }

    @Test
    void parse_WithFlagsAndMultipleFiles_ShouldSetFlagsAndFileNamesCorrectly() throws Exception {
        String[] args = {"-n", "-r", "file1.txt", "file2.txt"};
        parser.parse(args);
        assertTrue(parser.isFirstWordNumber(), "Flag '-n' should be recognized");
        assertTrue(parser.isReverseOrder(), "Flag '-r' should be recognized");
        List<String> fileNames = parser.getFileNames();
        assertEquals(2, fileNames.size(), "Two files should be recognized");
        assertEquals("file1.txt", fileNames.get(0), "First file name should be 'file1.txt'");
        assertEquals("file2.txt", fileNames.get(1), "Second file name should be 'file2.txt'");
    }
}
