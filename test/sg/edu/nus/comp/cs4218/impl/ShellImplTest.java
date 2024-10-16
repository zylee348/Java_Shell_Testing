package sg.edu.nus.comp.cs4218.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;
import sg.edu.nus.comp.cs4218.exception.SortException;
import sg.edu.nus.comp.cs4218.impl.app.CatApplication;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_INVALID_APP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.*;
import com.ginsberg.junit.exit.ExpectSystemExitWithStatus;

public class ShellImplTest {
    private ShellImpl shell;
    private OutputStream outputStream;
    private static final String CWD = System.getProperty("user.dir");
    private static final String SHELLIMPL_RESOURCES = fileSeparator() + "test" + fileSeparator() + "resources"
            + fileSeparator() + "shellImpl";

    private void deleteFile(Path path) throws IOException {
        if(Files.exists(path)) {
            Files.delete(path);
        }
    }

    @Nested
    class ParseAndEvaluateSystemTest {
        @BeforeEach
        void setup() throws IOException {
            shell = new ShellImpl();
            outputStream = new ByteArrayOutputStream();
            Environment.currentDirectory = CWD + SHELLIMPL_RESOURCES;
        }

        @AfterEach
        void reset() throws IOException {
            outputStream.close();
            Environment.currentDirectory = CWD;
        }

        @Test
        public void parseAndEvaluate_WithValidCdCommand_DoesNotThrowExceptionAndWorkProperly() {
            String command = "cd ../../..";
            assertDoesNotThrow(() -> {
                shell.parseAndEvaluate(command, outputStream);
            });
            assertEquals(Environment.currentDirectory, CWD);
        }

        @Test
        public void parseAndEvaluate_WithInValidCommand_ThrowsException() {
            String command = "invalidCommand";
            ShellException exception = assertThrows(ShellException.class, () -> {
                shell.parseAndEvaluate(command, outputStream);
            });
            String expected = "shell: " + command + ": " + ERR_INVALID_APP;
            assertEquals(expected, exception.getMessage());
        }

        @Test
        public void parseAndEvaluate_CatCommandWithOneFile_ShouldCatProperly() throws Exception {
            // Assuming 'file1.txt' and 'file2.txt' exist in the current working directory
            // with the contents 'Content of File 1' and 'Content of File 2' respectively
            String command = "cat file1.txt";

            shell.parseAndEvaluate(command, outputStream);

            String expectedOutput = "File 1 Line 1" + STRING_NEWLINE +
                    "File 1 Line 2" + STRING_NEWLINE +
                    "File 1 Line 3" + STRING_NEWLINE;
            String actualOutput = outputStream.toString();
            assertEquals(expectedOutput, actualOutput);
        }

        @Test
        public void parseAndEvaluate_CatCommandWithMultipleFiles_ShouldCatProperly() throws Exception {
            // Assuming 'file1.txt' and 'file2.txt' exist in the current working directory
            // with the contents 'Content of File 1' and 'Content of File 2' respectively
            String command = "cat file1.txt file2.txt";
            shell.parseAndEvaluate(command, outputStream);
            String expectedOutput = "File 1 Line 1" + STRING_NEWLINE +
                    "File 1 Line 2" + STRING_NEWLINE +
                    "File 1 Line 3" + STRING_NEWLINE +
                    "File 2 Line 1" + STRING_NEWLINE +
                    "File 2 Line 2" + STRING_NEWLINE +
                    "File 2 Line 3" + STRING_NEWLINE;
            String actualOutput = outputStream.toString();
            assertEquals(expectedOutput, actualOutput);
        }

        @Test
        public void parseAndEvaluate_CatCommandWithMultipleFilesAndIO_ShouldConCatProperly() throws Exception {
            // Assuming 'file1.txt' and 'file2.txt' exist in the current working directory
            // with the contents 'Content of File 1' and 'Content of File 2' respectively
            String command = "cat file1.txt file2.txt > union.txt";
            assertDoesNotThrow(() -> {
                shell.parseAndEvaluate(command, outputStream);
            });
            CatApplication catApplication = new CatApplication();
            String expectedOutput = "File 1 Line 1" + STRING_NEWLINE +
                    "File 1 Line 2" + STRING_NEWLINE +
                    "File 1 Line 3" + STRING_NEWLINE +
                    "File 2 Line 1" + STRING_NEWLINE +
                    "File 2 Line 2" + STRING_NEWLINE +
                    "File 2 Line 3" + STRING_NEWLINE;
            String actualOutput = catApplication.catFiles(false, "union.txt");
            assertEquals(expectedOutput, actualOutput);
        }

        @Test
        public void parseAndEvaluate_MkdirCommand_CreatesDirectorySuccessfully() throws Exception {
            String newDirName = "newDir";
            Path newDirPath = Paths.get(Environment.currentDirectory, newDirName);
            Files.deleteIfExists(newDirPath); // Ensure the directory doesn't exist before the test
            String command = "mkdir " + newDirName;
            shell.parseAndEvaluate(command, outputStream);
            assertTrue(Files.exists(newDirPath));
            assertTrue(Files.isDirectory(newDirPath));
            Files.deleteIfExists(newDirPath);
        }

