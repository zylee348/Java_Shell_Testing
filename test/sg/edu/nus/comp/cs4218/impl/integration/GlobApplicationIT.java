package sg.edu.nus.comp.cs4218.impl.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.fileSeparator;

public class GlobApplicationIT { //NOPMD

    private ShellImpl shell;
    private ByteArrayOutputStream outContent;
    private static final String CWD = System.getProperty("user.dir");
    private static final String GLOB_IT_RESOURCES =  fileSeparator() + "test" + fileSeparator() + "resources" + fileSeparator() + "integration" + fileSeparator() + "globIT" ;

    @BeforeEach
    public void setUp() throws IOException {
        shell = new ShellImpl();
        outContent = new ByteArrayOutputStream();

        // Set the current directory to our temporary test directory
        Environment.currentDirectory = CWD + GLOB_IT_RESOURCES;
        // Create sample files starting with 'x'
        Path filePath1 = Path.of(Environment.currentDirectory + fileSeparator() + "xfile1.txt");
        if (!Files.exists(filePath1)) {
            Files.createFile(filePath1);
        }
        Files.write(filePath1, Arrays.asList("Line 1", "Line 2", "Line 3"));

        Path filePath2 = Path.of(Environment.currentDirectory + fileSeparator() + "xfile2.txt");
        if (!Files.exists(filePath2)) {
            Files.createFile(filePath2);
        }
        Files.write(filePath2, Arrays.asList("Line 4", "Line 5", "Line 6"));
    }

    @AfterEach
    public void tearDown() throws IOException {
        outContent.close();
        Environment.currentDirectory = CWD;
    }

    @Test
    public void parseAndEvaluate_globwithIOQuoteAndSubstitution_ShouldWorkCorrectly() throws AbstractApplicationException, ShellException, IOException {
        String testCommand = "paste `ls x*` > all.txt";
        // Execute the command
        shell.parseAndEvaluate(testCommand, outContent);
        // Verify that "all.txt" is created and contains expected content
        Path outputFile = Paths.get(Environment.currentDirectory).resolve("all.txt");
        assertTrue(Files.exists(outputFile));
        // Read the contents of "all.txt" and perform further checks as needed
        String fileContent = Files.readString(outputFile);
        // Assertions to check the content
        String expectedContent = "Line 1\tLine 4" + STRING_NEWLINE +  "Line 2\tLine 5" + STRING_NEWLINE +  "Line 3\tLine 6" + STRING_NEWLINE; // This depends on your paste implementation
        assertEquals(expectedContent, fileContent);
    }
    @Test
    public void parseAndEvaluate_CatWithGlobAndIORedirection_ShouldWorkProperly() throws AbstractApplicationException, ShellException, IOException {
        String testCommand = "cat x*.txt > combined.txt";
        shell.parseAndEvaluate(testCommand, outContent);
        // Assert
        Path outputFile = Paths.get(Environment.currentDirectory).resolve("combined.txt");
        assertTrue(Files.exists(outputFile));
        String fileContent = Files.readString(outputFile);
        String expectedContent = "Line 1" + STRING_NEWLINE + "Line 2" + STRING_NEWLINE + "Line 3" + STRING_NEWLINE + "Line 4" + STRING_NEWLINE + "Line 5" + STRING_NEWLINE + "Line 6";
        assertEquals(expectedContent, fileContent.trim());
    }
    @Test
    public void parseAndEvaluate_GlobWithSubstitutionInEchoCommand_ShouldDisplayMatchedFiles() throws AbstractApplicationException, ShellException, IOException {
        String testCommand = "echo `ls x*`";
        shell.parseAndEvaluate(testCommand, outContent);

        // Assert that the output is the names of files that match the pattern
        String expectedOutput = "xfile1.txt xfile2.txt";
        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    public void parseAndEvaluate_EchoWithQuotedGlob_ShouldNotExpand() throws AbstractApplicationException, ShellException, IOException {
        String testCommand = "echo 'x*'";
        shell.parseAndEvaluate(testCommand, outContent);

        // Assert that the output is the literal string "x*" and not expanded
        String expectedOutput = "x*";
        assertEquals(expectedOutput, outContent.toString().trim());
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    public void parseAndEvaluate_IOWithNonExistingGlob_ShouldThrowException() {
        String testCommand = "cat y*.txt > invalidglob.txt"; // assuming no files start with 'y'
        CatException exception = assertThrows(CatException.class, () -> shell.parseAndEvaluate(testCommand, outContent));
        // Assert
        String expectedErrorMessage = "cat: y*.txt: " + ERR_FILE_NOT_FOUND;
        System.out.println(exception.getMessage());
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void parseAndEvaluate_MultipleGlobPatterns_ShouldExpandCorrectly() throws AbstractApplicationException, ShellException, IOException {
        // Testing with multiple glob patterns
        String testCommand = "echo xfile1.txt x*.txt";
        shell.parseAndEvaluate(testCommand, outContent);

        // Assert
        String expectedOutput = "xfile1.txt xfile1.txt xfile2.txt";
        assertEquals(expectedOutput, outContent.toString().trim());
    }
}
