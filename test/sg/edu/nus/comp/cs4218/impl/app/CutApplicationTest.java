package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.CutException;

public class CutApplicationTest {
    private CutApplication app;
    private OutputStream stdout;
    private InputStream stdin;
    private static final String TEMP_DIR = "temp-cut" + File.separator;
    private static final String TEMP_FILE = "temp.txt";
    private static Path tempPath;

    private static String testString = "abcdefghijklmnop";
    private static String multiLineString = "abcde\n12345\nqwerty";

    private static String sepPosString = "abd";
    private static String outOfRangeString = "jklmnop";

    private static String testFile = "file1.txt";

    @BeforeAll
    static void createTemp() throws NoSuchFieldException, IllegalAccessException {
        tempPath = Paths.get(Environment.currentDirectory, TEMP_DIR);
    }

    @BeforeEach
    public void setUp() throws IOException {
        app = new CutApplication();
        stdin = System.in;
        stdout = new ByteArrayOutputStream();
        Path tempPath = Paths.get(Environment.currentDirectory, TEMP_DIR);
        Files.createDirectory(tempPath);
    }

    @AfterEach
    public void clean() throws Exception {
        if (Files.exists(Paths.get(TEMP_FILE))) {
            Files.delete(Paths.get(TEMP_FILE));
        }

        if (Files.exists(Paths.get(TEMP_DIR))) {
            Files.walk(Paths.get(TEMP_DIR))
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }

    @Test
    void run_NullArgs_ThrowsNullArgsError() {
        CutException exception = assertThrows(CutException.class, () -> this.app.run(null, null, null));
        assertEquals(new CutException(ERR_NULL_ARGS).getMessage(), exception.getMessage());
    }

    @Test
    void run_NullStdout_ThrowsNullStdoutError() {
        String[] args = { "1" };
        CutException exception = assertThrows(CutException.class, () -> this.app.run(args, null, null));
        assertEquals(new CutException(ERR_NO_OSTREAM).getMessage(), exception.getMessage());
    }

    @Test
    void run_InvalidFlag_ThrowsInvalidFlagError() {
        String[] args = { "-i" };
        CutException exception = assertThrows(CutException.class, () -> this.app.run(args, null, stdout));
        assertEquals(new CutException("illegal option -- i").getMessage(), exception.getMessage());
    }

    @Test
    void run_NullInputAndNoFiles_ThrowsNoInputError() {
        String[] args = { "1" };
        CutException exception = assertThrows(CutException.class, () -> this.app.run(args, null, stdout));
        assertEquals(new CutException(ERR_NO_INPUT).getMessage(), exception.getMessage());
    }

    @Test
    void run_NoExpression_ThrowsNoExprError() {
        String[] args = { "-b" };
        CutException exception = assertThrows(CutException.class, () -> this.app.run(args, stdin, stdout));
        assertEquals(new CutException(ERR_NO_EXP).getMessage(), exception.getMessage());
    }

    @Test
    void run_EmptyExpression_ThrowsNoExprError() {
        String[] args = { "-b", "" };
        CutException exception = assertThrows(CutException.class, () -> this.app.run(args, stdin, stdout));
        assertEquals(new CutException(ERR_NO_EXP).getMessage(), exception.getMessage());
    }

    @Test
    void run_multipleFlags_ThrowsInvalidFlagError() {
        String[] args = { "-b", "-c", "1" };
        CutException exception = assertThrows(CutException.class, () -> this.app.run(args, stdin, stdout));
        assertEquals(new CutException(ERR_INVALID_FLAG).getMessage(), exception.getMessage());
    }

    @Test
    void getCutRanges_SingleNumberZero_ThrowsInvalidIndexError() {
        CutException exception = assertThrows(CutException.class, () -> this.app.getCutRanges("0"));
        assertEquals(new CutException(ERR_INVALID_INDEX).getMessage(),
                exception.getMessage());
    }

    @Test
    void getCutRanges_SingleNumberNegative_ThrowsInvalidFlagError() throws Exception {
        CutException exception = assertThrows(CutException.class, () -> this.app.getCutRanges("-1"));
        assertEquals(new CutException(ERR_INVALID_REGEX).getMessage(),
                exception.getMessage());
    }

    @Test
    void getCutRanges_SingleNumber_ReturnsValidArray() throws Exception {
        List<int[]> cutRanges = this.app.getCutRanges("12");
        assertEquals(1, cutRanges.size());
        assertArrayEquals(new int[] { 12, 12 }, cutRanges.get(0));
    }

    @Test
    void getCutRanges_CommaSeparatedZero_ThrowsInvalidIndexError() {
        CutException exception = assertThrows(CutException.class, () -> this.app.getCutRanges("1,0,2"));
        assertEquals(new CutException(ERR_INVALID_INDEX).getMessage(),
                exception.getMessage());
    }

    @Test
    void getCutRanges_CommaSeparatedNegative_ThrowsInvalidRegexError() {
        CutException exception = assertThrows(CutException.class, () -> this.app.getCutRanges("1,-2"));
        assertEquals(new CutException(ERR_INVALID_REGEX).getMessage(),
                exception.getMessage());
    }

    @Test
    void getCutRanges_CommaSeparatedPositive_ReturnsValidArrayRanges() throws Exception {
        List<int[]> cutRanges = this.app.getCutRanges("1,2,30");
        assertEquals(3, cutRanges.size());
        assertArrayEquals(new int[] { 1, 1 }, cutRanges.get(0));
        assertArrayEquals(new int[] { 2, 2 }, cutRanges.get(1));
        assertArrayEquals(new int[] { 30, 30 }, cutRanges.get(2));
    }

    @Test
    void getCutRanges_CommaSeparatedPositiveUnsorted_ReturnsSortedArrayRanges() throws Exception {
        List<int[]> cutRanges = this.app.getCutRanges("30,2,1");
        assertEquals(3, cutRanges.size());
        assertArrayEquals(new int[] { 1, 1 }, cutRanges.get(0));
        assertArrayEquals(new int[] { 2, 2 }, cutRanges.get(1));
        assertArrayEquals(new int[] { 30, 30 }, cutRanges.get(2));
    }

    @Test
    void getCutRanges_RangeNumberNegativeStart_ThrowsInvalidRegexError() {
        CutException exception = assertThrows(CutException.class, () -> this.app.getCutRanges("-1-2"));
        assertEquals(new CutException(ERR_INVALID_REGEX).getMessage(),
                exception.getMessage());
    }

    @Test
    void getCutRanges_RangeNumberNegativeEnd_ThrowsInvalidRegexError() {
        CutException exception = assertThrows(CutException.class, () -> this.app.getCutRanges("1--2"));
        assertEquals(new CutException(ERR_INVALID_REGEX).getMessage(),
                exception.getMessage());
    }

    @Test
    void getCutRanges_RangeNumberZero_ThrowsInvalidIndexError() {
        CutException exception = assertThrows(CutException.class, () -> this.app.getCutRanges("0-0"));
        assertEquals(new CutException(ERR_INVALID_INDEX).getMessage(),
                exception.getMessage());
    }

    @Test
    void getCutRanges_RangeNumberDecreasing_ThrowsDecRangeError() {
        CutException exception = assertThrows(CutException.class, () -> this.app.getCutRanges("5-1"));
        assertEquals(new CutException(ERR_DEC_RANGE).getMessage(),
                exception.getMessage());
    }

    @Test
    void getCutRanges_RangeNumberValid_ReturnsValidArrayRanges() throws Exception {
        List<int[]> cutRanges = this.app.getCutRanges("12-30");
        assertEquals(1, cutRanges.size());
        assertArrayEquals(new int[] { 12, 30 }, cutRanges.get(0));
    }

    @Test
    void cutFromFiles_PassInvalidFile_ShowsErrorFileNotFound() throws Exception {
        List<int[]> rangeList = Arrays.asList(new int[] { 1, 1 });
        String cutResults = this.app.cutFromFiles(true, false, rangeList,
                "invalid_file.txt");

        assertEquals("invalid_file.txt: No such file or directory" + STRING_NEWLINE, cutResults);
    }

    @Test
    void cutFromFiles_PassDirectory_ShowsErrorIsDirectoryFound() throws Exception {
        List<int[]> rangeList = Arrays.asList(new int[] { 1, 1 });
        String cutResults = this.app.cutFromFiles(true, false, rangeList,
                tempPath.toString());

        assertTrue(cutResults.contains("This is a directory"));
    }

    @Test
    void cutFromFiles_CharacterNumberList_ReturnsValidResult() throws Exception {
        Path tempFile = Paths.get(TEMP_DIR + TEMP_FILE);
        Files.createFile(tempFile);
        Files.write(tempFile, testString.getBytes());

        List<int[]> rangeList = Arrays.asList(new int[] { 1, 1 }, new int[] { 2, 2 }, new int[] { 4, 4 });
        String cutResults = this.app.cutFromFiles(true, false, rangeList,
                tempFile.toString());

        assertEquals(sepPosString + STRING_NEWLINE, cutResults);
    }

    @Test
    void cutFromFiles_CharacterNumberRange_ReturnsValidResult() throws Exception {
        Path tempFile = Paths.get(TEMP_DIR + TEMP_FILE);
        Files.createFile(tempFile);
        Files.write(tempFile, testString.getBytes());

        List<int[]> rangeList = Arrays.asList(new int[] { 1, 4 });
        String cutResults = this.app.cutFromFiles(true, false, rangeList,
                tempFile.toString());

        assertEquals("abcd" + STRING_NEWLINE, cutResults);
    }

    @Test
    void cutFromFiles_CharacterOutOfRange_ReturnsEmpty() throws Exception {
        Path tempFile = Paths.get(TEMP_DIR + TEMP_FILE);
        Files.createFile(tempFile);
        Files.write(tempFile, testString.getBytes());

        List<int[]> rangeList = Arrays.asList(new int[] { 20, 20 });
        String cutResults = this.app.cutFromFiles(true, false, rangeList,
                tempFile.toString());

        assertEquals("" + STRING_NEWLINE, cutResults);
    }

    @Test
    void cutFromFiles_CharacterHalfOutOfRange_ReturnsCapturedPortion() throws Exception {
        Path tempFile = Paths.get(TEMP_DIR + TEMP_FILE);
        Files.createFile(tempFile);
        Files.write(tempFile, testString.getBytes());

        List<int[]> rangeList = Arrays.asList(new int[] { 10, 20 });
        String cutResults = this.app.cutFromFiles(true, false, rangeList,
                tempFile.toString());

        assertEquals(outOfRangeString + STRING_NEWLINE, cutResults);
    }

    @Test
    void cutFromFiles_ByteNumberList_ReturnsValidResult() throws Exception {
        Path tempFile = Paths.get(TEMP_DIR + TEMP_FILE);
        Files.createFile(tempFile);
        Files.write(tempFile, testString.getBytes());

        List<int[]> rangeList = Arrays.asList(new int[] { 1, 1 }, new int[] { 2, 2 }, new int[] { 4, 4 });
        String cutResults = this.app.cutFromFiles(false, true, rangeList,
                tempFile.toString());

        assertEquals(sepPosString + STRING_NEWLINE, cutResults);
    }

    @Test
    void cutFromFiles_ByteNumberRange_ReturnsValidResult() throws Exception {
        Path tempFile = Paths.get(TEMP_DIR + TEMP_FILE);
        Files.createFile(tempFile);
        Files.write(tempFile, testString.getBytes());

        List<int[]> rangeList = Arrays.asList(new int[] { 1, 4 });
        String cutResults = this.app.cutFromFiles(false, true, rangeList,
                tempFile.toString());

        assertEquals("abcd" + STRING_NEWLINE, cutResults);
    }

    @Test
    void cutFromFiles_ByteOutOfRange_ReturnsEmpty() throws Exception {
        Path tempFile = Paths.get(TEMP_DIR + TEMP_FILE);
        Files.createFile(tempFile);
        Files.write(tempFile, testString.getBytes());

        List<int[]> rangeList = Arrays.asList(new int[] { 20, 20 });
        String cutResults = this.app.cutFromFiles(false, true, rangeList,
                tempFile.toString());

        assertEquals("" + STRING_NEWLINE, cutResults);
    }

    @Test
    void cutFromFiles_ByteHalfOutOfRange_ReturnsCapturedPortion() throws Exception {
        Path tempFile = Paths.get(TEMP_DIR + TEMP_FILE);
        Files.createFile(tempFile);
        Files.write(tempFile, testString.getBytes());

        List<int[]> rangeList = Arrays.asList(new int[] { 10, 20 });
        String cutResults = this.app.cutFromFiles(false, true, rangeList,
                tempFile.toString());

        assertEquals(outOfRangeString + STRING_NEWLINE, cutResults);
    }

    @Test
    void cutFromFiles_MultipleFiles_ReturnsNewlineSeparatedResult() throws Exception {
        Path file1 = Paths.get(TEMP_DIR + testFile);
        Files.createFile(file1);
        Files.write(file1, "abcdefgh".getBytes());
        Path file2 = Paths.get(TEMP_DIR + "file2.txt");
        Files.createFile(file2);
        Files.write(file2, "qwerty".getBytes());
        Path file3 = Paths.get(TEMP_DIR + "file3.txt");
        Files.createFile(file3);
        Files.write(file3, "12345678".getBytes());

        List<int[]> rangeList = Arrays.asList(new int[] { 1, 5 });
        String cutResults = this.app.cutFromFiles(true, false, rangeList,
                file1.toString(), file2.toString(), file3.toString());

        String expected = "abcde" + STRING_NEWLINE + "qwert" + STRING_NEWLINE +
                "12345" + STRING_NEWLINE;
        assertEquals(expected, cutResults);
    }

    @Test
    void cutFromFiles_CharacterMultipleLines_ReturnsEachOutputForEachLine() throws Exception {
        Path file1 = Paths.get(TEMP_DIR + testFile);
        Files.createFile(file1);
        Files.write(file1, multiLineString.getBytes());

        List<int[]> rangeList = Arrays.asList(new int[] { 1, 3 });
        String cutResults = this.app.cutFromFiles(true, false, rangeList,
                file1.toString());

        String expected = "abc" + STRING_NEWLINE + "123" + STRING_NEWLINE + "qwe" + STRING_NEWLINE;
        assertEquals(expected, cutResults);
    }

    @Test
    void cutFromFiles_CharacterMultipleLinesCommaSeparated_ReturnsEachOutputForEachLine() throws Exception {
        Path file1 = Paths.get(TEMP_DIR + testFile);
        Files.createFile(file1);
        Files.write(file1, multiLineString.getBytes());

        List<int[]> rangeList = Arrays.asList(new int[] { 1, 1 }, new int[] { 3, 3 }, new int[] { 9, 9 });
        String cutResults = this.app.cutFromFiles(true, false, rangeList,
                file1.toString());

        String expected = "ac" + STRING_NEWLINE + "13" + STRING_NEWLINE + "qe" + STRING_NEWLINE;
        assertEquals(expected, cutResults);
    }

    @Test
    void cutFromFiles_ByteMultipleLines_ReturnsEachOutputForEachLine() throws Exception {
        Path file1 = Paths.get(TEMP_DIR + testFile);
        Files.createFile(file1);
        Files.write(file1, multiLineString.getBytes());

        List<int[]> rangeList = Arrays.asList(new int[] { 1, 3 });
        String cutResults = this.app.cutFromFiles(false, true, rangeList,
                file1.toString());

        String expected = "abc" + STRING_NEWLINE + "123" + STRING_NEWLINE + "qwe" + STRING_NEWLINE;
        assertEquals(expected, cutResults);
    }

    @Test
    void cutFromFiles_ByteMultipleLinesCommaSeparated_ReturnsEachOutputForEachLine() throws Exception {
        Path file1 = Paths.get(TEMP_DIR + testFile);
        Files.createFile(file1);
        Files.write(file1, multiLineString.getBytes());

        List<int[]> rangeList = Arrays.asList(new int[] { 1, 1 }, new int[] { 3, 3 }, new int[] { 9, 9 });
        String cutResults = this.app.cutFromFiles(false, true, rangeList,
                file1.toString());

        String expected = "ac" + STRING_NEWLINE + "13" + STRING_NEWLINE + "qe" + STRING_NEWLINE;
        assertEquals(expected, cutResults);
    }

    @Test
    void cutFromStdin_CharacterNumberList_ReturnsValidResult() throws Exception {
        List<int[]> rangeList = Arrays.asList(new int[] { 1, 1 }, new int[] { 2, 2 }, new int[] { 4, 4 });

        InputStream inputStream = new ByteArrayInputStream("abcdefg".getBytes());
        String cutResults = this.app.cutFromStdin(true, false, rangeList,
                inputStream);

        assertEquals(sepPosString + STRING_NEWLINE, cutResults);
    }

    @Test
    void cutFromStdin_CharacterNumberRange_ReturnsValidResult() throws Exception {
        List<int[]> rangeList = Arrays.asList(new int[] { 1, 6 });
        InputStream inputStream = new ByteArrayInputStream("abcdefg".getBytes());
        String cutResults = this.app.cutFromStdin(true, false, rangeList,
                inputStream);

        assertEquals("abcdef" + STRING_NEWLINE, cutResults);
    }

    @Test
    void cutFromStdin_CharacterOutOfRange_ReturnsEmpty() throws Exception {
        List<int[]> rangeList = Arrays.asList(new int[] { 20, 20 });
        InputStream inputStream = new ByteArrayInputStream("werwrw".getBytes());
        String cutResults = this.app.cutFromStdin(true, false, rangeList,
                inputStream);

        assertEquals("" + STRING_NEWLINE, cutResults);
    }

    @Test
    void cutFromStdin_CharacterHalfOutOfRange_ReturnsCapturedPortion() throws Exception {
        List<int[]> rangeList = Arrays.asList(new int[] { 10, 20 });
        InputStream inputStream = new ByteArrayInputStream(testString.getBytes());
        String cutResults = this.app.cutFromStdin(true, false, rangeList,
                inputStream);

        assertEquals(outOfRangeString + STRING_NEWLINE, cutResults);
    }

    @Test
    void cutFromStdin_ByteNumberList_ReturnsValidResult() throws Exception {
        List<int[]> rangeList = Arrays.asList(new int[] { 1, 1 }, new int[] { 2, 2 }, new int[] { 4, 4 });

        InputStream inputStream = new ByteArrayInputStream("abcde".getBytes());
        String cutResults = this.app.cutFromStdin(true, false, rangeList,
                inputStream);

        assertEquals(sepPosString + STRING_NEWLINE, cutResults);
    }

    @Test
    void cutFromStdin_ByteNumberRange_ReturnsValidResult() throws Exception {
        List<int[]> rangeList = Arrays.asList(new int[] { 1, 6 });
        InputStream inputStream = new ByteArrayInputStream("abcdefgh".getBytes());
        String cutResults = this.app.cutFromStdin(true, false, rangeList,
                inputStream);

        assertEquals("abcdef" + STRING_NEWLINE, cutResults);
    }

    @Test
    void cutFromStdin_ByteOutOfRange_ReturnsValidResult() throws Exception {
        List<int[]> rangeList = Arrays.asList(new int[] { 20, 20 });
        InputStream inputStream = new ByteArrayInputStream("abc".getBytes());
        String cutResults = this.app.cutFromStdin(true, false, rangeList,
                inputStream);

        assertEquals("" + STRING_NEWLINE, cutResults);
    }

    @Test
    void cutFromStdin_ByteHalfOutOfRange_ReturnsValidResult() throws Exception {
        List<int[]> rangeList = Arrays.asList(new int[] { 10, 20 });
        InputStream inputStream = new ByteArrayInputStream(testString.getBytes());
        String cutResults = this.app.cutFromStdin(true, false, rangeList,
                inputStream);

        assertEquals(outOfRangeString + STRING_NEWLINE, cutResults);
    }

    @Test
    void cutFromStdin_MultipleInputs_ReturnsNewlineSeparatedResult() throws Exception {
        String input = "abcdefgh\nqwerty\n12345678\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());

        List<int[]> rangeList = Arrays.asList(new int[] { 1, 6 });
        String cutResults = this.app.cutFromStdin(true, false, rangeList,
                inputStream);

        String expected = "abcdef" + STRING_NEWLINE + "qwerty" + STRING_NEWLINE + "123456" + STRING_NEWLINE;
        assertEquals(expected, cutResults);
    }

}