        @Test
        @EnabledOnOs(OS.WINDOWS)
        public void parseAndEvaluate_WcCommandWithFile_ShouldCountCorrectly() throws Exception {
            String command = "wc wcTestFileShellImpl.txt";
            shell.parseAndEvaluate(command, outputStream);
            String expectedOutput = "3\t10\t57 wcTestFileShellImpl.txt";

            String actualOutput = outputStream.toString().trim();
            assertEquals(expectedOutput, actualOutput);
        }

        @Test
        @EnabledOnOs(OS.MAC)
        public void parseAndEvaluate_WcCommandWithFileMac_ShouldCountCorrectly() throws Exception {
            String command = "wc wcTestFileShellImpl.txt";
            shell.parseAndEvaluate(command, outputStream);
            String expectedOutput = "3\t10\t55 wcTestFileShellImpl.txt";

            String actualOutput = outputStream.toString().trim();
            assertEquals(expectedOutput, actualOutput);
        }

        @Test
        public void parseAndEvaluate_SortEmptyFile_ShouldReturnEmptyString() throws Exception {
            String command = "sort emptyFile.txt";
            shell.parseAndEvaluate(command, outputStream);
            String expectedOutput = "" + STRING_NEWLINE;
            String actualOutput = outputStream.toString();
            assertEquals(expectedOutput, actualOutput);
        }

        @Test
        public void parseAndEvaluate_SortNonExistentFile_ShouldThrowException() {
            String command = "sort nonExistentFile.txt";
            SortException exception = assertThrows(SortException.class, () -> {
                shell.parseAndEvaluate(command, outputStream);
            });
            String expectedOutput = "sort: No such file or directory";
            String actualOutput = exception.getMessage();
            assertEquals(expectedOutput, actualOutput);

        }

        @Test
        public void parseAndEvaluate_SortMultipleFilesComplicatedPattern_ShouldSortCorrectly() throws Exception {
            String command = "sort sortfile1.txt sortfile2.txt sortfile3.txt";
            shell.parseAndEvaluate(command, outputStream);
            String expectedOutput = "19" + STRING_NEWLINE +
                    "1990-01-01" + STRING_NEWLINE + "2005-07-21" + STRING_NEWLINE +
                    "42" + STRING_NEWLINE + "7" + STRING_NEWLINE +
                    "Alice" + STRING_NEWLINE + "apple" + STRING_NEWLINE +
                    "mango" + STRING_NEWLINE + "zebra";
            String actualOutput = outputStream.toString().trim();
            assertEquals(expectedOutput, actualOutput);
        }

        @Test
        @EnabledOnOs(OS.MAC)
        void parseAndEvaluate_PipeAndSequenceWithCdAndLsAndCat_DisplayContentInTheFolder() throws FileNotFoundException,
                AbstractApplicationException, ShellException {
            Environment.currentDirectory = CWD;

            String command = "cd " + CWD+SHELLIMPL_RESOURCES + "; ls";
            shell.parseAndEvaluate(command, outputStream);
            String expected = "File1.txt"+ STRING_NEWLINE +"File2.txt"+ STRING_NEWLINE +"emptyFile.txt"+ STRING_NEWLINE
                    + "simpleTextFile.txt"+ STRING_NEWLINE +"sortfile1.txt"+ STRING_NEWLINE +"sortfile2.txt"+
                    STRING_NEWLINE +"sortfile3.txt"+ STRING_NEWLINE +"union.txt" + STRING_NEWLINE
                    +"wcTestFileShellImpl.txt"+ STRING_NEWLINE;
            assertEquals(expected, outputStream.toString());
        }
    }

    @Nested
    class MainSystemTest {
        private final String folderName = "test" + CHAR_FILE_SEP + "resources" + CHAR_FILE_SEP + "integration" +
                CHAR_FILE_SEP + "PipeApplicationIT";
        private final String fileOne = folderName + CHAR_FILE_SEP + "file1.txt";

        @BeforeEach
        void setup() throws IOException {
            shell = new ShellImpl();
            outputStream = new ByteArrayOutputStream();
            Environment.currentDirectory = CWD;
        }

        @AfterEach
        void reset() throws IOException {
            outputStream.close();
        }

