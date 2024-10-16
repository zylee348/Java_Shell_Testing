package sg.edu.nus.comp.cs4218.impl.util;

import static org.junit.jupiter.api.Assertions.*;
import static sg.edu.nus.comp.cs4218.impl.util.ErrorConstants.ERR_SYNTAX;
import static sg.edu.nus.comp.cs4218.impl.util.StringUtils.STRING_NEWLINE;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sg.edu.nus.comp.cs4218.Command;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.SequenceCommand;

import java.util.List;

public class CommandBuilderTest {

    private ApplicationRunner appRunner;

    @BeforeEach
    public void setUp() {
        appRunner = new ApplicationRunner();
    }

    @Test
    public void parseCommand_ParseBlankCommand_ThrowsSyntaxException() throws ShellException {
        String commandString = " ";
        ShellException thrown = assertThrows(ShellException.class, () -> {
            CommandBuilder.parseCommand(commandString, appRunner);
        });
        String expected = "shell: " + ERR_SYNTAX;
        assertEquals(expected, thrown.getMessage());
    }
    @Test
    public void parseCommand_ParseNewLineCommand_ThrowsSyntaxException() throws ShellException {
        String commandString = STRING_NEWLINE;
        ShellException thrown = assertThrows(ShellException.class, () -> {
            CommandBuilder.parseCommand(commandString, appRunner);
        });
        String expected = "shell: " + ERR_SYNTAX;
        assertEquals(expected, thrown.getMessage());
    }
    @Test
    public void parseCommand_ParseUnMatchedArguments_ThrowsSyntaxException() throws ShellException {
        String commandString = "'unmatched quote";
        ShellException thrown = assertThrows(ShellException.class, () -> {
            CommandBuilder.parseCommand(commandString, appRunner);
        });
        String expected = "shell: " + ERR_SYNTAX;
        assertEquals(expected, thrown.getMessage());
    }
    @Test
    public void parseCommand_ParseSemiColonOnly_ThrowsSyntaxException() throws ShellException {
        String commandString = ";";
        ShellException thrown = assertThrows(ShellException.class, () -> {
            CommandBuilder.parseCommand(commandString, appRunner);
        });
        String expected = "shell: " + ERR_SYNTAX;
        assertEquals(expected, thrown.getMessage());
    }
    @Test
    public void parseCommand_ParsePipeOnly_ThrowsSyntaxException() throws ShellException {
        String commandString = "|";
        ShellException thrown = assertThrows(ShellException.class, () -> {
            CommandBuilder.parseCommand(commandString, appRunner);
        });
        String expected = "shell: " + ERR_SYNTAX;
        assertEquals(expected, thrown.getMessage());
    }
    @Test
    public void parseCommand_CallCommand_ReturnsCallCommandObject() throws ShellException {
        String commandString = "echo 'hello world'";
        Command command = CommandBuilder.parseCommand(commandString, appRunner);
        assertTrue(command instanceof CallCommand);
    }

    @Test
    public void parseCommand_PipeCommand_ReturnsPipeCommandObject() throws ShellException {
        String commandString = "cat file.txt | grep 'hello'";
        Command command = CommandBuilder.parseCommand(commandString, appRunner);
        assertTrue(command instanceof PipeCommand);
    }

    @Test
    public void parseCommand_SequenceCommand_ReturnsSequenceCommandObject() throws ShellException {
        String commandString = "echo 'hello'; echo 'world'";
        Command command = CommandBuilder.parseCommand(commandString, appRunner);
        assertTrue(command instanceof SequenceCommand);
    }
    @Test
    public void parseCommand_WithInputRedirection_ReturnsCallCommandWithRedirectionToken() throws ShellException {
        String commandString = "cat < input.txt";
        CallCommand command = (CallCommand) CommandBuilder.parseCommand(commandString, appRunner);

        // Assuming you have a way to retrieve tokens from CallCommand, e.g., a getTokens method.
        List<String> tokens = command.getArgsList();
        assertTrue(tokens.contains("<"));
        assertTrue(tokens.contains("input.txt"));
    }

    @Test
    public void parseCommand_WithOutputRedirection_ReturnsCallCommandWithRedirectionToken() throws ShellException {
        String commandString = "echo hello > output.txt";
        CallCommand command = (CallCommand) CommandBuilder.parseCommand(commandString, appRunner);

        // Assuming you have a way to retrieve tokens from CallCommand, e.g., a getTokens method.
        List<String> tokens = command.getArgsList();
        assertTrue(tokens.contains(">"));
        assertTrue(tokens.contains("output.txt"));
    }
}
