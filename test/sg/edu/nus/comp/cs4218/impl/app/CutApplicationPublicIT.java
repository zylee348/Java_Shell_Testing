package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

public class CutApplicationPublicIT { //NOPMD - suppressed ClassNamingConventions
    public static final String CHAR_FLAG = "-c";
    public static final String BYTE_FLAG = "-b";
    public static final String TEST_RANGE = "1-3";

    public static final String CUT_TEST_OUTPUT1 = "hel";
    public static final String CUT_TEST_OUTPUT2 = "wor";
    CutApplication cutApplication;
    private InputStream stdin;

    private String joinStringsByLineSeparator(String... strs) {
        return String.join(STRING_NEWLINE, strs);
    }

    private InputStream generateInputStreamFromStrings(String... strs) {
        return new ByteArrayInputStream(joinStringsByLineSeparator(strs).getBytes(StandardCharsets.UTF_8));
    }

    @BeforeEach
    public void setUp() {
        cutApplication = new CutApplication();
    }


    @AfterEach
    public void tearDown() throws Exception {
        if (stdin != null) {
            stdin.close();
        }
    }

    @Test
    void run_SingleLineByCharRange_ReturnCutByLine() throws Exception {
        String[] argList = new String[] { CHAR_FLAG, TEST_RANGE };
        stdin = generateInputStreamFromStrings("hello world");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        cutApplication.run(argList, stdin, output);
        assertEquals(CUT_TEST_OUTPUT1 + STRING_NEWLINE, output.toString(StandardCharsets.UTF_8));
    }

    @Test
    void cutFromStdin_SingleLineByByteRange_ReturnCutByByte() throws Exception {
        String[] argList = new String[] { BYTE_FLAG, TEST_RANGE };
        stdin = generateInputStreamFromStrings("hello world");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        cutApplication.run(argList, stdin, output);
        assertEquals(CUT_TEST_OUTPUT1 + STRING_NEWLINE, output.toString(StandardCharsets.UTF_8));
    }

    @Test
    void cutFromStdin_MultipleLinesByCharRange_ReturnCutContentAtEachLineByByte() throws Exception {
        String[] argList = new String[] { CHAR_FLAG, TEST_RANGE };
        stdin = generateInputStreamFromStrings("hello", "world");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        cutApplication.run(argList, stdin, output);
        assertEquals(CUT_TEST_OUTPUT1 + STRING_NEWLINE + CUT_TEST_OUTPUT2 + STRING_NEWLINE, output.toString(StandardCharsets.UTF_8));
    }

    @Test
    void cutFromStdin_MultipleLinesByByteRange_ReturnCutContentAtEachLineByByte() throws Exception {
        String[] argList = new String[] { BYTE_FLAG, TEST_RANGE };
        stdin = generateInputStreamFromStrings("hello", "world");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        cutApplication.run(argList, stdin, output);
        assertEquals(CUT_TEST_OUTPUT1 + STRING_NEWLINE + CUT_TEST_OUTPUT2 + STRING_NEWLINE, output.toString(StandardCharsets.UTF_8));
    }

    // we print collate and print out invalid file error instead of throwing
    // @Test
    // void cutFromFile_InvalidFile_ThrowsException() {
    // String[] argList = new String[] { BYTE_FLAG, TEST_RANGE, "invalidFile" };
    // assertThrows(CutException.class,
    // () -> cutApplication.run(argList, System.in, System.out));
    // }

}
