package sg.edu.nus.comp.cs4218.impl.cmd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_INVALID_APP;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.util.ApplicationRunner;
import sg.edu.nus.comp.cs4218.impl.util.ArgumentResolver;

public class SequenceCommandTest {
    private OutputStream stdout;
    private InputStream stdin;
    private ApplicationRunner appRunner;
    private ArgumentResolver argumentResolver;

    @BeforeEach
    public void setUp() {
        stdin = System.in;
        stdout = new ByteArrayOutputStream();
        appRunner = new ApplicationRunner();
        argumentResolver = new ArgumentResolver();
    }


    @Test
    void evaluate_ListOfValidCommands_ReturnConcatenatedOutput() throws Exception {
        List<Command> cmds = new LinkedList<>();
        cmds.add(new CallCommand(Arrays.asList("echo", "a"), appRunner, argumentResolver));
        cmds.add(new CallCommand(Arrays.asList("echo", "b"), appRunner, argumentResolver));
        Command seqCommand = new SequenceCommand(cmds);

        seqCommand.evaluate(stdin, stdout);

        assertEquals("a\nb" + STRING_NEWLINE, stdout.toString());
    }

    @Test
    void evaluate_InvalidCommands_ReturnInvalidAppMessageOutput() throws Exception {
        List<Command> cmds = new LinkedList<>();
        cmds.add(new CallCommand(Arrays.asList("invalidapp"), appRunner, argumentResolver));
        Command seqCommand = new SequenceCommand(cmds);

        seqCommand.evaluate(stdin, stdout);

        String expected = new ShellException("invalidapp: " + ERR_INVALID_APP).getMessage() + STRING_NEWLINE;
        assertEquals(expected, stdout.toString());
    }

    @Test
    void evaluate_ValidAndInvalidCommands_RunsAllCommands() throws Exception {
        List<Command> cmds = new LinkedList<>();
        cmds.add(new CallCommand(Arrays.asList("echo", "a"), appRunner, argumentResolver));
        cmds.add(new CallCommand(Arrays.asList("invalidapp"), appRunner, argumentResolver));
        cmds.add(new CallCommand(Arrays.asList("echo", "b"), appRunner, argumentResolver));
        Command seqCommand = new SequenceCommand(cmds);

        seqCommand.evaluate(stdin, stdout);

        String expected = new ShellException("invalidapp: " + ERR_INVALID_APP).getMessage() + STRING_NEWLINE;
        expected = "a" + STRING_NEWLINE + expected + "b" + STRING_NEWLINE;
        assertEquals(expected, stdout.toString());
    }

}