        @Test
        @ExpectSystemExitWithStatus(0)
        @EnabledOnOs(OS.MAC)
        void main_BFSystemTest_AllOutputCorrectly() throws IOException {
            String folderPath = folderName + fileSeparator() + "tempFolder";
            try {
                String command = "echo \"Hello World\"" + STRING_NEWLINE +
                        "cd " + folderName + STRING_NEWLINE +
                        "mkdir tempFolder" + STRING_NEWLINE +
                        "sort file1.txt" + STRING_NEWLINE +
                        "wc -l file2.txt" + STRING_NEWLINE +
                        "cat file2.txt" + STRING_NEWLINE +
                        "exit" + STRING_NEWLINE;

                System.setIn(new ByteArrayInputStream(command.getBytes()));
                System.setOut(new PrintStream(outputStream));

                ShellImpl.main();

                assertTrue(Files.exists(Path.of(folderPath)));
                assertTrue(outputStream.toString().contains("Hello World"));
                assertTrue(outputStream.toString().contains("Orange\nRed\nRed\nYellow"));
                assertTrue(outputStream.toString().contains("2"));
                assertTrue(outputStream.toString().contains("Yellow\nBlue\nGreen"));

            } catch (Exception ignore) {
                fail();
            } finally {
                deleteFile(Path.of(folderPath));
            }
        }
        @Test
        @EnabledOnOs(OS.MAC)
        void main_EFOneSystemTest_OutputsAllCorrectly() throws IOException {
            Path outputFile = Files.createTempFile(Path.of(Environment.currentDirectory), "tempOutput", ".txt");
            try {
                String command = "mv *.txt " + folderName + STRING_NEWLINE +
                        "ls " + folderName + " | paste | uniq " + STRING_NEWLINE;

                System.setIn(new ByteArrayInputStream(command.getBytes()));
                System.setOut(new PrintStream(outputStream));

                ShellImpl.main();
                assertTrue(outputStream.toString().contains(folderName + ":" + STRING_NEWLINE +
                        "file1.txt\nfile2.txt\n" + outputFile.getFileName() + STRING_NEWLINE));

            } catch (Exception ignore) {
                fail();
            } finally {
                deleteFile(Path.of(folderName + StringUtils.fileSeparator()+ outputFile.getFileName()));
            }
        }

        @Test
        void main_EFTwoSystemTestWithPipe_OutputsAllCorrectly() throws IOException {
            Path fileThreeFile = Files.createTempFile(Path.of(folderName), "file3", ".txt");
            try {
                String command = "cut -c 1-3 " + fileOne + " | tee " + fileThreeFile.toString() + "; grep 'Yel' "
                        + fileThreeFile + STRING_NEWLINE +
                        "rm " + fileThreeFile.toString() + STRING_NEWLINE;

                System.setIn(new ByteArrayInputStream(command.getBytes()));
                System.setOut(new PrintStream(outputStream));

                ShellImpl.main();

                assertFalse(Files.exists(Path.of(fileThreeFile.toString())));
                assertTrue(outputStream.toString().contains("Yel"));

            } catch (Exception ignore) {
                fail();
            } finally {
                deleteFile(Path.of(fileThreeFile.toString()));
            }
        }

        @Test
        @ExpectSystemExitWithStatus(0)
        @EnabledOnOs(OS.MAC)
        void main_SystemTestWithAllFunctions_AllOutputsCorrect() throws IOException {
            Path fileThreeFile = Files.createTempFile(Path.of(folderName), "file3", ".txt");
            try {
                String command = "mkdir tempFolder"+ "; mv tempFolder " + folderName +"; " +
                        "cd " + folderName + STRING_NEWLINE +
                        "echo `cat file2.txt`" + STRING_NEWLINE +
                        "ls * | sort" + STRING_NEWLINE +
                        "paste < file1.txt | cut -c 1-3 | grep \"Red\"" + STRING_NEWLINE +
                        "uniq file1.txt | wc -l | tee " + fileThreeFile.getFileName() +STRING_NEWLINE+
                        "cat " + fileThreeFile.getFileName() +STRING_NEWLINE +
                        "rm "+ fileThreeFile.getFileName() +STRING_NEWLINE +
                        "rm -d tempFolder" + STRING_NEWLINE +
                        "exit " + STRING_NEWLINE;

                System.setIn(new ByteArrayInputStream(command.getBytes()));
                System.setOut(new PrintStream(outputStream));

                ShellImpl.main();

                assertTrue(outputStream.toString().contains(
                        "file1.txt\nfile2.txt\n"+fileThreeFile.getFileName()+STRING_NEWLINE+"tempFolder"));
                assertTrue(outputStream.toString().contains("Red\nRed"));
                assertTrue(outputStream.toString().contains("4"));
                assertFalse(Files.exists(Path.of(fileThreeFile.toString())));
                assertFalse(Files.exists(Path.of(folderName + StringUtils.fileSeparator() + "tempFolder")));

            } catch(Exception ignore) {
                fail();
            } finally {
                deleteFile(Path.of(fileThreeFile.toString()));
                deleteFile(Path.of(folderName + StringUtils.fileSeparator() + "tempFolder"));
            }
        }
        @Test
        @ExpectSystemExitWithStatus(0)
        void main_SystemTestExit_AllOutputsCorrect() {
            String command = "exit" + STRING_NEWLINE;

            System.setIn(new ByteArrayInputStream(command.getBytes()));
            System.setOut(new PrintStream(outputStream));

            ShellImpl.main();
        }
    }


}
