package bugs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.impl.app.GrepApplication;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;

public class GrepApplicationBugTest {
    private final GrepApplication grepApplication = new GrepApplication();
    private InputStream inputStream;
    private OutputStream outputStream;
    private static final String CWD = System.getProperty("user.dir");
    private static final String FOLDER_NAME = CWD + CHAR_FILE_SEP + "test" + CHAR_FILE_SEP + "resources" + CHAR_FILE_SEP + "app" +
            CHAR_FILE_SEP + "grep";
    private static final String FILE_NAME = FOLDER_NAME + CHAR_FILE_SEP + "c.md";

    @BeforeEach
    void setUp() throws IOException {
        inputStream = new ByteArrayInputStream("".getBytes());
        outputStream = new ByteArrayOutputStream();
    }

    @AfterEach
    void cleanUp() throws IOException {
        inputStream.close();
        outputStream.close();
    }
    @Test
    void run_WithDashAndFile_ReturnsCorrectOutput() throws AbstractApplicationException {
        String CWD = System.getProperty("user.dir");

        String[] args = {"-", FILE_NAME};
        grepApplication.run(args, inputStream, outputStream);
        String expected = "- One\n" +
                "- Two\n" +
                "- Three\n" +
                "|----|-----|\n" +
                "eat: it was a [hobbit-hole][1], and that means comfort.\n";
        assertEquals(expected, outputStream.toString());
    }
}
