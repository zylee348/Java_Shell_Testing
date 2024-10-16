package sg.edu.nus.comp.cs4218.impl.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver;
import sg.edu.nus.comp.cs4218.impl.util.StringUtils;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.*;

public class PipeApplicationIT {
    private OutputStream stdout;
    private InputStream stdin;
    private PipeCommand pipeCommand;
    private ApplicationRunner appRunner;
    private ArgumentResolver argumentResolver;
    private LinkedList<String> argList1;
    private LinkedList<String> argList2;
    private LinkedList<CallCommand> callCommandsList;
    private Path outputFile;
    private static final String FOLDER_NAME = "test" + CHAR_FILE_SEP + "resources" + CHAR_FILE_SEP + "integration" +
            CHAR_FILE_SEP + "PipeApplicationIT";
    private static final String FILE_ONE = FOLDER_NAME + CHAR_FILE_SEP + "file1.txt";
    private static final String FILE_TWO = FOLDER_NAME + CHAR_FILE_SEP + "file2.txt";
    private static final Path CWD = Paths.get(FOLDER_NAME);

    @BeforeEach
    void setUp() throws IOException {
        stdout = new ByteArrayOutputStream();
        stdin = new ByteArrayInputStream("".getBytes());
        appRunner = new ApplicationRunner();
        argumentResolver = new ArgumentResolver();
        callCommandsList = new LinkedList<>();
        outputFile = Files.createTempFile(CWD, "output", ".txt");
    }

    @AfterEach
    void tearDown() throws IOException {
        delete(outputFile);
        stdout.close();
        stdin.close();
        argList1 = null;
        argList2 = null;
    }

    private void delete(Path path) throws IOException {
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }

    private List<String> getFileContent(Path path) throws IOException {
        System.out.println(path.toString());
        return  Files.readAllLines(path);
    }

