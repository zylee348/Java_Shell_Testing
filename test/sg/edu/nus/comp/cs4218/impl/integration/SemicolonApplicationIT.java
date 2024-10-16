package sg.edu.nus.comp.cs4218.impl.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.fileSeparator;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.CommandBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SemicolonApplicationIT { //NOPMD
    private ShellImpl shell;
    private ByteArrayOutputStream outContent;
    private final String CWD = System.getProperty("user.dir") + fileSeparator() + "test" + fileSeparator() + "resources";
    private static String newDir = "testDir";
    private final String SMIT_RESOURCES =  fileSeparator() + "integration" + fileSeparator() + "semicolonIT" ;
    private final String resourcesDirPath = CWD + SMIT_RESOURCES;
    private final String newDirPathString = resourcesDirPath + fileSeparator() + newDir;
    private final Path newDirPath = Paths.get(newDirPathString);
    private final Path resourcesPath = Paths.get(resourcesDirPath);
    private final Path testFile = resourcesPath.resolve("test.txt");
    private static final String TEMP_DIR = "tempdir" + File.separator;
    private static final String TEMP_FILE = "temp.txt";
    private static final String TEMP_FILE2 = "temp2.txt";

    private static final String FILE_CONTENT = "abcd";

    @Nested
    class SemicolonWithApplicationOnly {
        @BeforeEach
        public void setUp () throws IOException {
            shell = new ShellImpl();
            outContent = new ByteArrayOutputStream();
            Environment.currentDirectory = CWD + SMIT_RESOURCES;
            // Delete the test directory if it exists
            if (Files.exists(newDirPath)) {
                Files.delete(newDirPath);
            }
            Files.createDirectories(resourcesPath);
        }

        @AfterEach
        public void tearDown () throws IOException {
            outContent.close();
            // Reset the current directory to its original state after each test
            Environment.currentDirectory = CWD;
        }

        @Test
        public void parseAndEvaluate_cdThenMkdir_ShouldFailButWithFolderCreation () throws
        FileNotFoundException, AbstractApplicationException, ShellException {
            String testCommand = "cd " + newDir + " ; mkdir " + newDir;
            shell.parseAndEvaluate(testCommand, outContent);
            String expectedMessage = "cd: No such file or directory"; // adjust based on your implementation
            String output = outContent.toString();
            assertTrue(output.contains(expectedMessage));
            assertTrue(Files.exists(newDirPath));
        }
        @Test
        public void parseAndEvaluate_mkdirThenCd_ShouldSucceed () throws Exception {
            String testCommand = "mkdir " + newDir + "; cd " + newDir;

            shell.parseAndEvaluate(testCommand, outContent);

            assertEquals(Environment.currentDirectory, newDirPath.toString());
        }
        @Test
        public void parseAndEvaluate_CdThenLs_ShouldDisplayFiles () throws
                IOException, AbstractApplicationException, ShellException {
            // Create a new directory for the test
            Path newDir = resourcesPath.resolve(Environment.currentDirectory + fileSeparator() + "CdThenLs");
            // Create a test file in the new directory
            // Test command to change directory and then list files
            Path testFilePath = newDir.resolve(Environment.currentDirectory + fileSeparator() + "testFile.txt");
            if (!Files.exists(testFilePath)) {
                Files.createFile(testFilePath);
            }
            String testCommand = "cd " + newDir + "; ls";
            shell.parseAndEvaluate(testCommand, outContent);
            // The output should contain the test file name
            String output = outContent.toString();
            assertTrue(output.contains("testFile.txt"));
        }


        @Test
        public void parseAndEvaluate_EchoThenCatWithRedirection_ShouldCreateFileWithContent () throws
        IOException, AbstractApplicationException, ShellException {
            // Test command to echo a string into a file and then read it with cat
            String testContent = "Hello, World!";
            String testCommand = "echo " + testContent + " > test.txt; cat test.txt";
            shell.parseAndEvaluate(testCommand, outContent);
            // The output should contain the echoed string
            String output = outContent.toString();
            assertEquals(testContent + STRING_NEWLINE, output);

            // Also, check that the file actually contains the content
            String fileContent = Files.readString(testFile);
            assertEquals(testContent + STRING_NEWLINE, fileContent);
        }
    }

    @Nested
    class SemicolonWithOtherShellCommand {
        private OutputStream outputStream;

        @BeforeEach
        void setup() throws IOException {
            shell = new ShellImpl();
            outputStream = new ByteArrayOutputStream();
        }

        @AfterEach
        void reset() throws IOException {
            outputStream.close();

            if (Files.exists(Paths.get(TEMP_DIR))) {
                Files.walk(Paths.get(TEMP_DIR))
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            }

        }

        @Test
        void parseAndEvaluate_SemicolonSemicolon_RunsBothApplicationsSeparately() throws Exception {
            Path tempPath = Paths.get(Environment.currentDirectory, TEMP_DIR);
            Files.createDirectory(tempPath);
            Path tempFile = Paths.get(TEMP_DIR + TEMP_FILE);
            Files.createFile(tempFile);
            Files.write(tempFile, FILE_CONTENT.getBytes());

            String command = "cut -c 1-3 tempdir/temp.txt; echo b";
            shell.parseAndEvaluate(command, outputStream);

            String expected = "abc" + STRING_NEWLINE + "b" + STRING_NEWLINE;
            assertEquals(expected, outputStream.toString());
        }

        @Test
        void parseAndEvaluate_SemicolonIO_RunsBothApplicationsSeparately() throws Exception {
            Path tempPath = Paths.get(Environment.currentDirectory, TEMP_DIR);
            Files.createDirectory(tempPath);
            Path tempFile = Paths.get(TEMP_DIR + TEMP_FILE);
            Files.createFile(tempFile);
            Files.write(tempFile, FILE_CONTENT.getBytes());

            Path tempFile2 = Paths.get(TEMP_DIR + TEMP_FILE2);
            Files.createFile(tempFile2);

            String command = "cut -c 1-3 < tempdir/temp.txt; echo b";
            shell.parseAndEvaluate(command, outputStream);

            String expected = "abc" + STRING_NEWLINE + "b" + STRING_NEWLINE;
            assertEquals(expected, outputStream.toString());
        }

        // cannot parse glob *
        @Test
        @DisabledOnOs(OS.WINDOWS)
        void parseAndEvaluate_SemicolonGlob_RunsBothApplicationsSeparately() throws Exception {
             Path tempPath = Paths.get(Environment.currentDirectory, TEMP_DIR);
             Files.createDirectory(tempPath);
             Path tempFile = Paths.get(TEMP_DIR + TEMP_FILE);
             Files.createFile(tempFile);

             Path tempFile2 = Paths.get(TEMP_DIR + TEMP_FILE2);
             Files.createFile(tempFile2);

             Path tempFile3 = Paths.get(TEMP_DIR + "test.txt");
             Files.createFile(tempFile3);

             Path tempFile4 = Paths.get(TEMP_DIR + "test2.txt");
             Files.createFile(tempFile4);

             String command = "ls tempdir/temp*; ls tempdir";
             shell.parseAndEvaluate(command, outputStream);

             List<String> expectedArr = Arrays.asList("tempdir/temp.txt\n", "tempdir/temp2.txt",
             "tempdir:", "temp.txt",
             "temp2.txt", "test.txt", "test2.txt");
             String expected = String.join(STRING_NEWLINE, expectedArr);
             assertEquals(expected + STRING_NEWLINE, outputStream.toString());
         }

        @Test
        void parseAndEvaluate_SemicolonSubstitution_RunsBothApplicationsSeparately() throws Exception {
            Path tempPath = Paths.get(Environment.currentDirectory, TEMP_DIR);
            Files.createDirectory(tempPath);
            Path tempFile = Paths.get(TEMP_DIR + TEMP_FILE);
            Files.createFile(tempFile);
            Files.write(tempFile, FILE_CONTENT.getBytes());

            String command = "echo `cat tempdir/temp.txt`; echo 'cat tempdir/temp.txt'";
            shell.parseAndEvaluate(command, outputStream);

            String expected = FILE_CONTENT + STRING_NEWLINE + "cat tempdir/temp.txt" + STRING_NEWLINE;
            assertEquals(expected, outputStream.toString());
        }

        @Test
        void parseAndEvaluate_SemicolonQuoting_RunsBothApplicationsSeparately()
            throws Exception {
            Path tempPath = Paths.get(Environment.currentDirectory, TEMP_DIR);
            Files.createDirectory(tempPath);
            Path tempFile = Paths.get(TEMP_DIR + TEMP_FILE);
            Files.createFile(tempFile);

            String command = "echo 'a > b'; echo b";
            shell.parseAndEvaluate(command, outputStream);

            String expected = "a > b" + STRING_NEWLINE + "b" + STRING_NEWLINE;
            assertEquals(expected, outputStream.toString());
        }

        @Test
        void parseAndEvaluate_SemicolonWithApps_RunsAllApplicationsSeparately() throws Exception {
            Path tempPath = Paths.get(Environment.currentDirectory, TEMP_DIR);
            Files.createDirectory(tempPath);
            Path tempFile = Paths.get(TEMP_DIR + TEMP_FILE);
            Files.createFile(tempFile);
            Files.write(tempFile, "abcd\n1234".getBytes());

            List<String> commands = Arrays.asList("cd tempdir", "cat temp.txt", "mkdir tempdir2",
                    "cut -c 1-3 temp.txt", "echo 123", "ls .", "grep 'bc' temp.txt", "rm -d tempdir2", "ls .");
            String command = String.join(";", commands);

            ApplicationRunner appRunner = mock(ApplicationRunner.class);
            Command builtCommand = CommandBuilder.parseCommand(command, appRunner);
            builtCommand.evaluate(System.in, outputStream);

            verify(appRunner, times(9)).runApp(any(), any(), any(), any());
        }
    }
}