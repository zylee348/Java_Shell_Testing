package bugs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.CutException;
import sg.edu.nus.comp.cs4218.impl.app.CutApplication;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_INVALID_INTEGER;

public class CutApplicationBugTest {
    private CutApplication app;
    private OutputStream stdout;
    private InputStream stdin;
    private static final String TEMP_DIR = "temp-cut" + File.separator;
    private static final String TEMP_FILE = "temp.txt";
    private static Path tempPath;
    private static String testString = "abcdefghijklmnop";

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
    void cutFromFiles_largeNumber_DoesNotCrash() throws Exception {
        Path tempFile = Paths.get(TEMP_DIR + TEMP_FILE);
        Files.createFile(tempFile);
        Files.write(tempFile, testString.getBytes());

        String[] args = new String[]{"-c", "5294967296", tempFile.toString()};

        CutException exception = assertThrows(CutException.class, () -> this.app.run(args, stdin, stdout));
        assertEquals(new CutException(ERR_INVALID_INTEGER).getMessage(), exception.getMessage());

    }

}
