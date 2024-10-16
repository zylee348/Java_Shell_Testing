package bugs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.impl.app.SortApplication;
import sg.edu.nus.comp.cs4218.testutils.TestEnvironmentUtil;
import sg.edu.nus.comp.cs4218.testutils.TestStringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.fileSeparator;
import static sg.edu.nus.comp.cs4218.testutils.TestStringUtils.STRING_NEWLINE;

class SortApplicationBugTest { //NOPMD
    private static final String TEST_FILE = "file.txt";
    private static final String NUMBER_FLAG = "-n";
    private static final String REVERSE_FLAG = "-r";
    private static final String CASE_INSENSITIVE_FLAG = "-f";
    private static SortApplication sortApplication;
    private static final Deque<Path> FILES = new ArrayDeque<>();
    private static final String CWD = System.getProperty("user.dir") + fileSeparator() + "TestResources";
    private static final String TEMP = CWD + fileSeparator() + "sort";
    private static final Path TEMP_PATH = Path.of(TEMP);




    private String joinStringsByLineSeparator(String... strs) {
        return String.join(TestStringUtils.STRING_NEWLINE, strs);
    }

    private InputStream generateInputStreamFromStrings(String... strs) {
        return new ByteArrayInputStream(joinStringsByLineSeparator(strs).getBytes(StandardCharsets.UTF_8));
    }

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException, IOException {
        sortApplication = new SortApplication();
        Environment.currentDirectory = TEMP;
        Files.createDirectory(TEMP_PATH);
    }

    @AfterEach
    void tearDown() throws IOException, NoSuchFieldException, IllegalAccessException {
        Files.walk(TEMP_PATH)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
        Environment.currentDirectory = CWD;
        for (Path file : FILES) {
            Files.deleteIfExists(file);
        }
    }

    @Test
    void run_sortWithNumbersAndLeadingWhitespace_Should_sortProperly() throws Exception {
        String[] inputData = {
                "  -18", "  56", "-20", " 15", "-3", "3", "  100", " -2", " 0", " 23", "-5", " 11"
        };
        String expectedOutput =
                "-20" + STRING_NEWLINE +
                        "  -18" + STRING_NEWLINE +
                        "-5" + STRING_NEWLINE +
                        "-3" + STRING_NEWLINE +
                        " -2" + STRING_NEWLINE +
                        " 0" + STRING_NEWLINE +
                        "3" + STRING_NEWLINE +
                        " 11" + STRING_NEWLINE +
                        " 15" + STRING_NEWLINE +
                        " 23" + STRING_NEWLINE +
                        "  56" + STRING_NEWLINE +
                        "  100" + STRING_NEWLINE;

        InputStream stdin = generateInputStreamFromStrings(inputData);
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();

        // Run the sort application with the numeric sort flag
        sortApplication.run(new String[]{NUMBER_FLAG}, stdin, stdout);

        // Verify that the output is as expected
        String actualOutput = stdout.toString(StandardCharsets.UTF_8);
        assertEquals(expectedOutput, actualOutput, "Output does not match expected sorted order with leading whitespaces.");
    }
}