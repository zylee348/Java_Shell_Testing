package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.PasteException;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.*;

public class PasteApplicationTest {
    private final PasteApplication pasteApplication = new PasteApplication();
    private static final String CWD = System.getProperty("user.dir");
    private static final String FOLDER_NAME = CWD + CHAR_FILE_SEP + "test" + CHAR_FILE_SEP + "resources" + CHAR_FILE_SEP + "app" +
            CHAR_FILE_SEP + "paste";
    private static final String FILE_NAME_A = FOLDER_NAME + CHAR_FILE_SEP + "A.txt";
    private static final String FILE_NAME_B = FOLDER_NAME + CHAR_FILE_SEP + "B.txt";
    private static final String FILE_NAME_C = FOLDER_NAME + CHAR_FILE_SEP + "C.txt";
    private static final String FILE_EMPTY_1 = FOLDER_NAME + CHAR_FILE_SEP + "Empty1.txt";
    private static final String FILE_EMPTY_2 = FOLDER_NAME + CHAR_FILE_SEP + "Empty2.txt";
    private static final String PASTE_EXCEPTION_PREFIX = "paste: ";

    @Test
    void mergeFile_OnlyOneFile_ReturnsCorrectString() throws Exception {
        String expected = "A" + STRING_NEWLINE + "B" + STRING_NEWLINE + "C" + STRING_NEWLINE + "D";
        String actual = pasteApplication.mergeFile(false, FILE_NAME_A);
        assertEquals(expected, actual);
    }

    @Test
    void mergeFile_MergeTwoNonEmptyFilesParallel_ReturnsCorrectString() throws Exception {
        String expected = "A" + CHAR_TAB + "1" + STRING_NEWLINE +
                "B" + CHAR_TAB + "2" + STRING_NEWLINE +
                "C" + CHAR_TAB + "3" + STRING_NEWLINE +
                "D" + CHAR_TAB + "4";
        String actual = pasteApplication.mergeFile(false, FILE_NAME_A, FILE_NAME_B);
        assertEquals(expected, actual);
    }

    @Test
    void mergeFile_MergeTwoNonEmptyFilesSerial_ReturnsCorrectString() throws Exception {
        String expected = "A" + CHAR_TAB + "B" + CHAR_TAB + "C" + CHAR_TAB + "D" + STRING_NEWLINE +
                "1" + CHAR_TAB + "2" + CHAR_TAB + "3" + CHAR_TAB + "4";
        String actual = pasteApplication.mergeFile(true, FILE_NAME_A, FILE_NAME_B);
        assertEquals(expected, actual);
    }

    @Test
    void mergeFile_MergeTwoNonEmptyFilesWithDiffLineCountParallel_ReturnsCorrectString() throws Exception {
        String expected = "1" + CHAR_TAB + "A" + STRING_NEWLINE +
                "2" + CHAR_TAB + "B" + STRING_NEWLINE +
                "3" + CHAR_TAB + "C" + STRING_NEWLINE +
                "4" + CHAR_TAB + "D" + STRING_NEWLINE +
                CHAR_TAB + "E";
        String actual = pasteApplication.mergeFile(false, FILE_NAME_B, FILE_NAME_C);
        assertEquals(expected, actual);
    }

    @Test
    void mergeFile_MergeNonEmptyWithEmptyFileParallel_ReturnsNonEmptyString() throws Exception {
        String expected = "A" + CHAR_TAB + STRING_NEWLINE +
                "B" + CHAR_TAB + STRING_NEWLINE +
                "C" + CHAR_TAB + STRING_NEWLINE +
                "D";
        String actual = pasteApplication.mergeFile(false, FILE_NAME_A, FILE_EMPTY_1);
        assertEquals(expected, actual);
    }

    @Test
    void mergeFile_MergeEmptyWithNonEmptyFileParallel_ReturnsNonEmptyString() throws Exception {
        String expected = CHAR_TAB + "A" + STRING_NEWLINE +
                CHAR_TAB + "B" + STRING_NEWLINE +
                CHAR_TAB + "C" + STRING_NEWLINE +
                CHAR_TAB + "D";
        String actual = pasteApplication.mergeFile(false, FILE_EMPTY_1, FILE_NAME_A);
        assertEquals(expected, actual);
    }

    @Test
    void mergeFile_MergeTwoEmptyFilesParallel_ReturnsEmptyString() throws Exception {
        String expected = "";
        String actual = pasteApplication.mergeFile(false, FILE_EMPTY_1, FILE_EMPTY_2);
        assertEquals(expected, actual);
    }

    @Test
    void mergeFile_MergeThreeNonEmptyFilesParallel_ReturnsCorrectString() throws Exception {
        String expected = "A" + CHAR_TAB + "1" + CHAR_TAB + "A" + STRING_NEWLINE +
                "B" + CHAR_TAB + "2" + CHAR_TAB + "B" + STRING_NEWLINE +
                "C" + CHAR_TAB + "3" + CHAR_TAB + "C" + STRING_NEWLINE +
                "D" + CHAR_TAB + "4" + CHAR_TAB + "D" + STRING_NEWLINE +
                CHAR_TAB + CHAR_TAB + "E";
        String actual = pasteApplication.mergeFile(false, FILE_NAME_A, FILE_NAME_B, FILE_NAME_C);
        assertEquals(expected, actual);
    }

