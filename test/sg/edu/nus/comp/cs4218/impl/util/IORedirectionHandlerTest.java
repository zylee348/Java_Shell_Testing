package sg.edu.nus.comp.cs4218.impl.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_FILE_NOT_FOUND;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_SYNTAX;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import sg.edu.nus.comp.cs4218.exception.ShellException;

public class IORedirectionHandlerTest {
    private OutputStream stdout;
    private InputStream stdin;
    private ArgumentResolver argumentResolver;

    private static String echo = "echo";
    private static String cat = "cat";
    private static String path = "path";
    private static String txtfile = "file.txt";
    private static String txtfile1 = "file1.txt";
    private static String txtfile2 = "file2.txt";


    @BeforeEach
    public void setUp() {
        stdin = System.in;
        stdout = new ByteArrayOutputStream();
        argumentResolver = new ArgumentResolver();
    }

    @Test
    void extractRedirOptions_NullArgs_ThrowsInvalidSyntaxsError() {
        IORedirectionHandler handler = new IORedirectionHandler(null, stdin, stdout, argumentResolver);
        ShellException exception = assertThrows(ShellException.class, () -> handler.extractRedirOptions());
        assertEquals(new ShellException(ERR_SYNTAX).getMessage(), exception.getMessage());
    }

    @Test
    void extractRedirOptions_EmptyArgs_ThrowsInvalidSyntaxError() {
        List<String> args = Arrays.asList();
        IORedirectionHandler handler = new IORedirectionHandler(args, stdin, stdout,
                argumentResolver);
        ShellException exception = assertThrows(ShellException.class, () -> handler.extractRedirOptions());
        assertEquals(new ShellException(ERR_SYNTAX).getMessage(), exception.getMessage());
    }

    @Test
    void extractRedirOptions_NoRedir_LeavesCommandUntouched() throws Exception {
        List<String> args = Arrays.asList(echo, "a");
        IORedirectionHandler handler = new IORedirectionHandler(args, stdin, stdout,
                argumentResolver);
        handler.extractRedirOptions();

        assertArrayEquals(args.toArray(), handler.getNoRedirArgsList().toArray());
    }

    @Test
    void extractRedirOptions_NonExistantFile_ThrowsFileNotFoundError() throws Exception {
        List<String> args = Arrays.asList(cat, "<", txtfile);
        IORedirectionHandler handler = new IORedirectionHandler(args, stdin, stdout,
                argumentResolver);
        ShellException exception = assertThrows(ShellException.class, () -> handler.extractRedirOptions());
        assertEquals(new ShellException(ERR_FILE_NOT_FOUND).getMessage(), exception.getMessage());
    }

    @Test
    void extractRedirOptions_SingleRedirectInput_SetsInputToFile(@TempDir Path tempDir) throws Exception {

            Path file1 = tempDir.resolve(txtfile1);
            Files.createFile(file1);
            List<String> args = Arrays.asList(cat, "<", file1.toString());
            IORedirectionHandler handler = new IORedirectionHandler(args, stdin, stdout,
                    argumentResolver);
            handler.extractRedirOptions();

            List<String> expected = Arrays.asList(cat);

            assertArrayEquals(expected.toArray(), handler.getNoRedirArgsList().toArray());
            FileInputStream inputStream = (FileInputStream) handler.getInputStream();
            try {
                Field field = inputStream.getClass().getDeclaredField(path);
                field.setAccessible(true);
                String path = (String) field.get(inputStream);

                assertEquals(file1.toString(), path);
            } finally {
                inputStream.close();
            }
    }

    @Test
    void extractRedirOptions_MultipleRedirectInput_IgnoresMultipleRedirect(@TempDir Path tempDir) throws Exception {
        Path file1 = tempDir.resolve(txtfile);
        Files.createFile(file1);
        List<String> args = Arrays.asList(cat, "<", "<", "<", file1.toString());
        IORedirectionHandler handler = new IORedirectionHandler(args, stdin, stdout,
                argumentResolver);
        handler.extractRedirOptions();

        List<String> expected = Arrays.asList(cat);

        assertArrayEquals(expected.toArray(), handler.getNoRedirArgsList().toArray());
        FileInputStream inputStream = (FileInputStream) handler.getInputStream();
        try {
            Field field = inputStream.getClass().getDeclaredField(path);
            field.setAccessible(true);
            String path = (String) field.get(inputStream);

            assertEquals(file1.toString(), path);
        } finally {
            inputStream.close();
        }
    }

    @Test
    void extractRedirOptions_MultipleRedirectInput_InputFromLastFile(@TempDir Path tempDir) throws Exception {
        Path file1 = tempDir.resolve(txtfile1);
        Files.createFile(file1);
        Path file2 = tempDir.resolve(txtfile2);
        Files.createFile(file2);
        List<String> args = Arrays.asList(cat, "<", file1.toString(), "<", file2.toString());
        IORedirectionHandler handler = new IORedirectionHandler(args, stdin, stdout,
                argumentResolver);
        handler.extractRedirOptions();

        List<String> expected = Arrays.asList(cat);

        assertArrayEquals(expected.toArray(), handler.getNoRedirArgsList().toArray());
        FileInputStream inputStream = (FileInputStream) handler.getInputStream();

        try {
            Field field = inputStream.getClass().getDeclaredField(path);
            field.setAccessible(true);
            String path = (String) field.get(inputStream);

            assertEquals(file2.toString(), path);
        } finally {
            inputStream.close();
        }
    }

