package bugs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.MkdirException;
import sg.edu.nus.comp.cs4218.impl.app.MkdirApplication;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.fileSeparator;

public class MkdirApplicationBugTest {
    private MkdirApplication mkdirApplication;
    private static final String CWD = System.getProperty("user.dir");
    private static final String MKDIR_RESOURCES = fileSeparator() + "test" + fileSeparator() + "resources" + fileSeparator() + "app" + fileSeparator() + "mkdir";;

    @BeforeEach
    public void setUp() {
        mkdirApplication = new MkdirApplication();
        Environment.currentDirectory = CWD + MKDIR_RESOURCES;
        String folderPath = Environment.currentDirectory + fileSeparator();
//        mkdirApplication.setLocation(folderPath);
    }

    @AfterEach
    public void tearDown() throws Exception {
        Environment.currentDirectory = CWD;
//        mkdirApplication.setLocation(CWD);
    }

    private void deleteDirectoryIfExists(String dirName) throws Exception {
        Path path = Paths.get(Environment.currentDirectory + fileSeparator() + dirName);
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    @Test
    void run_WithInvalidEmptyQuotedString_ShouldThrowException() {
        assertThrows(MkdirException.class, () -> {
            mkdirApplication.run(new String[]{" "}, null, null);
        });
    }
}