    private static List<String> getFileNamesInDirectory(Path directory) throws IOException {
        List<String> list = new ArrayList<>();
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(directory)) {
            for(Path path : paths) {
                if(!Files.isHidden(path)) {
                    list.add(path.getFileName().toString());
                }
            }
            return list;
        }
    }

    @Nested
    class SinglePipeWithApplicationIT {
        private void addCommandListAndPipeEvaluate(List<String> argList1, List<String> argList2) throws
                FileNotFoundException, AbstractApplicationException, ShellException {
            callCommandsList.add(new CallCommand(argList1, appRunner, argumentResolver));
            callCommandsList.add(new CallCommand(argList2, appRunner, argumentResolver));

            pipeCommand = new PipeCommand(callCommandsList);
            pipeCommand.evaluate(stdin, stdout);
        }

        // Found shell: tee: Invalid App
        @Test
        public void evaluate_LsPipeTee_ContentInTextFileSameAsTheListOfFIleNameInCurrDir() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("ls", FOLDER_NAME));
            argList2 = new LinkedList<>(Arrays.asList("tee", outputFile.toString()));
            addCommandListAndPipeEvaluate(argList1,argList2);

            List<String> expected = getFileNamesInDirectory(CWD);
            expected.add(FOLDER_NAME +":");
            List<String> actual = getFileContent(outputFile);
            assertTrue(expected.size()==actual.size() && expected.containsAll(actual)
                    && actual.containsAll(expected));
        }

        // Found shell: paste: Invalid App
        @Test
        public void evaluate_PastePipeGrep_DisplayWordThatContainsYellow() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("paste", FILE_ONE, FILE_TWO));
            argList2 = new LinkedList<>(Arrays.asList("grep", "Yellow"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("Red"+CHAR_TAB+"Yellow"+ STRING_NEWLINE +"Yellow" + CHAR_TAB+
                    STRING_NEWLINE, stdout.toString());
        }

        // Found shell: uniq: Invalid App
        @Test
        public void evaluate_UniqPipeEcho_DisplayTheEchoArgument() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("uniq", FILE_ONE));
            argList2 = new LinkedList<>(Arrays.asList("echo", "Unique line"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("Unique line" + STRING_NEWLINE, stdout.toString());
        }

        @Test
        public void evaluate_EchoPipeCut_DisplayTheChara() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("echo", "baz"));
            argList2 = new LinkedList<>(Arrays.asList("cut", "-b", "2"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("a" + STRING_NEWLINE, stdout.toString());
        }

        @Test
        public void evaluate_EchoPipeTee_DisplayHelloInList() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("echo", "Hello"));
            argList2 = new LinkedList<>(Arrays.asList("tee", outputFile.toString()));
            addCommandListAndPipeEvaluate(argList1,argList2);

            List<String> expected = new ArrayList<>(List.of( "Hello"));
            List<String> actual = getFileContent(outputFile);
            assertEquals(expected,actual);
        }

        @Test
        public void evaluate_GrepPipeCat_DisplayWordYellow() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("grep", "Yellow", FILE_ONE));
            argList2 = new LinkedList<>(List.of("cat"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("Yellow" + STRING_NEWLINE,stdout.toString());
        }

        @Test
        public void evaluate_UniqPipeWc_DisplayTheThreeValuesOfWC() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("uniq", FILE_ONE));
            argList2 = new LinkedList<>(List.of("wc"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("4" + CHAR_TAB + "3" + CHAR_TAB + "18", stdout.toString().trim());
        }

        // Found shell: sort: Invalid App
        @Test
        public void evaluate_SortPipeCutWithBOption_DisplayTheFirstLetterInSortedOrder() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("sort", FILE_TWO));
            argList2 = new LinkedList<>(Arrays.asList("cut", "-b", "1"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("B"+STRING_NEWLINE+"G"+STRING_NEWLINE+"Y"+STRING_NEWLINE, stdout.toString());
        }

        @Test
        public void evaluate_EchoPipeWcWithLOption_DisplayNumberOne() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("echo", "Hello\nWorld"));
            argList2 = new LinkedList<>(Arrays.asList("wc", "-l"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertTrue(stdout.toString().contains("3"));
        }

        @Test
        public void evaluate_lsPipeSortWithROption_DisplayTheListOfFileNameInReverseSortOrder() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("ls", FOLDER_NAME));
            argList2 = new LinkedList<>(Arrays.asList("sort", "-r"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals(FOLDER_NAME+":"+ STRING_NEWLINE+ outputFile.getFileName() + STRING_NEWLINE+
                    "file2.txt\nfile1.txt\n", stdout.toString());
        }

        @Test
        public void evaluate_PastePipeCatWithNOption_Display() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("paste", FILE_ONE, FILE_TWO));
            argList2 = new LinkedList<>(Arrays.asList("cat", "-n"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("1 Red" + CHAR_TAB + "Yellow" + STRING_NEWLINE +
                            "2 Red" + CHAR_TAB + "Blue" + STRING_NEWLINE +
                            "3 Orange" + CHAR_TAB + "Green" + STRING_NEWLINE +
                            "4 Yellow" + CHAR_TAB + STRING_NEWLINE, stdout.toString());
        }

        @Test
        public void evaluate_LsPipeUniqWithdOption_ReturnEmptyAsNoDuplicate() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("ls", FOLDER_NAME));
            argList2 = new LinkedList<>(Arrays.asList("uniq", "-d"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("", stdout.toString());
        }

        @Test
        public void evaluate_CutWithcOptionPipeUniqWithdOption_ReturnEmptyAsNoDuplicate() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("cut", "-c", "1-2", FILE_ONE));
            argList2 = new LinkedList<>(Arrays.asList("uniq", "-c"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("2 Re" + STRING_NEWLINE +"1 Or" + STRING_NEWLINE + "1 Ye" +
                    STRING_NEWLINE, stdout.toString());
        }

        @Test
        public void evaluate_WcPipeCutWithcOption_ReturnTheFirstTwoWcValues() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("wc", FILE_ONE));
            argList2 = new LinkedList<>(Arrays.asList("cut", "-c", "2,4"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("44" + STRING_NEWLINE, stdout.toString());
        }

        @Test
        public void evaluate_CutWithcOptionPipeTee_ReturnAllFirstCharInTheOutputFile() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("cut", "-c", "1", FILE_TWO));
            argList2 = new LinkedList<>(Arrays.asList("tee", outputFile.toString()));
            addCommandListAndPipeEvaluate(argList1,argList2);

            List<String> expected = new ArrayList<>(List.of( "Y", "B", "G"));
            List<String> actual = getFileContent(outputFile);

            assertEquals(expected, actual);
        }

        @Test
        public void evaluate_SortPipeTee_SortTheFileContentAndOutputToAnotherFile() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("sort", FILE_TWO));
            argList2 = new LinkedList<>(Arrays.asList("tee", outputFile.toString()));
            addCommandListAndPipeEvaluate(argList1,argList2);

            List<String> expected = new ArrayList<>(List.of( "Blue", "Green", "Yellow"));
            List<String> actual = getFileContent(outputFile);

            assertEquals(expected, actual);
        }
        @Test
        public void evaluate_CatPipeUniq_DisplayFileOneContentWithoutRepeat() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("cat", FILE_ONE));
            argList2 = new LinkedList<>(List.of("uniq"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("Red" + STRING_NEWLINE + "Orange" + STRING_NEWLINE +
                    "Yellow" + STRING_NEWLINE, stdout.toString());
        }
        @Test
        public void evaluate_CatPipeCut_DisplayTheFirstCharOfFileTwoContent() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("cat", FILE_TWO));
            argList2 = new LinkedList<>(Arrays.asList("cut", "-b", "1"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("Y" + STRING_NEWLINE + "B" + STRING_NEWLINE +
                    "G" + STRING_NEWLINE, stdout.toString());
        }
        @Test
        public void evaluate_CatPipeTee_DisplayFileTwoContentInOutputTxt() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("cat", FILE_TWO));
            argList2 = new LinkedList<>(Arrays.asList("tee", outputFile.toString()));
            addCommandListAndPipeEvaluate(argList1,argList2);

            List<String> expected = new ArrayList<>(List.of( "Yellow", "Blue", "Green"));
            List<String> actual = getFileContent(outputFile);

            assertEquals(expected, actual);
        }

        @Test
        public void evaluate_CutWithcOptionPipeGrep_DisplayRed() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("cut", "-c", "1-4", FILE_TWO));
            argList2 = new LinkedList<>(Arrays.asList("grep", "Blue"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("Blue" + STRING_NEWLINE, stdout.toString());
        }

        @Test
        public void evaluate_LsPipeCut_OutputWordRed() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("ls", FOLDER_NAME));
            argList2 = new LinkedList<>(Arrays.asList("cut", "-c", "1-4"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("test" + STRING_NEWLINE + "file" + STRING_NEWLINE + "file" +
                    STRING_NEWLINE + "outp" + STRING_NEWLINE, stdout.toString());
        }

        @Test
        public void evaluate_LsPipeGrep_DisplayFileOneAndFileTwoFIleName() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("ls", FOLDER_NAME));
            argList2 = new LinkedList<>(Arrays.asList("grep", "file"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("file1.txt\nfile2.txt\n", stdout.toString());
        }

        @Test
        public void evaluate_PastePipeUniqWithdOption_EmptyAsNoDuplicate() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("paste", FILE_ONE, FILE_TWO));
            argList2 = new LinkedList<>(Arrays.asList("uniq", "-d"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("", stdout.toString());
        }
        @Test
        public void evaluate_PasteWithsOptionPipeCutWithcOption_DisplayRedAtTopAndYelAtBottom() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("paste","-s", FILE_ONE, FILE_TWO));
            argList2 = new LinkedList<>(Arrays.asList("cut", "-c", "1-3"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("Red" + STRING_NEWLINE + "Yel" + STRING_NEWLINE, stdout.toString());
        }

        @Test
        public void evaluate_PastePipeTee_DisplayThePasteContentFromFIleOneAndFileTwoToOutputTxt() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("paste", FILE_ONE, FILE_TWO));
            argList2 = new LinkedList<>(Arrays.asList("tee", outputFile.toString()));
            addCommandListAndPipeEvaluate(argList1,argList2);

            List<String> expected = new ArrayList<>(List.of( "Red"+CHAR_TAB+"Yellow",
                    "Red"+CHAR_TAB+"Blue", "Orange"+CHAR_TAB+"Green", "Yellow"+CHAR_TAB));
            List<String> actual = getFileContent(outputFile);

            assertEquals(expected, actual);
        }

        @Test
        public void evaluate_SortPipeCat_DisplaySortedContentOfFileTwo() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("sort", FILE_TWO));
            argList2 = new LinkedList<>(List.of("cat"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("Blue"+STRING_NEWLINE+"Green"+STRING_NEWLINE+"Yellow"+STRING_NEWLINE,
                    stdout.toString());
        }
        @Test
        public void evaluate_PastePipeSort_DisplaySortedPasteOutputContent() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("paste", FILE_ONE, FILE_TWO));
            argList2 = new LinkedList<>(List.of("sort"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            String expected = "Orange"+CHAR_TAB+"Green"+STRING_NEWLINE+
                    "Red"+CHAR_TAB+"Blue"+STRING_NEWLINE+
                    "Red"+CHAR_TAB+ "Yellow"+ STRING_NEWLINE+
                    "Yellow"+CHAR_TAB+STRING_NEWLINE;

            assertEquals(expected, stdout.toString());
        }

        @Test
        public void evaluate_SortWithrOptionPipeUniq_DisplaySortedFileOneContentWithNoRepeat() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("sort","-r", FILE_ONE));
            argList2 = new LinkedList<>(List.of("uniq"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("Yellow" + STRING_NEWLINE + "Red" +STRING_NEWLINE+"Orange"+STRING_NEWLINE,
                    stdout.toString());
        }
        @Test
        public void evaluate_SortPipeGrepWithiOption_DisplayRedFromFileOneContent() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("sort", FILE_ONE));
            argList2 = new LinkedList<>(Arrays.asList("grep", "-i", "red"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("Red" +STRING_NEWLINE+"Red" +STRING_NEWLINE, stdout.toString());
        }

        @Test
        public void evaluate_GrepPipeTee_TwoWordRedInOutputTxt() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("grep", "Red", FILE_ONE));
            argList2 = new LinkedList<>(Arrays.asList("tee", outputFile.toString()));
            addCommandListAndPipeEvaluate(argList1,argList2);

            List<String> expected = new ArrayList<>(List.of( "Red", "Red"));
            List<String> actual = getFileContent(outputFile);

            assertEquals(expected, actual);
        }

        @Test
        public void evaluate_UniqPipeTee_FileOneContentInOutputTxtWithNoRepeats() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("uniq", FILE_ONE));
            argList2 = new LinkedList<>(Arrays.asList("tee", outputFile.toString()));
            addCommandListAndPipeEvaluate(argList1,argList2);

            List<String> expected = new ArrayList<>(List.of( "Red", "Orange", "Yellow"));
            List<String> actual = getFileContent(outputFile);

            assertEquals(expected, actual);
        }
        @Test
        public void evaluate_UniqPipeGrep_DisplaySingleWordRed() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("uniq", FILE_ONE));
            argList2 = new LinkedList<>(Arrays.asList("grep", "Red"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("Red"+STRING_NEWLINE, stdout.toString());
        }

        @Test
        public void evaluate_WcWithlOptionPipeCat_ReturnValueFour() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("wc", "-l", FILE_ONE));
            argList2 = new LinkedList<>(List.of("cat"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertTrue(stdout.toString().contains("4"));
        }
        @Test
        public void evaluate_LsPipeWcWithwOption_ReturnValueFour() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("ls", FOLDER_NAME));
            argList2 = new LinkedList<>(Arrays.asList("wc", "-w"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertTrue(stdout.toString().contains("4"));
        }

        @Test
        public void evaluate_GrepPipeWcWithwOption_ReturnValueTwo() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("grep", "Red", FILE_ONE));
            argList2 = new LinkedList<>(Arrays.asList("wc", "-w"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertTrue(stdout.toString().contains("2"));
        }

        @Test
        public void evaluate_LsPipeCat_ReturnValueFour() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("ls", FOLDER_NAME));
            argList2 = new LinkedList<>(List.of("cat"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals(FOLDER_NAME+":"+STRING_NEWLINE+
                    "file1.txt"+STRING_NEWLINE+"file2.txt"+ STRING_NEWLINE +
                    outputFile.getFileName() + STRING_NEWLINE, stdout.toString());
        }

        @Test
        public void evaluate_EchoPipeCat_DisplayHelloWorld() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("echo", "Hello World"));
            argList2 = new LinkedList<>(List.of("cat"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("Hello World" + STRING_NEWLINE, stdout.toString());
        }

        @Test
        public void evaluate_WcPipeTee_WcOutputIntoOutputTxt() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("wc", FILE_TWO));
            argList2 = new LinkedList<>(Arrays.asList("tee", outputFile.toString()));
            addCommandListAndPipeEvaluate(argList1,argList2);

            List<String> expected = new ArrayList<>(List.of( "3\t3\t17 "+ FILE_TWO));
            List<String> actual = new ArrayList<>(List.of(getFileContent(outputFile).get(0).trim()));

            assertEquals(expected, actual);
        }

        @Test
        public void evaluate_LsPipePaste_WcOutputIntoOutputTxt() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("ls", FOLDER_NAME));
            argList2 = new LinkedList<>(List.of("paste"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals(FOLDER_NAME+":"+STRING_NEWLINE+"file1.txt\nfile2.txt\n"+
                    outputFile.getFileName()+STRING_NEWLINE, stdout.toString());
        }

        @Test
        public void evaluate_InvalidFirstCommandPipeSecondCommand_ThrowsShellExceptionInvalidApp() {
            argList1 = new LinkedList<>(List.of("lsa"));
            argList2 = new LinkedList<>(Arrays.asList("tee", outputFile.toString()));

            ShellException exception = assertThrows(ShellException.class,
                    () -> addCommandListAndPipeEvaluate(argList1,argList2));
            assertEquals("shell: lsa: Invalid app", exception.getMessage());
        }

        @Test
        public void evaluate_FirstCommandPipeInvalidSecondCommand_ThrowsShellExceptionInvalidApp() {
            argList1 = new LinkedList<>(List.of("ls"));
            argList2 = new LinkedList<>(Arrays.asList("teee", outputFile.toString()));

            ShellException exception = assertThrows(ShellException.class,
                    () -> addCommandListAndPipeEvaluate(argList1,argList2));
            assertEquals("shell: teee: Invalid app", exception.getMessage());
        }

        @Test
        public void evaluate_EmptyCommandPipeSecondCommand_ThrowsShellExceptionInvalidSyntax() {
            argList1 = new LinkedList<>();
            argList2 = new LinkedList<>(Arrays.asList("echo", "Hello"));

            ShellException exception = assertThrows(ShellException.class,
                    () -> addCommandListAndPipeEvaluate(argList1,argList2));
            assertEquals("shell: Invalid syntax", exception.getMessage());
        }

        @Test
        public void evaluate_EmptyCommandPipeInvalidSecondCommand_ThrowsShellExceptionInvalidSyntax() {
            argList1 = new LinkedList<>();
            argList2 = new LinkedList<>(Arrays.asList("echoo", "Hello"));

            ShellException exception = assertThrows(ShellException.class,
                    () -> addCommandListAndPipeEvaluate(argList1,argList2));
            assertEquals("shell: Invalid syntax", exception.getMessage());
        }

        @Test
        public void evaluate_InvalidFirstCommandPipeEmptyCommand_ThrowsShellExceptionInvalidApp() {
            argList1 = new LinkedList<>(Arrays.asList("echoo", "Hello"));
            argList2 = new LinkedList<>();

            ShellException exception = assertThrows(ShellException.class,
                    () -> addCommandListAndPipeEvaluate(argList1,argList2));
            assertEquals("shell: echoo: Invalid app", exception.getMessage());
        }
    }

    @Nested
    class DoublePipeWithApplicationIT {
        private List<String> argList3;
        private void addCommandListAndPipeEvaluate(List<String> argList1, List<String> argList2, List<String> argList3)
                throws FileNotFoundException, AbstractApplicationException, ShellException {
            callCommandsList.add(new CallCommand(argList1, appRunner, argumentResolver));
            callCommandsList.add(new CallCommand(argList2, appRunner, argumentResolver));
            callCommandsList.add(new CallCommand(argList3, appRunner, argumentResolver));

            pipeCommand = new PipeCommand(callCommandsList);
            pipeCommand.evaluate(stdin, stdout);
        }

        @Test
        public void evaluate_LsPipeTeePipeSort_return() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("ls", FOLDER_NAME));
            argList2 = new LinkedList<>(Arrays.asList("tee", outputFile.toString()));
            argList3 = new LinkedList<>(Arrays.asList("sort", "-r"));
            addCommandListAndPipeEvaluate(argList1,argList2,argList3);

            assertEquals(FOLDER_NAME +":"+ STRING_NEWLINE+ outputFile.getFileName() + STRING_NEWLINE +
                    "file2.txt\nfile1.txt\n", stdout.toString());

        }

        @Test
        public void evaluate_CatPipeSortPipeTee_SortedValueFromFile2TxtInOutputTxt() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("cat", FILE_TWO));
            argList2 = new LinkedList<>(List.of("sort"));
            argList3 = new LinkedList<>(Arrays.asList("tee", outputFile.toString()));
            addCommandListAndPipeEvaluate(argList1,argList2,argList3);

            List<String> expected = new ArrayList<>(List.of( "Blue", "Green", "Yellow"));
            List<String> actual = getFileContent(outputFile);

            assertEquals(expected, actual);
        }

        @Test
        public void evaluate_SortPipeUniqWithcOptionPipeWcWithwOption_OutputContainsSizWords() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("sort", FILE_ONE));
            argList2 = new LinkedList<>(Arrays.asList("uniq", "-c"));
            argList3 = new LinkedList<>(Arrays.asList("wc", "-w"));
            addCommandListAndPipeEvaluate(argList1,argList2,argList3);

            assertTrue(stdout.toString().contains("6"));
        }

        @Test
        public void evaluate_PastePipeUniqPipeWcWithwOption_ReturnValueSeven() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("paste", FILE_ONE, FILE_TWO));
            argList2 = new LinkedList<>(List.of("uniq"));
            argList3 = new LinkedList<>(Arrays.asList("wc", "-w"));
            addCommandListAndPipeEvaluate(argList1,argList2,argList3);

            assertTrue(stdout.toString().contains("7"));
        }

        @Test
        public void evaluate_FirstCommandPipeSecondCommandPipeInvalidThirdCommand_ThrowsShellExceptionInvalidApp() {
            argList1 = new LinkedList<>(List.of("ls"));
            argList2 = new LinkedList<>(Arrays.asList("tee", outputFile.toString()));
            argList3 = new LinkedList<>(Arrays.asList("wcc", "-l"));

            ShellException exception = assertThrows(ShellException.class,
                    () -> addCommandListAndPipeEvaluate(argList1,argList2,argList3));
            assertEquals("shell: wcc: Invalid app", exception.getMessage());
        }

        @Test
        public void evaluate_FirstCommandPipeSecondCommandPipeEmptyCommand_ThrowsShellExceptionInvalidSyntax() {
            argList1 = new LinkedList<>(List.of("ls"));
            argList2 = new LinkedList<>(Arrays.asList("tee", outputFile.toString()));
            argList3 = new LinkedList<>();

            ShellException exception = assertThrows(ShellException.class,
                    () -> addCommandListAndPipeEvaluate(argList1,argList2,argList3));
            assertEquals("shell: Invalid syntax", exception.getMessage());
        }
    }

    @Nested
    class PipeWithOtherShellCommandIT {
        private void addCommandListAndPipeEvaluate(List<String> argList1, List<String> argList2) throws
                FileNotFoundException, AbstractApplicationException, ShellException {
            callCommandsList.add(new CallCommand(argList1, appRunner, argumentResolver));
            callCommandsList.add(new CallCommand(argList2, appRunner, argumentResolver));

            pipeCommand = new PipeCommand(callCommandsList);
            pipeCommand.evaluate(stdin, stdout);
        }

        @Test
        public void evaluate_PipeAndGlobWithLsAndWcWithwOption_ReturnValueThree() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("ls", FOLDER_NAME+ StringUtils.fileSeparator() + "f*"));
            argList2 = new LinkedList<>(Arrays.asList("wc", "-w"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertTrue(stdout.toString().contains("2"));
        }

        @Test
        public void evaluate_PipeAndGlobWithLsAndCat_ReturnTheOutputTxtFilePath() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("ls", FOLDER_NAME+ StringUtils.fileSeparator() + "o*"));
            argList2 = new LinkedList<>(List.of("cat"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals(FOLDER_NAME+ StringUtils.fileSeparator() + outputFile.getFileName() +
                            STRING_NEWLINE, stdout.toString());
        }

        @Test
        public void evaluate_PipeAndQuotingWithPasteAndGrep_ReturnWordYellow() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("paste", FILE_TWO));
            argList2 = new LinkedList<>(Arrays.asList("grep", "\"Yellow\""));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("Yellow" + STRING_NEWLINE, stdout.toString());
        }

        @Test
        public void evaluate_PipeAndQuotingWithEchoAndCut_ReturnWordBlue() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("echo", "'Blue is great colour'"));
            argList2 = new LinkedList<>(Arrays.asList("cut", "-c", "1-4"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertEquals("Blue" + STRING_NEWLINE, stdout.toString());
        }

        @Test
        public void evaluate_PipeAndCommandSubWithPasteAndTee_FileOneContentInOutputTxt() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("paste", "`ls " + FILE_ONE + "`"));
            argList2 = new LinkedList<>(Arrays.asList("tee", outputFile.toString()));
            addCommandListAndPipeEvaluate(argList1,argList2);

            List<String> expected = new ArrayList<>(List.of( "Red", "Red", "Orange", "Yellow"));
            List<String> actual = getFileContent(outputFile);

            assertEquals(expected, actual);
        }
        @Test
        public void evaluate_PipeAndCommandSubWithEchoAndWc_ReturnValueOne() throws IOException,
                AbstractApplicationException, ShellException {
            argList1 = new LinkedList<>(List.of("echo", "`ls " + FOLDER_NAME + "`"));
            argList2 = new LinkedList<>(Arrays.asList("wc", "-l"));
            addCommandListAndPipeEvaluate(argList1,argList2);

            assertTrue(stdout.toString().contains("2"));
        }

    }



}
