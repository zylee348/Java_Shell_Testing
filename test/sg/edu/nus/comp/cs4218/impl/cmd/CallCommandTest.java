package sg.edu.nus.comp.cs4218.impl.cmd;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_SYNTAX;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import static org.mockito.Mockito.*;

public class CallCommandTest {
    ApplicationRunner appRunnerStub = mock(ApplicationRunner.class);
    ArgumentResolver argResolverStub = mock(ArgumentResolver.class);

    ApplicationRunner applicationRunner = new ApplicationRunner();
    ArgumentResolver argumentResolver = new ArgumentResolver();
    CallCommand callCommand;
    private InputStream stdin;
    private OutputStream stdout;

    private static final List<String> ARG_LIST = Arrays.asList("echo", "hello", "world");

    @BeforeEach
    void setup() {
        stdin = new ByteArrayInputStream(new byte[0]);
        stdout = new ByteArrayOutputStream();
    }

    @AfterEach
    void reset() throws IOException {
        stdin.close();
        stdout.flush();
    }
    @Test
    public void callCommandConstructor_validStubArguments_runsWithoutExceptions() {
        assertDoesNotThrow(
                () -> new CallCommand(new ArrayList<>(), appRunnerStub, argResolverStub)
        );
    }

    @Test
    void evaluate_emptyArgsList_ThrowShellException() {
        callCommand = new CallCommand(new ArrayList<>(), appRunnerStub, argResolverStub);
        ShellException shellException = assertThrows(ShellException.class, () -> {
            callCommand.evaluate(stdin, stdout);
        });
        assertEquals("shell: " + ERR_SYNTAX, shellException.getMessage());
    }

    @Test
    void evaluate_nullArgsList_ThrowShellException() {
        callCommand = new CallCommand(null, applicationRunner, argumentResolver);
        ShellException shellException = assertThrows(ShellException.class, () -> {
            callCommand.evaluate(stdin, stdout);
        });
        assertEquals("shell: " + ERR_SYNTAX, shellException.getMessage());
    }

    @Test
    void evaluate_echoCommandArgsList_OutputsCorrectly() throws FileNotFoundException, AbstractApplicationException, ShellException {
        List<String> args = Arrays.asList("echo", "hello world");
        callCommand = new CallCommand(args, applicationRunner, argumentResolver);
        String expectedOutput = "hello world" + STRING_NEWLINE;
        callCommand.evaluate(stdin, stdout);
        assertEquals(expectedOutput, stdout.toString());
    }


    @Test
    void getArgList_emptyArgsListCallCommand_ReturnsEmptyArgList() {
        callCommand = new CallCommand(new ArrayList<>(), applicationRunner, argumentResolver);
        List<String> expectedList = new ArrayList<>();
        assertEquals(expectedList, callCommand.getArgsList());
    }

    @Test
    void getArgList_nonEmptyArgsListCallCommand_ReturnsCorrectArgList() {
        callCommand = new CallCommand(ARG_LIST, applicationRunner, argumentResolver);

        List<String> expectedList = Arrays.asList("echo", "hello", "world");
        assertEquals(expectedList, callCommand.getArgsList());
    }

}