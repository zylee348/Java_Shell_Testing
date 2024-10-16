package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.testutils.TestEnvironmentUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.fileSeparator;
import static sg.edu.nus.comp.cs4218.testutils.TestStringUtils.CHAR_FILE_SEP;
import static sg.edu.nus.comp.cs4218.testutils.TestStringUtils.STRING_NEWLINE;

public class CatApplicationPublicTest {
    private static final String TEXT_ONE = "Test line 1" + STRING_NEWLINE + "Test line 2" + STRING_NEWLINE +
            "Test line 3";
    private static final String EXPECT_ONE_NUM = "1 Test line 1" + STRING_NEWLINE + "2 Test line 2" +
            STRING_NEWLINE + "3 Test line 3";
    private static final String TEMP = "temp_cat";
    private static final String TEST_FILE = "fileA.txt";
//    public static final String ERR_IS_DIR = String.format("cat: %s: Is a directory", DIR);
    public static final String ERR_NO_SUCH_FILE = "cat: %s: No such file or directory";
    private static Path tempPath;
    private static final String CAT_RESOURCES = fileSeparator() + "test" + fileSeparator() + "resources" + fileSeparator() + "app" + fileSeparator() + "cat";


    private CatApplication catApplication;
    private static final String CWD = System.getProperty("user.dir");


    @BeforeEach
    void setUp() {
        catApplication = new CatApplication();
    }

    @BeforeEach
    void createTemp() throws IOException, NoSuchFieldException, IllegalAccessException {
        tempPath = Paths.get(CWD, CAT_RESOURCES + CHAR_FILE_SEP + TEMP + CHAR_FILE_SEP + TEST_FILE);
        Environment.currentDirectory = CWD + CAT_RESOURCES + CHAR_FILE_SEP + TEMP;
        System.out.println(Environment.currentDirectory);
    }

    @AfterEach
    void deleteFiles() throws IOException {
        Environment.currentDirectory = CWD;
    }

    @Test
    void catStdin_NoFlag_ReturnsStdinString() throws AbstractApplicationException {
        InputStream inputStream = new ByteArrayInputStream(TEXT_ONE.getBytes());
        String actual = catApplication.catStdin(false, inputStream);
        assertEquals(TEXT_ONE + STRING_NEWLINE, actual);
    }

    @Test
    void catStdin_EmptyStringNoFlag_ReturnsEmptyString() throws AbstractApplicationException {
        String text = "";
        InputStream inputStream = new ByteArrayInputStream(text.getBytes());
        String actual = catApplication.catStdin(false, inputStream);
        assertEquals(text, actual);
    }

    @Test
    void catStdin_IsLineNumberFlag_ReturnsStdinStringLineNo() throws AbstractApplicationException {
        InputStream inputStream = new ByteArrayInputStream(TEXT_ONE.getBytes());
        String actual = catApplication.catStdin(true, inputStream);
        assertEquals(EXPECT_ONE_NUM + STRING_NEWLINE, actual);
    }
}