    @Test
    void mergeStdin_OnlyOneStdinParallel_ReturnsCorrect() throws Exception {
        String input = "A" + STRING_NEWLINE + "B" + STRING_NEWLINE + "C" + STRING_NEWLINE + "D";
        InputStream stdin = new ByteArrayInputStream(input.getBytes());
        String expected = "A" + STRING_NEWLINE + "B" + STRING_NEWLINE + "C" + STRING_NEWLINE + "D";
        String actual = pasteApplication.mergeStdin(false, stdin);
        assertEquals(expected, actual);
        stdin.close();
    }

    @Test
    void mergeFileAndStdin_MergeOneFileAndStdinParallel_ReturnsCorrect() throws Exception {
        String input = "A" + STRING_NEWLINE + "B" + STRING_NEWLINE + "C" + STRING_NEWLINE + "D";
        InputStream stdin = new ByteArrayInputStream(input.getBytes());
        String expected = "A" + CHAR_TAB + "1" + STRING_NEWLINE +
                "B" + CHAR_TAB + "2" + STRING_NEWLINE +
                "C" + CHAR_TAB + "3" + STRING_NEWLINE +
                "D" + CHAR_TAB + "4";
        String actual = pasteApplication.mergeFileAndStdin(false, stdin, FILE_NAME_B);
        assertEquals(expected, actual);
        stdin.close();
    }

    @Test
    void run_WithTwoFilesParallel_ReturnsCorrect() throws Exception {
        String[] args = {FILE_NAME_A, FILE_NAME_B};
        String expected = "A" + CHAR_TAB + "1" + STRING_NEWLINE +
                "B" + CHAR_TAB + "2" + STRING_NEWLINE +
                "C" + CHAR_TAB + "3" + STRING_NEWLINE +
                "D" + CHAR_TAB + "4" + STRING_NEWLINE;
        OutputStream stdout = new ByteArrayOutputStream();
        pasteApplication.run(args, null, stdout);
        assertEquals(expected, stdout.toString());
        stdout.close();
    }

    @Test
    void run_WithTwoFilesSerial_ReturnsCorrect() throws Exception {
        String[] args = {"-s", FILE_NAME_A, FILE_NAME_B};
        String expected = "A" + CHAR_TAB + "B" + CHAR_TAB + "C" + CHAR_TAB + "D" + STRING_NEWLINE +
                "1" + CHAR_TAB + "2" + CHAR_TAB + "3" + CHAR_TAB + "4" + STRING_NEWLINE;
        OutputStream stdout = new ByteArrayOutputStream();
        pasteApplication.run(args, null, stdout);
        assertEquals(expected, stdout.toString());
        stdout.close();
    }

    @Test
    void run_WithDashAsStdin_ReturnsCorrect() throws Exception {
        String[] args = {"-"};
        String input = "A" + STRING_NEWLINE + "B" + STRING_NEWLINE + "C" + STRING_NEWLINE + "D";
        InputStream stdin = new ByteArrayInputStream(input.getBytes());
        String expected = "A" + STRING_NEWLINE + "B" + STRING_NEWLINE + "C" + STRING_NEWLINE + "D" + STRING_NEWLINE;
        OutputStream stdout = new ByteArrayOutputStream();
        pasteApplication.run(args, stdin, stdout);
        assertEquals(expected, stdout.toString());
        stdin.close();
        stdout.close();
    }

    @Test
    void run_WithStdInAndFilesParallel_ReturnsCorrect() throws Exception {
        String[] args = {"-", FILE_NAME_B, "-"};
        String input = "A" + STRING_NEWLINE + "B" + STRING_NEWLINE + "C" + STRING_NEWLINE + "D";
        InputStream stdin = new ByteArrayInputStream(input.getBytes());
        String expected = "A" + CHAR_TAB + "1" + CHAR_TAB + "B" + STRING_NEWLINE +
                "C" + CHAR_TAB + "2" + CHAR_TAB + "D" + STRING_NEWLINE +
                CHAR_TAB + "3" + CHAR_TAB + STRING_NEWLINE +
                CHAR_TAB + "4" + CHAR_TAB + STRING_NEWLINE;
        OutputStream stdout = new ByteArrayOutputStream();
        pasteApplication.run(args, stdin, stdout);
        assertEquals(expected, stdout.toString());
        stdin.close();
        stdout.close();
    }

    @Test
    void run_WithNullArgs_ThrowsException() throws Exception {
        String expected = PASTE_EXCEPTION_PREFIX + ERR_NULL_ARGS;
        Throwable thrown = assertThrows(PasteException.class, () -> {
            pasteApplication.run(null, null, null);
        });
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    void run_WithNullStdOut_ThrowsException() throws Exception {
        String[] args = {FILE_NAME_A};
        String expected = PASTE_EXCEPTION_PREFIX + ERR_NO_OSTREAM;
        Throwable thrown = assertThrows(PasteException.class, () -> {
            pasteApplication.run(args, null, null);
        });
        assertEquals(expected, thrown.getMessage());
    }
}