    @Test
    void extractRedirOptions_SingleRedirectOutput_SetsOutputToFile(@TempDir Path tempDir) throws Exception {
        Path file1 = tempDir.resolve(txtfile);
        Files.createFile(file1);
        List<String> args = Arrays.asList(echo, "e", ">", file1.toString());
        IORedirectionHandler handler = new IORedirectionHandler(args, stdin, stdout,
                argumentResolver);
        handler.extractRedirOptions();

        List<String> expected = Arrays.asList(echo, "e");

        assertArrayEquals(expected.toArray(), handler.getNoRedirArgsList().toArray());
        FileOutputStream outputStream = (FileOutputStream) handler.getOutputStream();
        try {
            Field field = outputStream.getClass().getDeclaredField(path);
            field.setAccessible(true);
            String path = (String) field.get(outputStream);

            assertEquals(file1.toString(), path);
        } finally {
            outputStream.close();
        }
    }

    @Test
    void extractRedirOptions_MultipleRedirectOutput_IgnoresMultipleRedirect(@TempDir Path tempDir) throws Exception {
        Path file1 = tempDir.resolve(txtfile);
        Files.createFile(file1);
        List<String> args = Arrays.asList(echo, "e", ">", ">", ">", file1.toString());
        IORedirectionHandler handler = new IORedirectionHandler(args, stdin, stdout,
                argumentResolver);
        handler.extractRedirOptions();

        List<String> expected = Arrays.asList(echo, "e");

        assertArrayEquals(expected.toArray(), handler.getNoRedirArgsList().toArray());
        FileOutputStream outputStream = (FileOutputStream) handler.getOutputStream();
        try {
            Field field = outputStream.getClass().getDeclaredField(path);

            field.setAccessible(true);
            String path = (String) field.get(outputStream);

            assertEquals(file1.toString(), path);
        } finally {
            outputStream.close();
        }
    }

    @Test
    void extractRedirOptions_MultipleRedirectOutput_OutputToLastFile(@TempDir Path tempDir) throws Exception {
        Path file1 = tempDir.resolve(txtfile1);
        Files.createFile(file1);
        Path file2 = tempDir.resolve(txtfile2);
        Files.createFile(file2);
        List<String> args = Arrays.asList(echo, "e", ">", file1.toString(), ">", file2.toString());
        IORedirectionHandler handler = new IORedirectionHandler(args, stdin, stdout,
                argumentResolver);
        handler.extractRedirOptions();

        List<String> expected = Arrays.asList(echo, "e");

        assertArrayEquals(expected.toArray(), handler.getNoRedirArgsList().toArray());
        FileOutputStream outputStream = (FileOutputStream) handler.getOutputStream();
        try {
            Field field = outputStream.getClass().getDeclaredField(path);

            field.setAccessible(true);
            String path = (String) field.get(outputStream);

            assertEquals(file2.toString(), path);
        } finally {
            outputStream.close();
        }
    }

    @Test
    void extractRedirOptions_MixedRedirect_TakesFirstRedirect(@TempDir Path tempDir) throws Exception {
        Path file1 = tempDir.resolve(txtfile1);
        Files.createFile(file1);
        List<String> args = Arrays.asList(echo, "e", ">", "<", file1.toString());
        IORedirectionHandler handler = new IORedirectionHandler(args, stdin, stdout,
                argumentResolver);
        handler.extractRedirOptions();

        List<String> expected = Arrays.asList(echo, "e");

        assertArrayEquals(expected.toArray(), handler.getNoRedirArgsList().toArray());
        FileOutputStream outputStream = (FileOutputStream) handler.getOutputStream();
        try {
            Field field = outputStream.getClass().getDeclaredField(path);
            field.setAccessible(true);
            String path = (String) field.get(outputStream);

            assertEquals(file1.toString(), path);
            assertTrue(stdin.equals(handler.getInputStream())); // same reference
        } finally {
            outputStream.close();
        }
    }

    @Test
    void extractRedirOptions_DoubleRedirect_RedirectInputAndOutput(@TempDir Path tempDir) throws Exception {
        Path file1 = tempDir.resolve(txtfile1);
        Files.createFile(file1);
        Path file2 = tempDir.resolve(txtfile2);
        Files.createFile(file2);
        List<String> args = Arrays.asList(cat, "<", file1.toString(), ">", file2.toString());
        IORedirectionHandler handler = new IORedirectionHandler(args, stdin, stdout,
                argumentResolver);
        handler.extractRedirOptions();

        List<String> expected = Arrays.asList(cat);

        assertArrayEquals(expected.toArray(),
                handler.getNoRedirArgsList().toArray());
        FileInputStream inputStream = (FileInputStream) handler.getInputStream();
        FileOutputStream outputStream = (FileOutputStream) handler.getOutputStream();
        try {
            Field inField = inputStream.getClass().getDeclaredField(path);
            inField.setAccessible(true);
            String inPath = (String) inField.get(inputStream);

            assertArrayEquals(expected.toArray(),
                    handler.getNoRedirArgsList().toArray());
            Field outField = outputStream.getClass().getDeclaredField(path);
            outField.setAccessible(true);
            String outPath = (String) outField.get(outputStream);

            assertEquals(file1.toString(), inPath);
            assertEquals(file2.toString(), outPath);
        } finally {
            inputStream.close();
            outputStream.close();
        }
    }

}
