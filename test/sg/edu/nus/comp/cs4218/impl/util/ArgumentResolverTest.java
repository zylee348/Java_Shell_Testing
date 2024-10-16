package sg.edu.nus.comp.cs4218.impl.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.CHAR_FILE_SEP;


public class ArgumentResolverTest {
    private static final String ECHO_CMD = "echo";
    private static final String TEST_STR = "hello";
    ArgumentResolver argumentResolver;

    @BeforeEach
    void setUp() {
        this.argumentResolver = new ArgumentResolver();
    }

    @Test
    public void parseArguments_NullArgsList_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> argumentResolver.parseArguments(null));
    }

    @Test
    public void parseArguments_ValidArgsList_ReturnsEmptyParsedArgsList()
            throws FileNotFoundException, AbstractApplicationException, ShellException {
        List<String> parsedArgsList = argumentResolver.parseArguments(Arrays.asList("echo", "`echo hello`"));
        assertEquals(Arrays.asList(ECHO_CMD, TEST_STR), parsedArgsList);
    }

    @Test
    public void constructor_noParams_doesNotThrowException() {
        assertDoesNotThrow(() -> new ArgumentResolver());
    }


    @Test
    public void resolveOneArgument_Null_ThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> argumentResolver.resolveOneArgument(null));
    }

    @Test
    public void resolveOneArgument_StrWithDoubleQuotes_ReturnsCorrectParsedArgsList() {
        assertDoesNotThrow(() -> {
            List<String> parsedArg = argumentResolver.resolveOneArgument("\"hello\"");
            assertEquals(Arrays.asList(TEST_STR), parsedArg);
        });
    }

    @Test
    public void resolveOneArgument_NestedDoubleQuotes_ReturnsCorrectParsedArgsList() {
        assertDoesNotThrow(() -> {
            List<String> parsedArg = argumentResolver.resolveOneArgument("We are echoing a space: echo \" \".");
            assertEquals(Arrays.asList("We are echoing a space: echo  ."), parsedArg);
        });
    }

    @Test
    public void resolveOneArgument_StrSingleQuotes_ReturnsCorrectParsedArgsList() {
        assertDoesNotThrow(() -> {
            List<String> parsedArg = argumentResolver.resolveOneArgument("\'hello\'");
            assertEquals(Arrays.asList(TEST_STR), parsedArg);

        });
    }

    @Test
    public void resolveOneArgument_StrSingleQuotesAndBackslash_ReturnsCorrectParsedArgsList() {
        assertDoesNotThrow(() -> {
            List<String> parsedArg = argumentResolver.resolveOneArgument("'We are echoing a space and backslashes: echo \" \".'");
            assertEquals(Arrays.asList("We are echoing a space and backslashes: echo \" \"."), parsedArg);
        });
    }

    @Test
    public void resolveOneArgument_StrSingleQuotesAndTwoBackslashes_ReturnsCorrectParsedArgsList() {
        assertDoesNotThrow(() -> {
            List<String> parsedArg = argumentResolver.resolveOneArgument("\"'We are echoing a space: echo \" \"'\"");
            assertEquals(Arrays.asList("'We are echoing a space: echo  '"), parsedArg);
        });
    }

    @Test
    public void resolveOneArgument_StrWithBackslashes_ReturnsCorrectParsedArgsList() {
        assertDoesNotThrow(() -> {
            List<String> parsedArg = argumentResolver.resolveOneArgument("`echo \"hello\"`");
            assertEquals(Arrays.asList(TEST_STR), parsedArg);
        });
    }

    @Test
    public void resolveOneArgument_DoubleQuotesAndBackslashes_ReturnsCorrectParsedArgsList() {
        assertDoesNotThrow(() -> {
            List<String> parsedArg = argumentResolver.resolveOneArgument("\"`echo 'Hello'`\"");
            assertEquals(Arrays.asList("Hello "), parsedArg);
        });
    }

    @Test
    public void resolveOneArgument_SingleQuotesAndBackslashes_ReturnsCorrectParsedArgsList() {
        assertDoesNotThrow(() -> {
            List<String> parsedArg = argumentResolver.resolveOneArgument("'`echo 'Hello'`'");
            assertEquals(Arrays.asList("`echo Hello`"), parsedArg);
        });
    }

    @Test
    public void resolveOneArgument_SingleQuotesAndDoubleQuotesAndBackslashes1_ReturnsCorrectParsedArgsList() {
        assertDoesNotThrow(() -> {
            List<String> parsedArg = argumentResolver.resolveOneArgument("\"'echo `echo \"Hello\"`'\"");
            assertEquals(Arrays.asList("'echo Hello '"), parsedArg);
        });
    }

    @Test
    public void resolveOneArgument_SingleQuotesAndDoubleQuotesAndBackslashes2_ReturnsCorrectParsedArgsList() {
        assertDoesNotThrow(() -> {
            List<String> parsedArg = argumentResolver.resolveOneArgument("\"echo `echo '\"nxx'`");
            assertEquals(Arrays.asList("echo \"nxx "), parsedArg);
        });
    }

    @Test
    public void resolveOneArgument_ArgWithSingleQuotesAndDoubleQuotesAndBackslashes3_ReturnsCorrectParsedArgsList() {
        assertDoesNotThrow(() -> {
            List<String> parsedArg = argumentResolver.resolveOneArgument("\"echo `echo '\"nxx'''``");
            assertEquals(Arrays.asList("echo \"nxx "), parsedArg);
        });
    }

    @Test
    public void resolveOneArgument_SingleQuotesAndDoubleQuotesAndBackslashes4_ReturnsCorrectParsedArgsList() {
        assertDoesNotThrow(() -> {
            List<String> parsedArg = argumentResolver.resolveOneArgument("'\"We are echoing a space `echo \" \"`\"'");
            assertEquals(Arrays.asList("\"We are echoing a space `echo \" \"`\""), parsedArg);
        });
    }

}